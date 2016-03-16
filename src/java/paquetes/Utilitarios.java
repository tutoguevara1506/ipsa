package paquetes;

import java.sql.*;

public class Utilitarios extends Conexion {

    public Utilitarios() {
    }

    public static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
  
}
