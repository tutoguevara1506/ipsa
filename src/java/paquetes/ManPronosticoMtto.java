package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
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
    private CatMantenimientos catmantenimientos;
    private List<CatMantenimientos> mantenimientos;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatListaEquipos catlistaequipos;
    private List<CatListaEquipos> lequipos;
    private CatCalendario catcalendario;
    private List<CatCalendario> listaMttos;
    private List<CatCalendario> listaMttosPre;
    private String buscar_serie, startDate, endDate, observacion;

    // Variables para timeline
    private TimelineModel modelTimeLine;
    private TimelineEvent eventTimeLine;

    
    public ManPronosticoMtto() {
    }

    @PostConstruct
    public void init() {
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
            modelTimeLine.add(tle);
        }
    }

    public String getBuscar_serie() {
        return buscar_serie;
    }

    public void setBuscar_serie(String buscar_serie) {
        this.buscar_serie = buscar_serie;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    public void iniciarventana() {
    }

    public void cerrarventana() {
    }

    public void nuevo() {
    }

    public void iniciarventananew() {
    }

    public void cerrarventananew() {

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

    
    public void onEventSelect(SelectEvent selectEvent) {

        ScheduleEvent smtto = (ScheduleEvent) selectEvent.getObject();

        for (CatCalendario cm : listaMttosPre) {
            if (cm.getCod_man() == smtto.getData()) {
                catcalendario = cm;
                buscar_serie = catcalendario.getCod_lis_equ();
                llenarMttosPreventivos();
                break;
            }
        }
    }

    public void onMttoSelect(String cod_lis_equ) {
        buscar_serie = cod_lis_equ;
        llenarMttosPreventivos();
    }

    public void llenarMttosCalendario() {
        String mQuery = "";
        try {
            catcalendario = new CatCalendario();
            listaMttos = new ArrayList<>();

            mQuery = " select tbl_mae_man.cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, des_equ, "
                    + "if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=2,'yellow','red')) as color,"
                    + " week(fec_ini,1) as semana "
                    + " from tbl_mae_man inner join lis_equ on "
                    + " tbl_mae_man.cod_lis_equ = lis_equ.cod_lis_equ "
                    + " order by cod_man;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                listaMttos.add(new CatCalendario(
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

    
    
    public void llenarMttosPreventivos() {
        String mQuery = "";
        try {

            listaMttosPre = new ArrayList<>();

            mQuery = " select tbl_mae_man.cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, des_equ, "
                    + "case det_sta when 1 then if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=2,'yellow','red')) "
                    + "when 2 then 'lime' when 3 then if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,fec_ini,now()))<=2,'yellow','red')) " 
                    + "when 4 then 'lime' end as color,"
                    + " week(fec_ini,1) as semana "
                    + " from tbl_mae_man inner join lis_equ on "
                    + " tbl_mae_man.cod_lis_equ = lis_equ.cod_lis_equ "
                    + " where cod_tip = 1 order by cod_man;";

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
                buscar_serie = catcalendario.getCod_lis_equ();
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

}