<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "ysm.dao.*, ysm.dto.*, java.util.List, java.sql.Date" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>여사모♡</title>
</head>
<body>
	<jsp:include page="nav.jsp" flush="false"/>		
	<form action="update.mb" method="post">
		<div class="container" style="padding-top:30px; width:300px; height:300px; margin:0 auto;">
			<h1 class=".display-4">내정보</h1>
            <div class="form-group">
							<label style="width:70px;">아이디</label>
							<input type="text" value="${id }" name="id" readonly>
						</div>
						<div class="form-group">
							<label style="width:70px;">비밀번호</label>
							<input type="text" value="${pwd }" name="pwd">
						</div>
						<div class="form-group">
							<label style="width:70px;">닉네임</label>
							<input type="text" value="${nick }" name="nick">
						</div>
			<button type="submit" class="btn btn-secondary">수정</button>
			<input type="button"  class="btn btn-secondary" onClick="location.href='delete.mb'" value="탈퇴">
		</div>
	</form>
</body>
</html>