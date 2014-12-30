
/**
 * @author luofuyun
 *
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
	if (courseType != "") {
		$("#courseforteacher option[value='" + courseType + "']").attr(
				"selected", true);
	}
});

function addClass(classId) {
	var courseselect = $("#courseforteacher");
	var courseId = courseselect.val();
	location = "/FinalDesign/classinfo/addClass?classId=" + classId
			+ "&courseId=" + courseId;
};

function deClass(classId) {
	var a = window.confirm("确定删除 ？");
	if (a) {
		$.get("/FinalDesign/classinfo/deleteClass?classId=" + classId,
				function(data) {
					if ("success" == data.result) {
						alert("删除成功");
						window.location.reload();
					} else {
						alert("删除失败");
					}
				});
	}
};

/**
 * 创建课程选项
 * 
 * @param coursedata
 */
function createCourseSelect(coursedata) {
	var courseselect = $("#courseforteacher");
	courseselect.empty();
	for (var i = 0; i < coursedata.length; i++) {
		var option = $("<option>").text(coursedata[i].courseName)
				.val(coursedata[i].courseId);
		courseselect.append(option);
	}
};

// 全选框勾选--选择所有
function switchSelectBox() {
	var selectBoxs = document.all("selectUserCk");
	if (!selectBoxs)
		return;
	if (typeof(selectBoxs.length) == "undefined") {// only one checkbox
		selectBoxs.checked = event.srcElement.checked;
	} else {// many checkbox ,so is a array
		for (var i = 0; i < selectBoxs.length; i++) {
			selectBoxs[i].checked = event.srcElement.checked;
		}
	}
};
// 选择开设课程后对课程筛选显示
function onCourseSelectChange(obj) {
	var courseidval = $(obj).val();// 未选择时为null
	location = "/FinalDesign/classinfo/courseClassMan?courseid=" + courseidval;
};

/**
 * 带着学生信息跳转到另一个页面
 * 
 * @param student
 */
function toEditStudent(teachclassid, studentid) {
	var courseid = $("#courseforteacher").val();
	top.location = "/FinalDesign/userinfo/toEditStudentInfo?courseid="
			+ courseid + "&studentid=" + studentid + "&teachclassid="
			+ teachclassid;
};

/**
 * 为学生重置密码
 * 
 * @param username
 * @param userid
 */
function resetPassword(username, userid) {
	var a = window.confirm("确定重置 " + username + " 的登陆密码？");
	if (a) {
		$.get("/FinalDesign/userinfo/resetPassword?userId=" + userid, function(
						data) {
					if ("success" == data.result) {
						alert("重置密码成功，初始密码为 123456");
					} else {
						alert("重置密码失败");
					}
				});
	}
};