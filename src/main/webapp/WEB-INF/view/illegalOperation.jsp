<%@ include file="common/header.jsp" %>
<div style="height: 100px"></div>

<div class="container">
    <h2 class="text-center">Something went wrong!</h2>
    <div class="jumbotron">
        <div class="text-center">
            <div style="height: 50px">
                <h4>Whatever you did on the previous page caused the program to crash.</h4>
            </div>
            <div style="height: 50px">
                <h5>Please check your input.</h5>
            </div>
            <!--
    Failed URL: ${url}
    Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    ${ste}
    </c:forEach>
  -->
            <div class="text-center">
                <a href="${pageContext.request.contextPath}/" class="btn btn-warning" role="button">Back to Main</a>
            </div>
        </div>
        <div style="height: 50px"></div>
        <div class="text-center">
            <img src="${pageContext.request.contextPath}/assets/error.jpg" class="img-fluid" alt="Warning">
        </div>
    </div>
</div>


    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

<%@ include file="common/footer.jsp" %>