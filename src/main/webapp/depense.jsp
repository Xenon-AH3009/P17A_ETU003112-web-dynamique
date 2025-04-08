<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="main.java.com.itu.model.Prevision" %>
<%
    Prevision []previsions=(Prevision[])request.getAttribute("previsions");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Depense</title>
</head>
<body>
    <form action=insererdepense method="post">
        <label for="prevision">Prevision</label>
        <select type="number" name="prevision" id="prevision">
            <% for(int i=0;i<previsions.length;i++) {%>
                <option value="<%=previsions[i].getId()%>"><%=previsions[i].getLibelle();%></option>
            <% }%>
        </select>
        <label for="montant">Montant</label>
        <input type="number" name="montant" id="montant">
        <button class="submit">Valider</button>
    </form>
</body>
</html>