<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
    <title>Board Web Project</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
    <script type="text/JavaScript"  src=http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js></script>
</head>

<body>
    <br><br>
    <h1 align = "center">상세 페이지</h1>
    <br><br><br>

    <table border="1" align = "center">
    <form action="/insertProc" method="post">
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
    <br>

    <table border="1" align = "center">
      <tr>
        <td width="80" align="center">파일</td>
        <td>
          <input type="text" id="filename" name="filename" required minlength="2" maxlength="80" size="89" onClick="location.href='/fileDown/${files.fileNum}'" value="${files.realFileName}" readonly>
        </td>
      </tr>
    </form>
    </table>

    <br><br>

    <div style="float:right; margin-right: 385px">
    <input id="listbutton" type="button" value="목록" onClick="func_list()">
    <script>
      var listbutton = document.getElementById('listbutton')
        listbutton.addEventListener('click', function func_list(){
          location.href='/list';
        })
    </script>
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


<script>
	var boardNum = '${detail.boardNum}'; //게시글 번호
	
	$('[name=commentInsertBtn]').click(function(){ //댓글 등록 버튼 클릭시 
	    var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
	    commentInsert(insertData); //Insert 함수호출(아래)
	});

	//댓글 목록 
	function commentList(){
	    $.ajax({
	        url : '/comment/list',
	        type : 'get',
	        data : {'boardNum':boardNum},
	        success : function(data){
	            var a =''; 
	            $.each(data, function(key, value){ 
	                a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
	                a += '<div class="commentInfo'+value.commentNum+'">'+'댓글번호 : '+value.commentNum+' / 작성자 : '+value.writer;
	                a += '<a onclick="commentUpdate('+value.commentNum+',\''+value.content+'\');"> 수정 </a>';
	                a += '<a onclick="commentDelete('+value.commentNum+');"> 삭제 </a> </div>';
	                a += '<div class="commentContent'+value.commentNum+'"> <p> 내용 : '+value.content +'</p>';
	                a += '</div></div>';
	            });
	            
	            $(".commentList").html(a);
	        }
	    });
	}
	 
	//댓글 등록
	function commentInsert(insertData){
	    $.ajax({
	        url : '/comment/insert',
	        type : 'post',
	        data : insertData,
	        success : function(data){
	            if(data == 1) {
	                commentList(); //댓글 작성 후 댓글 목록 reload
	                $('[name=content]').val('');
	            }
	        }
	    });
	}
	 
	//댓글 수정 - 댓글 내용 출력을 input 폼으로 변경 
	function commentUpdate(commentNum, content){
	    var a ='';
	    
	    a += '<div class="input-group">';
	    a += '<input type="text" class="form-control" name="content_'+commentNum+'" value="'+content+'"/>';
	    a += '<span class="input-group-btn"><button class="btn btn-default" type="button" onclick="commentUpdateProc('+commentNum+');">수정</button> </span>';
	    a += '</div>';
	    
	    $('.commentContent'+commentNum).html(a);
	    
	}
	 
	//댓글 수정
	function commentUpdateProc(commentNum){
	    var updateContent = $('[name=content_'+commentNum+']').val();
	    
	    $.ajax({
	        url : '/comment/update',
	        type : 'post',
	        data : {'content' : updateContent, 'commentNum' : commentNum},
	        success : function(data){
	            if(data == 1) commentList(boardNum); //댓글 수정후 목록 출력 
	        }
	    });
	}

	//댓글 삭제 
	function commentDelete(commentNum){
	    $.ajax({
	        url : '/comment/delete/'+commentNum,
	        type : 'post',
	        success : function(data){
	            if(data == 1) commentList(boardNum); //댓글 삭제후 목록 출력 
	        }
	    });
	}

	$(document).ready(function(){
	    commentList(); //페이지 로딩시 댓글 목록 출력 
	});
</script>
</body>
</html>
</layoutTag:layout>