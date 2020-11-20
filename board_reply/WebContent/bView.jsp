<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>  
<%@ page import = "cs.dit.dao.*, cs.dit.dto.*, java.util.List, java.sql.Date" %>
<%@page import="org.json.simple.JSONArray" %>
<%@page import="org.json.simple.JSONObject" %>
   
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  
	<title>게시판</title>

</head>
<body>
<div class="container">
	<h2>상세보기</h2>
	<br/>
	<form action="list.do" method="post">
		<input type="hidden" name="bcode" value="${dto.bcode}" id="bcode">
		<table class="table table-striped table-hover">
			<tr>
				<th>bcode</th><td>${dto.bcode}</td>
				<th>subject</th><td><input type="text" value="${dto.subject}" name="subject"></td>
			</tr>
			<tr>
				<th>content</th><td><input type="text" value="${dto.content}" name="content"></td>
				<th>writer</th><td><input type="text" value="${dto.writer}" name="writer"></td>
			</tr>
			<tr>
				<th>regDate</th><td colspan="3"><input type="text" value="${dto.regDate}" name="regDate"></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="submit" value ="멤버 목록" >
					<input type="button" value ="홈으로" onclick ="location.href='index.html'">
				</td>
			</tr>
		</table><br><br>
	</form>
</div>

<!-- 댓글 달기 -->
	<script type="text/javascript">
	
		var xhr1 = new XMLHttpRequest();		//rlist
		var xhr2 = new XMLHttpRequest();		//rinsert
		
		var bcode = encodeURIComponent(document.getElementById("bcode").value);
		
 		function replylist(){	
		//to do
 			var table = document.getElementById("replyTable");
 			table.innerHTML = "";
 			
 			xhr1.onreadystatechange = function(){
 				if(this.readyState == 4 && this.status == 200){
 					var json = this.responseText;
 					json = json.substring(json.indexOf("["), json.lastIndexOf("]")+1);
 					
 					var list = JSON.parse(json);
 					
 					for(var i=0 in list){
 						var row = table.insertRow(0);
 						var cell1 = row.insertCell(0);
 						var cell2 = row.insertCell(1);
 						var cell3 = row.insertCell(2);
 						
 						cell1.innerHTML = list[i].rcode;
 						cell2.innerHTML = list[i].reply;
 					}
 				}
 			};
			
			xhr1.open("POST", "/board_report/rlist.rp", true);
			
			//서버에서는 이를 통해 서버로 전달된 변수가 form을 통해 전달된 정보로 간주
			xhr1.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8;");
			var data='';
			data += 'bcode=' + bcode;
			xhr1.send(data);
		}		
 		
 		function rinsert(){
	 		var reply = encodeURIComponent(document.getElementById("reply").value);
 			
     // to do
 			xhr2.open('POST', '/board_report/insert.rp', true);
		} 
		
		window.onload=function(){
			replylist();
		}
	</script>
</head>
<body>
	<br>
	<div class="container">
	<form name="myForm" method="post">
	</form>
		<table class="table" style="text-align:center; border: 1px solid #ddddddd">
				<tr>
					<td style="background-color:#fafafa; text-align:center">댓글 : </td>
					<td><input class="form-control" type="text" id="reply" size="100"></td>
					<td colspan="2"><button class="btn btn-primary pull-right" onclick="rinsert();">한줄 댓글 작성</button></td>
				</tr>
			</tbody>
		</table>
		<table class="table" style="text-align:center; border:1px solid #dddddd">
			<tbody id ="replyTable">
			
			</tbody>
		</table>
	</div>
</body>
</html>