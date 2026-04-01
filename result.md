# 对比测试结果

- just `new`
- [`BeeOP`](https://github.com/Chris2018998/BeeOP)
- [`commons-pool`](https://commons.apache.org/proper/commons-pool/)
- [`commons-pool2`](https://commons.apache.org/proper/commons-pool/)
- [`CoralPool`](https://github.com/coralblocks/CoralPool/)
- [`ERASOFT Furious Object Pool`](https://code.google.com/archive/p/furious-objectpool/)
- [`Fast Object Pool`](https://github.com/DanielYWoo/fast-object-pool)
- [`frogspawn`](https://itcraft.cn/frogspawn/)
- [`generic-object-pool`](https://github.com/bbottema/generic-object-pool)
- [`key bean pool`](https://github.com/gondor/kbop/)
- [`lite-pool`](https://github.com/nextopcn/lite-pool)
- [`Stormpot Blaze/Queue Pool`](http://chrisvest.github.io/stormpot/)
- [`vibur-object-pool`](https://github.com/vibur/vibur-object-pool)

> 运行于 Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

> `JVM` Oracle JDK 1.8.0_371

> `JVM` 开启参数 `-XX:-RestrictContended`

> 测试版本: frogspawn 0.6

```verilog
Benchmark                                           (desc)   Mode  Cnt     Score     Error   Units
Compare001Benchmark.test     ApacheCommonsPool001StackPool  thrpt    5     3.768 ±   1.665  ops/us
Compare001Benchmark.test          ApacheCommonsPool002Pool  thrpt    5     0.575 ±   0.614  ops/us
Compare001Benchmark.test   ApacheCommonsPool003SoftRefPool  thrpt    5     4.553 ±   5.945  ops/us
Compare001Benchmark.test         ApacheCommonsPool2001Pool  thrpt    5     2.276 ±   0.537  ops/us
Compare001Benchmark.test  ApacheCommonsPool2002SoftRefPool  thrpt    5     0.131 ±   0.105  ops/us
Compare001Benchmark.test                  BeeOp001FastPool  thrpt    5    37.843 ±  49.588  ops/us
Compare001Benchmark.test              BeeOp002ObjectSource  thrpt    5    33.148 ±  19.778  ops/us
Compare001Benchmark.test             CoralPool001ArrayPool  thrpt    5    13.748 ±   2.667  ops/us
Compare001Benchmark.test            CoralPool002LinkedPool  thrpt    5     5.090 ±   1.081  ops/us
Compare001Benchmark.test                       FastPool001  thrpt    5    13.397 ±   0.932  ops/us
Compare001Benchmark.test              FastPool002Disruptor  thrpt    5    17.896 ±   4.955  ops/us
Compare001Benchmark.test                      Frogspawn001  thrpt    5   152.079 ±  66.113  ops/us
Compare001Benchmark.test                      Frogspawn002  thrpt    5   310.426 ±  97.565  ops/us
Compare001Benchmark.test                      Frogspawn003  thrpt    5  1029.938 ± 164.359  ops/us
Compare001Benchmark.test                      Frogspawn004  thrpt    5   402.626 ± 156.408  ops/us
Compare001Benchmark.test                      Frogspawn005  thrpt    5   370.714 ± 372.613  ops/us
Compare001Benchmark.test                      Frogspawn006  thrpt    5   298.895 ± 441.894  ops/us
Compare001Benchmark.test                      Frogspawn007  thrpt    5   466.868 ± 275.812  ops/us
Compare001Benchmark.test                      Frogspawn008  thrpt    5   295.966 ± 272.881  ops/us
Compare001Benchmark.test                      Frogspawn009  thrpt    5   457.340 ± 645.845  ops/us
Compare001Benchmark.test              FuriousObjectPool001  thrpt    5     9.177 ±   7.264  ops/us
Compare001Benchmark.test              GenericObjectPool001  thrpt    5     7.340 ±   2.801  ops/us
Compare001Benchmark.test                        JavaNew001  thrpt    5   662.220 ± 211.229  ops/us
Compare001Benchmark.test                         KOPool001  thrpt    5     4.985 ±   2.996  ops/us
Compare001Benchmark.test                       LitePool001  thrpt    5     2.305 ±   0.419  ops/us
Compare001Benchmark.test              StormPot001BlazePool  thrpt    5   292.231 ±  37.807  ops/us
Compare001Benchmark.test              StormPot002QueuePool  thrpt    5     4.174 ±   0.628  ops/us
Compare001Benchmark.test                      ViburPool001  thrpt    5     4.554 ±   1.316  ops/us
```

```verilog
Benchmark                                               (desc)   Mode  Cnt      Score      Error   Units
Compare002Benchmark.testBatch    ApacheCommonsPool001StackPool  thrpt    5    139.504 ±   99.225  ops/ms
Compare002Benchmark.testBatch         ApacheCommonsPool002Pool  thrpt    5     19.846 ±   15.258  ops/ms
Compare002Benchmark.testBatch  ApacheCommonsPool003SoftRefPool  thrpt    5    152.700 ±  166.225  ops/ms
Compare002Benchmark.testBatch        ApacheCommonsPool2001Pool  thrpt    5     76.468 ±   30.473  ops/ms
Compare002Benchmark.testBatch                 BeeOp001FastPool  thrpt    5    175.617 ±  100.578  ops/ms
Compare002Benchmark.testBatch            CoralPool001ArrayPool  thrpt    5    227.340 ±  472.552  ops/ms
Compare002Benchmark.testBatch                      FastPool001  thrpt    5    408.738 ±   94.943  ops/ms
Compare002Benchmark.testBatch             FastPool002Disruptor  thrpt    5    658.670 ±  151.784  ops/ms
Compare002Benchmark.testBatch                     Frogspawn001  thrpt    5    934.223 ±   60.328  ops/ms
Compare002Benchmark.testBatch                     Frogspawn003  thrpt    5    889.036 ±   99.040  ops/ms
Compare002Benchmark.testBatch                     Frogspawn004  thrpt    5    865.809 ±   91.297  ops/ms
Compare002Benchmark.testBatch                     Frogspawn006  thrpt    5    892.890 ±   40.006  ops/ms
Compare002Benchmark.testBatch                     Frogspawn007  thrpt    5    883.431 ±   62.435  ops/ms
Compare002Benchmark.testBatch                     Frogspawn008  thrpt    5    901.441 ±   88.390  ops/ms
Compare002Benchmark.testBatch                     Frogspawn009  thrpt    5    858.134 ±   66.698  ops/ms
Compare002Benchmark.testBatch             FuriousObjectPool001  thrpt    5    310.546 ±  230.466  ops/ms
Compare002Benchmark.testBatch             GenericObjectPool001  thrpt    5    267.957 ±  129.352  ops/ms
Compare002Benchmark.testBatch                       JavaNew001  thrpt    5  18122.459 ± 2920.725  ops/ms
Compare002Benchmark.testBatch                        KOPool001  thrpt    5    222.126 ±  146.944  ops/ms
Compare002Benchmark.testBatch             StormPot001BlazePool  thrpt    5    134.825 ±   15.467  ops/ms
Compare002Benchmark.testBatch             StormPot002QueuePool  thrpt    5    133.564 ±   17.498  ops/ms
Compare002Benchmark.testBatch                     ViburPool001  thrpt    5    125.134 ±    8.533  ops/ms
```

## 测试失败说明

以下实现在批量测试中失败：

| 实现 | 失败原因 |
|------|----------|
| ApacheCommonsPool2002SoftRefPool | NullPointerException: makeObject() 返回 null |
| BeeOp002ObjectSource | ClassCastException: 代理类转换失败 |
| Frogspawn002 | RuntimeException: NOT_AVAILABLE 策略在池耗尽时抛异常 |
| Frogspawn005 | RuntimeException: NOT_AVAILABLE 策略在池耗尽时抛异常 |