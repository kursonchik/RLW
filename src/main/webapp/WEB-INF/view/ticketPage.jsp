<%@ include file="common/header.jsp" %>

<nav class="navbar navbar-expand-md navbar-light sticky-top" style="background-color: #6897BB">
    <a href="<c:url value="/"/>" class="navbar-brand" style="color: white">RLW</a>
    <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarCollapse">
        <div class="navbar-nav">
            <a href="<c:url value="/"/>" class="nav-item nav-link active" style="color: white">Home</a>
            <div class="btn-group">
                <a href="/myaccount/${pageContext.request.userPrincipal.name}"
                   class="btn btn-outline-light">My Account</a>
                <a href="/myaccount/${pageContext.request.userPrincipal.name}/tickets" class="btn btn-outline-light">My
                    Tickets</a>
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
    <h2 class="text-center">Please check your travel details:</h2>
    <div class="jumbotron">
        <div class="text-center">
            <p>Date: <strong>${ticketForm.date}</strong></p>
            <p>From <strong>${ticketForm.departureStation}</strong> at <strong>${ticketForm.departureTime}</strong></p>
            <p>To <strong>${ticketForm.arrivalStation}</strong> at <strong>${ticketForm.arrivalTime}</strong></p>
            <c:if test="${trainsList.size() == 1}">
                <p>Train: <strong>${trainsList.get(0).name}</strong></p>
            </c:if>
            <c:if test="${trainsList.size() > 1}">
                <p>Trains:
                    <c:forEach var="train" items="${trainsList}">
                        <strong>${train.name} </strong>
                    </c:forEach>
                </p>
            </c:if>
            <c:if test="${!empty passenger.id}">
                <p>Passenger: <strong>${passenger.firstName} ${passenger.lastName}</strong></p>
                <p>Birth Date: <strong>${passenger.birthDate}</strong>
                </p>
                <p>Passport Number: <strong>${passenger.passportNumber}</strong></p>

            </c:if>
            <span style="color: red">${message}</span>
            <div>
                <a href="/myaccount/${pageContext.request.userPrincipal.name}"
                   class="btn btn-secondary" role="button">Edit personal data</a>
            </div>
        </div>
        <div style="height: 30px"></div>
        <div class="text-center">
            <a href="/ticket/buy"
               class="btn btn-success" role="button">Confirm and Buy</a>
        </div>
    </div>
</div>

<div style="height: 100px"></div>


<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<%@ include file="common/footer.jsp" %>