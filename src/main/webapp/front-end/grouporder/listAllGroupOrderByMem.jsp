<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.members.model.*"%>

<%
MembersVO membersVO = (MembersVO) request.getAttribute("membersVO"); //MembersServlet.java(Concroller), 存入req的membersVO物件
%>


<!DOCTYPE html>
<html lang="en">

<head>
<title>Let's Work Out &mdash; All Group Order By Member</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<link href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900" rel="stylesheet">
<link rel="stylesheet" href="../../source/fonts/icomoon/style.css">

<link rel="stylesheet" href="../../source/css/bootstrap.min.css">
<link rel="stylesheet" href="../../source/css/jquery-ui.css">
<link rel="stylesheet" href="../../source/css/owl.carousel.min.css">
<link rel="stylesheet" href="../../source/css/owl.theme.default.min.css">
<link rel="stylesheet" href="../../source/css/owl.theme.default.min.css">

<link rel="stylesheet" href="../../source/css/jquery.fancybox.min.css">

<link rel="stylesheet" href="../../source/css/bootstrap-datepicker.css">

<link rel="stylesheet" href="../../source/fonts/flaticon/font/flaticon.css">

<link rel="stylesheet" href="../../source/css/aos.css">
<link href="../../source/css/jquery.mb.YTPlayer.min.css" media="all" rel="stylesheet"
	type="text/css">

<link rel="stylesheet" href="../../source/css/style.css">

<style>
  #content#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  #content#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
</style>

<style>
  #content {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	font-size: 10px
  }
/*    #content, th, td {  */
/*      border: 1px solid #CCCCFF;  */
/*    }  */
   th, td { 
     padding: 5px; 
     text-align: center; 
   } 
   button{
   	cursor:pointer;
   }
</style>
</head>

<body data-spy="scroll" data-target=".site-navbar-target" data-offset="300">

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
						<a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section">Let's Work Out</a>
					</div>
					<div class="ml-auto">
						<nav class="site-navigation position-relative text-right"
							role="navigation">
							<ul
								class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section" class="nav-link">Home</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/members_only2.jsp#member-section" class="nav-link active">Member</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#classes-section" class="nav-link">Classes</a></li>
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

<!-- test_1-1     -->

<table width="400" height="180">

　<tr><td align="center"><font size="6" color="green">基本資料修改&訂單查詢</font><br></td></tr>
</table>

<!-- test_1-2     -->


<!-- test_2-1     -->

<div class="container rounded bg-white mt-5 mb-5">
    <div class="row">
        <div class="col-md-3 border-right">
            <div class="d-flex flex-column align-items-center text-center p-3 py-5"><img class="rounded-circle mt-5" width="150px" src="https://st3.depositphotos.com/15648834/17930/v/600/depositphotos_179308454-stock-illustration-unknown-person-silhouette-glasses-profile.jpg"><span class="font-weight-bold">周大雄</span><span class="text-black-50">jjyomeoe@gmail.com</span><span> </span></div>
        </div>
        <div class="col-md-5 border-right">
            <div class="p-3 py-5">
                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h4 class="text-right">團體課程訂單</h4>
                </div>
                <div class="row mt-2">
                   
                </div>
                <div class="row mt-3">
                    	<table id="content">
					        <tr>
					            <th>團課名稱</th>
					            <th>課程日期</th>
					            <th>課程時間</th>
					            <th>訂單建立時間</th>
					            <th>課程狀態</th>
					            <th>訂單狀態</th>
					            <th>取消訂單</th>
					        </tr>
					    </table>
                </div>
                <div class="row mt-3">
                    <div class="col-md-6"></div>
                    <div class="col-md-6"></div>
                </div>
              
            </div>
        </div>
	        	<div class="col-md-4">
		            <div class="p-3 py-5">
		                <div class="d-flex justify-content-between align-items-center experience"><span><font size="6">會員-訂單資訊查詢</font><br></span></div><br>
		                         
<!-- test_3-1           -->
<div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">商品訂單</button></div>
<div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button">教練課程訂單</button></div>
<div class="mt-5 text-center"><button class="btn btn-primary profile-button" type="button" id="groupOrder">團體課程訂單</button></div>
<!-- test_3-2            -->
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
							<li><a href="#">Home</a></li>
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

	<script src="../../source/js/jquery-3.3.1.min.js"></script>
	<script src="../../source/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="../../source/js/jquery-ui.js"></script>
	<script src="../../source/js/popper.min.js"></script>
	<script src="../../source/js/bootstrap.min.js"></script>
	<script src="../../source/js/owl.carousel.min.js"></script>
	<script src="../../source/js/jquery.stellar.min.js"></script>
	<script src="../../source/js/jquery.countdown.min.js"></script>
	<script src="../../source/js/bootstrap-datepicker.min.js"></script>
	<script src="../../source/js/jquery.easing.1.3.js"></script>
	<script src="../../source/js/aos.js"></script>
	<script src="../../source/js/jquery.fancybox.min.js"></script>
	<script src="../../source/js/jquery.sticky.js"></script>
	<script src="../../source/js/jquery.mb.YTPlayer.min.js"></script>
	<script src="../../source/js/listAllGroupOrderByMem.js"></script>




	<script src="../../source/js/main.js"></script>

</body>

</html>