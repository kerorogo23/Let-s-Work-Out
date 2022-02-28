<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_category.model.*"%>

<%
Integer product_no = Integer.valueOf(request.getParameter("product_no"));
pageContext.setAttribute("product_no", product_no);

int count = (Integer) session.getAttribute("count") == null ? 0 : (Integer) session.getAttribute("count");
session.setAttribute("count", count);
%>

<html>
<head>

<link rel="stylesheet"href="<%=request.getContextPath()%>/front-end/product/css/itemDetails.css"/>

<title>Let's Work Out &mdash; Product Details</title> 
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
								<li><a
									href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section"
									class="nav-link">Home</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/members_only2.jsp#member-section"class="nav-link">Member</a></li>
								<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#classes-section">Classes</a></li>
								<li><a href="<%=request.getContextPath()%>/back-end/pro.do?action=listAllPros" class="nav-link">Coach</a></li>
								<li><a
									href="<%=request.getContextPath()%>/front-end/product/shop.jsp"
									class="nav-link active">Shop</a></li>
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




		<jsp:useBean id="productVO" scope="page"
			class="com.product.model.ProductVO" />
		<jsp:useBean id="productSvc" scope="page"
			class="com.product.model.ProductService" />
		<jsp:useBean id="productcategorySvc" scope="page"
			class="com.product_category.model.ProductCategoryService" />

		<main class="contain">
			<section class="item" style="background-color:#212529;">
				<div class="left__box">
					<img
						src="<%=request.getContextPath()%>/DBGifReader2?id=${product_no}"
						class="item__img">
					
				</div>
				<div class="right__box">
					<span class="item__name" style="color:#48d494">${productSvc.getOneProduct(product_no).p_name}</span>



					<div class="item__price-box" style="#495057">
						<div>${productSvc.getOneProduct(product_no).p_price}</div>
					</div>







					<span class="checkout-type"></span>
					<hr>
					<span class="item__content" style="color:white">${productSvc.getOneProduct(product_no).p_detail}</span>
					<hr>
					<div class="checkout">
						<%--             <button class="checkout-btn btn"><a href="<%=request.getContextPath()%>/front-end/product/shoppingCar.jsp">查看購物車</a></button> --%>
						<button class="add-btn btn" data-item="${product_no}">加入購物車</button>
						<a
							href="<%=request.getContextPath()%>/front-end/product/shoppingCar.jsp"
							class="checkout-btn btn">查看購物車</a>
						<div class="shopping-car">
							<span class="shopping-car__number">${count}</span> <img
								src="<%=request.getContextPath()%>/front-end/pic/shopping-cart1.svg"
								class="shopping-car__icon">
						</div>
					</div>
					<hr>
					<div class="right__box--bottom">
						
					</div>
				</div>
			</section>
			<section class="other-goods" style="background-color:#212529;">
			<span class="t" style="color:#48d494">相關商品</span>
				<div class="goods-contain">
					<div class="goods" >
						<img src="<%=request.getContextPath()%>/DBGifReader2?id=4001"
							class="goods__img" style="width:168px;height:168px"> <span class="goods__name"style="color:white">${productSvc.getOneProduct(4001).p_name}</span>
						<span class="goods__price">NT
							${productSvc.getOneProduct(4001).p_price}</span>
					</div>
					<div class="goods">
						<img src="<%=request.getContextPath()%>/DBGifReader2?id=4003"
							class="goods__img" style="width:168px;height:168px"> <span class="goods__name"style="color:white">${productSvc.getOneProduct(4003).p_name}</span>
						<span class="goods__price">NT
							${productSvc.getOneProduct(4003).p_price}</span>
					</div>
					<div class="goods">
						<img src="<%=request.getContextPath()%>/DBGifReader2?id=4004"
							class="goods__img" style="width:168px;height:168px"> <span class="goods__name"style="color:white">${productSvc.getOneProduct(4004).p_name}</span>
						<span class="goods__price">NT
							${productSvc.getOneProduct(4004).p_price}</span>
					</div>
					<div class="goods">
						<img src="<%=request.getContextPath()%>/DBGifReader2?id=4005"
							class="goods__img" style="width:168px;height:168px"> <span class="goods__name" style="color:white">${productSvc.getOneProduct(4005).p_name}</span>
						<span class="goods__price">NT
							${productSvc.getOneProduct(4005).p_price}</span>
					</div>
					<div class="goods">
						<img src="<%=request.getContextPath()%>/DBGifReader2?id=4006"
							class="goods__img" style="width:168px;height:168px"> <span class="goods__name"style="color:white">${productSvc.getOneProduct(4006).p_name}</span>
						<span class="goods__price">NT
							${productSvc.getOneProduct(4006).p_price}</span>
					</div>
				</div>
			</section>
		</main>




		<script type="text/javascript">
const addBtn = document.querySelector(".add-btn");
const itemNumber = document.querySelector(".shopping-car__number");

let shoppingCar = function (product_no) {
fetch("http://localhost:8081/CFA103G1/front-end/product/ShoppingCar.do?product_no="+product_no)
  	.then(function(response) {
  		
 		 }).catch((error) => console.log("error", error));
}

let count="${count}";
addBtn.addEventListener("click", function () {
	    let product_no=addBtn.getAttribute("data-item");
	    console.log(product_no);
	    itemNumber.innerHTML = ++count;
	    shoppingCar(product_no);
	    })
</script>

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