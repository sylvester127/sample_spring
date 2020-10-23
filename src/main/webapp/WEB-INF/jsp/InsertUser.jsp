<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<html>
<head>
	<title>회원가입</title>
</head>
<body>
<form action = "join" method="post">

아이디 : <input type = "text" name = "userId"><br>
비밀번호 : <input type ="password" name = "password"><br>
성명 : <input type = "text" name = "name"><br>
email : <input type = "text" name = "email"><br>
생년월일 : <input type = "number" name = "birthday" placeholder="YYMMDD"><br>
주소 : <input type = "text" name = "address"><br>
전화번호 : <input type = "text" name = "phone"><br>
성별 : <input type = "text" name = "sex" placeholder="남/여"><br><br>

<input type ="submit" value = "회원가입"><br>

</form>
</body>
</html>