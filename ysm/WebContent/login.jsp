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
    <link rel="stylesheet" href="css/login.css">
  </head>
  <body>
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">로그인</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">회원가입</label>
				<div class="login-form">
				<!-- 로그인~ -->
				  <form action="login.mb">
						<div class="sign-in-htm">
							<div class="group">
								<label for="id" class="label">아이디</label>
								<input name="id" type="text" class="input">
							</div>
							<div class="group">
								<label for="pwd" class="label">비밀번호</label>
								<input name="pwd" type="password" class="input" data-type="password">
							</div>
<!-- 							<div class="group">
								<input id="check" type="checkbox" class="check" checked>
								<label for="check"><span class="icon"></span> 로그인 유지</label>
							</div> --> 
							<div class="group">
								<input type="submit" class="button" value="로그인">
							</div>
<!-- 							<div class="hr"></div>
							<div class="foot-lnk">
								<a href="#forgot">비밀번호를 잊어버렸나요?</a>
							</div> -->
						</div>
					</form>
					<!-- ~로그인 -->
					<!-- 회원가입 -->
					<form action="sign.mb">
					<div class="sign-up-htm">
						<div class="group">
							<label for="Id" class="label">아이디</label>
								<input name="id" type="text" class="input" style="width:75%; float:left;">	<!-- style:아이디 입력과 중복확인 버튼 나란히 배치 -->
 								<input type="button" class="button" value="중복확인" style="width:25%; float:right; font-size:15px" onClick="checkId();">
						</div>
						<div class="group">
							<label for="pwd" class="label">비밀번호</label>
							<input name="pwd" type="password" class="input" data-type="password">
						</div>
						<!-- <div class="group">
							<label for="pass" class="label">비밀번호 재입력</label>
							<input id="pass" type="password" class="input" data-type="password">
						</div> -->
<!-- 						<div class="group">
							<label for="pass" class="label">이메일</label>
							<input id="pass" type="text" class="input">
						</div -->
						<div class="group">
							<label for="nick" class="label">닉네임</label>
							<input name="nick" type="text" class="input">
						</div>
						<div class="group">
							<input type="submit" class="button" value="회원가입">
						</div>
						<div class="hr"></div>
						<div class="foot-lnk">
							<label for="tab-1">이미 회원이신가요?</a>
						</div>
					</div>
					</form>
					<!-- 회원가입 -->
				</div>
			</div>
		</div>
</body>
</html>