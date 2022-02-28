<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.purchase_order.model.*"%>
<%@ page import="com.purchase_order_details.model.*"%>
<jsp:useBean id="productSvc" scope="page" class="com.product.model.ProductService" />
<jsp:useBean id="producrcategorySvc" scope="page" class="com.product_category.model.ProductCategoryService" />

<%
Map<Integer, Integer> testmap = (Map<Integer, Integer>) session.getAttribute("shoppingList");
Set<Integer> setproduct_no = (Set<Integer>)testmap.keySet();
for(Integer product_no: setproduct_no) {
// 	System.out.println(product_no + ":" + testmap.get(product_no));
	if(testmap.get(product_no)==0)
		continue;
}
System.out.println(setproduct_no);
pageContext.setAttribute("setproduct_no", setproduct_no);
%>


<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/product/css/itemView.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/product/css/page.css" />
<title>Let's Work Out &mdash; Product Order</title>
<style>
#info{
	
	margin: 10px 0 0 30px;
/* 	width:90%; */
	
}
#info td{
/* 	border:1px solid black; */
	vertical-align:top;
}
#comde{
	left:100px;
	border-collapse:collapse;
	}
#comde th{
	
	text-align:center;
	width:150px;
	height:24px; 
}
#comde tr:nth-child(even){
 	background-color: #f1f3f5; 
 	margin-top:2px;
	}
#comde td{
	
	text-align:center;
	width:325px;
	height:52px;
/* 	border:1px solid black; */
}
.hidden{
display:none;
}
.myimg{
	width:50px;
	height:auto;
}
</style>
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



		

		<div >
	<br><br><br><br>
<h3 style="text-align:center;color:#48d494">訂單</h3>

<input type="button" id="hbtn" style="width:150px ; height:50px;margin-left:45.5%;" value="查看訂單細項" >
		
			<table id="comde" class="hidden" >
		<tr>
			<th>商品照片</th>
			<th>商品名稱</th>
			<th>單價</th>
			<th>商品數量</th>
			<th>小計金額</th>
		</tr>
		
		<c:forEach var="setproduct_no" items="${setproduct_no}">
			<c:if test="${shoppingList.get(setproduct_no) >0}">
			
			<tr>
			<td><img class="myimg" src="<%=request.getContextPath()%>/DBGifReader2?id=${setproduct_no}"/></td>
			<td>${productSvc.getOneProduct(setproduct_no).p_name}</td>
			
			<td>${productSvc.getOneProduct(setproduct_no).p_price}</td>
			
			<td>${shoppingList.get(setproduct_no)}</td>
			<td>
			${productSvc.getOneProduct(setproduct_no).p_price*shoppingList.get(setproduct_no)} 
			</td>

			</tr>
			</c:if>
		</c:forEach>
	</table>
	<br><br>
	<div style="margin-left:1300px;">
	<b>訂單總額:${purchaseorderVO.po_total}</b>
	</div>
	
			
			
		</div>

<h2 style="margin-left:672px; color:#48d494">輸入訂單資訊</h2>

<FORM METHOD="POST" ACTION="<%= request.getContextPath()%>/back-end/po/po.do">
<table id="info">
<tr>
	<td><b style="margin-left:600px;">會員編號:</b></td>
	<td><input type="text" name="mem_id" required placeholder="請輸入會員編號"><br><br></td>
</tr>


<tr>
	<td><b style="margin-left:600px;">選擇送貨方式:</b></td>
	<td>
	<label ><input type="radio" name="po_delivery"  value="宅配" onclick="showdiv();">宅配</label>
	<label><input type="radio" name="po_delivery"  value="超商取貨" onclick="showdiv();">超商取貨</label>
	<div id="pd0" style="display:none"><br>收貨地址:
	<input type="text" name="purchase_detail1" placeholder="請輸入地址"> 
	</div>
	<div id="pd1" style="display:none" ><br>收貨門市:
	
	<input type="text" name="purchase_detail2" placeholder="請輸入超商地址"> 
	
	</div>
	<br><br></td>
</tr>

<tr>
	<td><b style="margin-left:600px;">選擇付款方式:</b></td>
	<td>
	<label><input type="radio" name="po_payment"  value="貨到付款" onclick="showdiv2();">貨到付款</label>
	<label><input type="radio" name="po_payment"  value="信用卡" onclick="showdiv2();">信用卡</label>
	<div id="pd3" style="display:none" ><br>請輸入卡號:
	<input type="text" name="purchase_detailC" placeholder="請輸入信用卡卡號" maxlength="12"> 
	</div>
	</td>
</tr>


	
	<tr>
		<td >
		<input type="hidden" name="action" value="insertorderinfo">
<%-- 		<input type="hidden" name="mem_id" value="${purchaseorderVO.mem_id}"> --%>
		<input type="hidden" name="po_total" value="${purchaseorderVO.po_total}">
		<input type="hidden" name="po_time" value="${purchaseorderVO.po_time}">
		<input type="hidden" name="po_status" value="1">
		<input type="submit"  style="margin-left:710px" id="submitbtn"  value="確認" >
		</td>
	</tr>
	
</table>
</FORM>
<script>
// 送貨方式
function showdiv(){

    let po_delivery="";
    let pd = document.getElementsByName("po_delivery");
    for(var i=0;i<pd.length;i++){
    if(pd[i].checked)
    	po_delivery = pd[i].value;
    }
  
    switch (po_delivery ){
        case '宅配':
            document.getElementById("pd0").style.display="block";
            document.getElementById("pd1").style.display="none";
            break;
        case '超商取貨':
            document.getElementById("pd0").style.display="none";
            document.getElementById("pd1").style.display="block";
            break;
        default:
            document.getElementById("pd0").style.display="none";
            document.getElementById("pd1").style.display="none";
            break;                                                              
    }
}	
	
function showdiv2(){

    let po_payment="";
    let po = document.getElementsByName("po_payment");
    for(var i=0;i<po.length;i++){
    if(po[i].checked)
    	po_payment = po[i].value;
    }
  
    switch (po_payment){
        case '信用卡':
            document.getElementById("pd3").style.display="block";
            break;
        case '貨到付款':
            document.getElementById("pd3").style.display="none";
            break;
//         case '2':
//             document.getElementById("op0").style.display="none";
//             break;
        default:
            document.getElementById("op0").style.display="none";
            break;                                                              
    }
}

	let hbtn = document.getElementById("hbtn");
	hbtn.addEventListener("click", function(){
		let tcomde = document.getElementById("comde");
		tcomde.classList.toggle("hidden");
		if(hbtn.value==="隱藏訂單細項"){
			hbtn.value="查看訂單細項"
		}else if(hbtn.value==="查看訂單細項")
		hbtn.value="隱藏訂單細項";
	})
	
// 	let confbtn = document.getElementById("submitbtn");
// 	let orderPaying1 = document.getElementsByName("po_payment");
// 	confbtn.addEventListener("click", function(){
// 		if(orderPaying1[1].checked){
// 			alert('親~ 您選擇了ATM付款,在您付款完成後會盡速幫你準備商品出貨唷!\n匯款帳號 : (玉山銀行) XXX-0827942888825');
// 		}
// 	})

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