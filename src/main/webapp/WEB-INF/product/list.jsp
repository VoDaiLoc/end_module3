<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20/07/2022
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Vertical layout | Zircos - Responsive Bootstrap 4 Admin Dashboard</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta content="Responsive bootstrap 4 admin template" name="description">
    <meta content="Coderthemes" name="author">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <jsp:include page="/WEB-INF/layout/meta_css.jsp"></jsp:include>

</head>

<body>

<!-- Begin page -->
<div id="wrapper">


    <!-- Topbar Start -->
    <jsp:include page="/WEB-INF/layout/navbar-custom.jsp"></jsp:include>
    <!-- end Topbar -->

    <!-- ========== Left Sidebar Start ========== -->
    <jsp:include page="/WEB-INF/layout/left-side-menu.jsp"></jsp:include>

    <!-- Left Sidebar End -->

    <!-- ============================================================== -->
    <!-- Start Page Content here -->
    <!-- ============================================================== -->

    <div class="content-page">
        <div class="content">

            <!-- Start Content-->
            <div class="container-fluid">



                <div class="row">
                    <div class="col-xl-12">
                        <div class="card-box">
                            <div style="display: flex;justify-content: space-between">
                                <h1 class="list">List Products</h1>
                                <form method="get" action="/product" class="app-search">
                                    <input type="hidden" value="search" name="action">
                                    <div class="app-search-box">
                                        <div class="input-group">
                                            <input oninput="search(event)" style="border-radius: 20px" type="text" name="searchproduct" class="form-control" placeholder="Search..." value="${requestScope.searchproduct}">
                                            <div class="input-group-append">
                                                <button class="btn " type="submit">
                                                    <i class="fas fa-search text-dark"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                                <button type="button" class="btn-add btn-primary" ><a href="/product?action=create" >Add Product</a></button>
                            </div>
                            <div class="table-responsive" id="tbPU">
                                <table class="table table-hover table-centered m-0">
                                    <thead>
                                    <tr style="background-color: #008a00;color: white">
                                        <th style="text-align: right">Name</th>
                                        <th style="text-align: right">Price</th>
                                        <th style="text-align: right">Quantity</th>
                                        <th style="text-align: right">Màu sắc</th>
                                        <th style="text-align: right">Mô tả</th>
                                        <th style="text-align: right;padding-right:  50px">Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>

                                    <c:forEach var="product" items="${productList}">
                                        <tr>
                                            <td style="text-align: right">${product.getName()}</td>
                                            <td style="text-align: right">${product.getPrice()}</td>
                                            <td style="text-align: right">${product.getQuantity()}</td>
                                            <td style="text-align: right">${product.getColor()}</td>
                                            <td style="text-align: right">${product.getDes()}</td>
                                            <td style="text-align: right">
                                                <button class="edit"><a href="/product?action=edit&id=${product.getId()}"><i class="fas fa-edit"></i></a></button>
                                                <button class="delete"><a href="/product?action=delete&id=${product.getId()}" onclick="return confirm('Bạn có chắc chắn muốn xóa sản phẩm này không?');"><i class="fas fa-trash"></i></a></button>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                    </tbody>
                                </table>

                            </div>
                            <!-- table-responsive -->

                        </div>
                        <!-- end card -->
                    </div>
                    <!-- end col -->



                </div>
                <!-- end row -->

            </div>
            <!-- end container-fluid -->

        </div>
        <!-- end content -->



        <!-- Footer Start -->
        <footer class="footer">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        2018 - 2020 &copy; Zircos theme by <a href="">Coderthemes</a>
                    </div>
                </div>
            </div>
        </footer>
        <!-- end Footer -->

    </div>
    <!-- END wrapper -->

    <!-- Right Sidebar -->


    <!-- Vendor js -->
    <jsp:include page="/WEB-INF/layout/script.jsp"></jsp:include>

</body>

</html>
