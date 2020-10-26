<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=devide-width, intial-scale=1.0">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="/static/js/login.js"></script>
  <title>로그인</title>
</head>
<body>
	<!-- header -->
	<nav class="navbar navbar-light bg-light">
	  <a class="navbar-brand" href="/">
	    <img src="/static/image/AJlogo.jpg" height="35" class="d-inline-block align-top" alt="AJ로고" loading="lazy">
	    AJ
	  </a>
	</nav>
	<!-- login form -->
	<section class="container h-75">
		<h1>로그인</h1>
    	
	    <form action="/members/${id}" method="POST">
	    	<div class="form-group col-4">
				<label for="id">아이디 : </label>
				<input type="text" class="form-control" id="id" name="id">
			</div>
			<div class="form-group col-4">
				<label for="password">비밀번호 : </label>
				<input type="password" class="form-control" id="password" name="password">
			</div>
			<div class="form-group col">
				<div class="form-check">
				  <input class="form-check-input" type="checkbox" value="" id="chkId" name="chkId">
				  <label class="form-check-label" for="chkId">아이디저장</label>
				</div>
			</div>
			<div class="col">
				<button type="submit" class="btn btn-primary btn-login">로그인</button>
			</div>
	    </form>
    
	    <div class="row">
		    <div class="col-1">
				<a href="/members">회원가입</a>
		    </div>
		    <div class="col-2">
				<a href="#">아이디/비밀번호 찾기</a>
		    </div>
	    </div>
	</section>
	
	<!-- footer -->
	<footer class="container-fluid">
	    <div class="mx-auto" style="width: 500px;">
		    <p class="text-center">Copyright(C) 2020 AJ Networks All rights reserved.</p>
	    </div>
	</footer>
</body>
</html>