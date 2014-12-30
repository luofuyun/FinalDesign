
$(document).ready(function(){
	 $.ajax({
			cache:true,
			type:"POST",
      url:'/FinalDesign/course/getAllSemester',
      async:false,
			error:function(request){
      alert('获取学年学期信息失败，请重试');
		          },
      success:function(data){
      createSemesterSelect(data);	
      }
		});
	 if(semesterid != ""){
		 $("#semester option[value='"+semesterid+"']").attr("selected",true);	
	}
});

function createSemesterSelect(semesterdata){
	var semesterselect = $("#semester");
	semesterselect.empty();
	for(var i=0;i<semesterdata.length;i++) {
	    var option = $("<option>").text(semesterdata[i].term+"学年度第"+semesterdata[i].semesterTime+"学期").val(semesterdata[i].semesterId);
	    semesterselect.append(option);
	}
}

/**
 * 修改个人信息
 */
function modifySemesterCourseInfo(){	
	
	var semestercourseid = $("#semestercourseid").attr("value");
	var courseNo = $("#courseNo").attr("value");
	var courseName = $("#courseName").attr("value");
	var semesterid = $("#semester").val();
	var coursetype = $("#coursetype").val();
	var credit = $("#credit").attr("value");
	if(validate(courseNo,courseName,credit)){
		$.ajax({
			cache:true,
			type:"POST",
            url:'/FinalDesign/course/modifyOrSaveSemesterCourseInfo',
            data:{
            	semestercourseid:semestercourseid,
            	courseNo:courseNo,
            	courseName:courseName,
            	semesterid:semesterid,
            	coursetype:coursetype,
            	credit:credit
            },
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

function validate(courseNo,courseName,credit){
	var coursenoerror = document.getElementById("coursenoerror");
    var coursenameerror = document.getElementById("coursenameerror");
    var crediterror = document.getElementById("crediterror");
    
	if(!courseNo.match("[0-9]{8}")){
		coursenoerror.innerHTML='课程编号为为8位数字';
		coursenoerror.style.display="";
		return false;
	}else{
		coursenoerror.style.display="none";
	}
	
	if(courseName == ""){
		coursenameerror.innerHTML='课程名称不能为空';
		coursenameerror.style.display="";
		return false;
	}else{
		coursenameerror.style.display="none";
	}
	
	if(credit == ""){
		crediterror.innerHTML='学分只能输入数值且不能为空';
		crediterror.style.display="";
		return false;	
	}else{
	if(!/\D/.test(credit)){
		crediterror.innerHTML='学分只能输入数值且不能为空';
		crediterror.style.display="";
		return false;
	}else{
		crediterror.style.display="none";
	}	
	}
	return true;
}

/**
 * 校验选择的学年的合法性
 */
function pick() {
	var startvalue = $("#termfirst").val();
	var endvalue = $("#termlast").val();
	if(endvalue != ""){
		if(startvalue != ""){
		if(startvalue >= endvalue){
			alert("学年格式错误");
			$("#termfirst").val("");
			$("#termlast").val("");
		}
		}
	}
}

/**
 * 取消按钮
 */
function cancel(){
	window.history.back(-1); 
}