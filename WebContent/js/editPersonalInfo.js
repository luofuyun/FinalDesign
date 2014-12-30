


$(document).ready(function(){
	var msg = $("#userId").attr("value");
});

/**
 * 修改个人信息
 */
function modifyPersonalInfo(){	
	var userform = $("#courseForm");
	var accountid = $("#accountId").attr("value");
	var username = $("#userName").attr("value");
	var psd = $("#password").attr("value");
	var repsd = $("#repassword").attr("value");
	var contact = $("#contact").attr("value");
	if(validate(accountid,username,psd,repsd,contact)){
		$.ajax({
			cache:true,
			type:"POST",
            url:'/FinalDesign/userinfo/modifyPersonalInfo',
            data:$('#userForm').serialize(),
            async:false,
			error:function(request){
            alert('保存失败，请重试');
		          },
            success:function(data){
            	 alert('保存成功');
            	 location.reload();
//            	 showUserInfoAfterModify(data);
	            }
		});
	}
}

function getUSerInfo(){
	
}

function validate(accountid,username,psd,repsd,contact){
	var psderror = document.getElementById("psderror");
    var accounterror = document.getElementById("accounterror");
    var nameerror = document.getElementById("nameerror");
    var phoneerror = document.getElementById("phoneerror");
    var ckpsd = $("#checkModifypsd");
    var selectContact = $("#selectcontact");
    
	if(!accountid.match("[0-9]{8}")){
		accounterror.innerHTML='管理员账号为8位';
		accounterror.style.display="";
		return false;
	}else{
		accounterror.style.display="none";
	}
	
	if(username == ""){
		nameerror.innerHTML='姓名不能为空';
		nameerror.style.display="";
		return false;
	}else{
		nameerror.style.display="none";
	}
	
	if(selectContact.val() == "phone"){
		if((contact != "")&&(!contact.match("[0-9]{11}"))){
			phoneerror.innerHTML='手机号码为11位';
			phoneerror.style.display="";
			return false;
		}
		else{
			phoneerror.style.display="none";
		}
	}
	
	if(ckpsd.attr("checked") == "checked"){
    if(!(psd == repsd)){
		psderror.innerHTML='两次输入密码不一致';
		psderror.style.display="";
		$("#password").attr("value","");
		$("#repassword").attr("value","");
		return false;
	}else if(!psd.match("[0-9]{6,20}")){
		psderror.innerHTML='密码长度为6 - 20位';
		psderror.style.display="";
		$("#password").attr("value","");
		$("#repassword").attr("value","");
		return false;
	}
	else{
		psderror.style.display="none";
	}
    }
	
	return true;
}

//展示个人信息
function showUserInfoBeforeModify(userdata){
		$("#accountId").attr("value",userdata.accountId);
		$("#userName").attr("value",userdata.userName);
		$("#contact").attr("value",userdata.contact);
		$("#gender option[value='"+userdata.gender+"']").attr("selected", "selected");
}

//展示修改后的信息
function showUserInfoAfterModify(userdata){
		$("#accountId").attr("value",userdata[0].accountId);
		$("#userName").attr("value",userdata[0].userName);
		$("#contact").attr("value",userdata[0].contact);
		$("#gender option[value='"+userdata[0].gender+"']").attr("selected", "selected");
}

//显示、收起修改密码块
function showPsdArea(){
	var temp = $("#checkModifypsd");
		  if(temp.attr("checked") == "checked"){   
			  $("#psdarea").fadeIn();
      }else{   
    	  $("#psdarea").fadeOut();         
      }   
}

//改变当前位置名称
function showCurLocationName(name){
	document.getElementById("curLocation").innerHTML = name;
	}

/**
 * 取消按钮
 */
function cancel(){
	window.history.back(-1); 
}