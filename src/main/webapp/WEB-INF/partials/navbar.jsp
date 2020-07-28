<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">Adlister</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/login">Login</a></li>
            <li><a href="/register">Register</a></li>
            <li><a href="/ads">Ads</a></li>
        </ul>
        <form class="form-inline nav navbar-right" action="/search" method="POST" href="/search">
            <input id="search" name="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <select name="sort">
                    <option value="sort1">By Title</option>
                    <option value="cat">By Category</option>
            </select>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
<%--            <a href="/search" class="Button btn btn-outline-success" type="submit">Submit</a>--%>

        </form>
    </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>
