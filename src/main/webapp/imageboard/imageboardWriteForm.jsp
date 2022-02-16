<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h3>이미지 등록</h3>
<!-- <form id="imageboardWriteForm" method="post" enctype="multipart/form-data" 
action="/SpringProject/imageboard/imageboardWrite" > -->
<form id="imageboardWriteForm">
	<table border="1" cellspacing="0" cellpadding="5">
		<tr>
			<td width="100" align="center">상품코드</td>
			<td>
				<input type="text" name ="imageId" size="50">
			</td>
		</tr>
		<tr>
			<td width="100" align="center">상품명</td>
			<td>
				<input type="text" name ="imageName" size="50">
			</td>
		</tr>
		<tr>
			<td width="100" align="center">단가</td>
			<td>
				<input type="text" name ="imagePrice" size="50">
			</td>
		</tr>
		<tr>
			<td width="100" align="center">개수</td>
			<td>
				<input type="text" name ="imageQty" size="50">
			</td>
		</tr>
		<tr>
			<td width="100" align="center">내용</td>
			<td>
				<textarea cols="50" rows="15" name="imageContent"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2" >
				<input type="file" name="img" >
			</td>
		</tr>
		<tr>
			<td colspan="2" >
				<input type="file" name="img" >
			</td>
		</tr>
		<tr>
			<td colspan="2" >
				<input type="file" name="img[]" multiple="multiple" ><!-- 파일을 한번에 여러개 선택 -->
			</td>
		</tr>
		<tr>
		
			<td colspan="2" align="center">
				<input type="button" id="imageboardWriteBtn" value="이미지등록">
				<input type="reset" value="다시작성">
			</td>
		</tr>
	</table>
</form>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$('#imageboardWriteBtn').click(function(){
	//1. 단순 submit
	console.log("ddd");
	//$('#imageboardWriteForm').submit();
	//이러면 등록도하기전에 리스트로넘어가버림
	//alert("등록성공");
	//location.href="/SpringProject/imageboard/imageboardList";
	
	//2. ajax
	var formData = new FormData($('#imageboardWriteForm')[0]);//<-form의 모든것
	$.ajax({
		type: 'post',
		url: '/SpringProject/imageboard/imageboardWrite',
		enctype: 'multipart/form-data',
		processData: false,
		contentType: false,
		data: formData,
		success: function(){
			alert('이미지등록완료');
			location.href="/SpringProject/imageboard/imageboardList";
		},
		error:function(err){
			console.log(err);
		}
	});
});
</script>

<!-- 
processData
-기본값은 true
-기본적으로 Query String 으로 변환해서 보내진다('변수=값&변수=값')
-파일 전송시에는 반드시 false로 해야한다 (formdata를 문자열로변환하지않음)

contentType
  - 기본적으로 "application/x-www-form-urlencoded; charset=UTF-8"
  - 파일 전송시에는 'multipart/form-data'로 전송이 될 수 있도록 false로 설정한다
 -->