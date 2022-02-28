<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>
<style>
table {
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

   th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<table id="table-1" style="width:600px">
		<tr>
			<td>
				<h3>ListAllCourseSchedule</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/course_schedule/select_page.jsp"><img
						src="<%=request.getContextPath()%>/images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<table>
		<tr>
			<th>預約單號碼</th>
			<th>訂單號碼</th>
			<th>預約上課時間</th>
			<th>預約狀態</th>
			<th>資料建立時間</th>
			<th>修改資料</th>
		</tr>
		<c:forEach var="courseScheduleVO" items="${list}">
			<tr>
				<td>${courseScheduleVO.scheduleNo }</td>
				<td>${courseScheduleVO.courseOrderNo }</td>
				<td>
					<fmt:formatDate value="${courseScheduleVO.reserveTime}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<c:choose>
						<c:when test="${courseScheduleVO.courseStatus == -1}">曠課</c:when>
						<c:when test="${courseScheduleVO.courseStatus == 0}">預備上課</c:when>
						<c:when test="${courseScheduleVO.courseStatus == 1}">已上課</c:when>
						<c:when test="${courseScheduleVO.courseStatus == 2}">取消預約</c:when>
					</c:choose>
				</td>
				<td><fmt:formatDate value="${courseScheduleVO.createdTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<form method="post" action="<%=request.getContextPath()%>/back-end/course_schedule.do">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="hidden" name="scheduleNo" value="${courseScheduleVO.scheduleNo }">
						<input type="submit" value="修改">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>