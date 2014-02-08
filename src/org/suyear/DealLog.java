package org.suyear;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class DealLog {

	public static void main(String[] args) throws IOException {
		dealLog("F:\\v4_error.log");
	}

	public static void dealLog(String string) throws IOException {
		List<String> str = IOUtils.readLines(new FileInputStream(new File(
				string)));
		List<String> real = new ArrayList<String>();
		for (int i = 0; i < str.size(); i++) {
			String log = str.get(i);

			if (IsNullOrEmpty(log)) {
				continue;
			}
			if (real.size() == 0) {
				real.add(log);
				continue;
			}

			if (!real.contains(log)) {
				real.add(log);
			}

		}
		for (int i = 0; i < real.size(); i++) {
			System.out.println(real.get(i));
		}
	}

	public static boolean IsNullOrEmpty(String value) {
		return value == null || "".equals(value.trim());
	}

}
