/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jboss.logging.Logger;


@Singleton
public class AlertSchedule {

    private final Logger log = Logger.getLogger(getClass().getName());

    @Schedule(hour = "*", dayOfWeek = "*", info = "Todos los dias a las 9:00")
    //@Schedule(second = "*", minute = "*/5", hour = "*", info = "cada 5 minutos")

    public void performTask() throws EmailException {

        long timeInit = System.currentTimeMillis();
        
        log.info(":. Inicio TareaProgramada.");
        
        Email email = new SimpleEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("rramirezech@gmail.com", "tdsystems"));
        email.setSSLOnConnect(true);
        email.setFrom("rramirezech@gmail.com");
        email.setSubject("Correo de Prueba");
        email.setMsg("Este es un correo de prueba");
        email.addTo("rramirezech@hotmail.com");
        email.send();

        try {
            timeInit = System.currentTimeMillis();
            // TODO Hacer la logica de la tarea programada

        } 
        catch (Exception e) {
            log.error("Error en la tarea programada");
            log.info(e);

            long time = System.currentTimeMillis();
            time = System.currentTimeMillis() - timeInit;
            log.info(":. Fin tarea programada. Tiempo de proceso = " + time);

        }
    }
}







    
