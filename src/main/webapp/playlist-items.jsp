<html>
<div class="java">
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="DTO.VideoDTO" %>
    <%  String userEmail = (String)request.getAttribute("userEmail");
    ArrayList<VideoDTO> playListItems = (ArrayList<VideoDTO>) request.getAttribute("playListItems");
    if(playListItems == null) playListItems = new ArrayList<VideoDTO>();
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
                    <% if(playListItems.size()==0) { %>
                        <div><br><center><h3>No video Found In Playlist</h3><center><div>
                        <% } %>
                        <table>
                            <div class="java"><% for(VideoDTO video : playListItems) { %></div>
                            <div>
                                <tr>
                                   <td> <a href="<%=video.getLink()%>" target="_blank"><%=video.getTitle()%></a></td>
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