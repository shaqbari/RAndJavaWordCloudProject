package com.sist.r;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.Rserve.RConnection;
import org.springframework.stereotype.Component;

@Component
public class RManager {
	/* 
	 * /home/sist/springDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/RAndJavaWordCloudProject/ 
	 * */
	
	public void wordcloud() {
		try {
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			rc.voidEval("library(wordcloud)");
			rc.voidEval("data<-readLines(\"/home/sist/r-data/news_data\")");
			rc.voidEval("png(\"/home/sist/springDev/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/RAndJavaWordCloudProject/main/news.png\", width=450, height=450)");
			rc.voidEval("data2<-sapply(data, extractNoun, USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data4<-Filter(function(x){nchar(x)>=2}, data3)");
			rc.voidEval("data5<-table(data4)");
			rc.voidEval("wordcloud(names(data5), freq = data5, scale = c(5, 1), rot.per = 0.25, min.freq = 1, random.order = F, random.color = T, colors = rainbow(15))");
			rc.voidEval("dev.off()");
			rc.close();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
		}
	}
	
	
	/*                { label: 'Back to top', url: 'https://www.jqueryscript.net/tags.php?/Back%20to%20top/', target: '_top' },
*/
	
	
	public String wordcloudData(){
		String data="";		
		try {
			RConnection rc=new RConnection();
			rc.voidEval("library(rJava)");
			rc.voidEval("library(KoNLP)");
			rc.voidEval("data<-readLines(\"/home/sist/r-data/news_data\")");
			rc.voidEval("data2<-sapply(data, extractNoun, USE.NAMES = F)");
			rc.voidEval("data3<-unlist(data2)");
			rc.voidEval("data4<-Filter(function(x){nchar(x)>=2}, data3)");
			//R에서 직접 그리는 거 보다 통계를 낸뒤에 데이터 받는거 많이 쓰인다. js로뿌려주자
			REXP p=rc.eval("data4");
			String[] temp=p.asStrings();
			rc.close();
			
			JSONArray arr=new JSONArray();
			for (String s : temp) {
				JSONObject obj=new JSONObject();
				obj.put("label", s);
				obj.put("url", "#");//link는 알걸거임
				obj.put("target", "_top");
				arr.add(obj);
			}
			data=arr.toJSONString();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return data;
	}
}






