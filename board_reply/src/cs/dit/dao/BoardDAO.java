package cs.dit.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cs.dit.dto.BoardDTO;

public class BoardDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
//생성자에서 jdbc/mvc 객체를 찾아 DataSource 로 받는다.
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/JSP");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
//Connection 해제를 위한 메소드
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
	
	public ArrayList<BoardDTO> list(){
		String sql = "select * from board";
		
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBcode(rs.getInt("NO"));
				dto.setSubject(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegDate(rs.getTimestamp("REGDATE"));
				dtos.add(dto);
			}
			rs.close(); pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dtos;
	}
	
	//글작성
	public boolean insert(BoardDTO dto) {	//DB에 저장이 잘되면 true, 잘안되었으면  false를 반환
		String sql = "insert into board(no, title, writer, content, regdate) values(SEQ.NEXTVAL,?,?,?, sysdate)"; 
		boolean check = false;
		try {  
			con = ds.getConnection();  //Connection객체 CP에서 얻어오기
			pstmt =con.prepareStatement(sql);  	//Connection객체를 통해 SQL문 준비
//			pstmt.setInt(1, dto.getNo());	//SQL문과 데이터 바인팅
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());
			
			int x = pstmt.executeUpdate();	//SQL을 수행하고 결과 반환 : 결과는 입력이 된 행 갯수

			if(x<1) {	//1보다 적으면
				System.out.println("정상적으로 저장되지 않았습니다.");
			}else {		//1이상인 경우는 저장이 된 경우
				check=true;
			}
			pstmt.close();
		}catch(SQLException ex) {
			System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
			check = false;
		}
		return check;
	}

	//글 상세 보기	
	public BoardDTO view(int bcode) {
		String sql ="select * from board where no=?";
		BoardDTO dto = new BoardDTO();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bcode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {		//상세보기를 위한 한 레코드셋을 DTO에 저장
				dto.setBcode(rs.getInt("NO"));
				dto.setSubject(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegDate(rs.getTimestamp("REGDATE"));
			}
			
			rs.close();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;	//DTO객체에 데이터를 담아서 반환
	}
	
	// 글 수정하기	
		public boolean update(BoardDTO dto) {
			String sql = "update board set title=?, writer=?, content=?, regdate=? where no=?";
			boolean check = false;
			try {
				con = ds.getConnection();
				pstmt =con.prepareStatement(sql);
				pstmt.setString(1, dto.getSubject());
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getContent());
				pstmt.setTimestamp(4, dto.getRegDate());
				pstmt.setInt(5, dto.getBcode());
				
				int x = pstmt.executeUpdate();	

				if(x<1) {
					System.out.println("정상적으로 저장되지 않았습니다.");
				}else {
					check=true;
				}
				pstmt.close();
			}catch(SQLException ex) {
				System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
				check = false;
			}
			return check;
		}
		// 글 삭제 하기		
		public boolean delete(int no) {
			String sql = "delete from board where no=?";
			boolean check = false;
			try {
				con = ds.getConnection();
				pstmt =con.prepareStatement(sql);
				pstmt.setInt(1, no);
				
				int x = pstmt.executeUpdate();	

				if(x<1) {
					System.out.println("정상적으로 삭제되지 않았습니다.");
				}else {
					check=true;
				}
				pstmt.close();
			}catch(SQLException ex) {
				System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
				check = false;
			}
			return check;
		}
}
