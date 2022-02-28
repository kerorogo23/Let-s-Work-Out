<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouphour.model.*"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>
<%GroupHourVO groupHourVO = (GroupHourVO) request.getAttribute("groupHourVO");%>
<%
	GroupHourService groupHourSvc = new GroupHourService();
	List<GroupHourVO> list =  groupHourSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="groupClassSvc" scope="page" class="com.groupclass.model.GroupClassService" />

<html>
<head>
<title>所有團課時間資料 - listAllGroupHour.jsp</title>
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
          	<h1 class="h3 mb-4 text-gray-800">團課時間</h1>
          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
					<button type="button" data-toggle="modal" data-target="#add"><a href="addGroupHour.jsp">新增團課時間</a></button>
					<div class="card-body">
                		<div class="table-responsive">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<th>時間編號</th>
									<th>團課名稱</th>
									<th>課程日期</th>
									<th>課程時間</th>
									<th>報名開始時間</th>
									<th>報名結束時間</th>
									<th>課程狀態</th>
									<th>修改</th>
<!-- 									<th>刪除</th> -->
									<th>查詢團課訂單</th>
								</tr>
								<%@ include file="page1.file" %> 
								<c:forEach var="groupHourVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr ${(groupHourVO.groupTimeNo==param.groupTimeNo) ? 'bgcolor=#E8E8E8':''}><!--將修改的那一筆加入對比色而已-->
										<td>${groupHourVO.groupTimeNo}</td>
										<td><c:forEach var="groupClassVO" items="${groupClassSvc.all}">
							                    <c:if test="${groupHourVO.groupClassNo==groupClassVO.groupClassNo}">
								                   ${groupClassVO.className}
							                    </c:if>
							                </c:forEach>
										</td>
										<td>${groupHourVO.groupDate}</td>
										<td>${groupHourVO.groupStartingTime}</td>
										<%--=groupHourVO.getGroupStartingTime()+":00~"+(new Integer(groupHourVO.getGroupStartingTime())+1)+":00"--%>
										<td><fmt:formatDate value="${groupHourVO.registStartTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${groupHourVO.registEndTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											${(groupHourVO.courseStatus=='-1')?'取消':''}
								            ${(groupHourVO.courseStatus==0)?'未開始':''}
								            ${(groupHourVO.courseStatus==1)?'已上課':''}
										</td>			
										
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouphour/groupHour.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改" ${(groupHourVO.courseStatus  ==1)? 'disabled':''} ${(groupHourVO.courseStatus  ==-1)? 'disabled':''}> 
										     <input type="hidden" name="groupTimeNo"value="${groupHourVO.groupTimeNo}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
										     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
										</td>
<!-- 										<td> -->
<%-- 										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouphour/groupHour.do" style="margin-bottom: 0px;"> --%>
<!-- 										     <input type="submit" value="刪除"> -->
<%-- 										     <input type="hidden" name="groupTimeNo"value="${groupHourVO.groupTimeNo}"> --%>
<%-- 										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 										     <input type="hidden" name="action"     value="delete_GroupHour"></FORM> -->
<!-- 										</td> -->
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouphour/groupHour.do" style="margin-bottom: 0px;">
										    <input type="submit" value="送出查詢"> 
										    <input type="hidden" name="groupTimeNo" value="${groupHourVO.groupTimeNo}">
										    <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										    <input type="hidden" name="whichPage"	value="<%=whichPage%>"> 
										    <input type="hidden" name="action" value="listGroupOrders_ByGroupTimeNo_A"></FORM>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			  	</div>						
		<%@ include file="page2.file" %>
		<%if (request.getAttribute("listGroupOrders_ByGroupTimeNo")!=null){%>
			   <jsp:include page="listGroupOrders_ByGroupTimeNo.jsp" />
		<%} %>
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