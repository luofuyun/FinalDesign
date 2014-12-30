$(document).ready(function(){
    	$.ajax({
    		type:"POST",
    		url:"/FinalDesign/item/findPersonalItemsByUserNoPage",
    		dataType:"json",
    		success:function(data){
    			showteacheritem(data);
    		}
    	});
    	$.ajax({
    		type:"POST",
    		url:"/FinalDesign/item/showDefaultItemsNoPage",
    		dataType:"json",
    		success:function(data){
    			showdefault(data);
    		}
    	});
    	
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
    	
    	$.ajax({
    		type:"POST",
    		url:"/FinalDesign/classinfo/getAllteachClassByCourse",
    		dataType:"json",
    		success:function(data){
    			showclassforcourse(data);
    		}
    	});
 });
  
/**
 * 展示默认题目
 * @param data
 */
function showdefault(data){
    	var listtable = $("#mytable1");
    	if (listtable != null)
    		listtable.remove();
    	var table = $("<table id=\"mytable1\" cellspacing=\"0\" class=\"col-md-12\"> </table>");
    	table.appendTo("#defaulttable");
    	var thead=$("<thead><tr>"+ 
					"<th class=\"col-md-1\" abbr=\"Configurations\"></th> "+
					"<th>作业标题</th >"+
					"<th width=\"20%\">截止日期</th>"+ 
					"</tr></thead>");
    	thead.appendTo(table);
    	var length = data.length;
    	for(var i = 0;i<length;i++){
    		var tr = $("<tr></tr>");
    		tr.appendTo(table);
    		var checkbox = $("<td class=\"col-md-1\" abbr=\"Configurations\ class=\"specalt\"><input type=\"checkbox\" value="+data[i].itemId+" name=\"checkbox\"/></td>");
    		checkbox.appendTo(tr);
    		var topic = $("<td><a href=\"item/getitem?itemId="+data[i].itemId+"\">"+data[i].topic+"</a></td>");
    		topic.appendTo(tr);
    		var deadlineDate=$("<td class=\"alt\"><input type=\"date\" name=\"date\"/>");
    		deadlineDate.appendTo(tr);
   		}
    };
 
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
     * 选择课程信息后，列出该课程所对应的班级
     * @param obj
     */
    function onCourseSelectChange(obj) {
    	  var courseidval = $(obj).val();//未选择时为null
    		$.ajax({
        		type:"POST",
        		url:"/FinalDesign/classinfo/getAllteachClassByCourse",
        		data:{
        			courseid:courseidval
        		},
        		dataType:"json",
        		success:function(data){
        			showclassforcourse(data);
        		}
        	});	
    };
  
    /**
     * 展示教师个人题库
     * @param data
     */
 function showteacheritem(data) {
		var listtable = $("#mytable2");
		if (listtable != null)
			listtable.remove();
		var table = $("<table id=\"mytable2\" cellspacing=\"0\" class=\"col-md-12\"> </table>");
		table.appendTo("#teacheritem");
		var thead = $("<thead><tr>"
				+ "<th class=\"col-md-1\" abbr=\"Configurations\"></th> "
				+ "<th>作业标题</th >" + "<th width=\"20%\">截止日期</th>"
				+ "</tr></thead>");
		thead.appendTo(table);
		var length = data.length;
		for ( var i = 0; i < length; i++) {
			var tr = $("<tr></tr>");
			tr.appendTo(table);
			var flagValue = data[i].itemId;
			var flag = $("<input type=\"hidden\" value="+flagValue+" name=\"flag\"/></td>");
			flag.appendTo(tr);
			var checkbox = $("<td class=\"col-md-1\" abbr=\"Configurations\ class=\"specalt\"><input type=\"checkbox\" value="+data[i].itemId+" name=\"checkbox\"/></td>");
			checkbox.appendTo(tr);
			var topic = $("<td><a href=\"item/getitem?itemId=" + data[i].itemId
					+ "\">" + data[i].topic + "</a></td>");
			topic.appendTo(tr);
			var deadlineDate = $("<td class=\"alt\"><input type=\"date\" name=\"date\"/>");
			deadlineDate.appendTo(tr);
		}
};
  
/**
 * 选择课程后，展示改教师所开设的教学班级
 * @param data
 */
function showclassforcourse(data){
    		var listtable = $("#mytable3");
        	if (listtable != null)
        		listtable.remove();
        	var table = $("<table id=\"mytable3\" cellspacing=\"0\" class=\"col-md-12\"> </table>");
        	table.appendTo("#classforcourse");
        	var thead=$("<thead><tr>"+ 
    					"<th class=\"col-md-1\"></th>"+
    					"<th class=\"col-md-5\">班级</th>"+
    					"<th class=\"col-md-6\">类型"+
    					"</tr></thead>");
        	thead.appendTo(table);
        	var length=data.length;
        	for(var i=0;i<length;i++){
        		var tr=$("<tr></tr>");
        		tr.appendTo(table);
        		var checkbox = $("<td class=\"col-md-1\" abbr=\"Configurations\ class=\"specalt\"><input type=\"checkbox\" value="+data[i].classCourseId+" name=\"classcheckbox\"/></td>");
            	checkbox.appendTo(tr);
            	var clas = $("<td class=\"alt\" class=\"col-md-5\">"+data[i].className+"</td>");
            	clas.appendTo(tr);
            	var typetr;
            	if(data[i].type == 1){
            		typetr=$("<td class=\"col-md-6\" class=\"alt\">行政班</td>");
            	}else{
            		typetr=$("<td class=\"col-md-6\" class=\"alt\">教学班</td>");
            	}
                    typetr.appendTo(tr);
        	}
    	};
   
function ajaxFileUpload(){
    		var topic=document.getElementById("itemtopic").value;
    		var chapter=document.getElementById("itemchapter").value;
    		var description=document.getElementById("itemdescription").value;
			var file=document.getElementById("itemfile").value;
    		$.ajaxFileUpload({
    			url:"item/uploadfile",
    			type: 'POST',
    			data:{ "topic": topic,"chapter":chapter,"description":description},
    			secureuri:false,                       //是否启用安全提交,默认为false 
    			fileElementId:'itemfile',              //文件选择框的id属性
    			dataType:'text',                       //服务器返回的格式,可以是json或xml等
    			success:function(data, status){        //服务器响应成功时的处理函数
    				data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
    				data = data.replace(/<PRE.*?>/g, ''); 
    				data = data.replace("<PRE>", '');
    				data = data.replace("</PRE>", '');  
    				data = data.replace("<pre>", '');  
    				data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
    			    
    				if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
    					$('#edithomework').hide();
    					$('#success').show();
    				}else{
    					$('#edithomework').hide();
    					$('#false').show();
    				}
    			},
    			error:function(data, status, e){ //服务器响应失败时的处理函数
    				$('#edithomework').hide();
    				$('#false').show();
    			}
    		});
};


function showNo1(){
	var temp = $("#switch1");
	  if(temp.is(':checked') == true){   
		  $("#NO1").fadeIn();
	  }else{   
	  $("#NO1").fadeOut();         
	  }   
};

function showNo2(){
	var temp = $("#switch2");
	  if(temp.is(':checked') == true){   
		  $("#NO2").fadeIn();
	  }else{   
		  $("#NO2").fadeOut();         
	  }   
};

$('#switch1').on('switch-change', function(e, data) {
    if(data.value)
    	$('#NO1').show();
    else
    	$('#NO1').hide();
});

$('#switch2').on('switch-change', function(e, data) {
    if(data.value)
    	$('#NO2').show();
    else
    	$('#NO2').hide();
});