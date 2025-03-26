<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
    <link rel="stylesheet" type="text/css" href="static/login.css">
</head>
<body>
    <div class="login-container">
        <h2>Book Details</h2>
        
        <%
            boolean isFound = (request.getAttribute("isFound") != null) ? (boolean) request.getAttribute("isFound") : false;
            if (!isFound) {
        %>
            <p class="not-found">Book Not Found</p>
        <%
            } else {
                Map<String, Object> book = (Map<String, Object>) request.getAttribute("book_info");
        %>
            <div class="details">
                <% 
                    for (Map.Entry<String, Object> entry : book.entrySet()) { 
                        String columnName = entry.getKey(); 
                        Object columnValue = entry.getValue();
                %>
                    <p><strong><%= columnName %>:</strong> <%= columnValue %></p>
                <% } %>
            </div>
        <%
            }
        %>
        
        <a href="userBookRequest.jsp"><button>Search Another</button></a>
    </div>
</body>
</html>
