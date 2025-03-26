<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Find Book</title>
    <link rel="stylesheet" type="text/css" href="static/login.css">
</head>
<body>

    <div class="login-container">
        <h2>Find a Book</h2>

        <% 
            String errorMessage = (String) request.getAttribute("errorMessage");
            if (errorMessage != null) { 
        %>
            <p class="error-message"><%= errorMessage %></p>
        <% } %>

        <form action="UserBookServlet" method="get">
            <input type="number" name="bookid" placeholder="Enter Book ID" required>
            <button type="submit">Search</button>
        </form>
    </div>

</body>
</html>
