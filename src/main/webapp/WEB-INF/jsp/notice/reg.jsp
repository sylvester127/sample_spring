<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="/static/js/reg.js"></script>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Board Web Project</title>
</head>
<body>
	<div class="container">
		<form id="notice">
			<h1 class="mt-4 text-center">등록 페이지</h1>

			<div class="form-group mt-5">
				<label>제목</label>
				<input type="text" class="form-control" id="title" name="title" required
				minlength="2" maxlength="80" size="100" placeholder="제목을 입력해 주세요">
			</div>
			
			<div class="form-group">
				<label>작성자</label>
				<input type="text" class="form-control-plaintext" id="writer" name="writer"
				minlength="2" maxlength="80" size="100" value="${sessionScope.userId}" readonly>
			</div>
			
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="8" id="content" name="content" minlength="2" 
				maxlength="3000" size="100" placeholder="내용을 입력해 주세요"></textarea>
			</div>
			
			<div class="input-group mb-3">
				<div class="input-group-prepend">
			    	<span class="input-group-text" id="file">파일</span>
			  	</div>
				<div class="custom-file">
				  <input multiple="multiple" type="file" class="custom-file-input" 
				  id="file" name="filename[]" aria-describedby="file">
				  <label class="custom-file-label" for="file">파일을 선택해주세요.</label>
				</div>
			</div>
			
			<div id="comments">
				<table class="table table-striped table-sm">
					<tbody>
					</tbody>
				</table>
			</div>

			<template id="row">
				<tr class="d-flex justify-content-end position-relative">
					<td class="position-absolute pl-2" style="left: 0px"></td>
					<td class="text-right"></td>
					<td><button type="button" id="delete-file" class="btn btn-primary btn-sm"
					name="deleteFile">파일삭제</button></td>
				</tr>
			</template>

			<div class="d-flex justify-content-end mt-5 position-relative">
				<div class="form-group row m-0 position-absolute" style="left: 0px">
					<label class="mr-2 col-form-label" for="password">비밀번호</label>
					<input type="password" class="form-control col" id="password" name="password">
				</div>
				<input id="saveButton" type="button" class="btn btn-primary mr-4" value="저장">
				<a href="/notices" role="button" class="btn btn-primary">취소</a>
			</div>
		</form>
	</div>
</body>
</html>