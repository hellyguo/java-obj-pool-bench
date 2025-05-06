# java-obj-pool-bench

try to compare all available object pool libraries

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
Benchmark                                       Mode  Cnt    Score    Error   Units
Compare001Benchmark.test00New                  thrpt    5  205.219 ± 90.634  ops/us
Compare001Benchmark.test0101BeeOP              thrpt    5   24.990 ± 30.747  ops/us
Compare001Benchmark.test0102BeeOP              thrpt    5   12.373 ±  2.314  ops/us
Compare001Benchmark.test0201CommonsPool        thrpt    5    3.050 ±  0.187  ops/us
Compare001Benchmark.test0202CommonsPool        thrpt    5    0.944 ±  0.084  ops/us
Compare001Benchmark.test0203CommonsPool        thrpt    5    4.917 ±  0.223  ops/us
Compare001Benchmark.test0301CommonsPool2       thrpt    5    1.936 ±  0.035  ops/us
Compare001Benchmark.test0302CommonsPool2       thrpt    5    0.059 ±  0.006  ops/us
Compare001Benchmark.test0401CoralArrayPool     thrpt    5   22.745 ±  1.376  ops/us
Compare001Benchmark.test0402CoralLinkedPool    thrpt    5    8.302 ±  1.113  ops/us
Compare001Benchmark.test05ERASOFTPool          thrpt    5    8.162 ±  0.319  ops/us
Compare001Benchmark.test06FastObjectPool       thrpt    5   16.574 ± 17.230  ops/us
Compare001Benchmark.test07FrogspawnPool        thrpt    5  137.792 ± 11.821  ops/us
Compare001Benchmark.test08GOPool               thrpt    5    5.806 ±  0.506  ops/us
Compare001Benchmark.test09KBPool               thrpt    5    2.952 ±  0.626  ops/us
Compare001Benchmark.test10LitePool             thrpt    5    0.422 ±  0.044  ops/us
Compare001Benchmark.test1101StormPotBlazePool  thrpt    5   96.361 ± 20.628  ops/us
Compare001Benchmark.test1102StormPotQueuePool  thrpt    5    4.753 ±  0.592  ops/us
Compare001Benchmark.test12ViburPool            thrpt    5    3.729 ±  0.169  ops/us
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
