package cs.dit.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import cs.dit.dto.ReplyDTO;

public class ReplyDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ReplyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP");
			
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
	
	  public void register(int bcode, String reply) { 
		  //to do 
		  String sql ="insert into reply(bcode, rcode, reply, regdate) values(?, SEQ.NEXTVAL, ? , sysdate)"; 
		  boolean check = false; 
		  ReplyDTO dto = new ReplyDTO(); 
		  try { 
			  con = ds.getConnection(); 
			  pstmt = con.prepareStatement(sql); 
			  pstmt.setInt(1, bcode);
			  pstmt.setString(2, dto.getReply());
	  
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
		} 
	}


	public JSONArray rlist(int bcode) {
		//to do
		String sql = "select rcode, reply from reply where bcode=?";
		
		JSONArray list = new JSONArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject json = new JSONObject();
				json.put("rcode", rs.getInt("rcode"));
				json.put("reply", rs.getString("reply"));
				
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
	
	
}
