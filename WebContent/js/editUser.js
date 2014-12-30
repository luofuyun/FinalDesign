$(document).ready(function(){
	if(userrole != ""){
	 $("#role option[value='"+userrole+"']").attr("selected",true);	
	}
	 $.ajax({
			cache:true,
			type:"POST",
         url:'/Program/classinfo/getAllClass',
//         data:$('#userForm').serialize(),
//         {
//         	name:name
//         },
         async:false,
			error:function(request){
         alert('获取课程信息失败，请重试');
		          },
         success:function(data){
         createClassSelect(data);	
         }
		});
	 if(userclass != ""){
	 $("#classSelect option[value='"+userclass+"']").attr("selected",true);
	 }
});

function createClassSelect(classdata){
	var friend = $("#classSelect");
	friend.empty();
	for(var i=0;i<classdata.length;i++) {
		if(classdata[i].type == 1){
	    var option = $("<option>").text(classdata[i].className).val(classdata[i].classId);
	    friend.append(option);
		}
	}
}

function addUser(){
	alert('---add');
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
