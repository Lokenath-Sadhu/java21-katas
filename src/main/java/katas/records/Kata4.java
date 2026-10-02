package katas.records;

import java.math.BigDecimal;

public class Kata4 {
    public static void main(String[] args) {
        var a = new Trade("T1", "INFY", 50, new BigDecimal("1500"), new BigDecimal("1560"), Trade.Status.TARGET_HIT);
        var b = new Trade("T1", "INFY", 50, new BigDecimal("1500"), new BigDecimal("1560"), Trade.Status.TARGET_HIT);

        System.out.println(a);                           // toString for free
        System.out.println(a.symbol() + " " + a.qty());  // no "get" prefix
        System.out.println(a.equals(b));                 // true: compares values
        System.out.println(a == b);                      // false: different objects
        System.out.println(a.pnl());                     // your own method works
    }
}
