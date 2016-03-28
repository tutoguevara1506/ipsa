/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquetes;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    
    private List<CatCalendario> listaMttosPre;
    private LogAlertas logalertas;
    private List<LogAlertas> logale;
    private String id_log_ale, fec_ale, id_tip_ale,  ale_des;
    //private String id_ale, cod_dep, id_tip_ale, aviso, recordatorio, id_estado, nom_dep, nom_tip_ale;
    
    private final Logger log = Logger.getLogger(getClass().getName());
    private String hostname, smtp_port, user, pass, remitente;
    Calendar calendar =Calendar.getInstance(); //obtiene la fecha de hoy 

    
        
    @Schedule(hour = "8", dayOfWeek = "*", info = "Todos los dias a las 8:00 a.m.")
    //@Schedule(second = "*", minute = "*/10", hour = "*", persistent= true, info = "cada 2 minutos")

    public void performTask() throws EmailException {

        long timeInit = System.currentTimeMillis();
        ConfiguracionMail();
              
        log.info(":. Inicio TareaProgramada cada dia");
                
        try {
            timeInit = System.currentTimeMillis();
               
            // TLS agregado para server AWS
            //email.setStartTLSEnabled(true);
            //email.setStartTLSRequired(true);
            
            obtenerAlertas();
                        
            // Luego de obtener las alertas las recorremos para conformar cada uno de los selects de validacion
            alertas.stream().forEach((ale) -> {
                
                llenarAlertasUsuarios(ale.getId_ale());
                verificarPorTipoAlerta(ale);
                
                logale.stream().forEach((lgal) ->{
                    
                    try {
                        Email email = new SimpleEmail();
                        email.setHostName(this.hostname);
                        email.setSmtpPort(Integer.parseInt(this.smtp_port));
                        email.setAuthenticator(new DefaultAuthenticator(this.user, this.pass));
                        email.setSSLOnConnect(true);
                        email.setFrom(this.remitente);
                        //String[] recipients = {"rramirezech@hotmail.com", "rramirezech@outlook.com", "rramirezech@gmail.com"};
                        email.setSubject(lgal.getNom_tip_ale());
                        email.setMsg(lgal.getAle_des());
                        
                        alertasusuarios.stream().forEach((mailDestino) ->{
                            try {
                                email.addTo(mailDestino.getMailusu());
                            } catch (Exception e){
                                System.out.println("Error en la obtencion de destinatarios. " + e.getMessage());                        
                            }
                        });
                        
                        email.send();
                    }
                    catch (Exception e){
                        System.out.println("Error en la conformacion del correo. " + e.getMessage());                        
                    }
                });
                
            });                
        } 
        catch (Exception e) {
            log.error("Error en la tarea programada");
            log.info(e);
        }
        
        long time = System.currentTimeMillis();
        time = System.currentTimeMillis() - timeInit;
        log.info(":. Fin tarea programada. Tiempo de proceso = " + time);   
        
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
            
            alertas = new ArrayList<>();
            
            mQuery = "select ale.id_ale, ale.cod_dep, ale.id_tip_ale, ale.aviso, ale.recordatorio, ale.id_estado, dep.nom_dep, tip.nom_tip_ale "
                    +"from cat_ale ale inner join cat_dep dep on ale.cod_dep = dep.cod_dep "
                    + "inner join cat_tip_ale tip on ale.id_tip_ale = tip.id_tip_ale order by ale.id_ale;";
            
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
                        resVariable.getString(8)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
        }   
    }
    
    public void verificarPorTipoAlerta(CatAlertas ale){
               
        switch (ale.getId_tip_ale()) {
 
            case "1":       // Mantenimientos Preventivos
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                llenarMttosPreventivos(ale);
                Date hoy = calendar.getTime();
                logale = new ArrayList<>();
                
                listaMttosPre.stream().forEach((mtto) -> {
                    String mQuery = "";
                    id_log_ale="";
                    fec_ale = format.format(hoy);                    
                    id_tip_ale = ale.getId_tip_ale();
                    ale_des = "Faltan " + ale.getAviso() + " dias para realizar el mantenimiento preventivo del "
                            + "equipo "+ mtto.getDes_equ();
                    
                    
                    try {
                            Accesos mAccesos = new Accesos();
                            mAccesos.Conectar();
                            mQuery = "select ifnull(max(id_log_ale),0)+1 as codigo from log_ale;";
                                id_log_ale = mAccesos.strQuerySQLvariable(mQuery);
                                mAccesos.Desconectar();
                    } catch (Exception e) {
                            System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
                        }
                    
                    //System.out.println("desAlerta = "+ DesAlerta + "; fecha alerta= "+ fechaAlerta+"; tipoAlerta= "+tipoAlerta+"; tipoAlerta2= "+tipoAlerta2);
                    
                                    
                    logale.add(new LogAlertas(
                            id_log_ale,
                            fec_ale,
                            id_tip_ale,
                            ale_des, ""
                    ));
                    
                    guardar();
                    
                });
                
            break;

            case "2":       // Vida util de repuestos 
                System.out.println("dos");
            break;

            case "3":       // Tiempo para adquisicion de repuestos
                System.out.println("tres");
            break;

        }
               
    }


    public void llenarAlertasUsuarios(String alerta) {
        String mQuery = "";
        try {
            
            alertasusuarios = new ArrayList<>();

            mQuery = "select aleusu.id_ale_usu, aleusu.id_ale, aleusu.cod_usu, usu.nom_usu, per.email from "
                    + "cat_ale_usu aleusu inner join cat_usu usu on aleusu.cod_usu = usu.cod_usu "
                    + "inner join  cat_persona per on usu.cod_usu = per.cod_usu "
                    + "WHERE aleusu.id_ale = "+alerta+" order by id_ale_usu;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                alertasusuarios.add(new CatAlertasUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de usuarios. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void llenarLogAlertas() {
        String mQuery = "";
        try {
            
            logale = new ArrayList<>();

            mQuery = "select lale.id_log_ale, lale.fec_ale, lale.id_tip_ale, lale.ale_des, tip.nom_ale from log_ale lale "
                    + "inner join cat_tip_ale tip  on lale.id_tip_ale = tip.id_tip_ale order by id_ale_usu;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                logale.add(new LogAlertas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de log de alertas. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void llenarMttosPreventivos(CatAlertas ale) {
        String mQuery = "";
        
        try {
            
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            
            listaMttosPre = new ArrayList<>();
            calendar.roll(Calendar.DATE, Integer.parseInt(ale.getAviso())); // roll resta dias add suma 
            Date fech = calendar.getTime();
            Date hoy = new Date();
            
            
            listaMttosPre = new ArrayList<>();

            mQuery = " select tbl_mae_man.cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, des_equ, "
                     + "if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=2,'yellow','red')) as color,"
                    + " week(fec_ini,1) as semana "
                    + " from tbl_mae_man inner join lis_equ on "
                    + " tbl_mae_man.cod_lis_equ = lis_equ.cod_lis_equ "
                    + " where cod_tip = 1 AND "
                    + " det_sta IN (1,3) AND fec_ini >= '" + format.format(fech) + "' AND fec_ini <= '" + format.format(hoy) + "' order by cod_man;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                listaMttosPre.add(new CatCalendario(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getDate(5),
                        resVariable.getDate(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10),
                        resVariable.getString(11)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Calendarización. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void guardar() {
        
        String mQuery = "";
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                mQuery = "insert into log_ale (id_log_ale, fec_ale, id_tip_ale, ale_des) "
                            + "values (" + id_log_ale + ", str_to_date('" +fec_ale+ "', '%d/%m/%Y %H:%i')," + id_tip_ale + ",'"+ale_des+"');";
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                
            } catch (Exception e) {
                System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            
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

    public String getAle_des() {
        return ale_des;
    }

    public void setAle_des(String ale_des) {
        this.ale_des = ale_des;
    }

    public String getId_tip_ale() {
        return id_tip_ale;
    }

    public void setId_tip_ale(String id_tip_ale) {
        this.id_tip_ale = id_tip_ale;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    
    
    
    
}