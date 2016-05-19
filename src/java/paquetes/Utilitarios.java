package paquetes;

import java.sql.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.faces.context.FacesContext;

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
    
    public java.sql.Date sumarFechas(java.sql.Date fch, int dias){ 
        Calendar cal = new GregorianCalendar(); 
        cal.setTimeInMillis(fch.getTime()); 
        cal.add(Calendar.DATE, dias); 
        return new Date(cal.getTimeInMillis()); 
    } 

   public java.sql.Date restarFechas(java.sql.Date fch, int dias){ 
     Calendar cal = new GregorianCalendar(); 
     cal.setTimeInMillis(fch.getTime()); 
     cal.add(Calendar.DATE, - dias); 
     return new Date(cal.getTimeInMillis()); 
   }  
     
}
