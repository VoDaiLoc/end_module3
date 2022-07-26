<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 20/07/2022
  Time: 9:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
            <div class="container-fluid" style="margin-top: 25px">



                <div class="row">
                    <div class="col-lg-9 " style="margin: auto">

                        <h1 class="header-title" style="font-size: 30px;text-align: center">Product Form</h1>

                        <div class="p-4">
                            <form  method="post" data-parsley-validate="" novalidate="">
                                <div class="form-group">

                                    <label for="name">Product Name<span class="text-danger">*</span></label>
                                    <input type="text" name="name" parsley-trigger="change" required="" placeholder="Enter product name" class="form-control" id="name">
                                </div>

                                <div class="form-group">
                                    <label for="price">Price<span class="text-danger">*</span></label>
                                    <input type="number" name="price" parsley-trigger="change" required="" placeholder="Enter Price" class="form-control" id="price">
                                </div>

                                <div class="form-group">
                                    <label for="quantity">Quantity<span class="text-danger">*</span></label>
                                    <input type="number" name="quantity" parsley-trigger="change" required="" placeholder="Enter Quantity" class="form-control" id="quantity">
                                </div>

                                <div class="form-group">
                                    <label for="color">Color<span class="text-danger">*</span></label>
                                    <input type="text" name="color" parsley-trigger="change" required="" placeholder="Enter Color" class="form-control" id="color">
                                </div>

                                <div class="form-group">
                                    <label for="des">Des<span class="text-danger">*</span></label>
                                    <input type="text" name="des" parsley-trigger="change" required="" placeholder="Enter Des" class="form-control" id="des">
                                </div>


                                <div class="form-group">
                                    <div class="checkbox checkbox-purple">
                                        <input id="remember-1" type="checkbox">
                                        <label for="remember-1"> Remember me </label>
                                    </div>
                                </div>

                                <div class="form-group text-right mb-0">
                                    <button class="btn btn-primary waves-effect waves-light" type="submit">
                                        Submit
                                    </button>
                                    <button type="reset" class="btn btn-secondary waves-effect ml-1">
                                        <a href="/product">Cancel</a>
                                    </button>
                                </div>

                            </form>
                        </div>

                    </div>
                    <div class="col-lg-3">
                        <div class="row" style="margin-top: 20px">
                            <div class="col-12">
                                <c:if test="${requestScope['success'] == true}">
                                    <span class="alert alert-success "
                                          style="font-size: 15px">Thêm mới thành công</span>
                                </c:if>
                                <c:if test="${!requestScope['errors'].isEmpty()}">
                                    <c:forEach items="${requestScope['errors']}" var="item">
                                        <div class="alert alert-danger" style="margin-top: -20px;">
                                            <span style="font-size: 15px;">
                                                    ${item}
                                            </span>
                                        </div>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    </div>

                    </div>
                 </div>
            <!-- end container-fluid -->

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
