package paquetes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.component.timeline.TimelineUpdater;
import org.primefaces.extensions.event.timeline.TimelineAddEvent;
import org.primefaces.extensions.event.timeline.TimelineModificationEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;

@Named
@ConversationScoped
public class ManPronosticoMtto implements Serializable {

    private static final long serialVersionUID = 8774297541534938L;
    @Inject
    Login cbean;
    private CatPronosticoMtto catpronosticomtto;
    private List<CatPronosticoMtto> pronosticomtto;
    private CatDetallePronosticoMtto catdetallepronosticomtto;
    private List<CatDetallePronosticoMtto> detallepronosticomtto;    
    private CatMantenimientos catmantenimientos;
    private List<CatMantenimientos> mantenimientos;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatListaEquipos catlistaequipos;
    private List<CatListaEquipos> lequipos;
    private CatCalendario catcalendario;
    private List<CatCalendario> listaMttos;
    private String id_pro_mtto, nom_pro_mtto, fecha_pro_mtto, anho_origen, anho_pro_mtto, aprobado;
    private String id_det_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta,
            cod_usu, nomtip, status, datraso, color, cod_per, periodo, flg_ext, cod_sup, turno, cod_pri, cod_dep, cod_alt, obs_tec, otr_per, nomequ;
    private Date mfecha, mfec_fin;

    // Variables para timeline
    private TimelineModel modelTimeLine;
    private TimelineEvent tlevent;

    
    public ManPronosticoMtto() {
    }
    
    // Metodos para cabecera del pronostico de mantenimientos
    
    public void iniciarventana(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = format.format(mfecha);
        anho_origen = "";
        anho_pro_mtto = "";
        llenarPronosticos();
        llenarListaEquipos();
    }

    public void cerrarventana() {
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = "";
        anho_origen = "";
        anho_pro_mtto = "";
        pronosticomtto = new ArrayList<>();
        lequipos = new ArrayList<>();
    }
    
    public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = format.format(mfecha);
        anho_origen = "";
        anho_pro_mtto = "";
        catpronosticomtto = new CatPronosticoMtto();
        llenarListaEquipos();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_pro_mtto)) {
                    mQuery = "select ifnull(max(id_pro_mtto),0)+1 as codigo from cat_pro_mtto;";
                    id_pro_mtto = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_pro_mtto (id_pro_mtto, nom_pro_mtto, fecha_pro_mtto, anho_origen, anho_pro_mtto) "
                            + "values (" + id_pro_mtto + ",'" + nom_pro_mtto + "', str_to_date('" + fecha_pro_mtto + "','%d/%m/%Y'),"+ anho_origen +", "+ anho_pro_mtto+");";
                    
                    //llenamos los mantenimientos preventivos del anho origen (tipo 1)
                    llenarMttosPreventivos(1);
                    
                    detallepronosticomtto.stream().forEach((mpdet) -> {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String mQuery2="";

                        mQuery2 = "SELECT ifnull(max(id_det_pro_mtto),0)+1 as codigo FROM ipsa.det_pro_mtto;";
                        id_det_pro_mtto = mAccesos.strQuerySQLvariable(mQuery2);
                        cod_lis_equ = mpdet.getCod_lis_equ();
                        cod_man = mpdet.getCod_man();
                        cod_tip = mpdet.getCod_tip();
                        det_obs = mpdet.getDet_obs().replace("'", " ");
                        fec_ini = fmt.format(mpdet.getFec_ini());
                        fec_fin = fmt.format(mpdet.getFec_fin());
                        det_sta = mpdet.getDet_sta();
                        cod_usu = mpdet.getCod_usu();
                        cod_per = mpdet.getCod_per();
                        flg_ext = mpdet.getFlg_ext();
                        cod_pri = mpdet.getCod_pri();
                        cod_sup = mpdet.getCod_sup();
                        cod_dep = mpdet.getCod_dep();
                        turno = mpdet.getTurno();
                         
                        //cambio de anho de la fecha de inicio y fin
                        
                        fec_ini = fec_ini.replaceAll(fec_ini.substring(0,4), anho_pro_mtto);
                        fec_fin = fec_fin.replaceAll(fec_fin.substring(0,4), anho_pro_mtto);
                                                
                        // cambio en el correlativo del cod_man
                        
                                                
                        mQuery2 = "INSERT INTO ipsa.det_pro_mtto " +
                                  "(id_det_pro_mtto, id_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta) " +
                                  "VALUES ("+id_det_pro_mtto+","+id_pro_mtto+","+cod_lis_equ+", 1, 1,'"+ det_obs +"','" + fec_ini + "','" + fec_fin + "',1);";
                                
                                /*
                                INSERT COMPLETO-- DA ERROR PORQUE CUANDO SE TOMA UNA PLANIFICACIÓN LOS REGISTROS INGRESADOS A TRAVEZ DE NUEVO
                                EN EL TIMELINE NO TIENE LOS DATOS COMPLETOS.
                        
                                "INSERT INTO ipsa.det_pro_mtto " +
                                  "(id_det_pro_mtto, id_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, cod_per, flg_ext, cod_pri, cod_sup, cod_dep, turno) " +
                                  "VALUES ("+id_det_pro_mtto+","+id_pro_mtto+","+cod_lis_equ+","+cod_man+","+ cod_tip +",'"+ det_obs +"','" + fec_ini + "','" + fec_fin + "',"+ det_sta +","+ cod_usu +","+ cod_per +","+ flg_ext + ",'"+ cod_pri +"',"+ cod_sup +","+ cod_dep + "," + turno + ");";
                                */
                        
                        mAccesos.dmlSQLvariable(mQuery2);
                    });             
                    
                    
                } else {
                    mQuery = "update cat_pro_mtto SET "
                            + " nom_pro_mtto = '" + nom_pro_mtto + "', "
                            + " fecha_pro_mtto = str_to_date('" + fecha_pro_mtto + "','%d/%m/%Y %h:%i'), "
                            + " anho_origen = " + anho_origen + ", " 
                            + " anho_pro_mtto = " + anho_pro_mtto + " " 
                            + " WHERE id_pro_mtto = " + id_pro_mtto + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Evaluacion", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Evaluacion", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Evaluacion. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPronosticos();
        }
        nuevo();

    }
    
    public void guardarNuevoMtto(){
        
        String mQuery;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        mQuery = "select ifnull(max(id_det_pro_mtto),0)+1 as codigo from det_pro_mtto;";
        id_det_pro_mtto = mAccesos.strQuerySQLvariable(mQuery);
          
        cod_lis_equ = ((CatCalendario) tlevent.getData()).getCod_lis_equ();
        det_obs = ((CatCalendario) tlevent.getData()).getDet_obs();
        mfec_fin = ((CatCalendario) tlevent.getData()).getFec_fin();
        fec_fin = fmt.format(mfec_fin);
        fec_ini = fmt.format(tlevent.getStartDate());
        
        mQuery = "INSERT INTO ipsa.det_pro_mtto " +
                 "(id_det_pro_mtto, id_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta) " +
                 "VALUES ("+id_det_pro_mtto+","+id_pro_mtto+","+cod_lis_equ+", 1, 1,'"+ det_obs +"','" + fec_ini + "','" + fec_fin + "',1);";
                                           
        mAccesos.dmlSQLvariable(mQuery);
        mAccesos.Desconectar();
        
        llenarDetallePronosticoMtto();
        
        //TimelineUpdater timelineUpdater = TimelineUpdater.getCurrentInstance(":frmDetalleProMtto:PronosticoTimeline");
        //modelTimeLine.update(tlevent, timelineUpdater);
        
        addMessage("Guardar Mantenimiento", "Información Almacenada con éxito.", 1);    
    }

    public void eliminar() {
        String mQuery = "";
        String mQuery2 = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_pro_mtto) == false) {
            try {
                mQuery2 = "delete from det_pro_mtto where id_pro_mtto=" + id_pro_mtto + ";";
                mQuery = "delete from cat_pro_mtto where id_pro_mtto=" + id_pro_mtto + ";";
                mAccesos.dmlSQLvariable(mQuery2);
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Evaluacion", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Evaluacion", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Evaluacion. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPronosticos();
            nuevo();
        } else {
            addMessage("Eliminar Evaluaciones", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }
    
    public void autorizar() {
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_pro_mtto) == false) {
            
            String mQuery2="";
            mQuery2 = "SELECT ifnull(aprobado,0) FROM ipsa.cat_pro_mtto WHERE anho_pro_mtto ="+ anho_pro_mtto +" AND aprobado= 1;";
            aprobado = mAccesos.strQuerySQLvariable(mQuery2);
            
            if("".equals(aprobado) == true){
            
                llenarMttosPreventivos(2);
                    
                detallepronosticomtto.stream().forEach((mpdet) -> {
                    String mQuery = "";
                    SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                    cod_lis_equ = mpdet.getCod_lis_equ();

                    // Genera cod_man para cod_lis_equ de acuerdo al correlativo existente.
                    mQuery = "SELECT ifnull(max(cod_man),0)+1 as codigo FROM ipsa.tbl_mae_man WHERE cod_lis_equ ="+ cod_lis_equ +";";
                    cod_man = mAccesos.strQuerySQLvariable(mQuery);

                    cod_tip = mpdet.getCod_tip();
                    det_obs = mpdet.getDet_obs().replace("'", " ");
                    fec_ini = fmt.format(mpdet.getFec_ini());
                    fec_fin = fmt.format(mpdet.getFec_fin());
                    det_sta = mpdet.getDet_sta();
                    cod_usu = mpdet.getCod_usu();
                    cod_per = mpdet.getCod_per();
                    flg_ext = mpdet.getFlg_ext();
                    cod_pri = mpdet.getCod_pri();
                    cod_sup = mpdet.getCod_sup();
                    cod_dep = mpdet.getCod_dep();
                    turno = mpdet.getTurno();


                    mQuery = "INSERT INTO ipsa.tbl_mae_man " +
                              "(cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta) " +
                              "VALUES ("+cod_lis_equ+","+cod_man+","+ cod_tip +",'"+ det_obs +"','" + fec_ini + "','" + fec_fin + "',"+ det_sta +");";
                            
                            /*
                            Insert completo
                            "INSERT INTO ipsa.tbl_mae_man " +
                              "(cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, cod_per, flg_ext, cod_pri, cod_sup, cod_dep, turno) " +
                              "VALUES ("+cod_lis_equ+","+cod_man+","+ cod_tip +",'"+ det_obs +"','" + fec_ini + "','" + fec_fin + "',"+ det_sta +","+ cod_usu +","+ cod_per +","+ flg_ext + ",'"+ cod_pri +"',"+ cod_sup +","+ cod_dep + "," + turno + ");";
                            */
                    mAccesos.dmlSQLvariable(mQuery);
                });
                
                mQuery2 = "";
                mQuery2 = "UPDATE ipsa.cat_pro_mtto SET Aprobado = 1 WHERE id_pro_mtto =" + id_pro_mtto +";";
                mAccesos.dmlSQLvariable(mQuery2);
                addMessage("Autorizar Programa", "La Actualización de mantenimientos fue satisfactoria.", 1);
                
            } else {
                addMessage("Autorizar Programa", "NO puede autorizar planificaciones para un año ya autorizado.", 2);
            }
                 
        } else {
            addMessage("Autorizar Programa", "Debe elegir un Registro de pronostico.", 2);
        }
        
        //actualiza registro de cat_pro_mtto indicando que el registro se migró
        
        mAccesos.Desconectar();
       
    }
    
    public void imprimir() {
        try {
            byte[] content;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            content = imprimirPronostico();
            response.setContentType("application/pdf");
            response.setContentLength(content == null ? 0 : content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] imprimirPronostico() throws SQLException, JRException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportPath = ctx.getRealPath(File.separator + "reportes" + File.separator);
        HashMap param = new HashMap();
        param.put("idProMtto", id_pro_mtto);

        Accesos racc = new Accesos();
        return JasperRunManager.runReportToPdf(reportPath + File.separator + "listaMttosPreventivos.jasper", param, racc.Conectar());
    }
   
    public void llenarPronosticos() {
        String mQuery = "";
        try {
            catpronosticomtto = new CatPronosticoMtto();
            pronosticomtto = new ArrayList<>();

            mQuery = "SELECT id_pro_mtto, nom_pro_mtto, DATE_FORMAT(fecha_pro_mtto, '%d/%m/%Y'), anho_origen, anho_pro_mtto, aprobado FROM ipsa.cat_pro_mtto;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                pronosticomtto.add(new CatPronosticoMtto(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo de Evaluaciones. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void llenarDetallePronosticoMtto(){
        modelTimeLine = new TimelineModel();
        Date now = new Date();

        llenarMttosPreventivos(2);
        llenarListaEquipos();

        for (CatDetallePronosticoMtto cm : detallepronosticomtto) {
            TimelineEvent tle = new TimelineEvent();
            tle.setData(cm.getDes_equ());
            tle.setStartDate(cm.getFec_ini());
            //tle.setEndDate(cm.getFec_fin());
            //tle.setGroup(cm.getDes_equ()); 
            tle.setStyleClass("pronostico");
            modelTimeLine.add(tle);
        }
    }
    
    public void llenarListaEquipos() {
        try {
            lequipos = new ArrayList<>();

            String mQuery = "select cod_lis_equ,'','','','','','',des_equ "
                    + "from lis_equ order by des_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                lequipos.add(new CatListaEquipos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        "", "", "", "", "", "", "", "", "", "", "", ""
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Equipos ManpronosticoMtto " + e.getMessage());
        }
    }
    
    public void llenarMttosPreventivos(int tipo) {
        String mQuery = "";
        try {

            detallepronosticomtto = new ArrayList<>();
            
            if (tipo == 1){
                mQuery = " select det.id_det_pro_mtto, det.id_pro_mtto, det.cod_lis_equ, det.cod_man, det.cod_tip, det.det_obs, det.fec_ini, det.fec_fin, det.det_sta, det.cod_usu, det.cod_per, det.flg_ext, det.cod_pri, det.cod_sup, det.cod_dep, det.turno, lis.des_equ"
                    + " from det_pro_mtto det inner join cat_pro_mtto pro on "
                    + " det.id_pro_mtto = pro.id_pro_mtto inner join lis_equ lis on det.cod_lis_equ = lis.cod_lis_equ"
                    + " where det.cod_tip = 1 and pro.id_pro_mtto ="+ anho_origen +" order by cod_man;";                 
            }else{
                mQuery = " select det.id_det_pro_mtto, det.id_pro_mtto, det.cod_lis_equ, det.cod_man, det.cod_tip, det.det_obs, det.fec_ini, det.fec_fin, det.det_sta, det.cod_usu, det.cod_per, det.flg_ext, det.cod_pri, det.cod_sup, det.cod_dep, det.turno, lis.des_equ"
                    + " from det_pro_mtto det inner join cat_pro_mtto pro on "
                    + " det.id_pro_mtto = pro.id_pro_mtto inner join lis_equ lis on det.cod_lis_equ = lis.cod_lis_equ"
                    + " where det.cod_tip = 1 and pro.id_pro_mtto ="+ id_pro_mtto +" order by cod_man;";                 
            }
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detallepronosticomtto.add(new CatDetallePronosticoMtto(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getDate(7),
                        resVariable.getDate(8),
                        resVariable.getString(9),
                        resVariable.getString(10),
                        resVariable.getString(11),
                        resVariable.getString(12),
                        resVariable.getString(13),
                        resVariable.getString(14),
                        resVariable.getString(15),
                        resVariable.getString(16),
                        resVariable.getString(17)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Calendarización. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void actualizar() {
        String mQuery;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        mQuery = "update det_pro_mtto SET "
                + " fec_ini = '" + fmt.format(catdetallepronosticomtto.getFec_ini()) + "', "
                + " fec_fin = '" + fmt.format(catdetallepronosticomtto.getFec_fin()) + "' "
                + "WHERE cod_man = " + catdetallepronosticomtto.getCod_man() + " AND cod_lis_equ = '" + catdetallepronosticomtto.getCod_lis_equ() + "' AND id_pro_mtto = "+ catdetallepronosticomtto.getId_pro_mtto()+";";

        mAccesos.dmlSQLvariable(mQuery);
        mAccesos.Desconectar();
        addMessage("Guardar Mantenimiento", "Información Almacenada con éxito.", 1);
    }


    // Eventos
    
     public void dateSelected(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fecha_pro_mtto = format.format(date);
    }

    
    public void onRowSelect(SelectEvent event) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        id_pro_mtto = ((CatPronosticoMtto) event.getObject()).getId_pro_mtto();
        nom_pro_mtto = ((CatPronosticoMtto) event.getObject()).getNom_pro_mtto();
        fecha_pro_mtto = ((CatPronosticoMtto) event.getObject()).getFecha_pro_mtto();
        anho_origen = ((CatPronosticoMtto) event.getObject()).getAnho_origen();
        anho_pro_mtto = ((CatPronosticoMtto) event.getObject()).getAnho_pro_mtto();
        aprobado = ((CatPronosticoMtto) event.getObject()).getAprobado();
        mfecha = format.parse(fecha_pro_mtto);
    }
    
    
    public void onMttoSelect(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
        llenarMttosPreventivos(2);
    }   
    
    public void onEdit(TimelineModificationEvent e) {
        TimelineEvent tlmtto = e.getTimelineEvent();

        for (CatDetallePronosticoMtto cm : detallepronosticomtto) {
            if (cm.getDes_equ() == tlmtto.getData()) {
                catdetallepronosticomtto = cm;
                cod_lis_equ = catdetallepronosticomtto.getCod_lis_equ();
                cod_man = catdetallepronosticomtto.getCod_man();
                llenarMttosPreventivos(2);
                break;
            }
        }
    }

    public void onChange(TimelineModificationEvent e) {
        
        for (CatDetallePronosticoMtto cm : detallepronosticomtto) {
            if (cm.getDes_equ() == e.getTimelineEvent().getData()) {
                Calendar calendar = Calendar.getInstance();

                long dif = cm.getFec_fin().getTime() - cm.getFec_ini().getTime();
                long difDias = dif / (1000 * 60 * 60 * 24);

                calendar.setTime(e.getTimelineEvent().getStartDate());
                calendar.add(Calendar.DAY_OF_YEAR, (int) difDias);

                catdetallepronosticomtto = cm;
                cm.setFec_ini(e.getTimelineEvent().getStartDate());
                cm.setFec_fin(calendar.getTime());

                actualizar();
                break;
            }
        }
    }
    
     public void onAdd(TimelineAddEvent e) {  
        tlevent = new TimelineEvent(new CatCalendario(), e.getStartDate(), e.getEndDate(), true, e.getGroup());
        tlevent.setStyleClass("timeline-event-selected");            
        modelTimeLine.add(tlevent);
    }   
     
    // Mensajes
    
     public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_pro_mtto) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el pronostico de mantenimiento.", 2);
        }
        
        if ("".equals(fecha_pro_mtto) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una fecha de pronostico de mantenimiento.", 2);
        }
        
        if ("".equals(anho_pro_mtto) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un año  pronosticar.", 2);
        }else{
            int anho = Integer.parseInt(anho_pro_mtto);
            if (anho < 2017){
                mValidar = false;
                addMessage("Validar Datos", "Debe Ingresar un año valido mayor a 2016.", 2);
            }
        }
        
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_pro_mtto) from cat_pro_mtto "
                + "where upper(nom_pro_mtto)='" + nom_pro_mtto.toUpperCase() + "';")) == false && "".equals(id_pro_mtto)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de Pronostico ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

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

    
    // Setter y Getters

    public String getId_pro_mtto() {
        return id_pro_mtto;
    }

    public void setId_pro_mtto(String id_pro_mtto) {
        this.id_pro_mtto = id_pro_mtto;
    }
    
    public String getNom_pro_mtto() {
        return nom_pro_mtto;
    }

    public void setNom_pro_mtto(String nom_pro_mtto) {
        this.nom_pro_mtto = nom_pro_mtto;
    }

    public String getFecha_pro_mtto() {
        return fecha_pro_mtto;
    }

    public void setFecha_pro_mtto(String fecha_pro_mtto) {
        this.fecha_pro_mtto = fecha_pro_mtto;
    }

    public String getAnho_pro_mtto() {
        return anho_pro_mtto;
    }

    public void setAnho_pro_mtto(String anho_pro_mtto) {
        this.anho_pro_mtto = anho_pro_mtto;
    }

    public String getId_det_pro_mtto() {
        return id_det_pro_mtto;
    }

    public void setId_det_pro_mtto(String id_det_pro_mtto) {
        this.id_det_pro_mtto = id_det_pro_mtto;
    }

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getCod_man() {
        return cod_man;
    }

    public void setCod_man(String cod_man) {
        this.cod_man = cod_man;
    }

    public String getCod_tip() {
        return cod_tip;
    }

    public void setCod_tip(String cod_tip) {
        this.cod_tip = cod_tip;
    }

    public String getDet_obs() {
        return det_obs;
    }

    public void setDet_obs(String det_obs) {
        this.det_obs = det_obs;
    }

    public String getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(String fec_ini) {
        this.fec_ini = fec_ini;
    }

    public String getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(String fec_fin) {
        this.fec_fin = fec_fin;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getNomtip() {
        return nomtip;
    }

    public void setNomtip(String nomtip) {
        this.nomtip = nomtip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatraso() {
        return datraso;
    }

    public void setDatraso(String datraso) {
        this.datraso = datraso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCod_per() {
        return cod_per;
    }

    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getFlg_ext() {
        return flg_ext;
    }

    public void setFlg_ext(String flg_ext) {
        this.flg_ext = flg_ext;
    }

    public String getCod_sup() {
        return cod_sup;
    }

    public void setCod_sup(String cod_sup) {
        this.cod_sup = cod_sup;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCod_pri() {
        return cod_pri;
    }

    public void setCod_pri(String cod_pri) {
        this.cod_pri = cod_pri;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }

    public String getCod_alt() {
        return cod_alt;
    }

    public void setCod_alt(String cod_alt) {
        this.cod_alt = cod_alt;
    }

    public String getObs_tec() {
        return obs_tec;
    }

    public void setObs_tec(String obs_tec) {
        this.obs_tec = obs_tec;
    }

    public String getOtr_per() {
        return otr_per;
    }

    public void setOtr_per(String otr_per) {
        this.otr_per = otr_per;
    }

    public String getNomequ() {
        return nomequ;
    }

    public void setNomequ(String nomequ) {
        this.nomequ = nomequ;
    }

    public Date getMfecha() {
        return mfecha;
    }

    public void setMfecha(Date mfecha) {
        this.mfecha = mfecha;
    }
        

    public CatMantenimientos getCatmantenimientos() {
        return catmantenimientos;
    }

    public void setCatmantenimientos(CatMantenimientos catmantenimientos) {
        this.catmantenimientos = catmantenimientos;
    }

    public List<CatMantenimientos> getMantenimientos() {
        return mantenimientos;
    }

    public void setMantenimientos(List<CatMantenimientos> mantenimientos) {
        this.mantenimientos = mantenimientos;
    }

    public CatEquipos getCatequipos() {
        return catequipos;
    }

    public void setCatequipos(CatEquipos catequipos) {
        this.catequipos = catequipos;
    }

    public List<CatEquipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<CatEquipos> equipos) {
        this.equipos = equipos;
    }

    public CatListaEquipos getCatlistaequipos() {
        return catlistaequipos;
    }

    public void setCatlistaequipos(CatListaEquipos catlistaequipos) {
        this.catlistaequipos = catlistaequipos;
    }

    public List<CatListaEquipos> getLequipos() {
        return lequipos;
    }

    public void setLequipos(List<CatListaEquipos> lequipos) {
        this.lequipos = lequipos;
    }

    public CatCalendario getCatcalendario() {
        return catcalendario;
    }

    public void setCatcalendario(CatCalendario catcalendario) {
        this.catcalendario = catcalendario;
    }

    public TimelineModel getModelTimeLine() {
        return modelTimeLine;
    }

    public void setModelTimeLine(TimelineModel modelTimeLine) {
        this.modelTimeLine = modelTimeLine;
    }

    public TimelineEvent getTlevent() {
        return tlevent;
    }

    public void setTlevent(TimelineEvent tlevent) {
        this.tlevent = tlevent;
    }

   

    public List<CatCalendario> getListaMttos() {
        return listaMttos;
    }

    public void setListaMttos(List<CatCalendario> listaMttos) {
        this.listaMttos = listaMttos;
    }

    public CatPronosticoMtto getCatpronosticomtto() {
        return catpronosticomtto;
    }

    public void setCatpronosticomtto(CatPronosticoMtto catpronosticomtto) {
        this.catpronosticomtto = catpronosticomtto;
    }

    public List<CatPronosticoMtto> getPronosticomtto() {
        return pronosticomtto;
    }

    public void setPronosticomtto(List<CatPronosticoMtto> pronosticomtto) {
        this.pronosticomtto = pronosticomtto;
    }

    public CatDetallePronosticoMtto getCatdetallepronosticomtto() {
        return catdetallepronosticomtto;
    }

    public void setCatdetallepronosticomtto(CatDetallePronosticoMtto catdetallepronosticomtto) {
        this.catdetallepronosticomtto = catdetallepronosticomtto;
    }

    public List<CatDetallePronosticoMtto> getDetallepronosticomtto() {
        return detallepronosticomtto;
    }

    public void setDetallepronosticomtto(List<CatDetallePronosticoMtto> detallepronosticomtto) {
        this.detallepronosticomtto = detallepronosticomtto;
    }

    public String getAnho_origen() {
        return anho_origen;
    }

    public void setAnho_origen(String anho_origen) {
        this.anho_origen = anho_origen;
    }

    public Date getMfec_fin() {
        return mfec_fin;
    }

    public void setMfec_fin(Date mfec_fin) {
        this.mfec_fin = mfec_fin;
    }

    public String getAprobado() {
        return aprobado;
    }

    public void setAprobado(String aprobado) {
        this.aprobado = aprobado;
    }
}
