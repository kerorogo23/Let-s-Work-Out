<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.grouphour.model.*"%>

<%
  GroupHourVO groupHourVO = (GroupHourVO) request.getAttribute("groupHourVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>團課時間資料修改 - update_groupHour_input.jsp</title>
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
          	<h1 class="h3 mb-4 text-gray-800">修改團課時間</h1>
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
							
							<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/grouphour/groupHour.do" name="form1">
							<table class="table table-bordered" width="100%" cellspacing="0">
								<tr>
									<td>時間編號:<font color=red><b>*</b></font></td>
									<td><%=groupHourVO.getGroupTimeNo()%></td>
								</tr>
								<jsp:useBean id="groupClassSvc" scope="page" class="com.groupclass.model.GroupClassService" />
								<tr>
									<td>團課名稱:<font color=red><b>*</b></font></td>
									<td><select size="1" name="groupClassNo">
										<c:forEach var="groupClassVO" items="${groupClassSvc.all}">
											<option value="${groupClassVO.groupClassNo}" ${(groupHourVO.groupClassNo==groupClassVO.groupClassNo)?'selected':'' } >${groupClassVO.className}
										</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td>課程日期:</td>
									<td><input name="groupDate" id="f_date1" type="text" "/></td>
								</tr>
								<tr>
									<td>課程時間:</td>
									 <td><select size="1" id="groupStartingTime" name="groupStartingTime">
							                    <option value="8" ${(groupHourVO.groupStartingTime==8)?'selected':'' }>08:00~09:00
							                    <option value="9" ${(groupHourVO.groupStartingTime==9)?'selected':'' }>09:00~10:00
							                    <option value="10" ${(groupHourVO.groupStartingTime==10)?'selected':'' }>10:00~11:00
							                    <option value="11" ${(groupHourVO.groupStartingTime==11)?'selected':'' }>11:00~12:00
							                    <option value="12" ${(groupHourVO.groupStartingTime==12)?'selected':'' }>12:00~13:00
							                    <option value="13" ${(groupHourVO.groupStartingTime==13)?'selected':'' }>13:00~14:00
							                    <option value="14" ${(groupHourVO.groupStartingTime==14)?'selected':'' }>14:00~15:00
							                    <option value="15" ${(groupHourVO.groupStartingTime==15)?'selected':'' }>15:00~16:00
							                    <option value="16" ${(groupHourVO.groupStartingTime==16)?'selected':'' }>16:00~17:00
							                    <option value="17" ${(groupHourVO.groupStartingTime==17)?'selected':'' }>17:00~18:00
							                    <option value="18" ${(groupHourVO.groupStartingTime==18)?'selected':'' }>18:00~19:00
							                    <option value="19" ${(groupHourVO.groupStartingTime==19)?'selected':'' }>19:00~20:00
							                    <option value="20" ${(groupHourVO.groupStartingTime==20)?'selected':'' }>20:00~21:00
							                    <option value="21" ${(groupHourVO.groupStartingTime==21)?'selected':'' }>21:00~22:00
							                    <option value="22" ${(groupHourVO.groupStartingTime==22)?'selected':'' }>22:00~23:00
							                    <option value="23" ${(groupHourVO.groupStartingTime==23)?'selected':'' }>23:00~00:00
							              </select>
									</td>
								</tr>
								<tr>
									<td>報名開始時間:</td>
									<td><input name="registStartTime" id="f_date2" type="text" "/></td>
								</tr>
								<tr>
									<td>報名結束時間:</td>
									<td><input name="registEndTime" id="f_date3" type="text" "/></td>
								</tr>
									<td>課程狀態:</td>
									<td><input type="radio" class="courseStatus" name="courseStatus" size="45" value="-1" ${(groupHourVO.courseStatus==-1)?'checked':'' } />取消
										<input type="radio" class="courseStatus" name="courseStatus" size="45" value="0" ${(groupHourVO.courseStatus==0)?'checked':'' }/>未開始
										<input type="radio" class="courseStatus" name="courseStatus" size="45" value="1" ${(groupHourVO.courseStatus==1)?'checked':'' }/>已上課
								</tr>
							
							</table>
							<br>
							<input type="hidden" name="action" value="update">
							<input type="hidden" name="groupTimeNo"  value="<%=groupHourVO.getGroupTimeNo()%>">
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



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/grouphour/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/grouphour/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/grouphour/datetimepicker/jquery.datetimepicker.full.js"></script>

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
  		   value: '<%=groupHourVO.getGroupDate()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=groupHourVO.getRegistStartTime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date3').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:true,       //timepicker:true,
  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
  		   value: '<%=groupHourVO.getRegistEndTime()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        

        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
              var somedate1 = new Date();
              $('#f_date1').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                      ) {
                          return [false, ""]
                      }
                      return [true, ""];
              }});
              var somedate1 = new Date();
              $('#f_date2').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                      ) {
                          return [false, ""]
                      }
                      return [true, ""];
              }});
              var somedate1 = new Date();
              $('#f_date3').datetimepicker({
                  beforeShowDay: function(date) {
                	  if (  date.getYear() <  somedate1.getYear() || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
                      ) {
                          return [false, ""]
                      }
                      return [true, ""];
              }});

        
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