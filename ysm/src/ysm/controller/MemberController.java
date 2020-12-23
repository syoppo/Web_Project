package ysm.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ysm.command.MCommand;
import ysm.command.MDeleteCommand;
import ysm.command.MInsertCommand;
import ysm.command.MLoginCommand;
import ysm.command.MLogoutCommand;
import ysm.command.MUpdateCommand;

@WebServlet("*.mb")
public class MemberController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");

		String viewPage = null;
		MCommand command = null;
		
		HttpSession session = request.getSession();
				 
		String uri = request.getRequestURI(); 	
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".mb")); 
		
		if(com !=null && com.trim().equals("sign")) {
			command = new MInsertCommand();
			command.execute(request, response);
			viewPage = "login.jsp";
		} else if(com !=null && com.trim().equals("login")){
			command = new MLoginCommand();
			command.execute(request, response);
			int check = (int)request.getAttribute("check");
			if(check == 0 || check ==-1) {
				request.setAttribute("check", check);
				viewPage = "login.jsp";
			}else if(check == 1){
				viewPage = "main.jsp";
			}
		}	else if(com !=null && com.trim().equals("logout")){
			command = new MLogoutCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		}else if(com !=null && com.trim().equals("update")){
			command = new MUpdateCommand();
			command.execute(request, response);
			String nick = (String)session.getAttribute("nick");
			viewPage = "mypage.jsp";
		}else if(com !=null && com.trim().equals("delete")){
			command = new MDeleteCommand();
			command.execute(request, response);
			viewPage = "main.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
}
