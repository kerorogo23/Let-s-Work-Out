<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%
Integer totalAmount = 0;

Map<Integer, Integer> shoppingList = (Map<Integer, Integer>) session.getAttribute("shoppingList");

ProductService ProductSvc = new ProductService();

for (Map.Entry<Integer, Integer> entry : shoppingList.entrySet()) {
	totalAmount += ProductSvc.getOneProduct(entry.getKey()).getP_price() * entry.getValue();
	
}
%>






<html>

<head>
<title>Let's Work Out &mdash; ShoppingCar</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/css/shoppingCar.css" />
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link
	href="https://fonts.googleapis.com/css?family=Muli:300,400,700,900"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/fonts/icomoon/style.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/jquery-ui.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/owl.carousel.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/owl.theme.default.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/owl.theme.default.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/jquery.fancybox.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/bootstrap-datepicker.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/fonts/flaticon/font/flaticon.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/aos.css">
<link
	href="<%=request.getContextPath()%>/source/css/jquery.mb.YTPlayer.min.css"
	media="all" rel="stylesheet" type="text/css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/source/css/style.css">




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
						<a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section">Let's Work Out</a>
					</div>
					<div class="ml-auto">
						<nav class="site-navigation position-relative text-right"
							role="navigation">
							<ul
								class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section" class="nav-link">Home</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/members_only2.jsp#member-section"class="nav-link">Member</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#classes-section">Classes</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/pro.do?action=listAllPros" class="nav-link">Coach</a></li>					
								<li><a href="<%=request.getContextPath()%>/front-end/product/shop.jsp" class="nav-link active">Shop</a></li>
							</ul>
						</nav>
					</div>
					<div class="ml-auto">
						<nav class="site-navigation position-relative text-right"
							role="navigation">
							<ul
								class="site-menu main-menu site-menu-dark js-clone-nav mr-auto d-none d-lg-block">
								<li class="cta"><a href="index.html#contact-section"
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


		<jsp:useBean id="productSvc" scope="page"
			class="com.product.model.ProductService" />
<br><br><br>
		<div class="contain">
			<h1 class="shoppingCar" ></h1>
			<ul class="contain">
				<li class="item title">
					
					
					<h3 style="color:#48D494;">商品</h3>
					<h3 style="color:#48D494;">單價</h3>
					<h3 style="color:#48D494;">數量</h3>
					<h3 style="color:#48D494;">總計</h3>
					<h3 style="color:#48D494;">刪除</h3>
				</li>
				<c:forEach var="product" items="${shoppingList}">
					<c:if test="${product.value >0}">
						<li class="item ">
							<div class="item__box">
								<img
									src="<%=request.getContextPath()%>/DBGifReader2?id=${product.key}"
									class="item__img">
								<h4 class="item__name">${productSvc.getOneProduct(product.key).p_name}</h4>
							</div>



							<div class="item__price">$${productSvc.getOneProduct(product.key).p_price}</div>

							<div class="item__number-box">
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
									<input type="submit" value="+" class="plus item__btn" /> <input
										type="hidden" name="action" value="shoppingCarUpdate">
									<input type="hidden" name="actionForProduct" value="plus">
									<input type="hidden" name="product_no" value="${product.key}">
								</form>
								<h4 class="item__number" style="color:grey">${product.value}</h4>
								<form METHOD="post"
									ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
									<input type="submit" value="-" class="mius item__btn" /> <input
										type="hidden" name="action" value="shoppingCarUpdate">
									<input type="hidden" name="actionForProduct" value="mius">
									<input type="hidden" name="product_no" value="${product.key}">
								</form>
							</div>



							<div class="item__total">$${product.value*productSvc.getOneProduct(product.key).p_price}</div>


							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/back-end/product/product.do">
								<input type="submit" value="刪除" class="item__delete" /> <input
									type="hidden" name="action" value="shoppingCarUpdate">
								<input type="hidden" name="actionForProduct" value="delete">
								<input type="hidden" name="product_no" value="${product.key}">
							</form>
						</li>
					</c:if>
				</c:forEach>
			</ul>
			<form METHOD="post"
				ACTION="<%=request.getContextPath()%>/back-end/po/po.do">
				<div class="checkout">
					<h3 class="checkout__amount">總金額</h3>
					<h3 class="money">
						$<%=totalAmount%></h3>
					<input type="submit" value="下訂單" class="checkout__btn" /> 
					<input type="hidden" name="mem_id" value="1007"> 
					<input type="hidden" name="action" value="gopay">
					<input type="hidden" name="po_total" value="<%=totalAmount%>">
					
				</div>
			</form>
		</div>





		<footer class="footer-section">
			<div class="container">
				<div class="row">
					<div class="col-md-4">
						<h3>About Gymer</h3>
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
								All rights reserved
								<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							</p>
						</div>
					</div>

				</div>
			</div>
		</footer>



	</div>
	<!-- .site-wrap -->

	<script
		src="<%=request.getContextPath()%>/source/js/jquery-3.3.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery-migrate-3.0.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/source/js/jquery-ui.js"></script>
	<script src="<%=request.getContextPath()%>/source/js/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/owl.carousel.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.stellar.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.countdown.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/bootstrap-datepicker.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.easing.1.3.js"></script>
	<script src="<%=request.getContextPath()%>/source/js/aos.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.fancybox.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.sticky.js"></script>
	<script
		src="<%=request.getContextPath()%>/source/js/jquery.mb.YTPlayer.min.js"></script>




	<script src="<%=request.getContextPath()%>/source/js/main.js"></script>

</body>

</html>