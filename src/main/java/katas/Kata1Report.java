package katas;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Kata1Report {
    public static void main(String[] args) {
        var trades = new ArrayList<Trade>();
        trades.add(new Trade("T1", "INFY", 50, new BigDecimal("1500"), new BigDecimal("1560"), Trade.Status.TARGET_HIT));
        trades.add(new Trade("T2", "TCS", 20, new BigDecimal("3900"), new BigDecimal("3850"), Trade.Status.STOP_HIT));
        trades.add(new Trade("T3", "RELIANCE", 30, new BigDecimal("2900"), null, Trade.Status.OPEN));
        trades.add(new Trade("T4", "INFY", 40, new BigDecimal("1580"), new BigDecimal("1620"), Trade.Status.TARGET_HIT));

        var closed = trades.stream().filter(trade -> trade.getStatus()!= Trade.Status.OPEN).toList();
        var total = closed.stream().map(Trade::pnl).reduce(BigDecimal.ZERO,BigDecimal::add);

        var symbols = closed.stream().map(Trade::getSymbol).distinct().toList();


        System.out.println("Closed trades: " + closed.size());
        System.out.println("Symbols: " + symbols);
        System.out.println("Total P&L: " + total);
    }
}