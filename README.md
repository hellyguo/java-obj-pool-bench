# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 21**

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
- [`Stormpot Blaze/Queue Pool`](http://chrisvest.github.io/stormpot/)
- [`vibur-object-pool`](https://github.com/vibur/vibur-object-pool)

> 运行于 Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

> `JVM` 开启参数 `-XX:-RestrictContended`

```verilog
Benchmark                                                            (desc)   Mode  Cnt    Score    Error   Units
Compare001Benchmark.testPoolGetAndRelease     ApacheCommonsPool001StackPool  thrpt    5    3.209 ±  0.165  ops/us
Compare001Benchmark.testPoolGetAndRelease          ApacheCommonsPool002Pool  thrpt    5    0.949 ±  0.704  ops/us
Compare001Benchmark.testPoolGetAndRelease   ApacheCommonsPool003SoftRefPool  thrpt    5    3.124 ±  0.824  ops/us
Compare001Benchmark.testPoolGetAndRelease         ApacheCommonsPool2001Pool  thrpt    5    1.682 ±  0.209  ops/us
Compare001Benchmark.testPoolGetAndRelease  ApacheCommonsPool2002SoftRefPool  thrpt    5    0.034 ±  0.008  ops/us
Compare001Benchmark.testPoolGetAndRelease                  BeeOp001FastPool  thrpt    5   36.595 ± 20.279  ops/us
Compare001Benchmark.testPoolGetAndRelease              BeeOp002ObjectSource  thrpt    5   18.224 ±  3.181  ops/us
Compare001Benchmark.testPoolGetAndRelease             CoralPool001ArrayPool  thrpt    5    1.235 ±  0.262  ops/us
Compare001Benchmark.testPoolGetAndRelease            CoralPool002LinkedPool  thrpt    5   15.141 ±  0.905  ops/us
Compare001Benchmark.testPoolGetAndRelease                   EraaSoftPool001  thrpt    5    7.497 ±  1.323  ops/us
Compare001Benchmark.testPoolGetAndRelease                       FastPool001  thrpt    5   15.941 ±  9.477  ops/us
Compare001Benchmark.testPoolGetAndRelease                      Frogspawn001  thrpt    5  147.943 ± 35.777  ops/us
Compare001Benchmark.testPoolGetAndRelease                        JavaNew001  thrpt    5  322.181 ± 70.937  ops/us
Compare001Benchmark.testPoolGetAndRelease                         KOPool001  thrpt    5    2.315 ±  0.340  ops/us
Compare001Benchmark.testPoolGetAndRelease                       LitePool001  thrpt    5  123.492 ± 37.044  ops/us
Compare001Benchmark.testPoolGetAndRelease                       StormPot001  thrpt    5   55.383 ± 15.792  ops/us
Compare001Benchmark.testPoolGetAndRelease                      ViburPool001  thrpt    5    4.619 ±  0.523  ops/us
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
