# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 1.8**

> this is the result for JVM8. If need the result for JVM9+, please switch to branch `jvm21`.

## 对比测试结果

[result](result.md)

## AI 点评

[review](review.md)

## 结论

1. 逐个使用，建议使用：`frogspawn`/`stormpot`(`blaze pool`)
2. 一批使用，建议使用：`frogspawn`/`FastPool`+`Disruptor`

