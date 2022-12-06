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
    <h2 class="text-center">The following stations are currently on Track ${track}:</h2>
    <div class="jumbotron">
        <div style="height: 30px"></div>
        <div class="text-center">
            <h4>
                ${stationsList.get(0).name} -
                <c:forEach var="stationItem" items="${stationsList}" begin="1" end="${stationsList.size() - 2}">
                    ${stationItem.name} -
                </c:forEach>
                ${stationsList.get(stationsList.size() - 1).name}
            </h4>
            <div style="height: 30px"></div>
            <p>Where would you like to append <strong>${station.name}?</strong></p>
        </div>
        <div style="height: 100px"></div>
        <form action="/stations/edit/track" method="POST" class="col-sm-10 offset-1">
            <input type="hidden" name="appendLocation" value="before">
            <input type="hidden" name="track" value="${track}">
            <input type="hidden" name="length" value="${length}">
            <button class="col-sm-10 offset-1 btn btn-outline-info btn-block"
                    type="submit">Before ${stationsList.get(0).name}</button>
        </form>
        <div style="height: 30px"></div>
        <form action="/stations/edit/track" method="POST" class="col-sm-10 offset-1">
            <input type="hidden" name="appendLocation" value="after">
            <input type="hidden" name="track" value="${track}">
            <input type="hidden" name="length" value="${length}">
            <button class="col-sm-10 offset-1 btn btn-outline-info btn-block"
                    type="submit">After ${stationsList.get(stationsList.size() - 1).name}</button>
        </form>
    </div>
</div>

<%@ include file="common/footer.jsp" %>