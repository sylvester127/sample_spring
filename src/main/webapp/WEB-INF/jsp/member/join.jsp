<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="UTF-8">
  	<meta name="viewport" content="width=devide-width, intial-scale=1.0">
	<title>회원가입</title>
</head>
<body>
	<form action="join" method="post">
		<label>아이디 : </label>
		<input type="text" name="userId"><br>
		<label>비밀번호 : </label>
		<input type="password" name="password"><br>
		<label>성명 : </label>
		<input type="text" name="name"><br>
		<label>email : </label>
		<input type="text" name="email"><br>
		<label>생년월일 : </label>
		<input type="number" name="birthday" placeholder="YYMMDD"><br>
		<label>주소 : </label>
		<input type="text" name="address"><br>
		<label>전화번호 : </label>
		<input type="text" name="phone"><br>
		<label>성별 : </label>
		<input type="text" name="sex" placeholder="남/여"><br><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>