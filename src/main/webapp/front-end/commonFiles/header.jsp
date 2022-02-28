<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <header class="site-navbar py-4 js-sticky-header site-navbar-target" role="banner" style="background-color: black;">

      <div class="container-fluid">
        <div class="d-flex align-items-center">
          <div class="site-logo"><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section">Let's Work Out</a></div>
          <div class="ml-auto">
            <nav class="site-navigation position-relative text-right" role="navigation">
              <ul class="site-menu main-menu js-clone-nav mr-auto d-none d-lg-block">
                <li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#home-section" class="nav-link">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/front-end/index/members_only2.jsp#member-section"class="nav-link">Member</a></li>
				<li><a href="<%=request.getContextPath()%>/front-end/index/index.jsp#classes-section">Classes</a></li>
                <li><a href="<%=request.getContextPath()%>/back-end/pro.do?action=listAllPros" class="nav-link active">Coach</a></li>
                <li><a href="<%=request.getContextPath()%>/front-end/product/shop.jsp" class="nav-link">Shop</a></li>
              </ul>
            </nav>
          </div>
          <div class="ml-auto">
            <nav class="site-navigation position-relative text-right" role="navigation">
              <ul class="site-menu main-menu site-menu-dark js-clone-nav mr-auto d-none d-lg-block">
                <li class="cta"><a href="<%=request.getContextPath() %>/front-end/signin.jsp" class="nav-link"><span
                      class="rounded border border-primary">CONTACT</span></a></li>
              </ul>
            </nav>
            <a href="#" class="d-inline-block d-lg-none site-menu-toggle js-menu-toggle text-black float-right"><span
                class="icon-menu h3"></span></a>
          </div>
        </div>
      </div>

    </header>