<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Book</title>
    <style>
        body { 
            font-family: Arial, sans-serif; 
            text-align: center; 
            margin-top: 100px;
        }
        .container {
            width: 300px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        input, button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
        }
    </style>
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
