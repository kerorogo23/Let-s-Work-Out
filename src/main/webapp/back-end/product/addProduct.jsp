<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO");
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>商品資料新增</title>

<!-- Custom fonts for this template -->
<link href="assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

<!-- Custom styles for this page -->
<link href="assets/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">

</head>
<body id="page-top">

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
					<h1 class="h3 mb-2 text-gray-800">商品新增</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">

	<FORM METHOD="post" ACTION="product.do" name="form1">
		<table>
			<tr>
				<td>商品分類編號:</td>
				<td><select size="1"name = "p_category_no">
						<option value = "1"selected>1-食品</option>
						<option value = "2">2-健身</option>
						<option value = "3">3-瑜珈</option>
				</select></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="TEXT" name="p_name" size="45"
					value="<%=(productVO == null) ? "乳清白" : productVO.getP_name()%>" /></td>
			</tr>
			<tr>
				<td>商品價格:</td>
				<td><input type="TEXT" name="p_price" size="45"
					value="<%=(productVO == null) ? "" : productVO.getP_price()%>" /></td>
			</tr>
			<tr>
				<td>商品詳情:</td>
				<td><input type="TEXT" name="p_detail" size="45"
					value="<%=(productVO == null) ? "" : productVO.getP_detail()%>" /></td>
			</tr>
			<tr>
				<td>商品狀態:</td>
				<td>
				<lable><input type="radio" name="p_status" value="0" checked>上架中</lable>
				<lable><input type="radio" name="p_status" value="1">已下架</lable>
				<td>	
			</tr>
			<tr>
				<td>上架時間:</td>
				<td><input name="p_upload_time" id="f_date1" type="date"></td>

			</tr>
			




		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>
		
													
											
											
										
									</div>
								</div>
							</div>

						</div>
						<!-- /.container-fluid -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!-- End of Footer -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- 				Setting Modal -->
	<div class="modal fade" id="Setting" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">修改密碼</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/worker/worker.do"
						name="form1">
						<table>
							<tr>
								<td>原密碼:</td>
								<td><input type="password" name="w_pw" size=45 /></td>
							</tr>
							<tr>
								<td>新密碼:</td>
								<td><input type="password" name="w_npw" id="t_name" size=45 /></td>
							</tr>
						</table>
						<br> <input type="hidden" name="work_id"
							value="${workerVOLogin.work_id}"> <input type="hidden"
							name="action" value="update_pw"> <input type="submit"
							value="送出修改">
					</FORM>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/product/assets/vendor/jquery/jquery.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/product/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script src="<%=request.getContextPath()%>/back-end/product/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script src="<%=request.getContextPath()%>/back-end/product/assets/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script src="<%=request.getContextPath()%>/back-end/product/assets/vendor/datatables/jquery.dataTables.min.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/product/assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="<%=request.getContextPath()%>/back-end/product/assets/js/demo/datatables-demo.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/product/assets/js/wedyListAllProduct.js"></script>
	
</body>

<script>
	// 	$("#dataTable").DataTable({
	// 		"aLengthMenu" : [ 5, 10, 25, 50, 100 ]
	// 	});
</script>
</html>