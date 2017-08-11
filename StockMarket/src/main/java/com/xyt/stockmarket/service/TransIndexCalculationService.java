package com.xyt.stockmarket.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xyt.stockmarket.entity.Stock;
import com.xyt.stockmarket.entity.StockType;
import com.xyt.stockmarket.entity.Trade;
import com.xyt.stockmarket.entity.TransactionIndex;
import com.xyt.stockmarket.util.NumberUtil;
import com.xyt.stockmarket.util.TradeUtil;

public class TransIndexCalculationService implements CalculationService{
	private static Logger logger = LoggerFactory.getLogger(TransIndexCalculationService.class);
	
	public void calculate(List<Trade> tradeList) {
		logger.info("start working on calculating transaction index..");
		Trade newTrade = tradeList.get(tradeList.size() -1);
		calculateDividend(newTrade);
		calculatePERatio(newTrade);
		calculateVolumeWeightedPrice(tradeList);
		calculateShareIndex(tradeList);
		logger.info("calculating transaction index complete..");
	}

	private void calculatePERatio(Trade trade) {
		logger.info("start calculating P/E ratio ...");
		BigDecimal price = trade.getPrice();
		if(trade.getTransIndex() == null || price == null) {
			return;
		}
		
		BigDecimal devidendYield = trade.getTransIndex().getDividendYield();
		if(devidendYield == null || BigDecimal.ZERO == devidendYield) {
			trade.getTransIndex().setPeRatio(BigDecimal.ZERO);
			return;
		} else {
			trade.getTransIndex().setPeRatio(NumberUtil.format(price.divide(devidendYield)));
		}
		logger.info("calculate P/E ratio complete...");

	}

	private void calculateDividend(Trade trade) {
		logger.info("start calculating dividend yield ...");
		TransactionIndex transIndex = trade.getTransIndex();
		if(transIndex == null)
			return;
		
		Stock stock = trade.getStock();
		if(stock == null) {
			return;
		}
		StockType stockType = stock.getType();
		if(stockType == null)
			return;
		
		BigDecimal price = trade.getPrice();
		
		switch (stockType) {
		case COMMON:
			BigDecimal lastDividend = stock.getLastDividend();
			if(lastDividend ==  null || lastDividend == BigDecimal.ZERO || price == null || BigDecimal.ZERO == price) {
				trade.getTransIndex().setDividendYield(BigDecimal.ZERO);
			} else {
				trade.getTransIndex().setDividendYield(NumberUtil.format(lastDividend.divide(price)));
			}
			break;
			
		case PREFERRED:
			BigDecimal fixedDividend = stock.getFixedDividend();
			BigDecimal parValue = stock.getParValue();
			
			if (fixedDividend == null || fixedDividend == BigDecimal.ZERO 
					|| parValue == null || parValue == BigDecimal.ZERO || price == null || BigDecimal.ZERO == price) {
				trade.getTransIndex().setDividendYield(BigDecimal.ZERO);
			} else {
				trade.getTransIndex().setDividendYield(NumberUtil.format(fixedDividend.multiply(parValue).divide(price, RoundingMode.UP)));
			}
			
			break;

		default:
			break;
		}
		
		logger.info("calculate dividenc yield complete...");

	}

	private void calculateVolumeWeightedPrice(List<Trade> tradeList) {
		logger.info("start calculating volume weighted ...");
		if(CollectionUtils.isEmpty(tradeList))
			return;
		List<Trade> pastTradeList = TradeUtil.getTradesBookedInPastSpecificTimeRange(tradeList);
		if(CollectionUtils.isEmpty(pastTradeList)) {
			return;
		}
		BigDecimal totalQty = NumberUtil.format(pastTradeList.stream().map(Trade::getQuantity).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		BigDecimal totalAmt = NumberUtil.format(pastTradeList.stream().map(Trade::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
		Trade newTrade = tradeList.get(tradeList.size() -1);
		newTrade.getTransIndex().setVolumeWeightedPrice(NumberUtil.format(totalAmt.divide(totalQty,RoundingMode.UP)));
		logger.info("calculate volume weighted complete...");

	}

	private void calculateShareIndex(List<Trade> tradeList) {
		logger.info("start calculating share index ...");
		if(CollectionUtils.isEmpty(tradeList))
			return;
		BigDecimal multiplyResult = tradeList.stream().map(Trade::getPrice).reduce(BigDecimal::multiply).orElse(BigDecimal.ZERO);
		double shareIndex = Math.pow(multiplyResult.doubleValue(), new Double(BigDecimal.ONE.intValue() / tradeList.size()));
		Trade newTrade = tradeList.get(tradeList.size() -1);
		newTrade.getTransIndex().setShareIndex(NumberUtil.format(new BigDecimal(shareIndex)));
		logger.info("calculate share index complete...");

	}

}
