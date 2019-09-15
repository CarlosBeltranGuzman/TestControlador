/**
 *
 * Clase: ConectarMySQL
 * 
 * @version: 0.1
 *  
 * @since: 5/08/2019
 * 
 * Fecha de Modificaci�n:
 * 
 * @author: Carlos Beltrán Guzmán
 * 
 * Copyright: CECAR
 */


package edu.cecar.componentes.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Componente que permite conectarse a una base de datos MySQL
 * 
 */
public class ConectarMySQL {

	private Connection connection;


	public ConectarMySQL(String servidorBD, String nombreBD, String puerto, String usuario, String password) {

		try {

			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();

			
			// Se crea un URL hacia la maquina y la base de datos
			String url= "jdbc:mysql://" + servidorBD + ":"+ puerto + "/" + nombreBD + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"; 

			//se crea la conexion a la base de datos
			connection=DriverManager.getConnection(url,usuario,password);

		}catch  (Exception e) {

			System.out.println("Error"+e);
		}
	}


	public Connection getConnection() {
		return connection;
	}

	public void cerrarConexion()  {
		
		try {
			
			connection.close();
			
		} catch (SQLException e) {
			

			e.printStackTrace();
		}
		
	}
}
