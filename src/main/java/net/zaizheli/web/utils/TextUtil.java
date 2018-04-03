package net.zaizheli.web.utils;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class TextUtil {
	
	public String removeHtml(String htmlStr) {
		if(htmlStr==null || "".equals(htmlStr)) return "";  
		      java.util.regex.Pattern pattern;    
		      java.util.regex.Matcher matcher;    
		     
		     try {
		      String regEx_remark = "<!--.+?-->";
		      String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }    
		      String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }    
		          String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式    
		          String regEx_html1 = "<[^>]+";
//		          htmlStr = htmlStr.replaceAll("\n","");
//		          htmlStr = htmlStr.replaceAll("\t","");
		          pattern=Pattern.compile(regEx_remark);//过滤注释标签
		          matcher=pattern.matcher(htmlStr);
		      htmlStr=matcher.replaceAll("");
		       
		      pattern = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);    
		      matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤script标签    

		          pattern = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤style标签    
		        
		          pattern = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		            
		          pattern = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);    
		          matcher = pattern.matcher(htmlStr);    
		          htmlStr = matcher.replaceAll(""); //过滤html标签    
		         	        
//		          htmlStr = htmlStr.trim();    
		       
		   }catch(Exception e) {
		   System.out.println("获取HTML中的text出错:");
		   e.printStackTrace();
		    }    
		    
		  return htmlStr;//返回文本字符串
		} 
//	public String removeHtml(String html){
//	    String regEx="<.+?>"; //表示标签
//	    Pattern p=Pattern.compile(regEx);
//	    Matcher m=p.matcher(html);
//	    html=m.replaceAll("");
//	    return html;
//	}
	
	public  String turn(String str) { 
		int index;
        while ((index = str.indexOf("\n")) != -1) {
        	if(index < (str.length()-1)) {
	            str = str.substring(0, index) + "<br>"  
	                    + str.substring(index + 1);
        	}
        	else {
        		str = str.substring(0, index) + "<br>";
        	}
        }  
        while ((index = str.indexOf(" ")) != -1) {  
        	if(index < (str.length()-1)) {
	            str = str.substring(0, index) + "&nbsp;&nbsp;"  
	                    + str.substring(index + 1);
        	}
        	else {
        		str = str.substring(0, index) + "&nbsp;&nbsp;";
        	}
        }  
        return str;  
    }  

}
