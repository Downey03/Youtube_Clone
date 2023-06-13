<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>error</title>
    <link rel="stylesheet" href="exception.css">
</head>
<body>
    <div class="error-container">
        <div class="main-container">
            <div class="java">
                <% String msg = (String) request.getAttribute("msg");
                    if(msg!=null) out.println(msg); %>

            </div>
            <a href="./sign-up.jsp">login</a>
        </div>
    </div>
</body>
</html>