package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ysm.dao.MemberDAO;
import ysm.dto.MemberDTO;

public class MUpdateCommand implements MCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");  //한글 처리
		
		HttpSession session = request.getSession();
		
		MemberDTO dto = new MemberDTO();
		
		dto.setId(request.getParameter("id"));
		dto.setPwd(request.getParameter("pwd"));
		dto.setNick(request.getParameter("nick"));
		
		MemberDAO dao = new MemberDAO();

		dao.update(dto);//DB에 변경된 데이터 업데이트
		
		//변경된 값들이 화면에 바로 보이게 하기
		String id = dto.getId();
		String pwd = dto.getPwd();
		String nick = dto.getNick();
		session.setAttribute("id", id);
		session.setAttribute("pwd", pwd);
		session.setAttribute("nick", nick);	
		}
}
