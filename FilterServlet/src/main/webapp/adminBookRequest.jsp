<%@ page import="java.util.*, javax.servlet.http.*, java.math.BigDecimal" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Book Request</title>
    <link rel="stylesheet" href="static/login.css">
</head>
<body>
    <div class="container">
        <h2>Admin - Book Requests</h2>

        <div class="book-table">
            <table>
                <thead>
                    <tr>
                        <% 
                            List<Map<String, Object>> bookList = (List<Map<String, Object>>) session.getAttribute("bookList");
                            if (bookList != null && !bookList.isEmpty()) {
                                Map<String, Object> firstRow = bookList.get(0);
                                for (String column : firstRow.keySet()) { 
                        %>
                            <th><%= column %></th>
                        <%  
                                }
                            } 
                        %>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (bookList != null) {
                            for (Map<String, Object> book : bookList) { 
                    %>
                        <tr>
                            <% 
                                for (Map.Entry<String, Object> entry : book.entrySet()) {  
                                    Object value = entry.getValue();
                                    String displayValue = (value instanceof BigDecimal) ? ((BigDecimal) value).toPlainString() : String.valueOf(value);
                            %>
                                <td><%= displayValue %></td>
                            <% } %>
                        </tr>
                    <% 
                            } 
                        } 
                    %>
                </tbody>
            </table>
        </div>

        <a href="login.jsp" class="button">Back to Login</a>
    </div>
</body>
</html>
