<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing More Info" />
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

<div class="container">
    <h1>Here's more information about the ad!</h1>

    <div class="col-md-6">
        <c:forEach var="ad" items="${ad}">
            <h1>Created by ${user.username}</h1>
            <h2>${ad.title}</h2>
            <p>${category}</p>
            <p>${ad.description}</p>
        </c:forEach>
    </div>
</div>

</body>
</html>
