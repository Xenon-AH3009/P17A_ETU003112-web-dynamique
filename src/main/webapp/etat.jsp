<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.com.itu.model.Prevision" %>
<%@ page import="main.java.com.itu.model.Depense" %>
<%
    Prevision []previsions=(Prevision[])request.getAttribute("previsions");
    Depenses []depensess=(Depenses[])request.getAttribute("depensess");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Depense</title>
</head>
<body>
    <h1>Etat</h1>
    <table>
        <tr>
            <th>Prevision</th>
            <th>Depense</th>
            <th>Reste</th>
        </tr>
        <tr>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    <table>
</body>
</html>