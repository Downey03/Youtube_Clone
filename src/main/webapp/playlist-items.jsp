<%@ page import="java.util.ArrayList" %>
<%@ page import="DTO.VideoDTO" %>
<html>
    <head>
    </head>
    <body>
        <%  String userEmail = (String)request.getAttribute("userEmail");
        ArrayList<VideoDTO> playListItems = (ArrayList<VideoDTO>) request.getAttribute("playListItems");
        request.setAttribute("userEmail",userEmail);
        String playListName = (String)request.getAttribute("playListName");
        %>
         <div class="profile-nav">
            <form action="GoHomeController" method="POST">
                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                <input type="submit" value="Home">
            </form>
        </div>
        <div class="playlist-list-nav">
        </div>
        <div class="playlist-list-container">
            <div class="main-container">
                <% for(VideoDTO video : playListItems) { %>
                   <div>
                        <a href="<%=video.getLink()%>"><%=video.getTitle()%></a>
                        <form action="DeleteVideoFromPlayList" method="POST">
                            <input type="hidden" value="<%=video.getTitle()%>" name="videoTitle">
                            <input type="hidden" value="<%=userEmail%>" name="userEmail">
                            <input type="hidden" value="<%=playListName%>" name="playListName">
                            <input type="submit" value="Delete From Playlist">
                        </form>
                   </div
               <% } %>
            </div>
        </div>
    </body>
</html>