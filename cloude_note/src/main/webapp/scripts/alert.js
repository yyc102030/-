/*封装对话框的处理*/
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