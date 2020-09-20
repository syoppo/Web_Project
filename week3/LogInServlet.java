package cs.dit.ch03;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LogInServlet extends HttpServlet {
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
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//공통 저장소에 저장한다.
		session.setAttribute("id", id);
		
		response.setContentType("text/html; charset=utf-8");
				
		RequestDispatcher rd = request.getRequestDispatcher("3주차/loginout.jsp");
		rd.forward(request, response);
	}

}
