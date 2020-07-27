<%--
  Created by IntelliJ IDEA.
  User: justinleemccaleb
  Date: 7/26/20
  Time: 4:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="/WEB-INF/partials/head.jsp">
        <jsp:param name="title" value="Edit Profile"/>
    </jsp:include>
</head>
<body>
<jsp:include page="/WEB-INF/partials/navbar.jsp"/>

<div class="container">
    <h1>Edit Profile</h1>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="username">Username</label>
            <input id="username" name="username" class="form-control" type="text">
        </div>
        <input type="submit" class="btn btn-primary btn-block" value="Update Username">
    </form>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="email">Email</label>
            <input id="email" name="email" class="form-control" type="email">
            <input type="submit" class="btn btn-primary btn-block" value="Update Email">
        </div>
    </form>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="password">Password</label>
            <input id="password" name="password" class="form-control" type="password">
            <input type="submit" class="btn btn-primary btn-block" value="Update Password">
        </div>
    </form>
</div>

</body>
</html>
