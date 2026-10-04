package katas.sizing;

import java.math.BigDecimal;

public class SizingDemo {
    public static void main(String[] args) {
        var sizer=new PositionSizer();
        var  request = new SizingRequest(new BigDecimal("500000"), new BigDecimal("1"),
                new BigDecimal("1500"), new BigDecimal("1550"), new BigDecimal("40"));
        System.out.println(sizer.explain(sizer.size(request)));
    }
}
