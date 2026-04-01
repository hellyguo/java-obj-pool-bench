# 对比测试结果

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

> `JVM` Oracle JDK 21 (21+35-LTS-2513)

> `JVM` 开启参数 `-XX:-RestrictContended`

> 测试版本: frogspawn 0.6

```verilog
Benchmark                                           (desc)   Mode  Cnt     Score     Error   Units
Compare001Benchmark.test     ApacheCommonsPool001StackPool  thrpt    5     2.696 ±   1.734  ops/us
Compare001Benchmark.test          ApacheCommonsPool002Pool  thrpt    5     0.517 ±   0.154  ops/us
Compare001Benchmark.test   ApacheCommonsPool003SoftRefPool  thrpt    5     3.079 ±   3.631  ops/us
Compare001Benchmark.test         ApacheCommonsPool2001Pool  thrpt    5     2.704 ±   2.544  ops/us
Compare001Benchmark.test  ApacheCommonsPool2002SoftRefPool  thrpt    5     0.104 ±   0.053  ops/us
Compare001Benchmark.test                  BeeOp001FastPool  thrpt    5    54.133 ±  12.468  ops/us
Compare001Benchmark.test              BeeOp002ObjectSource  thrpt    5    74.039 ± 102.320  ops/us
Compare001Benchmark.test             CoralPool001ArrayPool  thrpt    5     9.644 ±   0.778  ops/us
Compare001Benchmark.test            CoralPool002LinkedPool  thrpt    5     6.982 ±   1.157  ops/us
Compare001Benchmark.test                       FastPool001  thrpt    5    11.765 ±  10.966  ops/us
Compare001Benchmark.test              FastPool002Disruptor  thrpt    5    19.946 ±  11.463  ops/us
Compare001Benchmark.test                      Frogspawn001  thrpt    5  1630.756 ± 130.822  ops/us
Compare001Benchmark.test                      Frogspawn002  thrpt    5  1364.211 ± 275.112  ops/us
Compare001Benchmark.test                      Frogspawn003  thrpt    5  1515.705 ±  71.387  ops/us
Compare001Benchmark.test                      Frogspawn004  thrpt    5  1636.973 ± 201.100  ops/us
Compare001Benchmark.test                      Frogspawn005  thrpt    5  1379.323 ± 163.204  ops/us
Compare001Benchmark.test                      Frogspawn006  thrpt    5  1373.697 ± 122.471  ops/us
Compare001Benchmark.test                      Frogspawn007  thrpt    5  1572.294 ± 241.037  ops/us
Compare001Benchmark.test                      Frogspawn008  thrpt    5  1317.496 ± 264.103  ops/us
Compare001Benchmark.test                      Frogspawn009  thrpt    5  1504.361 ± 133.420  ops/us
Compare001Benchmark.test              FuriousObjectPool001  thrpt    5    12.778 ±   7.788  ops/us
Compare001Benchmark.test                        JavaNew001  thrpt    5   613.399 ±  48.279  ops/us
Compare001Benchmark.test                         KOPool001  thrpt    5     4.079 ±   1.393  ops/us
Compare001Benchmark.test                       LitePool001  thrpt    5   254.470 ±  75.504  ops/us
Compare001Benchmark.test       NoMoreInstance001SweepClean  thrpt    5   625.508 ±  34.097  ops/us
Compare001Benchmark.test            NoMoreInstance002Clean  thrpt    5     2.212 ±   0.200  ops/us
Compare001Benchmark.test                         Recall001  thrpt    5     8.605 ±   1.122  ops/us
Compare001Benchmark.test                       StormPot001  thrpt    5   269.695 ±  10.417  ops/us
Compare001Benchmark.test                      ViburPool001  thrpt    5     5.455 ±   1.164  ops/us
```

```verilog
Benchmark                                               (desc)   Mode  Cnt      Score      Error   Units
Compare002Benchmark.testBatch    ApacheCommonsPool001StackPool  thrpt    5     70.959 ±   27.569  ops/ms
Compare002Benchmark.testBatch         ApacheCommonsPool002Pool  thrpt    5     22.418 ±   17.213  ops/ms
Compare002Benchmark.testBatch  ApacheCommonsPool003SoftRefPool  thrpt    5     88.882 ±   61.783  ops/ms
Compare002Benchmark.testBatch        ApacheCommonsPool2001Pool  thrpt    5     74.169 ±   31.834  ops/ms
Compare002Benchmark.testBatch                 BeeOp001FastPool  thrpt    5    195.474 ±   50.490  ops/ms
Compare002Benchmark.testBatch                      FastPool001  thrpt    5    429.997 ±   19.217  ops/ms
Compare002Benchmark.testBatch             FastPool002Disruptor  thrpt    5    937.239 ±  492.048  ops/ms
Compare002Benchmark.testBatch                     Frogspawn001  thrpt    5    897.318 ±   98.407  ops/ms
Compare002Benchmark.testBatch                     Frogspawn003  thrpt    5    870.748 ±   86.167  ops/ms
Compare002Benchmark.testBatch                     Frogspawn004  thrpt    5    875.032 ±  116.578  ops/ms
Compare002Benchmark.testBatch                     Frogspawn006  thrpt    5    891.042 ±  168.399  ops/ms
Compare002Benchmark.testBatch                     Frogspawn007  thrpt    5    678.908 ±   46.245  ops/ms
Compare002Benchmark.testBatch                     Frogspawn008  thrpt    5    883.591 ±  111.139  ops/ms
Compare002Benchmark.testBatch                     Frogspawn009  thrpt    5    672.920 ±   37.483  ops/ms
Compare002Benchmark.testBatch             FuriousObjectPool001  thrpt    5    417.782 ±  252.709  ops/ms
Compare002Benchmark.testBatch                       JavaNew001  thrpt    5  16751.427 ± 2446.980  ops/ms
Compare002Benchmark.testBatch                        KOPool001  thrpt    5    233.435 ±   86.218  ops/ms
Compare002Benchmark.testBatch      NoMoreInstance001SweepClean  thrpt    5  16845.563 ± 1272.416  ops/ms
Compare002Benchmark.testBatch           NoMoreInstance002Clean  thrpt    5     61.255 ±    4.384  ops/ms
Compare002Benchmark.testBatch                      StormPot001  thrpt    5    168.170 ±   10.980  ops/ms
Compare002Benchmark.testBatch                     ViburPool001  thrpt    5    145.024 ±    6.880  ops/ms
```

## 测试失败说明

以下实现在批量测试中失败：

| 实现 | 失败原因 |
|------|----------|
| ApacheCommonsPool2002SoftRefPool | NullPointerException: makeObject() 返回 null |
| BeeOp002ObjectSource | ClassCastException: 代理类转换失败 |
| CoralPool001ArrayPool | ArrayIndexOutOfBoundsException |
| CoralPool002LinkedPool | NullPointerException |