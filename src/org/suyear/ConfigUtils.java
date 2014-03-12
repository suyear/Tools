package org.suyear;

import java.util.ArrayList;
import java.util.List;


public class ConfigUtils {
	
	public static void main(String[] args) {
	    List<Integer> list = new ArrayList<Integer>();
        String[] lines = {"TOKEN", "1", "2",
            "TOKEN", "1", "3", "4",
            "TOKEN", "1", "2", "3"};
        int total = 0;
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].equals("TOKEN")) {
                if (i != 0) {
                    list.add(total);
                    total = 0;
                }
            } else {
                total += Integer.parseInt(lines[i]);
            }
        }
        list.add(total);
        System.out.println(list);
	}
}
