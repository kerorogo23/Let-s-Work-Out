<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.purchase_order_details.model.*"%>
<%@ page import="java.util.*"%>

<%
PurchaseOrderVO purchaseorderVO = (PurchaseOrderVO) request.getAttribute("purchaseorderVO"); //EmpServlet.java(Concroller), 存入req的empVO物件

PurchaseOrderDetailsService purchaseorderdetailsSvc = new PurchaseOrderDetailsService();

List<PurchaseOrderDetailsVO> list = purchaseorderdetailsSvc.getPODByPN(purchaseorderVO.getPo_no());
request.setAttribute("list", list);


// PurchaseOrderDetailsVO purchaseorderdetailsVO = purchaseorderdetailsSvc.getOnePOD(purchaseorderVO.getPo_no());

Map <Integer , String>map = new HashMap<Integer , String>();
map.put(1,"處理中");
map.put(2,"商品運送中");
map.put(3,"已到貨");
map.put(4,"已出貨");
map.put(5,"訂單已完成");

pageContext.setAttribute("map",map);
%>

<html>
<head>
<meta charset="UTF-8">




<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>訂單查詢</title>

<!-- Custom fonts for this template -->
<link
	href="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="<%=request.getContextPath()%>/back-end/purchase_order/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/datatables/dataTables.bootstrap4.min.css"
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
					<h1 class="h3 mb-2 text-gray-800">訂單查詢</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">



								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>編號</th>
											<th>會員編號</th>
											<th>時間</th>
											<th>付款方式</th>
											<th>配送方式</th>
											<th>總計</th>
											<th>購買備註</th>
											<th>訂單狀態</th>
											<th>訂單明細</th>
											

										</tr>

									</thead>

									<tbody>

										<tr>
											<td width="50px"><%=purchaseorderVO.getPo_no()%></td>

											<td width="90px"><%=purchaseorderVO.getMem_id()%></td>
											<td width="80px"><%=purchaseorderVO.getPo_time()%></td>

											<td width="80px"><%=purchaseorderVO.getPo_payment()%></td>
											<td><%=purchaseorderVO.getPo_delivery()%></td>

											<td><%=purchaseorderVO.getPo_total()%>元</td>

											<td><%=purchaseorderVO.getPurchase_detail()%></td>
											<td>${map[purchaseorderVO.po_status]}</td>
											<%-- 												<jsp:useBean id="purchaseorderdetailsSvc" scope="page" class="com.purchase_order_details.model.PurchaseOrderDetailsService" /> --%>
											<td>
												<c:forEach var="podVO" items="${list}">
													
 														商品編號:${podVO.product_no} 數量: ${podVO.quantity}總計${podVO.p_price}
														<br>
												</c:forEach>
											</td>


<!-- 											<td> -->
<!-- 												<FORM METHOD="post" -->
<%-- 													ACTION="<%=request.getContextPath()%>/back-end/po/po.do" --%>
<!-- 													style="margin-bottom: 0px;"> -->
<!-- 													<input type="submit" value="刪除">  -->
<%-- 													<input type="hidden" name="po_no" value="${purchaseorderVO.po_no}">  --%>
<!-- 													<input -->
<!-- 														type="hidden" name="requestURL" -->
<%-- 														value="<%=request.getContextPath()%>/back-end/purchase_order/listAllOrder.jsp"> <input --%>
<!-- 														type="hidden" name="action" value="delete"> -->
<!-- 												</FORM> -->
<!-- 											</td> -->
										</tr>

									</tbody>
								</table>
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
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/js/sb-admin-2.min.js"></script>

	<!-- Page level plugins -->
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/js/demo/datatables-demo.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/purchase_order/assets/js/wedyListAllProduct.js"></script>


</body>
<script>
	// 	$("#dataTable").DataTable({
	// 		"aLengthMenu" : [ 5, 10, 25, 50, 100 ]
	// 	});
</script>
</html>