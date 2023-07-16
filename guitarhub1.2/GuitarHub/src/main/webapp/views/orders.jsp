<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="./css/orders.css">
  <script src="https://kit.fontawesome.com/fbe6bde3b4.js"></script>
  <script src="./scripts/headerScript.js"></script>
  <title>GuitarHub - I miei ordini</title>
</head>
<body>
<%@ include file="header.jsp" %>
<script>activator();</script>

<div class="order-box">
        <div class="order-header">
            <div class="order-info">
                <p>Date: <span class="order-date">[Order Date]</span></p>
                <p>Total Paid: $<span class="order-total">[Total Paid]</span></p>
                <p>Destination: <span class="order-destination">[Destination]</span></p>
                <p>Order ID: <span class="order-id">[Order ID]</span></p>
                <a href="#" class="order-link">Fattura</a>
            </div>
        </div>
        <hr>
        <div class="shipping-status">
            <h3 class="shipH3">Shipping Status:</h3>
            <p>[Shipping Status]</p>
        </div>
        <div class="ordered-items">
            <h3>Ordered Items:</h3>
            <div class="item">
                <img src="../images/sample.jpg" alt="[Item Name]">
                <p class="item-name">[Item Name]</p>
            </div>
            <div class="item">
                <img src="../images/sample.jpg" alt="[Item Name]">
                <p class="item-name">[Item Name]</p>
            </div>
        </div>
    </div>

    <div class="order-box">
        <div class="order-header">
            <div class="order-info">
                <p>Date: <span class="order-date">[Order Date]</span></p>
                <p>Total Paid: $<span class="order-total">[Total Paid]</span></p>
                <p>Destination: <span class="order-destination">[Destination]</span></p>
                <p>Order ID: <span class="order-id">[Order ID]</span></p>
                <a href="#" class="order-link">Fattura</a>
            </div>
        </div>
        <hr>
        <div class="shipping-status">
            <h3 class="shipH3">Shipping Status:</h3>
            <p>[Shipping Status]</p>
        </div>
        <div class="ordered-items">
            <h3>Ordered Items:</h3>
            <div class="item">
                <img src="../images/sample.jpg" alt="[Item Name]">
                <p class="item-name">[Item Name]</p>
            </div>
            <div class="item">
                <img src="../images/sample.jpg" alt="[Item Name]">
                <p class="item-name">[Item Name]</p>
            </div>
            <div class="item">
                <img src="../images/sample.jpg" alt="[Item Name]">
                <p class="item-name">[Item Name]</p>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp" %>
</body>
</html>