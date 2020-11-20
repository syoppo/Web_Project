<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>JSP AJAX</title>
	<link rel="stylesheet" href="css/bootstrap.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>
	
	<script type="text/javascript">
	
		var registerRequest = new XMLHttpRequest();		
		
	
		function register(){
			registerRequest.onreadystatechange = registerProcess();
			
 			var reply = encodeURIComponent(document.getElementById("reply").value);

			registerRequest.open("POST", "../register?reply=" + reply, true); 
			registerRequest.send(null);
		}
		
		function registerProcess(){
			if(registerRequest.readyState == 4 && registerRequest.status == 200){
				var result = registerRequest.responseText;
				
				if(result!=1){
					alert("등록실패");
				}
				else{
					var reply = document.getElementById("reply");
					reply.value="";
				}
				searchFunction();
			}
			
		}
		window.onload = function(){
			searchFunction();
		}
		
	</script>
</head>
<body>
	<br>
	<div class="container">
		<table class="table" style="text-align:center; border: 1px solid #ddddddd">
				<tr>
					<td style="background-color:#fafafa; text-align:center">댓글 : </td>
					<td><input class="form-control" type="text" id="reply" size="100"></td>
					<td colspan="2"><button class="btn btn-primary pull-right" onclick="register();">등록</button></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>