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
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <div class="btn-group">
                    <a href="<c:url value="/stations"/>" class="btn btn-outline-light">Stations</a>
                    <a href="<c:url value="/trains"/>" class="btn btn-outline-light">Trains</a>
                    <a href="<c:url value="/passengers"/>" class="btn btn-outline-light">Passengers</a>
                    <a href="<c:url value="/timetable"/>" class="btn btn-outline-light">Timetable</a>
                </div>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="btn-group">
                    <a href="/myaccount/${pageContext.request.userPrincipal.name}"
                       class="btn btn-outline-light">My Account</a>
                    <a href="/myaccount/${pageContext.request.userPrincipal.name}/tickets"
                       class="btn btn-outline-light">My Tickets</a>
                    <a href="<c:url value="/timetable"/>" class="btn btn-outline-light">Timetable</a>
                </div>
            </sec:authorize>
        </div>
        <div class="navbar-nav ml-auto">
            <sec:authorize access="!isAuthenticated()">
                <a href="<c:url value="/registration"/>" class="nav-item nav-link" style="color: white">Registration</a>
                <a href="<c:url value="/login"/>" class="btn btn-outline-light">Login</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="#" class="nav-item nav-link disabled" style="color: white">
                    Logged in as <strong>${pageContext.request.userPrincipal.name}</strong></a>
                <a href="<c:url value="/logout"/>" class="btn btn-outline-light">Logout</a>
            </sec:authorize>
        </div>
    </div>
</nav>

<div style="height: 100px"></div>

<div class="container">
    <div class="jumbotron">
        <div class="text-center">
            <h2>Oops! It seems there are no trains available for this date.</h2>
        </div>
        <div style="height: 50px"></div>
        <div class="text-center">
            <h3>Please try another date or destination.</h3>
        </div>
        <div style="height: 50px"></div>
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/"
               class="btn btn-warning" role="button">Go Back</a>
        </div>
    </div>
</div>

<div style="height: 100px"></div>


<%@ include file="common/footer.jsp" %>