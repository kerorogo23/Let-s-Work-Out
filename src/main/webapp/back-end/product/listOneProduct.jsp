<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.product_category.model.*"%>
<%@ page import="com.product_photo.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
ProductPhotoVO ppVO = (ProductPhotoVO) request.getAttribute("ppVO");
%>
<%
  ProductCategoryService  productcategorySvc = new ProductCategoryService();
ProductCategoryVO  productcategoryVO =  productcategorySvc.getOnePC(productVO.getP_category_no());
%>

<html>
<head>
<title>商品資料</title>

<style>
  table#table-1 {
  width:100%;
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
  #p{
  
  width:150px;
  height:150px;
  }
</style>

<style>
  table {
	width: 100%;
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


<table id="table-1">
	<tr><td>
		 <h3>商品資料</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>商品分類編號</th>
		<th>商品名稱</th>
		<th>商品價格</th>
		<th>商品詳情</th>
		<th>商品狀態</th>
		<th>上架時間</th>
		
	</tr>
	<tr>
		<td><%=productVO.getProduct_no()%></td>
		<td><%=productVO.getP_category_no()%>【<%=productcategoryVO.getP_category_name()%> 】</td>
		<td><%=productVO.getP_name()%></td>
		<td><%=productVO.getP_price()%></td>
		<td><%=productVO.getP_detail()%></td>
		<td>${(productVO.p_status==0)?"上架中":"已下架"}</td>
		<td><%=productVO.getP_upload_time()%></td>
		
	</tr>
		
</table>

		<p><img id="p" src="/CFA103G1-1/DBGifReader2?id=<%=productVO.getProduct_no()%>"></p>
</body>
</html>