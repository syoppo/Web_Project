package cs.dit.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProc(request, response);
	}
	
	public void doProc(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html; charset=utf-8");
		
		int result;
		int one = Integer.parseInt(request.getParameter("one"));
		int two = Integer.parseInt(request.getParameter("two"));
		String sign = request.getParameter("sign");
		
		PrintWriter out = response.getWriter();
		out.println("계산결과 : ");
		
		if(sign.equals("p")) {
			result = one + two;
			out.println(result);
		} else if(sign.equals("s")){
			result = one - two;
			out.println(result);
		} else if(sign.equals("m")) {
			result = one * two;
			out.println(result);
		} else if(sign.equals("d")) {
			result = one / two;
			out.println(result);
		}		
	}

}
