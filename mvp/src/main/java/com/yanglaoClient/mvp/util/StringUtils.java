package com.yanglaoClient.mvp.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

public class StringUtils {

	public static String getPercentString(String per) {
		if (StringUtils.isValid(per)) {
			if (per.endsWith("%")) {
				return per;
			} else {
				return per + "%";
			}
		} else {
			return "";
		}
	}

	public static Spanned getUnderLineSpanned(String str) {
		return Html.fromHtml("<u>" + str + "</u>");
	}

	public static Spanned hyperlinkStyled(CharSequence str) {
		return Html.fromHtml("<font color=\"#0094d9\"><u>" + str + "</u></font>");
	}

	/**
	 * 解析地址信息
	 */
	public static ArrayList<String> parsePath(String path) {
		ArrayList<String> resut = null;
		if (StringUtils.isValid(path)) {
			String[] strs = path.split(";");
			if (strs != null && strs.length > 0) {
				resut = new ArrayList<String>();
				for (int i = 0; i < strs.length; i++) {
					resut.add(strs[i]);
				}
			}
		}
		return resut;
	}

	/**
	 * 构造地址信息
	 */
	public static String getPathString(ArrayList<String> str) {

		String result = "";
		StringBuilder sb = new StringBuilder();
		if (str != null) {
			for (String s : str) {
				sb.append(s + ";");
			}
			int index = sb.lastIndexOf(";");
			if (index != -1) {
				result = sb.substring(0, index);
			} else {
				result = sb.toString();
			}
		}
		return result;
	}

	public static String buildTimeDate(String date, String time) {
		return date + " " + time;
	}

	/**
	 * 评分
	 */
	public static String buildRatingText(float rate) {
		DecimalFormat nf = new DecimalFormat("#.#");
		return nf.format(rate) + "分";
	}

	/**
	 * 字符串非空检验
	 * 
	 * @param str
	 * @return true for非空<br>
	 *         false 字符串为空
	 */
	public static boolean isValid(String str) {
		return !(str == null || str.trim().length() == 0 || "".equals(str));
	}

	/**
	 * 判断两个字符串是否相等(都为空时认为相等)
	 * 
	 * @param arg1
	 * @param arg2
	 * @return
	 */
	public static boolean isEqual(String arg1, String arg2) {
		if (arg1 != null && arg2 != null) {
			return arg1.equals(arg2);
		} else return arg1 == null && arg2 == null;
	}

	/**
	 * 格式化浮点数据(取两位小数,四舍五入)
	 * 
	 * @param d
	 * @return
	 */
	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat("0.00");// #.00 无法格式化0.005
		String fd = df.format(d);
		return fd;
	}

	/**
	 * 获取视图文本
	 * 
	 * @param v
	 * @return
	 */
	public static String getViewText(TextView v) {
		return v.getText().toString().trim();
	}

	/**
	 * 将号码字符串拆分成号码数组
	 * 
	 * @param numStr
	 * @return
	 */
	public static String[] splitTelNum(String numStr) {
		// 去掉连字符
		String result = numStr.trim().replaceAll("-", "");
		String[] sa = result.split(",");
		return sa;
	}

	/**
	 * 拼装收件人字符串
	 * 
	 * @param receivers
	 * @return
	 */
	public static String buildRcvrStr(ArrayList<String> receivers) {
		StringBuilder receiversBuilder = new StringBuilder();
		if (receivers != null && receivers.size() > 0) {
			for (int i = 0; i < receivers.size(); i++) {
				receiversBuilder.append(receivers.get(i) + ",");
			}
			receiversBuilder.deleteCharAt(receiversBuilder.length() - 1);// 去掉尾部","
		}
		return receiversBuilder.toString();
	}

	/**
	 * 拼装收件人字符串
	 * 
	 * @param receivers
	 * @return
	 */
	public static String buildRcvrStr(String[] receivers) {
		StringBuilder receiversBuilder = new StringBuilder();
		if (receivers != null && receivers.length > 0) {
			for (int i = 0; i < receivers.length; i++) {
				receiversBuilder.append(receivers[i] + ",");
			}
			receiversBuilder.deleteCharAt(receiversBuilder.length() - 1);// 去掉尾部","
		}
		return receiversBuilder.toString();
	}

	/**
	 * czh
	 * 
	 * @param oldStr
	 * @return
	 */
	public static String getOverCapHint(String oldStr) {

		StringBuffer sb = new StringBuffer();

		String[] OverCapHintString = oldStr.split("<br/>");
		if (OverCapHintString != null && OverCapHintString.length > 0) {

			for (int i = 0; i < OverCapHintString.length; i++) {

				if (OverCapHintString[i].split("\\|").length >= 2) {

					sb.append("(");
					sb.append(i + 1);
					sb.append(")");
					sb.append(OverCapHintString[i].split("\\|")[1]);
					sb.append("<br>");
				}
			}

			sb.append("\n");
		}

		return sb.toString();

	}

	public static String getDuplicateCheckInfo(String oldStr) {

		StringBuffer sb = new StringBuffer();
		sb.append("该申请中某些明细可能已经在其它申请中提交过:\n\n");

		String[] duplicateCheckInfoStr = oldStr.split("<br/>");

		if (duplicateCheckInfoStr != null && duplicateCheckInfoStr.length > 0) {

			for (int i = 0; i < duplicateCheckInfoStr.length; i++) {

				String[] duplicateDetailStr = duplicateCheckInfoStr[i].split("\\|");

				if (duplicateDetailStr != null && duplicateDetailStr.length == 4) {

					sb.append("申请单号: ");
					sb.append(duplicateDetailStr[0]);
					sb.append(" 发生日期: ");
					sb.append(duplicateDetailStr[1]);
					sb.append(" 费用类型： ");
					sb.append(duplicateDetailStr[2]);
					sb.append("\n");

				}

			}

			sb.append("\n");

		}

		return sb.toString();

	}

	/**
	 * 把一个输入流读成字符串
	 * 
	 * @param is
	 * @param encoding
	 * @return
	 */
	public static String streamToString(InputStream is, String encoding) {
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = null;
		BufferedReader bufferedreader = null;
		String str = null;
		try {
			isr = new InputStreamReader(is, encoding);
			bufferedreader = new BufferedReader(isr, 4096);
			while ((str = bufferedreader.readLine()) != null) {
				sb.append(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedreader != null) {
					bufferedreader.close();// 关闭顺序从外向内
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				if (isr != null) {
					isr.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	/**
	 * 从文件浏览器返回的uri中提取文件保存路径
	 * 
	 * @param uri
	 * @return
	 */
	public static String getPathFromUri(Uri uri, Context context) {
		String path = "";
		if (uri != null) {
			String sUri = Uri.decode(uri.toString());
			if (sUri.startsWith(FILE_PREFIX)) {
				path = sUri.substring(FILE_PREFIX.length());
			} else if (sUri.startsWith(CONTENT_PREFIX)) {
				ContentResolver resolver = context.getContentResolver();
				Cursor cursor = resolver.query(uri, null, null, null, null);
				if (cursor.moveToFirst()) {
					path = cursor.getString(cursor.getColumnIndex("_data"));
				}
			}
		}
		return path;
	}

	/**
	 * 获取小图路径
	 */
	public static String getMinPicPath(String bigpath) {
		if (StringUtils.isValid(bigpath)) {
			return bigpath.replaceAll("big_pic", "min_pic");
		}
		return "";
	}

	/**
	 * 判断字符长度
	 * 
	 * @param string
	 * @return
	 */
	public static int getUTF8Length(String string) {

		int strLenth = 0;
		try {

			strLenth = string.toString().getBytes("UTF-8").length;

		} catch (UnsupportedEncodingException e) {

			strLenth = string.toString().length();

		}

		return strLenth;
	}

	/**
	 * 判断是不是一个合法的电子邮件地址
	 * 
	 * @param email
	 * @return boolean
	 * @author Xin
	 */
	private final static Pattern emailer = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

	public static boolean isEmail(String email) {
		if (email == null || email.trim().length() == 0)
			return false;
		return emailer.matcher(email).matches();
	}

	/**
	 * 
	 * @param str
	 * @return String
	 * @author Xin
	 */
	public static String trimmy(String str) {
		String dest = "";
		if (str != null) {
			str = str.replaceAll("-", "");
			str = str.replaceAll("\\+", "");
			dest = str;
		}
		return dest;
	}

	/**
	 * 判断输入的字符串是否为纯汉字
	 * 
	 * @param str
	 *            传入的字符窜
	 * @return 如果是纯汉字返回true,否则返回false
	 */
	public static boolean isChinese(String str) {
		Pattern pattern = Pattern.compile("[\u0391-\uFFE5]+$");
		return pattern.matcher(str).matches();
	}

	public static boolean isEnglishName(String str) {
		Pattern pattern = Pattern.compile("[a-zA-Z ]+");
		return pattern.matcher(str).matches();
	}

	private static final String FILE_PREFIX = "file://";
	private static final String CONTENT_PREFIX = "content://";

	/**
	 * 获得百度静态地图URl地址
	 * 
	 * @param areaPoint
	 * @param location
	 * @param act
	 * @return
	 */
	public static String getLocationBaiduMapUrl(String areaPoint, String location, Activity act) {
		String center = "";
		if (areaPoint == null || areaPoint.equals(""))
			try {
				center = URLEncoder.encode(location, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
			}
		else
			center = areaPoint;
		DisplayMetrics dm = new DisplayMetrics();
		act.getWindowManager().getDefaultDisplay().getMetrics(dm);
		int screenWidth = dm.widthPixels;
		StringBuffer url = new StringBuffer();
		url.append("http://api.map.baidu.com/staticimage?");
		url.append("width=").append(screenWidth);
		url.append("&height=").append("300");
		url.append("&zoom=13");
		url.append("&markerStyles=l");

		url.append("&center=").append(center);
		url.append("&markers=").append(center);
		String u = url.toString();
		return u;
	}

	public static String transformDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date();
		try {
			d = sdf.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (d.getYear() + 1900) + "年";
	}

	/**
	 * 字符串替换
	 * 
	 * @param 字符串
	 * @param 要替换的原字符串
	 * @param 要替换的新字符串
	 * @return
	 */
	public static String replaceEach(String text, String[] searchList, String[] replacementList) {
		return replaceEach(text, searchList, replacementList, false, 0);
	}

	private static String replaceEach(String text, String[] searchList, String[] replacementList, boolean repeat,
			int timeToLive) {

		// mchyzer Performance note: This creates very few new objects (one
		// major goal)
		// let me know if there are performance requests, we can create a
		// harness to measure

		if (text == null || text.length() == 0 || searchList == null || searchList.length == 0
				|| replacementList == null || replacementList.length == 0) {
			return text;
		}

		// if recursing, this shouldnt be less than 0
		if (timeToLive < 0) {
			throw new IllegalStateException("TimeToLive of " + timeToLive + " is less than 0: " + text);
		}

		int searchLength = searchList.length;
		int replacementLength = replacementList.length;

		// make sure lengths are ok, these need to be equal
		if (searchLength != replacementLength) {
			throw new IllegalArgumentException(
					"Search and Replace array lengths don't match: " + searchLength + " vs " + replacementLength);
		}

		// keep track of which still have matches
		boolean[] noMoreMatchesForReplIndex = new boolean[searchLength];

		// index on index that the match was found
		int textIndex = -1;
		int replaceIndex = -1;
		int tempIndex = -1;

		// index of replace array that will replace the search string found
		// NOTE: logic duplicated below START
		for (int i = 0; i < searchLength; i++) {
			if (noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length() == 0
					|| replacementList[i] == null) {
				continue;
			}
			tempIndex = text.indexOf(searchList[i]);

			// see if we need to keep searching for this
			if (tempIndex == -1) {
				noMoreMatchesForReplIndex[i] = true;
			} else {
				if (textIndex == -1 || tempIndex < textIndex) {
					textIndex = tempIndex;
					replaceIndex = i;
				}
			}
		}
		// NOTE: logic mostly below END

		// no search strings found, we are done
		if (textIndex == -1) {
			return text;
		}

		int start = 0;

		// get a good guess on the size of the result buffer so it doesnt have
		// to double if it goes over a bit
		int increase = 0;

		// count the replacement text elements that are larger than their
		// corresponding text being replaced
		for (int i = 0; i < searchList.length; i++) {
			if (searchList[i] == null || replacementList[i] == null) {
				continue;
			}
			int greater = replacementList[i].length() - searchList[i].length();
			if (greater > 0) {
				increase += 3 * greater; // assume 3 matches
			}
		}
		// have upper-bound at 20% increase, then let Java take over
		increase = Math.min(increase, text.length() / 5);
		StringBuilder buf = new StringBuilder(text.length() + increase);

		while (textIndex != -1) {

			for (int i = start; i < textIndex; i++) {
				buf.append(text.charAt(i));
			}
			buf.append(replacementList[replaceIndex]);

			start = textIndex + searchList[replaceIndex].length();

			textIndex = -1;
			replaceIndex = -1;
			tempIndex = -1;
			// find the next earliest match
			// NOTE: logic mostly duplicated above START
			for (int i = 0; i < searchLength; i++) {
				if (noMoreMatchesForReplIndex[i] || searchList[i] == null || searchList[i].length() == 0
						|| replacementList[i] == null) {
					continue;
				}
				tempIndex = text.indexOf(searchList[i], start);

				// see if we need to keep searching for this
				if (tempIndex == -1) {
					noMoreMatchesForReplIndex[i] = true;
				} else {
					if (textIndex == -1 || tempIndex < textIndex) {
						textIndex = tempIndex;
						replaceIndex = i;
					}
				}
			}
			// NOTE: logic duplicated above END

		}
		int textLength = text.length();
		for (int i = start; i < textLength; i++) {
			buf.append(text.charAt(i));
		}
		String result = buf.toString();
		if (!repeat) {
			return result;
		}

		return replaceEach(result, searchList, replacementList, repeat, timeToLive - 1);
	}

	/**
	 * 将日期字符串以指定格式转换为Date
	 * 
	 * @param time
	 *            日期字符串
	 * @param format
	 *            指定的日期格式，若为null或""则使用指定的格式"yyyy-MM-dd HH:MM"
	 * 
	 */

	public final static String FORMAT_DATE = "yyyy-MM-dd";
	public final static String FORMAT_TIME = "hh:mm";
	public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm";
	public final static String FORMAT_MONTH_DAY_TIME = "MM月dd日 hh:mm";
	private static SimpleDateFormat sdf = new SimpleDateFormat();

	public static Date getTimeFromString(String timeStr, String format) {
		if (format == null || format.trim().equals("")) {
			sdf.applyPattern(FORMAT_DATE_TIME);
		} else {
			sdf.applyPattern(format);
		}
		try {
			return sdf.parse(timeStr);
		} catch (ParseException e) {
			return new Date();
		}
	}

	/**
	 * 隐藏手机号中间4位
	 * 
	 * @param mobile
	 * @return
	 */
	public static String phone2Hide(String mobile) {
		if (ValidateUtils.isMobile(mobile)) {
			return mobile.replaceAll("(?<=\\d{3})\\d(?=\\d{4})", "*");
		}
		return mobile;
	}

	/**
	 * 判断字符串是否为空 是返回空字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String formatString(String str) {
		if (TextUtils.isEmpty(str) || str.equals("null")) {
			return "";
		}
		return str;
	}

	/**
	 * 检测是否有emoji表情
	 *
	 * @param source
	 * @return
	 */
	public static boolean containsEmoji(String source) {
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (!isEmojiCharacter(codePoint)) { // 如果不能匹配,则该字符是Emoji表情
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断是否是Emoji
	 *
	 * @param codePoint
	 *            比较的单个字符
	 * @return
	 */
	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
				|| ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF));
	}

	/** 匹配&或全角状态字符或标点 */
	public static final String PATTERN = "&|[\uFE30-\uFFA0]|‘’|“”";

	public static String replaceSpecialtyStr(String str, String pattern, String replace) {
		if (isBlankOrNull(pattern))
			pattern = "\\s*|\t|\r|\n";// 去除字符串中空格、换行、制表
		if (isBlankOrNull(replace))
			replace = "";
		return Pattern.compile(pattern).matcher(str).replaceAll(replace);

	}

	public static boolean isBlankOrNull(String str) {
		if (null == str)
			return true;
		return str.length() == 0;
	}

	/** 清除数字和空格 */
	public static String cleanBlankOrDigit(String str) {
		if (isBlankOrNull(str))
			return "null";
		return Pattern.compile("\\d|\\s").matcher(str).replaceAll("");
	}

	/**
	 * 验证身份证号是否符合规则
	 * @param text 身份证号
	 * @return if one of them match, return true,else return false
	 */
	public static boolean personIdValidation(String text) {
		String regx = "[0-9]{17}x";
		String reg1 = "[0-9]{15}";
		String regex = "[0-9]{18}";
		return text.matches(regx) || text.matches(reg1) || text.matches(regex);
	}

}
