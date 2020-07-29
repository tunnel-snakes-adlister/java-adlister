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
<body>
<c:choose>
    <c:when test="${sessionScope.user!=null}">
        <jsp:include page="/WEB-INF/partials/logged-in-navbar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    </c:otherwise>
</c:choose>

<h1>ADMIN PAGE, OHHHH YEA</h1>

<div class="container">
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
            <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/edit-ad/${ad.id}" role="button">Edit Ad</a>
            <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/delete-ad/${ad.id}" role="button">Delete Ad</a>
        </div>
    </c:forEach>
</div>
<div class="container">
    <c:forEach var="user"  items="${users}">
        <div class="col-md-6">
            <h2>${user.username}</h2>
            <h4>${user.email}</h4>
        </div>
    </c:forEach>
</div>

</body>
</html>
