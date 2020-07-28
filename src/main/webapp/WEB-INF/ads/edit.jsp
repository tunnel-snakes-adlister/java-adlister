<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Ad" />
    </jsp:include>
</head>
<body>
<c:choose>
    <c:when test="${sessionScope.user!=null}">
        <jsp:include page="/WEB-INF/partials/loggedInNavbar.jsp"/>
    </c:when>
    <c:otherwise>
        <jsp:include page="/WEB-INF/partials/navbar.jsp"/>
    </c:otherwise>
</c:choose>

<div class="container">
    <h1>Edit Ad</h1>

    <div class="col-md-6">
        <c:forEach var="ad" items="${ad}">
            <h1>Created by ${user.username}</h1>
            <h2>${ad.title}</h2>
            <p>${category}</p>
            <p>${ad.description}</p>
        </c:forEach>
    </div>
    <form action="/edit-ad" method="post">
        <div class="form-group">
            <label for="updatedTitle">Update Title</label>
            <input id="updatedTitle" name="updatedTitle" class="form-control" type="text">
        </div>
        <div class="form-group">
            <label for="updatedDescription">Update Description</label>
            <input id="updatedDescription" name="updatedDescription" class="form-control" type="text">
        </div>

    </form>
</div>


</body>
</html>
