<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import = "cs.board.dao.*, cs.board.dto.*, java.util.List, java.sql.Date" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<title>게시판</title>
	<link rel="stylesheet" type="text/css" href="css/board.css">
</head>
<body>
<div class="container">
	<h2>상세보기</h2>
	<br/>
	<form action="update.do" method="post">
		<input type="hidden" name="no" value="${dto.no}">
		<table class="table table-striped table-hover">
			<tr>
				<th>title</th><td><input type="text" value="${dto.title}" name="title"></td>
			</tr>
			<tr>
				<th>writer</th><td><input type="text" value="${dto.writer}" name="writer"></td>
				<th>content</th><td><input type="text" value="${dto.content}" name="content"></td>
			</tr>
			<tr>
				<th>regdate</th><td colspan="3"><input type="text" value="${dto.regdate}" name="regdate"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value ="글 수정" >
					<input type="button" value ="글 삭제" onclick ="location.href='delete.do?no=${dto.no}'">
					<input type="button" value ="글 목록" onclick ="location.href='list.do'">
					<input type="button" value ="글 등록" onclick ="location.href='insertForm.do'">
				</td>
			</tr>
		</table><br><br>
	</form>
</div>
</body>
</html>