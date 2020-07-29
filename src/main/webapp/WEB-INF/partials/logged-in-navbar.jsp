<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<nav class="navbar bg-dark">--%>
<%--    <div class="container-fluid">--%>
<%--        <!-- Brand and toggle get grouped for better mobile display -->--%>
<%--        <div class="navbar-header">--%>
<%--            <a class="navbar-brand" href="/index.jsp">Adlister</a>--%>
<%--        </div>--%>
<%--        <ul class="nav navbar-nav d-flex flex-row justify-content-between ">--%>
<%--            <li class="nav-item mx-3"><a class="nav-link" href="/login">Login</a></li>--%>
<%--            <li class="nav-item mx-3"><a class="nav-link" href="/register">Register</a></li>--%>
<%--            <li class="nav-item mx-3"><a class="nav-link" href="/ads">Ads</a></li>--%>
<%--            <li class="nav-item mx-3"><a class="nav-link" href="/ads/create">Create an Ad</a></li>--%>
<%--        </ul>--%>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/index.jsp">Adlister</a>
    <%--        <img src="<%=pageContext.request.ContextPath()%>/img/tunnel-snakes%20logo.png" width="30" height="30" alt="" class="logo"/>--%>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="/logout">Logout</a>
            <a class="nav-item nav-link" href="/profile">Profile</a>
            <a class="nav-item nav-link" href="/ads">Ads</a>
            <a class="nav-item nav-link" href="/ads/create">Create an Ad</a>
        </div>
    </div>
    <form class="form-inline nav navbar-right" action="/search" method="POST" href="/search" name="myform">
        <input id="search" name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
        <select name="sort">
            <option value="sort1">By Title</option>
            <option value="cat">By Category</option>
        </select>
        <button class="btn btn-outline-success m-2" type="submit">Search</button>
</nav>
<%--            <a href="/search" class="Button btn btn-outline-success" type="submit">Submit</a>--%>

</form>
<script type="text/javascript">
    (function() {
        var form = document.getElementsByName('myform')[0];
        if (localStorage['name'] !== undefined) {
            var displayArea = document.getElementById('theSearch');
            displayArea.textContent = localStorage['search'];
        }
        form.addEventListener('submit', function() {
            var nameField = document.getElementsByName('search')[0];
            localStorage['search'] = nameField.value;
        }, false);
    })();
</script>
</div><!-- /.navbar-collapse -->
</div><!-- /.container-fluid -->
</nav>