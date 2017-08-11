package com.xyt.stockmarket.entity;

import java.math.RoundingMode;
import java.util.Calendar;
//global setup, should be configure in DB or property file
public class Constants {
	
	public static final int VOLUME_WEIGHTED_PRICE_CALCULATE_SCOPE = 15;
	public static final int VOLUME_WEIGHTED_PRICE_CALCULATE_SCALE = Calendar.MINUTE;
	
	public static final RoundingMode NUMBER_ROUNDING_MODE = RoundingMode.UP;
	public static final int NUMBER_SCALE = 2;
	

}
