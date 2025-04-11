# java-obj-pool-bench

try to compare all available object pool libraries

## 对比测试结果

- just `new`
- [`BeeOP`](https://github.com/Chris2018998/BeeOP)
- [`commons-pool`](https://commons.apache.org/proper/commons-pool/)
- [`commons-pool2`](https://commons.apache.org/proper/commons-pool/)
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
Benchmark                                     Mode  Cnt    Score     Error   Units
Compare001Benchmark.test00New                thrpt    5  191.197 ± 185.102  ops/us
Compare001Benchmark.test01BeeOP              thrpt    5   23.143 ±   6.302  ops/us
Compare001Benchmark.test02BeeOP              thrpt    5   11.880 ±  12.909  ops/us
Compare001Benchmark.test03CommonsPool        thrpt    5    2.682 ±   0.151  ops/us
Compare001Benchmark.test04CommonsPool        thrpt    5    0.881 ±   0.104  ops/us
Compare001Benchmark.test05CommonsPool        thrpt    5    4.119 ±   0.122  ops/us
Compare001Benchmark.test06CommonsPool2       thrpt    5    1.729 ±   0.156  ops/us
Compare001Benchmark.test07CommonsPool2       thrpt    5    0.042 ±   0.026  ops/us
Compare001Benchmark.test08ERASOFTPool        thrpt    5    7.232 ±   0.916  ops/us
Compare001Benchmark.test09FrogspawnPool      thrpt    5  115.128 ±  30.398  ops/us
Compare001Benchmark.test10GOPool             thrpt    5    4.453 ±   0.337  ops/us
Compare001Benchmark.test11KBPool             thrpt    5    2.263 ±   0.180  ops/us
Compare001Benchmark.test12LitePool           thrpt    5    0.351 ±   0.118  ops/us
Compare001Benchmark.test13StormPotBlazePool  thrpt    5   85.196 ±  14.715  ops/us
Compare001Benchmark.test14StormPotQueuePool  thrpt    5    4.594 ±   1.015  ops/us
Compare001Benchmark.test15ViburPool          thrpt    5    4.317 ±   0.478  ops/us
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
