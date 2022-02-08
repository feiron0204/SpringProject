<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.modal {
          position: fixed;
          top: 0;
          left: 0;
          width: 100%;
          height: 100%;
          display: flex;
          justify-content: center;
          align-items: center;
          color: red;
        }

        .modal .bg {
          width: 100%;
          height: 100%;
          background-color: rgba(0, 0, 0, 0.6);
        }

        .modalBox {
          position: absolute;
          background-color: #fff;
          width: 400px;
          height: 200px;
          padding: 15px;
        }

        .modalBox button {
          display: block;
          width: 80px;
          margin: 0 auto;
        }

        .hidden {
          display: none;
        }
</style>

<h3>${ sessionScope.memName }님 로그인</h3>
<input type="button" value="회원정보수정" id="modifyBtn">
<input type="button" value="로그아웃" id="logoutBtn">
<input type="button" value="회원탈퇴" id="deleteModalBtn">
<div class="modal hidden">
  <div class="bg"></div>
  <div class="modalBox">
    <p>비밀번호를 재입력해주세요<br>
    <input type="hidden" id="id" value="${sessionScope.memId }">
    <input type="password" id="pwd" placeholder="비밀번호를 입력해주세요">
	    <input type="button" id="deleteBtn" value="확인">
	    <input type="button" id="closeBtn" value="취소"></p>
  </div>
</div>

<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
	$('#deleteBtn').click(function(){
		
		$.ajax({
			type: 'post',
			url:"/SpringProject/member/login",
			data:
			{
				'id':$('#id').val(),
				'pwd':$('#pwd').val()
			},
			dataType:'text',
			success:function(data){
				if(data =='ok'){
					$.ajax({
						type: 'post',
						url:"/SpringProject/member/delete",
						data:
						{
							'id':$('#id').val()
						},
						success:function(){
							alert("탈퇴완료");
							location.href='/SpringProject/index.jsp'
						},
						error:function(err){
							console.log(err);
							alert(err);
						}
					});
				}
				else if(data=='fail'){
				//비밀번호 다름	취소
					alert("비밀번호가 다릅니다.");
				}
				$('.modal').addClass("hidden");
			},
			error:function(err){
				console.log(err);
				alert(err);
			}
		});
	});
	$('#deleteModalBtn').click(function(){
		$('#pwd').val('');
		$('.modal').removeClass("hidden");
	});
	$('#closeBtn').click(function(){
		$('.modal').addClass("hidden");	
	});
	$('.bg').click(function(){
		$('.modal').addClass("hidden");	
	});
	
	$('#modifyBtn').click(function(){
		location.href='/SpringProject/member/modifyForm';
	});
	
	$('#logoutBtn').click(function(){
		$.ajax({
			type: 'post',
			url: '/SpringProject/member/logout',
			//data, dataType 없음
			success:function(){
				alert('로그아웃');
				location.href='/SpringProject/index.jsp';
			},
			error:function(err){
				alert(err);
			}
		});
	});
</script>















