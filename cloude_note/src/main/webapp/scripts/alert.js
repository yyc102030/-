/*封装对话框的处理*/
//弹出笔记管理菜单
function alertNoteMenu(){
	//隐藏所有笔记菜单
	$("#note_ul div").hide();
	flag++;
	if(flag%2==0){
		//显示笔记管理菜单
		var $menu=$(this).parent().next();
		$menu.slideDown(1000);
	}					
	//设置点击笔记选中效果
	$("#note_ul a").removeClass("checked");
	$(this).parent().addClass("checked");
	//阻止事件向body,li冒泡
	return false;
}


//当点击添加笔记的按钮时，对弹框进行判断
function chooseAlert(){
		$li=$("#notebooks a.checked").parent();
		noteBookId=$li.data("noteBookId");
		if($li.length==0){
			alert("您还没有选择要添加到哪个笔记本");
		}else{
			alertAddNoteWindow();
		}
	}

//弹出添加笔记的对话框
function alertAddNoteWindow(){
	$("#can").load(
	"alert/alert_note.html");
	$(".opacity_bg").show();
}

//弹出创建笔记本对话框
function alertAddNoteBookWindow(){
	//弹出对话框alert_notebook.html
	/* 	<!-- alert_background
		对话框的背景		
	-->
		<div class="opacity_bg" style='display:none'></div>
		<!-- alert_notebook
			对话框
		-->
		<div id="can"></div> */
		$("#can").load(
				"alert/alert_notebook.html");
		$(".opacity_bg").show();
		
}

//关闭对话框
function closeAlertWindow(){
	//关闭操作
	$("#can").html("");
	$(".opacity_bg").hide();
}