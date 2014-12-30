//修改用户信息

var PageData = {		
};
var batchUserInfo = {};
var cancelBacthAddUser = false;

$(document).ready(function(){
	if(usertype != ""){
	 $("#type option[value='"+usertype+"']").attr("selected",true);	
	}
});
function modifyUser(accountID){
		window.location.href="/Program/userinfo/getUser?accountId="+accountID;
}

//删除用户
function delUser(accountId,username){
	var a=window.confirm("确定删除用户  "+username+"？");
	  if(a){
	 $.get("/Program/userinfo/delUser?accountId=" + accountId,function(data){  
         if("success" == data.result){  
             alert("删除成功");  
             window.location.reload();  
         }else{  
             alert("删除失败");  
         }  
     });  
	  }
}

function searchUserByUserName(){
	var username = $("#searchusername").attr("value");
	if(username == '输入用户名查询用户'){
		username = "";
	}
	username = encodeURI(username);
	username = encodeURI(username);
	 location="/Program/userinfo/getAllUser?username="+username;
}

function toAddUser(){
	window.location.href="/Program/userinfo/toAddUser";
}

function toBatchAddUser(){
	window.location.href="/Program/userinfo/toBatchAddUser";
}

/**
 * 上传用户信息文件
 */
function AnalyseUserInfo(){
	alert('test');
	$.ajaxFileUpload({
		url:'/Program/userinfo/BacthAddUser',
		type: 'POST',
		data: {//加入的文本参数  
           },
		secureuri:false,                       //是否启用安全提交,默认为false 
		fileElementId:'myBlogImage',           //文件选择框的id属性
		dataType:'json',                       //服务器返回的格式,可以是json或xml等
		success:function(data, status){  
			  batchUserInfo = data[0].USERLIST;
     	      showBatchUserInfo(data); 
		},
		error:function(data, status, e){ //服务器响应失败时的处理函数
		}
	});
//	var filepath = $("#myBlogImage").val();
//	var file_upl = document.getElementById('myBlogImage');
//	file_upl.select();
//	var realpath = document.selection.createRange().text;
//	alert('analyse'+realpath);
//	$.ajax({
//		cache:true,
//		type:"POST",
//        url:'/Program/userinfo/BacthAddUser',
//        data:
//        {
//     	filepath:filepath
//        },
//        async:false,
//		error:function(request){
//        alert('保存失败，请重试');
//	          },
//        success:function(data){
//        batchUserInfo = data[0].USERLIST;
//        showBatchUserInfo(data);    
//        }
//	});
}

/**
 * 
 * @param data
 */
function showBatchUserInfo(data){
	var isValid = true;
	var listtable = $("#listtable");
	if (listtable != null)
		listtable.remove();
	var table = $("<table id=\"listtable\" align=\"center\" style=\"font-size: 1.1em;\" ></table>");
	table.appendTo("#userinfoarea");
	var thead = $("<thead><tr>" +
			"<th style=\"width:80px\">学号</th>" +
	        "<th style=\"width:80px\" >姓名</th>" +
	        "<th style=\"width:80px\">性别</th>" +
	        "<th style=\"width:60px\">年级</th>" +
	        "<th style=\"width:80px\">学院</th>" +
	        "<th style=\"width:200px\">专业</th>" +
	        "<th style=\"width:100px\">班级</th>" +
	        "<th style=\"width:100px\">联系方式</th>" +	        
            "</tr></thead>");
	thead.appendTo(table);
	var length = data[0].USERLIST.length;
	for(var i = 0;i<length;i++){
		var tr = $("<tr></tr>");
		tr.appendTo(table);
		var accountid = data[0].USERLIST[i].accountId;
		if((accountid.trim().match("[0-9]{12}"))){
			var accountIdtd = $("<td>"+data[0].USERLIST[i].accountId+"</td>");
			accountIdtd.appendTo(tr);	
		}else{
			  if(accountid.trim().match("[0-9]{8}")){
				  var accountIdtd = $("<td style=\"color:red\">"+data[0].USERLIST[i].accountId+"</td>");
					accountIdtd.appendTo(tr);
					isValid = false; 
			  }else{
				  var accountIdtd = $("<td>"+data[0].USERLIST[i].accountId+"</td>");
					accountIdtd.appendTo(tr);
			  }		
		}
		var userNametd = $("<td>"+data[0].USERLIST[i].userName+"</td>");
		userNametd.appendTo(tr);
		var gender = data[0].USERLIST[i].gender;
		if(gender == 1){
			var gendertd = $("<td>男</td>");
			gendertd.appendTo(tr);	
		}else if (gender == 2){
			var gendertd = $("<td>女</td>");
			gendertd.appendTo(tr);	
		}else{
			var gendertd = $("<td></td>");
			gendertd.appendTo(tr);	
		}	
		var gradetd = $("<td>"+data[0].USERLIST[i].grade+"</td>");
		gradetd.appendTo(tr);
		var collegetd = $("<td>"+data[0].USERLIST[i].college+"</td>");
		collegetd.appendTo(tr);
		var departmenttd = $("<td>"+data[0].USERLIST[i].department+"</td>");
		departmenttd.appendTo(tr);
		if(data[0].USERLIST[i].adminclass != null){
		var classnametd = $("<td>"+data[0].USERLIST[i].adminclass.classname+"</td>");
		classnametd.appendTo(tr);
		}else{
			var classnametd = $("<td style=\"color:red\">无对应班级信息</td>");	
			classnametd.appendTo(tr);
			isValid = false;
			}
		var contacttd = $("<td>"+data[0].USERLIST[i].contact+"</td>");
		contacttd.appendTo(tr);
	}
	if(!isValid){
		var lastwarndiv = $("#warn");
		if (lastwarndiv != null)
			lastwarndiv.remove();
	var warndiv = $("<div id=\"warn\" style=\"margin-top:15px;color:red;font-size:1.1em\">&nbsp;*&nbsp;红色字体部分学生信息有误，无法上传！</div>");
	warndiv.appendTo("#buttonarea");
	var warn = $("#warn");
	var confirmUpload = $("#confirmUpload");
	warn.insertBefore(confirmUpload);
	$("#confirmUpload").attr('disabled',true);//设置disabled属性为true，按钮不可用 
	}else{
		var lastwarndiv = $("#warn");
		if (lastwarndiv != null)
			lastwarndiv.remove();
		$("#confirmUpload").attr('disabled',false);//设置disabled属性为true，按钮不可用 

	}
}

function checkcancel()
{
	  var a=window.confirm("资料尚未保存，是否取消？");
	  if(a){
		 location="/Program/userinfo/getAllUser";
	  }   
}

function submitBatchUserInfo(){
	var type = $("#usertype").val();
alert(type);
//	$.ajax({
//		cache:true,
//		type:"POST",
//        url:'/Program/userinfo/BacthAddUserInfo',
//        data:
//        {
//        },
//        async:true,
//		error:function(request){
//        alert('保存失败，请重试');
//	          },
//        success:function(data){
//        	alert('保存成功  自动跳转到用户管理页面');
//      	  location.href="/Program/userinfo/getAllUser";   
//              }
//	});
}

//全选框勾选--选择所有
function switchSelectBox(){
    var selectBoxs = document.all("selectUserCk");
    if(!selectBoxs) return ;
    if(typeof(selectBoxs.length) == "undefined"){//only one checkbox
        selectBoxs.checked = event.srcElement.checked;
    }else{//many checkbox ,so is a array 
      for(var i = 0 ; i < selectBoxs.length ; i++){
         selectBoxs[i].checked = event.srcElement.checked;
      }
    } 
}   

//批量删除用户
function BatchDelUser(){
	 var ids = getSelectedUserIds();
	 if(ids != null){
		 if(ids != ""){
	 var a=window.confirm("确定删除 ?");
	 if(a){			
	 $.get("/Program/userinfo/bacthDelUser?userIds=" + ids,function(data){  
        if("success" == data.result){  
            alert("删除成功");  
            window.location.reload();  
        }else{  
            alert("删除失败");  
        }  
    });  
	  }
}else{
			 alert("请先选择用户");
    }
	 }else{
		 alert("请先选择用户");
	 }
}

//获得选择的用户id
function getSelectedUserIds(){
    var selectBoxs = document.all("selectUserCk");
    if(!selectBoxs) return null;
    if(typeof(selectBoxs.length) == "undefined" && selectBoxs.checked){   
        return selectBoxs.value;
    }else{//many checkbox ,so is a array 
      var ids = "";
      var split = "";
      for(var i = 0 ; i < selectBoxs.length ; i++){
         if(selectBoxs[i].checked){
            ids += split+selectBoxs[i].value;
            split = ",";
         }
      }
      return ids;
    }
}

//选择类型后对用户信息筛选显示
function onTypeSelectChange(obj) {
	  var typeval = $(obj).val();//未选择时为null
	  location="/Program/userinfo/getAllUser?type="+typeval; 
};