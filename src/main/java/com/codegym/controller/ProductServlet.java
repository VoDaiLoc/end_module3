package com.codegym.controller;

import com.codegym.dao.CategoryDAO;
import com.codegym.dao.ProductDAO;
import com.codegym.model.Category;
import com.codegym.model.Product;
import com.codegym.utils.ValidateUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductDAO productDAO;
    CategoryDAO categoryDAO;

    public void init() throws ServletException {
        productDAO = new ProductDAO();
        categoryDAO = new CategoryDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                case "search":
                    search(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/edit.jsp");
        request.setAttribute("product", existingProduct);
        dispatcher.forward(request, response);

    }

    private void search(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/list.jsp");

        String searchWord = request.getParameter("searchproduct");

        List<Product> productList = productDAO.searchProduct(searchWord);

        request.setAttribute("productList", productList);

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        Product product;
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();
        String quantity = request.getParameter("quantity").trim();
        String color = request.getParameter("color").trim();
        String des = request.getParameter("des").trim();
        List<String> errors = new ArrayList<>();
        boolean isPrice = ValidateUtils.isNumberVailid(price);
        boolean isQuantity = ValidateUtils.isNumberVailid(quantity);


        product = new Product(name, price, quantity, color, des);


        if (
                name.isEmpty() ||
                        price.isEmpty() ||
                        quantity.isEmpty() ||
                        color.isEmpty()
        ) {
            errors.add("Vui lòng điền đầy đủ thông tin");
        }

        if (name.isEmpty()) {
            errors.add("Name Product không được để trống");
        }
        if (price.isEmpty()) {
            errors.add("Price Product không được để trống");
        }
        if (quantity.isEmpty()) {
            errors.add("Quantity Product không được để trống");
        }
        if (color.isEmpty()) {
            errors.add("Color Product không được để trống");
        }

        if (!isQuantity) {
            errors.add("Quantity phải là một số và là số >=0");
        }
        if (!isPrice) {
            errors.add("Price phải là một số và là số dương");
        }
        double checkPrice = Double.parseDouble(price);

        if (!isPrice || checkPrice == 0) {
            errors.add("Price phải là một số và là số dương");
        }
        try {

            if (checkPrice > 500000) {
                errors.add("Price phải <= 500000 đồng");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("Định dạng giá không hợp lệ");
        }


        if (errors.size() == 0) {
            product = product = new Product(name, price, quantity, color, des);
            boolean success = false;
            success = productDAO.insertProduct(product);
            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }


        }
        if (errors.size() > 0) {
            request.setAttribute("errors", errors);
            request.setAttribute("inserProduct", product);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/add.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        String name = request.getParameter("name");
//        String price = request.getParameter("price");
//        String quantity = request.getParameter("quantity");
//        String image = request.getParameter("image");
//        Product newProduct = new Product(id, name, price,quantity,image);
//        System.out.println(newProduct);
//        productDAO.updateProduct(newProduct);
////        RequestDispatcher dispatcher = request.getRequestDispatcher("/product");
////        dispatcher.forward(request, response);
//        response.sendRedirect("/product");

        List<String> errors = new ArrayList<>();
        Product product;
        String para = request.getParameter("id").trim();
        int id = ValidateUtils.isIntValid(para) ? Integer.parseInt(para) : 11;
        String name = request.getParameter("name").trim();
        String price = request.getParameter("price").trim();
        String quantity = request.getParameter("quantity").trim();
        String color = request.getParameter("color");
        String des = request.getParameter("des");
        boolean isPrice = ValidateUtils.isNumberVailid(price);
        boolean isQuantity = ValidateUtils.isNumberVailid(quantity);
        product = new Product(id, name, price, quantity, color, des);

        if (
                name.isEmpty() ||
                        price.isEmpty() ||
                        quantity.isEmpty() ||
                        color.isEmpty()
        ) {
            errors.add("Hãy nhập đầy đủ thông tin");
        }
        if (name.isEmpty()) {
            errors.add("Name không được để trống");
        }
        if (price.isEmpty()) {
            errors.add("Price không được để trống");
        }
        if (quantity.isEmpty()) {
            errors.add("Quantity không được để trống");
        }
        double checkPrice = Double.parseDouble(price);
        if (!isPrice || checkPrice == 0) {
            errors.add("Price phải là một số và là một số dương");
        }
        try {

            if (checkPrice > 500000) {
                errors.add("Price phải <= 500000 đồng!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            errors.add("Định dạng giá không hợp lệ");
        }
        if (!isQuantity) {
            errors.add("Quantity phải là một số và là một số dương");
        }
        if (errors.size() == 0) {
            product = new Product(id, name, price, quantity, color, des);
            boolean success = false;
            try {
                success = productDAO.updateProduct(product);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            if (success) {
                request.setAttribute("success", true);
            } else {
                request.setAttribute("errors", true);
                errors.add("Invalid data, Please check again!");
            }
        } else {
            request.setAttribute("errors", errors);
            request.setAttribute("product", product);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/add.jsp");
        List<Category> categoryList = categoryDAO.selectAllCategory();
        req.setAttribute("categoryList", categoryList);
        dispatcher.forward(req, resp);
    }

    private void listNumberPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        System.out.println("numberPage");
        int page = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }
        ;
        String name = "";
        if (request.getParameter("searchproduct") != null) {
            name = request.getParameter("searchproduct");
        }
        List<Product> listProduct = productDAO.getNumberPage((page - 1) * recordsPerPage, recordsPerPage, name);
        int noOfRecords = productDAO.getNoOfRecords();
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
//        System.out.println("noOfPages" + noOfPages);
//        System.out.println(noOfRecords);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", page);
        request.setAttribute("searchproduct", name);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/product/list.jsp");
        requestDispatcher.forward(request, response);

    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/product/list.jsp");
//        List<Category> categoryList = categoryDAO.selectAllCategory();
        List<Product> productList = productDAO.selectAllProduct();

//        req.setAttribute("categoryList",categoryList);
        req.setAttribute("productList", productList);


        dispatcher.forward(req, resp);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);

        List<Product> listProduct = productDAO.selectAllProduct();
        request.setAttribute("listProduct", listProduct);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/product/list.jsp");
//        dispatcher.forward(request, response);
        response.sendRedirect("/product");
    }
}
