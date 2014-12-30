/**
 * 教师修改学生信息
 * 
 * @author zst
 */
$(document).ready(function() {
	$.ajax({
				cache : true,
				type : "POST",
				url : '/FinalDesign/course/getCoursesByTeacherIdInSemester',
				async : false,
				error : function(request) {
					alert('获取教师开设课程信息失败，请重试');
				},
				success : function(data) {
					createCourseSelect(data);
				}
			});

	$.ajax({
				cache : true,
				type : "POST",
				url : '/FinalDesign/classinfo/getAllAdminClass',
				async : false,
				error : function(request) {
					alert('获取行政班级信息失败，请重试');
				},
				success : function(data) {
					createClassSelect(data);
				}
			});

	$.ajax({
				cache : true,
				type : "POST",
				url : '/FinalDesign/classinfo/getAllteachClassByCourse?courseid='
						+ courseid,
				async : false,
				error : function(request) {
					alert('获取教师开设教学班信息失败，请重试');
				},
				success : function(data) {
					createTeachClassSelect(data);
				}
			});
});

/**
 * 创建行政班选项
 * 
 * @param classdata
 */
function createClassSelect(classdata) {
	var classselect = $("#classSelect");
	classselect.empty();
	for (var i = 0; i < classdata.length; i++) {
		var option = $("<option>").text(classdata[i].classYear + "级"
				+ classdata[i].className).val(classdata[i].classId);
		classselect.append(option);
	}
	if (classid != null) {
		$("#classSelect option[value='" + classid + "']")
				.attr("selected", true);
	}
};

/**
 * 创建课程选项
 * 
 * @param coursedata
 */
function createCourseSelect(coursedata) {
	var courseselect = $("#courseSelect");
	courseselect.empty();
	for (var i = 0; i < coursedata.length; i++) {
		var option = $("<option>").text(coursedata[i].courseName)
				.val(coursedata[i].courseId);
		courseselect.append(option);
	}
	if (courseid != null) {
		$("#courseSelect option[value='" + courseid + "']").attr("selected",
				true);
	}
};

/**
 * 创建教学班选项
 * 
 * @param coursedata
 */
function createTeachClassSelect(teachclassdata) {
	var teachclassSelect = $("#teachclassSelect");
	teachclassSelect.empty();
	for (var i = 0; i < teachclassdata.length; i++) {
		var option = $("<option>").text(teachclassdata[i].classYear + "年度"
				+ teachclassdata[i].className).val(teachclassdata[i].classId);
		teachclassSelect.append(option);
	}
	if (teachclassid != null) {
		$("#teachclassSelect option[value='" + teachclassid + "']").attr(
				"selected", true);
	}
};
/**
 * 保存修改后的学生信息
 */
function modifyStduentInfo() {
	var accountid = $("#accountId").attr("value");
	var username = $("#userName").attr("value");
	if (validate(accountid, username)) {
		$.ajax({
					cache : true,
					type : "POST",
					url : '/FinalDesign/userinfo/modifyStudentInfo',
					data : $('#userForm').serialize(),
					async : false,
					error : function(request) {
						alert('保存失败，请重试');
					},
					success : function(data) {
						alert('保存成功');
						window.location.href = "/FinalDesign/userinfo/getStudentInfo";
					}
				});
	}
}

/**
 * 取消按钮
 */
function cancel() {
	window.history.back(-1);
}

/**
 * 校验修改后的信息
 * 
 * @param accountid
 * @param username
 * @returns {Boolean}
 */
function validate(accountid, username) {
	var accounterror = document.getElementById("accounterror");
	var nameerror = document.getElementById("nameerror");
	if (!accountid.match("[0-9]{12}")) {
		accounterror.innerHTML = '学号为12位数字';
		accounterror.style.display = "";
		return false;
	} else {
		accounterror.style.display = "none";
	}

	if (username == "") {
		nameerror.innerHTML = '姓名不能为空';
		nameerror.style.display = "";
		return false;
	} else {
		nameerror.style.display = "none";
	}
	return true;
}

/**
 * 展示修改后的信息
 * 
 * @param userdata
 */
function showUserInfoAfterModify(userdata) {
	$("#accountId").attr("value", userdata[0].accountId);
	$("#userName").attr("value", userdata[0].userName);
}

/**
 * 改变课程选项联动带出对应的教学班信息
 * 
 * @param obj
 */
function onCourseSelectChange(obj) {
	var courseid = $(obj).val();// 未选择时为null
	$.ajax({
				cache : true,
				type : "POST",
				url : '/FinalDesign/classinfo/getAllteachClassByCourse?courseid='
						+ courseid,
				async : false,
				error : function(request) {
					alert('获取教师开设教学班信息失败，请重试');
				},
				success : function(data) {
					createTeachClassSelect(data);
				}
			});
};