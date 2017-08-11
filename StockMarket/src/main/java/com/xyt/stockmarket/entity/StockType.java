package com.xyt.stockmarket.entity;

public enum StockType {

	COMMON("Common"),
	PREFERRED("Preferred");
	
	private String comments;

	private StockType(String comments) {
		this.setComments(comments);
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
