<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<%
MembersVO membersVO = (MembersVO) session.getAttribute("membersVOLogin");
%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>Let's Work Out &mdash; Member Edit Profile</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<link
	href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900"
	rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/fonts/icomoon/style.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/jquery-ui.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/owl.carousel.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/owl.theme.default.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/owl.theme.default.min.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/jquery.fancybox.min.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/bootstrap-datepicker.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/aos.css">
<link href="<%=request.getContextPath()%>/front-end/index/css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet"
	type="text/css">

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/index/css/style.css">



</head>

<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">



	<div class="site-wrap">

		<div class="site-mobile-menu site-navbar-target">
			<div class="site-mobile-menu-header">
				<div class="site-mobile-menu-close mt-3">
					<span class="icon-close2 js-menu-toggle"></span>
				</div>
			</div>
			<div class="site-mobile-menu-body"></div>
		</div>


		<header class="site-navbar py-4 js-sticky-header site-navbar-target"
			role="banner">

			<div class="container-fluid">
				<div class="d-flex align-items-center">
					<div class="site-logo">
						<a href="<%=request.getContextPath()%>/front-end/index/index.jsp">Let's Work Out</a>
					</div>
					<div class="ml-auto">
						<nav class="site-navigation position-relative text-right"
							role="navigation">
							<ul
								class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section" class="nav-link">Home</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/members_only2.jsp#member-section"class="nav-link active">Member</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#classes-section">Classes</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/pro.do?action=listAllPros" class="nav-link">Coach</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/product/shop.jsp" class="nav-link">Shop</a></li>
							</ul>
						</nav>
					</div>
					<div class="ml-auto">
						<nav class="site-navigation position-relative text-right"
							role="navigation">
							<ul
								class="site-menu main-menu site-menu-dark js-clone-nav mr-auto d-none d-lg-block">
								<li class="cta"><a href="index.jsp#contact-section"
									class="nav-link"><span
										class="rounded border border-primary">Contact</span></a></li>
							</ul>
						</nav>
						<a href="#"
							class="d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black float-right"><span
							class="icon-menu h3"></span></a>
					</div>
				</div>
			</div>

		</header>


		<table width="400" height="180">

			<tr>
				<td align="center"><font size="6" color="green">會員資料修改</font><br></td>
			</tr>
		</table>


		<!-- 會員修改資料內容     -->

		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/members/members.do">

			<div class="container rounded bg-white mt-5 mb-5">
				<div class="row">
					<div class="col-md-3 border-right">
						<div
							class="d-flex flex-column align-items-center text-center p-3 py-5">
							<img class="rounded-circle mt-5" width="150px"
								src="<%=request.getContextPath()%>/front-end/index/images/2f815e.jpg"><span class="font-weight-bold"></span><span
								class="text-black-50">「想著要贏得比賽不會給你力量，在鍛鍊中掙扎力量才會增長，當你克服困難不想放棄時，這就是力量。」——阿諾德·施瓦辛格</span><span> </span>
						</div>
					</div>
					<div class="col-md-5 border-right">
						<div class="p-3 py-5">
							<div
								class="d-flex justify-content-between align-items-center mb-3">
								<h4 class="text-right">Profile Settings</h4>
							</div>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

							<div class="row mt-3">
								<tr>
									<td><input type="hidden" name="memId" value="<%=membersVO.getMemId()%>" /></td>
								</tr>
								<tr>
									<td>Name：</td>
									<td><input type="TEXT" name="memName" size="45"
										value="<%=membersVO.getMemName()%>" /></td>
								</tr>
								<tr>
									<td>Birthday：</td>
									<td><input name="memBir" id="f_date1" type="text"
										size="45" value="<%=membersVO.getMemBir()%>"></td>
								</tr>
								<tr>
									<td>Sex：</td>
									<td><INPUT TYPE="radio" NAME="sex" VALUE="男"
										<%=((membersVO.getSex()).equals("男")) ? "checked" : ""%>>男
										<INPUT TYPE="radio" NAME="sex" VALUE="女"
										<%=((membersVO.getSex()).equals("女")) ? "checked" : ""%>>女
										<p></td>
								</tr>
								<br>
								<tr>
									<td>Address：</td>
									<td><input type="TEXT" size="45" name="memAddr"
										value="<%=membersVO.getMemAddr()%>" /></td>
								</tr>
								<br>
								<tr>
									<td>Email：</td>
									<td><input type="TEXT" size="45" name="memEmail"
										value="<%=membersVO.getMemEmail()%>" /></td>
								</tr>
								<br>
								<tr>
									<td>Phone：</td>
									<td><input type="TEXT" size="45" name="memPhone"
										value="<%=membersVO.getMemPhone()%>" /></td>
								</tr>
								<tr>
									<br>
									<td>Resume：</td>
									<td><textarea id="memResume" name="memResume" rows="4"
											cols="46"><%=membersVO.getMemResume()%></textarea></td>
								</tr>
								<tr>
					
<!-- 		-------------------------------------------------隱藏_從membersVO取出值_但不要顯示的資料------------------------------------------------						 -->				
							
								<tr>
									<td><input type="hidden" name="memRegDate" value="<%=membersVO.getMemRegDate()%>" /></td>
								</tr>
								<tr>
									<td><input type="hidden" name="allAuth" value="<%=membersVO.getAllAuth()%>" /></td>
								</tr>
								<tr>
									<td><input type="hidden" name="artAuth" value="<%=membersVO.getArtAuth()%>" /></td>
								</tr>
								<tr>
									<td><input type="hidden" name="comAuth" value="<%=membersVO.getComAuth()%>" /></td>
								</tr>
							</div>

							

						</div>
					</div>


					<!-- 				            ↓↓↓↓     會員修改密碼     ↓↓↓↓  -->
					
					<div class="col-md-4">
						<div class="p-3 py-5">
						<div>-------修改會員密碼-------</div>
							<label for="password" class="cols-sm-2 control-label">新密碼：</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" name="memPsw"
									id="memPsw" value="<%= (membersVO==null)? "" : membersVO.getMemPsw()%>" placeholder="Enter Password" />
							</div>
							
							<br>
							
							<label for="password" class="cols-sm-2 control-label">請再次輸入新密碼：</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
									type="password" name="memPsw"
									id="memPsw" value="<%= (membersVO==null)? "" : membersVO.getMemPsw()%>" placeholder="Enter Password" />
							</div>
							
							<div
								class="d-flex justify-content-between align-items-center experience">

							</div>
							<div class="mt-5 text-center">
								<input type="hidden" name="action" value="update"> 
								<input type="hidden" name="memAcc" value="<%=membersVO.getMemAcc()%>"> 
								<input class="btn btn-primary profile-button" type="submit" value="儲存修改資料">
							</div>
							<br>

						</div>
					</div>

					<!-- 				            ↑↑↑↑     會員修改密碼    ↑↑↑↑  -->

				</div>
			</div>
	</div>
	</div>
	
	</FORM>
	
	<!-- 會員修改資料內容     -->




	<footer class="footer-section">
		<div class="container">
			<div class="row">
				<div class="col-md-4">
					<h3>About Let's Work Out</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Porro consectetur ut hic ipsum et veritatis corrupti. Itaque eius
						soluta optio dolorum temporibus in, atque, quos fugit sunt sit
						quaerat dicta.</p>
				</div>

				<div class="col-md-3 ml-auto">
					<h3>Links</h3>
					<ul class="list-unstyled footer-links">
						<li><a href="<%=request.getContextPath()%>/front-end/index/#">Home</a></li>
						<li><a href="#">Meditation</a></li>
						<li><a href="#">Gym</a></li>
						<li><a href="#">Aerobatics</a></li>
					</ul>
				</div>

				<div class="col-md-4">
					<h3>Subscribe</h3>
					<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit.
						Nesciunt incidunt iure iusto architecto? Numquam, natus?</p>
					<form action="#">
						<div class="d-flex mb-5">
							<input type="text" class="form-control rounded-0"
								placeholder="Email"> <input type="submit"
								class="btn btn-primary rounded-0" value="Subscribe">
						</div>
					</form>
				</div>

			</div>

			<div class="row pt-5 mt-5 text-center">
				<div class="col-md-12">
					<div class=" pt-5">
						<p>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="icon-heart" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
				</div>

			</div>
		</div>
	</footer>

	</div>
	<!-- .site-wrap -->

	<script src="js/jquery-3.3.1.min.js"></script>
	<script src="js/jquery-migrate-3.0.1.min.js"></script>
	<script src="js/jquery-ui.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/owl.carousel.min.js"></script>
	<script src="js/jquery.stellar.min.js"></script>
	<script src="js/jquery.countdown.min.js"></script>
	<script src="js/bootstrap-datepicker.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script>
	<script src="js/aos.js"></script>
	<script src="js/jquery.fancybox.min.js"></script>
	<script src="js/jquery.sticky.js"></script>
	<script src="js/jquery.mb.YTPlayer.min.js"></script>




	<script src="js/main.js"></script>

</body>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=membersVO.getMemBir()%>
	', // value:   new Date(),
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