# java-obj-pool-bench

try to compare all available object pool libraries

## 对比测试结果

- [`commons-pool2`](https://commons.apache.org/proper/commons-pool/)
- [`vibur-object-pool`](https://github.com/vibur/vibur-object-pool)
- [`BeeOP`](https://github.com/Chris2018998/BeeOP)
- [`lite-pool`](https://github.com/nextopcn/lite-pool)
- [`generic-object-pool`](https://github.com/bbottema/generic-object-pool)
- [`frogspawn`](https://itcraft.cn/frogspawn/)

> 运行于 Intel(R) Core(TM) i5-10210U CPU @ 1.60GHz

> `JVM` 开启参数 `-XX:-RestrictContended`

```verilog
Benchmark                               Mode  Cnt       Score       Error   Units
Compare001Benchmark.testBeeOP          thrpt    5   17813.885 ±  1514.243  ops/ms
Compare001Benchmark.testCommonsPool    thrpt    5    2498.101 ±    64.911  ops/ms
Compare001Benchmark.testFrogspawnPool  thrpt    5  144598.931 ± 11889.548  ops/ms
Compare001Benchmark.testGOPool         thrpt    5    5794.308 ±   361.158  ops/ms
Compare001Benchmark.testLitePool       thrpt    5     452.887 ±    39.941  ops/ms
Compare001Benchmark.testNew            thrpt    5  287114.810 ± 29764.280  ops/ms
Compare001Benchmark.testViburPool      thrpt    5    4675.089 ±   466.321  ops/ms
```

```verilog
Benchmark                                                   Mode  Cnt     Score      Error   Units
Compare002Benchmark.testBeeOPOneRequestMultiTimes          thrpt    5  9667.946 ± 1277.391  ops/ms
Compare002Benchmark.testFrogspawnPoolOneRequestMultiTimes  thrpt    5   948.129 ±   76.413  ops/ms
Compare002Benchmark.testGOPoolOneRequestMultiTimes         thrpt    5   173.312 ±   12.269  ops/ms
Compare002Benchmark.testLitePoolOneRequestMultiTimes       thrpt    5   439.864 ±   63.803  ops/ms
Compare002Benchmark.testNewOneRequestMultiTimes            thrpt    5  8385.798 ±  720.384  ops/ms
Compare002Benchmark.testViburPoolOneRequestMultiTimes      thrpt    5   140.954 ±    6.561  ops/ms
```
