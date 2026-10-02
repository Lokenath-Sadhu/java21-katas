package katas.records;


import java.math.BigDecimal;

public record Trade(String id, String symbol, int qty, BigDecimal entry, BigDecimal exit, Status status) {
    public enum Status {OPEN, TARGET_HIT, STOP_HIT, CANCELLED}

    public Trade {
        if (id == null || id.isBlank())
            throw new IllegalArgumentException("id is required");
        if (symbol == null || symbol.isBlank())
            throw new IllegalArgumentException("symbol is required");
        if (qty <= 0)
            throw new IllegalArgumentException("qty must be > 0");
        if (entry == null || entry.signum() <= 0)
            throw new IllegalArgumentException("entry must be > 0");
        if (status == null)
            throw new IllegalArgumentException("status is required");
        if ((status == Status.OPEN) != (exit == null))
            throw new IllegalArgumentException("exit must be set exactly when trade is closed");
        symbol = symbol.strip().toUpperCase();   // clean the input
    }

    public BigDecimal pnl() {
        if (exit == null) {
            return BigDecimal.ZERO;
        }
        return exit.subtract(entry).multiply(BigDecimal.valueOf(qty));
    }
}
