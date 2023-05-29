
<%@ page import="shop.Book" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Book Search Results</title>
</head>
<body>
<h1>Book Search Results</h1>

<%
    List<Book> searchResult = (List<Book>) request.getAttribute("searchResult");

    if (searchResult != null && searchResult.size() > 0) {
%>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Price</th>
    </tr>

    <% for(Book product : searchResult) { %>
    <tr>
        <td>${product.name}</td>
        <td>${product.author}</td>
        <td>${product.price}</td>
    </tr>
    <% } %>
</table>
<% } else { %>
<p>No search results found.</p>
<% } %>
</body>
</html>