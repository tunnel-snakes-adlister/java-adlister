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
    <h4>Current Username: ${sessionScope.user.username}</h4>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="updatedUsername">Update Username</label>
            <input id="updatedUsername" name="updatedUsername" class="form-control" type="text">
        </div>
        <input type="submit" class="btn btn-primary btn-block" value="Update Username">
    </form>
    <h4>Current Email: ${sessionScope.user.email}</h4>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="updatedEmail">Update Email</label>
            <input id="updatedEmail" name="updatedEmail" class="form-control" type="email">
        </div>
            <input type="submit" class="btn btn-primary btn-block" value="Update Email">
    </form>
    <form action="/edit-profile" method="POST">
        <div class="form-group">
            <label for="updatedPassword">Update Password</label>
            <input id="updatedPassword" name="updatedPassword" class="form-control" type="password">
        </div>
        <div class="form-group">
            <label for="confirm_password">Confirm Password</label>
            <input id="confirm_password" name="confirm_password" class="form-control" type="password">
        </div>
            <input type="submit" class="btn btn-primary btn-block" value="Update Password">
    </form>
</div>

</body>
</html>
