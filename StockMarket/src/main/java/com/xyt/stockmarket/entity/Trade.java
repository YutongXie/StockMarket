package com.xyt.stockmarket.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.xyt.stockmarket.util.NumberUtil;

public class Trade {
	private TransactionType transactionType;// Buy,Sell
	private BigDecimal price;
	private BigDecimal quantity;
	private Stock stock;
	private Date entryTime;
	
	private TransactionIndex transIndex;

	public TransactionType getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public TransactionIndex getTransIndex() {
		return transIndex;
	}

	public void setTransIndex(TransactionIndex transIndex) {
		this.transIndex = transIndex;
	}

	public BigDecimal getAmount() {
		if(price == BigDecimal.ZERO || quantity == BigDecimal.ZERO)
			return BigDecimal.ZERO;
		
		return NumberUtil.format(price.multiply(quantity));
	}

	public String getTradeInfo() {
		return "Trade [transactionType=" + transactionType + ", price=" + price + ", quantity=" + quantity + ", stock="
				+ stock !=null ?stock.getInfo():"" + ", entryTime=" + entryTime + "]";
	}

	public String getTransIndexInfo() {
		return transIndex.getDetailInfo();
	}
	

}
