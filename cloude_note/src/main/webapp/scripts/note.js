//封装笔记相关的操作功能
//添加笔记
function addNote(){
	//获取请求参数
	var userId=getCookie("user_id");
	var noteTitle=$("#input_note").val();
 	alert("userId:"+userId+",noteTitle:"+noteTitle+",noteBookId:"+noteBookId);
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
	$("#notebooks a").removeClass("checked");
	//设置笔记本li的选中状态    				
	 $(this).find("a").addClass("checked");
	//每次查看笔记时，将前一个的笔记清空
	$("#note_ul").html("");
	//获取请求参数
	var noteBookId=$(this).data("noteBookId");
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
					var sli="";
					sli+="<li class='online'>";
					sli+="<a>";
					sli+="<i class='fa fa-file-text-o' title='online' rel='tooltip-bottom'></i>";
					sli+=""+noteTitle;
					sli+="<button type='button' class='btn btn-default btn-xs btn_position btn_slide_down'><i class='fa fa-chevron-down'></i></button>";
					sli+="</a>";
					sli+="<div class='note_menu' tabindex='-1'>";
					sli+="<dl>";
					sli+="<dt><button type='button' class='btn btn-default btn-xs btn_move' title='移动至...'><i class='fa fa-random'></i></button></dt>";
					sli+="<dt><button type='button' class='btn btn-default btn-xs btn_share' title='分享'><i class='fa fa-sitemap'></i></button></dt>";
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