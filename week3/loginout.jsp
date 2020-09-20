<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 로그아웃 하기</title>
</head>
<body>
	<h1>로그인/로그아웃</h1>
	<%
		if(session.getAttribute("id") == null) {
	%>
	<form action="../login" method="post">
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="text" name="pwd"><br>
		<input type="submit" value="로그인">
	</form>
	<%
		}
	%>
	<%
		if(session.getAttribute("id") != null) {
	%>
	<form action="logout" method="post">
		<p name="id">${id} 로그인 되었습니다.</p>
		<input type="submit" value="로그아웃">
	</form>
	<%
		}
	%>

 </body>
</html>