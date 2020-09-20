package cs.dit.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	public void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");

		//아이디 입력 정보 가져오기
		String id = (String)session.getAttribute("id");
		String pwd = (String)session.getAttribute("pwd");
		
		//공통 저장소에 저장한다.
		
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();
		out.println(id+" 로그아웃되었습니다.");
		
		session.invalidate();

	}

}
