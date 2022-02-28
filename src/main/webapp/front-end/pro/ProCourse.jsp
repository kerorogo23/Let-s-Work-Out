<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Let's Work Out &mdash; ProCourse</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="../commonFiles/head.jsp"></jsp:include>
</head>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">

	<div class="site-wrap">
		<jsp:include page="../commonFiles/header.jsp"></jsp:include>

		<div class="site-section" style="background: rgb(182, 235, 167);">
			<div class="container" style="margin-top: 50px">
				<div class="row">
					<div class="col-lg-4">
						<img  class="card-img-top" src="<%=request.getContextPath()%>/DBGifReader5?proId=${proVO.proId}" alt="Card image cap" style="width:150px;margin:0px 75px;">
					</div>
					<div class="col-lg-8">

						<div class="form-group row" style="margin-bottom: 0px">
							<div class="alert alert-warning" role="alert"
								style="margin-bottom: 0.5rem">
								<label for="name">姓名</label>
							</div>
							<div class="alert alert-primary" role="alert"
								style="margin-bottom: 0.5rem">
								<input type="text" readonly class="form-control-plaintext"
									id="name" value="大力水手">
							</div>
						</div>
						<div class="form-group row" style="margin-bottom: 0px">
							<div class="alert alert-warning" role="alert"
								style="margin-bottom: 0.5rem">
								<label for="skills">專長</label>
							</div>
							<div class="alert alert-primary" role="alert"
								style="margin-bottom: 0.5rem">
								<input type="text" readonly class="form-control-plaintext"
									id="skills" value="${proVO.proResume }">
							</div>
						</div>
						<div class="form-group row" style="margin-bottom: 0px">
							<div class="alert alert-warning" role="alert"
								style="margin-bottom: 0.5rem">
								<label for="expr">經歷</label>
							</div>
							<div class="alert alert-primary" role="alert"
								style="margin-bottom: 0.5rem">
								<input type="text" readonly class="form-control-plaintext"
									id="expr" value="${proVO.expr }">
							</div>
						</div>
						<table class="table" style="border-color: black; border: solid">
							<thead>
								<tr style="border-color: black; border: solid">
									<th scope="col" style="width: 100px">優惠價</th>
									<th scope="col" style="width: 100px">時數</th>
									<th scope="col" style="width: 300px">上課須知</th>
									<th scope="col" style="width: 100px">點我購買</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="courseVO" items="${list}">
									<tr>
										<th scope="row">${courseVO.coursePrice }</th>
										<td>${courseVO.courseHours }</td>
										<td>${courseVO.courseDetail }</td>
										<td>
											<form action="<%=request.getContextPath() %>/order/order.do?orderNo=${courseVO.courseId}" method="post">
												<input type="hidden" name="orderNo" value="${courseVO.courseId }"/>
												<input type="submit" value="GO">
											</form>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		<jsp:include page="../commonFiles/footer.jsp"></jsp:include>
		<jsp:include page="../commonFiles/scriptImport.jsp"></jsp:include>
	</div>
</body>
</html>