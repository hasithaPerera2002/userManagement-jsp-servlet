<%--
  Created by IntelliJ IDEA.
  User: hasit
  Date: 1/6/2024
  Time: 8:59 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
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
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-12 col-md-8 col-lg-6 col-xl-5">
                <div class="card bg-light p-5 ">
                    <form id="orderForm" action="orderController" method="post">
                        <select id="categorySelect" class="custom-select" name="selectedItemId"> <!-- Move name attribute to select tag -->
                            <option selected>Category</option>
                        </select>

                        <div class="form-group">
                            <label>Quantity At Inventory</label>
                            <input type="number" class="form-control" id="inventoryQuantity" readonly>
                        </div>
                        <div class="form-group">
                            <label>Quantity</label>
                            <input type="number" name="quantity" class="form-control" id="quantity">
                        </div>
                        <div class="form-group">
                            <label>PricePerOne</label>
                            <input type="number" class="form-control" id="pricePerOne" readonly>
                        </div>
                        <div class="form-group">
                            <label>Total</label>
                            <input type="number" name="total" class="form-control" id="total">
                        </div>
                        <div class="form-group">
                            <button class="btn btn-warning" type="submit">Save Order</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
</body>
<script>

    $('#orderForm').submit(function (event) {
        event.preventDefault(); // Prevent default form submission

        var selectedOption = $('#categorySelect').find(":selected");
        var selectedItemId = selectedOption.data('item-id'); // Retrieve item ID from selected option

        console.log(selectedItemId)
        // Add the selected item's ID to the form data before submitting
        $(this).append('<input type="hidden" name="selectedItemId" value="' + selectedItemId + '">');

        // Submit the form with the updated data
        this.submit();
    });


    $(document).ready(function () {
            $.ajax({
                url: "http://localhost:8080/userManagement/orderController",
                type: "GET",
                dataType: "json",
                success: function (data) {
                    var categorySelect = $('#categorySelect');

                    categorySelect.find('option:not(:first-child)').remove();
                    console.log(data)

                    data.forEach(function (category) {
                        categorySelect.append($('<option>', {
                            value: category.id,
                            text: category.name,
                            'data-price': category.price,
                            'data-quantity': category.quantity,
                            name:'itemId'

                        }));
                    });
                },
                error: function (xhr, status, error) {
                    console.error("Error fetching categories:", error);
                }

            });
            $('#categorySelect').change(function () {
                var selectedOption = $(this).find(":selected");
                console.log(selectedOption)

                $('#inventoryQuantity').val(selectedOption.data('quantity'));
                $('#pricePerOne').val(selectedOption.data('price'));

                var quantity = parseInt($('#quantity').val()) || 0;
                var pricePerOne = parseInt($('#pricePerOne').val()) || 0;
                var total = pricePerOne * quantity;

                $('#total').val(total);
            });

            function calculateTotal() {
                var selectedOption = $('#categorySelect').find(":selected");
                var quantity = parseInt($('#quantity').val()) || 0;
                var pricePerOne = parseInt($('#pricePerOne').val()) || 0;
                var total = pricePerOne * quantity;
                $('#total').val(total);
            }


            calculateTotal();


            $('#quantity').on('input', function () {
                calculateTotal();
            });
        }
    );
</script>
<script src="bootstrap-4.0.0-dist/js/bootstrap.min.js"></script>


</html>
