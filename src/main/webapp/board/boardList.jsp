<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css" >
#boardListTable th{
	font-size: 16px;
}

#boardListTable td{
	font-size: 13px;
}

#boardListTable{
	border-color:yellow;
	margin-left: 10pt;
}
/* a태그 활성화순간 */
.subjectA:link{
		color: white;
		text-decoration:none;
}
/* 클릭당한경험 유 */
.subjectA:visited{
		color: white;
		text-decoration:none;
}
/* 마우스올라왔을때 */
.subjectA:hover{
		color: green;
		text-decoration:underline;
}
/* 마우스클릭동안 */
.subjectA:active{
		color: cyan;
		text-decoration:none;
}

#boardPagingDiv span {
	margin: 5px;
	padding: 5px;
	border: 1px white solid;
}

#currentPaging{
	color:red;
	cursor:pointer;
}

#paging{
	cursor:pointer;
}

#boardPagingDiv{
text-align: center;
font-size:13pt;
margin-top: 10px;
}
.red{
	color:red;
}
</style>   
<input type="text" name="pg" id="pg" value="${pg}">    
<input type="text" name="search" id="search" value="off">
<table border="1" cellspacing="0" cellpadding="5" id="boardListTable" frame="hsides" rules="rows">
	<tr>
		<th width="100">글번호</th>
		<th width="300">제목</th>
		<th width="100">작성자</th>
		<th width="100">작성일</th>
		<th width="100">조회수</th>
	</tr>
</table>
<div id="boardPagingDiv"></div>
<div id="searchDiv" style="text-align: center; margin: 10px" >
<form id="searchForm" >
	<input type="text" id="searchPg" name="pg" >
	<select id="searchOption" name="searchOption" >
		<option value="subject">제목</option>
		<option value="id">작성자</option>
	</select>
	<input type="text" id="keyword" name="keyword">
	<input type="button" id="boardSearchBtn" value="검색">
</form>
</div>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/SpringProject/js/boardList.js"></script>
<script type="text/javascript">

function boardPaging(pg2){
	if($('#search').val()=='off'){	
		location.href="/SpringProject/board/boardList?pg="+pg2;
	}else if($('#search').val()=='on'){
		$('#searchPg').val(pg2);
		$('#boardSearchBtn').trigger('click');
	}
}

$('#boardSearchBtn').click(function(){
	$('#boardListTable').empty();
	$.ajax({
		type:'post',
		url:'/SpringProject/board/getBoardSearchList',
		data:$('#searchForm').serialize(),
		dataType:'json',
		success:function(data){
			$('#search').val('on');
				$('<tr/>').append($('<th/>',{width:100,text:'글번호'}))
						  .append($('<th/>',{width:300,text:'제목'}))
						  .append($('<th/>',{width:100,text:'작성자'}))
						  .append($('<th/>',{width:100,text:'작성일'}))
						  .append($('<th/>',{width:100,text:'조회수'}))
						  .appendTo($('#boardListTable'));
			$.each(data.list,function(index,items){
				
				$('<tr/>')
				.append($('<td/>',{
					align: 'center',
					text:items.seq
				}))
				.append($('<td/>')
				.append($('<a/>',{
					href:'#',
					text:items.subject,
					class:'subjectA  subjectA_'+items.seq
				})))
				.append($('<td/>',{
					align: 'center',
					text:items.id
				}))
				.append($('<td/>',{
					align: 'center',
					text:items.logtime
				}))
				.append($('<td/>',{
					align: 'center',
					text:items.hit
				}))
				.appendTo($('#boardListTable'));
				
				if(items.lev!=0){
					for(i=0;i<items.lev;i++){
					$('.subjectA_'+items.seq).before('&emsp;');	
					}
					$('.subjectA_'+items.seq).before($('<img/>',{
						src:"/SpringProject/image/reply.gif"
					}));
				}
			});
				//로그인 여부
				$('.subjectA').click(function(a){
					console.log($(this).parent().prev().text());
					if(data.memId==null){
					    alert('먼저 로그인 하세요');
					}else{
						location.href='/SpringProject/board/boardView?seq='+$(this).parent().prev().text()+'&pg='+$('#pg').val();
					}
				});//click
				
			
				$('#boardPagingDiv').html(data.boardPaging);
		},
		error:function(err){
			alert(err);
		}
	});
});
</script>
