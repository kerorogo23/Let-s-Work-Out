<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Product</title>

<style>
 aside.aside{
        border: 1px solid blue;
        position: fixed;
        top: var(--header-height);
        left: 0;
        height: calc(100% - var(--header-height));
        width: var(--aside-width);
        background-color: #efefef;
        overflow-y: auto;
        padding: 20px 0;
        transition: all 1s;
      }
      aside.aside button.btn_hamburger{
        display: none;
      }
      @media screen and (max-width: 767px){
        aside.aside{
          top: 0;
          height: 100%;
          transform: translateX(-100%);
        }
        aside.aside.-on{
          transform: translateX(0%);
        }
        header.header button.btn_hamburger, aside.aside button.btn_hamburger{
          display: inline-block;
        }
      }

      aside.aside > nav.nav > ul.nav_list{
        margin: 0;
        padding: 0;
        list-style: none;
      }
      aside.aside > nav.nav > ul.nav_list > li > a{
        display: inline-block;
        border: 1px solid lightblue;
        width: 100%;
        padding: 3px 10px;
      }
table#table-1 {
	width: 100%;
	background-color: skyblue;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
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

</head>
<body bgcolor='white'>


	<table id="table-1">
		<tr>
			<td><h3>Product</h3>
				
		</tr>
	</table>

	

	<h3>商品查詢:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='<%=request.getContextPath()%>/back-end/product/listAllProduct.jsp'>查看所有商品</a> <br>
			<br></li>


		<li>
			<FORM METHOD="post" ACTION="product.do">
				<b>輸入商品編號 (如4001):</b> <input type="text" name="product_no">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>

		<jsp:useBean id="productSvc" scope="page"
			class="com.product.model.ProductService" />

		<li>
			<FORM METHOD="post" ACTION="product.do">
				<b>選擇商品編號:</b> <select size="1" name="product_no">
					<c:forEach var="productVO" items="${productSvc.all}">
						<option value="${productVO.product_no}">${productVO.product_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
		
		<jsp:useBean id="productcategorySvc" scope="page"
			class="com.product_category.model.ProductCategoryService" />
		<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product_category/productcategory.do">
				<b><font color=green>選擇商品類別編號:(1.食品2.健身3.瑜珈)</font></b> <select size="1" name="p_category_no">
					<c:forEach var="productcategoryVO" items="${productcategorySvc.all}">
						<option value="${productcategoryVO.p_category_no}">${productcategoryVO.p_category_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="listProducts_ByPC_A">
				<input type="submit" value="送出">
			</FORM>
		</li>
		


	</ul>


	<h3>商品管理</h3>

	<ul>
		<li><a href='addProduct.jsp'>新增</a>商品</li>
	</ul>
	
	<ul>
		<li><a href='<%=request.getContextPath()%>/back-end/product_category/listAllPC.jsp'>顯示</a>所有商品類別</li>
	
	</ul>

</body>
</html>