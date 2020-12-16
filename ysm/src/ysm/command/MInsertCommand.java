package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.MemberDAO;
import ysm.dto.MemberDTO;;

public class MInsertCommand implements MCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemberDTO dto = new MemberDTO();	//DB에 데이터를 저장하기 위해 DTO 객체 생성
		
		String id = request.getParameter("id");
		System.out.println(id);
		
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setNick(request.getParameter("nick"));
		
		MemberDAO dao = new MemberDAO();	
		dao.sign(dto);			//DB에 DTO객체를 저장하기 위한 메소드 sign 호출
	}
}
