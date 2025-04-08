package main.java.com.itu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.java.com.itu.data.MaConnexion;
import main.java.com.itu.data.Result;

public class Depense extends BaseModel{
    private String libelle;
    private Float montant;

    public Depense(int id,String libelle,Float montant) {
        this.setId(id);
        this.setPrevision(libelle);
        this.setMontant(montant);
    }

    public Depense(String libelle,Float montant) {
        this.setPrevision(libelle);
        this.setMontant(montant);
    }

    @Override
    public int getId() {
        // TODO Auto-generated method stub
        return super.getId();
    }

    @Override
    public void setId(int id) {
        // TODO Auto-generated method stub
        super.setId(id);
    }

    public String getPrevision() {
        return this.libelle;
    }
    public void setPrevision(String libelle) {
        this.libelle = libelle;
    }
    
    public Float getMontant() {
        return montant;
    }
    public void setMontant(Float montant) {
        this.montant = montant;
    }
    
    public void save(String libelle,Float montant) { 
        MaConnexion Mcon=new MaConnexion(); 
        Connection con=null;
        StringBuilder sql=new StringBuilder("INSERT INTO depense(prevision,montant) VALUES");
        try {
            con=Mcon.ConnectToDb();
            String additionnal="('"+this.getPrevision()+"',"+Float.toString(this.getMontant())+")";
            sql.append(additionnal);
            PreparedStatement state=Mcon.allocMem(con,sql.toString());
            state.executeUpdate();
            con.close();
        } catch(Exception e) {
            System.out.println("Error during executing the query");
        } 
    }

    public static Depense[] castObjectToDepense(Object[] obj) {
        Depense[] Depenses = new Depense[obj.length];
        for (int i = 0; i < obj.length; i++) {
            Depenses[i] = (Depense) obj[i];
        }
        return Depenses;
    }

    public static Depense[] findAll() throws Exception{
        String sql="select * from depense";
        Result result=new Result(sql);
        Object []obj=Result.setObject("db_s2_ETU003112.Depense",result.getObject());
        Depense[] Depenses=Depense.castObjectToDepense(obj);
        return Depenses;        
    }
}