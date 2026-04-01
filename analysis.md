# JDK 8 vs JDK 21 对象池性能对比分析

> 分析日期：2026-04-01
> 
> 测试环境：Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

## 1. 核心发现

### 1.1 单个操作性能对比 (ops/us)

| 实现 | JDK 8 | JDK 21 | 提升幅度 | 备注 |
|------|-------|--------|----------|------|
| Frogspawn001 | 152 | **1630** | **+972%** 🚀 | JDK 21 最优 |
| Frogspawn004 | 402 | **1636** | **+307%** 🚀 | JDK 21 最优 |
| Frogspawn003 | **1029** | 1515 | +47% | JDK 8 最优 |
| Frogspawn007 | 466 | 1572 | +237% | |
| Frogspawn002 | 310 | 1364 | +340% | |
| JavaNew001 | 662 | 613 | -7% | 基线 |
| LitePool001 | 2.3 | 254 | **+10943%** 🚀 | JDK 21 兼容性改进 |
| StormPot | 292 (BlazePool) | 269 | -8% | |
| BeeOp001FastPool | 38 | 54 | +42% | |

### 1.2 批量操作性能对比 (ops/ms)

| 实现 | JDK 8 | JDK 21 | 提升幅度 | 备注 |
|------|-------|--------|----------|------|
| JavaNew001 | **18122** | **16751** | -8% | 两者均为最优 |
| Frogspawn001 | 934 | 897 | -4% | |
| Frogspawn006 | 892 | 891 | ~0% | |
| Frogspawn003 | 889 | 870 | -2% | |
| FastPool002Disruptor | 658 | **937** | **+42%** | JDK 21 改进显著 |
| Frogspawn008 | 901 | 883 | -2% | |
| BeeOp001FastPool | 175 | 195 | +11% | |

## 2. 关键结论

### 2.1 JDK 21 带来的巨大性能提升

**Frogspawn 系列**：
- JDK 21 对 Frogspawn 性能提升巨大，特别是 Frogspawn001/004 达到 1600+ ops/us
- 相比 JDK 8 最优的 Frogspawn003（1029 ops/us），JDK 21 最优性能提升 **59%**
- 相比 JDK 8 的 Java new 基线（662 ops/us），JDK 21 的 Frogspawn 快 **2.6 倍**

**LitePool**：
- 从 JDK 8 的 2.3 ops/us 提升到 JDK 21 的 254 ops/us，提升超过 100 倍
- 推测 JDK 21 修复了 LitePool 的兼容性问题或 LitePool 针对 JDK 21 进行了优化

**FastPool+Disruptor**：
- 批量操作从 658 提升到 937 ops/ms，提升 42%

### 2.2 最优策略选择变化

| 版本 | 单操作最优 | 批量操作最优 | 说明 |
|------|-----------|--------------|------|
| JDK 8 | Frogspawn003 (1029 ops/us) | Java new (18122 ops/ms) | 003 = PARK+THROW |
| JDK 21 | Frogspawn001/004 (1630+ ops/us) | Java new (16751 ops/ms) | 001=SPIN+THROW, 004=SPIN+CREATE |

**策略差异分析**：
- JDK 8：`PARK` 策略（003）最优，说明 JDK 8 下自旋等待成本较高
- JDK 21：`SPIN` 策略（001/004）最优，说明 JDK 21 优化了自旋等待性能

### 2.3 通用结论（两版本一致）

1. **批量操作**：直接 `new` 始终是最快选择，但 GC 压力大
2. **单个操作**：对象池（Frogspawn）比直接 `new` 更快，且 GC 友好
3. **资源受限场景**：应选择对象池而非直接 new

## 3. 版本独有实现

### JDK 8 独有

| 实现 | 性能 | 说明 |
|------|------|------|
| GenericObjectPool001 | 7.3 ops/us | 通用对象池 |
| StormPot001BlazePool | 292 ops/us | Blaze 模式 |
| StormPot002QueuePool | 4.2 ops/us | Queue 模式，性能较差 |

### JDK 21 独有

| 实现 | 性能 | 说明 |
|------|------|------|
| NoMoreInstance001SweepClean | 625 ops/us | 接近 new 性能，但 GC 不友好 |
| NoMoreInstance002Clean | 2.2 ops/us | 性能较差 |
| Recall001 | 8.6 ops/us | 性能一般 |
| StormPot001 | 269 ops/us | 统一版本，不再区分 Blaze/Queue |

## 4. JDK 21 性能优化原因推测

### 4.1 JVM 层面改进

1. **VarHandle 优化**：JDK 9+ 引入 VarHandle，JDK 21 进一步优化
2. **内存屏障改进**：更高效的 volatile/内存屏障实现
3. **逃逸分析增强**：更好的标量替换和逃逸分析
4. **Virtual Threads 基础设施**：底层并发原语优化

### 4.2 对象池受益分析

| 优化项 | 受益实现 | 说明 |
|--------|----------|------|
| 自旋等待优化 | Frogspawn SPIN 策略 | JDK 21 下 SPIN 性能大幅提升 |
| CAS 操作优化 | 所有无锁实现 | Atomic 操作更高效 |
| ThreadLocal 优化 | LitePool 等 | JDK 21 ThreadLocal 性能改进 |

## 5. 最终推荐

### JDK 8 环境

| 场景 | 推荐 | 配置 |
|------|------|------|
| 高并发单操作 | **Frogspawn003** | FetchStrategy=PARK, FailStrategy=THROW |
| 批量操作 | 直接 new | 无需对象池 |
| 低 CPU 占用 | Frogspawn006 | FetchStrategy=PARK, FailStrategy=CREATE |

### JDK 21 环境

| 场景 | 推荐 | 配置 |
|------|------|------|
| 高并发单操作 | **Frogspawn001** 或 **004** | FetchStrategy=SPIN, FailStrategy=THROW/CREATE |
| 批量操作 | 直接 new | 无需对象池 |
| 低 CPU 占用 | Frogspawn003 | FetchStrategy=PARK, FailStrategy=THROW |
| 弹性扩容需求 | Frogspawn004 或 006 | FailStrategy=CREATE |

## 6. 测试失败对比

| 实现 | JDK 8 | JDK 21 | 失败原因 |
|------|-------|--------|----------|
| ApacheCommonsPool2002SoftRefPool | ❌ | ❌ | makeObject() 返回 null |
| BeeOp002ObjectSource | ❌ | ❌ | 代理类转换失败 |
| CoralPool001ArrayPool | ✅ | ❌ | ArrayIndexOutOfBoundsException |
| CoralPool002LinkedPool | ✅ | ❌ | NullPointerException |
| Frogspawn002/005 批量 | ❌ | ✅ | NOT_AVAILABLE 策略在 JDK 8 批量测试抛异常 |

**说明**：
- JDK 21 修复了 Frogspawn002/005 的批量测试问题
- CoralPool 在 JDK 21 下出现新问题

## 7. 总结

1. **JDK 21 是对象池的最佳选择**：Frogspawn 在 JDK 21 下性能提升 3-10 倍
2. **策略选择需随 JDK 版本调整**：JDK 8 选 PARK，JDK 21 选 SPIN
3. **批量操作始终用 new**：两个版本下直接 new 都是最快选择
4. **LitePool 在 JDK 21 下可用**：从 2.3 ops/us 提升到 254 ops/us