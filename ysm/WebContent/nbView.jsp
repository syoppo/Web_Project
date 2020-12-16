<!-- 공지사항 상세보기 화면 -->
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
        <style>
          .center{
            text-align:center;
          }
        </style>
    </head>
    <body>
		<jsp:include page="nav.jsp" flush="false"/>
        <div class="container" style="padding-top:20px;">
            <h1 class=".display-4">공지사항</h1>
        </div>
        <br><br>
	<form action="nupdateForm.do" method="post">
 			<input type="hidden" name="no" value="${dto.no}" id="no">
	        <div class="container">
            <h3 style="text-align: center;"><label type="text" name="title" >${dto.title}</label></h3>
                <h6 style="position:absolute;">${dto.regdate}</h6>
                <h6 style="position:static; text-align: right;">작성자 : ${dto.writer}</h6>
                <h6 style="position:static; text-align: right;">조회수 : ${dto.click }</h6>
            <hr>
            <p name="content">
            	${dto.content}
            </p>
            <hr>
        </div>
        <div class="container center" style="padding-bottom: 20px;">
        <c:if test="${id == 'admin' }"> <!-- 관리자만 수정 삭제 가능 -->
					<button type="button" class="btn btn-primary" onclick ="location.href='nupdateForm.do?no=${dto.no}'">글 수정</button>
					<button type="button" class="btn btn-primary" onclick ="location.href='ndelete.do?no=${dto.no}'">글 삭제</button>            		
				</c:if>
	      </div>
	</form>
        <div class="container center" style="padding-bottom: 20px;">
            <button type="button" class="btn btn-primary" onclick="history.go(-1)">목록</button>
        </div>
</body>
</html>