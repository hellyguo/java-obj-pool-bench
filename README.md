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
Compare001Benchmark.test                                      ApacheCommonsPool001StackPool  thrpt    5     4.354 ±     0.104  ops/us
Compare001Benchmark.test:·gc.alloc.rate                       ApacheCommonsPool001StackPool  thrpt    5    10.647 ±     3.780  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                  ApacheCommonsPool001StackPool  thrpt    5    19.753 ±    32.615    B/op
Compare001Benchmark.test:·gc.count                            ApacheCommonsPool001StackPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                           ApacheCommonsPool002Pool  thrpt    5     1.402 ±     0.179  ops/us
Compare001Benchmark.test:·gc.alloc.rate                            ApacheCommonsPool002Pool  thrpt    5    19.751 ±    15.471  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                       ApacheCommonsPool002Pool  thrpt    5   108.196 ±   101.830    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                   ApacheCommonsPool002Pool  thrpt    5    67.892 ±   584.572  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm              ApacheCommonsPool002Pool  thrpt    5   319.619 ±  2752.012    B/op
Compare001Benchmark.test:·gc.count                                 ApacheCommonsPool002Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                  ApacheCommonsPool002Pool  thrpt    5     9.000                  ms
Compare001Benchmark.test                                    ApacheCommonsPool003SoftRefPool  thrpt    5     4.770 ±     0.975  ops/us
Compare001Benchmark.test:·gc.alloc.rate                     ApacheCommonsPool003SoftRefPool  thrpt    5    37.955 ±    37.888  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                ApacheCommonsPool003SoftRefPool  thrpt    5    59.389 ±    29.266    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space            ApacheCommonsPool003SoftRefPool  thrpt    5    23.858 ±   205.422  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm       ApacheCommonsPool003SoftRefPool  thrpt    5    83.756 ±   721.165    B/op
Compare001Benchmark.test:·gc.count                          ApacheCommonsPool003SoftRefPool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                           ApacheCommonsPool003SoftRefPool  thrpt    5     3.000                  ms
Compare001Benchmark.test                                          ApacheCommonsPool2001Pool  thrpt    5     2.592 ±     0.375  ops/us
Compare001Benchmark.test:·gc.alloc.rate                           ApacheCommonsPool2001Pool  thrpt    5    80.059 ±    89.837  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                      ApacheCommonsPool2001Pool  thrpt    5   225.978 ±    53.614    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                  ApacheCommonsPool2001Pool  thrpt    5    74.490 ±   641.378  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm             ApacheCommonsPool2001Pool  thrpt    5   192.702 ±  1659.225    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space              ApacheCommonsPool2001Pool  thrpt    5     0.050 ±     0.428  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm         ApacheCommonsPool2001Pool  thrpt    5     0.129 ±     1.107    B/op
Compare001Benchmark.test:·gc.count                                ApacheCommonsPool2001Pool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                 ApacheCommonsPool2001Pool  thrpt    5     8.000                  ms
Compare001Benchmark.test                                   ApacheCommonsPool2002SoftRefPool  thrpt    5     0.103 ±     0.211  ops/us
Compare001Benchmark.test:·gc.alloc.rate                    ApacheCommonsPool2002SoftRefPool  thrpt    5    26.622 ±    23.922  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm               ApacheCommonsPool2002SoftRefPool  thrpt    5  1809.007 ±  1008.879    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space           ApacheCommonsPool2002SoftRefPool  thrpt    5    56.988 ±   490.682  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm      ApacheCommonsPool2002SoftRefPool  thrpt    5  3234.926 ± 27853.691    B/op
Compare001Benchmark.test:·gc.count                         ApacheCommonsPool2002SoftRefPool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                          ApacheCommonsPool2002SoftRefPool  thrpt    5     9.000                  ms
Compare001Benchmark.test                                                   BeeOp001FastPool  thrpt    5    61.041 ±    26.823  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                    BeeOp001FastPool  thrpt    5   305.830 ±   406.721  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                               BeeOp001FastPool  thrpt    5    32.289 ±     2.114    B/op
Compare001Benchmark.test:·gc.count                                         BeeOp001FastPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                               BeeOp002ObjectSource  thrpt    5     9.910 ±     4.970  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                BeeOp002ObjectSource  thrpt    5   212.871 ±   265.273  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           BeeOp002ObjectSource  thrpt    5   121.036 ±     8.864    B/op
Compare001Benchmark.test:·gc.count                                     BeeOp002ObjectSource  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                              CoralPool001ArrayPool  thrpt    5     9.921 ±    28.380  ops/us
Compare001Benchmark.test:·gc.alloc.rate                               CoralPool001ArrayPool  thrpt    5   187.228 ±   378.425  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                          CoralPool001ArrayPool  thrpt    5   117.259 ±   384.092    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                      CoralPool001ArrayPool  thrpt    5    92.634 ±   488.503  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                 CoralPool001ArrayPool  thrpt    5    76.252 ±   430.974    B/op
Compare001Benchmark.test:·gc.count                                    CoralPool001ArrayPool  thrpt    5     2.000              counts
Compare001Benchmark.test:·gc.time                                     CoralPool001ArrayPool  thrpt    5   385.000                  ms
Compare001Benchmark.test                                             CoralPool002LinkedPool  thrpt    5    12.694 ±     2.281  ops/us
Compare001Benchmark.test:·gc.alloc.rate                              CoralPool002LinkedPool  thrpt    5    37.006 ±    33.750  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                         CoralPool002LinkedPool  thrpt    5    19.644 ±     9.366    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                     CoralPool002LinkedPool  thrpt    5    23.422 ±   201.674  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                CoralPool002LinkedPool  thrpt    5    23.724 ±   204.269    B/op
Compare001Benchmark.test:·gc.count                                   CoralPool002LinkedPool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                    CoralPool002LinkedPool  thrpt    5     5.000                  ms
Compare001Benchmark.test                                                    EraaSoftPool001  thrpt    5     7.359 ±     0.972  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                     EraaSoftPool001  thrpt    5    42.997 ±    45.300  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                EraaSoftPool001  thrpt    5    43.012 ±    19.808    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                            EraaSoftPool001  thrpt    5    64.077 ±   551.723  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                       EraaSoftPool001  thrpt    5    55.463 ±   477.555    B/op
Compare001Benchmark.test:·gc.count                                          EraaSoftPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                           EraaSoftPool001  thrpt    5     7.000                  ms
Compare001Benchmark.test                                                        FastPool001  thrpt    5    29.140 ±    25.640  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                         FastPool001  thrpt    5    76.482 ±   100.216  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                    FastPool001  thrpt    5    17.736 ±     7.327    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                FastPool001  thrpt    5    72.187 ±   621.549  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                           FastPool001  thrpt    5    18.691 ±   160.933    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                            FastPool001  thrpt    5     0.050 ±     0.431  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                       FastPool001  thrpt    5     0.013 ±     0.112    B/op
Compare001Benchmark.test:·gc.count                                              FastPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                               FastPool001  thrpt    5     8.000                  ms
Compare001Benchmark.test                                               FastPool002Disruptor  thrpt    5    23.373 ±    28.632  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                FastPool002Disruptor  thrpt    5    52.428 ±    53.141  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           FastPool002Disruptor  thrpt    5    16.457 ±     3.883    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                       FastPool002Disruptor  thrpt    5    63.143 ±   543.679  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                  FastPool002Disruptor  thrpt    5    13.766 ±   118.530    B/op
Compare001Benchmark.test:·gc.count                                     FastPool002Disruptor  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                      FastPool002Disruptor  thrpt    5     5.000                  ms
Compare001Benchmark.test                                                       Frogspawn001  thrpt    5   165.067 ±    86.000  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                        Frogspawn001  thrpt    5   365.260 ±   363.481  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                   Frogspawn001  thrpt    5    16.078 ±     0.667    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                               Frogspawn001  thrpt    5   812.454 ±  6995.475  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                          Frogspawn001  thrpt    5    29.808 ±   256.652    B/op
Compare001Benchmark.test:·gc.count                                             Frogspawn001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                              Frogspawn001  thrpt    5     5.000                  ms
Compare001Benchmark.test                                               GenericObjectPool001  thrpt    5     7.610 ±     0.447  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                GenericObjectPool001  thrpt    5    43.962 ±    44.369  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           GenericObjectPool001  thrpt    5    42.911 ±    18.288    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                       GenericObjectPool001  thrpt    5    64.548 ±   555.775  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                  GenericObjectPool001  thrpt    5    54.627 ±   470.352    B/op
Compare001Benchmark.test:·gc.count                                     GenericObjectPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                      GenericObjectPool001  thrpt    5     4.000                  ms
Compare001Benchmark.test                                                         JavaNew001  thrpt    5   269.644 ±   128.585  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                          JavaNew001  thrpt    5  1540.130 ±  2219.494  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                     JavaNew001  thrpt    5    40.074 ±     0.541    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                 JavaNew001  thrpt    5  1604.395 ±  8459.576  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                            JavaNew001  thrpt    5    32.038 ±   169.112    B/op
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space                             JavaNew001  thrpt    5     0.066 ±     0.410  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Survivor_Space.norm                        JavaNew001  thrpt    5     0.001 ±     0.008    B/op
Compare001Benchmark.test:·gc.count                                               JavaNew001  thrpt    5     2.000              counts
Compare001Benchmark.test:·gc.time                                                JavaNew001  thrpt    5     9.000                  ms
Compare001Benchmark.test                                                          KOPool001  thrpt    5     3.415 ±     0.765  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                           KOPool001  thrpt    5    73.790 ±    79.541  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                      KOPool001  thrpt    5   159.223 ±    36.319    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                  KOPool001  thrpt    5    74.519 ±   641.633  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                             KOPool001  thrpt    5   145.613 ±  1253.776    B/op
Compare001Benchmark.test:·gc.count                                                KOPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                                 KOPool001  thrpt    5     7.000                  ms
Compare001Benchmark.test                                                        LitePool001  thrpt    5   203.562 ±    71.626  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                         LitePool001  thrpt    5   465.113 ±   552.169  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                    LitePool001  thrpt    5    16.073 ±     0.621    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                                LitePool001  thrpt    5   813.270 ±  7002.498  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                           LitePool001  thrpt    5    26.410 ±   227.401    B/op
Compare001Benchmark.test:·gc.count                                              LitePool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                               LitePool001  thrpt    5     6.000                  ms
Compare001Benchmark.test                                               StormPot001BlazePool  thrpt    5   158.748 ±    32.258  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                StormPot001BlazePool  thrpt    5     1.300 ±     7.963  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           StormPot001BlazePool  thrpt    5     0.114 ±     0.837    B/op
Compare001Benchmark.test:·gc.count                                     StormPot001BlazePool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                               StormPot002QueuePool  thrpt    5     9.413 ±     0.672  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                StormPot002QueuePool  thrpt    5    43.479 ±    43.615  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                           StormPot002QueuePool  thrpt    5    33.676 ±    14.292    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                       StormPot002QueuePool  thrpt    5    64.515 ±   555.494  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                  StormPot002QueuePool  thrpt    5    44.180 ±   380.403    B/op
Compare001Benchmark.test:·gc.count                                     StormPot002QueuePool  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                      StormPot002QueuePool  thrpt    5     4.000                  ms
Compare001Benchmark.test                                                       ViburPool001  thrpt    5     6.325 ±     0.372  ops/us
Compare001Benchmark.test:·gc.alloc.rate                                        ViburPool001  thrpt    5    52.048 ±    52.477  MB/sec
Compare001Benchmark.test:·gc.alloc.rate.norm                                   ViburPool001  thrpt    5    58.341 ±    19.965    B/op
Compare001Benchmark.test:·gc.churn.G1_Eden_Space                               ViburPool001  thrpt    5    63.432 ±   546.168  MB/sec
Compare001Benchmark.test:·gc.churn.G1_Eden_Space.norm                          ViburPool001  thrpt    5    58.010 ±   499.480    B/op
Compare001Benchmark.test:·gc.count                                             ViburPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:·gc.time                                              ViburPool001  thrpt    5     4.000                  ms
```

```verilog
Benchmark                                                                                (desc)   Mode  Cnt      Score       Error   Units
Compare002Benchmark.testBatch                                     ApacheCommonsPool001StackPool  thrpt    5    132.639 ±     2.747  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                      ApacheCommonsPool001StackPool  thrpt    5      3.042 ±     5.725  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                 ApacheCommonsPool001StackPool  thrpt    5    235.825 ±  1060.664    B/op
Compare002Benchmark.testBatch:·gc.count                           ApacheCommonsPool001StackPool  thrpt    5        ≈ 0              counts
Compare002Benchmark.testBatch                                          ApacheCommonsPool002Pool  thrpt    5     54.539 ±     8.064  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                           ApacheCommonsPool002Pool  thrpt    5     26.573 ±    23.409  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                      ApacheCommonsPool002Pool  thrpt    5   3674.167 ±  2541.428    B/op
Compare002Benchmark.testBatch:·gc.count                                ApacheCommonsPool002Pool  thrpt    5        ≈ 0              counts
Compare002Benchmark.testBatch                                   ApacheCommonsPool003SoftRefPool  thrpt    5    159.330 ±     2.850  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                    ApacheCommonsPool003SoftRefPool  thrpt    5     31.898 ±    30.189  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm               ApacheCommonsPool003SoftRefPool  thrpt    5   1495.392 ±   885.715    B/op
Compare002Benchmark.testBatch:·gc.count                         ApacheCommonsPool003SoftRefPool  thrpt    5        ≈ 0              counts
Compare002Benchmark.testBatch                                         ApacheCommonsPool2001Pool  thrpt    5     70.548 ±     3.001  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                          ApacheCommonsPool2001Pool  thrpt    5     70.992 ±    77.940  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                     ApacheCommonsPool2001Pool  thrpt    5   7387.319 ±  1982.946    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                 ApacheCommonsPool2001Pool  thrpt    5     79.549 ±   684.943  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm            ApacheCommonsPool2001Pool  thrpt    5   7177.708 ± 61802.228    B/op
Compare002Benchmark.testBatch:·gc.count                               ApacheCommonsPool2001Pool  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                ApacheCommonsPool2001Pool  thrpt    5      1.000                  ms
Compare002Benchmark.testBatch                                                  BeeOp001FastPool  thrpt    5    202.719 ±    27.181  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                   BeeOp001FastPool  thrpt    5     33.806 ±    33.738  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                              BeeOp001FastPool  thrpt    5   1214.741 ±   678.715    B/op
Compare002Benchmark.testBatch:·gc.count                                        BeeOp001FastPool  thrpt    5        ≈ 0              counts
Compare002Benchmark.testBatch                                                       FastPool001  thrpt    5    462.527 ±   435.981  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                        FastPool001  thrpt    5     39.905 ±    34.474  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                                   FastPool001  thrpt    5    655.912 ±   185.277    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                               FastPool001  thrpt    5     28.862 ±   248.514  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                          FastPool001  thrpt    5    734.100 ±  6320.823    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space                           FastPool001  thrpt    5      0.023 ±     0.201  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space.norm                      FastPool001  thrpt    5      0.594 ±     5.117    B/op
Compare002Benchmark.testBatch:·gc.count                                             FastPool001  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                              FastPool001  thrpt    5      2.000                  ms
Compare002Benchmark.testBatch                                              FastPool002Disruptor  thrpt    5   1195.241 ±  2121.242  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                               FastPool002Disruptor  thrpt    5     20.526 ±    43.911  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                          FastPool002Disruptor  thrpt    5    128.763 ±   143.684    B/op
Compare002Benchmark.testBatch:·gc.count                                    FastPool002Disruptor  thrpt    5        ≈ 0              counts
Compare002Benchmark.testBatch                                                      Frogspawn001  thrpt    5   1363.755 ±   211.244  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                       Frogspawn001  thrpt    5     22.605 ±    18.574  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                                  Frogspawn001  thrpt    5    123.145 ±    98.911    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                              Frogspawn001  thrpt    5     78.537 ±   676.230  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                         Frogspawn001  thrpt    5    354.023 ±  3048.246    B/op
Compare002Benchmark.testBatch:·gc.count                                            Frogspawn001  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                             Frogspawn001  thrpt    5      1.000                  ms
Compare002Benchmark.testBatch                                              FuriousObjectPool001  thrpt    5    349.890 ±    36.055  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                               FuriousObjectPool001  thrpt    5     45.479 ±    46.476  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                          FuriousObjectPool001  thrpt    5    963.660 ±   392.955    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                      FuriousObjectPool001  thrpt    5     78.558 ±   676.411  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                 FuriousObjectPool001  thrpt    5   1440.838 ± 12406.050    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space                  FuriousObjectPool001  thrpt    5      0.001 ±     0.011  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space.norm             FuriousObjectPool001  thrpt    5      0.023 ±     0.201    B/op
Compare002Benchmark.testBatch:·gc.count                                    FuriousObjectPool001  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                     FuriousObjectPool001  thrpt    5      2.000                  ms
Compare002Benchmark.testBatch                                              GenericObjectPool001  thrpt    5    243.359 ±     7.757  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                               GenericObjectPool001  thrpt    5     31.664 ±    29.923  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                          GenericObjectPool001  thrpt    5    975.633 ±   580.787    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                      GenericObjectPool001  thrpt    5     78.540 ±   676.255  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                 GenericObjectPool001  thrpt    5   2059.196 ± 17730.303    B/op
Compare002Benchmark.testBatch:·gc.count                                    GenericObjectPool001  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                     GenericObjectPool001  thrpt    5      2.000                  ms
Compare002Benchmark.testBatch                                                        JavaNew001  thrpt    5  10172.461 ±   753.659  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                         JavaNew001  thrpt    5   1909.196 ±  2412.695  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                                    JavaNew001  thrpt    5   1281.682 ±    13.386    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                                JavaNew001  thrpt    5   1948.163 ±  2685.604  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                           JavaNew001  thrpt    5   1291.121 ±   381.785    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space                            JavaNew001  thrpt    5      0.008 ±     0.020  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space.norm                       JavaNew001  thrpt    5      0.006 ±     0.017    B/op
Compare002Benchmark.testBatch:·gc.count                                              JavaNew001  thrpt    5     28.000              counts
Compare002Benchmark.testBatch:·gc.time                                               JavaNew001  thrpt    5     14.000                  ms
Compare002Benchmark.testBatch                                                         KOPool001  thrpt    5    107.272 ±     3.643  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                          KOPool001  thrpt    5     63.537 ±    70.243  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                                     KOPool001  thrpt    5   4341.635 ±  1340.952    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                                 KOPool001  thrpt    5     29.391 ±   253.068  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                            KOPool001  thrpt    5   4720.468 ± 40644.656    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space                             KOPool001  thrpt    5      0.007 ±     0.058  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Survivor_Space.norm                        KOPool001  thrpt    5      1.085 ±     9.339    B/op
Compare002Benchmark.testBatch:·gc.count                                               KOPool001  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                                KOPool001  thrpt    5      1.000                  ms
Compare002Benchmark.testBatch                                              StormPot001BlazePool  thrpt    5    262.686 ±    47.635  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                               StormPot001BlazePool  thrpt    5     42.501 ±    43.526  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                          StormPot001BlazePool  thrpt    5   1161.827 ±   497.557    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                      StormPot001BlazePool  thrpt    5     78.177 ±   673.129  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                 StormPot001BlazePool  thrpt    5   2025.609 ± 17441.106    B/op
Compare002Benchmark.testBatch:·gc.count                                    StormPot001BlazePool  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                     StormPot001BlazePool  thrpt    5      2.000                  ms
Compare002Benchmark.testBatch                                              StormPot002QueuePool  thrpt    5    274.592 ±    48.355  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                               StormPot002QueuePool  thrpt    5     45.976 ±    47.518  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                          StormPot002QueuePool  thrpt    5   1192.633 ±   491.001    B/op
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space                      StormPot002QueuePool  thrpt    5     77.656 ±   668.646  MB/sec
Compare002Benchmark.testBatch:·gc.churn.G1_Eden_Space.norm                 StormPot002QueuePool  thrpt    5   1766.545 ± 15210.485    B/op
Compare002Benchmark.testBatch:·gc.count                                    StormPot002QueuePool  thrpt    5      1.000              counts
Compare002Benchmark.testBatch:·gc.time                                     StormPot002QueuePool  thrpt    5      2.000                  ms
Compare002Benchmark.testBatch                                                      ViburPool001  thrpt    5    185.873 ±     5.507  ops/ms
Compare002Benchmark.testBatch:·gc.alloc.rate                                       ViburPool001  thrpt    5     37.247 ±    36.969  MB/sec
Compare002Benchmark.testBatch:·gc.alloc.rate.norm                                  ViburPool001  thrpt    5   1479.993 ±   755.777    B/op
Compare002Benchmark.testBatch:·gc.count                                            ViburPool001  thrpt    5        ≈ 0              counts
```

## 结论

1. 逐个使用，建议使用：`frogspawn`/`stormpot`(`blaze pool`)
2. 一批使用，建议使用：`frogspawn`/`FastPool`+`Disruptor`
