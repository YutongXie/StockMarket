package com.xyt.stockmarket.server;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xyt.stockmarket.entity.Stock;
import com.xyt.stockmarket.entity.StockType;
import com.xyt.stockmarket.entity.Trade;
import com.xyt.stockmarket.service.EQTradingService;


public class StockMarketServer {
	private static Logger logger = LoggerFactory.getLogger(StockMarketServer.class);
	
	public static void main(String[] args) {
		final StockMarketServer server = new StockMarketServer();
		
		try {
			server.startServer();
		} catch (Exception e) {
			logger.error("Stock market Server start failed:", e);
		}
	}
	
	public void startServer() throws Exception {
		loadAppConfiguration();
			
	}
	
	private void loadAppConfiguration() throws Exception{
		logger.info("Loading application configuratioin");
		
		ApplicationContext factory=new ClassPathXmlApplicationContext("classpath:applicationContext.xml"); 
		EQTradingService eqTradingService = factory.getBean(EQTradingService.class);
		Trade trade1 = new Trade();
		trade1.setPrice(new BigDecimal(15));
		trade1.setQuantity(new BigDecimal(200));
		Stock stock = new Stock();
		stock.setType(StockType.COMMON);
		stock.setSymbol("TEA");
		trade1.setStock(stock);
		stock.setLastDividend(BigDecimal.ZERO);
		
		eqTradingService.bookTrade(trade1);
		
		Trade trade2 = new Trade();
		trade2.setPrice(new BigDecimal(20));
		trade2.setQuantity(new BigDecimal(140));
		Stock stock2 = new Stock();
		stock2.setType(StockType.PREFERRED);
		stock2.setSymbol("GIN");
		trade2.setStock(stock);
		stock2.setParValue(new BigDecimal("250"));
		stock2.setFixedDividend(new BigDecimal("0.2"));
		eqTradingService.bookTrade(trade2);
		
		
	}
	
}
