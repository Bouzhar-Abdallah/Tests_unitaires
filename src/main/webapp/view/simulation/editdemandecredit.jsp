<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/header.jsp" %>

<c:if test="${not empty requestScope.error}">
  <div class="error-message">
    Error: ${requestScope.error}
  </div>
</c:if>
<div class="new_container">
  <h1>Edit Demande de Crédit</h1>
  <form action="${request.contextPath}/demandeCredit?action=update" method="post">
    <input type="hidden" name="action" value="edit">
    <input type="hidden" name="numero" value="${demandeCredit.numero}" readonly>

    <label for="montant">Montant:</label>
    <input class="form-inputs" type="text" id="montant" name="montant" value="${demandeCredit.montant}" required><br>
    <label for="duree">Durée:</label>
    <input class="form-inputs" type="text" id="duree" name="duree" value="${demandeCredit.duree}" required><br>
    <label for="remarque">Remarque:</label>
    <input class="form-inputs" type="text" id="remarque" name="remarque" value="${demandeCredit.remarque}" required><br>
    <label for="status">Status:</label>
    <select class="form-inputs" id="status" name="status" required>
      <option value="EnAttante">EnAttante</option>
      <option value="Accepte" selected>Accepte</option>
      <option value="Refuse">Refuse</option>
    </select>
    <label for="agence_code">Agence Code:</label>
    <input class="form-inputs" type="text" id="agence_code" name="agence_code" value="${demandeCredit.agence.code}" required><br>
    <label for="employe_matricule">Employe Matricule:</label>
    <input class="form-inputs" type="text" id="employe_matricule" name="employe_matricule" value="${demandeCredit.employe.matricule}" required><br>
    <label for="client_code">Client Code:</label>
    <input class="form-inputs" type="text" id="client_code" name="client_code" value="${demandeCredit.client.code}" required><br>
    <label for="simulation">Simulation:</label>
    <input class="form-inputs" type="text" id="simulation" name="simulation" value="${demandeCredit.simulation}" required><br>
    <button type="submit" class="submit-button">Update Demande de Crédit</button>
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
      window.location.href = "${pageContext.request.contextPath}/demandeCredit?action=list";
    }
  });
</script>
<% } %>
