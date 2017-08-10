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

/**
 *
 * @author Hawk
 */
public class Conexion {
    private static Conexion instance = new Conexion();
    private Connection connection = null;
    private ResultSet result = null;
    private CallableStatement procedure= null;
    private String asisRest = null;
    
    private Conexion(){};
    
    public static Conexion getInstance(){
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet getResult() {
        return result;
    }

    public CallableStatement getProcedure() {
        return procedure;
    }

    public String getAsisRest() {
        return asisRest;
    }
    
}
