
<%@ include file="../includes/header.jsp" %>



<c:if test="${not empty requestScope.error}">
    <div class="error-message">
        Error: ${requestScope.error}
    </div>
</c:if>
<div class="new_container">
    <h1>Edit Client</h1>
    <form action="${request.contextPath}/client?action=update" method="post">
        <input class="form-inputs" type="hidden" name="action" value="edit">
        <label for="code">Client Code:</label>
        <input class="form-inputs" type="hidden" id="code" name="code" value="${client.code}" readonly><br>
        <label for="nom">Last Name:</label>
        <input class="form-inputs" type="text" id="nom" name="nom" value="${client.nom}" required><br>
        <label for="prenom">First Name:</label>
        <input class="form-inputs" type="text" id="prenom" name="prenom" value="${client.prenom}" required><br>
        <label for="dateN">Date of Birth (YYYY-MM-DD):</label>
        <input class="form-inputs" type="date" id="dateN" name="dateN" value="${client.dateN}" required><br>
        <label for="tel">Phone:</label>
        <input class="form-inputs" type="text" id="tel" name="tel" value="${client.tel}" required><br>
        <label for="adress">Address:</label>
        <input class="form-inputs" type="text" id="adress" name="adress" value="${client.adress}" required><br>
        <button type="submit" class="submit-button">Update Client</button>

    </form>
</div>

<%@ include file="../includes/footer.jsp" %>
<% String successMessage = (String) request.getAttribute("successMessage"); %>
<% if (successMessage != null) { %>
<script>
    swal({
        title: "Success!",
        text: "<%= successMessage %>",
        icon: "success",
    }).then(function (confirmed) {
        if (confirmed) {
            window.location.href = "${pageContext.request.contextPath}/client?action=list";
        }
    });
</script>
<% } %>

</body>
</html>
