<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouporder.model.*"%>
<%@ page import="com.grouphour.model.*"%>
<%@ page import="com.groupclass.model.*"%>
<%@ page import="com.members.model.*"%>

<%
  GroupOrderVO groupOrderVO = (GroupOrderVO) request.getAttribute("groupOrderVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
  groupOrderVO.getGroupTimeNo();
//   System.out.print();
  GroupHourService groupHourSvc = new GroupHourService();
  GroupHourVO groupHourVO = groupHourSvc.getOneGroupHour(groupOrderVO.getGroupTimeNo());
  
  MembersService membersSvc = new MembersService();
  MembersVO membersVO = membersSvc.getOneMembers(groupOrderVO.getMemId());
  
  GroupClassService groupClassSvc = new GroupClassService();
  GroupClassVO groupClassVO = groupClassSvc.getOneGroupClass(groupHourVO.getGroupClassNo());
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>團課訂單資料修改 - update_groupOrder_input.jsp</title>
   <!-- Custom fonts for this template-->
  <link href="assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="assets/css/sb-admin-2.min.css" rel="stylesheet">

  <!-- Custom styles for this page -->
  <link href="assets/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


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
          	<h1 class="h3 mb-4 text-gray-800">修改訂單</h1>
          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
            		<div class="card-body">
                		<div class="table-responsive">
							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color:red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color:red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" name="form1">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<td>訂單編號:<font color=red><b>*</b></font></td>
									<td><%=groupOrderVO.getGroupOrderNo()%></td>
								</tr>
								<tr>
									<td>會員姓名:<font color=red><b>*</b></font></td>
									<td><%=membersVO.getMemName()%></td>
								</tr>
								<tr>
									<td>團課名稱:<font color=red><b>*</b></font></td>
									<td><%=groupClassVO.getClassName()%></td>
								</tr>
								<tr>
									<td>課程日期:<font color=red><b>*</b></font></td>
									<td><%=groupHourVO.getGroupDate()%></td>
								</tr>
								<tr>
									<td>課程時間:<font color=red><b>*</b></font></td>
									<td><%=(groupHourVO.getGroupStartingTime())+":00~"+ (new Integer(groupHourVO.getGroupStartingTime())+1) + ":00" %></td>
								</tr>
								<tr>
									<td>訂單建立時間:<font color=red><b>*</b></font></td>
									<td><%=groupOrderVO.getGroupOrderTime()%></td>
								</tr>
								</tr>
									<td>課程狀態:</td>
									<td>
										<input type="radio" class="courseStatus" name="groupOrderStatus" size="45" value="-1" ${(groupOrderVO.groupOrderStatus==-1)?'checked':'' } />取消
										<input type="radio" class="courseStatus" name="groupOrderStatus" size="45" value="0" ${(groupOrderVO.groupOrderStatus==0)?'checked':'' }/>進行中
										<input type="radio" class="courseStatus" name="groupOrderStatus" size="45" value="1" ${(groupOrderVO.groupOrderStatus==1)?'checked':'' }/>已完成
								</tr>
							</table>
							<br>
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="memId"  value="<%=groupOrderVO.getMemId()%>">
							<input type="hidden" name="groupTimeNo"  value="<%=groupOrderVO.getGroupTimeNo()%>">
							<input type="hidden" name="groupOrderTime"  value="<%=groupOrderVO.getGroupOrderTime()%>">
							<input type="hidden" name="groupOrderNo"  value="<%=groupOrderVO.getGroupOrderNo()%>">
							<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
							<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
							<input type="submit" value="送出修改"></FORM>
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