<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <c:if test="${empty train.name}">
        <title>Add Train</title>
    </c:if>
    <c:if test="${!empty train.name}">
        <title>Edit Train</title>
    </c:if>
         <!-- Bootstrap CSS (Cloudflare CDN) -->
           <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

          <!-- jQuery (Cloudflare CDN) -->
          <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.slim.min.js"></script>

          <!-- Bootstrap Bundle JS (Cloudflare CDN) -->
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

          <link href="${pageContext.request.contextPath}/resources/webapp/assets/favicon.ico" rel="icon" type="image/x-icon"/>

</head>

<body>
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
    <h2 class="form-heading text-center">Edit train data:</h2>
    <div class="jumbotron">
        <c:if test="${empty train.track.id || empty train.name || empty train.seats}">
            <c:url value="/trains/add" var="var"/>
        </c:if>
        <c:if test="${!empty train.track.id && !empty train.name && !empty train.seats}">
            <c:url value="/trains/edit" var="var"/>
        </c:if>
        <form:form action="${var}" modelAttribute="train" method="POST"
                   class="needs-validation col-sm-10 offset-1" novalidate="true">
            <c:if test="${!empty train.name}">
                <input type="hidden" name="id" value="${train.id}">
            </c:if>
            <spring:bind path="track.id">
                <div class="form-group col-sm-4 offset-sm-4">
                    <c:if test="${empty train.name}">
                        <label for="track">Track</label>
                        <form:input path="track.id" type="number" class="form-control"
                                    id="track" autofocus="true" min="1" max="9" required="true"/>
                    </c:if>
                    <c:if test="${!empty train.name}}">
                        <form:input path="track.id" type="hidden" class="form-control"
                                    id="track" value="${train.track.id}"/>
                    </c:if>
                    <div class="invalid-feedback">Please enter a number from 1 to 9.</div>
                </div>
            </spring:bind>
            <spring:bind path="name">
                <div class="form-group col-sm-4 offset-sm-4">
                    <label for="name">Name</label>
                    <c:if test="${empty train.name}">
                        <form:input path="name" type="text" class="form-control ${status.error ? 'is-invalid' : ''}"
                                    id="name" pattern="^[a-zA-Z]+ [MDCLXVI]+$" required="true"/>
                    </c:if>
                    <c:if test="${!empty train.name}">
                        <form:input path="name" type="text" class="form-control ${status.error ? 'is-invalid' : ''}"
                                    id="name" value="${train.name}" autofocus="true"
                                    pattern="^[a-zA-Z]+ [MDCLXVI]+$" required="true"/>
                    </c:if>
                    <div class="invalid-feedback">
                        <c:set var="nameError"><form:errors path="name"/></c:set>
                        <c:if test="${!empty nameError}">${nameError}</c:if>
                        <c:if test="${empty nameError}">
                            Train name should consist of the letters and a number separated by space.
                        </c:if>
                    </div>
                </div>
            </spring:bind>
            <spring:bind path="seats">
                <div class="form-group col-sm-4 offset-sm-4">
                    <label for="seats">Seats</label>
                    <c:if test="${empty train.seats}">
                        <form:input path="seats" type="number" class="form-control"
                                    id="seats" min="10" max="50" required="true"/>
                    </c:if>
                    <c:if test="${!empty train.seats}">
                        <form:input path="seats" type="number" class="form-control"
                                    id="seats" value="${train.seats}" min="10" max="50" required="true"/>
                    </c:if>
                    <div class="invalid-feedback">Please enter a number from 10 to 50.</div>
                </div>
            </spring:bind>
            <div style="height: 30px"></div>
            <div class="text-center">
                <button class="btn btn-outline-info btn-center" type="submit">Submit and proceed to Schedule
                </button>
            </div>
        </form:form>
    </div>
</div>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            let forms = document.getElementsByClassName('needs-validation');
            let validation = Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>

<%@ include file="common/footer.jsp" %>