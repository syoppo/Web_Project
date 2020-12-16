package ysm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.command.BCommand;
import ysm.command.BViewCommand;
import ysm.command.RInsertCommand;
import ysm.dao.ReplyDAO;

import org.json.simple.JSONArray;

@WebServlet("*.rp")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI(); 	
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".rp")); 
		
		if(com !=null && com.trim().equals("rlist")) {
			command = new BViewCommand();
			command.execute(request, response);
		}else if(com !=null && com.trim().equals("rinsert")){
			command = new RInsertCommand();
			command.execute(request, response);
			//viewPage = "main.jsp";
			//RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			//rd.forward(request, response);
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}