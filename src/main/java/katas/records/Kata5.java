package katas.records;

import java.math.BigDecimal;
import java.util.List;

public class Kata5 {
    static String describeOld(Object o){
        if(o instanceof Trade){
            Trade t=(Trade) o;
            return "Trade " + t.symbol() +" x "+ t.qty();
        }else if (o instanceof String){
            String s=(String) o;
            return "Note "+s.strip();
        }else if (o instanceof Integer){
            Integer n=(Integer) o;
            return "Number "+ n;
        }

        return "Unknown";
    }

    public static void main(String[] args) {
        var trade=new Trade("T1", "INFY", 50, new BigDecimal("1500"),
                new BigDecimal("1560"), Trade.Status.TARGET_HIT);

        List<Object> items=List.of(trade,"  Check Stops  ",42,3.14);
        for(Object o:items){
            System.out.println(describeOld(o));
        }
    }
}
