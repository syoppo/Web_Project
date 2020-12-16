<!-- 스페인게시판 글작성 화면 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix ="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix ="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<title>게시글 작성</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    </head>
    <body>
		<jsp:include page="nav.jsp" flush="false"/>		
        <div class="container" style="padding-top:20px;">
            <h1 class=".display-4">유럽>스페인</h1>
                <form action="insert.do" method="post" class="form-horizontal">
                    <div class="table table-responsive" >
                        <table class="table table-striped"  class="justify-content-center">
                            <tr>
                                <td>작성자</td>
                                <td><input type="text"  class="form-control" name="writer" value="${nick }" readonly></td>
                            </tr>
                            <tr>
                                <td>제목</td>
                                <td><input type="text"  class="form-control" name="title" value=""></td>
                            </tr>
                            <tr>
                                <td>글내용</td>
                                <td><textarea rows="10" cols="50" name="content" id="editor" class="form-control"></textarea></td>
                            </tr>
                            <!-- 2. TEXT 편집 툴을 사용할 textarea -->
														<!-- <script>
												     //3. CKEditor5를 생성할 textarea 지정
												     
												      ClassicEditor
												      .create( document.querySelector( '#editor' ), {
												          plugins: [ Essentials, Paragraph, Bold, Italic ],
												          toolbar: [ 'bold', 'italic' ]
											        		removePlugins:['MediaEmbed']	//동영상 삭제

												      } )
												      .then( editor => {
												          console.log( 'Editor was initialized', editor );
												      } )
												      .catch( error => {
												          console.error( error.stack );
												      } );
												    </script> -->
                            <tr>
                                <td>사진</td>
                                <td><input type="file" name="filename"  class="btn btn-primary" value="업로드">
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"  class="text-center">            
                                    <input type="submit" value="작성" class="btn btn-primary">
                                    <input type="button"  class="btn btn-primary" onClick="history.go(-1)" value="취소">
                                </td>
                            </tr>
                  </table>
                  
                </form>
            </div>
        </div>
              
          </div>
</body>
</html>