<!DOCTYPE html>
<html>
<head>
    <div class="java">
        <%@ page language="java" %>
        <%@ page contentType="text/html; charset=UTF-8" %>
    </div>
    <meta name="google-signin-client_id" content="549185040544-b01q4fgnmobi5e4h40a6n0m6ptr93akp.apps.googleusercontent.com.apps.googleusercontent.com">
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <link rel="stylesheet" href="styles.css"> 
</head>
<body>
    <div class="signup-container">
        <div class="main-container" id="main-container">
            <div class="back">
                 <a href="./index.jsp"><img  src="./resources/images/left-arrow.png" class="left-arrow" alt=""><span>back</span></a>
            </div>
            <div class="signup-form">
                <form action="SignUpController" method="POST">
                    <label for="name">Name</label> <br>
                    <input type="text" name="name" id="name"> <br>
                    <label for="email">E-mail</label> <br>
                    <input type="email" name="userEmail" id="email"> <br>
                    <label for="password">Password</label> <br>
                    <input type="password" name="password" id="password">
                    <input type="submit" id="submit" >
                </form>
            </div>
        </div>
    </div>
</body>
</html>