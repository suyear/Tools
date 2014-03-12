package org.suyear;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewFeature {
	public static void main(String[] args) throws IOException {
		try(BufferedReader reader=new BufferedReader(new InputStreamReader(new URL("http://www.oracle.com/technetwork/cn/articles/java/java7exceptions-1404186-zhs.html").openStream()))){
			String line;
			try {
				line = reader.readLine();
				SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-DD");
				Date date=format.parse(line);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
			
		}
	}
}
