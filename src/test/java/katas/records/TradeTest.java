package katas.records;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class TradeTest {
    public static Trade closed(int qty){
        return  new Trade("T1","Infy",qty, new BigDecimal("1500"),new BigDecimal("1560"), Trade.Status.TARGET_HIT);
    }

    @Test
    void pnlIsCalculatedFromEntryAndExit(){
        assertEquals(new BigDecimal("3000"),closed(50).pnl());
    }

    @Test
    void symbolIsCleanedAndUppercased(){
        assertEquals("INFY",closed(50).symbol());
    }

    @Test
    void zeroQtyIsRejected() {
        assertThrows(IllegalArgumentException.class,()->closed(0));
    }

    @Test
    void openTradeCannotHaveAnExitPrice(){
        assertThrows(IllegalArgumentException.class,()->
                new Trade("T2","TCS",10,new BigDecimal("100"),new BigDecimal(110), Trade.Status.OPEN)
                );
    }

    @Test
    void tradeWithNegativeEntryRejected(){
        assertThrows(IllegalArgumentException.class,()->new Trade("T3","TCS",10,new BigDecimal(-100),new BigDecimal(100), Trade.Status.TARGET_HIT));
    }
}
