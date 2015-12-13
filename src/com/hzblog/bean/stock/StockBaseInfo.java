package com.hzblog.bean.stock;

/**
 * 股票基本信息
 * @author whz
 *
 */
public class StockBaseInfo {

	/**
	 * 对应数据主键ID，自增长
	 */
	private String id ;
	/**
	 * 股票代码，唯一性
	 */
	private String productId;
	/**
	 * 股票简称
	 */
	private String productName;
	/**
	 * 股票全名
	 */
	private String fullName;
	/**
	 * 股票类型：【如上证A股，深证H股】
	 */
	private String stockType;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getStockType() {
		return stockType;
	}
	public void setStockType(String stockType) {
		this.stockType = stockType;
	}
	
}
