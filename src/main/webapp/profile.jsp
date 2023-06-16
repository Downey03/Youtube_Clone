<html>
    <div class="java">
        <%@ page import="java.util.ArrayList" %>
        <% String userEmail = (String)request.getAttribute("userEmail");
        request.setAttribute("userEmail",userEmail);
        ArrayList<String> playLists = (ArrayList<String>)request.getAttribute("playLists");
        %>
    </div>
    <head>
        <link rel="stylesheet" href="global-styles.css">
        <link rel="stylesheet" href="profile.css">
    </head>
    <body>
       
        <div class="profile-nav">
            <form action="GoHomeController" method="POST" prevent>
                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                <input type="submit" id="home" value="Home">
            </form>
        </div>
            <div class="playlist-container">
                <div class="main-container">
                    <div class="java"><%if(playLists!=null){%></div>
                        <h3>List of Playlists :</h3>
                        <table>
                            <div class="java"><%for(String name : playLists){%></div>
                                <tr>
                                    <td>
                                        <form action=ShowPlayListItemsController method="POST">
                                            <input type="hidden" value="<%=name%>" name="playListName">
                                            <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                            <input type="submit" value="<%=name%>" id="playlist-name" >
                                        </form>

                                    </td>
                                    <td>
                                         <form action=DeletePlayListController method="POST">
                                             <input type="hidden" value="<%=name%>" name="playListName">
                                              <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                             <button type="submit">Delete Playlist</button>
                                        </form
                                    </td>
                                </tr>
                            <div class="java"><% } %></div>
                        <table>
                    <div class="java"><%}%></div>
                    <hr>
                    <div class="new-playlist">
                        <h3>Create A Playlist:</h3>
                        <form action=CreateNewPlayListController id="myForm" method="POST">
                            <input type="text"  onkeyup="checkValue()" id="new-playlist" placeholder="Enter Playlist name" name="playListName">
                            <input type="hidden" value="<%=userEmail%>" name="userEmail">
                            <input type="submit" id="create"value="Create">
                        </form>
                    </div>
                </div>
            </div>
            <script >

                  <%-- This script will check if the playlist name has more than 3 letters --%>
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
            </script>
            <script >
                  <%-- This script will not let enter key to submit form --%>
                let input = document.getElementById("new-playlist");
                      input.addEventListener('keydown', function(e) {
                        if (e.keyCode === 13) {
                          e.preventDefault();
                        }
                      });

            </script>
    </body>
</html>