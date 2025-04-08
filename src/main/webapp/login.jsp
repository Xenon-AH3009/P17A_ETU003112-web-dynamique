<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    if(request.getSession().getAttribute("key")!=null) {
        response.sendRedirect("success?connexion=déja connecté");
        }
    String error=request.getParameter("error");
    String message="";
    if(error!=null) {
        if(error.equals("1")) {
            message="login not found";
        }
        else if(error.equals("0")) {
            message="wrong password";
        }
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href='assets/css/bootstrap.css' rel='stylesheet'>
    <title>Document</title>
</head>
<body>
</br></br></br></br></br></br></br>
    <div class="panel panel-default">
        <h1 class="text-center panel-heading">Login Authentification</h1>
        </br>
        <form name="form1" method="post" action=form>
            <div class="panel-body container form-group">
                <div class="row">
                    <label for="login" class="col-xs-3">Login</label> 
                    <div class="col-xs-5">
                        <input class="form-control" type="text" name="login" id="login">
                    </div>
                </div>
                </br>
                <div class="row">
                    <label for="password" class="col-xs-3">Password</label> 
                    <div class="col-xs-5">
                        <input class="col-xs-5 form-control" type="password" name="pwd" id="password">
                    </div>
                </div>
                    <p class="text-danger"><%=message%></p>
                    <button type="submit" class="btn btn-primary btn-lg btn-block">Sign In</button>
            </div>
        </form>  
    </div>
</body>
</html>