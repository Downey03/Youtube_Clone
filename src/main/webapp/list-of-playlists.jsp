<html>
<div class="java">
    <%@ page import="java.util.ArrayList"%>
    <% ArrayList<String> playLists = (ArrayList<String>) request.getAttribute("playLists"); %>
    <% String userEmail = (String)request.getAttribute("userEmail"); %>
    <% String videoTitle = (String)request.getAttribute("videoTitle"); %>
    <% request.setAttribute("userEmail",userEmail);
        System.out.println(videoTitle);
    %>
</div>
    <head>
        <link rel="stylesheet" href="list-of-playlists.css">
        <link rel="stylesheet" href="global-styles.css">
    </head>
    <body>
        <div class="profile-nav">
            <form action="GoHomeController" method="POST">
                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                <input type="submit" id="home" value="Home">
            </form>
        </div>
        <div class="playlist-container">
            <div class="main-container">
                <div class="java"><%if(playLists!=null){%></div>
                    <h3>Select A PlayList :</h3>
                    <table>
                        <div class="java"><%for(String name : playLists){%></div>
                                    <tr>
                                        <td>
                                             <form action=AddToPlayListController method="POST">
                                                 <input type="hidden" value="<%=name%>" name="playListName">
                                                  <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                                 <input type="hidden" id="video-title" value="<%=videoTitle%>" name="videoTitle">
                                                 <button type="submit"><%=name%></button>
                                            </form
                                        </td>
                                    </tr>
                                <div class="java"><% } %></div>
                            <table>
                     <div class="java"><%}%></div>

                     <hr>
                     <h3>Create A New Playlist :</h3>
                    <div class="new-playlist">
                        <form action=CreatePlayListAndAddVideoController method="POST">
                            <input type="text" onkeyup="checkValue()" id="new-playlist" placeholder="Enter Playlist name" name="playListName">
                             <input type="hidden"  value="<%=userEmail%>" name="userEmail">
                            <input type="hidden" value="<%=videoTitle%>" name="videoTitle">
                            <input type="submit" id="create" value="Create">
                        </form>
                    </div>

            </div>
        </div>
        <script >
            <%-- This script will check if the playList name has more than 3 letters --%>
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

        <script>
             <%-- This script will not let enter key to submit form --%>
                const input = document.getElementById("new-playlist");
                      input.addEventListener('keydown', function(e) {
                        if (e.keyCode === 13) {
                          e.preventDefault();
                        }
                      });
        </script>
    </body>
</html>