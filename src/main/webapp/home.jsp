
<%@ page import="java.util.ArrayList" %>
    <%@ page import="DTO.VideoDTO" %>
    <%@ page import="DTO.UserDTO" %>
    <%@ page import="java.lang.System" %>
    <%@ page import="java.util.Arrays" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="home.css">
</head>
<body>

    <%ArrayList<VideoDTO> videoList = (ArrayList<VideoDTO>) request.getAttribute("videoList");
        ArrayList<String> playLists = (ArrayList<String>) request.getAttribute("playLists");        %>
        <%  String userEmail = (String) request.getAttribute("userEmail"); %>
        <% request.setAttribute("userEmail",userEmail); %>
    <div class="home-nav">
        <div class="profile">
                <form action="ProfileController" method="POST">
                    <input type=hidden value="<%=userEmail%>" name="userEmail">
                    <input type=Submit value="Profile">
                </form>
        </div>
        <div class="search-bar">
            <form action="SearchController" method="POST">
                <input type="text" name="searchKeyword" placeholder="search a song">
                <input type="hidden" name="userEmail" value="<%=userEmail%>" >
                <input type="submit" id="submit-btn">
            </form>
        </div>
        <div class="logout">
                <a href="LogoutController">Logout</a>
        </div>
    </div>
    <div class="home-container">

        <% if(playLists!=null && playLists.size()>0) { %>
            <div class="playlist-result">
                <div>
                    <% for(String name : playLists) { %>
                        <div>
                            <form action=ShowPlayListItemsController method="POST" >
                                <input type="hidden" value="<%=name%>" name="playListName">
                                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                <input type="submit" value="<%=name%>">
                            </form
                        </div>
                    <% } %>
                <div>
            </div>

        <% } else{ %>
                <% if(videoList.size()>0) { %>
                    <div class="result-container">
                        <table>
                            <thead>
                                <td>Title</td>
                                <td>Link</td>
                            </thead>
                            <tbody>
                                 <% for(VideoDTO video : videoList)  {   %>
                                        <tr>
                                            <form action="GetAndAddToPlayListController" method="POST" >
                                                <input type="hidden" value="<%=video.getTitle()%>" name="videoTitle">
                                                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                                <td><% out.write(video.getTitle());%></td>
                                                <td><a href="<% out.write(video.getLink());%>">Link</a></td>
                                                <td><button type="submit" >Add To PlayList</button></td>
                                            </form>
                                        </tr>
                                 <% } %>
                            </tbody>
                        </table>
                    </div>
                <% } else{ %>
                        <div>
                            <p>No video Found</p>
                        </div>
                    <% } %>

        <% } %>
    </div>
    
</body>
</html>