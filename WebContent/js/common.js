/**
 * 
 */

var Common = {};
/**
 * 展示处理中的等待条（waitingBar）
 * 参数：
 * 		msg : 需要显示的消息
 */
Common.showProcessingWindow = function(msg) {
	if(msg == null) 
	{
		msg = "正在处理中，请稍后......";
	}
	var $processingBarDiv = $('#processingBar');
	if($processingBarDiv.length > 0)
	{
		$('#processingBar').window("open");
		return ;
	}
	$("body").append("<div id='processingBar' style='margin:0 auto;text-align:center;'></div>");
	$('#processingBar').window({
		title : "",
		width:250,
		height:80,
		closable : false,
		draggable : false,
		collapsible : false,
		minimizable : false,
		maximizable : false,
		resizable : false,
		inline : true,
		content: "<div style='display:inline-block;margin-top:20px;'><span class='loading'></span><span style='margin-left:10px;'>" + msg + "</span></div>",
		modal:true
	});
};
/**
 * 关闭进度条
 */
Common.closeProcessingWindow = function() {
	$('#processingBar').window("close");
};
/**
 * 判断字符串是否为空
 * 参数：str-需要进行判断的字符串
 */
Common.isStringEmpty = function(str) {
	if(str == null || $.trim(str).length < 1)
	{
		return true;
	}
	else
	{
		return false;
	}
};
