<%@ include file="common/header.jsp" %>

<nav class="navbar navbar-expand-md navbar-light fixed-top" style="background-color: #6897BB">
<span class="material-symbols-outlined" style="color: white"> train </span>
    <a href="<c:url value="/"/>" class="navbar-brand" style="color: white">RLW</a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav">
            <a href="<c:url value="/"/>" class="nav-item nav-link active" style="color: white">Home</a>
            <div class="btn-group">
                <a href="<c:url value="/stations"/>" class="btn btn-outline-light">Stations</a>
                <a href="<c:url value="/trains"/>" class="btn btn-outline-light">Trains</a>
                <a href="<c:url value="/passengers"/>" class="btn btn-outline-light">Passengers</a>
                <a href="<c:url value="/timetable"/>" class="btn btn-outline-light">Timetable</a>
            </div>
        </div>
        <div class="navbar-nav ml-auto">
            <a href="#" class="nav-item nav-link disabled" style="color: white">
                Logged in as <strong>${pageContext.request.userPrincipal.name}</strong></a>
            <a href="<c:url value="/logout"/>" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>
<div style="height: 100px"></div>

<div class="container">
    <div class="jumbotron">
        <table class="col-sm-10 offset-1 table table-sm table-striped table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="station" items="${stationsList}">
                <tr>
                    <td>${station.name}</td>
                    <td>
                        <a href="/stations/edit/${station.id}" class="btn btn-secondary" role="button">Edit</a>
                        <a href="/stations/delete/${station.id}" class="btn btn-danger" role="button">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value="/stations/add"/>" class="col-sm-10 offset-1 btn btn-outline-info btn-block"
           role="button">+</a>
    </div>
</div>

<%@ include file="common/footer.jsp" %>