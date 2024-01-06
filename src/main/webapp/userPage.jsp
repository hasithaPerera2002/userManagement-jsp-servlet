<%--
  Created by IntelliJ IDEA.
  User: hasit
  Date: 1/5/2024
  Time: 2:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserPage</title>
    <link rel="stylesheet" href="bootstrap-4.0.0-dist/css/bootstrap.min.css">
    <style>

        .gradient-custom {
            /* fallback for old browsers */
            background: #6a11cb;

            /* Chrome 10-25, Safari 5.1-6 */
            background: -webkit-linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1));

            /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
            background: linear-gradient(to right, rgba(106, 17, 203, 1), rgba(37, 117, 252, 1))
        }
    </style>
</head>
<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <form action="userPageController" method="post">
            <div class="row d-flex w-100 align-items-center justify-content-center align-items-center h-100">



                <button class="card bg-dark mr-5 text-white" name="action" value="order" type="submit" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                         Order
                    </div>
                </button>
                <button class="card mr-5 bg-dark ml-4 text-white" name="action" value="inventory" type="submit" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Manage Inventory
                    </div>
                </button>
                <button class="card mr-5 bg-dark ml-4 text-white" name="action" value="orders" type="submit" style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Orders
                    </div>
                </button>



            </div>
        </form>
    </div>
</section>
</body>
</html>
