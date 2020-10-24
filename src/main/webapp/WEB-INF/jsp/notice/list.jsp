<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>

<layoutTag:layout>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width" initial-scale=1.0>
    <title>Board Web Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
</head>
<body>
	<h1>${sessionScope.userId}님의 페이지</h1>
    <br><br>
    <h1 align = "center">조회 페이지</h1>
    <br><br><br>

    <div style="float:right; margin-right: 370px">
    <select name="align">
      <option>정렬</option>
      <option value="number">번호</option>
      <option value="date">작성일</option>
      <option value="writer">작성자</option>
      <option value="title">제목</option>
    </select>

    <input type="text" id="name" name="name" required
           minlength="4" maxlength="8" size="10">

    <button class="favorite styled" type="button">
        검색
    </button>
    </div>
    <br><br>

    <table border="2px" bordercolor="black" width="800" height="300" align = "center">
    	<tr align = "center" bgcolor="skybule">
        <th width="80">번호</th>
  		<th width="130">작성일</th>
        <th width="110">작성자</th>
        <th width="360">제목</th>
  		<th width="120">조회수</th>
    	</tr>
    	
    	<c:forEach var="l" items="${list}">
	    <tr align = "center">
  			<td onClick="location.href='/detail/${l.boardNum}'"><b>${l.boardNum}</b></td>
  			<td>${l.reg_date}</td>
  			<td>${l.writer}</td>
        	<td align = "left">${l.title}</td>
        	<td>${l.read_cnt}</td>
        </tr>
        </c:forEach>
      	
    </table>
    <br>

  <input id="writebutton" type="button" value="글쓰기" onClick="func_write()" style="float:right; margin-right: 370px">
  <script>
    var writebutton = document.getElementById('writebutton')
      writebutton.addEventListener('click', function func_write(){
        location.href='/insert';
      })
  </script>
  <br><br><br>

  <ul class="pagination justify-content-center">
  	<li class="page-item"><a class="page-link" href="#">Previous</a></li>
  	<li class="page-item"><a class="page-link" href="#">1</a></li>
  	<li class="page-item"><a class="page-link" href="#">2</a></li>
  	<li class="page-item"><a class="page-link" href="#">3</a></li>
  	<li class="page-item"><a class="page-link" href="#">Next</a></li>
  </ul>
  <form action="/logout">
  	<input type="submit" value="로그아웃" style="float:right; margin-right: 370px">
  </form>

</body>
</html>
</layoutTag:layout>