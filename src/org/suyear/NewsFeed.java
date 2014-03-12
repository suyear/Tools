package org.suyear;

public class NewsFeed {
	private String url;
	private String queryPath;
	private String charset;
	public NewsFeed(String url, String queryPath,String charset) {
		this.url = url;
		this.queryPath = queryPath;
		this.charset = charset;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getQueryPath() {
		return queryPath;
	}
	public void setQueryPath(String queryPath) {
		this.queryPath = queryPath;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	
	
}
