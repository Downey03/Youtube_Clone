let showPass = document.getElementById("password-toggle")
let password = document.getElementById("password")

function togglePassword(){
    if(password.type=="password"){
        password.type="text"
        showPass.innerText = "hide"
    }
    else{
        password.type="password"
        showPass.innerText = "show"
    }
}