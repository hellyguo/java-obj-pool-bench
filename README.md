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
Benchmark                                                            (desc)   Mode  Cnt    Score    Error   Units
Compare001Benchmark.testPoolGetAndRelease     ApacheCommonsPool001StackPool  thrpt    5    3.498 ±  0.323  ops/us
Compare001Benchmark.testPoolGetAndRelease          ApacheCommonsPool002Pool  thrpt    5    0.876 ±  0.240  ops/us
Compare001Benchmark.testPoolGetAndRelease   ApacheCommonsPool003SoftRefPool  thrpt    5    3.579 ±  1.079  ops/us
Compare001Benchmark.testPoolGetAndRelease         ApacheCommonsPool2001Pool  thrpt    5    1.677 ±  0.109  ops/us
Compare001Benchmark.testPoolGetAndRelease  ApacheCommonsPool2002SoftRefPool  thrpt    5    0.043 ±  0.023  ops/us
Compare001Benchmark.testPoolGetAndRelease                  BeeOp001FastPool  thrpt    5   41.345 ±  5.385  ops/us
Compare001Benchmark.testPoolGetAndRelease              BeeOp002ObjectSource  thrpt    5   16.782 ±  6.023  ops/us
Compare001Benchmark.testPoolGetAndRelease             CoralPool001ArrayPool  thrpt    5    1.215 ±  0.352  ops/us
Compare001Benchmark.testPoolGetAndRelease            CoralPool002LinkedPool  thrpt    5   12.837 ±  3.524  ops/us
Compare001Benchmark.testPoolGetAndRelease                   EraaSoftPool001  thrpt    5    8.481 ±  1.492  ops/us
Compare001Benchmark.testPoolGetAndRelease                       FastPool001  thrpt    5   22.677 ± 47.170  ops/us
Compare001Benchmark.testPoolGetAndRelease                      Frogspawn001  thrpt    5  142.799 ± 39.825  ops/us
Compare001Benchmark.testPoolGetAndRelease                        JavaNew001  thrpt    5  298.461 ± 80.669  ops/us
Compare001Benchmark.testPoolGetAndRelease                         KOPool001  thrpt    5    2.118 ±  0.260  ops/us
Compare001Benchmark.testPoolGetAndRelease                       LitePool001  thrpt    5  135.093 ± 25.988  ops/us
Compare001Benchmark.testPoolGetAndRelease       NoMoreInstance001SweepClean  thrpt    5  302.087 ± 32.272  ops/us
Compare001Benchmark.testPoolGetAndRelease            NoMoreInstance002Clean  thrpt    5    3.076 ±  0.725  ops/us
Compare001Benchmark.testPoolGetAndRelease                       StormPot001  thrpt    5   52.248 ±  6.214  ops/us
Compare001Benchmark.testPoolGetAndRelease                      ViburPool001  thrpt    5    4.365 ±  0.428  ops/us
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
