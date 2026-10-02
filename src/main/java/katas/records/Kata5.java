package katas.records;

import java.math.BigDecimal;
import java.util.List;

public class Kata5 {
    static String describeOld(Object o) {
        if (o instanceof Trade) {
            Trade t = (Trade) o;
            return "Trade " + t.symbol() + " x " + t.qty();
        } else if (o instanceof String) {
            String s = (String) o;
            return "Note " + s.strip();
        } else if (o instanceof Integer) {
            Integer n = (Integer) o;
            return "Number " + n;
        }

        return "Unknown";
    }

    static String describe(Object o) {
        if (o instanceof Trade t && t.qty() >= 100) {
            return "Big Trade " + t.symbol() + " x " + t.qty();
        } else if (o instanceof Trade t) {
            return "Trade " + t.symbol() + " x " + t.qty();
        } else if (o instanceof String s) {
            return "Note " + s.strip();
        } else if (o instanceof Integer n) {
            return "Number " + n;
        }

        return "Unknown";
    }

    static int qtyOrZero(Object o){
        if(o instanceof  Trade t) return t.qty();
        else return 0;
    }

    public static void main(String[] args) {
        var trade = new Trade("T1", "INFY", 50, new BigDecimal("1500"),
                new BigDecimal("1560"), Trade.Status.TARGET_HIT);

        var trade1 = new Trade("T2", "TCS", 150, new BigDecimal("1500"),
                new BigDecimal("1560"), Trade.Status.TARGET_HIT);

        List<Object> items = List.of(trade, "  Check Stops  ", 42, 3.14);
        for (Object o : items) {
            System.out.println(describeOld(o));
        }
        System.out.println("_".repeat(30));
        for (Object o : items) {
            System.out.println(describe(o));
        }

        for (Object o : items) {
            System.out.println(qtyOrZero(o));
        }

        List<Object> items1 = List.of(trade1, "  Check Stops  ", 42, 3.14);
        for(Object o:items1){
            System.out.println(describe(o));
        }
    }
}
