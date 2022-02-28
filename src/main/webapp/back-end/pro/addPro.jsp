<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.pro.model.*"%>

<%
  ProVO proVO = (ProVO) request.getAttribute("proVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>�нm��Ʒs�W - addPro.jsp</title>

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
		 <h3>�нm��Ʒs�W - addPro.jsp</h3></td><td>
		 <h4><a href="<%=request.getContextPath()%>/back-end/pro/select_page.jsp"><img src="<%=request.getContextPath()%>/images/tomcat.png" width="100" height="100" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
/LWO
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pro/pro.do" name="form1" enctype="multipart/form-data">
<table>
<!-- 	<tr> -->
<!-- 		<td>�нm�s��:</td> -->
<!-- 		<td><input type="TEXT" name="pro_ID" size="45"  -->
<%-- 			 value="<%= (proVO==null)? "3006" : proVO.getPro_ID()%>" /></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>���u�s��:</td>
		<td><input type="TEXT" name="work_ID" size="45"
			 value="<%= (proVO==null)? "2006" : proVO.getWorkId()%>" /></td>
	</tr>
	<tr>
		<td>²��:</td>
		<td><input type="TEXT" name="pro_resume" size="45"
			 value="<%= (proVO==null)? "�]�B" : proVO.getProResume()%>" /></td>
	</tr>
	<tr>
		<td>�g��:</td>
		<td><input type="TEXT" name="expr" size="45"
			 value="<%= (proVO==null)? "��|�Ѯv" : proVO.getExpr()%>" /></td>
	</tr>
	<tr>
		<td>�нm�Ϥ�:</td>
		<td><input type="file" name="pro_photo"></td>
	</tr>

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>����:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="�e�X�s�W"></FORM>
</body>

</html>