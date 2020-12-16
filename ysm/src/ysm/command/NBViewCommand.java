package ysm.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import ysm.dao.BoardDAO;
import ysm.dao.ReplyDAO;
import ysm.dto.BoardDTO;

public class NBViewCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String number = request.getParameter("no").trim();
		//System.out.println(request.getParameter("no"));
		if(number == null) {
			number="0";
		}
		int no = Integer.parseInt(number);
		System.out.println(no);
		
		//게시판 부분
		BoardDAO dao = new BoardDAO();
		dao.nclickUpdate(no);	//클릭수 증가
		BoardDTO dto = dao.nview(no);	//상세보기를 위해 선택한 id로 DB에서 데이터 추출하여 DTO에 담는다.
		request.setAttribute("dto", dto);//DTO를 view에서 데이터를 접근할 수 있도록 Request scope에 저장
		
	}
}
