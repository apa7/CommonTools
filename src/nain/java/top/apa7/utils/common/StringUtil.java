package cn.lidd.util;

import java.util.regex.Pattern;

/**
 * 字符串处理常用方法.
 *
 * @author lidong
 *
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * 判断字符串是否数字.
	 *
	 * @param str
	 *            传入字符串
	 * @return true：是数字 false：不是数字
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9,.,-]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断字符串是否全部为大写字母.
	 *
	 * @param str
	 *            传入字符串
	 * @return true：全部为大写字母 false：不全部为大写字母
	 */
	public static boolean isUpperCase(String str) {
		Pattern pattern = Pattern.compile("[A-Z]*");
		return pattern.matcher(str).matches();
	}
 
}