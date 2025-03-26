<%@ page import="javax.servlet.http.Cookie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Cookie[] cookies = request.getCookies();
    String savedUsername = null;

    if (cookies != null) {
        for (Cookie c : cookies) {
            if (c.getName().equals("username")) {
                savedUsername = c.getValue();
                break;
            }
        }
    }

    if (savedUsername != null) {
        response.sendRedirect("dashboard.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="static/styles.css">
</head>
<body>
    <div class="container">
        <h2>User Login</h2>
        <% String errorMessage = request.getParameter("error"); %>
        <% if (errorMessage != null) { %>
            <p class="error">Invalid Username or Password!</p>
        <% } %>
        <form action="LoginServlet" method="post">
            <label>Username:</label>
            <input type="text" name="username" required>
            <label>Password:</label>
            <input type="password" name="password" required>
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>
