package ysm.command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ysm.dao.BoardDAO;
import ysm.dto.BoardDTO;

public class BListCommand implements BCommand{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDAO dao = new BoardDAO();
		BoardDTO dto = new BoardDTO();	
		
		//페이지 분할
		int count = dao.getCount();
		request.setAttribute("count", count);
		System.out.print(count);
		
		int pageSize = 10;	//한 페이지에 출력할 레코드 수
		
		//페이지 링크를 클릭한 번호 -> 현재 페이지
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		int currentPage = Integer.parseInt(pageNum);
		
		//해당 페이지에서 시작할 레코드  마지막 레코드
		int startRow = (currentPage -1) *pageSize +1;
		int endRow = currentPage * pageSize;
		
		ArrayList<BoardDTO> dtos = null;
		if(count > 0) {
			dtos = dao.list(startRow, endRow);
		}
		//마지막 페이지가 총 페이지 수 보다 크면 endPage를 pageCount로 할당
		if(count >0) {
			int pageCount = count/pageSize+(count%pageSize == 0?0:1);
			int pageBlock = 10;	//한페이지 보여지는 페이지 링크 수
			int startPage = ((currentPage-1)/pageBlock)*pageBlock+1;
			int endPage = startPage + pageBlock -1;
			
			if(endPage > pageCount) {
				endPage = pageCount;
			}
			if(startPage>pageBlock) {
				currentPage=startPage-10;
			}
			
			if(endPage<pageCount) {
				currentPage=startPage+10;
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("pageBlock", pageBlock);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
		}
		if(count>0) {
			int number = count - (currentPage -1) * pageSize;
			for (int i=0; i<dtos.size(); i++) {
				dto = dtos.get(i);
			}
		}
		
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("dtos", dtos);
	}
}
