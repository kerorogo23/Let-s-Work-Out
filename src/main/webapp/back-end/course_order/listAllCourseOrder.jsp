<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
				<h3>ListAllCourseOrder</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/back-end/course_order/select_page.jsp">
						<img src="<%=request.getContextPath()%>/images/back1.gif" width="100"
							height="32" border="0">回首頁
					</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>
		<tr>
			<th>訂單號碼</th>
			<th>課程號碼</th>
			<th>會員號碼</th>
			<th>購買時數</th>
			<th>訂單價格</th>
			<th>訂單狀況</th>
			<th>資料建立時間</th>
			<th>修改資料</th>
		</tr>
		<c:forEach var="courseOrderVO" items="${list}">
			<tr>
				<td>${courseOrderVO.courseOrderNo }</td>
				<td>${courseOrderVO.courseId }</td>
				<td>${courseOrderVO.memId }</td>
				<td>${courseOrderVO.courseOrderHours }</td>
				<td>${courseOrderVO.courseOrderPrice }</td>
				
				<td>
					<c:choose>
						<c:when test="${courseOrderVO.courseOrderStatus == -1}">取消訂單</c:when>
						<c:when test="${courseOrderVO.courseOrderStatus == 0}">待付款</c:when>
						<c:when test="${courseOrderVO.courseOrderStatus == 1}">已付款</c:when>						
					</c:choose>
				</td>
				
				<td><fmt:formatDate value="${courseOrderVO.createdTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>
					<form method="post" action="<%=request.getContextPath()%>/back-end/course_order.do">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="hidden" name="courseOrderNo" value="${courseOrderVO.courseOrderNo }">
						<input type="submit" value="修改">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>