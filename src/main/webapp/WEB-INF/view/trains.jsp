<%@ include file="common/header.jsp" %>

<nav class="navbar navbar-expand-md navbar-light fixed-top" style="background-color: #491262">
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
                <th>Track</th>
                <th>Name</th>
                <th>Seats</th>
                <th>Route</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="train" items="${trainsList}">
                <tr>
                    <td>${train.track.id}</td>
                    <td>${train.name}</td>
                    <td>${train.seats}</td>
                    <td>
                        <c:if test="${!empty endpointsList.get(trainsList.indexOf(train))}">
                            ${endpointsList.get(trainsList.indexOf(train)).get(0).name} &LongRightArrow;
                            ${endpointsList.get(trainsList.indexOf(train)).get(1).name}
                        </c:if>
                    </td>
                    <td>
                        <a href="/trains/edit/${train.id}" class="btn btn-secondary" role="button">Edit</a>
                        <a href="/trains/delete/${train.id}" class="btn btn-danger" role="button">Delete</a>
                        <a href="/passengers/${train.id}" class="btn btn-info" role="button">Passengers</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <a href="<c:url value="/trains/add"/>" class="col-sm-10 offset-1 btn btn-outline-info btn-block"
           role="button">+</a>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<%@ include file="common/footer.jsp" %>