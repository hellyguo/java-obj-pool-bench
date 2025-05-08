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
Compare001Benchmark.test                        ApacheCommonsPool001StackPool  thrpt    5     3.185 ±     0.077  ops/us
Compare001Benchmark.test:gc.alloc.rate          ApacheCommonsPool001StackPool  thrpt    5    40.906 ±    63.866  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm     ApacheCommonsPool001StackPool  thrpt    5    21.180 ±    44.703    B/op
Compare001Benchmark.test:gc.count               ApacheCommonsPool001StackPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                             ApacheCommonsPool002Pool  thrpt    5     0.906 ±     0.497  ops/us
Compare001Benchmark.test:gc.alloc.rate               ApacheCommonsPool002Pool  thrpt    5    65.849 ±   110.612  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm          ApacheCommonsPool002Pool  thrpt    5   111.060 ±   128.451    B/op
Compare001Benchmark.test:gc.count                    ApacheCommonsPool002Pool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                      ApacheCommonsPool003SoftRefPool  thrpt    5     3.652 ±     0.365  ops/us
Compare001Benchmark.test:gc.alloc.rate        ApacheCommonsPool003SoftRefPool  thrpt    5   160.951 ±   295.581  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm   ApacheCommonsPool003SoftRefPool  thrpt    5    60.576 ±    39.504    B/op
Compare001Benchmark.test:gc.count             ApacheCommonsPool003SoftRefPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                            ApacheCommonsPool2001Pool  thrpt    5     1.764 ±     0.087  ops/us
Compare001Benchmark.test:gc.alloc.rate              ApacheCommonsPool2001Pool  thrpt    5   302.786 ±   567.313  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm         ApacheCommonsPool2001Pool  thrpt    5   229.088 ±    80.807    B/op
Compare001Benchmark.test:gc.count                   ApacheCommonsPool2001Pool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                     ApacheCommonsPool2002SoftRefPool  thrpt    5     0.049 ±     0.037  ops/us
Compare001Benchmark.test:gc.alloc.rate       ApacheCommonsPool2002SoftRefPool  thrpt    5    58.518 ±    88.327  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm  ApacheCommonsPool2002SoftRefPool  thrpt    5  1882.478 ±  1659.440    B/op
Compare001Benchmark.test:gc.count            ApacheCommonsPool2002SoftRefPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                     BeeOp001FastPool  thrpt    5    26.457 ±    13.509  ops/us
Compare001Benchmark.test:gc.alloc.rate                       BeeOp001FastPool  thrpt    5   637.455 ±  1282.328  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                  BeeOp001FastPool  thrpt    5    32.711 ±     4.722    B/op
Compare001Benchmark.test:gc.count                            BeeOp001FastPool  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                 BeeOp002ObjectSource  thrpt    5    14.778 ±    14.634  ops/us
Compare001Benchmark.test:gc.alloc.rate                   BeeOp002ObjectSource  thrpt    5  1427.816 ±  2925.769  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm              BeeOp002ObjectSource  thrpt    5   121.478 ±    12.924    B/op
Compare001Benchmark.test:gc.count                        BeeOp002ObjectSource  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                         BeeOp002ObjectSource  thrpt    5    12.000                  ms
Compare001Benchmark.test                                CoralPool001ArrayPool  thrpt    5    30.629 ±     5.215  ops/us
Compare001Benchmark.test:gc.alloc.rate                  CoralPool001ArrayPool  thrpt    5   374.775 ±   702.007  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm             CoralPool001ArrayPool  thrpt    5    17.120 ±     5.444    B/op
Compare001Benchmark.test:gc.count                       CoralPool001ArrayPool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                        CoralPool001ArrayPool  thrpt    5     7.000                  ms
Compare001Benchmark.test                               CoralPool002LinkedPool  thrpt    5    16.163 ±     2.195  ops/us
Compare001Benchmark.test:gc.alloc.rate                 CoralPool002LinkedPool  thrpt    5   208.741 ±   377.182  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm            CoralPool002LinkedPool  thrpt    5    18.414 ±    12.759    B/op
Compare001Benchmark.test:gc.count                      CoralPool002LinkedPool  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                       CoralPool002LinkedPool  thrpt    5     4.000                  ms
Compare001Benchmark.test                                      EraaSoftPool001  thrpt    5     8.747 ±     0.615  ops/us
Compare001Benchmark.test:gc.alloc.rate                        EraaSoftPool001  thrpt    5   278.767 ±   520.606  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                   EraaSoftPool001  thrpt    5    42.709 ±    16.096    B/op
Compare001Benchmark.test:gc.count                             EraaSoftPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                              EraaSoftPool001  thrpt    5     6.000                  ms
Compare001Benchmark.test                                          FastPool001  thrpt    5    21.565 ±    40.641  ops/us
Compare001Benchmark.test:gc.alloc.rate                            FastPool001  thrpt    5   331.695 ±   761.759  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       FastPool001  thrpt    5    21.185 ±    16.524    B/op
Compare001Benchmark.test:gc.count                                 FastPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                  FastPool001  thrpt    5     8.000                  ms
Compare001Benchmark.test                                 FastPool002Disruptor  thrpt    5    17.806 ±    13.219  ops/us
Compare001Benchmark.test:gc.alloc.rate                   FastPool002Disruptor  thrpt    5   222.968 ±   455.103  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm              FastPool002Disruptor  thrpt    5    17.003 ±     8.828    B/op
Compare001Benchmark.test:gc.count                        FastPool002Disruptor  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                         FastPool002Disruptor  thrpt    5     6.000                  ms
Compare001Benchmark.test                                         Frogspawn001  thrpt    5   134.023 ±    29.006  ops/us
Compare001Benchmark.test:gc.alloc.rate                           Frogspawn001  thrpt    5     6.236 ±     3.718  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                      Frogspawn001  thrpt    5     0.154 ±     0.881    B/op
Compare001Benchmark.test:gc.count                                Frogspawn001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                           JavaNew001  thrpt    5   285.933 ±   106.050  ops/us
Compare001Benchmark.test:gc.alloc.rate                             JavaNew001  thrpt    5  8614.512 ± 16961.328  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                        JavaNew001  thrpt    5    40.087 ±     0.550    B/op
Compare001Benchmark.test:gc.count                                  JavaNew001  thrpt    5     2.000              counts
Compare001Benchmark.test:gc.time                                   JavaNew001  thrpt    5    12.000                  ms
Compare001Benchmark.test                                            KOPool001  thrpt    5     2.406 ±     0.459  ops/us
Compare001Benchmark.test:gc.alloc.rate                              KOPool001  thrpt    5   405.363 ±   768.912  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                         KOPool001  thrpt    5   222.800 ±    57.433    B/op
Compare001Benchmark.test:gc.count                                   KOPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                    KOPool001  thrpt    5     8.000                  ms
Compare001Benchmark.test                                          LitePool001  thrpt    5   122.219 ±    23.161  ops/us
Compare001Benchmark.test:gc.alloc.rate                            LitePool001  thrpt    5  1421.180 ±  2701.225  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       LitePool001  thrpt    5    16.129 ±     1.106    B/op
Compare001Benchmark.test:gc.count                                 LitePool001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                          NoMoreInstance001SweepClean  thrpt    5   243.561 ±   235.059  ops/us
Compare001Benchmark.test:gc.alloc.rate            NoMoreInstance001SweepClean  thrpt    5  5234.959 ±  9329.665  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm       NoMoreInstance001SweepClean  thrpt    5    40.088 ±     0.339    B/op
Compare001Benchmark.test:gc.count                 NoMoreInstance001SweepClean  thrpt    5     2.000              counts
Compare001Benchmark.test:gc.time                  NoMoreInstance001SweepClean  thrpt    5    51.000                  ms
Compare001Benchmark.test                               NoMoreInstance002Clean  thrpt    5     3.082 ±     1.355  ops/us
Compare001Benchmark.test:gc.alloc.rate                 NoMoreInstance002Clean  thrpt    5     1.384 ±    11.185  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm            NoMoreInstance002Clean  thrpt    5     2.961 ±    25.251    B/op
Compare001Benchmark.test:gc.count                      NoMoreInstance002Clean  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                            Recall001  thrpt    5     0.880 ±     0.367  ops/us
Compare001Benchmark.test:gc.alloc.rate                              Recall001  thrpt    5   439.655 ±   836.090  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                         Recall001  thrpt    5   747.779 ±   134.482    B/op
Compare001Benchmark.test:gc.count                                   Recall001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                          StormPot001  thrpt    5    77.114 ±     8.150  ops/us
Compare001Benchmark.test:gc.alloc.rate                            StormPot001  thrpt    5     1.588 ±    11.556  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                       StormPot001  thrpt    5     0.220 ±     1.864    B/op
Compare001Benchmark.test:gc.count                                 StormPot001  thrpt    5       ≈ 0              counts
Compare001Benchmark.test                                         ViburPool001  thrpt    5     4.812 ±     0.518  ops/us
Compare001Benchmark.test:gc.alloc.rate                           ViburPool001  thrpt    5   208.889 ±   382.299  MB/sec
Compare001Benchmark.test:gc.alloc.rate.norm                      ViburPool001  thrpt    5    59.120 ±    26.679    B/op
Compare001Benchmark.test:gc.count                                ViburPool001  thrpt    5     1.000              counts
Compare001Benchmark.test:gc.time                                 ViburPool001  thrpt    5     5.000                  ms
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
