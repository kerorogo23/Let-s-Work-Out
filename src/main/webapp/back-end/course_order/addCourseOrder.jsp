<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
		action="<%=request.getContextPath()%>/back-end/course_order.do">
		<table>
			<tr>
				<td>課程號碼</td>
				<td><input type="text" name="courseId" value="${courseOrderVO.courseId }"/></td>
			</tr>
			<tr>
				<td>會員號碼</td>
				<td><input type="text" name="memId" value="${courseOrderVO.memId }"/></td>
			</tr>
			<tr>
				<td>購買時數</td>
				<td><input type="text" name="courseOrderHours" value="${courseOrderVO.courseOrderHours }"/></td>
			</tr>
			<tr>
				<td>訂單價格</td>
				<td><input type="text" name="courseOrderPrice" value="${courseOrderVO.courseOrderPrice }"/></td>
			</tr>
			<tr>
				<td>訂單狀況</td>
				<td><input type="text" name="courseOrderStatus" value="${courseOrderVO.courseOrderStatus }"/></td>
			</tr>

		</table>
		<input type="hidden" name="action" value="insert" /> <input
			type="submit" value="送出新增" />
	</form>
</body>
</html>