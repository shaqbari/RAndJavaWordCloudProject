package com.sist.dao;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author sist
 * Naver 정보주는 방식
 *	<rss>
 *		<channel>
 *			<item src="">
 *				<title></title>
 *				<description></description>
 *			</item>
 *			<item>
 *				<title></title>
 *				<description></description>
 *			</item>
 *			<item>
 *				<title></title>
 *				<description></description>
 *			</item>
 *		</channel>
 *<rss>
 */
@XmlRootElement
public class Rss {
	private Channel channel=new Channel();

	public Channel getChannel() {
		return channel;
	}
	
	@XmlElement(name="channel")//name이 변수와 같으면 생략가능 변수 못잡을때( <c:forEach>같은거)는 꼭 써줘야 한다. 쓰는게 원래 원칙
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	
	
}
