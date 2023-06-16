let createBtn = document.getElementById("create")
function checkValue(){
    let newPlayList = document.getElementById("new-playlist").value
    document.getElementById("new-playlist").value = newPlayList

    if(checkValidString(newPlayList)) createBtn.classList.add("display-block")
    else createBtn.classList.remove("display-block")
}

function checkValidString(str){
    if(str.length<3) return 0
    if(!str.match(/[A-Za-z]/g)) return 0
    return 1
}


