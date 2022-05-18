/*==========================
	MssqlDAO.java
	- OracleDAO 와 설정만 다르게 연결하여 실습
	- MSSQL 안깔려있음
===========================*/
package com.test.spr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MssqlDAO
{
	public List<MemberDTO> lists() throws ClassNotFoundException, SQLException
	{
		List<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// db 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@211.238.142.164:1521:xe", "scott","tiger" );
		
		// 쿼리문 준비
		String sql = "SELECT ID, NAME, TEL, EMAIL FROM TBL_MEMBERLIST";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getString("ID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setEmail(rs.getString("EMAIL"));
			
			result.add(dto);
		}
			
		// 리소스 반납
		rs.close();
		stmt.close();
		
		if(!conn.isClosed())
			conn.close();
		
		return  result;
	}
}
