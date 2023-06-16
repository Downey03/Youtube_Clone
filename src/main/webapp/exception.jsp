<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>error</title>
    <link rel="stylesheet" href="exception.css">
    <link rel="stylesheet" href="global-styles.css">
</head>
<body>
    <% String msg = (String) request.getAttribute("msg");
        String userEmail = (String) request.getAttribute("userEmail");
     %>
    <div class="exception-nav">
        <form action="GoHomeController" method="POST">
            <input type="hidden" value="<%=userEmail%>" name="userEmail">
            <input type="submit" id="home" value="Home">
        </form>
    </div>
    <div class="error-container">
        <div class="main-container">

              <h3><%  out.println(msg); %> </h3>

        </div>
    </div>
</body>
</html>