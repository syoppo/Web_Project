package ysm.command;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import ysm.dao.MemberDAO;
import ysm.dto.MemberDTO;

public class MLoginCommand implements MCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		MemberDAO dao = new MemberDAO();
		int check = dao.login(id, pwd);
		MemberDTO dto = dao.nick(id);	//아이디로 닉네임을 찾기위해 id값 보내기
		String nick = dto.getNick();
		
		request.setAttribute("check", check);

		if(check == 1 ) {
//			System.out.println(nick+"닉네임");
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			session.setAttribute("nick", nick);
			System.out.println("로그인 되었습니다.");
		} else if(check==0) {
//			session.setAttribute("id", null);
			System.out.println("비밀번호를 잘못입력하셨습니다.");
		} else if(check==-1){
//			session.setAttribute("id", null);
			System.out.println("없는 사용자 입니다.");
		}
	}
}
