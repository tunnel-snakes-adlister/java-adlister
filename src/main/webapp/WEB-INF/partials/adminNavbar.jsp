<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/index.jsp">ADMIN</a>
        </div>
        <ul class="nav navbar-nav">
            <li><a href="/logout">Logout</a></li>
            <li><a href="/profile">Register</a></li>
            <li><a href="/ads">Ads</a></li>
        </ul>
        <form class="form-inline nav navbar-right" name="myform">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
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
