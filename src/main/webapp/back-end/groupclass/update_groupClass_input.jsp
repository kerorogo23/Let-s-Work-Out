<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupclass.model.*"%>
<%
GroupClassVO groupClassVO = (GroupClassVO) request.getAttribute("groupClassVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>團體課程資料修改 - update_groupClass_input.jsp</title>
  <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
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

#preview img {
	height: 100px;
	width: auto;
}
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
          	<h1 class="h3 mb-4 text-gray-800">修改團課</h1>
          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
            		<div class="card-body">
            			<div class="table-responsive">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
						
							<FORM METHOD="post" ACTION="groupClass.do" name="form1" enctype="multipart/form-data">
								<table class="table table-bordered" width="100%" cellspacing="0">
									<tr>
										<td>團體課程編號:<font color=red><b>*</b></font></td>
										<td><%=groupClassVO.getGroupClassNo()%></td>
									</tr>
									<tr>
										<td>課程名稱:</td>
										<td><input type="TEXT" name="className" size="45" value="<%=groupClassVO.getClassName()%>" /></td>
									</tr>
									<jsp:useBean id="proSvc" scope="page" class="com.pro.model.ProService" />
									<tr>
										<td>教練:<font color=red><b>*</b></font></td>
										<td><select size="1" name="proId">
												<c:forEach var="proVO" items="${proSvc.all}">
													<option value="${proVO.pro_ID}" ${(groupClassVO.proId==proVO.pro_ID)?'selected':'' }>${groupClassVO.proId}
														<%-- ${proVO.workId == workerVO.wordId}${workerVO.w_name}--%>
												</c:forEach>
										</select></td>
									</tr>
									<jsp:useBean id="classTypeSvc" scope="page" class="com.classtype.model.ClassTypeService"/>
									<tr>
										<td>課程類別:<font color=red><b>*</b></font></td>
										<td><select size="1" name="classTypeNo">
												<c:forEach var="classTypeVO" items="${classTypeSvc.all}">
													<option value="${classTypeVO.classTypeNo}" ${(groupClassVO.classTypeNo==classTypeVO.classTypeNo)?'selected':'' }>${classTypeVO.classTypeName}
												</c:forEach>
										</select></td>
									</tr>
									<tr>
										<td>課程地點:</td>
										<td><input type="TEXT" name="loc" size="45" value="<%=groupClassVO.getLoc()%>" /></td>
									</tr>
									<tr>
										<td>人數上限:</td>
										<td><input type="TEXT" name="groupMax" size="45" value="<%=groupClassVO.getGroupMax()%>" /></td>
									</tr>
									<tr>
										<td>人數下限:</td>
										<td><input type="TEXT" name="groupMin" size="45" value="<%=groupClassVO.getGroupMin()%>" /></td>
									</tr>
									<tr>
										<td>價格:</td>
										<td><input type="TEXT" name="groupClassPrice" size="45" value="<%=groupClassVO.getGroupClassPrice()%>" /></td>
									</tr>
									<tr>
										<td>課程詳情:</td>
										<td><input type="TEXT" name="groupClassDetail" size="45" value="<%=groupClassVO.getGroupClassDetail()%>" /></td>
									</tr>
									<tr>
										<td>團體課程照片:</td>
										<td><div id="preview">
												<img src="<%=request.getContextPath()%>/back-end/groupclass/groupClass.do?id=${groupClassVO.groupClassNo}">
											</div> <input type="file" name="groupClassPic" id="myFile"></td>
									</tr>
						
								</table>
								<br> <input type="hidden" name="action" value="update"> 
								<input type="hidden" name="groupClassNo" value="<%=groupClassVO.getGroupClassNo()%>"> 
								<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
								<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">
								<input type="submit" value="送出修改">
							</FORM>
						</div>
					</div>
			  	</div>
        	</div>
        
		</div>
        <!-- /.container-fluid -->
       
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
  <script src="assets/vendor/jquery/jquery.min.js"></script>
  <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="assets/js/sb-admin-2.min.js"></script>
  
  <!-- Page level plugins -->
  <script src="assets/vendor/datatables/jquery.dataTables.min.js"></script>
  <script src="assets/vendor/datatables/dataTables.bootstrap4.min.js"></script>

  <!-- Page level custom scripts -->
  <script src="assets/js/demo/datatables-demo.js"></script>	
</body>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	// 題目： 請製作可以上傳圖片到前端預覽的功能
	// 學習重點：
	// 1. File API – Read as Data URL

	function init() {

		// 1. 抓取DOM元素
		let myFile = document.getElementById("myFile");
		let preview = document.getElementById('preview');

		myFile.addEventListener('change', function(e) {
			let files = e.target.files;
			if (files !== null) {
				let file = files[0];
				if (file.type.indexOf('image') > -1) {
					let reader = new FileReader();
					reader.addEventListener('load', function(e) {
						let result = e.target.result;
						if ($("#preview img").length > 0) {
							$("#preview img").remove();
							let img = document.createElement('img');
							img.src = result;
							preview.append(img);
						} else {
							let img = document.createElement('img');
							img.src = result;
							preview.append(img);
						}
					});
					reader.readAsDataURL(file);
				} else {
					alert('請上傳圖片！');
				}
			}
		});
	}

	window.onload = init;
</script>


</html>