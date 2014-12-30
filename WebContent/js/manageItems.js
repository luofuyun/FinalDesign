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
}

/**
 * 选择课程后对课程进行切换
 * @param obj
 */
function onCourseSelectChange(obj) {
	  var courseidval = $(obj).val();//未选择时为null
	  location="/FinalDesign/item/findPersonalItemsByUser?courseid="+courseidval; 
};

/**
 * 删除作业条目
 * @param userid
 * @param username
 * @param classid
 * @param classname
 */
function delItem(itemid,topic){
	var a=window.confirm("确定从题库删除  "+topic+"？");
	  if(a){
	 $.get("/FinalDesign/item/deletesingleitem?itemId="+itemid,function(data){  
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
 * 删除作业条目
 */
function BatchDelItem(){
	 var ids = getSelectedUserIds();
	 if(ids != null){
		 if(ids != ""){
	 var a=window.confirm("确定删除所选题目?");
	 if(a){			
	 $.get("/FinalDesign/item/bacthDelItem?itemIds=" + ids,function(data){  
        if("success" == data.result){  
            alert("删除成功");  
            window.location.reload();  
        }else{  
            alert("删除失败");  
        }  
    });  
	  }
}else{
			 alert("请先选择题目");
    }
	 }else{
		 alert("请先选择题目");
	 }
}

/**
 * 获得选择的题目id
 * @returns
 */
function getSelectedUserIds(){
    var selectBoxs = document.all("selectItemCk");
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

/**
 * 跳转到编辑题目信息页面
 * @param semesterCourseId
 */
function toEditItem(itemId){
	location="/FinalDesign/item/toEditItem?itemId="+itemId;
};


function showTeacherItems(data){
	var listtable = $("#table");
	if (listtable != null)
		listtable.remove();
	var table = $("<table id=\"teacherarr\" cellspacing=\"0\" class=\"col-md-12\"> </table>");
	table.appendTo("#teacherItems");
	var thead=$("<thead><tr>"+ 
				"<th class=\"col-md-1\" abbr=\"Configurations\"><input type=\"checkbox\"/></th> "+
				"<th class=\"col-md-5\">标题</th>"+
				"<th class=\"col-md-2\">查看"+
				"<th class=\"col-md-2\">修改"+
				"<th class=\"col-md-2\">删除"+
				"</tr></thead>");
	thead.appendTo(table);
	var length=data.length; 
	for(var i=0;i<length;i++){
		var tr=$("<tr></tr>");
		tr.appendTo(table);
		var checkbox = $("<td class=\"col-md-1\" abbr=\"Configurations\ class=\"specalt\"><input type=\"checkbox\" value="+data[i].itemId+" name=\"checkbox\"/></td>");
		checkbox.appendTo(tr);
		var topic = $("<td class=\"col-md-5\">"+data[i].topic+"</a></td>");
    	topic.appendTo(tr);
		var show = $("<td class=\"col-md-2\"><a href=\"getitem?itemId="+data[i].itemId+"\">查看</a></td>");
		show.appendTo(tr);
		var update = $("<td class=\"col-md-2\"><a href=\"getitem2?itemId="+data[i].itemId+"\">修改</a></td>");
		update.appendTo(tr);
		var del = $("<td class=\"col-md-2\"><a href=\"deletesingleitem?itemId="+data[i].itemId+"\">删除</a></td>");
		del.appendTo(tr);
		}
}


//全选框勾选--选择所有
function switchSelectBox(){
    var selectBoxs = document.all("selectItemCk");
    if(!selectBoxs) return ;
    if(typeof(selectBoxs.length) == "undefined"){//only one checkbox
        selectBoxs.checked = event.srcElement.checked;
    }else{//many checkbox ,so is a array 
      for(var i = 0 ; i < selectBoxs.length ; i++){
         selectBoxs[i].checked = event.srcElement.checked;
      }
    } 
};

/**
 * 刷新页面
 */
function refresh(){
	 location.reload();
}
