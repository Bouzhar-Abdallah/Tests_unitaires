<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="../includes/header.jsp" %>
<div class="ctnr">

    <h1>List Employes</h1>
    <form class="form" method="GET" action="${pageContext.request.contextPath}/employe">
        <div class="ctnr-search">
            <div class="flex-input">
                <input class="text" type="text" name="action" value="search" hidden>
                <input type="text" name="query" placeholder="Search by Email" class="input-search">
            </div>
            <div class="flex-button">
                <button class="search-button" type="submit">Search</button>
            </div>
        </div>
    </form>
    <div class="add-c-e-button">
        <form action="${pageContext.request.contextPath}/view/employe/addemploye.jsp">
            <button class="button-add">Add Employe</button>
        </form>
    </div>
    <table class="show-table">
        <thead>
        <tr>
            <th>Employe Matricule</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Email:</th>
            <th>Date of Birth</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Date Recruitment:</th>

            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${employes}" var="employe">
            <tr>
                <td>${employe.matricule}</td>
                <td>${employe.nom}</td>
                <td>${employe.prenom}</td>
                <td>${employe.emailAdresse}</td>
                <td>${employe.dateN}</td>
                <td>${employe.tel}</td>
                <td>${employe.adress}</td>
                <td>${employe.dateRecrutement}</td>
                <td>
                    <button class="button-update" onclick="showUpdateAlert('${employe.matricule}')">Update</button>
                    <button class="button-delete" onclick="showDeleteAlert('${employe.matricule}')">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script>
    function showUpdateAlert(employeMatricule) {
        swal({
            title: "Update Employe?",
            text: "Do you want to update this employe?",
            icon: "info",
            buttons: true,
            dangerMode: false,
        })
            .then((willUpdate) => {
                if (willUpdate) {
                    window.location.href = "${pageContext.request.contextPath}/employe?action=edit&matricule=" + employeMatricule;
                }
            });
    }
</script>

<script>
    function showDeleteAlert(employeMatricule) {
        swal({
            title: "Delete Employe?",
            text: "This employe will be permanently deleted!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href = "${pageContext.request.contextPath}/employe?action=delete&matricule=" + employeMatricule;
                }
            });
    }
</script>
<% String successMessage = request.getParameter("success"); %>
<% if (successMessage != null && successMessage.equals("delete-success")) { %>
<script>
    swal({
        title: "Success!",
        text: "Client deleted successfully!",
        icon: "success",
    }).then(function (confirmed) {
        if (confirmed) {
            window.location.href = "${pageContext.request.contextPath}/employe";
        }
    });
</script>
<% } %>
<%@ include file="../includes/footer.jsp" %>

</body>
</html>
