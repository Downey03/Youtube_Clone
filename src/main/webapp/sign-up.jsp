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
                 <a href="./index.jsp"><span>back</span></a>
            </div>
            <div class="pass-check"><h6 id="pass-check"></h6></div> 
            <div class="conf-pass"><h6 id="conf-pass"></h6></div>
            <div class="signup-form">
                <form action="SignUpController" method="POST">
                    <label for="name">Name</label> <br>
                    <input type="text" onkeyup="submitValidate()" name="name" required id="name"> <br>
                    <label for="email">E-mail</label> <br>
                    <input type="email" onkeyup="submitValidate()" name="userEmail" required id="email"> <br>
                    <label for="password">Password</label> <br>
                    <input type="password" name="password" onkeyup="check();confirmPassword();submitValidate()" required id="password">
                    <p id="password-toggle" onclick="togglePassword()">show</p>
                    <label for="confirm-password">Confirm Password</label> <br>
                    <input type="password" name="confirm-password" onkeyup="confirmPassword();submitValidate();" required id="confirm-password">
                    <button type="submit" class="submit" id="submit" disabled >create</button>
                </form>
            </div>

            <div class="java">
                <div>
                    <div class="hr-content">
                        <hr><span>OR</span><hr>
                    </div>
                    <div id="my-signin2"></div>
                </div>
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
    <script>
   
      
      let submit = document.getElementById("submit")
      function check(){
        let passWarn = document.getElementById("pass-check")  
        let confPass = document.getElementById("conf-pass")
        let password = document.getElementById("password").value.toString()
        document.getElementById("password").value.oldvalue = password
        if(password.length<8) passWarn.innerText = "Password size must be more than 8"
        else if(!password.match(/[a-zA-z]/g)) passWarn.innerText = "Password must have a letter"
        else if(!password.match(/[0-9]/g)) passWarn.innerText = "Password must have a number"
        else passWarn.innerText = ""
      }
   
      function confirmPassword(){
        let passWarn = document.getElementById("pass-check")  
        let confPass = document.getElementById("conf-pass")
        let password = document.getElementById("password").value.toString()
        let confPassword = document.getElementById("confirm-password").value.toString()

        document.getElementById("password").value.oldvalue = password;
        document.getElementById("confirm-password").value.oldvalue = confPassword;

        if(password!=confPassword) confPass.innerText = "Password and Confirm Password must be same"
        else confPass.innerText = ""
      }
   
      function submitValidate(){
        let password = document.getElementById("password").value.toString()
        document.getElementById("password").value.oldvalue = password
        let passWarn = document.getElementById("pass-check")
        let confPass = document.getElementById("conf-pass")
        if(password!="" && passWarn.outerText=="" && confPass.outerText=="") submit.disabled = false;
        else submit.disabled = true;
      }
   </script>
      <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>

    <script src="index-script.js"></script>
</body>
</html>