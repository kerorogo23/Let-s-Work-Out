<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

* /
   th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>ListOneCourseOrder</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/course_order/select_page.jsp"><img
						src="<%=request.getContextPath()%>/images/back1.gif" width="100"
						height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>
	<table>

		<tr>
			<td>以訂單號碼查詢資料</td>
		</tr>
		<tr>
			<td>訂單號碼</td>
			<td>${courseOrderVO.courseOrderNo }</td>
		</tr>
		<tr>
			<td>課程號碼</td>
			<td>${courseOrderVO.courseId }</td>
		</tr>
		<tr>
			<td>會員號碼</td>
			<td>${courseOrderVO.memId }</td>
		</tr>
		<tr>
			<td>購買時數</td>
			<td>${courseOrderVO.courseOrderHours }</td>
		</tr>
		<tr>
			<td>訂單價格</td>
			<td>${courseOrderVO.courseOrderPrice }</td>
		</tr>
		<tr>
			<td>訂單狀況</td>
			<td>${courseOrderVO.courseOrderStatus }</td>
		</tr>
		
	
	</table>


</body>
</html>