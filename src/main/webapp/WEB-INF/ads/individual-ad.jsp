<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing More Info" />
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp" />

<div class="container">
    <h1>Here's more information about the ad!</h1>

    <div class="col-md-6">
        <c:forEach var="ad" items="${ad}">
            <h1>Created by ${user.username}</h1>
            <h2>${ad.title}</h2>
            <p>${ad.description}</p>
        </c:forEach>
    </div>
</div>

</body>
</html>
