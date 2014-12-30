<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="pageUrl" required="true" rtexprvalue="true" description="分页页面对应的URl" %>
<%@ attribute name="pageAttrKey" required="true" type="com.zhst.Bean.PageBean" rtexprvalue="true" description="Page对象在Request域中的键名称" %>

<%
String separator = pageUrl.indexOf("?") > -1?"&":"?";
jspContext.setAttribute("pageResult", pageAttrKey);  
jspContext.setAttribute("pageUrl", pageUrl);
jspContext.setAttribute("separator", separator);
%>
<div>

	<c:if test="${pageResult.currentPage <= 1}">
	<font style="color:#428BCA; ">首页&nbsp;&nbsp;</font> 
	</c:if>
	<c:if test="${pageResult.currentPage > 1 }">
		<a href="${pageUrl}${separator}pageNo=1" style="color:#428BCA;">首页</a>&nbsp;&nbsp;
	</c:if>
	
	<c:if test="${pageResult.hasPreviousPage}">
		<a href="${pageUrl}${separator}pageNo=${pageResult.currentPage-1}" style="color:#428BCA; ">上一页</a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${!pageResult.hasPreviousPage}">
		<font style="color:#428BCA; ">上一页&nbsp;&nbsp;</font>
	</c:if>
	
	<c:choose>
	<c:when test="${pageResult.currentPage <= 5}">
		<c:forEach var="i" begin="1" end="${pageResult.currentPage-1}">		    
			<a href="${pageUrl}${separator}pageNo=${i}">【${i}】</a>&nbsp;		
		</c:forEach>
		【${pageResult.currentPage}】&nbsp;
	</c:when>
	<c:otherwise>
	...&nbsp;
	     <c:forEach var="i" begin="${pageResult.currentPage - 3}" end="${pageRersult.currentPage}">
			<a href="${pageUrl}${separator}pageNo=${i}">【${i}】</a>&nbsp;
		</c:forEach>
	</c:otherwise>
	</c:choose>
	
	<c:choose>
	<c:when test="${pageResult.currentPage >= pageResult.totalPage-4 || pageResult.totalPage-4 <= 0}">
		<c:forEach var="i" begin="${pageResult.currentPage+1}" end="${pageResult.totalPage}">
			<a href="${pageUrl}${separator}pageNo=${i}">【${i}】</a>&nbsp;
		</c:forEach>
	</c:when>
	<c:otherwise>
	     <c:forEach var="i" begin="${pageResult.currentPage + 1}" end="${pageRersult.currentPage+3}">
			<a href="${pageUrl}${separator}pageNo=${i}">【${i}】</a>&nbsp;
		</c:forEach>
		...&nbsp;
	</c:otherwise>
	</c:choose>
	
	
	<c:if test="${pageResult.hasNextPage}">
		<a href="${pageUrl}${separator}pageNo=${pageResult.currentPage+1}" style="color:#428BCA; ">下一页</a>&nbsp;&nbsp;
	</c:if>
	<c:if test="${!pageResult.hasNextPage}">
		<font style="color:#428BCA; ">下一页&nbsp;&nbsp;</font>
	</c:if>
	
	<c:if test="${pageResult.currentPage >= pageResult.totalPage}">
	 <font style="color:#428BCA; ">末页&nbsp;&nbsp;</font>
	</c:if>
	<c:if test="${pageResult.currentPage < pageResult.totalPage}">
	   <a href="${pageUrl}${separator}pageNo=${pageResult.totalPage }" style="color:#428BCA; ">末页</a>&nbsp;&nbsp;
	</c:if>
	
		<font style="color:#428BCA; ">当前第 ${pageResult.currentPage} / ${pageResult.totalPage} 页    共 ${pageResult.allRow} 记录 </font>
	
</div>