<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPage</title>
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

        /* Added styles for consistent spacing */
        .card-container {
            display: flex;
            justify-content: center;
            align-items: center;
            margin-top: 2rem;
        }

        .card {
            margin: 0 0.5rem;
        }
    </style>
</head>
<body>
<section class="vh-100 gradient-custom">
    <div class="container py-5 h-100">
        <form action="adminPageController" method="post">
            <div class="card-container">
                <button class="card bg-dark text-white" name="action" value="user" type="submit"
                        style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Manage Users
                    </div>
                </button>
                <button class="card bg-dark text-white" name="action" value="item" type="submit"
                        style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Manage Items
                    </div>
                </button>
                <button class="card bg-dark text-white" name="action" value="supplier" type="submit"
                        style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Manage Suppliers
                    </div>
                </button>
                <button class="card bg-dark text-white" name="action" value="reports" type="submit"
                        style="border-radius: 1rem;">
                    <div class="card-body p-5 text-center">
                        Reports
                    </div>
                </button>
            </div>
        </form>
    </div>
</section>
</body>
</html>
