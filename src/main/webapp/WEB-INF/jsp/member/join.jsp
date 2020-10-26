<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
	<meta charset="UTF-8">
  	<meta name="viewport" content="width=devide-width, intial-scale=1.0">
  	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
	<title>회원가입</title>
</head>
<body>
	<section class="container">
		<h1>회원가입</h1>
		<form action="members" method="POST">
			<div class="form-row mt-5">
				<div class="form-group col">
					<label for="id">아이디 : </label>
					<input type="text" class="form-control" id="id" name="id">
				</div>
				<div class="form-group col">
					<label for="password">비밀번호 : </label>
					<input type="password" class="form-control" id="password" name="password">
				</div>
			</div>
			<div class="form-row">
				<div class="form-group col">
					<label for="name">성명 : </label>
					<input type="text" class="form-control" id="name" name="name">
				</div>
				<div class="form-group col">
					<label for="phone">전화번호 : </label>
					<input type="text" class="form-control" id="phone" name="phone">
				</div>
			</div>
			<div class="form-group">
				<label for="email">email : </label>
				<input type="text" class="form-control" id="email" name="email">
			</div>
			<div class="form-group">
				<label for="address">주소 : </label>
				<input type="text" class="form-control" id="address" name="address">
			</div>
			<div class="form-row">
				<div class="form-group col">
					<label for="birthday">생년월일 : </label>
					<input type="date" class="form-control" id="birthday" name="birthday">
				</div>
				<div class="form-group col">
					<label for="sex">성별 : </label>
					<select class="form-control" id="sex" name="sex">
						<option selected>선택</option>
        				<option>남</option>
        				<option>여</option>
					</select>
				</div>
			</div>
			<div class="row">
				<button type="submit" class="btn btn-primary">회원가입</button>
				<div class="col"><p>${message}</p></div>
			</div>
		</form>
	</section>
</body>
</html>