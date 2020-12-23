package ysm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ysm.dto.ReplyDTO;

public class ReplyDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/ysm");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con !=null) {
				con.close();
				con=null;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	//스페인 게시판 댓글 목록 보기
	public JSONArray rlist(int no) {
		String sql = "select rcode, reply, regdate, writer from reply where no=? ORDER BY rcode";
		
		JSONArray list = new JSONArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("writer", rs.getString("writer"));
				json.put("rcode", rs.getInt("rcode"));
				json.put("reply", rs.getString("reply"));
				json.put("regdate", rs.getString("regdate"));
				
				list.add(json);
				
				System.out.println(list);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	//스페인 게시판 댓글 작성
	public void register(int no, ReplyDTO dto) { 
		  String sql ="insert into reply(no, rcode, reply, regdate, writer) values(?, SEQ.NEXTVAL, ? , sysdate, ?)"; 
		  boolean check = false; 
//		  ReplyDTO dto = new ReplyDTO(); 
		  try { 
			  con = ds.getConnection(); 
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setInt(1, dto.getBcode());
			  pstmt.setString(2, dto.getReply());
			  pstmt.setString(3, dto.getWriter());
	  
			  int x = pstmt.executeUpdate();
	  
			  if(x<1) { 
				  System.out.println("정상적으로 저장되지 않았습니다."); 
			  }else { 
				  check = true; 
			  }
			  pstmt.close(); 
			  }catch(SQLException ex) {
				  System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage()); 
				  check = false; 
		} finally {
			close();
		}
	}
}
