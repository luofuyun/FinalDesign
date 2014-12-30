function modifyClassInfo() {
	alert("avda");
	var classId = $("#classId").attr("value");
	var className = $("#className").attr("value");
	var courseId = $("#courseId").attr("value");
	if (className != null && className != "" && courseId != null
			&& courseId != "") {
		$.ajax({
					cache : true,
					type : "POST",
					url : '/FinalDesign/classinfo/modifyOrSaveClassInfo',
					async : false,
					data : $('#form').serialize(),
					success : function(data) {
						alert('保存成功');
						location.reload();
					},
					error : function(request) {
						alert('保存失败，请重试');
					}
				});
	}
};
function cancel() {
	window.history.back(-1); 
}