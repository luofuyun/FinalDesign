<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="userForm" name="userForm" method="post" action="">
						<input type="hidden" id="userId" name="userId" value="${USER_CONTEXT.userId}"/>
						
						<table style="font-size: 15px;border:none;cellpadding:'5';cellspacing:'5'" >

							<h2>
								以下带<font style="color: red;"> * </font>号为必填项
							</h2>

							<tr>
							<c:choose>
                               <c:when test="${USER_CONTEXT.role == 1}">
								<td><font style="color: red;"> * </font>账&nbsp; 号 :</td>
								<td><input type="text" id="accountId" name="accountId" value="${USER_CONTEXT.accountId}"/></td>
								</c:when>
								<c:when test="${USER_CONTEXT.role == 2}">
								<td><font style="color: red;"> * </font>工&nbsp; 号 :</td>
								<td><input type="text" id="accountId" name="accountId" value="${USER_CONTEXT.accountId}"/></td>		
								</c:when>
								<c:otherwise>
								<td><font style="color: red;"> * </font>学&nbsp; 号 :</td>
								<td><input type="text" id="accountId" name="accountId" disabled="disabled" value="${USER_CONTEXT.accountId}"/></td>
								</c:otherwise>
							</c:choose>
								<td><div id="accounterror" style="color:red;display: none"></div></td>
							</tr>
							<tr>
								<td><font style="color: red;"> * </font>姓&nbsp; 名 :</td>
								<td><input type="text" id="userName" name="userName" value="${USER_CONTEXT.userName}" maxLength="15"/></td>
								<td><div id="nameerror" style="color:red;display: none"></div></td>
							</tr>
							<c:choose>
							<c:when test="${USER_CONTEXT.role == 3}">
							<tr>
								<td><font style="color: red;"> * </font>班&nbsp; 级 :</td>
								<td><input type="text" id="adminclass" name="adminclass" disabled="disabled" value="${USER_CONTEXT.adminclass.classYear}级${USER_CONTEXT.adminclass.className}" maxLength="15"/></td>
								<td><div id="nameerror" style="color:red;display: none"></div></td>
							</tr>
							</c:when>
								<c:when test="${USER_CONTEXT.role == 2}">
							<tr>
								<td><font style="color: red;"> * </font>系&nbsp; 别 :</td>
								<td><input type="text" id="department" name="department" disabled="disabled" value="${USER_CONTEXT.department}系" maxLength="15"/></td>
								<td><div id="nameerror" style="color:red;display: none"></div></td>
							</tr>
							</c:when>
							</c:choose>
							<tr>
								<td>&nbsp;&nbsp;性&nbsp;&nbsp; 别 :</td>
								<td>
								<c:choose>
		                            <c:when test="${USER_CONTEXT.gender == 1}">
								<select id="gender" name="gender" class="select" size="1">
										<option value="1" selected="selected">男</option>
										<option value="2">女</option>
								</select>
								</c:when>
								<c:otherwise>
								<select id="gender" name="gender" class="select" size="1">
										<option value="1">男</option>
										<option value="2" selected="selected">女</option>
								</select>
								</c:otherwise>
								</c:choose>
								</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp;&nbsp;联系信息 :</td>
								<td><input type="text" id="contact" name="contact" value="${USER_CONTEXT.contact}" /></td>
								<td><div id="phoneerror" style="color:red;display: none"></div></td>
							</tr>
							<tr>
							<td>&nbsp;修改密码 :</td>
							<td><input type="checkbox" id="checkModifypsd" name="checkModifypsd" onchange="showPsdArea()"/>&nbsp;是</td>
							<td></td>
							</tr>
							<tr>
							<td colspan="3" align="center">
							<div id="psdarea" style="display: none">
							<table>
							<tr>
								<td colspan="2"><font style="color: red;">&nbsp; 注&nbsp;意</font>：密码长度均为6 - 20位</td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp&nbsp新 密 码 :</td>
								<td><input type="password" id="password" name="password" /></td>
								<td></td>
							</tr>
							<tr>
								<td>&nbsp&nbsp确认密码 :</td>
								<td><input type="password" id="repassword" name="repassword" /></td>
								<td align="right"><div id="psderror" style="color:red;display: none"></div></td>
							</tr>
							</table>	
							</div>
							</td>
					       </tr>
					       
							<tr>
								<td></td>
								<td><text id="save_button" name="save_button"
										class="btn btn-primary"
										onclick="modifyPersonalInfo()" type="submit">
										保存
									</text>&nbsp;&nbsp;
									<text id="cancel_button" name="cancel_button"
										class="btn btn-primary"
										onclick="cancel()" type="submit">
										取消
									</text>
								</td>
							</tr>
						</table>
					</form>