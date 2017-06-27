package com.sist.dao;

import java.util.*;

import javax.xml.bind.annotation.XmlElement;


/**
 * @author sist
 *	xml==> Object : 언마샬
 * Object ==> xml : 마샬 => bind(JAXB)
 */
public class Item {
	private String title;
	private String description;
	private String link;
	
	
	public String getTitle() {
		return title;
	}
	
	@XmlElement
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	
	@XmlElement
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	
	@XmlElement
	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	
}
