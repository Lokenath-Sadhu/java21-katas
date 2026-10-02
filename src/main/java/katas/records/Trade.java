package katas.records;


import java.math.BigDecimal;

public record Trade(String id, String symbol, int qty, BigDecimal entry, BigDecimal exit, Status status) {
    public enum Status { OPEN, TARGET_HIT, STOP_HIT , CANCELLED}

    public BigDecimal pnl() {
        if (exit == null) { return BigDecimal.ZERO; }
        return exit.subtract(entry).multiply(BigDecimal.valueOf(qty));
    }
}
