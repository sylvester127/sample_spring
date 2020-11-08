$(function() {
/*==============================================================================================*/
	let inputFile = $('.custom-file-input');
	let deleteBtns = $('#comments');
	let saveBtn = $('#saveButton');
	let fileList = [];
	let filesSize = 0;
/*==============================================================================================*/
	function showComments() {
		let tbody = $('#comments').children('table').children('tbody');
		
		tbody.empty();
		
		for(let i = 0; i < fileList.length; i++) {
			let template = document.querySelector('#row');
			let rowClone = document.importNode(template.content, true);
			
			let tr = $(rowClone).children('tr');
			
			let tdFileName = tr.children('td')[0];
			let tdFileSize = tr.children('td')[1];
			tdFileName.innerText = fileList[i].name;
			
			let nBytes = fileList[i].size;
			let sOutput = nBytes + " bytes";
			
			// multiples approximation을 위한 선택적 코드
			for (let aMultiples = ["KB", "MB", "GB", "TB", "PB"], nMultiple = 0, nApprox = nBytes / 1024; nApprox > 1; nApprox /= 1024, nMultiple++) {
			  sOutput = nApprox.toFixed(2) + " " + aMultiples[nMultiple];
			}
			tdFileSize.innerText = sOutput;
			tbody.append(tr);
		}
	} 
	
/*==============================================================================================*/
	/* 파일추가 버튼 */
	inputFile.change(function(e) {
		let files = $(this)[0].files;
		
		for(let i = 0; i < files.length; i++) {
			filesSize += files[i].size;

			if(filesSize > 20 * 1024 * 1024) {
				filesSize -= files[i].size;
				alert('첨부파일은 20mb를 넘을 수 없습니다.');
				return;
			}

			fileList.push(files[i]);
		}
		
		inputFile.val('');
		
		showComments();			
	});
	
/*==============================================================================================*/
	/* 파일 삭제 */
	deleteBtns.on('click', '#delete-file', function() {
		let index = $(this).parent().parent().index();
		
		filesSize -= fileList[index].size;
		fileList.splice(index, 1);
		
		showComments();
	});
	
/*==============================================================================================*/
	saveBtn.click(function(e) {
		let files = fileList;
		let form = $("#notice")[0];
		let data = new FormData(form);
		
		for (let i = 0; i < fileList.length; i++) {
			data.append('files', files[i]);
		}
		
		$.post({
        	url: '/notices/reg',
        	processData: false,
            contentType: false,
	        data: data,
	        type: 'POST',
			enctype: 'multipart/form-data',
	        success: function(result){
	            if(result == 1) {
	            	location.href = '/notices';
	            }
	        }
        });		
	});
});