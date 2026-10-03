# Modern Java in Action – Notes

| Feature                | Java 8 Style                                                                 | Modern Java Feature                                                                 | Trading API Use Case                                                                 |
|-------------------------|------------------------------------------------------------------------------|-------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------|
| Records                | Verbose POJOs with getters, setters, equals, hashCode, toString               | `record Trade(String id, double amount)`                                            | Represent immutable trade data with minimal boilerplate                               |
| Sealed Interfaces      | Open interfaces, any class could implement                                    | `sealed interface Order permits MarketOrder, LimitOrder`                            | Restrict order types to a fixed set for safer domain modeling                         |
| Pattern Matching       | `instanceof` checks followed by manual casting                                | `if (obj instanceof Trade t) { … }`                                                 | Cleaner type checks when processing different financial instruments                  |
| Switch Expressions     | Long `switch` blocks with fall-through risks                                  | `switch (orderType) { case MARKET -> …; case LIMIT -> …; }`                         | Simplify order routing logic                                                          |
| Virtual Threads        | Heavyweight threads with Executors                                            | `Thread.ofVirtual().start(() -> processTrade())`                                    | Handle thousands of concurrent trade requests efficiently                             |
| Structured Concurrency | Manual thread management, error handling scattered                            | `try (var scope = StructuredTaskScope.ShutdownOnFailure) { … }`                     | Group related tasks (e.g., fetching market data + validating trade) with unified control |
| HTTP Client            | Legacy `HttpURLConnection`                                                    | `HttpClient.newHttpClient().send(request, BodyHandlers.ofString())`                 | Fetch live market prices or submit trades to external APIs                            |
| Modules (JPMS)         | Monolithic JARs with unclear dependencies                                     | `module trading.api { requires java.net.http; }`                                    | Enforce boundaries between core trading logic and external integrations               |
| jpackage               | Manual distribution scripts                                                   | `jpackage --name TradingApp --input out --main-jar app.jar`                         | Deliver trading tools as native desktop apps                                          |

## Kata 7: virtual threads and pinning

- Virtual threads are cheap threads managed by Java. They run on a few real "carrier" threads (about one per CPU core). When a virtual thread waits (database, HTTP call, sleep), it steps off its carrier so another task can use it.
- Part A: 10,000 tasks that each wait 100 ms. Fixed pool of 200 threads: ___ ms (about 5 s expected, because 10,000 / 200 = 50 rounds). Virtual threads: ___ ms (well under a second, because all tasks wait at the same time).
- Pinning: in Java 21, if a virtual thread waits INSIDE a `synchronized` block, it stays stuck to its carrier thread and cannot step off. Other tasks must wait for a free carrier.
- My result (12 cores, 200 tasks that each wait 100 ms): ReentrantLock about 124 ms, synchronized about 2006 ms, so about 16 times slower.
- Proof: running with `-Djdk.tracePinnedThreads=full` printed a stack trace ending in `<== monitors:1` at my `timePinned` code.
- Rule for Java 21: do not wait or do blocking I/O inside `synchronized`. Use `ReentrantLock` (lock, try, finally unlock) around slow calls. Java 24 and later removed this limitation, but this project is on Java 21.
- Virtual threads help with waiting work (database and API calls), not CPU-heavy calculation.

## Other lessons from week 1

- Money uses BigDecimal, never double. Compare BigDecimal values with `compareTo(...) == 0`, not `equals`.
- Sealed types plus a switch turn a forgotten case into a compile error instead of a production bug.
- New files need `git add .` before `git commit`. `commit -am` only picks up files Git already tracks.
- In PowerShell, quote options like `"-Dsomething.else=value"`, and run Git commands on separate lines instead of using `&&`.