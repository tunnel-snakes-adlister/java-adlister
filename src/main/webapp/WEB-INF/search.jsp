<%--
  Created by IntelliJ IDEA.
  User: larry
  Date: 7/28/20
  Time: 9:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Viewing All The Ads" />
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
    <h1>Here are the search results!</h1>
    <form action="/search" method="post">
    <select name="sort" onchange="this.form.submit()">
        <option value="sort1">SORT</option>
        <option value="alpha">Sort A-Z</option>
        <option value="revalpha">Sort Z-A</option>
        <option value="revalpha">By Category</option>
    </select>
    </form>
    <c:forEach var="ad" items="${ads}">
        <div class="col-md-6">
            <h2>${ad.title}</h2>
            <a href="${pageContext.request.contextPath}/ads/individual-ad/${ad.id}">Click for more info</a>
        </div>
    </c:forEach>
</div>

</body>
</html>
