<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여사모♡</title>
</head>
<body>
		<jsp:include page="nav.jsp" flush="false"/>		
			<div class="container-fluid">
          <div class="row pb-10 p-5">
                            <div class="col-md-4">
                                <p class="font-weight-bold mb-4"><a class="nav-link" href="nlist.do">공지사항　　　　　　　　　　　　　　　　+</a></p>
                                <hr>
                                <table class = "table table-bordered table-hover">
                                <tbody>
                                <tr>
                                <td>공지사항 입니다.</td>
                                </tr>
                                <tr>
                                <td>가입 후 꼭 읽어보세요.</td>
                                </tr>
                              </tbody>
                                </table>
                            </div>

                            <div class="col-md-4">
                                <p class="font-weight-bold mb-4"><a class="nav-link" href="자유게시판.html">자유게시판　　　　　　　　　　　　　　　+</a></p>
                                <hr>
                                <table class = "table table-bordered table-hover">
                                <tbody>
                                <tr>
                                <td>자유게시판1</td>
                                </tr>
                                <tr>
                                <td>자유게시판2</td>
                                </tr>
                                <tr>
                                <td>자유게시판3</td>
                                </tr>
                                </tbody>
                                </table>
                            </div>
                        </div>

          </div>
          <div class="container-fluid">
          <div class="row p-5">
                            <div class="col-md-8">
                                <p class="font-weight-bold mb-8">인기여행지</p>
                                <hr>
                                <div id="demo" class="carousel slide" data-ride="carousel">
  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <a class="nav-link" href="list.do"><img src="image/maxresdefault.jpg" alt="스페인" width="1100" height="500"></a>
      <div class="carousel-caption">
        <h3>스페인</h3>
        <p>우리는 스페인에서 좋은 시간을 보냈습니다^_^</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="image/1582046411_Australia16.jpg" alt="호주" width="1100" height="500">
      <div class="carousel-caption">
        <h3>호주</h3>
        <p>고마워요, 호주!</p>
      </div>
    </div>
    <div class="carousel-item">
      <img src="image/ff.jpg" alt="프랑스" width="1100" height="500">
      <div class="carousel-caption">
        <h3>프랑스</h3>
        <p>우리는 프랑스를 사랑해요!</p>
      </div>
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>
  </div>
  </div>
    </div>
    </body>
</html>