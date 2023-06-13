<html>
    <head>
    </head>
    <body>
        <%@ page import="java.util.ArrayList" %>
        <% String userEmail = (String)request.getAttribute("userEmail");
            request.setAttribute("userEmail",userEmail);%>
        <% ArrayList<String> playLists = (ArrayList<String>)request.getAttribute("playLists"); %>

        <div class="profile-nav">
            <form action="GoHomeController" method="POST">
                <input type="hidden" value="<%=userEmail%>" name="userEmail">
                <input type="submit" value="Home">
            </form>
        </div>
            <div class="playlist-container">
                <div class="main-container">
                    <%if(playLists!=null){%>
                        <table>
                             <%for(String name : playLists){%>
                                <tr>
                                    <td>
                                        <form action=ShowPlayListItemsController method="POST">
                                            <input type="hidden" value="<%=name%>" name="playListName">
                                            <input type="hidden" value="<%=userEmail%>" name="userEmail">
                                            <input type="submit" value="<%=name%>" >
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
                             <% } %>
                        <table>
                    <%}%>
                    <div class="new-playlist">
                        <form action=CreateNewPlayListController method="POST">
                            <input type="text" placeholder="Enter Playlist name" name="playListName">
                             <input type="hidden" value="<%=userEmail%>" name="userEmail">
                            <button type="submit">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
    </body>
</html>