package main.java.com.itu.data;

import java.sql.ResultSet;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class Result {
    String sql;
    ResultSet result;
    String[] ColumnName;
    String[] ColumnType;
    int row;
    int column;

    // getter
    public String getSql() {
        return this.sql;
    }
    public ResultSet getResult() {
        return this.result;
    }
    public int getRow() {
        return this.row;
    }
    public int getColumn() {
        return this.column;
    }
    public String[] getColumnName() {
        return this.ColumnName;
    }
    public String[] getColumnType() {
        return this.ColumnType;
    }

    // setter
    public void setSql(String sql) {
        this.sql = sql;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public void setColumn(int column) {
        this.column = column;
    }
    public void setColumnName(String[] ColumnName) {
        this.ColumnName = ColumnName;
    }
    public void setColumnType(String[] columnType) {
        ColumnType = columnType;
    }

    public int getRow(String sql) {
        try {
            MaConnexion Mcon = new MaConnexion();
            Mcon.ConnectToDb();
            String sql1 = "select count(*) ";
            sql = sql.substring(sql.indexOf("from"));
            sql1 = sql1.concat(sql);
            ResultSet count = Mcon.makeResultSet(sql1);
            count.next();
            int row = count.getInt(1);
            count.close();
            return row;
        } catch (Exception e) {
            return 0;
        }
    }

    public Result(String sql, ResultSet result) throws Exception {
        this.setSql(sql);
        this.setResult(result);
        this.setRow(this.getRow(sql));
        this.setColumn(result.getMetaData().getColumnCount());
        String []ColumnName=new String[this.getColumn()];
        String []ColumnType=new String[this.getColumn()];
        int i=0;
        while(i<ColumnName.length) {
            ColumnName[i]=result.getMetaData().getColumnName(i+1);
            ColumnType[i]=result.getMetaData().getColumnTypeName(i+1);
            i++;
        }
        this.setColumnName(ColumnName);
        this.setColumnType(ColumnType);
    }

    public Result(String sql) throws Exception {
        this.setSql(sql);
        MaConnexion Mcon = new MaConnexion();
        ResultSet resultSet=Mcon.makeResultSet(sql);
        this.setResult(resultSet);
        this.setRow(this.getRow(sql));
        this.setColumn(result.getMetaData().getColumnCount());
        String []ColumnName=new String[this.getColumn()];
        String []ColumnType=new String[this.getColumn()];
        int i=0;
        while(i<ColumnName.length) {
            ColumnName[i]=result.getMetaData().getColumnName(i+1);
            ColumnType[i]=result.getMetaData().getColumnTypeName(i+1);
            i++;
        }
        this.setColumnName(ColumnName);
        this.setColumnType(ColumnType);
    }

    public Object[][] getObject() throws Exception{
        Object[][] value = new Object[this.getRow()][this.getColumn()];
        try {
            int i = 0;
while (this.getResult().next()) {
    int j = 0;
    while (j < this.getColumn()) { 
        Object valueObj;
        int columnType = this.getResult().getMetaData().getColumnType(j + 1); // Récupère le type de colonne
        switch (columnType) {
            case java.sql.Types.TIMESTAMP:
                valueObj = this.getResult().getTimestamp(j + 1);
                break;
            case java.sql.Types.DATE:
                valueObj = this.getResult().getDate(j + 1);
                break;
            case java.sql.Types.TIME:
                valueObj = this.getResult().getTime(j + 1);
                break;
            case java.sql.Types.BIGINT:
                valueObj = this.getResult().getLong(j + 1);
                break;
            case java.sql.Types.INTEGER:
                valueObj = this.getResult().getInt(j + 1);
                break;
            case java.sql.Types.FLOAT:
                valueObj=this.getResult().getFloat(j+1);
            case java.sql.Types.DOUBLE:
                valueObj = this.getResult().getDouble(j + 1);
                break;
            case java.sql.Types.NUMERIC:
            case java.sql.Types.DECIMAL:
                valueObj = this.getResult().getBigDecimal(j + 1);
                break;
            case java.sql.Types.BOOLEAN:
                valueObj = this.getResult().getBoolean(j + 1);
                break;
            case java.sql.Types.BLOB:
                valueObj = this.getResult().getBlob(j + 1);
                break;
            case java.sql.Types.CLOB:
                valueObj = this.getResult().getClob(j + 1);
                break;
            case java.sql.Types.VARCHAR:
            case java.sql.Types.CHAR:
            case java.sql.Types.LONGVARCHAR:
                valueObj = this.getResult().getString(j + 1);
                break;
            default:
                valueObj = this.getResult().getObject(j + 1); // Fallback pour les types inconnus
                break;
        }
        value[i][j] = valueObj;
        j++;
    }
    i++;

}
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.getResult().close();
        }
        return value;
    }

    public static Class<?>[] getFieldClass(String classname) throws Exception{
        Field[] fields=Class.forName(classname).getDeclaredFields();
        Class<?>[] fieldclass=new Class[fields.length];
        int i=0;
        while(i<fields.length) {
            fields[i].setAccessible(true);
            fieldclass[i]=fields[i].getType();
            i++;
        }
        return fieldclass;
    }

    public static Object[] setObject(String classname,Object [][]obj) throws Exception{
        Class <?>cl=Class.forName(classname);
        Object []inst=new Object[obj.length];
        int i=0;
        try{
            while(i<obj.length) { 
                int k=0;
                Constructor<?>[] constructor=cl.getDeclaredConstructors();
                if(constructor.length==1){k=0;}
                else if(!constructor[k].equals(cl.getDeclaredConstructor(Result.getFieldClass(classname)))  
                || !constructor[k].equals(cl.getDeclaredConstructor(Result.getFieldClass(classname))))
                {}
                else{k++;}
                inst[i]=constructor[k].newInstance(obj[i]);
                i++;
            }
            return inst;
        } catch(IllegalArgumentException e){e.printStackTrace();return null;}
    }

    public static Object[] setObject(String classname,Object []obj) throws Exception{
        Class <?>cl=Class.forName(classname);
        Object []inst=new Object[1];
        int i=0;
        try{
            while(i<obj.length) { 
                int k=0;
                Constructor<?>[] constructor=cl.getDeclaredConstructors();
                if(constructor.length==1){k=0;}
                else if(!constructor[k].equals(cl.getDeclaredConstructor(Result.getFieldClass(classname)))  
                || !constructor[k].equals(cl.getDeclaredConstructor(Result.getFieldClass(classname))))
                {}
                else{k++;}
                inst[i]=constructor[k].newInstance(obj);
                i++;
            }
            return inst;
        } catch(IllegalArgumentException e){e.printStackTrace();return null;}
    }

}