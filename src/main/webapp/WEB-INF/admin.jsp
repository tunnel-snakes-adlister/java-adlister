<%--
  Created by IntelliJ IDEA.
  User: justinleemccaleb
  Date: 7/26/20
  Time: 4:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Admin Dashboard"/>
    </jsp:include>
</head>
<body class="bg-secondary">
<c:choose>
    <c:when test="${sessionScope.user!=null}">
        <jsp:include page="/WEB-INF/partials/logged-in-navbar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    </c:otherwise>
</c:choose>

<h1 class="text-center">Admin Page</h1>
<hr>
<h1 class="text-center">Edit Ads</h1>
<div class="container-fluid">
    <div class="row d-flex justify-content-center">
    <c:forEach var="ad" items="${ads}">
        <div class="card m-2 col-5">
            <div class="card-body">
            <h2 class="card-title text-center">${ad.title}</h2>
            <p class="card-text text-center">${ad.description}</p>
                <div class="d-flex justify-content-center">
                <a class="btn btn-outline-primary btn-sm w-50 mr-1" href="${pageContext.request.contextPath}/edit-ad/${ad.id}" role="button">Edit Ad</a>
                <a class="btn btn-outline-danger btn-sm w-50" href="${pageContext.request.contextPath}/delete-ad/${ad.id}" role="button">Delete Ad</a>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>

<hr>

<h1 class="text-center">Edit Users</h1>
<div class="container-fluid">
    <div class="row d-flex justify-content-center">
    <c:forEach var="user"  items="${users}">
        <div class="card m-2 col-5">
            <div class="card-body">
            <h2 class="text-center card-title">${user.username}</h2>
            <h4 class=" text-center card-subtitle">${user.email}</h4>
                <div class="d-flex justify-content-center mt-2">
            <a class="btn btn-outline-danger btn-sm w-50" href="${pageContext.request.contextPath}/delete-user/${user.id}" role="button">Delete User</a>
                </div>
            </div>
        </div>
    </c:forEach>
    </div>
</div>

</body>
</html>
