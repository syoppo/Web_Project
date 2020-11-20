package cs.dit.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import cs.dit.dao.BoardDAO;
import cs.dit.dao.ReplyDAO;
import cs.dit.dto.BoardDTO;
import cs.dit.dto.ReplyDTO;

public class BViewService implements IBoardService{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		//to do
		
		String number = request.getParameter("bcode").trim();
//		System.out.println(request.getParameter("bcode"));
		if(number == null) {
			number="0";
		}
		int no = Integer.parseInt(number);
		System.out.println(no);
		
		//게시판 부분
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = dao.view(no);	//상세보기를 위해 선택한 id로 DB에서 데이터 추출하여 DTO에 담는다.
		request.setAttribute("dto", dto);//DTO를 view에서 데이터를 접근할 수 있도록 Request scope에 저장
		
		//댓글 보기 부분
		ReplyDAO rdao = new ReplyDAO();
		JSONArray rdtos = rdao.rlist(no);
		request.setAttribute("dtos", rdtos);
		PrintWriter out = response.getWriter();
		out.print(rdtos);
//		System.out.println(dtos);
	}
}
