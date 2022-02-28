<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<title>Let's Work Out &mdash; ProIndex</title>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<jsp:include page="../commonFiles/head.jsp"></jsp:include>
</head>
<body data-spy="scroll" data-target=".site-navbar-target"
	data-offset="300">

	<div class="site-wrap">
		<jsp:include page="../commonFiles/header.jsp"></jsp:include>

		<div class="site-section" style="background: rgb(182, 235, 167);">
			<div class="container" style="margin-top: 50px">
				<div class="row">
					<div class="col-lg-3">
                        <div class="list-group">
                            <a href="#" class="list-group-item list-group-item-action active">
                                Cras justo odio
                            </a>
                            <a href="#" class="list-group-item list-group-item-action">Dapibus ac facilisis in</a>
                            <a href="#" class="list-group-item list-group-item-action">Morbi leo risus</a>
                            <a href="#" class="list-group-item list-group-item-action">Porta ac consectetur ac</a>
                            <a href="#" class="list-group-item list-group-item-action disabled">Vestibulum at eros</a>
                        </div>
                    </div>
                    <div class="col-lg-9">
                        <div class="row">
                        	<c:forEach var="proVO" items="${list}">
                        		<c:set var="workId" value="${proVO.workId }" />
	                            <div class="col">
	                                <div class="card" style="width: 15rem;">
	                                	<img  class="card-img-top" src="<%=request.getContextPath()%>/DBGifReader5?proId=${proVO.proId}" alt="Card image cap" style="width:150px;margin:0px 50px;">
	                                    <div class="card-body">
	                                        <h5 class="card-title" style="color: black;text-align: center">${workerMap[workId] }</h5>
	                                        <p class="card-text">${ proVO.expr }</p>
	                                        <a href="<%=request.getContextPath() %>/course?action=showProAndCourse&proId=${proVO.proId}" class="btn btn-primary">Go somewhere</a>
	                                    </div>
	                                </div>
	                            </div>
                            </c:forEach>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commonFiles/footer.jsp"></jsp:include>
	<jsp:include page="../commonFiles/scriptImport.jsp"></jsp:include>
</body>

</html>