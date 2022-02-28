<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<form method="post"
		action="<%=request.getContextPath()%>/back-end/course_schedule.do">
		<table>
			<tr>
				<td>訂單號碼</td>
				<td><input type="text" name="courseOrderNo" value="${courseScheduleVO.courseOrderNo }"/></td>
			</tr>
			<tr>
				<td>預約上課時間</td>
				<td><input type="text" name="reserveTime" value="${courseScheduleVO.reserveTime }"/></td>
			</tr>
			<tr>
				<td>預約狀態</td>
				<td><input type="text" name="courseStatus" value="${courseScheduleVO.courseStatus }"/></td>
			</tr>

		</table>
		<input type="hidden" name="action" value="insert" /> <input
			type="submit" value="送出新增" />
	</form>
</body>
</html>