package katas.threads;

import java.util.ArrayList;
import java.util.List;

public class Kata8 {
    public static void main(String[] args) {
        var symbols=new ArrayList<>(List.of("INFY", "TCS", "RELIANCE"));
        System.out.println(symbols.getFirst());
        System.out.println(symbols.getLast());
        System.out.println(symbols.reversed());
        symbols.addFirst("HDFCBANK");
        System.out.println(symbols);
    }
}
