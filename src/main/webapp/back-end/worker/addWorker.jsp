<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="com.worker.model.*"%>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增 - addWorker.jsp</title>

<!-- Custom fonts for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/worker/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="<%=request.getContextPath()%>/back-end/worker/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>

<body bgcolor='white'>

	<!-- Page Wrapper -->
	<div id="wrapper">

		<%@ include file="/back-end/header.file"%>
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">

				<!-- Topbar -->
				<%@ include file="/back-end/logout.file"%>
				<!-- End of Topbar -->

				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">新增員工</h1>
					</div>

				</div>
				<!-- /.container-fluid -->
				<div class="row justify-content-center">



					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/worker/worker.do"
						name="form1">
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</c:if>
						<table style="border: 3px #cccccc solid;" cellpadding="10"
							border='0'>
							<tr>
								<td colspan=2><img
									src="<%=request.getContextPath()%>/back-end/worker/images/gym-logo.jpg"
									width=100% height="100" border="0"></td>
							</tr>
							<tr>
								<td Width="100">員工姓名:</td>
								<td><input type="TEXT" name="w_name" id="w_name" size="30"
									value="<%=(workerVO == null) ? "" : workerVO.getW_name()%>" /></td>
							</tr>
							<tr>
								<td>帳號:</td>
								<td><input type="TEXT" name="w_acc" id="w_acc" size="30"
									value="<%=(workerVO == null) ? "" : workerVO.getW_acc()%>" /></td>
							</tr>
							<tr>
								<td>密碼:</td>
								<td><input type="TEXT" name="w_acc" id="w_acc" size="30"
									value="<%=(workerVO == null) ? "" : workerVO.getW_acc()%>" /></td>
							</tr>
							<tr>
								<td>信箱:</td>
								<td><input type="TEXT" name="email" id="email" size="30"
									value="<%=(workerVO == null) ? "" : workerVO.getEmail()%>" /></td>
							</tr>

							<tr>
								<td colspan="2">權限:
									<button type="button" id="checkAll">全選</button>
									<button type="button" id="cancel">清除</button>
								</td>
								<tr>
      	
							<tr>    	
      		<td colspan="2">
      		<jsp:useBean id="PowerListSvc" scope="page"
										class="com.power_list.model.Power_listService" />
			<c:forEach var="powerlistVO" items="${PowerListSvc.all}">
				<input type="checkbox" name="POWER" value="${powerlistVO.power_id}"
											${fn:contains(wp, powerlistVO.power_id)? 'checked':''}>${powerlistVO.power_name}
			</c:forEach>
								</td>   	
      	</tr>
      </table>
  	  <br>	
      <input type="hidden" name="action" value="insert">
      <input type="submit" value="送出新增">
	  <button type="button" id="magic">小按鈕</button>
		
      </FORM>
      
  
      </div>

    </div>
    <!-- End of Main Content -->



  </div>
  <!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
								<a class="scroll-to-top rounded" href="#page-top">
  <i class="fas fa-angle-up"></i>
</a>

<!-- Bootstrap core JavaScript-->
<script
									src="<%=request.getContextPath()%>/back-end/worker/assets/vendor/jquery/jquery.min.js"></script>
<script
									src="<%=request.getContextPath()%>/back-end/worker/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script
									src="<%=request.getContextPath()%>/back-end/worker/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script
									src="<%=request.getContextPath()%>/back-end/worker/assets/js/sb-admin-2.min.js"></script>


							</body>

<script>
	function loadImageFile(event) {
		var image = document.getElementById('image');
		image.src = URL.createObjectURL(event.target.files[0]);
		image.height = 200;
		image.width = 200;
	};

	$("#magic").click(function() {
		$("#w_name").val("牛番茄");
		$("#w_acc").val("tomato79");
		$("#email").val("tomato79@gmail.com");
	});

	$("#checkAll").click(function() {
		$("input[name='POWER']").prop("checked", true);//把所有的核取方框的property都變成勾選
	});
	$("#cancel").click(function() {
		$("input[name='POWER']").prop("checked", false);//把所有的核取方框的property都變成不勾選
	});
</script>




