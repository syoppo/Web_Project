package cs.dit.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import cs.dit.dao.ReplyDAO;


public class RListService implements IBoardService{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//to do
		
		String number = request.getParameter("bcode").trim();
//		System.out.println(request.getParameter("bcode"));
		if(number == null) {
			number="5";
		}
		int no = Integer.parseInt(number);
		System.out.println(no);
		ReplyDAO dao = new ReplyDAO();
		JSONArray dtos = dao.rlist(no);
		request.setAttribute("dtos", dtos);
		PrintWriter out = response.getWriter();
		out.print(dtos);
//		System.out.println(dtos);
	}
}
