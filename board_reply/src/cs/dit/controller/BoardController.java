package cs.dit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs.dit.service.BListService;
import cs.dit.service.BViewService;
import cs.dit.service.IBoardService;


@WebServlet("*.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//to do
		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		IBoardService command = null;
		
		String uri = request.getRequestURI(); 	
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".do")); 
		
		if(com !=null && com.trim().equals("list")) {
			command = new BListService();
			command.execute(request, response);
			viewPage = "bList.jsp";
//		}else if(com !=null && com.trim().equals("insertForm")) {
//			viewPage = "/WEB-INF/View/bInsertForm.jsp";
//			
//		}else if(com !=null && com.trim().equals("insert")) {
//			command = new BInsertCommand();
//			command.execute(request, response);
//			viewPage ="/WEB-INF/View/list.do";
		}else if(com !=null && com.trim().equals("view")) {
			command = new BViewService();
			command.execute(request, response);
			viewPage = "bView.jsp";
//		}else if(com !=null && com.trim().equals("update")){
//			command = new BUpdateCommand();
//			command.execute(request, response);
//			viewPage = "/WEB-INF/View/list.do";
//		}else if(com !=null && com.trim().equals("delete")){
//			command = new BDeleteCommand();
//			command.execute(request, response);
//			viewPage = "/WEB-INF/View/list.do";
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
