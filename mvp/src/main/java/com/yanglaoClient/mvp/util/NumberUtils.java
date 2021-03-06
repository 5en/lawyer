package com.yanglaoClient.mvp.util;

import java.text.DecimalFormat;

public class NumberUtils {

	public static String keep2decimal(Object object) {
		String str = "0.00";
		try {
			if (object instanceof String) {
				str = double2((String) object);
			} else if (object instanceof Number) {
				str = keep2decimal((Number)object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String keep2decimal(Number num) {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		String str = "0.00";
		try {
			str = df.format(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String double2(double d) {
		DecimalFormat df = new DecimalFormat("###,##0.00");
		String str = "0.00";
		try {
			str = df.format(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String double2(String d) {
		double num = 0d;
		try {
			num = Double.parseDouble(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		DecimalFormat df = new DecimalFormat("###,##0.00");
		String str = "0.00";
		try {
			str = df.format(num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
