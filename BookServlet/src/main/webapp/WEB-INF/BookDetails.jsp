<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Book Details</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/static/styles.css">
</head>
<body>
    <div class="container">
        <h2>Book Details</h2>
        <table border="1">
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Price</th>
            </tr>
            <%
                List<Map<String, Object>> bookList = (List<Map<String, Object>>) request.getAttribute("bookList");
                for (Map<String, Object> book : bookList) {
            %>
            <tr>
                <td><%= book.get("bookId") %></td>
                <td><%= book.get("title") %></td>
                <td><%= book.get("author") %></td>
                <td><%= book.get("price") %></td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
