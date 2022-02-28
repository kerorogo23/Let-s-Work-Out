<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product_category.model.*"%>

<%
ProductCategoryVO productcategoryVO = (ProductCategoryVO) request.getAttribute("productcategoryVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品類別修改 - update_productcategory_input.jsp</title>

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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>商品類別修改 - update_productcategory_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/product/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="productcategory.do" name="form1">
<table>
	<tr>
		<td>商品類別編號:<font color=red><b>*</b></font></td>
		<td><%=productcategoryVO.getP_category_no()%></td>
	</tr>
	<tr>
	<td>商品分類名稱:</td>
		<td><input type="TEXT" name="p_category_name" size="10"	value="<%=productcategoryVO.getP_category_name()%>" /></td>
	</tr>
	


	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="p_category_no" value="<%=productcategoryVO.getP_category_no()%>">
<input type="submit" value="送出修改"></FORM>
</body>