package main.java.com.itu.Etat;

import java.io.IOException;

import javax.naming.Context;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.java.com.itu.model.Depense;
import main.java.com.itu.model.Prevision;
import jakarta.servlet.http.Cookie;

public class EtatServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        try {
            Depense[] depenses=Depense.findAll();
            Prevision[] previsions=Prevision.findAll();
            req.setAttribute("previsions", previsions);
            req.setAttribute("depenses", depenses);
        } catch(Exception e) {

        }
    }
}
