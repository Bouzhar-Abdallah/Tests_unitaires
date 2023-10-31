
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Easy Bank</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/styles.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/2.1.2/sweetalert.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>




</head>
<body>



<nav class="navbar">
    <div class="container2">
        <div class="navbar-brand">
                <span class="navbar-logo">
                    <a href="../../index.jsp">
                        <img src="${pageContext.request.contextPath}/resources/images/E-removebg-preview.png" style="height: 4.3rem;">
                    </a>
                </span>
            <span class="navbar-caption-wrap">
                    <a class="text-logo" href="../../index.jsp">EasyBank</a>
                </span>
        </div>
        <button class="navbar-toggler">
            <div class="hamburger">
                <span></span>
                <span></span>
                <span></span>
                <span></span>
            </div>
        </button>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link link display-4" href="../../index.jsp">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link link display-4" href="${pageContext.request.contextPath}../../client">Clients</a>
            </li>
            <li class="nav-item">
                <a class="nav-link link display-4" href="${pageContext.request.contextPath}../../employe">Employes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link link display-4" href="${pageContext.request.contextPath}../../demandeCredit">Simulations</a>
            </li>
        </ul>
        <div class="navbar-buttons mbr-section-btn right-phase">
            <a class="text-logo" href="https://www.linkedin.com/in/mohamed-amine-majidi-399035252/">Contact Us</a>
        </div>
    </div>
</nav>
