# java-obj-pool-bench

try to compare all available object pool libraries, under **JVM 25**

> this is the result for JVM25. If need the result for JVM8, please switch to branch `itc/jvm8`.

## 对比测试结果

[result](result.md)

## AI 点评

- [java-review-20260206-001](docs/review/java-review-20260206-001.md)
- [java-review-20260324-001](docs/review/java-review-20260324-001.md)

## 结论

### 单个操作（单取单还）场景

| 排名 | 实现 | 吞吐量 | 说明 |
|------|------|--------|------|
| 🥇 | **frogspawn** | **~1100 ops/us** | 比直接 new 快 2 倍！ |
| 🥈 | LitePool | 282 ops/us | - |
| 🥉 | Stormpot BlazePool | 291 ops/us | 零 GC 分配 |

**推荐**: `frogspawn` - 在高并发单操作场景表现极为出色

### 批量操作场景

| 排名 | 实现 | 吞吐量 | 说明 |
|------|------|--------|------|
| 🥇 | **Java new** | **20369 ops/ms** | 无同步开销，JVM 优化 |
| 🥈 | frogspawn | ~850 ops/ms | - |
| 🥉 | FastPool+Disruptor | 778 ops/ms | - |

**推荐**: 直接 `new` - 批量场景下无竞争，直接创建对象最快

### 综合建议

| 场景 | 推荐 | 原因 |
|------|------|------|
| **高并发单操作** | frogspawn | 无锁设计，吞吐量极高 |
| **批量操作** | 直接 new | 零同步成本，JVM 优化 |
| **连接池等重资源** | 对象池 | 对象创建成本高，需复用 |

### frogspawn 0.6 策略选择

| 策略 | 实现 | 适用场景 |
|------|------|----------|
| 默认配置 | Frogspawn001 | 通用场景，推荐 |
| 最高性能 | Frogspawn005/006 | 单操作高性能 |
| 批量场景 | Frogspawn007/008 | 批量吞吐量高 |
| NOT_AVAILABLE | Frogspawn002/005 | 严格资源控制，池耗尽时抛异常 |