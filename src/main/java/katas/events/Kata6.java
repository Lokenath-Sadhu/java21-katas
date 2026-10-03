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
        };
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
    }
}
