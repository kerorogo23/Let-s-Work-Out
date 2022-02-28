<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.grouporder.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	GroupOrderService groupOrderSvc = new GroupOrderService();
	List<GroupOrderVO> list = groupOrderSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<jsp:useBean id="groupHourSvc" scope="page" class="com.grouphour.model.GroupHourService" />
<jsp:useBean id="groupClassSvc" scope="page" class="com.groupclass.model.GroupClassService" />
<jsp:useBean id="membersSvc" scope="page" class="com.members.model.MembersService" />
<html>
<head>
<title>所有團課訂單資料 - listAllGroupOrder.jsp</title>
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
          	<h1 class="h3 mb-4 text-gray-800">團課訂單</h1>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" >
				     <b>選擇訂單狀態:</b>
				     <select size="1" name="groupOrderStatus">
				       <option value="-1">取消
				       <option value="0">進行中
				        <option value="1">已完成
				     </select>
				     <input type="hidden" name="action" value="listGroupOrders_ByGroupOrderStatus_A">
				     <input type="submit" value="送出">
				  </FORM>

          		<!-- DataTales Example -->
            	<div class="card shadow mb-4">
					<div class="card-body">
                		<div class="table-responsive">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<th>團課訂單編號</th>
									<th>會員姓名</th>
									<th>團課編號</th>
									<th>課程日期</th>
									<th>課程時間</th>
									<th>訂單建立時間</th>
									<th>訂單狀態</th>
									<th>修改</th>
<!-- 									<th>刪除</th> -->
								</tr>
								<%@ include file="page1.file" %> 
								<c:forEach var="groupOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
									<tr ${(groupOrderVO.groupOrderNo==param.groupOrderNo) ? 'bgcolor=#E8E8E8':''}><!--將修改的那一筆加入對比色而已-->
										<td>${groupOrderVO.groupOrderNo}</td>
										<td><c:forEach var="membersVO" items="${membersSvc.all}">
							                    <c:if test="${groupOrderVO.memId==membersVO.memId}">
								                    ${membersVO.memName}
							                    </c:if>
							                </c:forEach>
										</td>
										<td><c:forEach var="groupHourVO" items="${groupHourSvc.all}">
							                    <c:if test="${groupOrderVO.groupTimeNo==groupHourVO.groupTimeNo}">
							                 		${groupHourVO.groupClassNo}
							                    </c:if>
											 </c:forEach>
							                <c:forEach var="groupClassVO" items="${groupClassSvc.all}">
							                	<c:if test="${groupHourVO.groupClassNo==groupClassVO.groupClassNo}">
							                 		${groupClassVO.className}
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
										<td>
											${(groupOrderVO.groupOrderStatus=='-1')?'取消':''}
								            ${(groupOrderVO.groupOrderStatus==0)?'進行中':''}
								            ${(groupOrderVO.groupOrderStatus==1)?'已完成':''}</td>			
										
										<td>
										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" style="margin-bottom: 0px;">
										     <input type="submit" value="修改" ${(groupOrderVO.groupOrderStatus==1)? 'disabled':''}${(groupOrderVO.groupOrderStatus==-1)? 'disabled':''}> 
										     <input type="hidden" name="groupOrderStatus"  value="${groupOrderVO.groupOrderStatus}">
											 <input type="hidden" name="memId"  value="${groupOrderVO.memId}">
								 			 <input type="hidden" name="groupTimeNo"  value="${groupOrderVO.groupTimeNo}">
											 <input type="hidden" name="groupOrderTime"  value="${groupOrderVO.groupOrderTime}">
										     <input type="hidden" name="groupOrderNo"      value="${groupOrderVO.groupOrderNo}">
										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
										     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
										</td>
<!-- 										<td> -->
<%-- 										  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouporder/groupOrder.do" style="margin-bottom: 0px;"> --%>
<!-- 										     <input type="submit" value="刪除"> -->
<%-- 										     <input type="hidden" name="groupOrderNo"      value="${groupOrderVO.groupOrderNo}"> --%>
<%-- 										     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--> --%>
<%-- 										     <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller--> --%>
<!-- 										     <input type="hidden" name="action"     value="delete"></FORM> -->
<!-- 										</td> -->
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			  	</div>
		<%@ include file="page2.file" %>
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
  	<!-- Setting Modal -->
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
								<td><input type="password" name="w_pw" size=43 /></td>
							</tr>
							<tr>
								<td>新密碼:</td>
								<td><input type="password" name="w_npw" id="t_name" size=43 /></td>
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