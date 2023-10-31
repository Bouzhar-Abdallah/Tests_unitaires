<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/list.css">
  <title>Welcome to EasyBank</title>
</head>
<body>
<nav class="navbar">
  <div class="container2">
    <div class="navbar-brand">
      <span class="navbar-logo">
        <a href="index.jsp">
          <img src="${pageContext.request.contextPath}/resources/images/E-removebg-preview.png" alt="EasyBank Logo">
        </a>
      </span>
      <span class="navbar-caption-wrap">
        <a class="text-logo" href="index.jsp">EasyBank</a>
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
        <a class="nav-link link display-4" href="index.jsp">Home</a>
      </li>
      <li class="nav-item">
        <a class="nav-link link display-4" href="client">Clients</a>
      </li>
      <li class="nav-item">
        <a class="nav-link link display-4" href="employe">Employees</a>
      </li>
      <li class="nav-item">
        <a class="nav-link link display-4" href="demandeCredit">Simulations</a>
      </li>
    </ul>
    <div class="navbar-buttons mbr-section-btn right-phase">
      <a class="text-logo" href="https://www.linkedin.com/in/mohamed-amine-majidi-399035252/">Contact Us</a>
    </div>
  </div>
</nav>

<div class="hero">
  <h1>Welcome to EasyBank</h1>
  <p>Manage your finances with ease and convenience.</p>
  <a class="btn" href="client">Explore</a>
</div>

<footer class="footer">
  <p>&copy; 2023 EasyBank. All rights reserved.</p>
</footer>
</body>
</html>
