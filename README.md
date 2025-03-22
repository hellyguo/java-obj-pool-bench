# java-obj-pool-bench

try to compare all available object pool libraries

## 对比测试结果

- just `new`
- [`BeeOP`](https://github.com/Chris2018998/BeeOP)
- [`commons-pool2`](https://commons.apache.org/proper/commons-pool/)
- [`frogspawn`](https://itcraft.cn/frogspawn/)
- [`generic-object-pool`](https://github.com/bbottema/generic-object-pool)
- [`lite-pool`](https://github.com/nextopcn/lite-pool)
- [`vibur-object-pool`](https://github.com/vibur/vibur-object-pool)

> 运行于 Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

> `JVM` 开启参数 `-XX:-RestrictContended`

```verilog
Benchmark                                Mode  Cnt       Score       Error   Units
Compare001Benchmark.test0New            thrpt    5  301347.358 ± 22125.385  ops/ms
Compare001Benchmark.test1BeeOP          thrpt    5   18679.264 ±  1319.493  ops/ms
Compare001Benchmark.test2CommonsPool    thrpt    5    2015.602 ±   127.976  ops/ms
Compare001Benchmark.test3FrogspawnPool  thrpt    5  143229.173 ± 14218.968  ops/ms
Compare001Benchmark.test4GOPool         thrpt    5    5889.166 ±   519.793  ops/ms
Compare001Benchmark.test5LitePool       thrpt    5     407.865 ±    91.235  ops/ms
Compare001Benchmark.test6ViburPool      thrpt    5    4703.997 ±   393.154  ops/ms
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
