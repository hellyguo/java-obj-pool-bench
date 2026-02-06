# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 21**

> this is the result for JVM9+. If need the result for JVM8, please switch to branch `jvm8`.

## 对比测试结果

[result](result.md)

## 结论

1. 逐个使用，建议使用：`frogspawn`/`StormPot`,
2. 一批使用，建议使用：`BeeOP`
3. `NoMoreInstance`(`SweepClean`)/`LitePool` 相当于 `JavaNew`，所以快，但 `GC` 不友好
