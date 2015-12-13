package com.hzblog.task.webCrawler;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

/**
 * 股票列表数据抓取类
 * @author whz
 *
 */
@Component
public class StockCrawler  extends DeepCrawler{

	/**默认的数据存放路径*/
	private String dataPath  = "/project/crawler/data";
	/**默认的文件名[带有后缀的文件名称]*/
	private String fileName =  "stockList.txt";
	
	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @param crawlPath 抓取到的URL地址存放的路径,【说明：webcollector2.0内置Berkeley DB管理URL,而berkelyDB数据存放的
	 * 路径就是crawlPath指定的】
	 */
	public StockCrawler(String crawlPath) {
		super(crawlPath);
	}

	/**
	 * 这个方法主要用于访问页面page,并从页面中抽取页面中发现的需要爬取的URL返回，如果不需要从给定页面中发现新的链接，返回null
	 * 这个类我是用来抓取股票列表的json数据，不需要爬取URL，并且这里page是json数据。
	 * @param page
	 * @return
	 */
	@Override
	public Links visitAndGetNextLinks(Page page) {
		String jsonStr=page.getHtml();
        String newjson= jsonStr.substring(jsonStr.indexOf("{"), jsonStr.length());
        JSONObject json=new JSONObject(newjson);
       JSONArray  stockArr = json.getJSONArray("result");
       for (int i = 0; i < stockArr.length(); i++) {
    	   //保存数据到文件
           File outputFile = new File(dataPath, fileName);
           StringBuilder sb = new StringBuilder();
           JSONObject stockJson = stockArr.getJSONObject(i);
           sb.append(stockJson.getString("PRODUCTID")).append("|")
           .append(stockJson.getString("PRODUCTNAME")).append("|")
           .append(stockJson.getString("FULLNAME")).append("|")
           .append("上证A股");
           String result = sb.toString();
		   try {
				FileUtils.writeFile(outputFile, result.getBytes("utf-8"));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
		return null;
	}

}
