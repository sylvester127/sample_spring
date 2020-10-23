<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
