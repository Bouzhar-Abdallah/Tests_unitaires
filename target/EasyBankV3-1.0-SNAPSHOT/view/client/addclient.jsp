<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Client</title>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</head>
<body>



<%@ include file="../includes/header.jsp" %>

<div class="new_container">
    <h1>Add Client</h1>
    <form action="${request.contextPath}/client?action=add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="code">Client Code:</label>
        <input class="form-inputs" type="text" id="code" name="code" required><br>
        <label for="nom">Last Name:</label>
        <input class="form-inputs" type="text" id="nom" name="nom" required><br>
        <label for="prenom">First Name:</label>
        <input class="form-inputs" type="text" id="prenom" name="prenom" required><br>
        <label for="dateN">Date of Birth (YYYY-MM-DD):</label>
        <input class="form-inputs" type="date" id="dateN" name="dateN" required><br>
        <label for="tel">Phone:</label>
        <input class="form-inputs" type="text" id="tel" name="tel" required><br>
        <label for="adress">Address:</label>
        <input class="form-inputs" type="text" id="adress" name="adress" required><br>
        <button type="submit" class="submit-button">Add Client</button>
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
<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
<script>
    swal({
        title: "Error!",
        text: "<%= errorMessage %>",
        icon: "error",
});
</script>
<% } %>

</body>
</html>
