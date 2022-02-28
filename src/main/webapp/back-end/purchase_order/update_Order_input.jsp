<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.purchase_order.model.*"%>

<%
PurchaseOrderVO purchaseorderVO = (PurchaseOrderVO) request.getAttribute("purchaseorderVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>訂單修改</title>

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
					<h1 class="h3 mb-2 text-gray-800">商城</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">
								<FORM METHOD="post" ACTION="po.do" name="form1">
									<table class="table table-bordered" id="dataTable" width="100%"
										height="100%" cellspacing="0">
										<thead>
											<tr>
												<td>訂單編號:<font color=red><b>*</b></font></td>
												<td><%=purchaseorderVO.getPo_no()%></td>
											</tr>
											<jsp:useBean id="MembersSvc" scope="page"
												class="com.members.model.MembersService" />
											<tr>
												<td>會員編號:</td>
												<td><%=purchaseorderVO.getMem_id()%></td>
											</tr>
											<tr>
												<td>訂單成立時間:</td>
												<td><%=purchaseorderVO.getPo_time()%></td>
											</tr>
											<tr>
												<td>付款方式:</td>
												<td><%=purchaseorderVO.getPo_payment()%></td>
											</tr>
											<tr>
												<td>配送方式:</td>
												<td><%=purchaseorderVO.getPo_delivery()%></td>
											</tr>

											<tr>
												<td>總計:</td>
												<td><%=purchaseorderVO.getPo_total()%></td>
											</tr>
											<tr>
												<td>購買備註:</td>
												<td><%=purchaseorderVO.getPurchase_detail()%></td>
											</tr>
											<tr>
												<td>訂單狀態:</td>
												<td><lable> <input type="radio"
														name="po_status" value="1" checked>處理中</lable> 
													<lable>
													<input type="radio" name="po_status" value="2">商品運送中</lable>
													<lable>
													<input type="radio" name="po_status" value="3">已到貨</lable>
													<lable>
													<input type="radio" name="po_status" value="4">已出貨</lable>
													<lable>
													<input type="radio" name="po_status" value="5">訂單已完成</lable>

												</td>
											</tr>
										</tbody>
									</table>
									<br> <input type="hidden" name="action" value="update">
									<input type="hidden" name="po_no"
										value="<%=purchaseorderVO.getPo_no()%>">
										<input type="hidden" name="mem_id"
										value="<%=purchaseorderVO.getMem_id()%>">
										<input type="hidden" name="po_time"
										value="<%=purchaseorderVO.getPo_time()%>">
										<input type="hidden" name="po_payment"
										value="<%=purchaseorderVO.getPo_payment()%>">
										<input type="hidden" name="po_delivery"
										value="<%=purchaseorderVO.getPo_delivery()%>">
										<input type="hidden" name="po_total"
										value="<%=purchaseorderVO.getPo_total()%>">
										<input type="hidden" name="purchase_detail"
										value="<%=purchaseorderVO.getPurchase_detail()%>"> <input
										type="submit" value="送出修改">
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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<!--  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=purchaseorderVO.getPo_time()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>
-->