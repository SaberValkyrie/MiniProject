package shop;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
public class BookServlet extends HttpServlet {
    private List<Book> productList;

    @Override
    public void init() throws ServletException {
        super.init();
        productList = new ArrayList<>();
        productList.add(new Book(1,"Dragon Ball","Akira Toryama",18500,"manga","good for kid", 129,"book2.jpg"));
       productList.add(new Book(2,"Dragon Ball GT","Netizen",14000,"manga","conan", 123,"book2.jpg"));
        productList.add(new Book(3,"Conan","aoi",15000,"manga","conan", 10,"book1.jpg"));
       productList.add(new Book(4,"Conan","aoi",22000,"manga","conan", 41,"book1.jpg"));
      productList.add(new Book(5,"Doraemon 2","Fujko",15000,"manga","conan", 12,"book3.jpg"));
        productList.add(new Book(6,"Doraemon 3","Fujko",19000,"manga","conan", 100,"book3.jpg"));

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            showProductList(request, response);
        }
        switch (action) {
            case "new":
                showCreateForm(request,response);
                break;
            case "create":
                createProduct(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "update":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            default:
                showProductList(request, response);
                break;
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     doGet(request,response);
    }


    private void showProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Hiển thị trang tạo sản phẩm
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
        dispatcher.forward(request, response);
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = productList.size() + 1;
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String desc = request.getParameter("desc");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String img = request.getParameter("img");

        Book newProduct = new Book(id, name, author,price,category,desc,amount,img);
        productList.add(newProduct);

        response.sendRedirect("products");

    }
        private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID sản phẩm cần chỉnh sửa từ request
        int productId = Integer.parseInt(request.getParameter("id"));

        // Tìm sản phẩm trong danh sách theo ID
        Book product = getProductById(productId);

        if (product == null) {
            // Nếu không tìm thấy sản phẩm, chuyển hướng về trang danh sách sản phẩm
            response.sendRedirect("products");
            System.out.println("khong thay san pham");
            return;
        }
            // Đặt sản phẩm vào attribute của request để hiển thị trên trang chỉnh sửa
            request.setAttribute("product", product);

            // Hiển thị trang chỉnh sửa sản phẩm
            RequestDispatcher dispatcher = request.getRequestDispatcher("product-form.jsp");
            dispatcher.forward(request, response);

    }
    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy thông tin sản phẩm từ request
        int productId = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));
        String category = request.getParameter("category");
        String desc = request.getParameter("desc");
        int amount = Integer.parseInt(request.getParameter("amount"));
        String img = request.getParameter("img");

        // Tìm sản phẩm trong danh sách theo ID
        Book product = getProductById(productId);

        if (product == null){
            // Nếu không tìm thấy sản phẩm, chuyển hướng về trang danh sách sản phẩm
            response.sendRedirect("products");
        }
            // Cập nhật thông tin sản phẩm
            product.setName(name);
        product.setAuthor(author);
            product.setPrice(price);
            product.setCategory(category);
        product.setDesc(desc);
            product.setAmount(amount);
            product.setImg(img);

            // Chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect("products");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy ID sản phẩm cần xóa từ request
        int productId = Integer.parseInt(request.getParameter("id"));

        // Tìm sản phẩm trong danh sách theo ID
        Book product = getProductById(productId);

        if (product == null) {
            // Nếu không tìm thấy sản phẩm, chuyển hướng về trang danh sách sản phẩm
            response.sendRedirect("products");
        }
            // Xóa sản phẩm khỏi danh sách
            productList.remove(product);

            // Chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect("products");

    }

    private Book getProductById(int productId) {
        for (Book product : productList) {
            if (product.getId() == productId) {
                return product;
            }
        }
        return null;
    }
}
