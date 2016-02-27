/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import java.sql.ResultSet;
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
    private String hostname, smtp_port, user, pass, remitente;
        
    @Schedule(hour = "*", dayOfWeek = "*", info = "Todos los dias cada hora server")
    //@Schedule(second = "*", minute = "*/10", hour = "*", persistent= true, info = "cada 2 minutos")

    public void performTask() throws EmailException {

        long timeInit = System.currentTimeMillis();
        ConfiguracionMail();
              
        log.info(":. Inicio TareaProgramada cada 5 minutos");
                
        try {
            timeInit = System.currentTimeMillis();
            Email email = new SimpleEmail();
            email.setHostName(hostname);
            email.setSmtpPort(Integer.parseInt(smtp_port));
            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            //email.setSSLOnConnect(true);
            
            // TLS agregado para server AWS
            email.setStartTLSEnabled(true);
            email.setStartTLSRequired(true);
            email.setFrom(remitente);
            email.setSubject("Correo de Prueba");
            email.setMsg("Este es un correo de prueba desde server de prueba con seguridad TLS");
        
            String[] recipients = {"rramirezech@hotmail.com"};

            for (int i = 0; i < recipients.length; i++)
            {
                email.addTo(recipients[i]);
            }
        
            email.send();

        } 
        catch (Exception e) {
            log.error("Error en la tarea programada");
            log.info(e);

            long time = System.currentTimeMillis();
            time = System.currentTimeMillis() - timeInit;
            log.info(":. Fin tarea programada. Tiempo de proceso = " + time);

        }
    }
    
    public void ConfiguracionMail() {
        String mQuery = "";
        try {
            
            mQuery = "SELECT  hostname, smtp_port, user, pass, remitente FROM ipsa.cat_conf_mail;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            
            while (resVariable.next()) {
                hostname =    resVariable.getString(1);
                smtp_port =   resVariable.getString(2);
                user =        resVariable.getString(3);
                pass =        resVariable.getString(4);
                remitente =   resVariable.getString(5);
            }            
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getSmtp_port() {
        return smtp_port;
    }

    public void setSmtp_port(String smtp_port) {
        this.smtp_port = smtp_port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    } 

}