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
};

/**
 * 显示、收起修改文档提交方式
 */
function showMethodArea(){
	var temp = $("#checkMethod");
		if(temp.is(':checked') == true){   
		   $("#suffix").attr('disabled',false); //可编辑
      }else{   
    	  $("#suffix").attr('disabled', true); //不可编辑		
      }   
};


/**
 * 修改文档信息 
 */
function modifyItemInfo(){
	var itemId = $("#itemId").attr("value");
	var courseid = $("#courseforteacher").val();
	var chapter = $("#chapter").attr("value");
	var topic = $("#topic").attr("value");
	var description = $("#description").attr("value");
	var goal = $("#goal").attr("value");	
	var cksuffix = $("#checkMethod").is(':checked');
	var suffix = $("#suffix").attr("value");
	 var ckisDefault = $("#isDefault").is(':checked');
	if(validate(chapter,topic,goal)){
		$.ajax({
			cache:true,
			type:"POST",
            url:'/FinalDesign/item/modifyOrSaveItem',
            data:{
            	itemId:itemId,
            	courseid:courseid,
            	chapter:chapter,
            	topic:topic,
            	description:description,
            	goal:goal,
            	cksuffix:cksuffix,
            	suffix:suffix,
            	ckisDefault:ckisDefault
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
};

function validate(chapter,topic,goal){
	var chaptererror = document.getElementById("chaptererror");
    var topicerror = document.getElementById("topicerror");
    var goalerror = document.getElementById("goalerror");
    	
	if(chapter == ""){
		chaptererror.innerHTML='章节不能为空';
		chaptererror.style.display="";
		return false;
	}else{
		chaptererror.style.display="none";
	}
	
	if(topic == ""){
		topicerror.innerHTML='题目不能为空';
		topicerror.style.display="";
		return false;	
	}else{
		topicerror.style.display="none";
	}
	
	if(goal == ""){
		goalerror.innerHTML='默认得分不能为空';
		goalerror.style.display="";
		return false;	
	}else{
		goalerror.style.display="none";
	}
	return true;
}

/**
 * 取消按钮
 */
function cancel(){
	window.history.back(-1); 
}
