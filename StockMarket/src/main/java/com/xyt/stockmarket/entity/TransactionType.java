package com.xyt.stockmarket.entity;

public enum TransactionType {

	BUY("Buy"), SELL("Sell");

	private String comments;
	private TransactionType(String comments) {
		this.setComments(comments);
	}


	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

}
