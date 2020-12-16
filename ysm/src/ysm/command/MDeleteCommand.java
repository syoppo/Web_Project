package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ysm.dao.MemberDAO;
import ysm.dto.MemberDTO;

public class MDeleteCommand implements MCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");  
		request.setCharacterEncoding("utf-8"); 
		  
		HttpSession session = request.getSession();

		  String id = (String) session.getAttribute("id");//탈퇴에 필요한 id값 가져오기
		  MemberDAO dao = new MemberDAO();
		  
		  dao.delete(id);
		  
		  MemberDTO dto = new MemberDTO();
		  
		  //변경된 값들이 페이지에 바로 적용되게 하기
			id = dto.getId();
			String pwd = dto.getPwd();
			String nick = dto.getNick();
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			session.setAttribute("nick", nick);	
	}
}
