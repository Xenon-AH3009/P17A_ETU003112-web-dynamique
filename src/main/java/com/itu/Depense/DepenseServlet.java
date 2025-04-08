package main.java.com.itu.Depense;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.com.itu.model.Depense;
import main.java.com.itu.model.Prevision;

public class DepenseServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        String path = req.getServletPath();
        if("/insererpage".equals(path)){
            String prevision=req.getParameter("prevision");
            String montantString=req.getParameter("montant");
            float montant=Float.parseFloat(montantString);
            Depense depense=new Depense(prevision, montant);
            depense.save(prevision,montant);
            res.sendRedirect("home");  
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String path = req.getServletPath();
        if("/depense".equals(path)){
            try {
            Prevision[] previsions=Prevision.findAll();
            req.setAttribute("previsions", previsions);
            } catch(Exception e){}
        }
    }
}
