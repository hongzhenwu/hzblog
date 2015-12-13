package com.hzblog.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;

import com.hzblog.bean.webCrawler.StockListRequestBean;
import com.hzblog.task.webCrawler.StockCrawler;

/**
 * 
 * @author whz
 *
 */
@RequestMapping("/stock")
@Controller
public class StockController {

	@Resource  /**自动注入的注解  */
	private StockCrawler stockCrawler;
	
	
	public void getStockNameListByJson(StockListRequestBean requestBean) throws Exception{
		
		HttpRequesterImpl requester=(HttpRequesterImpl) stockCrawler.getHttpRequester();
       requester.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");  
       requester.addHeader("Host","query.sse.com.cn");
       requester.addHeader("Accept","*/*");
       requester.addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
       requester.addHeader("Accept-Encoding","gzip, deflate");
       requester.addHeader("Referer","ttp://www.sse.com.cn/assortment/stock/list/name/");
       stockCrawler.addSeed("http://query.sse.com.cn/commonQuery.do?jsonCallBack=jsonpCallback89539&isPagination=true&sqlId=COMMON_SSE_ZQPZ_GPLB_MCJS_SSAG_L&pageHelp.pageSize=50&pageHelp.pageNo=2&pageHelp.beginPage=1&pageHelp.endPage=5&_=1447596768497");
       stockCrawler.addSeed("http://query.sse.com.cn/commonQuery.do?jsonCallBack=jsonpCallback44701&isPagination=true&sqlId=COMMON_SSE_ZQPZ_GPLB_MCJS_SSAG_L&pageHelp.pageSize=50&pageHelp.pageNo=6&pageHelp.beginPage=6&pageHelp.endPage=10&_=1447599373570");
       stockCrawler.start(1);
	}
}
