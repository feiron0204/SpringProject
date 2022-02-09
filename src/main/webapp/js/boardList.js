$(function(){

	$.ajax({
		type:'post',
		url:'/SpringProject/board/getBoardList',
		data:'pg='+$('#pg').val(),
		dataType:'json',
		success:function(data){
			$('#search').val('off');
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
				/*
			$.ajax({
				type:'post',
				url:'/miniProject/board/findBoard.do',
				data:'pseq='+items.pseq,
				dataType:'text',
				success:function(data){
					
					let data1=data.trim();
					console.log(data1);
					if(data1=='false'){
						$('.subjectA_'+items.seq).before($('<span/>',{
						class:'red',
						text:"[원글이 삭제된 답글]"
					}));
					}
				},
				error:function(e){
					alert(e);
				}
			});
				*/
				
			});//each
			
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


