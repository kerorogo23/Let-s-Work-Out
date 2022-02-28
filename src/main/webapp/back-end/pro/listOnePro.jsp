<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pro.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
  System.out.println(proVO);
%>

<html>
<head>
<title>教練資料 - listOnePro.jsp</title>

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
 	width: 600px; 
 	background-color: white; 
 	margin-top: 5px; 
 	margin-bottom: 5px; 
   } 
   table, th, td { 
     border: 1px solid #CCCCFF; 
   } */
   th, td { 
     padding: 5px; 
     text-align: center;
   } 
</style> 

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>教練資料 - ListOnePro.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/pro/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>教練編號</th>
		<th>員工編號</th>
		<th>簡介</th>
		<th>經驗</th>
		<th>教練照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%=proVO.getProId()%></td>
		<td><%=proVO.getWorkId()%></td>
		<td><%=proVO.getProResume()%></td>
		<td><%=proVO.getExpr()%></td>
		<td><img src="<%=request.getContextPath()%>/DBGifReader5?proId=${proVO.proId}"></td>
		<td>  
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="pro_ID"  value="${proVO.proId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="pro_ID"  value="${proVO.proId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
	</tr>
</table>

</body>
</html>

	