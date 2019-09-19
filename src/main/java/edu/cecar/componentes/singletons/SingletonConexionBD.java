/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cecar.componentes.singletons;

import edu.cecar.componentes.baseDatos.ConectarMySQL;
import edu.cecar.controladores.ControladorApiGoRest;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1102888499
 * Singleton que devuelve la coneccion a una base de datos      // Lazy //
 */
public class SingletonConexionBD {
    private static Connection connection;
    private static ControladorApiGoRest rest;
    public static Connection getInstance() {
        if (connection == null){
            try {
                Properties properties = new Properties();
                properties.load(new FileInputStream("Recursos/conexion.properties"));
                connection = new ConectarMySQL(properties.getProperty("host"), 
                                               properties.getProperty("baseDatos"),
                                               properties.getProperty("port"),
                                               properties.getProperty("usuario"),
                                               properties.getProperty("password")).getConnection();
            } catch (Exception ex) {
                Logger.getLogger(SingletonConexionBD.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
    
    public static ControladorApiGoRest cv(){
        if(rest==null){
            rest=new ControladorApiGoRest();
        }
        return rest;
    }
}
