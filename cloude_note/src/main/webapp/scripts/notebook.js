/*notebook.js用来封装笔记本的相关处理*/
//添加笔记
function addNoteBook(){
	//获取参数 noteBookName
	var noteBookName=$("#input_notebook").val();
	var user_id=getCookie("user_id");
	$.ajax({
		url:"/cloude_note/notebook/addnotebook.do",
		type:"post",
		data:{"noteBookName":noteBookName,"user_id":user_id},
		dataType:"json",
		success:function(result){
			if(result.status==0){
				//添加成功
				//更新笔记本列表
				alert(result.msg);
				loadUserNoteBooks();   							
			}else{
				alert(result.msg);
			}
		},
		error:function(){
			alert("添加异常");
		}
	});
}


//加载用户笔记本列表
function loadUserNoteBooks(){
	//1.加载用户笔记本列表
	//载入完毕之后在笔记本模块载入用户的笔记本
	//也就是笔记本列表
	//获取用户的id
	var user_id=getCookie("user_id");
	//检查格式
	if(user_id!=null){
		//发送Ajax请求
		$.ajax({
			url:"notebook/loadbooks.do",
			type:"post",
			data:{"user_id":user_id},
			dataType:"json",
			success:function(result){
				//获取笔记本的集合
				var noteBooks=result.data;
				//循环生成列表元素
				for(var i=0;i<noteBooks.length;i++){
					//获取笔记本的id
					var noteBookId=noteBooks[i].cn_notebook_id;
					//获取笔记本的name
					var noteBookName=noteBooks[i].cn_notebook_name;
					//构建列表元素 
					var sli="";
					sli+="<li class='online'>";
					sli+="<a>";
					sli+="<i class='fa fa-book' title='online' rel='tooltip-bottom'>";
					sli+="</i>"+noteBookName+"</a></li>";
					//将noteBookId绑定到li元素上
					var $li=$(sli);
					$li.data("noteBookId",noteBookId);
					$("#notebooks").append($li);
				}
			},
			error:function(){
				alert("加载笔记本列表异常");
			}
		});
	}else{
		location.href="log_in.html";
	}  
}