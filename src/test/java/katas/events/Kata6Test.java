package katas.events;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Kata6Test {
    private final List<TradeEvent> events=List.of(
            new TradeEvent.Entry("T1","INFY",100,new BigDecimal("1500")),
            new TradeEvent.PartialExit("T1",40,new BigDecimal("1540")),
            new TradeEvent.PartialExit("T1",10,new BigDecimal("1555")),
            new TradeEvent.StopHit("T1",new BigDecimal("1490"))
    );

    @Test
     void remainingAfterTwoPartialExits(){
        assertEquals(50,Kata6.remainingQty(events.subList(0,3)));
    }

    @Test
    void stopHitClosesTheWholePosition(){
        assertEquals(0,Kata6.remainingQty(events));
    }

}
