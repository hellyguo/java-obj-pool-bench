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
Benchmark                                                            (desc)   Mode  Cnt    Score     Error   Units
Compare001Benchmark.testPoolGetAndRelease     ApacheCommonsPool001StackPool  thrpt    5    2.724 ±   0.369  ops/us
Compare001Benchmark.testPoolGetAndRelease          ApacheCommonsPool002Pool  thrpt    5    0.972 ±   0.176  ops/us
Compare001Benchmark.testPoolGetAndRelease   ApacheCommonsPool003SoftRefPool  thrpt    5    2.959 ±   0.710  ops/us
Compare001Benchmark.testPoolGetAndRelease         ApacheCommonsPool2001Pool  thrpt    5    1.629 ±   0.150  ops/us
Compare001Benchmark.testPoolGetAndRelease  ApacheCommonsPool2002SoftRefPool  thrpt    5    0.035 ±   0.010  ops/us
Compare001Benchmark.testPoolGetAndRelease                  BeeOp001FastPool  thrpt    5   43.821 ±   9.843  ops/us
Compare001Benchmark.testPoolGetAndRelease              BeeOp002ObjectSource  thrpt    5   19.421 ±   4.888  ops/us
Compare001Benchmark.testPoolGetAndRelease             CoralPool001ArrayPool  thrpt    5    1.263 ±   0.622  ops/us
Compare001Benchmark.testPoolGetAndRelease            CoralPool002LinkedPool  thrpt    5   14.171 ±   2.383  ops/us
Compare001Benchmark.testPoolGetAndRelease                   EraaSoftPool001  thrpt    5    7.643 ±   0.865  ops/us
Compare001Benchmark.testPoolGetAndRelease                       FastPool001  thrpt    5   15.145 ±  11.297  ops/us
Compare001Benchmark.testPoolGetAndRelease                      Frogspawn001  thrpt    5  141.188 ±  27.549  ops/us
Compare001Benchmark.testPoolGetAndRelease                        JavaNew001  thrpt    5  240.644 ± 321.133  ops/us
Compare001Benchmark.testPoolGetAndRelease                         KOPool001  thrpt    5    2.134 ±   0.403  ops/us
Compare001Benchmark.testPoolGetAndRelease                       LitePool001  thrpt    5  122.474 ±  15.174  ops/us
Compare001Benchmark.testPoolGetAndRelease       NoMoreInstance001SweepClean  thrpt    5  239.863 ± 329.752  ops/us
Compare001Benchmark.testPoolGetAndRelease            NoMoreInstance002Clean  thrpt    5    2.977 ±   0.313  ops/us
Compare001Benchmark.testPoolGetAndRelease                         Recall001  thrpt    5    1.257 ±   0.187  ops/us
Compare001Benchmark.testPoolGetAndRelease                       StormPot001  thrpt    5   49.692 ±   9.309  ops/us
Compare001Benchmark.testPoolGetAndRelease                      ViburPool001  thrpt    5    3.912 ±   0.202  ops/us
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
