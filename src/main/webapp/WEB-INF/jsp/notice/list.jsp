<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <meta name="viewport" content="width=device-width initial-scale=1.0">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>notice</title>
</head>
<body>
	<main>
		<div class="d-flex justify-content-end">
			<h4 class="my-auto">${sessionScope.id}님의 페이지</h4>
			
			<form class="my-auto" action="/members/${id}/session" method="POST">
				<button class="ml-3 btn btn-primary btn-sm" type="submit" value="DELETE">로그아웃</button>
			</form>
		</div>
		
		<article class="mt-5 mx-auto  w-75">
		    <h1 class="d-flex justify-content-center">조회 페이지</h1>
		    
		    <div class="mt-5 d-flex justify-content-end input-group">
			    <select class="form-control col-md-1" name="align">
			      <option>정렬</option>
			      <option value="number">번호</option>
			      <option value="date">작성일</option>
			      <option value="writer">작성자</option>
			      <option value="title">제목</option>
			    </select>
			
			    <input type="text" class="form-control col-md-3" id="name" name="name" required minlength="4" maxlength="8" size="10">
			
			    <button class="btn btn-primary" type="button">검색</button>
		    </div>
		    
		    <div class="mt-2 md-5 table-responsive">
				<table class="table table-hover">
			    	<thead class="thead-light">
				    	<tr align = "center" bgcolor="skybule">
					        <th scope="col" width="80">번호</th>
					  		<th scope="col" width="130">작성일</th>
					        <th scope="col" width="110">작성자</th>
					        <th scope="col" width="360">제목</th>
					  		<th scope="col" width="120">조회수</th>
				    	</tr>
			    	</thead>
			    	<tbody>
				    	<c:forEach var="notice" items="${list}">
						    <tr align = "center">
					  			<td onClick="location.href='/detail/${notice.boardNum}'"><b>${notice.boardNum}</b></td>
					  			<td>${notice.reg_date}</td>
					  			<td>${notice.writer}</td>
					        	<td align = "left">${notice.title}</td>
					        	<td>${notice.read_cnt}</td>
					        </tr>
				        </c:forEach>
			    	</tbody>      	
			    </table>
		    </div>
		    
			<nav class="d-flex position-relative">
				<ul class="pagination m-auto">
					<li class="page-item"><a class="page-link" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">Next</a></li>
				</ul>
			
				<div class="position-absolute my-auto" style="right: 0px;">
				  	<a class="btn btn-primary" href="/notices/reg" role="buuton">글쓰기</a>
				</div>
			</nav>
		</article>
	</main>
</body>
</html>