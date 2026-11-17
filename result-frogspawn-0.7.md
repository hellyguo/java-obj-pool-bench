# frogspawn 0.6 vs 0.7-dev 同机对比

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

1. **0.7 单操作比 0.6 低约 8%-19%**
   主要原因是 0.7 修复了 `release` 必须调用 `reset()` 的契约缺陷；0.6 在线程本地缓存路径跳过了
   `reset()`。`DemoPojo.reset()` 每次写 4 个字段，在 8 线程单取单还场景下成本可见。
   另外，0.7 的 `ArrayUtil` 走了 `MemoryAccess` SPI，主池元素访问多一层间接。

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
