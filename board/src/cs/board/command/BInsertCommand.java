package cs.board.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.board.dao.BoardDAO;
import cs.board.dto.BoardDTO;
import cs.board.command.BCommand;

public class BInsertCommand implements BCommand{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BoardDTO dto = new BoardDTO();	//DB에 데이터를 저장하기 위해 DTO 객체 생성
		
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setWriter(request.getParameter("writer"));
		
		BoardDAO dao = new BoardDAO();	
		dao.insert(dto);			//DB에 DTO객체를 저장하기 위한 메소드 insert 호출
	}
}
