<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css" >
#imageboardListTable th{
	font-size: 16px;
}

#imageboardListTable td{
	font-size: 13px;
}

#imageboardListTable{
	border-color:yellow;
	margin-left: 10pt;
}
/* a태그 활성화순간 */
.imageNameA:link{
		color: white;
		text-decoration:none;
}
/* 클릭당한경험 유 */
.imageNameA:visited{
		color: white;
		text-decoration:none;
}
/* 마우스올라왔을때 */
.imageNameA:hover{
		color: cyan;
		text-decoration:underline;
}
/* 마우스클릭동안 */
.imageNameA:active{
		color: cyan;
		text-decoration:none;
}

#imageboardPagingDiv span {
	margin: 5px;
	padding: 5px;
	border: 1px white solid;
}

#currentPaging{
	color:red;
	text-decoration:underline;
	cursor:pointer;
}

#paging{
	color:black;
	text-decoration:none;
	cursor:pointer;
}

#imageboardPagingDiv{
text-align: center;
font-size:13pt;
margin-top: 10px;
}
</style>   

<form name="" id="imageboardListForm" method="post" action="/SpringProject/imageboard/imageboardDelete">
<input type="hidden" name="pg" id="pg" value="${pg}">    
<table border="1" cellspacing="0" cellpadding="5" id="imageboardListTable" frame="hsides" rules="rows">
	<tr>
		<th width="100">
		<input type="checkbox" id="all" >글번호</th>
		<th width="100">이미지</th>
		<th width="150">상품명</th>
		<th width="150">단가</th>
		<th width="100">개수</th>
		<th width="150">합계</th>
		<fmt:formatNumber pattern=""></fmt:formatNumber>
	</tr>
</table>
<input id="imageboardDeleteBtn" type="button" value="선택삭제" style="float: left; margin: 5px 10px">
<div id="imageboardPagingDiv" style="text-align:center; width: 750; font-size: 15pt" ></div>
</form>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- <script type="text/javascript" src="/miniProjectT/js/imageboardList.js"></script> -->
<script type="text/javascript">
function imageboardPaging(pg2){
	location.href="/SpringProject/imageboard/imageboardList?pg="+pg2;
}
/*
function checkAll(){
	//alert("체크박스의 개수 = "+document.getElementsByName("check").length);
	//alert("글번호 체크 여부 = "+document.getElementById("all").checked);
	
	var check = document.getElementsByName("check");
	if(document.getElementById("all").checked){
		for(i=0;i<check.length;i++){
			check[i].checked=true;
		}
	}else{
		for(i=0;i<check.length;i++){
			check[i].checked=false;
		}
	}
}
*/
$(function(){
	$.ajax({
		type:'post',
		url:'/SpringProject/imageboard/getImageboardList',
		data:'pg='+$('#pg').val(),
		dataType:'json',
		success:function(data){
			$.each(data.list,function(index,items){
				$('<tr/>')
				.append($('<td/>', {align:'center',text:items.seq})
						.prepend($('<input>',{type:'checkbox',name:'check',class:'check',value:items.seq})))
				.append($('<td/>', {align:'center'})
						.append($('<img/>',{src:'/SpringProject/storage/'+items.image1,
							//class:'img_'+items.seq,
							class:'img',
							style:'width:70px;height:70px;cursor:pointer',alt:items.imageName})))
				.append($('<td/>', {align:'center'})
						.append($('<a/>',{href:'/SpringProject/imageboard/imageboardView?pg='+$('#pg').val()+'&seq='+items.seq,class:'imageNameA',text:items.imageName})))
				.append($('<td/>', {align:'center',class:'number',text:items.imagePrice.toLocaleString()}))
				.append($('<td/>', {align:'center',class:'number',text:items.imageQty.toLocaleString()}))
				.append($('<td/>', {align:'center',class:'number',text:(items.imagePrice*items.imageQty).toLocaleString()}))
						.appendTo($('#imageboardListTable'));
				
		/* 	$('.img_'+items.seq).click(function(){
				console.log($(this));
				location.href = '/SpringProject/imageboard/imageboardView?seq='+items.seq+'&pg='+$('#pg').val();
				}); */

			});
			//$.each($('.number'),function(){
			//	this.innerText = this.innerText.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
			//});
			
			$('.img').click(function(){
				//console.log($(this).parent().prev().text());
				
				var seq=$(this).parent().prev().text();
				location.href = '/SpringProject/imageboard/imageboardView?seq='+seq+'&pg='+$('#pg').val();
			});
			$('#imageboardPagingDiv').html(data.imageboardPaging.pagingHTML);
		},
		error:function(err){
			alert("겟이미지보드리스트");
			console.log(err);
		}
	});
});
//전체선택전체해제
$('#all').click(function(){
	//alert($('#all').attr('checked'));//이러면 정의되지않음! 이라고뜸...미리 checked라고 attribute를 준다고해도 계속 checked만 나옴
	//alert($('#all').prop('checked'));//이래야 ture, false뜸
	if($('#all').prop('checked')){
		$('input[name=check]').prop('checked',true);
	}else{
		$('input[name=check]').prop('checked',false);
	}
});

$('#imageboardDeleteBtn').click(function(){
	$.ajax({
		type:'post',
		data:$('#imageboardListForm').serialize(),
		url:'/SpringProject/imageboard/imageboardDelete',
		success:function(){
			alert('삭제완료');
			location.href="/SpringProject/imageboard/imageboardList";
		},
		error:function(err){
			console.log(err);
		}
	});//ajax
});
</script>

<%-- 
attr()
 - HTML에 작성된 속성값을 문자열로 가져온다
 
prop()
 - 자바스크립트의 프로퍼티를 가져온다
 - 자바스크립트의 프로퍼티 값이 넘어오므로 boolean, date, function등을 가져올 수 있다.
[형식]
prop(key) -> key에 해당하는 속성값을 가져온다
prop(key, value) -> key에 해당하는 속성값을 추가한다
 --%>