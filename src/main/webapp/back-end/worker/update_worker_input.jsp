<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="com.worker.model.*"%>
<%@ page import="com.work_power.model.*"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

<title>修改員工 - update_worker_input.jsp</title>

  <!-- Custom fonts for this template-->
  <link href="<%=request.getContextPath() %>/back-end/worker/assets/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

  <!-- Custom styles for this template-->
  <link href="<%=request.getContextPath() %>/back-end/worker/assets/css/sb-admin-2.min.css" rel="stylesheet">

  <style>
    table {
      width: 500px;
      background-color: white;
      margin-top: 1px;
      margin-bottom: 1px;
    }
    th, td {
      padding: 1px;
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
          <h1 class="h3 mb-4 text-gray-800">修改員工資料</h1>

        </div>
        <!-- /.container-fluid -->
        <div class="row justify-content-center">
        	
			
			<FORM METHOD="post" ACTION="<%=request.getContextPath() %>/worker/worker.do" name="form1" >
			<%-- 錯誤表列 --%>
			<c:if test="${not empty errorMsgs}">
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color:red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
						<table style="border: 3px #cccccc solid;" cellpadding="10"	border='0'>
							<tr>
								<td colspan=2><img src="<%=request.getContextPath() %>/back-end/worker/images/gym-logo.jpg" width=100%
									height="100" border="0"></td>
							</tr>

							<tr>
								<td Width="100">員工編號:<font color=red><b>*</b></font></td>
								<td><%=workerVO.getWork_id()%></td>
							</tr>
							<tr>
								<td>員工姓名:</td>
								<td><input type="TEXT" name="w_name" size="30"
									value="<%=workerVO.getW_name()%>" /></td>
							</tr>
							<tr>
								<td>帳號:</td>
								<td><input type="TEXT" name="w_acc" size="30"
									value="<%=workerVO.getW_acc()%>" /></td>
							</tr>
							<tr>
								<td>密碼:</td>
								<td><input type="TEXT" name="w_pw" size="30"
									value="<%=workerVO.getW_pw()%>" /></td>
							</tr>
							<tr>
								<td>信箱:</td>
								<td><input type="TEXT" name="email" size="30"
									value="<%=workerVO.getEmail()%>" /></td>
							</tr>
							<tr>
								<td>權限:<font color=red><b>*</b></font></td>
								<td><input type="TEXT" name="all_auth" size="45"
									value="<%=workerVO.getAll_auth()%>" /></td>
							</tr>
							<tr>
								<td>註冊時間<font color=red><b>*</b></font></td>
								<td><%=workerVO.getReg_date()%></td>
							</tr>

							<tr>
								<td colspan="2">權限:
									<button type="button" id="checkAll">全選</button>
									<button type="button" id="cancel">清除</button>
								</td>
							<tr>
							<tr>
							<tr>
								<td colspan="2"><jsp:useBean id="PowerListSvc" scope="page"
										class="com.power_list.model.Power_listService" /> <c:forEach
										var="powerlistVO" items="${PowerListSvc.all}">
										<input type="checkbox" name="POWER"
											value="${powerlistVO.power_id}"
											${fn:contains(wp, powerlistVO.power_id)? 'checked':''}>${powerlistVO.power_name}
			</c:forEach></td>
							</tr>
						</table>

			<input type="hidden" name="action" value="update">
			<input type="hidden" name="reg_date" value="<%=workerVO.getReg_date()%>">
			<input type="hidden" name="work_id" value="<%=workerVO.getWork_id()%>">
			<input type="submit" value="送出修改">
			<input type="button" value="取消" onclick="location.href='<%=request.getContextPath()%>/back-end/index.jsp'">
			</FORM>
        </div>

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
  <script src="<%=request.getContextPath() %>/back-end/worker/assets/vendor/jquery/jquery.min.js"></script>
  <script src="<%=request.getContextPath() %>/back-end/worker/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Core plugin JavaScript-->
  <script src="<%=request.getContextPath() %>/back-end/worker/assets/vendor/jquery-easing/jquery.easing.min.js"></script>

  <!-- Custom scripts for all pages-->
  <script src="<%=request.getContextPath() %>/back-end/worker/assets/js/sb-admin-2.min.js"></script>

</body>


<script>
  function loadImageFile(event){ 
    var image = document.getElementById('image');
    image.src = URL.createObjectURL(event.target.files[0]); 
    image.height= 200;
    image.width= 200;
  };
  
  $("#magic").click(function(){
	  $("#w_name").val("小王八蛋");
	  $("#w_acc").val("AAAAA");
	  $("#email").val("lyusiang794@gmail.com");
  });
  
  $("#checkAll").click(function(){
  	$("input[name='POWER']").prop("checked",true);//把所有的核取方框的property都變成勾選
  });
  $("#cancel").click(function(){
  	$("input[name='POWER']").prop("checked",false);//把所有的核取方框的property都變成不勾選
  });
</script>

</html>
