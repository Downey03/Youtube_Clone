<!DOCTYPE html>
<html>
<head>
    <div class="java">
        <%@ page language="java" %>
        <%@ page contentType="text/html; charset=UTF-8" %>
    </div>
    <meta name="google-signin-client_id" content="549185040544-b01q4fgnmobi5e4h40a6n0m6ptr93akp.apps.googleusercontent.com">
    <script src="https://accounts.google.com/gsi/client" async defer></script>
   <link rel="stylesheet" href="index-styles.css">
    <link rel="stylesheet" href="global-styles.css"> 
</head>
<body>
    <div class="signup-container">
        <div class="main-container" id="main-container">
            <div class="back">
                 <a href="./index.jsp">back<img  src="./resources/images/left-arrow.png" class="left-arrow" alt=""><span>back</span></a>
            </div>
            <div class="signup-form">
                <form action="SignUpController" method="POST">
                    <label for="name">Name</label> <br>
                    <input type="text" name="name" required id="name"> <br>
                    <label for="email">E-mail</label> <br>
                    <input type="email" name="userEmail" required id="email"> <br>
                    <label for="password">Password</label> <br>
                    <input type="password" name="password" required id="password">
                    <p id="password-toggle" onclick="togglePassword()">show</p>
                    <input type="submit" id="submit" >
                </form>
            </div>
            <div>
                <div class="hr-content">
                    <hr><span>OR</span><hr>
                </div>
                <div id="my-signin2"></div>
            </div>
        </div>
    </div>
    <script>
        function onSuccess(googleUser) {
          console.log('Logged in as: ' + googleUser.getBasicProfile().getName());
        }
        function onFailure(error) {
          console.log(error);
        }
        function renderButton() {
          gapi.signin2.render('my-signin2', {
            'scope': 'profile email',
            'width': 240,
            'height': 40,
            'longtitle': true,
            'theme': 'dark',
            'onsuccess': onSuccess,
            'onfailure': onFailure
          });
        }
      </script>
    
      <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

    <script src="index-script.js"></script>
</body>
</html>