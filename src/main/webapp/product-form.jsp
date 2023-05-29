<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Book Form</title>
    <link type="text/css" rel="stylesheet" href="css/form.css">
    <link rel="icon" href="img/icon.png">

</head>

<body style="background-image: url('img/bookshelf.jpg');">
<c:choose>
    <c:when test="${empty product.id}">
        <form action="products?action=create" method="post">
            <h1>Add New Book:</h1>
            <label for="name">Name:</label>
            <input type="text" placeholder="Enter the name" name="name" id="name"><br><br>
            <label for="author">Author:</label>
            <input type="text" name="author" placeholder="Enter the Author"  id="author"><br><br>
            <label for="price">Price:</label>
            <input type="text" name="price" placeholder="Price" id="price"><br><br>
            <label for="category">Category:</label>
            <input type="text" name="category" placeholder="Category" id="category"><br><br>
            <label for="desc">Description:</label>
            <input type="text" name="desc" placeholder="Description"  id="desc"><br><br>
            <label for="amount">Amount:</label>
            <input type="text" name="amount" placeholder="Amount" id="amount"><br><br>
            <label for="img"> Book image:</label>
            <input type="file" name="img" id="img"><br><br>
            <input type="submit" class="button" value="Create">
            <a href="products" class="button cancel">Cancel</a>
        </form>
    </c:when>
    <c:otherwise>
        <form action="products?action=update" method="post">

            <input type="hidden" name="id" value="${product.id}">
            <h1>Edit Book:</h1>
            <label for="name">Name:</label>
            <input type="text" name="name" id="name" value="${product.name}"><br><br>
            <label for="author">Author:</label>
            <input type="text" name="author" id="author" value="${product.author}"><br><br>
            <label for="price">Price:</label>
            <input type="text" name="price" id="price" value="${product.price}"><br><br>
            <label for="category">Category:</label>
            <input type="text" name="category" id="category" value="${product.category}"><br><br>
            <label for="desc">Description:</label>
            <input type="text" name="desc" id="desc" value="${product.desc}"><br><br>
            <label for="amount">Amount:</label>
            <input type="text" name="amount" id="amount" value="${product.amount}"><br><br>
            <label for="img"> Book image:</label>
            <input type="file" name="img" id="img" value="${product.img}"><br><br>
            <input type="submit" class="button" value="Update">
            <a href="products" class="button">Cancel</a>
        </form>

    </c:otherwise>
</c:choose>
</body>
</html>