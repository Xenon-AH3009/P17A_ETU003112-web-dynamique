package main.java.com.itu.Login;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class UIFormServlet extends HttpServlet {
    HttpSession session=null;
    String login="Kana";
    String pwd="seno";
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        if("/form".equals(path)) {
            res.setContentType("text/html");
            res.setCharacterEncoding("UTF-8");
            String post_login=req.getParameter("login");
            String post_pwd=req.getParameter("pwd");
            if(!post_login.equals(login) || !post_pwd.equals(pwd)) {
                String error=null;
                if (post_login.equals(login)) {
                    error="0";
                } 
                else {
                    error="1";
                }
                res.sendRedirect("login?error="+error);
            }
            else {
                session=req.getSession();
                session.setAttribute("key","pass");
                res.sendRedirect("success");
            }
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path=req.getServletPath();
        if("/".equals(path) || "/login".equals(path)){
            session=req.getSession();
            if(session.getAttribute("key")!=null) {
                res.sendRedirect("success?connexion=déja connecté");
            } else {
                res.sendRedirect("login");
            }
        }
    }
}