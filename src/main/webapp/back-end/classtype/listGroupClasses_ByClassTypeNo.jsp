<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.groupclass.model.*"%>

<jsp:useBean id="listGroupClasses_ByClassTypeNo" scope="request" type="java.util.Set<GroupClassVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="classTypeSvc" scope="page" class="com.classtype.model.ClassTypeService" />


<html>
<head>
<title>類別團課 - listGroupClasses_ByClassTypeNo.jsp</title>
  <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 800px;
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
          	<h1 class="h3 mb-4 text-gray-800">類別-團課</h1>
          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
					<div class="card-body">
                		<div class="table-responsive">            	
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<th>團體課程編號</th>
									<th>教練姓名</th>
									<th>課程類別</th>
									<th>課程名稱</th>
									<th>課程地點</th>
									<th>人數上限</th>
									<th>人數下限</th>
									<th>價格</th>
									<th>課程詳情</th>
									<th>課程照片</th>
									<th>修改</th>
							<!-- 		<th>刪除</th> -->
								</tr>
								<c:forEach var="groupClassVO" items="${listGroupClasses_ByClassTypeNo}" >
									<tr ${(groupClassVO.groupClassNo==param.groupClassNo) ? 'bgcolor=#E8E8E8':''}><!--將修改的那一筆加入對比色-->
										<td>${groupClassVO.groupClassNo}</td>
										<td>${groupClassVO.proId}</td>
										<td>
											<c:forEach var="classTypeVO" items="${classTypeSvc.all}">
							                    <c:if test="${groupClassVO.classTypeNo==classTypeVO.classTypeNo}">
								                    ${classTypeVO.classTypeName}
							                    </c:if>
							                </c:forEach>
										</td>
										<td>${groupClassVO.className}</td>
										<td>${groupClassVO.loc}</td>
										<td>${groupClassVO.groupMax}</td>
										<td>${groupClassVO.groupMin}</td>
										<td>${groupClassVO.groupClassPrice}</td>
										<td>${groupClassVO.groupClassDetail}</td>
										<td>
										  <img src="<%=request.getContextPath()%>/back-end/groupclass/groupClass.do?id=${groupClassVO.groupClassNo}" width="100px" height="70px">
										</td>	
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/groupclass/groupClass.do" style="margin-bottom: 0px;">
										    <input type="submit" value="修改"> 
										    <input type="hidden" name="groupClassNo"      value="${groupClassVO.groupClassNo}">
										    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
										    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
										</td>
							<!-- 			<td> -->
							<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/groupclass/groupClass.do" style="margin-bottom: 0px;"> --%>
							<!-- 			    <input type="submit" value="刪除"> -->
							<%-- 			    <input type="hidden" name="groupClassNo"      value="${groupClassVO.groupClassNo}"> --%>
							<%-- 			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
							<!-- 			    <input type="hidden" name="action"     value="delete_ClassType"></FORM> -->
							<!-- 			</td> -->
										
									</tr>
								</c:forEach>
							</table>
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
</html>