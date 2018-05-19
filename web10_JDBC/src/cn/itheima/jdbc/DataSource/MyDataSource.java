package cn.itheima.jdbc.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

import cn.itheima.jdbc.utils.JDBCUtils_V3;

public class MyDataSource implements DataSource{
	
	//1.创建一个容器用于存储connection的对象
	private static LinkedList<Connection> pool =new LinkedList<Connection>();
	
	//2,创建5个连接到容器中
	static{
		for (int i = 0; i < 5; i++) {
			Connection conn=JDBCUtils_V3.getConnection();
			pool.add(conn);
		}
	}
	
	/*
	 * 重写获取连接的方法
	 * */
	
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection conn=null;
		//1，使用前先判断是否有
		if(pool.size()==0){
			// 池子里没有再创建
			for (int i = 0; i < 5; i++) {
				conn=JDBCUtils_V3.getConnection();
				pool.add(conn);
			}
		}
		//从池里获取一个连接对象
		conn = pool.remove(0);
		return conn;
	}
	
	/**
	 * 归还连接对象到连接池中去
	 * */
	public void backConnection(Connection conn){
		pool.add(conn);
	}
	
	
	
	
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
