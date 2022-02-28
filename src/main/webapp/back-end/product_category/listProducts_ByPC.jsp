<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="listProducts_ByPC" scope="request" type="java.util.Set<ProductVO>" /> <!-- 於EL此行可省略 -->
<jsp:useBean id="productcategorySvc" scope="page" class="com.product_category.model.ProductCategoryService" />


<html>
<head><title>商品 - listProducts_ByPC.jsp</title>

<style>
  table#table-2 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-2 h4 {
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>


<table id="table-2">
	<tr><td>
		 <h3>商品 - listProducts_ByPC.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品詳情</th>
		<th>商品狀態</th>
		<th>上架時間</th>
		<th>商品分類編號</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	
	<c:forEach var="productVO" items="${listProducts_ByPC}" >
		<tr ${(productVO.product_no==param.product_no) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色-->
			<td>${productVO.product_no}</td>
			<td>${productVO.p_name}</td>
			<td>${productVO.p_price}</td>
			<td>${productVO.p_detail}</td>
			<td>${productVO.p_status}</td>
			<td>${productVO.p_upload_time}</td>
					
			<td><c:forEach var="productcategoryVO" items="${productcategorySvc.all}">
                    <c:if test="${productVO.p_category_no==productcategoryVO.p_category_no}">
	                    ${productcategoryVO.p_category_no}<font color=orange>${productcategoryVO.p_category_name}</font>
                    </c:if>
                </c:forEach>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/product.do" style="margin-bottom: 0px;">
			    <input type="submit" value="修改"> 
			    <input type="hidden" name="product_no" value="${productVO.product_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller--><!-- 目前尚未用到  -->
			    <input type="hidden" name="action"	   value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product/product.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="product_no"      value="${productVO.product_no}">
			    <input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    <input type="hidden" name="action"     value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>




</body>
</html>