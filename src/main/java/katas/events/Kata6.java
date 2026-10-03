package katas.events;
import katas.events.TradeEvent.*;

import java.math.BigDecimal;
import java.util.List;

public class Kata6 {

    static String describe(TradeEvent evt){
        return switch(evt){
            case Entry en      -> "Entered " + en.symbol() + " x" + en.qty() + " @ " + en.price();
            case PartialExit p-> "Sold "+p.qty()+" @ "+p.price();
            case StopHit s->"Stopped out @ " + s.price();
            case Cancelled c->"Cancelled "+c.tradeId();
        };
    }

    static String describeShort(TradeEvent evt){
        return switch (evt){
            case Entry(var tradeId, var symbol, var qty, var price)->tradeId + ": bought " + qty + " " + symbol;
            case PartialExit p when p.qty()>=30->"Large partial exit: " + p.qty();
            case PartialExit(var tradeId, var qty, var price)->tradeId + ": sold " + qty;
            case StopHit(String tradeId, BigDecimal price)->tradeId + ": stop at " + price;
            case Cancelled(String tradeId)->"Cancelled "+tradeId;
        };
    }

    static int remainingQty(List<TradeEvent> events){
        int qty=0;
        for(TradeEvent event:events){
            qty=switch (event){
                case Entry en->qty+en.qty();
                case PartialExit pe->qty- pe.qty();
                case StopHit s->0;
                case Cancelled c->qty;
            };
        }

        return qty;
    }

    public static void main(String[] args) {
        List<TradeEvent> events= List.of(
                new Entry("T1", "INFY", 100, new BigDecimal("1500")),
                new PartialExit("T1", 40, new BigDecimal("1540")),
                new PartialExit("T1", 10, new BigDecimal("1555")),
                new StopHit("T1", new BigDecimal("1490"))

        );

        for(TradeEvent event: events){
            System.out.println(describe(event));
        }
        System.out.println("_".repeat(30));
        for(TradeEvent event: events){
            System.out.println(describeShort(event));
        }
    }
}
