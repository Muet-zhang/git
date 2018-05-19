package cn.itheima.jdbc.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 鎻愪緵鑾峰彇杩炴帴鍜岄噴鏀捐祫婧愮殑 鏂规硶
 * 
 * @author Never Say Never
 * @date 2016骞�7鏈�29鏃�
 * @version V1.0
 */
public class JDBCUtils_V3 {
	private static String driver;
	private static String url;
	private static String username;
	private static String password;

	/**
	 * 闈欐�佷唬鐮佸潡鍔犺浇閰嶇疆鏂囦欢淇℃伅
	 */
	static {
		try {
			// 1.閫氳繃褰撳墠绫昏幏鍙栫被鍔犺浇鍣�
			ClassLoader classLoader = JDBCUtils_V3.class.getClassLoader();
			// 2.閫氳繃绫诲姞杞藉櫒鐨勬柟娉曡幏寰椾竴涓緭鍏ユ祦
			InputStream is = classLoader.getResourceAsStream("db.properties");
			// 3.鍒涘缓涓�涓猵roperties瀵硅薄
			Properties props = new Properties();
			// 4.鍔犺浇杈撳叆娴�
			props.load(is);
			// 5.鑾峰彇鐩稿叧鍙傛暟鐨勫��
			driver = props.getProperty("driver");
			url = props.getProperty("url");
			username = props.getProperty("username");
			password = props.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 鑾峰彇杩炴帴鏂规硶
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void release(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
