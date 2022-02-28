<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.members.model.*"%>

<%
MembersVO membersVO = (MembersVO) session.getAttribute("membersVOLogin");
%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>Let's Work Out &mdash; Member</title>
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
				<td align="center"><font size="6" color="green">基本資料&訂單查詢</font><br></td>
			</tr>
		</table>

		<!-- test_2-1     -->

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/front-end/index/members_only3.jsp">
			

			<div class="container rounded bg-white mt-5 mb-5">
				<div class="row">
					<div class="col-md-3 border-right">
						<div
							class="d-flex flex-column align-items-center text-center p-3 py-5">
							<img class="rounded-circle mt-5" width="150px"
								src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span
								class="font-weight-bold">周大雄</span><span class="text-black-50">jjyomeoe@gmail.com</span><span>
							</span>
						</div>
					</div>
					<div class="col-md-5 border-right">
						<div class="p-3 py-5">
							<div
								class="d-flex justify-content-between align-items-center mb-3">
								<h4 class="text-right">Profile Settings</h4>
							</div>
							<div class="row mt-3">
							<div class="col-md-12">
									<label class="labels">Name：</label><td><%=membersVO.getMemName()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Account：</label><td><%=membersVO.getMemAcc()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Birthday：</label><td><%=membersVO.getMemBir()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Sex：</label><td><%=membersVO.getSex()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Address：</label><td><%=membersVO.getMemAddr()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Email：</label><td><%=membersVO.getMemEmail()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Phone：</label><td><%=membersVO.getMemPhone()%></td>
								</div>
								<div class="col-md-12">
									<label class="labels">Resume：</label><td><%=membersVO.getMemResume()%></td>
								</div>
							</div>
							<!-- test_100-1 -->

							

							<div class="mt-5 text-center">
								<input type="hidden" name="action" value="update"> 
								<input class="btn btn-primary profile-button" type="submit" value="修改資料">
							</div>
		</FORM>

		<!-- test_100-2 -->


	</div>
	</div>
	<div class="col-md-4">
		<div class="p-3 py-5">
			<div
				class="d-flex justify-content-between align-items-center experience">
				<span><font size="6">會員-訂單資訊查詢</font><br></span>
			</div>
			<br>


			<div class="mt-5 text-center">
				<button class="btn btn-primary profile-button" type="button" href="<%=request.getContextPath()%>/back-end/po/po.do">商品訂單</button>
			</div>
			<div class="mt-5 text-center">
				<button class="btn btn-primary profile-button" type="button" href="<%=request.getContextPath()%>/order/order.do?orderNo=1">教練課程訂單</button>
			</div>
			<div class="mt-5 text-center">
				<button class="btn btn-primary profile-button" type="button" id="groupOrder">團體課程訂單</button>
			</div>

		</div>
	</div>
	</div>
	</div>
	</div>
	</div>

	<!-- test_2-2     -->

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
								href="<%=request.getContextPath()%>/front-end/index/https://colorlib.com" target="_blank">Colorlib</a>
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

	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../../source/js/mem.js"></script>

</body>

</html>