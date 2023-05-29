<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
    <link type="text/css" rel="stylesheet" href="css/style.css">
    <script src="js/list.js"></script>
    <link rel="icon" href="img/icon.png">
</head>
<body>
<div class="container" style="background-image: url('img/book.png');">
    <h2>Home Page</h2>
    <p>Welcome to Book Store!</p>
    <a class="highlight" href="products">My List Book</a>
</div>

<div class="additional-content">
    <h4>Featured Books</h4>
    <div class="book-list-container">
        <div class="book-list">
            <div class="book-item">
                <img src="img/book1.jpg" alt="Book 2">
                <h3>Co Lan</h3>
                <p1>Author: Aoi</p1>
            </div>
            <div class="book-item">
                <img src="img/book2.jpg" alt="Book 2">
                <h3>Dragon Ball</h3>
                <p1>Author: Akira Toryama</p1>
            </div>
            <div class="book-item">
                <img src="img/book3.jpg" alt="Book 3">
                <h3>Doraemon</h3>
                <p1>Author: Fujiko Fuho</p1>
            </div>
            <div class="book-item">
                <img src="img/book1.jpg" alt="Book 2">
                <h3>Co Lan</h3>
                <p1>Author: Aoi</p1>
            </div>
            <div class="book-item">
                <img src="img/book2.jpg" alt="Book 2">
                <h3>Dragon Ball</h3>
                <p1>Author: Akira Toryama</p1>
            </div>
            <div class="book-item">
                <img src="img/book3.jpg" alt="Book 3">
                <h3>Doraemon</h3>
                <p1>Author: Fujiko Fuho</p1>
            </div>

        </div>
        <button class="scroll-left-btn"><</button>
        <button class="scroll-right-btn">></button>
    </div>
</div>

</body>
</html>
