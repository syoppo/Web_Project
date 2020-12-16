package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.BoardDAO;
import ysm.dto.BoardDTO;

public class NBUpdateCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");  //한글 처리
		
		String no = request.getParameter("no");
		if (no == null) {
			no = "0";
			}
		int num = Integer.parseInt(no);
		
		BoardDTO dto = new BoardDTO();
		
		dto.setNo(num);
		dto.setTitle(request.getParameter("title"));
		dto.setWriter(request.getParameter("writer"));
		dto.setContent(request.getParameter("content"));
//		dto.setClick(Integer.parseInt(request.getParameter("click")));
//		dto.setRegdate(Date.valueOf(request.getParameter("regdate"))); //문자열로 받은 매개변수를 Date형으로 변환
		
		BoardDAO dao = new BoardDAO();

		dao.nupdate(dto);//DB에 변경된 데이터 업데이트
	}
}
