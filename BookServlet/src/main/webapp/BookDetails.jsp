<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            text-align: center; 
            margin-top: 50px;
        }
        .container {
            width: 400px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .not-found {
            color: red;
            font-weight: bold;
        }
        .details {
            text-align: left;
            margin-bottom: 20px;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
    </style>
</head>
<body>
    <div class="container">
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
        <br>
        <a href="BookIdForm.jsp">Search Another Book</a>
    </div>
</body>
</html>
