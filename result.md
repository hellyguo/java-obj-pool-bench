# 对比测试结果

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

> `JVM` OpenJDK 25.0.2+10-LTS

> `JVM` 开启参数 `-XX:-RestrictContended`

> 测试版本: frogspawn 0.6

```verilog
Benchmark                                           (desc)   Mode  Cnt     Score     Error   Units
Compare001Benchmark.test     ApacheCommonsPool001StackPool  thrpt    5     4.289 ±   3.918  ops/us
Compare001Benchmark.test          ApacheCommonsPool002Pool  thrpt    5     1.182 ±   1.937  ops/us
Compare001Benchmark.test   ApacheCommonsPool003SoftRefPool  thrpt    5     4.776 ±   4.855  ops/us
Compare001Benchmark.test         ApacheCommonsPool2001Pool  thrpt    5     2.173 ±   0.905  ops/us
Compare001Benchmark.test  ApacheCommonsPool2002SoftRefPool  thrpt    5     0.085 ±   0.038  ops/us
Compare001Benchmark.test                  BeeOp001FastPool  thrpt    5    57.061 ±  29.633  ops/us
Compare001Benchmark.test              BeeOp002ObjectSource  thrpt    5    44.011 ±  10.930  ops/us
Compare001Benchmark.test             CoralPool001ArrayPool  thrpt    5    24.434 ±   1.892  ops/us
Compare001Benchmark.test            CoralPool002LinkedPool  thrpt    5     6.838 ±   0.440  ops/us
Compare001Benchmark.test                       FastPool001  thrpt    5    15.163 ±   2.580  ops/us
Compare001Benchmark.test              FastPool002Disruptor  thrpt    5    18.879 ±  13.548  ops/us
Compare001Benchmark.test                      Frogspawn001  thrpt    5  1010.752 ±  61.414  ops/us
Compare001Benchmark.test                      Frogspawn002  thrpt    5  1151.422 ±  86.059  ops/us
Compare001Benchmark.test                      Frogspawn003  thrpt    5  1164.436 ± 268.994  ops/us
Compare001Benchmark.test                      Frogspawn004  thrpt    5  1055.440 ± 109.274  ops/us
Compare001Benchmark.test                      Frogspawn005  thrpt    5  1199.414 ± 107.628  ops/us
Compare001Benchmark.test                      Frogspawn006  thrpt    5  1073.233 ± 158.693  ops/us
Compare001Benchmark.test                      Frogspawn007  thrpt    5  1102.628 ±  82.463  ops/us
Compare001Benchmark.test                      Frogspawn008  thrpt    5  1029.057 ± 217.100  ops/us
Compare001Benchmark.test                      Frogspawn009  thrpt    5  1059.013 ± 116.408  ops/us
Compare001Benchmark.test              FuriousObjectPool001  thrpt    5    13.135 ±   9.107  ops/us
Compare001Benchmark.test              GenericObjectPool001  thrpt    5     7.243 ±   4.128  ops/us
Compare001Benchmark.test                        JavaNew001  thrpt    5   574.073 ± 241.607  ops/us
Compare001Benchmark.test                         KOPool001  thrpt    5     6.346 ±   3.612  ops/us
Compare001Benchmark.test                       LitePool001  thrpt    5   282.483 ±  85.462  ops/us
Compare001Benchmark.test              StormPot001BlazePool  thrpt    5   291.255 ±  24.446  ops/us
Compare001Benchmark.test              StormPot002QueuePool  thrpt    5     5.498 ±   0.838  ops/us
Compare001Benchmark.test                      ViburPool001  thrpt    5     4.042 ±   2.811  ops/us
```

```verilog
Benchmark                                               (desc)   Mode  Cnt      Score      Error   Units
Compare002Benchmark.testBatch    ApacheCommonsPool001StackPool  thrpt    5     82.350 ±   44.934  ops/ms
Compare002Benchmark.testBatch         ApacheCommonsPool002Pool  thrpt    5     24.150 ±   11.273  ops/ms
Compare002Benchmark.testBatch  ApacheCommonsPool003SoftRefPool  thrpt    5     91.548 ±   87.371  ops/ms
Compare002Benchmark.testBatch        ApacheCommonsPool2001Pool  thrpt    5     75.606 ±   44.528  ops/ms
Compare002Benchmark.testBatch                 BeeOp001FastPool  thrpt    5    163.425 ±   13.807  ops/ms
Compare002Benchmark.testBatch                      FastPool001  thrpt    5    412.837 ±  167.161  ops/ms
Compare002Benchmark.testBatch             FastPool002Disruptor  thrpt    5    778.448 ±  269.546  ops/ms
Compare002Benchmark.testBatch                     Frogspawn001  thrpt    5    905.256 ±  193.825  ops/ms
Compare002Benchmark.testBatch                     Frogspawn003  thrpt    5    825.525 ±  353.566  ops/ms
Compare002Benchmark.testBatch                     Frogspawn004  thrpt    5    863.316 ±   67.834  ops/ms
Compare002Benchmark.testBatch                     Frogspawn006  thrpt    5    880.359 ±  199.603  ops/ms
Compare002Benchmark.testBatch                     Frogspawn007  thrpt    5    910.591 ±   72.257  ops/ms
Compare002Benchmark.testBatch                     Frogspawn008  thrpt    5    853.516 ±   47.945  ops/ms
Compare002Benchmark.testBatch                     Frogspawn009  thrpt    5    656.258 ±   46.528  ops/ms
Compare002Benchmark.testBatch             FuriousObjectPool001  thrpt    5    363.443 ±  272.297  ops/ms
Compare002Benchmark.testBatch             GenericObjectPool001  thrpt    5    240.446 ±  111.403  ops/ms
Compare002Benchmark.testBatch                       JavaNew001  thrpt    5  20369.499 ± 2634.118  ops/ms
Compare002Benchmark.testBatch                        KOPool001  thrpt    5    206.909 ±  102.054  ops/ms
Compare002Benchmark.testBatch             StormPot001BlazePool  thrpt    5    186.601 ±   19.670  ops/ms
Compare002Benchmark.testBatch             StormPot002QueuePool  thrpt    5    167.141 ±   22.734  ops/ms
Compare002Benchmark.testBatch                     ViburPool001  thrpt    5    158.673 ±   16.967  ops/ms
```

## 测试失败说明

以下实现在批量测试中失败：

| 实现 | 失败原因 |
|------|----------|
| ApacheCommonsPool2002SoftRefPool | NullPointerException: makeObject() 返回 null |
| BeeOp002ObjectSource | ClassCastException: 代理类转换失败 |
| Frogspawn002 | RuntimeException: NOT_AVAILABLE 策略在池耗尽时抛异常 |
| Frogspawn005 | RuntimeException: NOT_AVAILABLE 策略在池耗尽时抛异常 |