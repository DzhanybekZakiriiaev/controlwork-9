<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Logout</title>
    <link rel="stylesheet" type="text/css" href="../main.css">
</head>
<body>
<div class="container">
    <h1>Logout</h1>
    <form method="POST" action="/logout">
        <input type="hidden" name="_csrf" value="${csrfToken}">
        <p>Are you sure you want to logout?</p>
        <button type="submit">Logout</button>
    </form>
</div>
</body>
</html>
