package com.xyt.stockmarket.entity;

import java.math.BigDecimal;

public class TransactionIndex {
	private BigDecimal dividendYield;
	private BigDecimal peRatio;
	private BigDecimal volumeWeightedPrice;
	private BigDecimal shareIndex;

	public BigDecimal getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(BigDecimal dividendYield) {
		this.dividendYield = dividendYield;
	}

	public BigDecimal getPeRatio() {
		return peRatio;
	}

	public void setPeRatio(BigDecimal peRatio) {
		this.peRatio = peRatio;
	}

	public BigDecimal getVolumeWeightedPrice() {
		return volumeWeightedPrice;
	}

	public void setVolumeWeightedPrice(BigDecimal volumeWeightedPrice) {
		this.volumeWeightedPrice = volumeWeightedPrice;
	}

	public BigDecimal getShareIndex() {
		return shareIndex;
	}

	public void setShareIndex(BigDecimal shareIndex) {
		this.shareIndex = shareIndex;
	}

	public String getDetailInfo() {
		return "TransactionIndex [dividendYield=" + dividendYield + ", peRatio=" + peRatio + ", volumeWeightedPrice="
				+ volumeWeightedPrice + ", ShareIndex=" + shareIndex + "]";
	}

}
