package org.suyear;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONDemo {
	public static void main(String[] args) {
		JSONObject obj=new JSONObject();
		obj.put("tom", "23");
		obj.put("dad", "50");
		obj.put("mom", "48");
		System.out.println("通过构造器的方式创建的JSONObject对象："+obj);
		
		Map<String,String> map=new LinkedHashMap<String,String>();
		map.put("tom","23");
		map.put("dad","50");
		map.put("mom", "48");
		System.out.println("通过fromObject方法将map对象转换为JSONObject对象："+JSONObject.fromObject(map));
		
		
		JSONArray arr=new JSONArray();
		arr.add(0, "tom");
		arr.add(1, "dad");
		arr.add(2, "mom");
		System.out.println("通过构造器的方式创建的JSONArray："+arr);
		
		ArrayList<String> list=new ArrayList<String>();
		list.add("tom");
		list.add("dad");
		list.add("mom");
		System.out.println("通过fromObject方法将Arraylist对象转换为JSONArray对象："+JSONArray.fromObject(list));
		
		
		System.out.println("将HashMap对象通过fromObject方法转换为JSONArray对象"+JSONArray.fromObject(map));
		
		String str="{\"derek\":23,\"dad\":49,\"mom\":45}";    
	    System.out.println("解析之后的JSON对象："+JSONObject.fromObject(str));    
	           
	       //遍历输出    
	    @SuppressWarnings("unchecked")
		Iterator<String> it=obj.keys();    
	    while(it.hasNext()){    
	    	String key=it.next();    
	        System.out.println(key+":"+obj.get(key));    
	    }    
	}
}
