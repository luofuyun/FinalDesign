<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); 
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"; %>   
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!--[if IE 7]>
<html class="ie ie7" lang="zh-CN">
<![endif]-->
<!--[if IE 8]>
<html class="ie ie8" lang="zh-CN">
<![endif]-->
<!--[if !(IE 7) | !(IE 8)  ]><!-->
<html lang="zh-CN">
<!--<![endif]-->
<head>
<meta charset="utf-8">

<style type="text/css"> 
/* CSS Document */ 

body { 
    font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    background: #E6EAE9; 
} 

a { 
    color: #c75f3e; 
} 

#mytable { 
    width: 100%; 
    padding: 0; 
    margin: 0; 
} 

caption { 
    padding: 0 0 5px 0; 
    width: 700px;      
    font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    text-align: right; 
} 

th { 
    font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #4f6b72; 
    border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    border-top: 1px solid #C1DAD7; 
    letter-spacing: 2px; 
    text-transform: uppercase; 
    text-align: left; 
    padding: 6px 6px 6px 12px; 
    background: #CAE8EA url(images/bg_header.jpg) no-repeat; 
} 

th.nobg { 
    border-top: 0; 
    border-left: 0; 
    border-right: 1px solid #C1DAD7; 
    background: none; 
} 

td { 
    border-right: 1px solid #C1DAD7; 
    border-bottom: 1px solid #C1DAD7; 
    background: #fff; 
    font-size:11px; 
    padding: 6px 6px 6px 12px; 
    color: #4f6b72; 
} 


td.alt { 
    background: #F5FAFA; 
    color: #797268; 
} 

th.spec { 
    border-left: 1px solid #C1DAD7; 
    border-top: 0; 
    background: #fff url(images/bullet1.gif) no-repeat; 
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
} 

th.specalt { 
    border-left: 1px solid #C1DAD7; 
    border-top: 0; 
    background: #f5fafa url(images/bullet2.gif) no-repeat; 
    font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif; 
    color: #797268; 
} 
/*---------for IE 5.x bug*/ 
html>body td{ font-size:11px;} 
</style> 

<style type="text/css">
    .center{
    margin: 0 auto;
}
    .left{
    float:right;
    }
</style>

<script type="text/javascript">

function show(){
	//alert("AAA");
	document.getElementById('updatehomework').style.display = ""; 
}

function ajaxFileUpload(){
	
	$.ajaxFileUpload({
		url:'/Program/item/studentUploadfile?description='+document.getElementById( "description" ).value+"'",
		secureuri:false,                       //是否启用安全提交,默认为false 
		fileElementId:'myBlogImage',           //文件选择框的id属性
		dataType:'text',                       //服务器返回的格式,可以是json或xml等
		success:function(data, status){        //服务器响应成功时的处理函数
			data = data.replace(/<pre.*?>/g, '');  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			data = data.replace(/<PRE.*?>/g, ''); 
			 data = data.replace("<PRE>", '');
			 data = data.replace("</PRE>", '');  
			 data = data.replace("<pre>", '');  
			 data = data.replace("</pre>", '');//本例中设定上传文件完毕后,服务端会返回给前台[0`filepath]
		
			if(data.substring(0, 1) == 0){     //0表示上传成功(后跟上传后的文件路径),1表示失败(后跟失败描述)
				alert("上传成功！");
			}else{
				alert("上传失败！");
			}
		},
		error:function(data, status, e){ //服务器响应失败时的处理函数
			alert("上传失败！请重试");
		}
	});
}

</script>  

<!-- 设置标题 -->
<title>学生主界面 </title> 
<link rel='stylesheet' id='zanblog-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/style.css?ver=2.0.7' type='text/css' media='all' />
<link rel='stylesheet' id='bootstrap-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/bootstrap.css?ver=3.0.0' type='text/css' media='all' />
<link rel='stylesheet' id='fontawesome-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/font-awesome/css/font-awesome.min.css?ver=4.0.1' type='text/css' media='all' />
<link rel='stylesheet' id='icheck-style-css'  href='http://www.yeahzan.com/zanblog/wp-content/themes/zanblog/ui/css/flat/red.css?ver=3.6' type='text/css' media='all' />

<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.1.10.2.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/embed.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.3.0.0.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zanblog.2.0.7.js"></script>   
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.icheck.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/ajaxfileupload.js"></script>     
<script type='text/javascript' src='<%=path%>/js/student.js'></script>
<script type="text/javascript">
	setInterval("showtime.innerHTML='登录日期：'+new Date().toLocaleString();",1000);
</script>
</head>
<body class="home blog">
<jsp:include page="StudentPublicJsp.jsp" />
	<div class="container">	
	 <!--  作业提交 -->
	 <div id="updatehomework" style="display:none">
	 		 <h4><i class="fa fa-list-ul"></i>  当前位置 ：作业管理</h4>

		<section class="well">
		<div text-align="center"><h2>实验1</h2></div></br>
		<div style="width:79%; height:65%;" class="center">
			<h4>实验要求：
			<div style="width:76%; height:65%;" class="center">
			
			（1）设计一个15行15列棋盘，要求自行给出估价函数，按极大极小搜索方法，并采用α-β减枝技术。</br></br>
	（2）采用人机对弈方式，对弈双方设置不用颜色的棋子，一方走完后，等待对方走步，对弈过程的每个棋局都在屏幕上显示出来。当某一方在横、竖或斜方向上先有5个棋子连成一线时，该方为赢。</br></br>
	（3）提交一篇实验论文，以及完整的软件（包括源程序和可可执行程序）和相关文档。	</br></br></br></br>
	
	
		<input type="text" style="width:80%; height:10%;"/>
			<input class="btn btn-primary btn-default"  data-toggle="modal" data-target="#myModal" type="button" value="文件上传"/>		
			</div>
			</h4>		
		</div>	
		</section>
		 </div>
		 
		 
		 
		 
		 
		<!--  个人信息管理   -->
		
		<div id="personmessage" style="display:none">
		 <h4><i class="fa fa-list-ul"></i>  当前位置 ：个人信息管理</h4>

		<section class="well">
			<div text-align="center">
				<form id="userForm" name="userForm" method="post" action="" >
					<input type="hidden" name="userForm" value="userForm" /></input>
                        <table style="font-size:15px;" cellpadding="5" cellspacing="5">
							<h2>以下带<font style="color:red;"> * </font>号为必填项</h2>
							<tr>
                                <td><font style="color:red;"> * </font>账   号 :</td>
                                <td>201130740330</td>
                            </tr>
                            <tr>
                                <td><font style="color:red;"> * </font>姓   名 :</td>
                                <td><input type="text" name="j_idt52" value="学生" /></td>
                            </tr>
                            <tr>
                                <td> &nbsp;&nbsp;联系信息 :</td>
                                <td><select name="j_idt54" class="select" size="1">	<option value="手机号码">手机号码</option>
									<option value="E-mail">E-mail</option>
									<option value="其他">其他</option>
									</select><input type="text" name="j_idt58" />
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">修改密码需注意：密码长度均为6 - 20位</td>
                            </tr>
                            <tr>
                                <td>&nbsp;&nbsp;旧 密 码  :</td>
                                <td><input type="password" name="j_idt60" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;&nbsp;新 密 码  :</td>
                                <td><input type="password" name="j_idt62" value="" />
                                </td>
                            </tr>
                            <tr>
                                <td>&nbsp;&nbsp;重复密码 :</td>
                                <td><input type="password" name="j_idt64" value="" />
                                </td>
                            </tr>
							<tr>
                                <td></td>
                                <td><button id="j_idt66" name="j_idt66" class="btn btn-primary" onclick="" type="submit">保存</button><script id="j_idt66_s" type="text/javascript">PrimeFaces.cw('CommandButton','widget_j_idt66',{id:'j_idt66'});</script>
                                </td>
                            </tr>
                                    
									
                        </table>
                            
                </form>
			
			</div>
				
		</section>
	</div>
		
	
		<!--  教学文档下载 -->
	<div id="downloadfile" style="display:none">
                
                    
                <h4><i class="fa fa-list-ul"></i>当前位置： 教学文档下载</h4>
				<section class="well">
					<form  name="conditionForm" method="post" action="">
						<input type="hidden" name="conditionForm" value="conditionForm" />
		
                           
                        <div class="search">
                           关键字 :   <input type="text"/>
									
									<button id="search" name="search" class="btn btn-primary" onclick="" type="submit">查询</button>
                        </div>
						<div text-align="center">
                        <div>
								<div id="submits" class="ui-datatable ui-widget">
								<div class="ui-datatable-tablewrapper">
								
							<table id="mytable" cellspacing="0" summary="The technical specifications of the Apple PowerMac G5 series"> 
							<caption> </caption> 
							<tr> 
								<th scope="col" abbr="Configurations">文档名称</th> 

								<th scope="col" abbr="Dual 1.8">文档大小</th> 
								<th scope="col" abbr="Dual 2">上传教师</th> 
								<th scope="col" abbr="Dual 2.5">上传日期</th> 
								<th scope="col" abbr="Dual 2">操作</th> 
							</tr> 
							<tr> 
								<th scope="row" abbr="Model" class="spec">DBTool.java</th> 
								<td>64KB</td> 

								<td>少涛</td> 
								<td>2014-4-3</td>
								<td><button class="btn btn-primary"> 下载</button>     <button class="btn btn-primary">删除</button></td> 
							</tr> 
							<tr> 
								<th scope="row" abbr="G5 Processor" class="specalt">hehe.word</th> 
								<td class="alt">fuckyou</td> 
								<td class="alt">roderick</td> 

								<td class="alt">2014-4-6</td>
								<td class="alt"><button class="btn btn-primary"> 下载</button>     <button class="btn btn-primary">删除</button></td> 	
							</tr> 
							<tr> 
								<th scope="row" abbr="Frontside bus" class="spec">tomcat.zip</th> 
								<td>tomcat安装包</td> 

								<td>pengo.wen</td> 
								<td>2014-4-6</td>
								<td><button class="btn btn-primary"> 下载</button>     <button class="btn btn-primary">删除</button></td> 
							</tr> 
							</table> 

								</div>
								</div>
								
						</div>
						</div>
					</form>
				</section>

				</div>
				
</div>
 <div class="modal fade " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  		<div class="modal-dialog">
    		<div class="modal-content">
    			  <div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title" id="myModalLabel">上传文件</h4>
      			  </div>
      			<div class="modal-body" >
      			
  						<div class="form-group">
    						<label for="inputEmail3" class="col-sm-3 control-label" >上传文件：</label>
    						<div class="col-sm-6">
      							<input type="file"  id="myBlogImage" name="myfiles" size="30"/>
    						</div>
 					 	</div>
 					 	<div class="form-group">
    						<label for="inputPassword3" class="col-sm-3 control-label">文件描述：</label>
    						<div class="col-sm-6">
     						<input type="text"  id="description" class="form-control" name="description" size="45" value="文件关键字不超过15个字" maxlength="15"/>	
     						</div>
  						</div>
      <div class="modal-footer">
        	<input type="submit" onclick="ajaxFileUpload();" value="上传" name="yes" />
        	<input type="button" onclick="checkcancel();"value="取消" name="yes" />
 
      </div>
       
       </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<footer id="zan-footer">	      	
	Copyright © 2014 <a target="_blank" title="华南农业大学" href="">java小组</a> Just do it, <a target="_blank" title="华南农业大学信息学院" href="">华南农业大学信息学院</a>. All Rights Reserved.
	<!--统计代码开始-->
        	                            	    <script type="text/javascript">
			var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
			document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fb8510898b22f723bada9539bb4177030' type='text/javascript'%3E%3C/script%3E"));
		</script>                                <!--统计代码结束-->
</footer>
</body>
</html>
