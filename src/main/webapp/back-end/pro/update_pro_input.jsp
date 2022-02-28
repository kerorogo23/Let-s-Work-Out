<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>

<%
  ProVO proVO = (ProVO) request.getAttribute("proVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�нm��ƭק� - update_pro_input.jsp</title>

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
		 <h3>�нm��ƭק� - update_pro_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/pro/select_page.jsp"><img src="<%=request.getContextPath()%>/images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>�нm�s��:<font color=red><b>*</b></font></td>
		<td><%=proVO.getProId()%></td>
	</tr>
	<tr>
		<td>���u�s��:</td>
		<td><input type="TEXT" name="workId" size="45" value="<%=proVO.getWorkId()%>" /></td>
	</tr>
	<tr>
		<td>²��:</td>
		<td><input type="TEXT" name="proResume" size="45"	value="<%=proVO.getProResume()%>" /></td>
	</tr>
	<tr>
		<td>�g��:</td>
		<td><input type="TEXT" name="expr" size="45"	value="<%=proVO.getExpr()%>" /></td>
	</tr>
	<tr>
		<td>�нm�Ϥ�:</td>
		<td><input type="file" name="pro_photo">
		     <img src="<%=request.getContextPath()%>/DBGifReader5?proId=${proVO.proId}">
		</td>
	</tr>
	
<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="proId" value="<%=proVO.getProId()%>">
<input type="submit" value="�e�X�ק�"></FORM>
</body>