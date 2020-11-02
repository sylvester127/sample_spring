<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<title>Board Web Project</title>
</head>
<body>
	<div class="container">
		<form action="/insertProc" method="post" enctype="multipart/form-data">
			<br> <br>
			<h1 align="center">등록 페이지</h1>
			<br> <br> <br>

			<div class="form-group">
				<label>제목</label>
				<input type="text" class="form-control" id="title" name="title" required
				minlength="2" maxlength="80" size="100" placeholder="제목을 입력해 주세요">
			</div>
			
			<div class="form-group">
				<label>작성자</label>
				<input type="text" class="form-control-plaintext" id="writer" name="writer" required
				minlength="2" maxlength="80" size="100" value="${sessionScope.userId}" readonly>
			</div>
			
			<div class="form-group">
				<label>내용</label>
				<textarea class="form-control" rows="8" id="content" name="content" required minlength="2" 
				maxlength="3000" size="100" placeholder="내용을 입력해 주세요"></textarea>
			</div>
			
			<table id="myTable" border="1" align="center">
				<tr>
					<td width="80" align="center">파일</td>
					<td width="510"><span id="custom-text">선택된 파일이 없습니다.</span></td>
					<td><input type="file" id="real-file" name="realfile"
						hidden="hidden" /> <input type="button" id="search-file"
						name="searchfile" class="searchfile" value="찾아보기.."></td>
				</tr>

			</table>
			<table>
				<tr>
					<td><input style="float: left; margin-left: 385px"
						type="button" id="input-file" name="inputfile" class="inputfile"
						value="파일추가"></td>
				</tr>
				<tr>
					<br>
					<input style="float: right; margin-right: 385px" type="button"
						id="delete-file" name="delete-file" value="파일삭제">
					<br>
					<script>
						var realFileBtn = document.getElementById("real-file");
						var customTxt = document.getElementById("custom-text");
						var searchFileBtn = document
								.getElementById("search-file");
						var inputFileBtn = document
								.getElementById("input-file");
						var deleteBtn = document.getElementById('delete-file');

						searchFileBtn.addEventListener("click", function() {
							realFileBtn.click();
						});

						realFileBtn
								.addEventListener(
										"change",
										function() {
											if (realFileBtn.value) {
												customTxt.innerHTML = realFileBtn.value
														.match(/[\/\\]([\w\d\s\.\-\(\)]+)$/)[1];
											} else {
												customTxt.innerHTML = "선택된 파일이 없습니다.";
											}
										});

						inputFileBtn
								.addEventListener(
										"click",
										function() {
											var table = document
													.getElementById("myTable");
											var row = table.insertRow(-1);
											var cell1 = row.insertCell(0);
											var cell2 = row.insertCell(1);
											var cell3 = row.insertCell(2);
											cell1.innerHTML = "파일";
											cell1.align = "center";
											cell2.innerHTML = customTxt.innerHTML;
											cell3.innerHTML = "<input type='button' value='찾아보기..' onClick='realFileBtn.click()'>";
										});
						deleteBtn.addEventListener(
								'click',
								function() {
									if (confirm("첨부파일을 삭제하시겠습니까")) {
										alert("첨부파일을 삭제했습니다.");
										document.getElementById("myTable")
												.deleteRow(-1);
										// var e = document.getElementById("filename");
										// e.parentElement.removeChild(e);
									} else {
										alert("삭제 취소");
									}
								})
					</script>
				</tr>
			</table>
			<br>

			<table border="1" style="float: left; margin-left: 385px">
				<tr>
					<td>비밀번호</td>
					<td><input type="password" id="password"></td>
				</tr>
			</table>

			<div style="float: right; margin-right: 385px">

				<input id="listbutton" type="button" value="목록"
					onClick="func_list()">
				<script>
					var listbutton = document.getElementById('listbutton')
					listbutton.addEventListener('click', function func_list() {
						location.href = 'list';
					})
				</script>

				<input id="savebutton" type="submit" value="저장"
					onClick="func_confirm()">
				<script>
					var savebutton = document.getElementById('savebutton')
					savebutton.addEventListener('click',
							function func_confirm() {
								if (confirm("게시글을 저장하시겠습니까")) {
									alert("게시글이 저장되었습니다.");

								} else {
									alert("게시글이 저장되지 않았습니다.");
								}
							})
				</script>
			</div>
		</form>
	</div>
</body>
</html>