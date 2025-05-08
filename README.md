# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 1.8**

> this is the result for JVM8. If need the result for JVM9+, please switch to branch `jvm21`.

## 对比测试结果

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

> `JVM` 开启参数 `-XX:-RestrictContended`

```verilog
Benchmark                                                                            (desc)   Mode  Cnt     Score       Error   Units
Compare001Benchmark.test                                      ApacheCommonsPool001StackPool  thrpt    5     3.538 ±     0.211  ops/us
Compare001Benchmark.test:·gc.alloc.rate                       ApacheCommonsPool001StackPool  thrpt    5     8.855 ±     1.709  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                  ApacheCommonsPool001StackPool  thrpt    5    20.705 ±    40.755    B/op
Compare001Benchmark.test:·gc.count                            ApacheCommonsPool001StackPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                           ApacheCommonsPool002Pool  thrpt    5     1.178 ±     0.297  ops/us
Compare001Benchmark.test:·gc.alloc.rate                            ApacheCommonsPool002Pool  thrpt    5    16.993 ±    12.743  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                       ApacheCommonsPool002Pool  thrpt    5   111.109 ±   129.514    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                   ApacheCommonsPool002Pool  thrpt    5    25.011 ±   215.356  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm              ApacheCommonsPool002Pool  thrpt    5   382.654 ±  3294.769    B/op
Compare001Benchmark.test:·gc.count                                 ApacheCommonsPool002Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                  ApacheCommonsPool002Pool  thrpt    5     9.000                  ms
Compare001Benchmark.test                                    ApacheCommonsPool003SoftRefPool  thrpt    5     4.079 ±     0.156  ops/us
Compare001Benchmark.test:·gc.alloc.rate                     ApacheCommonsPool003SoftRefPool  thrpt    5    32.691 ±    30.908  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                ApacheCommonsPool003SoftRefPool  thrpt    5    59.981 ±    34.518    B/op
Compare001Benchmark.test:·gc.count                          ApacheCommonsPool003SoftRefPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                          ApacheCommonsPool2001Pool  thrpt    5     2.126 ±     0.425  ops/us
Compare001Benchmark.test:·gc.alloc.rate                           ApacheCommonsPool2001Pool  thrpt    5    70.031 ±    74.259  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                      ApacheCommonsPool2001Pool  thrpt    5   243.055 ±    63.218    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                  ApacheCommonsPool2001Pool  thrpt    5    74.550 ±   641.898  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm             ApacheCommonsPool2001Pool  thrpt    5   240.573 ±  2071.406    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space              ApacheCommonsPool2001Pool  thrpt    5     0.052 ±     0.447  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm         ApacheCommonsPool2001Pool  thrpt    5     0.167 ±     1.442    B/op
Compare001Benchmark.test:·gc.count                                ApacheCommonsPool2001Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                 ApacheCommonsPool2001Pool  thrpt    5    10.000                  ms
Compare001Benchmark.test                                   ApacheCommonsPool2002SoftRefPool  thrpt    5     0.053 ±     0.088  ops/us
Compare001Benchmark.test:·gc.alloc.rate                    ApacheCommonsPool2002SoftRefPool  thrpt    5    17.713 ±    16.367  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm               ApacheCommonsPool2002SoftRefPool  thrpt    5  1950.539 ±  2203.388    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space           ApacheCommonsPool2002SoftRefPool  thrpt    5    60.069 ±   517.210  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm      ApacheCommonsPool2002SoftRefPool  thrpt    5  5726.396 ± 49305.992    B/op
Compare001Benchmark.test:·gc.count                         ApacheCommonsPool2002SoftRefPool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                          ApacheCommonsPool2002SoftRefPool  thrpt    5    10.000                  ms
Compare001Benchmark.test                                                   BeeOp001FastPool  thrpt    5    57.394 ±    13.106  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                    BeeOp001FastPool  thrpt    5   289.142 ±   379.677  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                               BeeOp001FastPool  thrpt    5    32.271 ±     2.321    B/op
Compare001Benchmark.test:·gc.count                                         BeeOp001FastPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                               BeeOp002ObjectSource  thrpt    5    25.360 ±    28.105  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                BeeOp002ObjectSource  thrpt    5   433.062 ±   373.895  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           BeeOp002ObjectSource  thrpt    5   120.409 ±     3.474    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                       BeeOp002ObjectSource  thrpt    5   793.065 ±  6828.527  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                  BeeOp002ObjectSource  thrpt    5   172.479 ±  1485.093    B/op
Compare001Benchmark.test:·gc.count                                     BeeOp002ObjectSource  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                      BeeOp002ObjectSource  thrpt    5     6.000                  ms
Compare001Benchmark.test                                              CoralPool001ArrayPool  thrpt    5    16.182 ±    23.062  ops/us
Compare001Benchmark.test:·gc.alloc.rate                               CoralPool001ArrayPool  thrpt    5   215.262 ±   565.218  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                          CoralPool001ArrayPool  thrpt    5    80.383 ±   193.374    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                      CoralPool001ArrayPool  thrpt    5   120.988 ±   488.253  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                 CoralPool001ArrayPool  thrpt    5    71.873 ±   288.422    B/op
Compare001Benchmark.test:·gc.count                                    CoralPool001ArrayPool  thrpt    5     3.000              counts
Compare001Benchmark.test:·gc.time                                     CoralPool001ArrayPool  thrpt    5   308.000                  ms
Compare001Benchmark.test                                             CoralPool002LinkedPool  thrpt    5    15.249 ±     1.255  ops/us
Compare001Benchmark.test:·gc.alloc.rate                              CoralPool002LinkedPool  thrpt    5    41.438 ±    34.868  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                         CoralPool002LinkedPool  thrpt    5    18.066 ±     6.995    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                     CoralPool002LinkedPool  thrpt    5    62.684 ±   539.732  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                CoralPool002LinkedPool  thrpt    5    23.089 ±   198.808    B/op
Compare001Benchmark.test:·gc.count                                   CoralPool002LinkedPool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                    CoralPool002LinkedPool  thrpt    5     5.000                  ms
Compare001Benchmark.test                                                    EraaSoftPool001  thrpt    5     9.083 ±     0.697  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                     EraaSoftPool001  thrpt    5    52.583 ±    56.511  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                EraaSoftPool001  thrpt    5    42.676 ±    15.702    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                            EraaSoftPool001  thrpt    5    64.563 ±   555.903  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                       EraaSoftPool001  thrpt    5    45.800 ±   394.350    B/op
Compare001Benchmark.test:·gc.count                                          EraaSoftPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                           EraaSoftPool001  thrpt    5     4.000                  ms
Compare001Benchmark.test                                                        FastPool001  thrpt    5    31.621 ±    30.868  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                         FastPool001  thrpt    5    84.523 ±    83.559  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                    FastPool001  thrpt    5    20.104 ±     7.261    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                FastPool001  thrpt    5    73.910 ±   636.391  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                           FastPool001  thrpt    5    18.214 ±   156.825    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                            FastPool001  thrpt    5     0.099 ±     0.854  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                       FastPool001  thrpt    5     0.024 ±     0.211    B/op
Compare001Benchmark.test:·gc.count                                              FastPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                               FastPool001  thrpt    5    10.000                  ms
Compare001Benchmark.test                                                       Frogspawn001  thrpt    5   183.956 ±    19.419  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                        Frogspawn001  thrpt    5     0.964 ±     8.190  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                   Frogspawn001  thrpt    5     0.077 ±     0.661    B/op
Compare001Benchmark.test:·gc.count                                             Frogspawn001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                               GenericObjectPool001  thrpt    5     6.476 ±     0.377  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                GenericObjectPool001  thrpt    5    37.632 ±    37.034  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           GenericObjectPool001  thrpt    5    43.339 ±    21.693    B/op
Compare001Benchmark.test:·gc.count                                     GenericObjectPool001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                                         JavaNew001  thrpt    5   243.052 ±   389.547  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                          JavaNew001  thrpt    5  1410.495 ±  2665.971  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                     JavaNew001  thrpt    5    40.042 ±     0.357    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                 JavaNew001  thrpt    5  1596.665 ±  8419.015  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                            JavaNew001  thrpt    5    35.038 ±   184.901    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                             JavaNew001  thrpt    5     0.060 ±     0.514  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                        JavaNew001  thrpt    5     0.001 ±     0.012    B/op
Compare001Benchmark.test:·gc.count                                               JavaNew001  thrpt    5     2.000              counts
Compare001Benchmark.test:·gc.time                                                JavaNew001  thrpt    5    11.000                  ms
Compare001Benchmark.test                                                          KOPool001  thrpt    5     2.669 ±     0.573  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                           KOPool001  thrpt    5    74.563 ±    83.168  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                      KOPool001  thrpt    5   205.067 ±    50.199    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                  KOPool001  thrpt    5    74.523 ±   641.667  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                             KOPool001  thrpt    5   194.819 ±  1677.446    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                              KOPool001  thrpt    5     0.053 ±     0.455  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                         KOPool001  thrpt    5     0.138 ±     1.188    B/op
Compare001Benchmark.test:·gc.count                                                KOPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                                 KOPool001  thrpt    5     9.000                  ms
Compare001Benchmark.test                                                        LitePool001  thrpt    5   163.187 ±    44.284  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                         LitePool001  thrpt    5   404.078 ±   542.815  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                    LitePool001  thrpt    5    16.100 ±     0.857    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                LitePool001  thrpt    5   300.026 ±  2583.315  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                           LitePool001  thrpt    5    30.867 ±   265.776    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                            LitePool001  thrpt    5     0.027 ±     0.236  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                       LitePool001  thrpt    5     0.003 ±     0.024    B/op
Compare001Benchmark.test:·gc.count                                              LitePool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                               LitePool001  thrpt    5     6.000                  ms
Compare001Benchmark.test                                               StormPot001BlazePool  thrpt    5   121.976 ±     5.407  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                StormPot001BlazePool  thrpt    5     0.975 ±     8.283  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           StormPot001BlazePool  thrpt    5     0.133 ±     1.142    B/op
Compare001Benchmark.test:·gc.count                                     StormPot001BlazePool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                               StormPot002QueuePool  thrpt    5     7.721 ±     0.259  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                StormPot002QueuePool  thrpt    5    38.576 ±    38.832  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           StormPot002QueuePool  thrpt    5    33.954 ±    16.736    B/op
Compare001Benchmark.test:·gc.count                                     StormPot002QueuePool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                                       ViburPool001  thrpt    5     5.361 ±     0.287  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                        ViburPool001  thrpt    5    48.585 ±    51.431  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                   ViburPool001  thrpt    5    58.669 ±    22.775    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                               ViburPool001  thrpt    5    62.836 ±   541.041  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                          ViburPool001  thrpt    5    63.924 ±   550.401    B/op
Compare001Benchmark.test:·gc.count                                             ViburPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                              ViburPool001  thrpt    5     5.000                  ms
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

1. 逐个使用，建议使用：`frogspawn`/`stormpot`(`blaze pool`)
2. 一批使用，建议使用：`BeeOP`
