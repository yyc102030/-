//封装笔记相关的操作功能

//恢复回收站笔记
function replayRallBackNote(){
	//获取请求参数
	var noteId=$(this).parents("li").data("rallBackNoteId");
	console.log("noteId:"+noteId);
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/replaynote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("恢复完成！");
				loadRallBackNotes();
			}else{
				alert("恢复失败！");
			}
		},
		error:function(){
			alert("恢复异常！")
		}
	});
	return false;
}

//将笔记从回收站删除
function deleteRallBackNote(){
	//获取请求参数
	var noteId=$(this).parents("li").data("rallBackNoteId");
	console.log("noteId:"+noteId);
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/deletenote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("删除成功！");
				loadRallBackNotes();
			}else{
				alert("删除失败！");
			}
		},
		error:function(){
			alert("删除异常！")
		}
	});
	return false;
}

//在预览笔记模块显示body和title
function LoadRallBackNoteBody(){
	$("#rallBackNotes a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取参数
	var noteId=$(this).data("rallBackNoteId");
	console.log("noteId:"+noteId);
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/loadrallbacknotebody.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			console.log(result);
			if(result.status==0){
				//查询成功
				//在预览笔记模块显示body和title
				var noteBody=result.data.cn_note_body;
				var noteTitle=result.data.cn_note_title;
				console.log("noteBody:"+noteBody+",noteTitle:"+noteTitle);
				$("#noput_note_title").html(noteTitle);
				$("#noput_note_body").html(noteBody);
			}
		},
		error:function(){
			alert("显示异常");
		}
	});
}

//显示回收站笔记
function loadRallBackNotes(){
	$("#pc_part_2").hide();
	$("#pc_part_3").hide();
	$("#pc_part_4").show();
	$("#pc_part_5").show();
	$("#rallBackNotes").html("");
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/rallback.do",
		type:"post",
		dataType:"json",
		success:function(result){
		if(result.status==0){
				//获取回收站所有笔记
				var rallBackNotes=result.data;
				for(var i=0;i<rallBackNotes.length;i++){
					var rallBackNoteId=rallBackNotes[i].cn_note_id;
					var rallBackNoteTitle=rallBackNotes[i].cn_note_title;
					var sli="";
					sli+="<li class='disable'>";
					sli+="<a>";
					sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'>";
					sli+="</i>"+rallBackNoteTitle;
					sli+="<button type='button' class='btn btn-default btn-xs btn_position btn_delete'>";
					sli+="<i class='fa fa-times'></i>";
					sli+="</button>"
					sli+="<button type='button' class='btn btn-default btn-xs btn_position_2 btn_replay'>";
					sli+="<i class='fa fa-reply'></i>";
					sli+="</button>";
					sli+="</a>";
					sli+="</li>";
					var $li=$(sli);
					$li.data("rallBackNoteId",rallBackNoteId);
					$("#rallBackNotes").append($li);
				}
			}else{
				alert("抱歉！回收站还是空的");
			}
		},
		error:function(){
			alert("显示异常");
		}
	});
}

//加载搜索笔记结果笔记的内容
function loadShareNoteBody(){	
	$("#pc_part_6 a").removeClass("checked");
	$(this).find("a").addClass("checked");
	var shareNoteId=$(this).data("shareNoteId");
	console.log(shareNoteId);
	//检查格式
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/loadsharenotebody.do",
		type:"post",
		data:{"shareNoteId":shareNoteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var noteBody=result.data.cn_share_body;
				console.log(noteBody);
				var noteTitle=result.data.cn_share_title;
				//将笔记的内容和标题显示到编辑页面
				$("#noput_note_title").html(noteTitle);
				$("#noput_note_body").html(noteBody);
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("查询笔记异常");
		}
	});	
}

//搜索结果点击更多，显示下一页
function searchLoadMore(){
	//获取请求参数
	var value=$("#search_note").val().trim();
	console.log(value);
	var inValue="%"+value+"%";
	var loadPageNum=$("#loadPageNum").val();
	page=parseInt(loadPageNum)+1;	                   
	var pagesize=5;
	var start=(page-1)*pagesize;
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/searchloadmore.do",
    	type:"post",
    	data:{"inValue":inValue,"start":start,"pagesize":pagesize},
    	dataType:"json",
    	success:function(result){
    		if(result.status==0){
    			var lis=result.data;
    			//成功
    			for(var i=0;i<lis.length;i++){
    				var shareNoteTitle=lis[i].cn_share_title;
        			var shareNoteId=lis[i].cn_share_id;
    				var sli="";
        			sli+="<li class='online'>";
        			sli+="<a>"
        			sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
        			sli+=""+shareNoteTitle;
        			sli+="</a>"
        			sli+="</li>"
        			var $li=$(sli);
					$li.data("shareNoteId",shareNoteId);
					$("#seachnotes").append($li);
				}
    		}else{
    			alert(result.msg);
    		}
    	},
    	error:function(){
    		alert("加载异常");
    	}
	});
	$("#loadPageNum").val(page);
}

//搜索笔记
function searchNote(){
	$("#loadPageNum").val("1");
	var lis;
	//获取请求参数
	var value=$("#search_note").val().trim();
	var inValue="%"+value+"%";
    $.ajax({
    	url:"/cloude_note/note/searchnote.do",
    	type:"post",
    	data:{"inValue":inValue},
    	dataType:"json",
    	success:function(result){
    		if(result.status==0){
    			$("#pc_part_2").hide();
    			$("#pc_part_3").hide();
    			$("#pc_part_6").show();
    			$("#pc_part_5").show();
    			console.log(result.data);
    			lis=result.data;        			
    			console.log("shareNoteTitle:"+shareNoteTitle+",shareNoteId:"+shareNoteId);
    			//清空上一次的搜索结果
    			$("#seachnotes").html("");
    			if(lis.length>5){
    				for(var i=0;i<5;i++){
        				var shareNoteTitle=lis[i].cn_share_title;
            			var shareNoteId=lis[i].cn_share_id;
        				var sli="";
            			sli+="<li class='online'>";
            			sli+="<a>"
            			sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
            			sli+=""+shareNoteTitle;
            			sli+="</a>"
            			sli+="</li>"
            			var $li=$(sli);
    					$li.data("shareNoteId",shareNoteId);
    					$("#seachnotes").append($li);
        			}
    			}else{
    				for(var i=0;i<lis.length;i++){
        				var shareNoteTitle=lis[i].cn_share_title;
            			var shareNoteId=lis[i].cn_share_id;
        				var sli="";
            			sli+="<li class='online'>";
            			sli+="<a>"
            			sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
            			sli+=""+shareNoteTitle;
            			sli+="</a>"
            			sli+="</li>"
            			var $li=$(sli);
    					$li.data("shareNoteId",shareNoteId);
    					$("#seachnotes").append($li);
        			}
    			}    			
    			console.log("lis.length:"+lis.length);
    			if(lis.length<=5){
    				//当搜索的记录小于5条时，隐藏加载更多的按钮
    				$("#more_note").hide();
    			}
    		}
    	},
    	error:function(){
    		alert("搜索异常");
    	}
    });
    return lis;
}

//分享笔记
function shareNote(){
	//获取请求参数
	var $li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	//发送Ajax请求
	$.ajax({
		url:"/cloude_note/note/sharenote.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//分享成功
				alert(result.msg);
				$li.find(".btn_slide_down").before("<span class='glyphicon glyphicon-eye-open'></span>");
			}
		},
		error:function(){
			alert("分享过程中发生了点意外！");
		}
	});
}

//移动笔记
function moveNote(){
	//获取请求参数
	var $li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	var noteBookId=$("#moveSelect").val();
	console.log(noteId+","+noteBookId);
	//发送ajax请求
	$.ajax({
		url:"/cloude_note/note/movenote.do",
		type:"post",
		data:{"noteId":noteId,"noteBookId":noteBookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				$li.remove();
				//移动成功
				alert(result.msg);								
			}else{
				//移动失败
				alert(result.msg);
			}
		},
		error:function(){
			//alert("移动笔记异常");
		}
	});
}

//弹出移动笔记提示框
function alertMoveNote(){
	//弹出移动笔记的对话框
	 $("#can").load(
	"alert/alert_move.html",function(){
		//获取笔记本id，在select下面添加option						
		//获取笔记本列表的li元素
		var notebooks=$("#notebooks li");
		//便利li，提取出noteBookId和noteBookName
		for(var i=0;i<notebooks.length;i++){
			var $li=$(notebooks[i]);//将每一个li转换成Jquery对象
			var noteBookId=$li.data("noteBookId");//获取笔记本id
			var noteBookName=$li.text().trim();//获取笔记本的name
			//添加到select元素下面
			var sport="";
			sport+="<option value="+noteBookId+">";
			sport+=noteBookName+"</option>";
			$("#moveSelect").append(sport);
		}
	});
	$(".opacity_bg").show();
}

//将笔记删除到回收
function rallBackNote(){
	$("#can").load(
	"alert/alert_delete_note.html");
	$(".opacity_bg").show();
	var $li=$("#note_ul a.checked").parent();
	var noteId=$li.data("noteId");
	console.log(noteId);
	$("#can").on("click","#deleteNote",function(){
		//获取请求参数
		$.ajax({
			url:"/cloude_note/note/rallbacknote.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					alert(result.msg);
				}
			},
			error:function(){
				alert("删除异常!请稍后再试");
			}
		});
	});
}
//添加笔记
function addNote(){
	//获取请求参数
	var userId=getCookie("user_id");
	var noteTitle=$("#input_note").val();
	//参数格式检查
	if(noteTitle==""){
		alert("笔记的名字不能为空!");
		return;
	}
 /*	alert("userId:"+userId+",noteTitle:"+noteTitle+",noteBookId:"+noteBookId);*/
 	$.ajax({
		url:"/cloude_note/note/addnote.do",
		type:"post",
		data:{"userId":userId,"noteTitle":noteTitle,"noteBookId":noteBookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				alert("添加笔记成功");	    							
			}else{
				alert("添加笔记失败");
			}
		},
		error:function(){
			alert("添加异常");
		}
	});
}

//保存笔记
function updateNote(){
		//获取请求参数noteId,noteTitle,noteBody
		var noteTitle=$("#input_note_title").val().trim();
		var noteBody=um.getContent();
		var $li=
			$("#note_ul a.checked").parent();
		var noteId=$li.data("noteId");
		/* alert(noteId+":"+noteTitle+":"+noteBody); */
		$("#note_title_span").html("");
		//格式检查
		if($li.length==0){
			alert("请选择笔记");
		}else if(noteTitle==""){
			$("#note_title_span").html("<font color='red'>标题不能为空</font>");
		}else if(noteBody==""){
			alert("还是写点内容吧！");
		}else{
			//发送Ajax请求
			$.ajax({
				url:"/cloude_note/note/saveNote.do",
				type:"post",
				data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
				dataType:"json",
				success:function(result){
					if(result.status==0){
						//保存成功
						//更新列表中的li中的标题
						var sli="";
						sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
						sli+=""+noteTitle;
						sli+="<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";       							
						//将选中的li元素的a内容替换
						$li.find("a").html(sli);
						alert(result.msg);
					}else{//保存失败
						alert(result.msg);
					}       						
				},
				error:function(){
					alert("保存发生异常");
				}
			});
		}
	}


//查询笔记的内容和标题，在编辑页面显示
function loadNoteBody(){
		//为点击的笔记进行选中处理
		$("#note_ul a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//获取请求参数，noteId
		var noteId=$(this).data("noteId");
		//检查格式
		//发送Ajax请求
		$.ajax({
			url:"/cloude_note/note/loadNoteBody.do",
			type:"post",
			data:{"noteId":noteId},
			dataType:"json",
			success:function(result){
				if(result.status==0){
					var noteBody=result.data.cn_note_body;
					var noteTitle=result.data.cn_note_title;
					//将笔记的内容和标题显示到编辑页面
					$("#input_note_title").val(noteTitle);
					um.setContent(noteBody);
				}else{
					alert(result.msg);
				}
			},
			error:function(){
				alert("查询笔记异常");
			}
		});
}

//根据笔记本id查询笔记
function loadBookNotes(){
	$("#pc_part_4").hide();
	$("#pc_part_6").hide();
	$("#pc_part_5").hide();
	$("#pc_part_3").show();
	$("#pc_part_2").show();
	$("#notebooks a").removeClass("checked");
	//设置笔记本li的选中状态    				
	 $(this).find("a").addClass("checked");
	//每次查看笔记时，将前一个的笔记清空
	$("#note_ul").html("");
	//获取请求参数
	var noteBookId=$(this).data("noteBookId");
	addCookie("noteBookId",noteBookId,2);
	//格式检查
	//发送Ajax请求
	$.ajax({
		url:"note/loadnotes.do",
		type:"post",
		data:{"noteBookId":noteBookId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				var notes=result.data;
				for(var i=0;i<notes.length;i++){
					var noteTitle=notes[i].cn_note_title;
					var noteId=notes[i].cn_note_id;
					var noteTypeId=notes[i].cn_note_type_id;
					console.log(noteTypeId);
					var sli="";
					sli+="<li class='online'>";
					sli+="<a>";
					sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
					sli+=""+noteTitle;
					if(noteTypeId=="1"){
						sli+="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-eye-open'></span>";
					}
					sli+="<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					sli+="</a>";
					sli+="<div class='note_menu' tabindex='-1'>";
					sli+="<dl>";
					sli+="<dt><button id='moveNote' type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
					if(noteTypeId!="1"){
					//改笔记为没有分享过的,分享过的笔记就隐藏分享按钮
					sli+="<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";	
					}
					sli+="<dt><button type='button' class='btn btn-default btn-xs btn_delete' title='删除'><i class='fa fa-times'></i></button></dt>";
					sli+="</dl>";
					sli+="</div>";
					sli+="</li>";
					var $li=$(sli);
					$li.data("noteId",noteId);
					$("#note_ul").append($li);
				}
			}else{
				//如果该笔记本下面没有笔记，打印出信息
				//该笔记本还没有笔记
				alert(result.msg);
			}
		},
		error:function(){
			alert("加载笔记列表异常");
		}
	});
 }