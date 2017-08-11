package com.xyt.stockmarket.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyt.stockmarket.entity.Trade;
import com.xyt.stockmarket.entity.TransactionIndex;

public class EQTradingService implements TradingService{
	private static Logger logger = LoggerFactory.getLogger(TransIndexCalculationService.class);
	
	private List<Trade> tradeList = new ArrayList<Trade>();

	private TransIndexCalculationService transIndexCalService;

	public TransIndexCalculationService getTransIndexCalService() {
		return transIndexCalService;
	}

	public void setTransIndexCalService(TransIndexCalculationService transIndexCalService) {
		this.transIndexCalService = transIndexCalService;
	}

	public List<Trade> getTradeList() {
		return tradeList;
	}

	public synchronized void bookTrade(Trade trade) {
		if (isInvalid(trade))
			return;
		recordTrade(trade);
		initialTransIndex(trade);
		calculateIndex();
		outputTransIndex(trade);
	}

	private void calculateIndex() {
		transIndexCalService.calculate(tradeList);
	}

	private void outputTransIndex(Trade trade) {
		logger.info("Transaction Index info after booking ->" + trade.getTransIndexInfo());
	}

	private boolean isInvalid(Trade trade) {
		if (trade == null || trade.getPrice() == null 
				|| trade.getPrice() == BigDecimal.ZERO || trade.getStock() == null
				|| trade.getQuantity() == null || trade.getQuantity() == BigDecimal.ZERO) {
			logger.warn("Invalid trade, no further process...");
			return true;
		}
		return false;
	}

	private void initialTransIndex(Trade trade) {
		if(trade == null)
			return;
		TransactionIndex transIndex = new TransactionIndex();
		trade.setTransIndex(transIndex);
	}

	private void recordTrade(Trade trade) {
		if (trade != null) {
			Calendar cal = Calendar.getInstance();
			trade.setEntryTime(cal.getTime());
			tradeList.add(trade);
			logger.info("Record trade ->{}", trade.getTradeInfo());
		} else {
			logger.error("invalid trade, will not do record...");	
		}
	}

}
