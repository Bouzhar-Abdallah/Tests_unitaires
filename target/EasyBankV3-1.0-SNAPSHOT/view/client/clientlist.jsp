<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../includes/header.jsp" %>

<div class="ctnr">
    <h1>List Clients</h1>
    <form method="GET" action="${pageContext.request.contextPath}/client">
        <div class="ctnr-search">
            <div class="flex-input">
                <input type="text" name="action" value="search" hidden>
                <input type="text" name="query" placeholder="Search by Code" class="input-search">
            </div>
            <div class="flex-button">
                <button class="search-button" type="submit">Search</button>
            </div>
        </div>
    </form>
    <div class="add-c-e-button">
        <form action="${pageContext.request.contextPath}/view/client/addclient.jsp">
            <button class="button-add">Add Client</button>
        </form>
    </div>
    <table class="show-table">
        <thead>
        <tr>
            <th>Client Code</th>
            <th>Last Name</th>
            <th>First Name</th>
            <th>Date of Birth</th>
            <th>Phone</th>
            <th>Address</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${clients}" var="client">
            <tr>
                <td>${client.code}</td>
                <td>${client.nom}</td>
                <td>${client.prenom}</td>
                <td>${client.dateN}</td>
                <td>${client.tel}</td>
                <td>${client.adress}</td>
                <td>
                    <button class="button-update" onclick="showUpdateAlert('${client.code}')">Update</button>
                    <button class="button-delete" onclick="showDeleteAlert('${client.code}')">Delete</button>
                </td>
            </tr>
        </c:forEach>

        </tbody>
    </table>
</div>
<script>
    function showUpdateAlert(clientCode) {
        swal({
            title: "Update Client?",
            text: "Do you want to update this client?",
            icon: "info",
            buttons: true,
            dangerMode: false,
        })
            .then((willUpdate) => {
                if (willUpdate) {
                    window.location.href = "${pageContext.request.contextPath}/client?action=edit&code=" + clientCode;
                }
            });
    }
</script>

<script>
    function showDeleteAlert(clientCode) {
        swal({
            title: "Delete Client?",
            text: "This client will be permanently deleted!",
            icon: "warning",
            buttons: true,
            dangerMode: true,
        })
            .then((willDelete) => {
                if (willDelete) {
                    window.location.href = "${pageContext.request.contextPath}/client?action=delete&code=" + clientCode;
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
            window.location.href = "${pageContext.request.contextPath}/client";
        }
    });
</script>
<% } %>

<%@ include file="../includes/footer.jsp" %>
</body>
</html>
