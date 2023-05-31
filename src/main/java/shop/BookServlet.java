package shop;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/products")
@MultipartConfig
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
        }else {
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
                case "search":
                    String searchItem = request.getParameter("searchItem");
                    List<Book> searchResults = searchProduct(searchItem);
                    request.setAttribute("productList", searchResults);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
                    dispatcher.forward(request, response);
                    break;


                default:
                    showProductList(request, response);
                    break;
            }

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

    private List<Book> searchProduct(String searchItem) {

        List<Book> searchResults = new ArrayList<>();
        for (Book product : productList) {
            if (product.getName().toLowerCase().contains(searchItem.toLowerCase())
            || product.getAuthor().toLowerCase().contains(searchItem.toLowerCase())) {
                searchResults.add(product);
//                System.out.println("<h1> đã tìm thấy "+ productList.size() + "kết quả phù hợp</h1>");
            }
        }
        return searchResults;
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

        Part filePart = request.getPart("img");
        String fileName = getFileName(filePart);
        String uploadDirectory = getServletContext().getRealPath("/img");
        String imagePath = uploadFile(filePart, fileName, uploadDirectory);
//        String img = "img/" + fileName;
        String img =  fileName;
        Book newProduct = new Book(id, name, author, price, category, desc, amount, img);
        productList.add(newProduct);

        response.sendRedirect("products");
    }

    private String uploadFile(Part part, String fileName, String uploadDirectory) throws IOException {
        String filePath = uploadDirectory + File.separator + fileName;

        try (InputStream is = part.getInputStream(); FileOutputStream out = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }

        return filePath;
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
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

        // Tìm sản phẩm trong danh sách theo ID
        Book book = getProductById(productId);

        if (book == null) {
            // Nếu không tìm thấy sản phẩm, chuyển hướng về trang danh sách sản phẩm
            response.sendRedirect("products");
            return;
        }

        book.setName(name);
        book.setAuthor(author);
        book.setPrice(price);
        book.setCategory(category);
        book.setDesc(desc);
        book.setAmount(amount);

        Part part = request.getPart("img");
        if (part != null && part.getSize() > 0) {
            deleteimg(book.getImg(), request);
            String fileName = getFileName(part);
            String upload = getServletContext().getRealPath("/img");
            String path = uploadFile(part, fileName, upload);
//            String img = "img/" + fileName;
            String img =  fileName;

            book.setImg(img);
        }

        // Chuyển hướng về trang danh sách sản phẩm
        response.sendRedirect("products");
    }
private void deleteimg(String img,HttpServletRequest request){
        String upload = request.getServletContext().getRealPath("") + File.separator + "img";
        String part = upload + File.separator + img;

        File file = new File(part);
        if(file.exists()){
            file.delete();
        }
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
