package cn.itheima.jdbc.test;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.itheima.jdbc.DataSource.MyDataSource;

public class TestMyDataSourse {
	@Test
	public void testAddUser(){
		Connection conn=null;
		PreparedStatement pstmt=null;
		//�����Զ������ӳض���
		MyDataSource dataSourse = new MyDataSource();
		try {
			//�ӳ����л�ȡ����
			conn = dataSourse.getConnection();
			String sql = "insert into user value(null,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "lvbu");
			pstmt.setString(2, "lvbu");
			int rows = pstmt.executeUpdate();
			if(rows>0){
				System.out.println("ok");
			}else{
				System.out.println("no");
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}finally{
			dataSourse.backConnection(conn);
		}
	}

}
