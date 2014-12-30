

/**
 * 删除年度课程信息
 * @param courseName
 * @param semesterCourseId
 */
function openOrCloseSemesterCourse(courseName,semesterCourseId){
	var isclose = $("#"+semesterCourseId+"statuvalue").attr("value");
	var isclosetoupdateindb = 0;
   if(isclose == 1){
	   var a=window.confirm("确定启用该课程 "+courseName+" ？");
	   if(a){
		   $("#"+semesterCourseId+"status").html('已启用');
		   $("#"+semesterCourseId+"statuvalue").attr("value",0);
		   $("#"+semesterCourseId+"openOrClose").html('关闭');
	   }
   }else{
	   var a=window.confirm("确定关闭该课程 "+courseName+" ？");
	   if(a){
		   $("#"+semesterCourseId+"status").html('已关闭');
		   $("#"+semesterCourseId+"statuvalue").attr("value",1);
		   $("#"+semesterCourseId+"openOrClose").html('启用');
		   isclosetoupdateindb = 1;
	   }
   }
  	 $.get("/FinalDesign/course/updateSemesterCourseStatus?semesterCourseId="+semesterCourseId+"&newstatus="+isclosetoupdateindb,function(data){  
           if("success" == data.result){  
               }else{  
               alert("更新课程状态失败");  
           }  
       });    
 };

/**
 * 跳转到编辑年度课程信息页面
 * @param semesterCourseId
 */
function toEditSemesterCourse(semesterCourseId){
	location="/FinalDesign/course/toEditSemesterCourse?semesterCourseId="+semesterCourseId;
};

/**
 * 刷新页面
 */
function refresh(){
	 location.reload();
}