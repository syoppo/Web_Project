<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>도서 정보 출력</title>
</head>
<body>
	<%
		request.setCharacterEncoding("utf-8");

		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		
		request.setAttribute("title", title);
		request.setAttribute("author", author);
		request.setAttribute("publisher", publisher);
	%>
	<jsp:useBean id="book" class="el.week04.BookBean" scope="request"/>

	<c:set value="${title }" target="${book }" property="title"/>
	<c:set value="${author }" target="${book }" property="author"/>
	<c:set value="${publisher }" target="${book }" property="publisher"/>
	
	<h1>도서 정보</h1>
	책 제목 : <c:out value="${book.title }"/><br>
	저자 : <c:out value="${book.author }"/><br>
	출판사 : <c:out value="${book.publisher }"/><br>
</body>
</html>