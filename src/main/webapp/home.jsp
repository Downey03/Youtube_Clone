<html lang="en">
<div class="java">
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="DTO.VideoDTO" %>
    <%@ page import="DTO.UserDTO" %>
    <%@ page import="java.lang.System" %>
    <%@ page import="java.util.Arrays" %>
    <%  ArrayList<VideoDTO> videoList = (ArrayList<VideoDTO>) request.getAttribute("videoList");
        ArrayList<String> playLists = (ArrayList<String>) request.getAttribute("playLists");
        String userEmail = (String) request.getAttribute("userEmail");
        request.setAttribute("userEmail",userEmail); %>
</div>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="home.css">
    <link rel="stylesheet" href="global-styles.css">
</head>
<body>
    
    <div class="home-nav">
        <div class="profile">
                <form action="ProfileController" method="POST">
                    <input type=hidden value="<%=userEmail%>" name="userEmail">
                    <input type=Submit value="Profile">
                </form>
        </div>
        <div class="search-bar">
            <form action="SearchController" method="POST">
                <input type="text" name="searchKeyword" id="search" placeholder="search a song">
                <input type="hidden" name="userEmail" value="<%=userEmail%>" >
                <input type="submit" value="search" id="submit-btn">
            </form>
        </div>
        <div class="logout">
                <a href="LogoutController">Logout</a>
        </div>
    </div>
    <div class="home-container">
        <div class="java"><% if(playLists!=null && playLists.size()>0) { %></div>
            <div class="playlist-result">
                <div>
                    <h3>Select a playlist : </h3>
                    <div class="java">  <% for(String name : playLists) { %> </div>
                        <div>
                            <form action=ShowPlayListItemsController method="POST" >
                                <input type="hidden" value="<%=name%>"  name="playListName">
                                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                <input type="submit" id="playlist-name" value="<%=name%>">
                            </form>
                        </div>
                    <div class="java">  <% } %> </div>
                </div>
            </div>
        <div class="java"> <% } else{ %>
                <% if(videoList!=null && videoList.size()>0) { %>
        </div>
            <div class="result-container">
                <table>
                    <thead>
                        <th>Title</th>
                    </thead>
                    <tbody>
                        <div class="java"><% for(VideoDTO video : videoList)  {   %>  </div>
                            <tr>
                                <form action="GetAndAddToPlayListController" method="POST" >
                                    <input type="hidden" value="<%=video.getTitle()%>" name="videoTitle">
                                    <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                    <td><a href="<%=video.getTitle()%>"><%=video.getTitle()%></a></td>
                                    <td><button type="submit" >Add To PlayList</button></td>
                                </form>
                            </tr>
                        <div class="java"> <% } %>   </div>
                    </tbody>
                </table>
            </div>
            <div class="java"> <% } else{ %>  </div>
                <div class="no-result">
                    <p>No video Found</p>
                </div>
            <div class="java">  <% } %>
                  <% } %>
            </div>
    </div>
    
</body>
</html>