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
		action="<%=request.getContextPath()%>/back-end/course_order.do">
		<table>
			<tr>
				<td>以訂單號碼查詢資料</td>
			</tr>
			<tr>
				<td><input type="text" name="courseOrderNo" value=""
					placeholder="請填入訂單號碼" /></td>
			</tr>
			<tr>
				<td><button type="submit">送出</button></td>
			</tr>
		</table>
		<input type="hidden" name="action" value="getOne_For_Display" />
	</form>
	<a href="<%=request.getContextPath()%>/back-end/course_order/addCourseOrder.jsp">點我去建立資料</a>
	<a href="<%=request.getContextPath()%>/back-end/course_order.do?action=listAll">點我看全部資料</a>
<%-- 	<a href="<%=request.getContextPath()%>/back-end/course_order/Update_courseOrder_input.jsp">點我去修改資料</a> --%>

	
</body>
</html>