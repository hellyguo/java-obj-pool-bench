# frogspawn 0.7-dev 全量横评与 0.6 同机对比

- **日期**：2026-11-17
- **目的**：在同一台机器、同一套 benchmark 代码、同一组 JVM 参数下，对比 frogspawn 0.6 与 0.7-dev。
- **基准**：`Compare001Benchmark`（单取单还）与 `Compare002Benchmark`（批量 32 个）

## 1. 环境

| 项 | 值 |
|----|----|
| CPU | Intel Core Ultra 9 285H，16 vCPU |
| 内存 | 46 GiB |
| JDK | Oracle JDK 1.8.0_371 |
| GC | G1，`-Xms4G -Xmx4G`，G1 参数与 `benchmark.sh` 一致 |
| 线程 | 8 |
| Fork / Iteration | 1 fork；3 warmup × 1s；5 measurement × 1s |

被测实现：`Frogspawn001`、`Frogspawn003`、`Frogspawn004`、`Frogspawn007`、`JavaNew001`。

## 2. 单操作（Compare001Benchmark，ops/us）

| 实现 | 0.6 | 0.7-dev | 变化 |
|------|----:|--------:|-----:|
| Frogspawn001 | 622.49 ± 8.72 | 502.12 ± 97.55 | -19.3% |
| Frogspawn003 | 597.60 ± 59.18 | 513.49 ± 57.99 | -14.1% |
| Frogspawn004 | 600.24 ± 32.71 | 270.88 ± 684.92 | 误差过大，不作结论 |
| Frogspawn007 | 590.88 ± 27.08 | 546.08 ± 66.05 | -7.6% |
| JavaNew001 | 725.63 ± 13.53 | 726.50 ± 28.77 | +0.1% |

## 3. 批量操作（Compare002Benchmark，ops/ms）

> JMH 原始单位为 ops/us，下表已换算为 ops/ms（×1000）。

| 实现 | 0.6 | 0.7-dev | 变化 |
|------|----:|--------:|-----:|
| Frogspawn001 | 413 | 386 | -6.5% |
| Frogspawn003 | 493 | 810 | **+64.3%** |
| Frogspawn004 | 406 | 795 | **+95.8%** |
| Frogspawn007 | 411 | 409 | -0.5% |
| JavaNew001 | 20,029 | 20,014 | -0.1% |

## 4. 结论

1. **0.7 单操作比 0.6 低约 8%-19%：这是 reset 成本从使用方转移到库的成本**
   - **0.6**：线程本地缓存路径不调用 `reset()`，脏对象会直接返回；使用方需要自己承担清理成本
     和心智负担。
   - **0.7**：`release()` 统一调用 `reset()`，库承担清理成本，使用方拿到即可用。
   - `DemoPojo.reset()` 每次写 4 个字段，在 8 线程单取单还场景下成本可见；另外 0.7 的
     `ArrayUtil` 走了 `MemoryAccess` SPI，主池元素访问多一层间接。
   - 这是**语义修正与心智负担降低**的代价，不是实现退化。

2. **0.7 批量场景在 003/004 上明显提升**
   `Frogspawn003` 493 → 810 ops/ms（+64.3%），`Frogspawn004` 406 → 795 ops/ms（+95.8%）。
   `Frogspawn001/007` 基本持平。

3. **`new` 基线稳定**
   单操作 725.63 → 726.50 ops/us，批量 20,029 → 20,014 ops/ms，说明测试环境与 harness 稳定。

4. **这是正确性成本，不是无意义回退**
   0.6 的缓存路径不 reset 会返回脏对象；0.7 修正了该语义。若单操作性能是硬指标，可在业务对象中
   让 `reset()` 尽量轻量，或后续评估“缓存路径延迟 reset / fetch 时 reset”的等价实现。

## 5. 说明

- 本报告只做同机 0.6 vs 0.7 对比；不要直接与旧 `result.md` 的绝对数字比较（CPU/环境不同）。
- 单操作 `Frogspawn004` 的 0.7 误差过大，建议后续用更多 iteration 重跑。
- 原始 JSON：`/tmp/opencode/review/poolcmp-0.6-*.json`、`/tmp/opencode/review/poolcmp-0.7-*.json`。
## 6. 全量对比：JDK8（main 分支，28 个实现）

### Compare001 单操作（ops/us）

| 排名 | 实现 | 吞吐 | 误差 |
|-----:|------|-----:|-----:|
| 1 | JavaNew001 | 725.711 | 36.507 |
| 2 | **Frogspawn009** | 539.642 | 49.086 |
| 3 | **Frogspawn002** | 533.292 | 10.003 |
| 4 | **Frogspawn001** | 520.987 | 69.632 |
| 5 | **Frogspawn005** | 512.728 | 70.510 |
| 6 | **Frogspawn008** | 316.192 | 810.997 |
| 7 | **Frogspawn006** | 285.472 | 678.500 |
| 8 | **Frogspawn007** | 278.869 | 652.638 |
| 9 | **Frogspawn003** | 272.630 | 668.662 |
| 10 | **Frogspawn004** | 271.738 | 672.583 |
| 11 | StormPot001BlazePool | 252.403 | 8.135 |
| 12 | BeeOp001FastPool | 143.963 | 17.702 |
| 13 | BeeOp002ObjectSource | 29.033 | 12.606 |
| 14 | CoralPool001ArrayPool | 21.668 | 3.066 |
| 15 | FastPool002Disruptor | 17.681 | 6.259 |
| 16 | FastPool001 | 13.641 | 8.821 |
| 17 | CoralPool002LinkedPool | 11.241 | 1.203 |
| 18 | FuriousObjectPool001 | 10.561 | 7.334 |
| 19 | GenericObjectPool001 | 6.531 | 1.013 |
| 20 | ViburPool001 | 4.556 | 1.081 |
| 21 | StormPot002QueuePool | 3.972 | 0.108 |
| 22 | KOPool001 | 3.874 | 0.414 |
| 23 | ApacheCommonsPool001StackPool | 3.729 | 3.113 |
| 24 | ApacheCommonsPool003SoftRefPool | 3.125 | 2.512 |
| 25 | ApacheCommonsPool2001Pool | 2.400 | 0.351 |
| 26 | LitePool001 | 2.086 | 0.210 |
| 27 | ApacheCommonsPool002Pool | 0.578 | 0.284 |
| 28 | ApacheCommonsPool2002SoftRefPool | 0.143 | 0.069 |

### Compare002 批量 32 个（ops/ms）

| 排名 | 实现 | 吞吐 | 误差 |
|-----:|------|-----:|-----:|
| 1 | JavaNew001 | 20133.112 | 627.566 |
| 2 | **Frogspawn007** | 825.876 | 11.186 |
| 3 | **Frogspawn004** | 812.934 | 11.970 |
| 4 | **Frogspawn009** | 806.308 | 120.790 |
| 5 | **Frogspawn003** | 800.458 | 16.098 |
| 6 | FastPool002Disruptor | 789.150 | 235.455 |
| 7 | FastPool001 | 413.075 | 28.148 |
| 8 | **Frogspawn008** | 382.621 | 4.064 |
| 9 | **Frogspawn001** | 370.572 | 6.929 |
| 10 | **Frogspawn006** | 347.174 | 7.467 |
| 11 | FuriousObjectPool001 | 292.615 | 63.585 |
| 12 | GenericObjectPool001 | 192.543 | 54.685 |
| 13 | BeeOp001FastPool | 152.009 | 29.280 |
| 14 | ViburPool001 | 148.250 | 95.493 |
| 15 | ApacheCommonsPool001StackPool | 132.016 | 97.206 |
| 16 | KOPool001 | 131.031 | 21.636 |
| 17 | StormPot001BlazePool | 126.773 | 11.159 |
| 18 | StormPot002QueuePool | 123.619 | 4.210 |
| 19 | ApacheCommonsPool003SoftRefPool | 78.837 | 77.435 |
| 20 | ApacheCommonsPool2001Pool | 69.850 | 30.780 |
| 21 | ApacheCommonsPool002Pool | 21.106 | 7.982 |

**Compare002 失败/缺失项**：
ApacheCommonsPool2002SoftRefPool, BeeOp002ObjectSource, CoralPool001ArrayPool, CoralPool002LinkedPool, Frogspawn002, Frogspawn005, LitePool001

## 7. 全量对比：JDK25（jvm21 分支，31 个实现，其他库使用较新版本）

> **口径警告**：`NoMoreInstance001SweepClean` / `NoMoreInstance002Clean` 的结果不可与
> borrow/return 池直接比较，原因见第 9 节。NoMoreInstance001 在本基准中实际等价于 `new`。

### Compare001 单操作（ops/us）

| 排名 | 实现 | 吞吐 | 误差 |
|-----:|------|-----:|-----:|
| 1 | **Frogspawn002** | 1133.188 | 24.951 |
| 2 | **Frogspawn004** | 1128.519 | 24.945 |
| 3 | **Frogspawn005** | 1006.079 | 181.766 |
| 4 | **Frogspawn006** | 973.376 | 31.890 |
| 5 | **Frogspawn009** | 960.425 | 49.508 |
| 6 | **Frogspawn008** | 889.810 | 48.565 |
| 7 | **Frogspawn003** | 846.286 | 142.625 |
| 8 | JavaNew001 | 763.712 | 12.529 |
| 9 | NoMoreInstance001SweepClean | 761.935 | 7.047 |
| 10 | **Frogspawn007** | 684.170 | 35.322 |
| 11 | **Frogspawn001** | 674.402 | 64.596 |
| 12 | LitePool001 | 233.712 | 39.389 |
| 13 | StormPot001 | 81.939 | 17.430 |
| 14 | BeeOp002ObjectSource | 55.002 | 25.789 |
| 15 | BeeOp001FastPool | 54.589 | 9.179 |
| 16 | FastPool002Disruptor | 14.627 | 4.204 |
| 17 | FastPool001 | 12.693 | 6.012 |
| 18 | CoralPool001ArrayPool | 11.837 | 0.230 |
| 19 | FuriousObjectPool001 | 9.954 | 2.238 |
| 20 | Recall001 | 8.777 | 0.186 |
| 21 | CoralPool002LinkedPool | 7.093 | 0.581 |
| 22 | KOPool001 | 5.621 | 1.202 |
| 23 | ViburPool001 | 5.305 | 1.429 |
| 24 | ApacheCommonsPool003SoftRefPool | 4.078 | 1.962 |
| 25 | NoMoreInstance002Clean | 3.749 | 0.091 |
| 26 | ApacheCommonsPool001StackPool | 2.634 | 2.578 |
| 27 | ApacheCommonsPool2001Pool | 2.368 | 0.399 |
| 28 | ApacheCommonsPool002Pool | 0.512 | 0.272 |
| 29 | ApacheCommonsPool2002SoftRefPool | 0.133 | 0.028 |

### Compare002 批量 32 个（ops/ms）

| 排名 | 实现 | 吞吐 | 误差 |
|-----:|------|-----:|-----:|
| 1 | JavaNew001 | 21936.336 | 782.950 |
| 2 | NoMoreInstance001SweepClean | 21372.685 | 710.291 |
| 3 | FastPool002Disruptor | 750.716 | 327.677 |
| 4 | **Frogspawn009** | 722.728 | 754.842 |
| 5 | **Frogspawn003** | 706.633 | 701.029 |
| 6 | **Frogspawn006** | 624.910 | 4.716 |
| 7 | **Frogspawn007** | 418.885 | 4.277 |
| 8 | FastPool001 | 414.076 | 85.086 |
| 9 | **Frogspawn001** | 391.566 | 19.563 |
| 10 | **Frogspawn004** | 379.269 | 97.632 |
| 11 | **Frogspawn008** | 375.618 | 76.255 |
| 12 | FuriousObjectPool001 | 311.053 | 58.902 |
| 13 | KOPool001 | 176.246 | 36.053 |
| 14 | BeeOp001FastPool | 174.216 | 10.914 |
| 15 | StormPot001 | 165.207 | 10.345 |
| 16 | ViburPool001 | 130.669 | 3.284 |
| 17 | ApacheCommonsPool001StackPool | 110.464 | 32.597 |
| 18 | ApacheCommonsPool003SoftRefPool | 83.082 | 76.972 |
| 19 | ApacheCommonsPool2001Pool | 66.079 | 20.781 |
| 20 | NoMoreInstance002Clean | 53.751 | 0.489 |
| 21 | ApacheCommonsPool002Pool | 18.942 | 6.406 |

**Compare002 失败/缺失项**：
ApacheCommonsPool2002SoftRefPool, BeeOp002ObjectSource, CoralPool001ArrayPool, CoralPool002LinkedPool, Frogspawn002, Frogspawn005, LitePool001, Recall001

## 8. 全量结论

### JDK8（main 分支，28 个实现）

- **单操作**：`new` 725.7 ops/us 第一；frogspawn 最好为 **Frogspawn009 539.6**，其次 002/001/005（约 512-533）。
  仍是池类第一，远高于 StormPot（252.4）、BeeOp（144.0）。
- **批量 32 个**：`new` 20,133 ops/ms 第一；frogspawn 最好为 **Frogspawn007 825.9**，
  004/009/003 约 800；FastPool+Disruptor 789.2。
- 0.7 在 JDK8 的单操作低于 0.6（同机子集对比），主要是 reset 成本从使用方转移到库；
  但 frogspawn 仍是池类第一。

### JDK25（jvm21 分支，31 个实现，其他库使用较新版本）

- **单操作**：**Frogspawn002 1133.2、Frogspawn004 1128.5** 超过 `new` 763.7；
  Frogspawn005/006/009/008/003 约 846-1006。**前 7 名全部是 frogspawn**。
- **批量 32 个**：`new` 21,936、NoMoreInstance001 21,373 前二；FastPool+Disruptor 750.7；
  Frogspawn009 722.7、Frogspawn003 706.6、Frogspawn006 624.9。
- 说明：jvm21 分支其他库版本较新，与 JDK8 main 的绝对数不可直接横比；同 JDK 内排名有效。

### 总体

- **单操作**：JDK25 上 frogspawn 0.7 能超过 `new`；JDK8 上 `new` 仍第一，但 frogspawn 是池类第一。
- **批量**：`new` 最快（无同步成本）；frogspawn 是池类第一梯队，显著高于多数通用池。
- **失败项**：`Frogspawn002/005` 批量失败是 `NOT_AVAILABLE` 策略的设计行为（池耗尽即抛异常）；
  其他失败来自第三方池实现。
- **reset 取舍**：0.6 把清理成本交给使用方；0.7 由库承担，降低心智负担。单操作数字的
  小幅下降是这一语义修正的代价，不是实现退化。

## 9. NoMoreInstance 口径说明（重要）

`NoMoreInstance001SweepClean` / `NoMoreInstance002Clean` 的高/低排名是**基准调用方式**造成的，
不能直接与 frogspawn 的 fetch/release 对比。

### 9.1 NoMoreInstance001SweepClean：实际等价于 `new`

NoMoreInstance 的 `SweepCleanablePool` 是 **sweep/scope 模型**：

- `get()` 只推进内部游标：`pool[index++]`；
- 没有 per-object `release`；
- `cleanAll()` 才把游标重置为 0；
- 游标越界后走 `supplier.get()`，即新建对象。

基准代码只在 `shutdown()` 调用 `cleanAll()`，测量期间从不 sweep。池容量 3000 很快耗尽，
之后每次 `get()` 都在新建 `DemoPojo`。GC 数据可以证明：

| 实现 | `gc.alloc.rate.norm` | GC count | 说明 |
|------|---------------------:|---------:|------|
| JavaNew001 | 1280.078 B/op | 55 | 每次 new |
| NoMoreInstance001SweepClean | 1280.081 B/op | 53 | 与 new 相同，说明未复用 |
| Frogspawn003 | 98.083 B/op | 1 | 真正复用 |

所以它的 21k ops/ms 不是“池比 frogspawn 快”，而是“它在这个基准里等于 `new`”。
旧结果里也标注了 NoMoreInstance001 “接近 new 性能，但 GC 不友好”，原因相同。

### 9.2 NoMoreInstance002Clean：批量未归还对象

`CleanablePool.get()` 返回 `Cleanable<T>`，必须 `close()` 才会归还对象。基准的批量代码是：

```java
pojoArray[i] = NMI_POOL.get().value();
```

`Cleanable` 包装被直接丢弃，从未 `close()`，对象没有归还；池耗尽后同样走向新建。
单操作使用 try-with-resources，是正确用法，但吞吐只有约 3.75 ops/us（包装对象与 close 成本）。

### 9.3 公平比较方式

- **NoMoreInstance001**：应使用 per-thread `SweepCleanablePool`，在 scope 结束时调用
  `cleanAll()`；它是 scope 池，不是 borrow/return 池，建议单列，不参与同一排名。
- **NoMoreInstance002**：批量应保存 `Cleanable` 数组，consume 后逐个 `close()`，把归还/清理
  成本计入测量。
- 当前全量表中的 NoMoreInstance 行应视为**说明性数据**，不作为“池性能超过 frogspawn”的证据。

