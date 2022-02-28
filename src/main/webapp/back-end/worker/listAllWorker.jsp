<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.worker.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
WorkerService workerSvc = new WorkerService();
List<WorkerVO> list = workerSvc.getAll();
pageContext.setAttribute("list", list);
%>

<jsp:useBean id="WorkPowerSvc" scope="page"
	class="com.work_power.model.Work_powerService" />
<jsp:useBean id="PowerListSvc" scope="page"
	class="com.power_list.model.Power_listService" />


<html>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

<!-- Custom fonts for this template -->
<link
	href="<%=request.getContextPath()%>/back-end/worker/assets/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link
	href="<%=request.getContextPath()%>/back-end/worker/assets/css/sb-admin-2.min.css"
	rel="stylesheet">

<!-- Custom styles for this page -->
<link
	href="<%=request.getContextPath()%>/back-end/worker/assets/vendor/datatables/dataTables.bootstrap4.min.css"
	rel="stylesheet">
	
<style>
table.table-bordered.dataTable tbody th, table.table-bordered.dataTable tbody td { border-bottom-width: thin; }
table.table-bordered.dataTable th, table.table-bordered.dataTable td { border-left-width: thin;}
</style>
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
					<h1 class="h3 mb-2 text-gray-800">�Ҧ����u</h1>

					<!-- DataTales Example -->
					<div class="card shadow mb-4">

						<div class="card-body">
							<div class="table-responsive">
								<table class="table table-bordered" id="dataTable" width="98%"
									cellspacing="0">
									
										<tr>
											<th>���u�s��</th>
											<th>���u�m�W</th>
											<th>���u�b��</th>
											<th>���u�K�X</th>
											<th>�H�c</th>
											<th>���U�ɶ�</th>
											<th>���A</th>
											<th>�\�ඵ��</th>
											<th>�ק�</th>
										</tr>
										<%@ include file="page1.file"%>
										<c:forEach var="workerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
											<tr>
												<td>${workerVO.work_id}</td>
												<td>${workerVO.w_name}</td>
												<td>${workerVO.w_acc}</td>
												<td>${workerVO.w_pw}</td>
												<td>${workerVO.email}</td>
												<td>${workerVO.reg_date}</td>
												<td>${workerVO.all_auth}</td>

												<td width="75"><c:forEach var="workpowerVO"
														items="${WorkPowerSvc.all}">
														<c:if test="${workerVO.work_id==workpowerVO.work_id}">
															<c:forEach var="powerlistVO" items="${PowerListSvc.all}">
																<c:if
																	test="${workpowerVO.power_id==powerlistVO.power_id}">
                                	${powerlistVO.power_name}
                    	        </c:if>
															</c:forEach>
														</c:if>
													</c:forEach></td>

												<td>
													<FORM METHOD="post"
														ACTION="<%=request.getContextPath()%>/worker/worker.do"	style="margin-bottom: 0px;">
														<input type="submit" value="�ק�"> <input
															type="hidden" name="work_id" value="${workerVO.work_id}">
														<input type="hidden" name="action"
															value="getOne_For_Update">
													</FORM>
												</td>

											</tr>
										</c:forEach>
								</table>
							</div>
						</div>
						
					</div>
<%@ include file="page2.file" %>
				</div>
				<!-- /.container-fluid -->

			</div>
			<!-- End of Main Content -->

		

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Setting Modal -->
	<div class="modal fade" id="Setting" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">�ק�K�X</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">��</span>
					</button>
				</div>
				<div class="modal-body">
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/worker/worker.do"
						name="form1">
						<table>
							<tr>
								<td>��K�X:</td>
								<td><input type="password" name="w_pw" size=43 /></td>
							</tr>
							<tr>
								<td>�s�K�X:</td>
								<td><input type="password" name="w_npw" id="t_name" size=43 /></td>
							</tr>
						</table>
						<br> <input type="hidden" name="work_id"
							value="${workerVOLogin.work_id}"> <input type="hidden"
							name="action" value="update_pw"> <input type="submit"
							value="�e�X�ק�">
					</FORM>
				</div>
			</div>
		</div>
	</div>

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

	<!-- Page level plugins -->
	<script
		src="<%=request.getContextPath()%>/back-end/worker/assets/vendor/datatables/jquery.dataTables.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/worker/assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

	<!-- Page level custom scripts -->
	<script
		src="<%=request.getContextPath()%>/back-end/worker/assets/js/demo/datatables-demo.js"></script>
	
</body>

</html>
