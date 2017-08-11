package com.xyt.stockmarket.util;

import java.math.BigDecimal;

import com.xyt.stockmarket.entity.Constants;

public class NumberUtil {
	
	public static BigDecimal format(BigDecimal input) {
		if(input == null) 
			return null;
		
		if(input == BigDecimal.ZERO) {
			return input;
		}
		return input.setScale(Constants.NUMBER_SCALE, Constants.NUMBER_ROUNDING_MODE);
	}

}
