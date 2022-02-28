<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<jsp:useBean id="productcategorySvc" scope="page" class="com.product_category.model.ProductCategoryService" />

<html>
<head><title>所有商品類別 - listAllPC.jsp</title>

<style>
  table#table-1 {
	background-color: orange;
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
<body>


<table id="table-1">
	<tr><td>
		 <h3>所有商品類別 - listAllPC.jsp</h3>
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
		<th>商品類別編號</th>
		<th>商品類別編號名稱</th>
		
		
		<th>刪除</th>
		<th>查詢同類別商品</th>
	</tr>
	
	<c:forEach var="productcategoryVO" items="${productcategorySvc.all}">
		<tr>
			<td>${productcategoryVO.p_category_no}</td>
			<td>${productcategoryVO.p_category_name}</td>
			
	
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product_category/productcategory.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="p_category_no" value="${productcategoryVO.p_category_no}">
			    <input type="hidden" name="action" value="delete_PC"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/product_category/productcategory.do" style="margin-bottom: 0px;">
			    <input type="submit" value="送出查詢"> 
			    <input type="hidden" name="p_category_no" value="${productcategoryVO.p_category_no}">
			    <input type="hidden" name="action" value="listProducts_ByPC_B"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

<%if (request.getAttribute("listProducts_ByPC")!=null){%>
       <jsp:include page="/back-end/product_category/listProducts_ByPC.jsp" />
<%} %>

</body>
</html>