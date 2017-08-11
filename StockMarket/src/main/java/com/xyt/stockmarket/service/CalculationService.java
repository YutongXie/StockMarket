package com.xyt.stockmarket.service;

import java.util.List;

import com.xyt.stockmarket.entity.Trade;

public interface CalculationService {

	public void calculate(List<Trade> tradeList);
}
