/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TDAs.strategy;

import TDAs.Conexion;
import java.sql.SQLException;

/**
 *
 * @author HOME
 */
public class Carnet implements FormaDePago {
    
    @Override
    public void pagar(String id, double cantidad) {
        Conexion con = Conexion.getInstance();
        try{
            con.setProcedure("Call updateSaldos('" + id + "'," + cantidad + ")");
            con.ejecutarQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
}
