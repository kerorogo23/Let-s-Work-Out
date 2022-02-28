<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.mybox {
	width: 80%;
	margin: 0 auto;
}
</style>
</head>


<body bgcolor='white'>
	<%-- <%錯誤訊息%> --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>


	<form method="post"
		action="<%=request.getContextPath()%>/back-end/pro.do">
		<table>
			<tr>
				<td>以教練編號查詢資料</td>
			</tr>
			<tr>
				<td><input type="text" name="proId" value=""
					placeholder="請填入教練編號" /></td>
			</tr>
			<tr>
				<td><button type="submit">送出</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="getOne_For_Display" />
	</form>
	<a href="<%=request.getContextPath()%>/back-end/pro/addPro.jsp">點我去建立資料</a>
	<a href="<%=request.getContextPath()%>/back-end/pro.do?action=listAll">點我看全部資料</a>


	
</body>
</html>