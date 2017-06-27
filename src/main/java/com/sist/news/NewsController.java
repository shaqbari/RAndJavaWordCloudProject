package com.sist.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.dao.*;
import com.sist.r.RManager;

import java.util.*;

@Controller
public class NewsController {
	@Autowired
	private NewsManager mgr;
	@Autowired
	private RManager rm;
	
	@RequestMapping("main/news_list.do")
	public String news_list(String page, String data, Model model){		
		if(page==null)page="1";
		int curpage=Integer.parseInt(page);
		if (data==null) {
			data="날씨";
		}
		List<Item> list=mgr.newsAlldata(curpage, data);
		model.addAttribute("list", list);
		rm.wordcloud();
		
		String json=rm.wordcloudData();
		model.addAttribute("json", json);
		model.addAttribute("data", data);
		model.addAttribute("page", curpage);
		
		return "main/news_list";
	}
	
	
}
