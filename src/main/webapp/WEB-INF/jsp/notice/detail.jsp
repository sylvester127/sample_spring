<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="/static/js/detail.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <title>Board Web Project</title>
</head>

<body>
    <h1 align = "center">상세 페이지</h1>
    <form action="/insertProc" method="post">
	    <table border="1" align = "center">
	      <tr align = "center">
	        <th width="740" id="title" name="title">
	          <textarea rows="1" cols="103" id="title" name="title" required minlength="2" maxlength="80" size="100" readonly>제목 :   ${detail.title}</textarea>
	        </th>
	      </tr>
	      <tr align = "center">
	        <th width="740" id="writer" name="writer">
	          <textarea rows="1" cols="103" id="writer" name="writer" required minlength="2" maxlength="80" size="100" readonly>작성자 :   ${detail.writer}</textarea>
	        </th>
	      </tr>
	      <tr align = "center">
	        <th width="740">
	          <textarea rows="10" cols="103" id="content" name="content" required minlength="2" maxlength="80" size="100" readonly>내용 :   ${detail.content}</textarea>
	        </th>
	      </tr>
	    
	    </table>
	
	    <%-- <table border="1" align = "center">
	      <tr>
	        <td width="80" align="center">파일</td>
	        <td>
	          <input type="text" id="filename" name="filename" required minlength="2" maxlength="80" size="89" onClick="location.href='/fileDown/${files.fileNum}'" value="${files.realFileName}" readonly>
	        </td>
	      </tr>
	    </table> --%>
    </form>

    <br><br>

    <div style="float:right; margin-right: 385px">
    <input id="listbutton" type="button" value="목록" onClick="func_list()">
	</div>
	
	<!-- 수정 시 비번 입력 기능 추가 필요 세션! -->
    <button class="favorite styled" type="button" onclick="location.href='/update/${detail.boardNum}'">
        수정
    </button>
    <button class="favorite styled" type="button" onClick="location.href='/delete/${detail.boardNum}'">
        삭제
    </button>
    
    
    <div class="container">
        <label for="content">comments</label>
        <form name="commentInsertForm">
            <div class="input-group">
               <input type="hidden" name="boardNum" value="${detail.boardNum}"/>
               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
               <span class="input-group-btn">
                    <button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
               </span>
            </div>
        </form>
    </div>
    
    <div class="container">
        <div class="commentList"></div>
    </div>
</body>
</html>