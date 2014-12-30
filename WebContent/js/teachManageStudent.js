$(document).ready(function(){
	 $.ajax({
			cache:true,
			type:"POST",
      url:'/FinalDesign/course/getCoursesByTeacherIdInSemester',
      async:false,
			error:function(request){
      alert('获取教师开设课程信息失败，请重试');
		          },
      success:function(data){
      createCourseSelect(data);	
      }
		});
	 if(courseType != ""){
		 $("#courseforteacher option[value='"+courseType+"']").attr("selected",true);	
	}
});


/**
 * 根据学生id从教学班删除学生信息
 * @param userid
 * @param username
 * @param classid
 * @param classname
 */
function delUserInCourse(userid,username,classid,classname){
	var a=window.confirm("确定从班级 "+classname+" 删除学生  "+username+"？");
	  if(a){
	 $.get("/FinalDesign/userinfo/delUserInClass?userId=" + userid+"&classId="+classid,function(data){  
         if("success" == data.result){  
             alert("删除成功");  
             window.location.reload();  
         }else{  
             alert("删除失败");  
         }  
     });  
	  }
}

/**
 * 创建课程选项
 * @param coursedata
 */
function createCourseSelect(coursedata){
	var courseselect = $("#courseforteacher");
	courseselect.empty();
	for(var i=0;i<coursedata.length;i++) {
	    var option = $("<option>").text(coursedata[i].courseName).val(coursedata[i].courseId);
	    courseselect.append(option);
	}
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


function addUser(){
	 $.ajax({
			cache:true,
			type:"POST",
   url:'/Program/userinfo/addUser',
   data:$('#userForm').serialize(),
  async:false,
			error:function(request){
   alert('保存失败，请重试');
		          },
   success:function(data){
 	  alert('保存成功');
 		window.location.href="/Program/userinfo/getAllUser";
 		
   }
		});
}

//选择开设课程后对学生信息筛选显示
function onCourseSelectChange(obj) {
	  var courseidval = $(obj).val();//未选择时为null
	  location="/FinalDesign/userinfo/getStudentInfo?courseid="+courseidval; 
};


/**
 * 带着学生信息跳转到另一个页面
 * @param student
 */
function toEditStudent(teachclassid,studentid){
	var courseid =  $("#courseforteacher").val();	
	top.location="/FinalDesign/userinfo/toEditStudentInfo?courseid="+courseid+"&studentid="+studentid+"&teachclassid="+teachclassid;
};

/**
 * 为学生重置密码
 * @param username
 * @param userid
 */
function resetPassword(username,userid){
	var a=window.confirm("确定重置 "+username+" 的登陆密码？");
	  if(a){
	 $.get("/FinalDesign/userinfo/resetPassword?userId=" + userid,function(data){  
       if("success" == data.result){  
           alert("重置密码成功，初始密码为 123456");    
       }else{  
           alert("重置密码失败");  
       }  
   });  
	  }
};

/**
 * 根据用户名筛选学生信息
 */
function searchStudentByName(){
	var username = $("#searchusername").attr("value");
	if(username == '输入用户名查询用户'){
		username = "";
	}
	
	 location="/FinalDesign/userinfo/searchStudentByName?userName="+username;
	 /*
	 var urlStr =  "/FinalDesign/userinfo/searchStudentByName";
	    var f= document.createElement('form');
	    f.action = urlStr;
	    f.method = 'post';
	    document.body.appendChild(f);
	    var temp=document.createElement('input');
	    temp.type= 'hidden';
	    temp.value=username; 
	    temp.name='userName';
	    f.appendChild(temp);
	    f.submit();
	    */
}