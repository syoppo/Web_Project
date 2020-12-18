<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import = "ysm.dao.*, ysm.dto.*, java.util.List, java.sql.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <title>여사모♡</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="css/nav.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-sm bg-pink navbar-pink fixed-top" style="position: static;">
            <div class= "navbar-collapse collapse w-100 order-3 dual-collapse7">
                <a class="navbar-brand" href="main.jsp">여사모♡</a>
                <ul class="navbar-nav">
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">아시아</a>
                    <ul class="dropdown-menu">
                      <li><a class="nav-link" href="#">국내</a></li>
                      <li><a class="nav-link" href="#">일본</a></li>
                      <li><a class="nav-link" href="#">중국</a></li>
                      <li><a class="nav-link" href="#">홍콩</a></li>
                      <li><a class="nav-link" href="#">태국</a></li>
                  </ul>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">유럽</a>
                    <ul class="dropdown-menu">
                      <li><a class="nav-link" href="#">영국</a></li>
                      <li><a class="nav-link" href="#">프랑스</a></li>
                      <li><a class="nav-link" href="list.do">스페인</a></li>
                      <li><a class="nav-link" href="#">이탈리아</a></li>
                      <li><a class="nav-link" href="#">그리스</a></li>
                  </ul>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">북미</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">남미</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">아프리카</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">오세아니아</a>
                  </li>
                </ul>
            </div>
            <div class="navbar-collapse collapse w-50 order-3 dual-collapse1">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item content-left">
                    	<c:if test="${empty sessionScope.id }">
                    	  <a class="nav-link" href="login.jsp">로그인</a>                    	
                    	</c:if>
                    	<c:if test="${not empty sessionScope.id }">
                    	<li><a class="nav-link" href="mypage.jsp">${nick }님</a></li>  
                    	 			<input type="hidden" name="pwd" value="${pwd}" id="pwd">
                    	 			<input type="hidden" name="nick" value="${id}" id="nick">
											<li><a class="nav-link" href="logout.mb">로그아웃</a></li>
                    	</c:if>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container-fluid" style="padding: 0;">
          <img src="image/nav.jpeg"
          style="padding: 0%;" width="100%" height="400">
        </div>
</body>
</html>