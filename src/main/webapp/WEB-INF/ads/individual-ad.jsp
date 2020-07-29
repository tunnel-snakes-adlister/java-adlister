<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing More Info" />
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

<div class="container">

    <div class="card border-none mt-5 p-4" style="">
        <c:forEach var="ad" items="${ad}">
            <h2>${ad.title}</h2>
            <h5>${category}</h5>
            <p>${ad.description}</p>
            <h4>Created by ${user.username}</h4>
        </c:forEach>
    </div>
</div>

</body>
</html>
