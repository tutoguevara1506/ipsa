package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.extensions.event.timeline.TimelineModificationEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.primefaces.model.ScheduleEvent;

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
    private List<CatCalendario> listaMttosPre;
    private String id_pro_mtto, nom_pro_mtto, fecha_pro_mtto, anho_origen, anho_pro_mtto;
    private String id_det_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta,
            cod_usu, nomtip, status, datraso, color, cod_per, periodo, flg_ext, cod_sup, turno, cod_pri, cod_dep, cod_alt, obs_tec, otr_per, nomequ;
    private Date mfecha;

    // Variables para timeline
    private TimelineModel modelTimeLine;
    private TimelineEvent eventTimeLine;

    
    public ManPronosticoMtto() {
    }
    
    // Metodos para cabecera del pronostico de mantenimientos
    
    public void iniciarventana(){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = format.format(mfecha);
        anho_pro_mtto = "";
        llenarPronosticos();
    }

    public void cerrarventana() {
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = "";
        anho_pro_mtto = "";
        pronosticomtto = new ArrayList<>();
    }
    
    public void cerrardetalle() {
        guardarDetalleMttos();
        pronosticomtto = new ArrayList<>();
    }

     public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mfecha = Date.from(Instant.now());
        id_pro_mtto= "";
        nom_pro_mtto = "";
        fecha_pro_mtto = format.format(mfecha);
        anho_pro_mtto = "";
        catpronosticomtto = new CatPronosticoMtto();
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
                    mQuery = "insert into cat_pro_mtto (id_pro_mtto, nom_pro_mtto, fecha_pro_mtto, anho_pro_mtto) "
                            + "values (" + id_pro_mtto + ",'" + nom_pro_mtto + "', str_to_date('" + fecha_pro_mtto + "','%d/%m/%Y')," + anho_pro_mtto+");";
                    
                    llenarMttosPreventivos();
                    
                    listaMttosPre.stream().forEach((mpdet) -> {
                        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                        String mQuery2="";

                        mQuery2 = "SELECT ifnull(max(id_det_pro_mtto),0)+1 as codigo FROM ipsa.det_pro_mtto;";
                        id_det_pro_mtto = mAccesos.strQuerySQLvariable(mQuery2);
                        cod_lis_equ = mpdet.getCod_lis_equ();
                        cod_man = mpdet.getCod_man();
                        cod_tip= mpdet.getCod_tip();
                        cod_usu = mpdet.getCod_usu();
                        color = mpdet.getColor();
                        det_obs = mpdet.getDet_obs();
                        det_sta = mpdet.getDet_sta();
                        fec_ini = fmt.format(mpdet.getFec_ini());
                        fec_fin = fmt.format(mpdet.getFec_fin());
                         
                        // Evaluar todas las variables adicionales necesarias para  aplicar mtto
                        // Fecha de inicio con cambio de año
                        // cambio en el correlativo del cod_man
                        
                                                
                        mQuery2 = "INSERT INTO ipsa.det_pro_mtto " +
                                  "(id_det_pro_mtto, cod_lis_equ, cod_man, cod_tip, cod_usu, color, det_obs, det_sta, fec_ini, fec_fin ) " +
                                  "VALUES ("+id_det_pro_mtto+","+cod_lis_equ+","+cod_man+","+ cod_tip+","+ cod_usu+","+ color +",'"+ det_obs +"',"+ det_sta +","+ fec_ini+","+ fec_fin+");";
                                                
                        mAccesos.dmlSQLvariable(mQuery2);
                    }); 
                    
                    
                    
                } else {
                    mQuery = "update cat_pro_mtto SET "
                            + " nom_pro_mtto = '" + nom_pro_mtto + "', "
                            + " fecha_pro_mtto = str_to_date('" + fecha_pro_mtto + "','%d/%m/%Y %h:%i'), "
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
    
    public void guardarDetalleMttos(){
        System.out.println("entra");
    
    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_pro_mtto) == false) {
            try {
                mQuery = "delete from cat_pro_mtto where id_pro_mtto=" + id_pro_mtto + ";";
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
            addMessage("Validar Datos", "Debe Ingresar un año a pronosticar.", 2);
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
        anho_pro_mtto = ((CatPronosticoMtto) event.getObject()).getAnho_pro_mtto();
        mfecha = format.parse(fecha_pro_mtto);
    }
    
    public void llenarPronosticos() {
        String mQuery = "";
        try {
            catpronosticomtto = new CatPronosticoMtto();
            pronosticomtto = new ArrayList<>();

            mQuery = "SELECT id_pro_mtto, nom_pro_mtto, DATE_FORMAT(fecha_pro_mtto, '%d/%m/%Y'), anho_origen, anho_pro_mtto FROM ipsa.cat_pro_mtto;";
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
                        resVariable.getString(5)
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

        llenarMttosPreventivos();
        llenarListaEquipos();

        for (CatCalendario cm : listaMttosPre) {
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
            System.out.println("Error en el llenado de Equipos MaestroMan. " + e.getMessage());
        }
    }
    
    public void llenarMttosPreventivos() {
        String mQuery = "";
        try {

            listaMttosPre = new ArrayList<>();

            mQuery = " select cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, des_equ, '', ''"
                    + " from det_pro_mtto det inner join cat_pro_mtto pro on "
                    + " det.id_pro_mtto = pro.id_pro_mtto inner join lis_equ lis on det.cod_lis_equ = lis.cod_lis_equ"
                    + " where cod_tip = 1 and anho_origen ="+ anho_origen +" order by cod_man;";
          
            
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

    public void actualizar() {
        String mQuery;
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        mQuery = "update tbl_mae_man SET "
                + " fec_ini = '" + fmt.format(catcalendario.getFec_ini()) + "', "
                + " fec_fin = '" + fmt.format(catcalendario.getFec_fin()) + "' "
                + "WHERE cod_man = " + catcalendario.getCod_man() + " AND cod_lis_equ = '" + catcalendario.getCod_lis_equ() + "';";

        mAccesos.dmlSQLvariable(mQuery);
        mAccesos.Desconectar();
        addMessage("Guardar Mantenimiento", "Información Almacenada con éxito.", 1);
    }


    // Eventos
    
    public void onEventSelect(SelectEvent selectEvent) {

        ScheduleEvent smtto = (ScheduleEvent) selectEvent.getObject();

        for (CatCalendario cm : listaMttosPre) {
            if (cm.getCod_man() == smtto.getData()) {
                catcalendario = cm;
                cod_lis_equ = catcalendario.getCod_lis_equ();
                cod_man = catcalendario.getCod_man();
                llenarMttosPreventivos();
                break;
            }
        }
    }

    public void onMttoSelect(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
        llenarMttosPreventivos();
    }   
    
    public void onEventMove(ScheduleEntryMoveEvent mttoMove) {

        for (CatCalendario cm : listaMttosPre) {
            if (cm.getCod_man() == mttoMove.getScheduleEvent().getData()) {
                catcalendario = cm;
                actualizar();
                break;
            }
        }

    }

    public void onEventResize(ScheduleEntryResizeEvent mttoResize) {
        for (CatCalendario cm : listaMttosPre) {
            if (cm.getCod_man() == mttoResize.getScheduleEvent().getData()) {
                catcalendario = cm;
                actualizar();
                break;
            }
        }
    }

    public void onEdit(TimelineModificationEvent e) {
        TimelineEvent tlmtto = e.getTimelineEvent();

        for (CatCalendario cm : listaMttosPre) {
            if (cm.getDes_equ() == tlmtto.getData()) {
                catcalendario = cm;
                cod_lis_equ = catcalendario.getCod_lis_equ();
                cod_man = catcalendario.getCod_man();
                llenarMttosPreventivos();
                break;
            }
        }
    }

    public void onChange(TimelineModificationEvent e) {

        for (CatCalendario cm : listaMttosPre) {
            if (cm.getDes_equ() == e.getTimelineEvent().getData()) {
                Calendar calendar = Calendar.getInstance();

                long dif = cm.getFec_fin().getTime() - cm.getFec_ini().getTime();
                long difDias = dif / (1000 * 60 * 60 * 24);

                calendar.setTime(e.getTimelineEvent().getStartDate());
                calendar.add(Calendar.DAY_OF_YEAR, (int) difDias);

                catcalendario = cm;
                cm.setFec_ini(e.getTimelineEvent().getStartDate());
                cm.setFec_fin(calendar.getTime());

                actualizar();
                break;
            }
        }
    }
    
    // Mensajes
    
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

    
    public TimelineEvent getEventTimeLine() {
        return eventTimeLine;
    }

    public void setEventTimeLine(TimelineEvent eventTimeLine) {
        this.eventTimeLine = eventTimeLine;
    }

    public List<CatCalendario> getListaMttos() {
        return listaMttos;
    }

    public void setListaMttos(List<CatCalendario> listaMttos) {
        this.listaMttos = listaMttos;
    }

    public List<CatCalendario> getListaMttosPre() {
        return listaMttosPre;
    }

    public void setListaMttosPre(List<CatCalendario> listaMttosPre) {
        this.listaMttosPre = listaMttosPre;
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
    

}