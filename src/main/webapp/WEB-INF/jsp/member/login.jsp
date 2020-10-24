<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=devide-width, intial-scale=1.0">
  <title>로그인</title>
</head>
<body>
	<section class="login">
		<label style="float:left; margin-left: 620px">
	    	<img src = "https://gw.ajway.kr/ekp/inc/file/fileView?fileUrl=/banner/image/2019/01/15&fileName=feca97d9-6c0f-4fbc-9060-332ea99f8a4c" alt="AJ로고">
	  	</label>
	
		<h2>로그인</h2>
    	
	    <form action="/login" method="post">
		    <ul>
				<li><input type="text" placeholder="아이디" title="아이디입력" name="id"></li>
				<li><input type="password" placeholder="비밀번호" title="비밀번호입력" name="pw"></li>
				<li><input type="checkbox" id="chk_id"><label for="chk_id">아이디저장</label></li>
				<li><input type=submit value="로그인"></input></li>
		    </ul>
	    </form>
    
	    <div>
		    <ul>
				<li><a href="/join">회원가입</a></li>
				<li><a href="">아이디 찾기</a></li>
				<li><a href="">비밀번호 찾기</a></li>
		    </ul>
	    </div>
	    
	    <br><br><br><br><br><br>
	    
	    <div>
		    <a href="http://www.ajnetworks.co.kr/aj/index.do">
		    	Copyright(C) 2020 AJ Networks All rights reserved.
		   	</a>
	    </div>
	</section>
</body>
</html>