package cn.lidd.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * JDBC相关操作
 *
 */
public class JdbcUtil {

	/**
	 * 根据数据库地址，用户名，密码取得Oracle数据库链接.
	 *
	 * @param url
	 *            数据库地址， 例如：jdbc:oracle:thin:@10.10.10.10:1521:orcl
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @return 数据库连接
	 * @throws SQLException
	 *             建立链接异常
	 * @throws ClassNotFoundException
	 *             异常
	 */
	public static Connection getOracleConnection(String url, String user,
			String password) throws SQLException, ClassNotFoundException {
		// 注册驱动
		Class.forName("oracle.jdbc.driver.OracleDriver");
		// 建立链接
		return DriverManager.getConnection(url, user, password);
	}

	/**
	 * 根据数据库IP，数据库端口, 数据库名称, 用户名，密码取得Oracle数据库链接.
	 *
	 * @param ip
	 *            数据库IP， 例如：10.10.10.10
	 * @param port
	 *            数据库端口， 例如：1521
	 * @param dbName
	 *            数据库名称， 例如：myOracle
	 * @param user
	 *            用户名
	 * @param password
	 *            密码
	 * @return 数据库连接
	 * @throws SQLException
	 *             建立链接异常
	 * @throws ClassNotFoundException
	 *             异常
	 */
	public static Connection getOracleConnection(String ip, String port,
			String dbName, String user, String password) throws SQLException,
			ClassNotFoundException {
		String url = "jdbc:oracle:thin:@" + ip + ":" + port + ":" + dbName;
		// 建立链接
		return getOracleConnection(url, user, password);
	}
}
