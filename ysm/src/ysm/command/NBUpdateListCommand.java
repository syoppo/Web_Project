package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.BoardDAO;
import ysm.dto.BoardDTO;

public class NBUpdateListCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));//링크가 걸려있는 no를 클릭하면 매개변수로 전달 받음
		
		BoardDAO dao = new BoardDAO();

		BoardDTO dto = dao.nview(no);	//상세보기를 위해 선택한 id로 DB에서 데이터 추출하여 DTO에 담는다.
		
		request.setAttribute("dto", dto);//DTO를 view에서 데이터를 접근할 수 있도록 Request scope에 저장
	}
}
