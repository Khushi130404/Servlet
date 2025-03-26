<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Book Search</title>
    <style>
        body { font-family: Arial, sans-serif; background: linear-gradient(to right, #141e30, #243b55);
               display: flex; align-items: center; justify-content: center; height: 100vh; margin: 0; color: white; }
        .container { background: rgba(255, 255, 255, 0.1); padding: 30px; border-radius: 10px; 
                     box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.2); text-align: center; backdrop-filter: blur(10px); }
        input, button { padding: 10px; margin: 10px; border: none; font-size: 16px; }
        button { background-color: #ff9800; color: white; cursor: pointer; border-radius: 5px; }
        button:hover { background-color: #e68900; }
    </style>
</head>
<body>
<div class="container">
        <h1>Find Book Details</h1>
        <form action="BookDetails" method="GET">
            <input type="text" name="bookid" placeholder="Enter Book ID" required>
            <button type="submit">Search</button>
        </form>
    </div>
</body>
</html>