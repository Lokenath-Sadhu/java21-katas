package katas.events;

import java.math.BigDecimal;

public sealed interface TradeEvent permits TradeEvent.Entry, TradeEvent.PartialExit, TradeEvent.StopHit ,TradeEvent.Cancelled{

    record Entry(String tradeId, String symbol, int qty, BigDecimal price) implements TradeEvent{}
    record PartialExit(String tradeId, int qty ,BigDecimal price) implements TradeEvent{}
    record StopHit(String tradeId, BigDecimal price) implements TradeEvent { }
    record Cancelled(String tradeId) implements TradeEvent{}
}
