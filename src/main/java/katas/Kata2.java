package katas;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class Kata2 {
    public static void main(String[] args) {
        var trades = List.of(
                new Trade("T1", "INFY", 50, new BigDecimal("1500"), new BigDecimal("1560"), Trade.Status.TARGET_HIT),
                new Trade("T2", "TCS", 20, new BigDecimal("3900"), new BigDecimal("3850"), Trade.Status.STOP_HIT),
                new Trade("T3", "RELIANCE", 30, new BigDecimal("2900"), null, Trade.Status.OPEN),
                new Trade("T4", "INFY", 40, new BigDecimal("1580"), new BigDecimal("1620"), Trade.Status.TARGET_HIT));
        Trade missing = findByIdOld(trades, "T9");
        //System.out.println(missing.getSymbol());
        //System.out.println(findById(trades,"T9").map(Trade ::getSymbol).orElseThrow(() -> new IllegalArgumentException("No Trade T9")));
        //System.out.println(findById(trades,"T3").map(Trade ::getSymbol).orElse("Not Found"));

        String raw = "  INFY \n\n TCS\n   \nRELIANCE  ";
        var cleaned=raw.lines()
                .map(String :: strip)
                .filter(line->!line.isBlank())
                .toList();
        System.out.println(cleaned);
        System.out.println("_".repeat(30));

    }

    static Trade findByIdOld(List<Trade> trades, String id) {
        for (Trade t : trades) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }

    static Optional<Trade> findById(List<Trade> trades,String id){
        return trades.stream().filter(trade -> trade.getId().equals(id)).findFirst();
    }


}
