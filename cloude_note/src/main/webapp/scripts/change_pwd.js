
$(function(){
	//绑定界面事件
	$("#last_password").blur(checkLastPwd);
	$("#new_password").blur(checkNewPwd);
	$("#final_password").blur(checkFinalPwd);
	$("#MycchangePassword").click(sureChangePwd);
	$("#back").click(noChangePwd);
});
//验证旧密码
function checkLastPwd(){
	var lastPwd=$("#last_password").val().trim();
	var userId=getCookie("user_id");
	console.log(lastPwd);
	$.ajax({
		url:"/cloude_note/user/checklastpwd.do",
		type:"post",
		data:{"lastPwd":lastPwd,"userId":userId},
		success:function(result){
			if(result.status==1){
				$("#warning_1").show();
				return false;
			}else{
				$("#warning_1").hide();
				return true;
			}
		},
		error:function(){
			$("#warning_1 msg").html("验证异常");
		}
	});
}

//验证新密码
function checkNewPwd(){
	var newPwd=$("#new_password").val().trim();
	var reg = /^\w{3,10}$/;
	if(!reg.test(newPwd)){
		//不满足要求
		$("#warning_2").show();
		return false;
	}else{
		//满足要求
		$("#warning_2").hide();
		return true;
	}
}



//确认新密码
function checkFinalPwd(){
	var newPwd=$("#new_password").val().trim();
	var final_password=$("#final_password").val().trim();
	if(!newPwd==final_password){
		$("#warning_3").show();
		return false;
	}else{
		$("#warning_3").hide();
		return true;
	}
}

//确认修改密码
function sureChangePwd(){
	var n=checkLastPwd()+checkNewPwd()+checkFinalPwd();
	var userId=getCookie("user_id");
	var newPwd=$("#new_password").val().trim();
		//验证通过，发送Ajax请求
		$.ajax({
			url:"/cloude_note/user/modifypwd.do",
			type:"post",
			data:{"userId":userId,"newPwd":newPwd},
			dataTy:"json",
			success:function(result){
				if(result.status==0){
					//修改成功
					$("#warning_1").html("<span style='color:green'>修改成功！</span>");
					delCookie("user_id");
					delCookie("user_name");
					history.back();
				}else{
					$("#warning_1").html("修改失败！");
				}
			},
			error:function(){
				alert("修改异常！");
			}
		});	
}


//返回
function noChangePwd(){
	history.back();
}
