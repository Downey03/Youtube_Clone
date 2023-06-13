

<%@ page import="java.util.ArrayList"%>
<html>
    <head>
    </head>
    <body>
        <% ArrayList<String> playLists = (ArrayList<String>) request.getAttribute("playLists"); %>
        <% String userEmail = (String)request.getAttribute("userEmail"); %>
        <% String videoTitle = (String)request.getAttribute("videoTitle"); %>
        <% request.setAttribute("userEmail",userEmail); %>
        <div class="nav">
        </div>
        <div class="playlist-container">
            <div class="main-container">
                        <%if(playLists!=null){%>
                            <table>
                                 <%for(String name : playLists){%>
                                    <tr>
                                        <td><%=name%></td>
                                        <td>
                                             <form action=AddToPlayListController method="POST">
                                                 <input type="hidden" value="<%=name%>" name="playListName">
                                                  <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                                 <input type="hidden" id="video-title" value="<%=videoTitle%>" name="videoTitle">
                                                 <button type="submit">Add To Playlist</button>
                                            </form
                                        </td>
                                    </tr>
                                 <% } %>
                            <table>
                        <%}%>
                    <div class="new-playlist">
                        <form action=CreatePlayListAndAddVideoController method="POST">
                            <input type="text" placeholder="Enter Playlist name" name="playListName">
                             <input type="hidden" value="<%=userEmail%>" name="userEmail">
                            <input type="hidden" value="<%=videoTitle%>" name="videoTitle">
                            <button type="submit">Submit</button>
                        </form>
                    </div>

            </div>
        </div>
    </body>
</html>