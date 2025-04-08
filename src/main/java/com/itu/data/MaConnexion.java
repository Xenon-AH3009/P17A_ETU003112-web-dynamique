package main.java.com.itu.data;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaConnexion {
    String url;
    String user;
    String password;

    //getter
    public String getUrl() {
        return this.url;
    }
    public String getUser() {
        return this.user;
    }
    public String getPassword() {
        return this.password;
    }

    //setter
    public void setUrl(String url) {
        this.url=url;
    }
    public void setUser(String user) {
        this.user=user;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    public MaConnexion(String url,String user,String password) {
        this.setUrl(url);
        this.setUser(user);
        this.setPassword(password);
    }
    
    public MaConnexion() {
        this.setUrl("jdbc:mysql://172.80.237.54:3306/db_s2_ETU003112");
        this.setUser("root");
        this.setPassword("");
    }

    public Connection ConnectToDb() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection MaConnexion=DriverManager.getConnection(this.getUrl(),this.getUser(),this.getPassword());
            return MaConnexion;
        }catch(Exception e){e.printStackTrace();return null;}
    }

    public PreparedStatement allocMem(Connection con,String sql) throws SQLException{
        PreparedStatement state=con.prepareStatement(sql);
        return state;
    }

    public ResultSet makeResultSet(String sql) {
        try {
            MaConnexion Mcon=new MaConnexion();
            Connection con=Mcon.ConnectToDb();
            PreparedStatement state=Mcon.allocMem(con,sql);
            ResultSet result=state.executeQuery();
            return result;
        }
        catch(Exception e) {e.printStackTrace();return null;}
    }

    public static void close(Connection con) throws Exception{
        con.close();
        System.out.println("deconnected");
    }

    public static void close(ResultSet result) throws Exception{
        result.getStatement().getConnection().close();
        System.out.println("deconnected");
    }
}