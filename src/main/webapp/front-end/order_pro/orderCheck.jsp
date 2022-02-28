<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Order Confirmation</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<jsp:include page="../commonFiles/head.jsp"></jsp:include>

    <style>
        #customers {
          font-family: Arial, Helvetica, sans-serif;
          border-collapse: collapse;
          width: 100%;
        }
        
        #customers td, #customers th {
          border: 1px solid #ddd;
          padding: 8px;
        }
        
        #customers tr:nth-child(even){background-color: #f2f2f2;}
        
        #customers tr:hover {background-color: #ddd;}
        
        #customers th {
          padding-top: 12px;
          padding-bottom: 12px;
          text-align: left;
          background-color: #04AA6D;
          color: white;
        }
     </style>
</head>

<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">

	<div class="site-wrap">
		<jsp:include page="../commonFiles/header.jsp"></jsp:include>

		<div class="site-section" style="background: rgb(182, 235, 167);">
			<div class="container" style="margin-top: 50px">
				<div class="row">
				    <h1  style="color:#5B00AE ; font-size:50px" }>訂單確認</h1>
					
					<table id="customers" class="table" style="border-color: black; border: solid">

					  <tr>
					    <th >購買人姓名 </th>				
					    <th >${membersVOLogin.memName}</th>											   				   					    			    
					  </tr>
				
					  <tr>
					    <td>購買人信箱</td>
					    <td>${membersVOLogin.memEmail}</td>					   
					  </tr>
					  <tr>
					    <td>購買人電話</td>
					    <td>${membersVOLogin.memPhone}</td>					    
					  </tr>
					   
					  <tr>
					    <td>選擇授課教練</td>
					    <td>${workerName }</td>			    
					  </tr>
					  <tr>
					    <td>購買堂數</td>
					    <td>${courseOrderVO.courseOrderHours }</td>					    
					  </tr>
					  <tr>
					    <td>消費價格</td>
					    <td>${courseOrderVO.courseOrderPrice }</td>					   
					  </tr>
					  <tr>
					    <td>訂單建立日期</td>
					    <td>${courseOrderVO.createdTime }</td>					    
					  </tr>					
					</table>

					
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commonFiles/footer.jsp"></jsp:include>
	<jsp:include page="../commonFiles/scriptImport.jsp"></jsp:include>
</body>

</html>