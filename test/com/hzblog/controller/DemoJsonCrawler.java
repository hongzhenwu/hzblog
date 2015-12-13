package com.hzblog.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.edu.hfut.dmic.webcollector.crawler.DeepCrawler;
import cn.edu.hfut.dmic.webcollector.model.Links;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.HttpRequesterImpl;
import cn.edu.hfut.dmic.webcollector.util.FileUtils;

/**爬取JSON的例子
 * 例如我们爬取http://www.brieftools.info/proxy/test/test1.json
 * 和http://www.brieftools.info/proxy/test/test2.json
 * 
 * 很多JSON爬取必须要设置Cookie、User-Agent
 * 有时候还需要使用POST方法
 * 
 * @author hu
 */
public class DemoJsonCrawler extends DeepCrawler{
    
   

    public DemoJsonCrawler(String crawlPath) {
        super(crawlPath);
        HttpRequesterImpl requester=(HttpRequesterImpl) this.getHttpRequester();
        
        //requester.setMethod("POST");
       // requester.setCookie("你的cookie");
       requester.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:44.0) Gecko/20100101 Firefox/44.0");  
       requester.addHeader("Host","query.sse.com.cn");
       requester.addHeader("Accept","*/*");
       requester.addHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
       requester.addHeader("Accept-Encoding","gzip, deflate");
       requester.addHeader("Referer","ttp://www.sse.com.cn/assortment/stock/list/name/");
       
//       Connection: keep-alive
        
    }

    @Override
    public Links visitAndGetNextLinks(Page page) {
        String jsonStr=page.getHtml();
        System.out.println(jsonStr);
        String newjson= jsonStr.substring(jsonStr.indexOf("{"), jsonStr.length());
        JSONObject json=new JSONObject(newjson);
        String dataPath  = "/project/crawler/data";
        String fileName =  "stockList.txt";
       JSONArray  stockArr = json.getJSONArray("result");
       for (int i = 0; i < stockArr.length(); i++) {
    	   //保存数据到文件
           File outputFile = new File(dataPath, fileName);
           if(!outputFile.exists()){
        	   outputFile.mkdirs();
           }
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
    
    public static void main(String[] args) throws Exception{
        
        DemoJsonCrawler crawler=new DemoJsonCrawler("/home/hu/data/wb");
        crawler.addSeed("http://query.sse.com.cn/commonQuery.do?jsonCallBack=jsonpCallback89539&isPagination=true&sqlId=COMMON_SSE_ZQPZ_GPLB_MCJS_SSAG_L&pageHelp.pageSize=50&pageHelp.pageNo=2&pageHelp.beginPage=1&pageHelp.endPage=5&_=1447596768497");
        crawler.addSeed("http://query.sse.com.cn/commonQuery.do?jsonCallBack=jsonpCallback44701&isPagination=true&sqlId=COMMON_SSE_ZQPZ_GPLB_MCJS_SSAG_L&pageHelp.pageSize=50&pageHelp.pageNo=6&pageHelp.beginPage=6&pageHelp.endPage=10&_=1447599373570");
        crawler.start(1);
        System.out.println(crawler.status);
    }
    
}