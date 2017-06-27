package com.sist.dao;

import org.springframework.stereotype.Component;
import java.util.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.*;

@Component
public class NewsManager {
	public List<Item> newsAlldata(int page, String find) {
		List<Item> list=new ArrayList<Item>();
		
		try {
			URL url=new URL("http://newssearch.naver.com/search.naver?where=rss&query="+URLEncoder.encode(find, "UTF-8"));
			JAXBContext jc=JAXBContext.newInstance(Rss.class);
			Unmarshaller un=jc.createUnmarshaller();
			Rss rss=(Rss) un.unmarshal(url);
			
			List<Item> temp= rss.getChannel().getItem();//50개가 들어간다.
			
			int a=0;//for문 돌아가는 위치 확인
			int b=0;//10개인지확인
			int pagecnt=(page-1)*10;
			/*
			 *  0 9
			 *  10 19
			 *  20 29
			 * 			 * 
			 * */
			//50개중 10개만 가져오기
			for (Item item : temp) {
				if (b<10 && a>=pagecnt) {
					list.add(item);
					b++;
				}				
				a++;
			}
			
			String data="";
			for (Item item : list) {
				data+=item.getDescription()+"\n";
			}
			data=data.substring(0, data.lastIndexOf("\n"));//맨마지막 \를 제외한다.
			data.replaceAll("[^가-힣 ]", "");//한글만가져온다. 한글제외하고 다 뺀다. 영문, 특수문자등을 뺀다. 공백은 지우면 안된다(단어자르기 힘들다.). 한칸띄자[^가-힣 ]
			
			FileWriter fw=new FileWriter("/home/sist/r-data/news_data");
			fw.write(data);
			fw.close();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		
		}
		
		return list;
	}
}
