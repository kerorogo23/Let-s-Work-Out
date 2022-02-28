<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.grouporder.model.*"%>

<% if (request.getAttribute("listGroupHours_ByGroupClassNo")!=null){%>
       <jsp:useBean id="listGroupOrders_ByGroupOrderStatus" scope="request" type="java.util.Set<GroupOrderVO>" /> <!-- 於EL此行可省略 -->
<% }%>
<jsp:useBean id="groupHourSvc" scope="page" class="com.grouphour.model.GroupHourService" />
<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MembersService" />


<html>
<head>
<title>訂單狀態的團課訂單 - listGroupOrders_ByGroupOrderStatus.jsp</title>
  <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


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
          	<h1 class="h3 mb-4 text-gray-800">團課訂單</h1>
          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
					<div class="card-body">
                		<div class="table-responsive">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<th>訂單編號</th>
									<th>會員姓名</th>
									<th>課程日期</th>
									<th>課程時間</th>
									<th>訂單建立時間</th>
									<th>訂單狀態</th>
									<th>修改</th>
							<!-- 		<th>刪除</th> -->
								</tr>
								
								<c:forEach var="groupOrderVO" items="${listGroupOrders_ByGroupOrderStatus}" >
									<tr ${(groupOrderVO.groupTimeNo==param.groupTimeNo) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色-->
										<td>${groupOrderVO.groupOrderNo}</td>
										<td><c:forEach var="membersVO" items="${membersSvc.all}">
							                    <c:if test="${groupOrderVO.memId==membersVO.memId}">
								                    ${membersVO.memName}
							                    </c:if>
							                </c:forEach>
										</td>
										<td><c:forEach var="groupHourVO" items="${groupHourSvc.all}">
							                    <c:if test="${groupOrderVO.groupTimeNo==groupHourVO.groupTimeNo}">
								                    ${groupHourVO.groupDate}
							                    </c:if>
							                </c:forEach>
										</td>
										<td><c:forEach var="groupHourVO" items="${groupHourSvc.all}">
							                    <c:if test="${groupOrderVO.groupTimeNo==groupHourVO.groupTimeNo}">
								                   ${groupHourVO.groupStartingTime}
							                    </c:if>
							                </c:forEach>
										</td>
										<td><fmt:formatDate value="${groupOrderVO.groupOrderTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${(groupOrderVO.groupOrderStatus=='-2')?'會員申請取消':''}
											${(groupOrderVO.groupOrderStatus=='-1')?'取消':''}
								            ${(groupOrderVO.groupOrderStatus==0)?'進行中':''}
								            ${(groupOrderVO.groupOrderStatus==1)?'已完成':''}
								        </td>			
										
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" style="margin-bottom: 0px;">
										    <input type="submit" value="修改" ${(groupOrderVO.groupOrderStatus==1)? 'disabled':''}${(groupOrderVO.groupOrderStatus==-1)? 'disabled':''}> 
										     <input type="hidden" name="action" value="update">
										     <input type="hidden" name="groupOrderStatus"  value="${(groupOrderVO.groupOrderStatus=='-2')? -1:0}">
											 <input type="hidden" name="memId"  value="${groupOrderVO.memId}">
								 			 <input type="hidden" name="groupTimeNo"  value="${groupOrderVO.groupTimeNo}">
											 <input type="hidden" name="groupOrderTime"  value="${groupOrderVO.groupOrderTime}">
										     <input type="hidden" name="groupOrderNo"      value="${groupOrderVO.groupOrderNo}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->            <!--送出當前是第幾頁給Controller-->
										     <input type="hidden" name="action"	    value="update"></FORM>
										</td>
							<!-- 			<td> -->
							<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" style="margin-bottom: 0px;"> --%>
							<!-- 			    <input type="submit" value="刪除"> -->
							<%-- 			    <input type="hidden" name="groupOrderNo"      value="${groupOrderVO.groupOrderNo}"> --%>
							<%-- 			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
							<!-- 			    <input type="hidden" name="action"     value="delete"></FORM> -->
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