package org.suyear;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DownloadNewsTest {

	public static void main(String[] args) throws IOException {
		try {
			List<NewsFeed> newList=new ArrayList<NewsFeed>();
			NewsFeed osnews=new NewsFeed("http://dajia.qq.com/rank", "ul li a","utf-8");
			newList.add(osnews);
			HashMap<String, String> authorAndHref=new HashMap<String, String>();
			/**
			 * 获取所有作者信息
			 */
			
			List<String> hrefList=new ArrayList<String>();
			for (int j = 0; j < newList.size(); j++) {
				NewsFeed temp=newList.get(j);
				String urlStr = temp.getUrl();
				Document doc=Jsoup.parse(getContent(urlStr,temp.getCharset()));
				Elements element=doc.select(temp.getQueryPath());
				for (int i = 0; i < element.size(); i++) {
					
					String name=element.get(i).text();
					if(name==null){
						continue;
					}
					String href=element.get(i).attr("href").split("#")[0];
					if(href==null){
						continue;
					}
					if(name.length() > 3){
						continue;
					}
					
					authorAndHref.put(name, href);
					hrefList.add(href);
				}	
			}
			
			
			/**
			 * 获取所有读者文章列表
			 */
			StringBuffer sb=new StringBuffer();
			HashMap<String, String> articleHash=new HashMap<String, String>();
			System.out.println(hrefList.size());
			for (int j = 0; j < hrefList.size(); j++) {
				String url=hrefList.get(j);
				
				if(url==null){
					continue;
				}
				
				if(url.indexOf("http://")==-1){
					continue;
				}
					
				
				Document doc=Jsoup.parse(getContent(url,"utf-8"));
				if(doc==null){
					continue;
				}
				
				
				Elements element=doc.select("div.item div.pt div.txt h2.clear a");
				if(element==null){
					continue;
				}
				
				
				for (int i = 0; i < element.size(); i++) {
					String name=element.get(i).text();
					String href=element.get(i).attr("href");
					articleHash.put(name, href);
					System.out.println(name + "==" + href);
				}	
			}
			
			
			
			File f = new File("F:\\dajia.txt");
			if(f.exists()){
				System.out.print("文件存在");
			}else{
			    System.out.print("文件不存在");
			    f.createNewFile();//不存在则创建
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			System.out.println(sb.toString());
			output.write(sb.toString());
			output.close();
			System.out.println("ok");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

	public static String getContent(String urlStr,String charset) {
		BufferedReader in = null;
		StringBuffer sb = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),charset));
			String line;
			sb = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "-2";
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException ioex) {
					ioex.printStackTrace();
				}
			}
		}
		
		return sb.toString();
	}
	
	public static HtmlEmail sendMail(String email, String context, String title) {
		try {
			HtmlEmail semail = new HtmlEmail();
			semail.setFrom("suyear@qq.com", "司苏阳");
			semail.setCharset("utf-8");
			semail.setHostName("smtp.qq.com");
			semail.setAuthentication("suyear@qq.com","187078@");
			semail.setSubject(title);
			semail.setHtmlMsg(context);
			semail.addTo(email);
			return semail;
		} catch (EmailException e) {
			e.printStackTrace();
			return null;
		}
	}
}
