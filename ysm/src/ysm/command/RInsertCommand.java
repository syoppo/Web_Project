package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.ReplyDAO;
import ysm.dto.ReplyDTO;

public class RInsertCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		String number = request.getParameter("no").trim();
		System.out.println(request.getParameter("no"));
		if(number == null) {
			number="0";
		}
		int no = Integer.parseInt(number);
		System.out.println(no);
		
		
		ReplyDTO dto = new ReplyDTO();	//DB에 데이터를 저장하기 위해 DTO 객체 생성
		
		dto.setBcode(no);
		dto.setReply(request.getParameter("reply"));
		dto.setWriter(request.getParameter("id"));
		
		ReplyDAO dao = new ReplyDAO();	
		dao.register(no, dto);			//DB에 DTO객체를 저장하기 위한 메소드 register 호출
	}

}
