# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 1.8**

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
Benchmark                                                            (desc)   Mode  Cnt    Score    Error   Units
Compare001Benchmark.testPoolGetAndRelease     ApacheCommonsPool001StackPool  thrpt    5    2.684 ±  0.680  ops/us
Compare001Benchmark.testPoolGetAndRelease          ApacheCommonsPool002Pool  thrpt    5    1.047 ±  0.150  ops/us
Compare001Benchmark.testPoolGetAndRelease   ApacheCommonsPool003SoftRefPool  thrpt    5    4.151 ±  0.494  ops/us
Compare001Benchmark.testPoolGetAndRelease         ApacheCommonsPool2001Pool  thrpt    5    1.804 ±  0.239  ops/us
Compare001Benchmark.testPoolGetAndRelease  ApacheCommonsPool2002SoftRefPool  thrpt    5    0.047 ±  0.009  ops/us
Compare001Benchmark.testPoolGetAndRelease                  BeeOp001FastPool  thrpt    5   24.292 ± 11.521  ops/us
Compare001Benchmark.testPoolGetAndRelease              BeeOp002ObjectSource  thrpt    5   11.077 ±  8.003  ops/us
Compare001Benchmark.testPoolGetAndRelease             CoralPool001ArrayPool  thrpt    5   16.179 ± 33.348  ops/us
Compare001Benchmark.testPoolGetAndRelease            CoralPool002LinkedPool  thrpt    5    7.127 ±  1.874  ops/us
Compare001Benchmark.testPoolGetAndRelease                   EraaSoftPool001  thrpt    5    7.016 ±  0.461  ops/us
Compare001Benchmark.testPoolGetAndRelease                       FastPool001  thrpt    5   10.222 ±  7.259  ops/us
Compare001Benchmark.testPoolGetAndRelease                      Frogspawn001  thrpt    5  117.436 ± 12.307  ops/us
Compare001Benchmark.testPoolGetAndRelease              GenericObjectPool001  thrpt    5    4.481 ±  0.075  ops/us
Compare001Benchmark.testPoolGetAndRelease                        JavaNew001  thrpt    5  287.359 ± 19.300  ops/us
Compare001Benchmark.testPoolGetAndRelease                         KOPool001  thrpt    5    2.257 ±  0.358  ops/us
Compare001Benchmark.testPoolGetAndRelease                       LitePool001  thrpt    5    0.369 ±  0.025  ops/us
Compare001Benchmark.testPoolGetAndRelease              StormPot001BlazePool  thrpt    5   77.003 ± 26.504  ops/us
Compare001Benchmark.testPoolGetAndRelease              StormPot002QueuePool  thrpt    5    4.601 ±  0.297  ops/us
Compare001Benchmark.testPoolGetAndRelease                      ViburPool001  thrpt    5    3.921 ±  0.105  ops/us
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
