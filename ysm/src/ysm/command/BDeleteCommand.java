package ysm.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.BoardDAO;

public class BDeleteCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("utf-8"); 
		  int no = Integer.parseInt(request.getParameter("no"));		  
		  BoardDAO dao = new BoardDAO();
		  
		  dao.delete(no);		  
	}
}
