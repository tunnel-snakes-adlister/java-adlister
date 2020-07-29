<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Your Profile"/>
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
<h1 class="text-center">Welcome, <c:out value="${sessionScope.user.username}!"/></h1>
<div class="container-fluid">
    <div class="row d-flex justify-content-center">
        <c:forEach var="ad" items="${ads}">
            <div class="card m-2 col-5">
                <div class="card-body">
                    <h2 class="card-title text-center">${ad.title}</h2>
                    <p class="card-text text-center">${ad.description}</p>
                    <div class="d-flex justify-content-center">
                        <a class="btn btn-outline-primary btn-sm w-50 mr-1"
                           href="${pageContext.request.contextPath}/edit-ad/${ad.id}" role="button">Edit Ad</a>
                        <a class="btn btn-outline-danger btn-sm w-50"
                           href="${pageContext.request.contextPath}/delete-ad/${ad.id}" role="button">Delete Ad</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>


</body>
</html>
