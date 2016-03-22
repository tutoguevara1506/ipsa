/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.jboss.logging.Logger;


@Singleton
public class AlertSchedule {

    private CatAlertas catalertas;
    private List<CatAlertas> alertas;
    private CatAlertasUsuarios catalertasusuarios;
    private List<CatAlertasUsuarios> alertasusuarios;
    private LogAlertas logalertas;
    private List<LogAlertas> logale;
    private String id_log_ale, fec_ale, cod_lis_equ, ale_des, tip_ale, ale_destino;
    
    private final Logger log = Logger.getLogger(getClass().getName());
    private String hostname, smtp_port, user, pass, remitente;
    
        
   // @Schedule(hour = "8", dayOfWeek = "*", info = "Todos los dias a las 8:00 a.m.")
    @Schedule(second = "*", minute = "*/10", hour = "*", persistent= true, info = "cada 2 minutos")

    public void performTask() throws EmailException {

        long timeInit = System.currentTimeMillis();
        ConfiguracionMail();
        Date hoy = new Date();
              
        log.info(":. Inicio TareaProgramada cada dia");
                
        try {
            timeInit = System.currentTimeMillis();
            Email email = new SimpleEmail();
            email.setHostName(hostname);
            email.setSmtpPort(Integer.parseInt(smtp_port));
            email.setAuthenticator(new DefaultAuthenticator(user, pass));
            email.setSSLOnConnect(true);
            
            // TLS agregado para server AWS
            //email.setStartTLSEnabled(true);
            //email.setStartTLSRequired(true);
            
            obtenerAlertas();
            
            
            // Luego de obtener las alertas las recorremos para conformar cada uno de los selects de validacion
            alertas.stream().forEach((ale) -> {
                String mQuery="";
                
                try {
                    //String pri= mAccesos.dmlPrimaryvariable(ale.getTabla_ctrl());
                    mQuery ="SELECT "+ale.getCamp_ctrl()+" FROM "+ ale.getTabla_ctrl();
                    ResultSet resVariable;
                    Accesos mAccesos = new Accesos();
                    mAccesos.Conectar();
                                       
                    resVariable = mAccesos.querySQLvariable(mQuery);

                    logale.add(new LogAlertas(
                            resVariable.getString(1),
                            resVariable.getString(2),
                            resVariable.getString(3),
                            resVariable.getString(4),
                            resVariable.getString(5),
                            resVariable.getString(6)                
                    ));

                    mAccesos.Desconectar();
                }
                catch (Exception e) {
                    System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
                }
                
                
            });
                
            
            
            
            
            email.setFrom(remitente);
            email.setSubject("Correo de Prueba");
            email.setMsg("Este es un correo de prueba desde server de prueba con seguridad TLS");
        
            String[] recipients = {"rramirezech@hotmail.com", "rramirezech@outlook.com", "rramirezech@gmail.com"};

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
    
    public void obtenerAlertas() {

        String mQuery = "";
        
        try {
            
            mQuery = "SELECT  id_ale, cod_dep, tabla_ctrl, camp_ctrl, camp_ref, alerta, aviso, recordatorio, id_estado "
                    + " FROM ipsa.cat_ale;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                alertas.add(new CatAlertas(
                resVariable.getString(1),
                resVariable.getString(2),
                resVariable.getString(3),
                resVariable.getString(4),
                resVariable.getString(5),
                resVariable.getString(6),
                resVariable.getString(7),
                resVariable.getString(8),
                resVariable.getString(9),
                resVariable.getString(10)
                ));
            }            
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
        }   
    }


    public void llenarAlertasusu() {
        String mQuery = "";
        try {
            alertasusuarios = new ArrayList<>();

            mQuery = "select id_ale_usu, id_ale, cod_usu, nom_usu order by id_ale_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                alertasusuarios.add(new CatAlertasUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de usuarios. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void guardar() {
        String mQuery = "";
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_log_ale)) {
                    mQuery = "select ifnull(max(id_log_ale),0)+1 as codigo from log_ale;";
                    id_log_ale = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into log_ale (id_log_ale, fec_ale, cod_lis_equ, ale_des, tip_ale, ale_destino) "
                            + "values (" + id_log_ale + ",'" + fec_ale + "'," + cod_lis_equ + ",'"+ale_des+"',"+tip_ale+",'"+ale_destino+");";
                } else {
                    mQuery = "update log_ale SET "
                            + " fec_ale = '" + fec_ale + "', "
                            + " cod_lis_equ = " + cod_lis_equ + ", "
                            + " ale_des = '" + ale_des + "', "
                            + " tip_ale = " + tip_ale + ", "
                            + " ale_destino = '" + ale_destino + "' "                            
                            + "WHERE id_log_ale = " + id_log_ale + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Usuario", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Usuario", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertasusu();
    }
    
     public void addMessage(String summary, String detail, int tipo) {
        FacesMessage message = new FacesMessage();
        switch (tipo) {
            case 1:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
                break;
            case 2:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
                break;
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public CatAlertas getCatalertas() {
        return catalertas;
    }

    public void setCatalertas(CatAlertas catalertas) {
        this.catalertas = catalertas;
    }

    public List<CatAlertas> getAlertas() {
        return alertas;
    }

    public void setAlertas(List<CatAlertas> alertas) {
        this.alertas = alertas;
    }

    public LogAlertas getLogalertas() {
        return logalertas;
    }

    public void setLogalertas(LogAlertas logalertas) {
        this.logalertas = logalertas;
    }

    public List<LogAlertas> getLogale() {
        return logale;
    }

    public void setLogale(List<LogAlertas> logale) {
        this.logale = logale;
    }

    public CatAlertasUsuarios getCatalertasusuarios() {
        return catalertasusuarios;
    }

    public void setCatalertasusuarios(CatAlertasUsuarios catalertasusuarios) {
        this.catalertasusuarios = catalertasusuarios;
    }

    public List<CatAlertasUsuarios> getAlertasusuarios() {
        return alertasusuarios;
    }

    public void setAlertasusuarios(List<CatAlertasUsuarios> alertasusuarios) {
        this.alertasusuarios = alertasusuarios;
    }

    public String getId_log_ale() {
        return id_log_ale;
    }

    public void setId_log_ale(String id_log_ale) {
        this.id_log_ale = id_log_ale;
    }

    public String getFec_ale() {
        return fec_ale;
    }

    public void setFec_ale(String fec_ale) {
        this.fec_ale = fec_ale;
    }

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getAle_des() {
        return ale_des;
    }

    public void setAle_des(String ale_des) {
        this.ale_des = ale_des;
    }

    public String getTip_ale() {
        return tip_ale;
    }

    public void setTip_ale(String tip_ale) {
        this.tip_ale = tip_ale;
    }

    public String getAle_destino() {
        return ale_destino;
    }

    public void setAle_destino(String ale_destino) {
        this.ale_destino = ale_destino;
    }
    
    
}