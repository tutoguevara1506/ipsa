/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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
    private CatConfiguracionMail catconfiguracionmail;
    private List<CatConfiguracionMail> confmail;
    private String hostname, smtp_port, user, pass, remitente;
        
    //@Schedule(hour = "*", dayOfWeek = "*", info = "Todos los dias cada hora server")
    @Schedule(second = "*", minute = "*/5", hour = "*", info = "cada 5 minutos")

    public void performTask() throws EmailException {

        long timeInit = System.currentTimeMillis();
        ConfiguracionMail();
        
        log.info(":. Inicio TareaProgramada cada 5 minutos");
        log.info(hostname);
        log.info(smtp_port);
        log.info(":. fin de variables-----------");
        
        Email email = new SimpleEmail();
        email.setHostName(hostname);
        email.setSmtpPort(Integer.parseInt(smtp_port));
        email.setAuthenticator(new DefaultAuthenticator(user, pass));
        email.setSSLOnConnect(true);
        email.setFrom(remitente);
        email.setSubject("Correo de Prueba");
        email.setMsg("Este es un correo de prueba desde mi entorno con variables");
        
        String[] recipients = {"rramirezech@hotmail.com", "tutoguevara1506@gmail.com"};

        for (int i = 0; i < recipients.length; i++)
        {
             email.addTo(recipients[i]);
        }
        
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
    
    public void ConfiguracionMail() {
        String mQuery = "";
        try {
            
            catconfiguracionmail = new CatConfiguracionMail();
            confmail = new ArrayList<>();

            mQuery = "SELECT  id_conf_mail, des_conf_mail, hostname, smtp_port, user, pass, remitente FROM ipsa.cat_conf_mail;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                confmail.add(new CatConfiguracionMail(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public List<CatConfiguracionMail> getConfmail() {
        return confmail;
    }

    public void setConfmail(List<CatConfiguracionMail> confmail) {
        this.confmail = confmail;
    }   

    public CatConfiguracionMail getCatconfiguracionmail() {
        return catconfiguracionmail;
    }

    public void setCatconfiguracionmail(CatConfiguracionMail catconfiguracionmail) {
        this.catconfiguracionmail = catconfiguracionmail;
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