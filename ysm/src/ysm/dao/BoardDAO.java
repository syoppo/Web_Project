package ysm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ysm.dto.BoardDTO;

public class BoardDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int result = 0;
	
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/ysm");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(con != null) {
				con.close();
				con=null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//스페인 게시판 총 게시글 갯수 구하기
	public int getCount() {
		int count = 0;
		String sql = "select count(*) from board";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return count;
	}
	
	//스페인 게시판 게시글 목록(페이징 처리)
	public ArrayList<BoardDTO> list(int startRow, int endRow){
		String sql = "select * from( select rownum as rn, A.* from( select rownum, no, title, writer, regdate, click from BOARD order by no DESC) A)"
				+ "where rn between ? and ?";
		
		ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setRegdate(rs.getDate("REGDATE"));
				dto.setClick(rs.getInt("click"));
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
	
	//스페인 게시판 글작성
	public boolean insert(BoardDTO dto) {	//DB에 저장이 잘되면 true, 잘안되었으면  false를 반환
		String sql = "insert into board(no, title, writer, content, regdate, click) values(SEQ.NEXTVAL,?,?,?, sysdate, 0)"; 
		
		boolean check = false;
		try {  
			con = ds.getConnection();  //Connection객체 CP에서 얻어오기
			pstmt =con.prepareStatement(sql);  	//Connection객체를 통해 SQL문 준비
//			pstmt.setInt(1, dto.getNo());	//SQL문과 데이터 바인팅
			pstmt.setString(1, dto.getTitle());
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
	//스페인 게시판 글 상세 보기	
	public BoardDTO view(int no) {
		String sql ="select * from board where no=?";
		BoardDTO dto = new BoardDTO();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {		//상세보기를 위한 한 레코드셋을 DTO에 저장
				dto.setNo(rs.getInt("NO"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setWriter(rs.getString("WRITER"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setRegdate(rs.getDate("REGDATE"));
				dto.setClick(rs.getInt("click"));
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
	//스페인 게시판 글 수정하기	
		public boolean update(BoardDTO dto) {
			String sql = "update board set title=?, writer=?, content=? where no=?";
			boolean check = false;
			try {
				con = ds.getConnection();
				pstmt =con.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getWriter());
				pstmt.setString(3, dto.getContent());
//				pstmt.setDate(4, dto.getRegdate());
//				pstmt.setInt(5, dto.getClick());
				pstmt.setInt(4, dto.getNo());
				
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
		// 스페인 게시판 글 삭제 하기		
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
		
		//스페인 게시판 조회수 증가
		public int clickUpdate(int no) {
			String sql = "UPDATE board SET click = click + 1 WHERE no = ?";
			boolean check = false;

			try {
				con = ds.getConnection();
				pstmt =con.prepareStatement(sql);
				pstmt.setInt(1, no);
				result = pstmt.executeUpdate();
			} catch(SQLException ex) {
				System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
				check = false;
			}
			return result;
		}
		//공지사항 글작성
		public boolean ninsert(BoardDTO dto) {	//DB에 저장이 잘되면 true, 잘안되었으면  false를 반환
			String sql = "insert into nboard(no, title, writer, content, regdate, click) values(nbSEQ.NEXTVAL,?,?,?, sysdate, 0)"; 
			
			boolean check = false;
			try {  
				con = ds.getConnection();  //Connection객체 CP에서 얻어오기
				pstmt =con.prepareStatement(sql);  	//Connection객체를 통해 SQL문 준비
//				pstmt.setInt(1, dto.getNo());	//SQL문과 데이터 바인팅
				pstmt.setString(1, dto.getTitle());
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

		//공지사항 게시글 목록(페이징 처리)
		public ArrayList<BoardDTO> nlist(int startRow, int endRow){
			String sql = "select * from( select rownum as rn, A.* from( select rownum, no, title, writer, regdate, click from NBOARD order by no DESC) A)where rn between ? and ?";
			
			ArrayList<BoardDTO> dtos = new ArrayList<BoardDTO>();
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					BoardDTO dto = new BoardDTO();
					dto.setNo(rs.getInt("NO"));
					dto.setTitle(rs.getString("TITLE"));
					dto.setWriter(rs.getString("WRITER"));
					dto.setRegdate(rs.getDate("REGDATE"));
					dto.setClick(rs.getInt("click"));
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

		//공지사항 총 게시글 갯수 구하기
		public int nbgetCount() {
			int count = 0;
			String sql = "select count(*) from nboard";
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt(1);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}finally {
				close();
			}
			return count;
		}
		
		//공지사항 상세보기
		public BoardDTO nview(int no) {
			String sql ="select * from nboard where no=?";
			BoardDTO dto = new BoardDTO();
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {		//상세보기를 위한 한 레코드셋을 DTO에 저장
					dto.setNo(rs.getInt("NO"));
					dto.setTitle(rs.getString("TITLE"));
					dto.setWriter(rs.getString("WRITER"));
					dto.setContent(rs.getString("CONTENT"));
					dto.setRegdate(rs.getDate("REGDATE"));
					dto.setClick(rs.getInt("click"));
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

		//공지사항 글 수정하기	
				public boolean nupdate(BoardDTO dto) {
					String sql = "update nboard set title=?, writer=?, content=? where no=?";
					boolean check = false;
					try {
						con = ds.getConnection();
						pstmt =con.prepareStatement(sql);
						pstmt.setString(1, dto.getTitle());
						pstmt.setString(2, dto.getWriter());
						pstmt.setString(3, dto.getContent());
//						pstmt.setDate(4, dto.getRegdate());
//						pstmt.setInt(5, dto.getClick());
						pstmt.setInt(4, dto.getNo());
						
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
				
				//공지사항 글 삭제 하기		
				public boolean ndelete(int no) {
					String sql = "delete from nboard where no=?";
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
				
				//공지사항 조회수 증가
				public int nclickUpdate(int no) {
					String sql = "UPDATE nboard SET click = click + 1 WHERE no = ?";
					boolean check = false;

					try {
						con = ds.getConnection();
						pstmt =con.prepareStatement(sql);
						pstmt.setInt(1, no);
						result = pstmt.executeUpdate();
					} catch(SQLException ex) {
						System.out.println("SQL insert 오류 : " + ex.getLocalizedMessage());
						check = false;
					}
					return result;
				}
}
