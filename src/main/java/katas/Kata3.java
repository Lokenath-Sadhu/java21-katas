package katas;

import java.math.BigDecimal;
import java.util.List;

public class Kata3 {
    public static void main(String[] args) {
        var trades = List.of(
                new Trade("T1", "INFY", 50, new BigDecimal("1500"), new BigDecimal("1560"), Trade.Status.TARGET_HIT),
                new Trade("T2", "TCS", 20, new BigDecimal("3900"), new BigDecimal("3850"), Trade.Status.STOP_HIT),
                new Trade("T3", "RELIANCE", 30, new BigDecimal("2900"), null, Trade.Status.OPEN),
                new Trade("T4", "INFY", 40, new BigDecimal("1580"), new BigDecimal("1620"), Trade.Status.TARGET_HIT));

        var t = trades.get(0);
        String jsonOld="{\n"+
                " \"id\": \"" +t.getId() + "\",\n"+
                " \"symbol\" : \""+ t.getSymbol()+" \",\n "+
                " \"pnl\" : \""+t.pnl() +"\",\n ";

        String json= """
                {
                "id":"%s",
                "symbol":"%s",
                "pnl":"%s"
                }
                """.formatted(t.getId(),t.getSymbol(),t.pnl());

        System.out.print(json);
    }
}
