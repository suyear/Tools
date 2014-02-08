package org.suyear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class OsChinaNews {

	public static void main(String[] args) throws IOException {
		String urlStr = "http://www.qiushibaike.com/8hr/page/2?s=4639482";
		Document doc=Jsoup.parse(getContent(urlStr));
		Elements element=doc.select(".block .content");
		for (int i = 0; i < element.size(); i++) {
			System.out.println(element.get(i).text());	
		}
		
	}

	public static String getContent(String urlStr) {
		BufferedReader in = null;
		StringBuffer sb = null;
		try {
			URL url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
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
}
