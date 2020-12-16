<!-- 스페인게시판 리스트보기 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

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
            <h1 class=".display-4">유럽>스페인</h1>
            	<a>총 개시글 수 : ${count }개</a>
                <table class="table table-bordered table-hover thead-dark">
                    <thead bgcolor="99CCFF">
                        <tr class="center" >
                            <th>번호</th>
                            <th>제목</th>
                            <th>닉네임</th>
                            <th>날짜</th>
                            <th>조회수</th>
                        </tr>
                    </thead>
					<c:forEach var="dto" items = "${dtos}">
						<tr>
							<td width=70 class="center">${dto.no}</td>
							<td width=650><a href="view.do?no=${dto.no}">${dto.title}</a></td>
							<td width=120 class="center">${dto.writer}</td>
							<td width=120 class="center"><fmt:formatDate value="${dto.regdate}"/></td>
							<td width=70 class="center">${dto.click}</td>
						</tr>
					</c:forEach>
                </table>
            </div>
            <div class="container">
            <ul class="pagination justify-content-center">
            	<c:if test="${count > 0}">
            		<c:if test="${startPage > pageBlock }">
            			<li class="page-item"><a class="page-link" href="list.do?pageNum=${i-1 }"><</a></li>
            		</c:if>
            		<c:forEach var="i" begin="${startPage }" end="${endPage }" step="${1 }">	<!-- 페이지 번호 생성 -->
            			<li class="page-item"><a class="page-link" href="list.do?pageNum=${i }">${i }</a></li>
            		</c:forEach>
            		<c:if test="${endPage < pageCount }">
                	<li class="page-item"><a class="page-link" href="javascript:void(0);">></a></li>
            		</c:if>
							</c:if>
            </ul>
            </div>
            <div class="container" style="padding-bottom: 20px;">
            	<c:if test="${empty sessionScope.id }">
            	  <button type="button" class="btn btn-primary" onclick="alert('로그인 후 글을 쓸 수 있습니다.')">글쓰기</button>
            	</c:if>
            	<c:if test="${not empty sessionScope.id }">
            	  <button type="button" class="btn btn-primary" onclick="location.href='insertForm.do'">글쓰기</button>
            	</c:if>
            </div>
    </body>
</html>
