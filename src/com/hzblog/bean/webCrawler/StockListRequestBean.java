package com.hzblog.bean.webCrawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抓取股票列表json数据的请求参数封装对象
 * @author whz
 *
 */
public class StockListRequestBean {

	private String crawlPath = "/project/crawler/url";//要抓取的url地址存放路径
	private String dataPath  = "/project/crawler/data";//抓取到的数据存放路径
	private List<String> urlList = new ArrayList<String>();//要抓取的URL地址集合
	
}
