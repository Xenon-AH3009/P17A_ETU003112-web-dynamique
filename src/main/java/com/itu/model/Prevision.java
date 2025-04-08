package main.java.com.itu.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import main.java.com.itu.data.MaConnexion;
import main.java.com.itu.data.Result;

public class Prevision extends BaseModel{
    private String libelle;
    private float montant;

    public Prevision(int id,String libelle,float montant) {
        this.setId(id);
        this.setLibelle(libelle);
        this.setMontant(montant);
    }

    public Prevision(String libelle,float montant) {
        this.setLibelle(libelle);
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

    public String getLibelle() {
        return this.libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
    
    public float getMontant() {
        return montant;
    }
    public void setMontant(float montant) {
        this.montant = montant;
    }
    
    public void save(String libelle,float montant) { 
        MaConnexion Mcon=new MaConnexion(); 
        Connection con=null;
        StringBuilder sql=new StringBuilder("INSERT INTO prevision(libelle,montant) VALUES");
        try {
            con=Mcon.ConnectToDb();
            String additionnal="('"+this.getLibelle()+"',"+Float.toString(this.getMontant())+")";
            sql.append(additionnal);
            PreparedStatement state=Mcon.allocMem(con,sql.toString());
            state.executeUpdate();
            con.close();
        } catch(Exception e) {
            System.out.println("Error during executing the query");
        } 
    }

    public static Prevision[] castObjectToPrevision(Object[] obj) {
        Prevision[] previsions = new Prevision[obj.length];
        for (int i = 0; i < obj.length; i++) {
            previsions[i] = (Prevision) obj[i];
        }
        return previsions;
    }

    public static Prevision[] findAll() throws Exception{
        String sql="select * from prevision";
        Result result=new Result(sql);
        Object []obj=Result.setObject("db_s2_ETU003112.Prevision",result.getObject());
        Prevision[] previsions=Prevision.castObjectToPrevision(obj);
        return previsions;        
    }
}
