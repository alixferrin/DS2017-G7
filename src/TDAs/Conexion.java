/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Hawk
 */
public class Conexion {
    private static final Conexion instance = new Conexion();
    private Connection conexion = null;
    private ResultSet result = null;
    private CallableStatement procedure= null;
    public static String asisRest = null;
    
    private Conexion(){};
    
    public static Conexion getInstance(){
        return instance;
    }

    public Connection getConnexion() {
        return conexion;
    }
    
    public ResultSet getResultSet(){
        return result;
    }
    
    public boolean iterarResultado() throws SQLException{
        return result.next();
    }
    
    public String getResultFila(int num) throws SQLException{
        return result.getString(num);
    }

    public CallableStatement getProcedure() {
        return procedure;
    }

    public String getAsisRest() {
        return asisRest;
    }
    
    public void setProcedure(String call) throws SQLException{
        procedure = conexion.prepareCall(call);
    }
    
    public void setConnexion(String user, String password) throws SQLException{
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/sistemabares?verifyServerCertificate=false&useSSL=true", user, password);
    }
//    jdbc:mysql://localhost:3306/Peoples?autoReconnect=true&useSSL=false
    
    public void ejecutarQuery() throws SQLException{
        result = procedure.executeQuery();
    }
}
