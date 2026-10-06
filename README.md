# java21-katas

Week 1 of my Java 21 / Spring Boot roadmap: modern Java through small katas, finishing with a position sizer.

## What's inside
- `katas/` - Java 8 style refactored to modern Java (streams, Optional, text blocks, switch expressions)
- `katas/records/` - records with validation, instanceof patterns
- `katas/events/` - sealed `TradeEvent` with pattern switch
- `katas/threads/` - virtual threads vs a thread pool, and the pinning pitfall
- `katas/sizing/` - **PositionSizer**: capital, risk %, entry, stop and brokerage in; quantity or a clear rejection out

## PositionSizer rules
1. Risk amount = capital x risk % (capped at 5% as a sanity check)
2. Brokerage is subtracted from the risk amount
3. Quantity = (risk amount - brokerage) / (entry - stop), rounded DOWN
4. Quantity is limited to what capital can buy
5. Long trades only; the stop must be below entry

Example: capital 500000, risk 1%, entry 1500, stop 1450, brokerage 40 -> 99 shares, max loss 4990.

## Design notes
- **BigDecimal, not double:** double cannot store many decimal values exactly
  (0.1 + 0.2 gives 0.30000000000000004), so tiny errors creep into money
  calculations. BigDecimal keeps the exact decimal value, and I choose the
  rounding myself.
- **SizingResult is a sealed type:** only `Sized` and `Rejected` are allowed to
  implement it. Together with records and a switch, the compiler checks that
  every case is handled, so a forgotten case becomes a compile error instead of
  a production bug.
- **Quantity is always rounded down:** this way the worst-case loss (including
  brokerage) never goes above the risk amount I calculated.
## Run the tests
Open the project in IntelliJ and run all tests (Java 21).