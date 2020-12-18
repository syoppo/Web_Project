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
            <h1 class=".display-4">유럽>스페인</h1>
        </div>
        <br><br>
	<form action="updateForm.do" method="post">	
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
        	<c:if test=""></c:if>
        	<button type="button" class="btn btn-primary" onclick ="location.href='updateForm.do?no=${dto.no}'">글 수정</button>
					<button type="button" class="btn btn-primary" onclick ="location.href='delete.do?no=${dto.no}'">글 삭제</button>
					<c:if test=""></c:if>		
	      </div>
	
	</form>
<div class="container">
  <h4 class="border-bottom pb-2 mb-0">댓글</h4>
  <!-- 댓글 달기 -->
	<script type="text/javascript">
	
		var xhr1 = new XMLHttpRequest();		//rlist
		var xhr2 = new XMLHttpRequest();		//rinsert
		
 		 function replylist(){	
			var no = encodeURIComponent(document.getElementById("no").value);
			var table = document.getElementById("board_comment");
			xhr1.onreadystatechange = function(){
				if(this.readyState == 4 && this.status == 200){
					var json = this.responseText;
					json = json.substring(json.indexOf("["), json.lastIndexOf("]")+1);
					
					var list = JSON.parse(json);
					var html = "";
					console.log(list.length);
					 if(list.length > 0){
 						 for(var i=0 in list){
 							html += "<div class='media border p-3'>";
 		          html += "<div><table class='replylist'><h6 class='mt-0 mb-1'>"+list[i].writer+" <small><a>"+list[i].regdate+"</a></small></h6>";
 		          html += list[i].reply + "<tr><td></td></tr>";
 		          html += "</table></div>";
 		          html += "</div>";
 						} 
					}else {
 						html += "<div>";
 		        html += "<div><table class='replylist'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
 		        html += "</table></div>";
 		        html += "</div>";  
				}
				$("#replylist").html(html);
				}
				
			};
		
		xhr1.open("POST", "/ysm/rlist.rp", true);
		
		//서버에서는 이를 통해 서버로 전달된 변수가 form을 통해 전달된 정보로 간주
		xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8;");
		var data='';
		data += 'no=' + no;
		xhr1.send(data);
	}
		
 		function rinsert(){
 			var no = encodeURIComponent(document.getElementById("no").value);
 			var id = encodeURIComponent(document.getElementById("id").value);
 			var params="reply="+$("#reply").val()+"&no="+no+"&id="+id;
  					$.ajax({
  		 				url:'/ysm/rinsert.rp',
  		 				type:'post',
 		 					async:true,
 		 					data:params,
 		 				success:function(data){
 								$('#reply').val("");
 								alert("댓글이 등록되었습니다.");
 								replylist();
 		 				}
 		 			});
		} 
		window.onload=function(){
			replylist();
		}
	</script>            
            <form name="comment">
                <div class="input-group">
 										<input type="hidden" name="id" value="${nick}" id="id">
 										<c:if test="${not empty sessionScope.id }">
                    <textarea class="form-control" id="reply" rows="2" placeholder="댓글을 입력해 주세요"></textarea>
                   	<span class="input-group-btn">
                        <button class="btn btn-primary" type="button" style="margin: 20px;" onclick="rinsert();">등록</button>
                   </span>
                   </c:if>
                   <c:if test="${empty sessionScope.id }"><!-- 로그인을 안하면 댓글을 못쓰게 함 -->
                    <textarea class="form-control" id="reply" rows="2" placeholder="로그인 후 댓글을 달아주세요." readonly></textarea>
                   	<span class="input-group-btn">
                        <button class="btn btn-secondary disabled" type="button" style="margin: 20px;">등록</button>
                   </span>
                   </c:if>
                  </div>
                </form>
                <br>
        </div>
        <div class="container my-3 p-3 bg-white rounded shadow-sm" style="padding-top: 10px">
            <div class="media border p-3">
                <div class="media-body">
                  <div id = "replylist"></div>
                </div>
            </div>
        </div>
        <div class="container center" style="padding-bottom: 20px;">
            <button type="button" class="btn btn-primary" onclick="history.go(-1)">목록</button>
        </div>
</body>
</html>