<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Please Log In" />
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
    <div class="container d-flex justify-content-center mt-5">
        <div class="card border-0 mr-5 bg-secondary" style="width: 40em">
            <h1 class="text-center">Log In</h1>
            <form action="/login" method="POST" name="myform">
            <div class="form-group">
                <label for="username">Username</label>
                <p><c:out value="${usernameError}"/></p>
                <input id="username" name="username" class="form-control" type="text">
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <p><c:out value="${passwordError}"/></p>
                <input id="password" name="password" class="form-control" type="password">
            </div>
            <input type="submit" class="btn btn-primary btn-block" value="Log In">
        </form>
        </div>
    </div>
<jsp:include page="/WEB-INF/partials/footer.jsp"/>
</body>
</html>
