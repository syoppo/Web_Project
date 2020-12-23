package ysm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.command.BCommand;
import ysm.command.BDeleteCommand;
import ysm.command.BInsertCommand;
import ysm.command.BListCommand;
import ysm.command.BUpdateCommand;
import ysm.command.BUpdateListCommand;
import ysm.command.BViewCommand;
import ysm.command.NBDeleteCommand;
import ysm.command.NBInsertCommand;
import ysm.command.NBListCommand;
import ysm.command.NBUpdateCommand;
import ysm.command.NBUpdateListCommand;
import ysm.command.NBViewCommand;
import ysm.dao.BoardDAO;

@WebServlet("*.do")
public class BoardController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String viewPage = null;
		BCommand command = null;
		
		String uri = request.getRequestURI(); 	
		String com= uri.substring(uri.lastIndexOf("/")+ 1, uri.lastIndexOf(".do")); 
		
		if(com !=null && com.trim().equals("list")) {
			command = new BListCommand();
			command.execute(request, response);
			viewPage = "bList.jsp";
		}else if(com !=null && com.trim().equals("insertForm")) {
			viewPage = "bInsertForm.jsp";			
		}else if(com !=null && com.trim().equals("insert")) {
			command = new BInsertCommand();
			command.execute(request, response);
			viewPage ="list.do";
		}else if(com !=null && com.trim().equals("view")) {
			command = new BViewCommand();
			command.execute(request, response);
			viewPage = "bView.jsp";
		}else if(com !=null && com.trim().equals("updateForm")) {
			command = new BUpdateListCommand();
			command.execute(request, response);
			viewPage = "bUpdate.jsp";			
		}else if(com !=null && com.trim().equals("update")){
			command = new BUpdateCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}else if(com !=null && com.trim().equals("delete")){
			command = new BDeleteCommand();
			command.execute(request, response);
			viewPage = "list.do";
		}else if(com !=null && com.trim().equals("ninsertForm")){
			viewPage = "nbInsertForm.jsp";
		}else if(com !=null && com.trim().equals("nlist")) {
			command = new NBListCommand();
			command.execute(request, response);
			viewPage = "nbList.jsp";
		}else if(com !=null && com.trim().equals("ninsert")) {
			command = new NBInsertCommand();
			command.execute(request, response);
			viewPage ="nlist.do";
		}else if(com !=null && com.trim().equals("nview")) {
			command = new NBViewCommand();
			command.execute(request, response);
			viewPage = "nbView.jsp";
		}else if(com !=null && com.trim().equals("nupdateForm")) {
			command = new NBUpdateListCommand();
			command.execute(request, response);
			viewPage = "nbUpdate.jsp";			
		}else if(com !=null && com.trim().equals("nupdate")){
			command = new NBUpdateCommand();
			command.execute(request, response);
			viewPage = "nlist.do";
		}else if(com !=null && com.trim().equals("ndelete")){
			command = new NBDeleteCommand();
			command.execute(request, response);
			viewPage = "nlist.do";
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
