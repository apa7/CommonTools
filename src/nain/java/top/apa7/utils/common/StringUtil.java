package cn.lidd.util;

import java.util.regex.Pattern;

/**
 * �ַ��������÷���.
 *
 * @author lidong
 *
 */
public final class StringUtil {

	private StringUtil() {
	}

	/**
	 * �ж��ַ����Ƿ�����.
	 *
	 * @param str
	 *            �����ַ���
	 * @return true�������� false����������
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("[0-9,.,-]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * �ж��ַ����Ƿ�ȫ��Ϊ��д��ĸ.
	 *
	 * @param str
	 *            �����ַ���
	 * @return true��ȫ��Ϊ��д��ĸ false����ȫ��Ϊ��д��ĸ
	 */
	public static boolean isUpperCase(String str) {
		Pattern pattern = Pattern.compile("[A-Z]*");
		return pattern.matcher(str).matches();
	}
 
}