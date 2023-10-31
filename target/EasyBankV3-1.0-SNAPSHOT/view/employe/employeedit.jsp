<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employe</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>
<body>


<%@ include file="../includes/header.jsp" %>


<c:if test="${not empty requestScope.error}">
    <div class="error-message">
        Error: ${requestScope.error}
    </div>
</c:if>
<div class="new_container">
    <h1>Edit Employe</h1>
    <form action="${request.contextPath}/employe?action=update" method="post">
        <input class="form-inputs" type="hidden" name="action" value="edit">
        <label for="matricule">Employe Matricule:</label>
        <input class="form-inputs" type="hidden" id="matricule" name="matricule" value="${employe.matricule}" readonly><br>
        <label for="nom">Last Name:</label>
        <input class="form-inputs" type="text" id="nom" name="nom" value="${employe.nom}" required><br>
        <label for="prenom">First Name:</label>
        <input class="form-inputs" type="text" id="prenom" name="prenom" value="${employe.prenom}" required><br>
        <label for="emailadresse">Email:</label>
        <input class="form-inputs" type="email" id="emailadresse" name="emailadresse" value="${employe.emailAdresse}" required>
        <label for="dateN">Date of Birth (YYYY-MM-DD):</label>
        <input class="form-inputs" type="date" id="dateN" name="dateN" value="${employe.dateN}" required><br>
        <label for="tel">Phone:</label>
        <input class="form-inputs" type="text" id="tel" name="tel" value="${employe.tel}" required><br>
        <label for="daterecrutement">date Recrutement</label>
        <input class="form-inputs" type="date" id="dateRecrutement" name="dateRecrutement" value="${employe.dateRecrutement}" required><br>
        <label for="adress">Address:</label>
        <input class="form-inputs" type="text" id="adress" name="adress" value="${employe.adress}" required><br>
        <button type="submit" class="submit-button">Update Employe</button>


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
            window.location.href = "${pageContext.request.contextPath}/employe?action=list";
        }
    });
</script>
<% } %>

</body>
</html>
