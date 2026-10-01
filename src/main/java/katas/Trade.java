package katas;

import java.math.BigDecimal;

public class Trade {
    public enum Status { OPEN, TARGET_HIT, STOP_HIT }

    private final String id;
    private final String symbol;
    private final int qty;
    private final BigDecimal entry;
    private final BigDecimal exit;   // null while open
    private final Status status;

    public Trade(String id, String symbol, int qty,
                 BigDecimal entry, BigDecimal exit, Status status) {
        this.id = id; this.symbol = symbol; this.qty = qty;
        this.entry = entry; this.exit = exit; this.status = status;
    }
    public String getId() { return id; }
    public String getSymbol() { return symbol; }
    public int getQty() { return qty; }
    public Status getStatus() { return status; }

    public BigDecimal pnl() {
        if (exit == null) { return BigDecimal.ZERO; }
        return exit.subtract(entry).multiply(BigDecimal.valueOf(qty));
    }
}
