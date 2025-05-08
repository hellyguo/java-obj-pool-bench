# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 21**

> this is the result for JVM9+. If need the result for JVM8, please switch to branch `jvm8`.

## 对比测试结果

- just `new`
- [`BeeOP`](https://github.com/Chris2018998/BeeOP)
- [`commons-pool`](https://commons.apache.org/proper/commons-pool/)
- [`commons-pool2`](https://commons.apache.org/proper/commons-pool/)
- [`CoralPool`](https://github.com/coralblocks/CoralPool/)
- [`ERASOFT Furious Object Pool`](https://code.google.com/archive/p/furious-objectpool/)
- [`Fast Object Pool`](https://github.com/DanielYWoo/fast-object-pool)
- [`frogspawn`](https://itcraft.cn/frogspawn/)
- [`key bean pool`](https://github.com/gondor/kbop/)
- [`lite-pool`](https://github.com/nextopcn/lite-pool)
- [`NoMoreInstance`](https://github.com/YvanMazy/NoMoreInstance)
- [`Stormpot Blaze/Queue Pool`](http://chrisvest.github.io/stormpot/)
- [`vibur-object-pool`](https://github.com/vibur/vibur-object-pool)

> 运行于 Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

> `JVM` 开启参数 `-XX:-RestrictContended`

```verilog
Benchmark                                                              (desc)   Mode  Cnt     Score       Error   Units
Compare001Benchmark.test                        ApacheCommonsPool001StackPool  thrpt    5     2.563 ±     0.498  ops/us
Compare001Benchmark.test:gc.alloc.rate          ApacheCommonsPool001StackPool  thrpt    5    32.055 ±    46.555  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm     ApacheCommonsPool001StackPool  thrpt    5    22.223 ±    54.182    B/op
Compare001Benchmark.test:gc.count               ApacheCommonsPool001StackPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                             ApacheCommonsPool002Pool  thrpt    5     1.148 ±     0.290  ops/us
Compare001Benchmark.test:gc.alloc.rate               ApacheCommonsPool002Pool  thrpt    5   100.519 ±   179.905  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm          ApacheCommonsPool002Pool  thrpt    5   125.803 ±   120.576    B/op
Compare001Benchmark.test:gc.count                    ApacheCommonsPool002Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                     ApacheCommonsPool002Pool  thrpt    5    10.000                  ms
Compare001Benchmark.test                      ApacheCommonsPool003SoftRefPool  thrpt    5     3.058 ±     0.787  ops/us
Compare001Benchmark.test:gc.alloc.rate        ApacheCommonsPool003SoftRefPool  thrpt    5   133.342 ±   244.379  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm   ApacheCommonsPool003SoftRefPool  thrpt    5    61.320 ±    46.310    B/op
Compare001Benchmark.test:gc.count             ApacheCommonsPool003SoftRefPool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time              ApacheCommonsPool003SoftRefPool  thrpt    5     9.000                  ms
Compare001Benchmark.test                            ApacheCommonsPool2001Pool  thrpt    5     1.603 ±     0.252  ops/us
Compare001Benchmark.test:gc.alloc.rate              ApacheCommonsPool2001Pool  thrpt    5   293.358 ±   549.154  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm         ApacheCommonsPool2001Pool  thrpt    5   245.704 ±    86.307    B/op
Compare001Benchmark.test:gc.count                   ApacheCommonsPool2001Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                    ApacheCommonsPool2001Pool  thrpt    5     6.000                  ms
Compare001Benchmark.test                     ApacheCommonsPool2002SoftRefPool  thrpt    5     0.043 ±     0.064  ops/us
Compare001Benchmark.test:gc.alloc.rate       ApacheCommonsPool2002SoftRefPool  thrpt    5    47.795 ±    74.274  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm  ApacheCommonsPool2002SoftRefPool  thrpt    5  2018.639 ±  2826.547    B/op
Compare001Benchmark.test:gc.count            ApacheCommonsPool2002SoftRefPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                     BeeOp001FastPool  thrpt    5    42.070 ±    10.644  ops/us
Compare001Benchmark.test:gc.alloc.rate                       BeeOp001FastPool  thrpt    5   916.989 ±  1753.775  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                  BeeOp001FastPool  thrpt    5    32.591 ±     2.871    B/op
Compare001Benchmark.test:gc.count                            BeeOp001FastPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                 BeeOp002ObjectSource  thrpt    5    17.914 ±     7.125  ops/us
Compare001Benchmark.test:gc.alloc.rate                   BeeOp002ObjectSource  thrpt    5  1560.975 ±  2963.409  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm              BeeOp002ObjectSource  thrpt    5   120.747 ±     6.571    B/op
Compare001Benchmark.test:gc.count                        BeeOp002ObjectSource  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                         BeeOp002ObjectSource  thrpt    5     8.000                  ms
Compare001Benchmark.test                                CoralPool001ArrayPool  thrpt    5     1.291 ±     0.366  ops/us
Compare001Benchmark.test:gc.alloc.rate                  CoralPool001ArrayPool  thrpt    5   721.259 ±  1379.808  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm             CoralPool001ArrayPool  thrpt    5   756.882 ±   110.228    B/op
Compare001Benchmark.test:gc.count                       CoralPool001ArrayPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                               CoralPool002LinkedPool  thrpt    5    14.235 ±     3.544  ops/us
Compare001Benchmark.test:gc.alloc.rate                 CoralPool002LinkedPool  thrpt    5   187.608 ±   342.222  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm            CoralPool002LinkedPool  thrpt    5    17.656 ±     8.703    B/op
Compare001Benchmark.test:gc.count                      CoralPool002LinkedPool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                       CoralPool002LinkedPool  thrpt    5     6.000                  ms
Compare001Benchmark.test                                      EraaSoftPool001  thrpt    5     7.609 ±     1.206  ops/us
Compare001Benchmark.test:gc.alloc.rate                        EraaSoftPool001  thrpt    5   240.362 ±   446.492  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                   EraaSoftPool001  thrpt    5    42.911 ±    18.131    B/op
Compare001Benchmark.test:gc.count                             EraaSoftPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                              EraaSoftPool001  thrpt    5     6.000                  ms
Compare001Benchmark.test                                          FastPool001  thrpt    5    22.414 ±    16.023  ops/us
Compare001Benchmark.test:gc.alloc.rate                            FastPool001  thrpt    5   309.016 ±   609.924  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       FastPool001  thrpt    5    18.469 ±     7.580    B/op
Compare001Benchmark.test:gc.count                                 FastPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                  FastPool001  thrpt    5     6.000                  ms
Compare001Benchmark.test                                         Frogspawn001  thrpt    5   130.246 ±    36.827  ops/us
Compare001Benchmark.test:gc.alloc.rate                           Frogspawn001  thrpt    5     2.419 ±    12.507  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                      Frogspawn001  thrpt    5     0.124 ±     0.959    B/op
Compare001Benchmark.test:gc.count                                Frogspawn001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                           JavaNew001  thrpt    5   264.256 ±   103.886  ops/us
Compare001Benchmark.test:gc.alloc.rate                             JavaNew001  thrpt    5  7615.927 ± 15548.623  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                        JavaNew001  thrpt    5    40.088 ±     0.489    B/op
Compare001Benchmark.test:gc.count                                  JavaNew001  thrpt    5     3.000              counts
Compare001Benchmark.test:gc.time                                   JavaNew001  thrpt    5    21.000                  ms
Compare001Benchmark.test                                            KOPool001  thrpt    5     2.129 ±     0.220  ops/us
Compare001Benchmark.test:gc.alloc.rate                              KOPool001  thrpt    5   253.754 ±   471.536  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                         KOPool001  thrpt    5   161.174 ±    65.710    B/op
Compare001Benchmark.test:gc.count                                   KOPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                    KOPool001  thrpt    5     5.000                  ms
Compare001Benchmark.test                                          LitePool001  thrpt    5   119.573 ±    24.189  ops/us
Compare001Benchmark.test:gc.alloc.rate                            LitePool001  thrpt    5  1462.619 ±  2815.489  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       LitePool001  thrpt    5    16.138 ±     1.177    B/op
Compare001Benchmark.test:gc.count                                 LitePool001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                          NoMoreInstance001SweepClean  thrpt    5   221.326 ±   233.184  ops/us
Compare001Benchmark.test:gc.alloc.rate            NoMoreInstance001SweepClean  thrpt    5  5965.105 ± 12511.500  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm       NoMoreInstance001SweepClean  thrpt    5    40.085 ±     0.411    B/op
Compare001Benchmark.test:gc.count                 NoMoreInstance001SweepClean  thrpt    5     2.000              counts
Compare001Benchmark.test:gc.time                  NoMoreInstance001SweepClean  thrpt    5    20.000                  ms
Compare001Benchmark.test                               NoMoreInstance002Clean  thrpt    5     2.991 ±     0.556  ops/us
Compare001Benchmark.test:gc.alloc.rate                 NoMoreInstance002Clean  thrpt    5     3.967 ±    10.535  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm            NoMoreInstance002Clean  thrpt    5     5.579 ±    38.261    B/op
Compare001Benchmark.test:gc.count                      NoMoreInstance002Clean  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                            Recall001  thrpt    5     1.183 ±     0.325  ops/us
Compare001Benchmark.test:gc.alloc.rate                              Recall001  thrpt    5   669.670 ±  1281.176  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                         Recall001  thrpt    5   743.448 ±   120.993    B/op
Compare001Benchmark.test:gc.count                                   Recall001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                          StormPot001  thrpt    5    61.862 ±    16.056  ops/us
Compare001Benchmark.test:gc.alloc.rate                            StormPot001  thrpt    5     6.415 ±    14.778  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       StormPot001  thrpt    5     0.327 ±     1.811    B/op
Compare001Benchmark.test:gc.count                                 StormPot001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                         ViburPool001  thrpt    5     4.443 ±     0.640  ops/us
Compare001Benchmark.test:gc.alloc.rate                           ViburPool001  thrpt    5   195.858 ±   361.794  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                      ViburPool001  thrpt    5    59.691 ±    31.716    B/op
Compare001Benchmark.test:gc.count                                ViburPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                 ViburPool001  thrpt    5     7.000                  ms
```

```verilog
Benchmark                                                    Mode  Cnt      Score      Error   Units
Compare002Benchmark.test0NewOneRequestMultiTimes            thrpt    5   6227.045 ± 2143.521  ops/ms
Compare002Benchmark.test1BeeOPOneRequestMultiTimes          thrpt    5  11614.665 ± 1636.184  ops/ms
Compare002Benchmark.test2CommonsPoolOneRequestMultiTimes    thrpt    5     58.541 ±    3.104  ops/ms
Compare002Benchmark.test3FrogspawnPoolOneRequestMultiTimes  thrpt    5    873.924 ±   18.034  ops/ms
Compare002Benchmark.test4GOPoolOneRequestMultiTimes         thrpt    5    172.718 ±    8.730  ops/ms
Compare002Benchmark.test5LitePoolOneRequestMultiTimes       thrpt    5    432.140 ±   72.527  ops/ms
Compare002Benchmark.test6ViburPoolOneRequestMultiTimes      thrpt    5    140.157 ±    3.787  ops/ms
```

## 结论

1. 逐个使用，建议使用：`frogspawn`/`StormPot`,
2. 一批使用，建议使用：`BeeOP`
3. `NoMoreInstance`(`SweepClean`)/`LitePool` 相当于 `JavaNew`，所以快，但 `GC` 不友好
