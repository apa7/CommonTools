package cn.lidd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC��ز���
 *
 */
public class JdbcUtil {

	/**
	 * �������ݿ��ַ���û���������ȡ��Oracle���ݿ�����.
	 *
	 * @param url
	 *            ���ݿ��ַ�� ���磺jdbc:oracle:thin:@10.10.10.10:1521:orcl
	 * @param user
	 *            �û���
	 * @param password
	 *            ����
	 * @return ���ݿ�����
	 * @throws SQLException
	 *             ���������쳣
	 * @throws ClassNotFoundException
	 *             �쳣
	 */
	public static Connection getOracleConnection(String url, String user,
			String password) throws SQLException, ClassNotFoundException {
		// ע������
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// ��������
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * �������ݿ�IP�����ݿ�˿�, ���ݿ�����, �û���������ȡ��Oracle���ݿ�����.
	 *
	 * @param ip
	 *            ���ݿ�IP�� ���磺10.10.10.10
	 * @param port
	 *            ���ݿ�˿ڣ� ���磺1521
	 * @param dbName
	 *            ���ݿ����ƣ� ���磺myOracle
	 * @param user
	 *            �û���
	 * @param password
	 *            ����
	 * @return ���ݿ�����
	 * @throws SQLException
	 *             ���������쳣
	 * @throws ClassNotFoundException
	 *             �쳣
	 */
	public static Connection getOracleConnection(String ip, String port,
			String dbName, String user, String password) throws SQLException,
			ClassNotFoundException {
		String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbName;
		// ��������
		return getOracleConnection(url, user, password);
	}
}
