<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    HttpSession sessionObj = request.getSession(false);
    String username = null;

    // Check cookies first
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                username = cookie.getValue();
                break;
            }
        }
    }

    // If not found in cookies, check session
    if (username == null && sessionObj != null) {
        username = (String) sessionObj.getAttribute("user");
    }

    // Redirect to login if username is still null
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="static/styles.css">
</head>
<body>
    <div class="container">
        <h2>Welcome, <%= username %>!</h2>
        <a href="LogoutServlet">Logout</a>
    </div>
</body>
</html>
