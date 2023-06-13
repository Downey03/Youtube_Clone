<!DOCTYPE html>
<html>
<head>
    <div class="java">
        <%@ page language="java" %>
        <%@ page contentType="text/html; charset=UTF-8" %>
    </div>
    <meta name="google-signin-client_id" content="549185040544-b01q4fgnmobi5e4h40a6n0m6ptr93akp.apps.googleusercontent.com">
    <script src="https://accounts.google.com/gsi/client" async defer></script>
    <link rel="stylesheet" href="./styles.css"> 
    <link rel="stylesheet" type="text/css" href="your_website_domain/css_root/flaticon.css" >
</head>
<body>

    <div class="login-container"><div class="main-container" id="main-container">
            <div class="back">
                 <a href="./index.jsp"><img  src="../resources/images/left-arrow.png" class="left-arrow" alt=""><span>back</span></a>
            </div>
            <img class="yt-icon" src="../resources/images/youtube.png" alt="Youtube Icon"> <br>
        
            <div class="login-content">
                <form action=LoginController method="POST">
                    <table class="login-table">
                        <tr>
                            <td>
                                <label for="userEmail">E-mail</label>
                            </td>
                            <td>
                                <input type="email" name="userEmail" >
                            </td>
                            <td rowspan="2">
                                <input type="submit">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label for="password">password</label>
                            </td>
                            <td>
                                <input type="password" name="password">
                            </td>
                        </tr>
                    </table>
                </form>
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

</body>
</html>