package org.suyear;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class DownloadNews {

	public static void main(String[] args) throws IOException {
		try {
			List<NewsFeed> newList=new ArrayList<NewsFeed>();
			NewsFeed osnews=new NewsFeed("http://www.oschina.net/news/", "ul.List li h2 a","utf-8");
			NewsFeed kr=new NewsFeed("http://www.36kr.net/product/list/", "span.inview","utf-8");
			NewsFeed csdn_geek=new NewsFeed("http://geek.csdn.net/", "div.content_info h4 a","utf-8");
			
			newList.add(osnews);
			newList.add(kr);
			newList.add(csdn_geek);
			
			StringBuffer sb=new StringBuffer();
			for (int j = 0; j < newList.size(); j++) {
				NewsFeed temp=newList.get(j);
				String urlStr = temp.getUrl();
				Document doc=Jsoup.parse(getContent(urlStr,temp.getCharset()));
				Elements element=doc.select(temp.getQueryPath());
				for (int i = 0; i < element.size(); i++) {
					/*String values=element.get(i).text();
					System.out.println(values);*/
					sb.append(element.get(i).text()+"\n");
				}	
			}
			
			
			File f = new File("F:\\123.txt");
			if(f.exists()){
				System.out.print("文件存在");
			}else{
			    System.out.print("文件不存在");
			    f.createNewFile();//不存在则创建
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(sb.toString());
			output.close();
			
			HtmlEmail semail=sendMail("suyear@kindle.cn", "测试", "测试");
			semail.embed(f);
			semail.send();
			System.out.println("ok");
		} catch (EmailException e) {
			e.printStackTrace();
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
