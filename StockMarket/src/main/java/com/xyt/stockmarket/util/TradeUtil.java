package com.xyt.stockmarket.util;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.xyt.stockmarket.entity.Constants;
import com.xyt.stockmarket.entity.Trade;

public class TradeUtil {

	public static boolean isBookedInPastSpecificTimeRange(Trade trade) {
		Date bookTime = trade.getEntryTime();
		if (bookTime == null)
			return false;
		Calendar now = Calendar.getInstance();
		now.setTime(new Date());

		Calendar past = Calendar.getInstance();
		past.setTime(bookTime);
		past.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE);
		if (past.compareTo(now) >= 0)
			return true;
		else
			return false;
	}

	public static List<Trade> getTradesBookedInPastSpecificTimeRange(List<Trade> tradeList) {
		if(tradeList == null || tradeList.size() == 0)
			return null;
		return tradeList.stream().filter(oldTrade -> isBookedInPastSpecificTimeRange(oldTrade))
				.collect(Collectors.toList());

	}

}
