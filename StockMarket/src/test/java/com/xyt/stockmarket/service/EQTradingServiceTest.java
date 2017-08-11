package com.xyt.stockmarket.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import com.xyt.stockmarket.entity.Stock;
import com.xyt.stockmarket.entity.Trade;

public class EQTradingServiceTest {

	private EQTradingService service = new EQTradingService();

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
	public void test_isInvalid_trade_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = null;
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_tradePrice_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);

		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_tradePrice_is_ZERO() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);
		when(trade.getPrice()).thenReturn(BigDecimal.ZERO);
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_tradeStock_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);
		when(trade.getPrice()).thenReturn(BigDecimal.ONE);
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_tradeQuantity_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);
		when(trade.getPrice()).thenReturn(BigDecimal.ONE);
		when(trade.getStock()).thenReturn(new Stock());
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_tradeQuantity_is_ZERO() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);
		when(trade.getPrice()).thenReturn(BigDecimal.ONE);
		when(trade.getStock()).thenReturn(new Stock());
		when(trade.getQuantity()).thenReturn(BigDecimal.ZERO);
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertTrue(result);
	}

	@Test
	public void test_isInvalid_trade_is_valid() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("isInvalid", Trade.class);
		method.setAccessible(true);
		Trade trade = mock(Trade.class);
		when(trade.getPrice()).thenReturn(BigDecimal.ONE);
		when(trade.getStock()).thenReturn(new Stock());
		when(trade.getQuantity()).thenReturn(BigDecimal.ONE);
		Object[] args = { trade };
		boolean result = (boolean) method.invoke(service, args);
		assertFalse(result);
	}

	@Test
	public void test_initialTransIndex_trade_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("initialTransIndex", Trade.class);
		method.setAccessible(true);
		Trade trade = null;
		Object[] args = { trade };
		method.invoke(service, args);
		assertTrue(trade == null);
	}

	@Test
	public void test_initialTransIndex_trade_is_valid() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("initialTransIndex", Trade.class);
		method.setAccessible(true);

		Trade trade = new Trade();
		Object[] args = { trade };

		method.invoke(service, args);
		assertTrue(trade.getTransIndex() != null);
	}

	@Test
	public void test_recordTrade_trade_is_empty() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("recordTrade", Trade.class);
		method.setAccessible(true);
		Trade trade = null;
		Object[] args = { trade };

		method.invoke(service, args);
		int tradeListSize = service.getTradeList().size();

		assertEquals(0, tradeListSize);
	}

	@Test
	public void test_recordTrade_trade_is_valid() throws Exception {
		Method method = EQTradingService.class.getDeclaredMethod("recordTrade", Trade.class);
		method.setAccessible(true);
		Trade trade = new Trade();
		Object[] args = { trade };

		method.invoke(service, args);
		int tradeListSize = service.getTradeList().size();
		assertEquals(1, tradeListSize);
		assertTrue(trade.getEntryTime() != null);
	}
}
