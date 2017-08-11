package com.xyt.stockmarket.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.xyt.stockmarket.entity.Constants;
import com.xyt.stockmarket.entity.Trade;

public class TradeUtilTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test_TradeEntryTime_In_Range() {
		Calendar entryTime = Calendar.getInstance();
		entryTime.setTime(new Date());
		Random random = new Random();
		int num = Math.negateExact(random.nextInt(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE));
		entryTime.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, num);
		
		Trade trade = mock(Trade.class);
		when(trade.getEntryTime()).thenReturn(entryTime.getTime());
		assertTrue(TradeUtil.isBookedInPastSpecificTimeRange(trade));
		
	}
	
	@Test
	public void test_TradeEntryTime_In_Range_Max() {
		Calendar entryTime = Calendar.getInstance();
		entryTime.setTime(new Date());
		int num = Math.negateExact(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE);
		entryTime.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, num);
		
		Trade trade = mock(Trade.class);
		when(trade.getEntryTime()).thenReturn(entryTime.getTime());
		
		assertTrue(TradeUtil.isBookedInPastSpecificTimeRange(trade));
	}
	
	@Test
	public void test_TradeEntryTime_Not_In_Range() {
		Calendar entryTime = Calendar.getInstance();
		entryTime.setTime(new Date());
		Random random = new Random();
		// always add 1 in case random number is 0 
		int num = Math.negateExact(random.nextInt(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE) + Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE) + 1;
		entryTime.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, num);
		
		Trade trade = mock(Trade.class);
		when(trade.getEntryTime()).thenReturn(entryTime.getTime());
		
		assertFalse(TradeUtil.isBookedInPastSpecificTimeRange(trade));
	}
	@Test
	public void test_TradeEntryTime_IS_Empty() {
		Trade trade = mock(Trade.class);
		when(trade.getEntryTime()).thenReturn(null);
		
		assertFalse(TradeUtil.isBookedInPastSpecificTimeRange(trade));
	}

	
	@Test
	public void test_getTradesBookedInPastSpecificTimeRange_valid() throws Exception {

		List<Trade> tradeList = new ArrayList<Trade>();

		Calendar entryTimeInRange = Calendar.getInstance();
		entryTimeInRange.setTime(new Date());
		Random random = new Random();
		int num = Math.negateExact(random.nextInt(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE));
		entryTimeInRange.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, num);

		Trade tradeInRange = mock(Trade.class);
		when(tradeInRange.getEntryTime()).thenReturn(entryTimeInRange.getTime());

		Calendar entryTimeNoInRange = Calendar.getInstance();
		entryTimeNoInRange.setTime(new Date());
		num = Math.negateExact(random.nextInt(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE)
				+ Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE) + 1;
		entryTimeNoInRange.add(Constants.VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE, num);

		Trade tradeNotInRange = mock(Trade.class);
		when(tradeNotInRange.getEntryTime()).thenReturn(entryTimeNoInRange.getTime());
		
		tradeList.add(tradeInRange);
		tradeList.add(tradeNotInRange);

		int result = TradeUtil.getTradesBookedInPastSpecificTimeRange(tradeList).size();
		assertEquals(1, result);
	}
	
	@Test
	public void test_getTradesBookedInPastSpecificTimeRange_tradeList_is_empty() throws Exception {
		List<Trade> tradeList = new ArrayList<Trade>();
		assertEquals(null, TradeUtil.getTradesBookedInPastSpecificTimeRange(tradeList));
	}
}
