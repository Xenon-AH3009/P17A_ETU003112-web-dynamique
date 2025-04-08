package main.java.com.itu.Prevision;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.com.itu.model.Prevision;

public class PrevisionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String libelle=(String)req.getParameter("libelle");
        String montantString=(String)req.getParameter("montant");
        float montant=Float.parseFloat(montantString);
        Prevision prevision=new Prevision(libelle, montant);
        prevision.save(libelle,montant);
        res.sendRedirect("home");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        
    }
}