<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/header.jsp" %>

<div class="ctnr">
    <h1>List Demande Credits</h1>

    <div class="add-c-e-button">
        <a href="${pageContext.request.contextPath}/demandeCredit?action=show" class="button-add">Add Demande Credit</a>
    </div>


    <table class="show-table">
        <thead>
        <tr>
            <th>Numero</th>
            <th>Date</th>
            <th>Montant</th>
            <th>Duree</th>
            <th>Remarque</th>
            <th>Status</th>
            <th>Simulation</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${DemendeCredits}" var="demendeCredit">
            <tr>
                <td>${demendeCredit.numero}</td>
                <td>${demendeCredit.date}</td>
                <td>${demendeCredit.montant}</td>
                <td>${demendeCredit.duree}</td>
                <td>${demendeCredit.remarque}</td>
                <td>${demendeCredit.status}</td>
                <td>${demendeCredit.simulation}</td>

                <td>
                    <button class="button-update" onclick="showUpdateAlert('${demendeCredit.numero}')">Update</button>
                    <button class="button-delete" onclick="showDeleteAlert('${demendeCredit.numero}')">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    function showUpdateAlert(demendeCreditNumero) {
        swal({
            title: "Update Demande Credit?",
            text: "Do you want to update this Demande Credit?",
            icon: "info",
            buttons: true,
            dangerMode: false,
        })
            .then((willUpdate) => {
                if (willUpdate) {
                    window.location.href = "${pageContext.request.contextPath}/demeadeCredit?action=edit&numero=" + demendeCreditNumero;
                }
            });
    }
</script>

<script>
    function showDeleteAlert(demendeCreditNumero) {
        swal({
            title: "Delete Demande Credit?",
            text: "This Demande Credit will be permanently deleted!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href = "${pageContext.request.contextPath}/demandeCredit?action=delete&numero=" + demendeCreditNumero;
                }
            });
    }
</script>

<script>
    function showUpdateAlert(demendeCreditNumero) {
        swal({
            title: "Update Demende?",
            text: "Do you want to update this demende?",
            icon: "info",
            buttons: true,
            dangerMode: false,
        })
            .then((willUpdate) => {
                if (willUpdate) {
                    window.location.href = "${pageContext.request.contextPath}/demandeCredit?action=edit&numero=" + demendeCreditNumero;
                }
            });
    }
</script>

<script>
    function showDeleteAlert(demendeCreditNumero) {
        swal({
            title: "Delete Demende?",
            text: "This demende will be permanently deleted!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href = "${pageContext.request.contextPath}/demandeCredit?action=delete&numero=" + demendeCreditNumero;
                }
            });
    }
</script>

<% String successMessage = request.getParameter("success"); %>
<% if (successMessage != null && successMessage.equals("delete-success")) { %>
<script>
    swal({
        title: "Success!",
        text: "Demende deleted successfully!",
        icon: "success",
    }).then(function (confirmed) {
        if (confirmed) {
            window.location.href = "${pageContext.request.contextPath}/demandeCredit";
        }
    });
</script>
<% } %>

</body>
</html>
