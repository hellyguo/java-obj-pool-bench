# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 21**

> this is the result for JVM9+. If need the result for JVM8, please switch to branch `jvm8`.

## 对比测试结果

[result](result.md)

## JDK 版本对比分析

[JDK 8 vs JDK 21 性能对比分析](docs/analysis.md)

## 结论

### 单个对象操作 (get/return)

| 推荐等级 | 实现 | 性能 (ops/us) | 说明 |
|---------|------|---------------|------|
| ⭐⭐⭐ | **Frogspawn001/004** | ~1630 | 比直接 new 快 2.6 倍，极高性能 |
| ⭐⭐ | Frogspawn003/007 | ~1540 | 性能优秀，适合不同策略需求 |
| ⭐⭐ | Frogspawn002/005/006/008/009 | ~1350-1500 | 不同策略组合，性能稳定 |
| ⭐ | JavaNew001 | 613 | 基准参照 |
| ⭐ | NoMoreInstance001(SweepClean) | 625 | 接近 new 性能，但 GC 不友好 |
| ⭐ | StormPot001 | 269 | 性能良好 |
| ⭐ | LitePool001 | 254 | 性能良好 |
| ⚠️ | BeeOp001FastPool | 54 | 性能一般 |
| ⚠️ | 其他实现 | <20 | 性能较低 |

### 批量对象操作 (批量 get/return)

| 推荐等级 | 实现 | 性能 (ops/ms) | 说明 |
|---------|------|---------------|------|
| ⭐⭐⭐ | **JavaNew001** | ~16751 | 直接 new 最快，但 GC 压力大 |
| ⭐⭐⭐ | **NoMoreInstance001(SweepClean)** | ~16845 | 与 new 持平，GC 不友好 |
| ⭐⭐ | FastPool002Disruptor | ~937 | 基于Disruptor，性能优秀 |
| ⭐⭐ | Frogspawn001/003/004/006/008 | ~870-900 | 性能稳定，GC 友好 |
| ⭐ | BeeOp001FastPool | ~195 | 性能良好 |
| ⚠️ | 其他实现 | <150 | 性能较低 |

### Frogspawn 策略选择

| 编号 | FetchStrategy | FailStrategy | 单个(ops/us) | 批量(ops/ms) | 推荐场景 |
|------|---------------|--------------|--------------|--------------|----------|
| 001 | SPIN | THROW | 1630 | 897 | 高性能首选 |
| 002 | YIELD | THROW | 1364 | - | CPU 亲和性低 |
| 003 | PARK | THROW | 1515 | 870 | 平衡性能与CPU |
| 004 | SPIN | CREATE | 1636 | 875 | 高性能+弹性 |
| 005 | YIELD | CREATE | 1379 | - | 低CPU占用+弹性 |
| 006 | PARK | CREATE | 1373 | 891 | 平衡+弹性 |
| 007 | SPIN | WAIT | 1572 | 678 | 高性能+阻塞等待 |
| 008 | YIELD | WAIT | 1317 | 883 | 低CPU+阻塞等待 |
| 009 | PARK | WAIT | 1504 | 672 | 平衡+阻塞等待 |

> 策略组合说明：
> - **SPIN**: 自旋等待，CPU 消耗高但延迟最低
> - **YIELD**: 让出 CPU 时间片，适合低竞争场景
> - **PARK**: 线程休眠，CPU 消耗最低
> - **THROW**: 无可用对象时抛异常
> - **CREATE**: 无可用对象时创建新对象
> - **WAIT**: 无可用对象时阻塞等待

### 场景推荐

1. **高频单个对象操作**: `Frogspawn001` 或 `Frogspawn004`
2. **批量对象操作**: 若可接受 GC 压力用 `JavaNew`，否则用 `Frogspawn001`
3. **低 CPU 占用场景**: `Frogspawn003` 或 `Frogspawn006`
4. **需要弹性扩容**: 选择 `*_CREATE` 策略 (004/005/006)
5. **资源严格受限**: 选择 `*_WAIT` 策略 (007/008/009)
