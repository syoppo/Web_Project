package cs.dit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import cs.dit.dao.ReplyDAO;
import cs.dit.service.BListService;
import cs.dit.service.BViewService;
import cs.dit.service.IBoardService;

@WebServlet("*.rp")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=utf-8");

		//to do
		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		IBoardService command = null;
		
		String uri = request.getRequestURI(); 	
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".rp")); 
		
		if(com !=null && com.trim().equals("rlist")) {
			command = new BViewService();
			command.execute(request, response);
		}else if(com !=null && com.trim().equals("insert")){
			command = new BViewService();
			command.execute(request, response);
			viewPage = "bcomment.jsp";
			RequestDispatcher rd = request.getRequestDispatcher(viewPage);
			rd.forward(request, response);
		}
//		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
//		rd.forward(request, response);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

}
