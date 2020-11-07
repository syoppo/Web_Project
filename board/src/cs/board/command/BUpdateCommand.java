package cs.board.command;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.board.dao.BoardDAO;
import cs.board.dto.BoardDTO;

public class BUpdateCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //한글 처리
		
		BoardDTO dto = new BoardDTO();
		
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		dto.setTitle(request.getParameter("title"));
		dto.setWriter(request.getParameter("writer"));
		dto.setContent(request.getParameter("content"));
		dto.setRegdate(Date.valueOf(request.getParameter("regdate"))); //문자열로 받은 매개변수를 Date형으로 변환
		
		BoardDAO dao = new BoardDAO();

		dao.update(dto);//DB에 변경된 데이터 업데이트
	}
}
