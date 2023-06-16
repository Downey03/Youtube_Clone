<html>
    <head>
         <link rel="stylesheet" href="global-styles.css">
         <link rel="stylesheet" href="sign-up-exception.css">
    </head>
    <body>
    <% String msg = (String) request.getAttribute("msg"); %>
        <div class="exception-container">
            <div class="main-container">
                <div class="back">
                     <a href="./sign-up.jsp">Back</a>
                </div>
                <div class="main-content">
                    <h6> <%=msg%> </h6>
                </div>
            </div>
        </div>
    </body>
</html>