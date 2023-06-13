<html>
<div class="java">
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="DTO.VideoDTO" %>
    <%  String userEmail = (String)request.getAttribute("userEmail");
    ArrayList<VideoDTO> playListItems = (ArrayList<VideoDTO>) request.getAttribute("playListItems");
    request.setAttribute("userEmail",userEmail);
    String playListName = (String)request.getAttribute("playListName");
    %>
</div>
    <head>
        <link rel="stylesheet" href="playlist-items.css">
        <link rel="stylesheet" href="global-styles.css">
    </head>
    <body>
        
        <div class="playlist-items-nav">
            <form action="GoHomeController" method="POST">
                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                <input type="submit" id="home" value="Home">
            </form>
        </div>

        <div class="playlist-items-container">
            <div class="main-container">
                <h3>Current Playlist : <%=playListName%></h3>
                <table>
                    <div class="java"><% for(VideoDTO video : playListItems) { %></div>
                    <div>
                        <tr>
                           <td> <a href="<%=video.getLink()%>"><%=video.getTitle()%></a></td>
                           <td> <form action="DeleteVideoFromPlayList" method="POST">
                                <input type="hidden" value="<%=video.getTitle()%>" name="videoTitle">
                                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                <input type="hidden" value="<%=playListName%>" name="playListName">
                                <button type="submit" >Delete From Playlist</button>
                            </form>
                            </td>
                        </tr>
                    </div
                     <% } %>
                </table>
            </div>
        </div>
    </body>
</html>