<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Book</title>
    <link rel="stylesheet" href="static/styles1.css">
</head>
<body>
    <div class="container">
        <h2>Search for a Book</h2>
        <form action="BookDetails" method="get">
            <input type="number" name="bookid" placeholder="Enter Book ID" required>
            <button type="submit">Search</button>
        </form>
    </div>
</body>
</html>
