package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent; 
import org.primefaces.context.RequestContext;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
 
@ManagedBean
@ViewScoped
public class SchMantenimiento implements Serializable {
 
    private CatCalendario catcalendario;
    private List<CatCalendario> listaMttos;
    private ScheduleModel mttoModel;
    private ScheduleEvent mtto = new DefaultScheduleEvent();
 
    public CatCalendario getCatcalendario() {
        return catcalendario;
    }

    public void setCatcalendario(CatCalendario catcalendario) {
        this.catcalendario = catcalendario;
    }

    public List<CatCalendario> getListaMttos() {
        return listaMttos;
    }

    public void setListaMttos(List<CatCalendario> listaMttos) {
        this.listaMttos = listaMttos;
    }

    public ScheduleModel getMttoModel() {
        return mttoModel;
    }

    public void setMttoModel(ScheduleModel mttoModel) {
        this.mttoModel = mttoModel;
    }
    
    @PostConstruct
    public void init() {
        catcalendario = new CatCalendario();
        mttoModel = new DefaultScheduleModel();
        llenarMantenimientos();
        	      
        for (CatCalendario cm : listaMttos){
            DefaultScheduleEvent cmt = new DefaultScheduleEvent();
            cmt.setId(cm.getCod_man());
            cmt.setDescription(cm.getDet_obs());
            cmt.setTitle(cm.getDes_equ());
            cmt.setData(cm.getCod_man());
            cmt.setAllDay(true);
            cmt.setEditable(true);
            cmt.setStartDate(cm.getFec_ini());
            if (cm.getFec_fin()== null)
                cm.setFec_fin(cm.getFec_ini());
            
            cmt.setEndDate(cm.getFec_fin());
                        
            mttoModel.addEvent(cmt);
  
        }
    }
    
    public void llenarMantenimientos() {
        String mQuery = "";
        try {
            catcalendario = new CatCalendario();
            listaMttos = new ArrayList<>();

            mQuery = "select tbl_mae_man.cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, "
                    + "det_sta, cod_usu, des_equ from tbl_mae_man inner join lis_equ on "
                    + "tbl_mae_man.cod_lis_equ = lis_equ.cod_lis_equ " 
                    + "order by cod_man;";
            
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
                        resVariable.getString(9)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Calendarización. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void actualizar() {
        String mQuery = "";
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();

                mQuery = "update tbl_mae_man SET "
                        + " cod_tip = '" + catcalendario.getCod_tip() + "', "
                        + " det_obs = '" + catcalendario.getDet_obs() + "', "
                        + " fec_ini = '" + fmt.format(catcalendario.getFec_ini()) + "', "
                        + " fec_fin = '" + fmt.format(catcalendario.getFec_fin()) + "' "
                       /* + " det_sta = '" + catcalendario.getDet_sta() + "' "*/
                        + "WHERE cod_man = " + catcalendario.getCod_man() + " AND cod_lis_equ = '"+ catcalendario.getCod_lis_equ() +"';";
                        
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Mantenimiento", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Mantenimiento", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Mantenimiento. " + e.getMessage() + " Query: " + mQuery);
            }
            init();
        }
    }
    
     public boolean validardatos() {
        boolean mValidar = true;
        
        if (catcalendario.getDet_obs() == "") {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una descripción.", 2);
        }
        
        return mValidar;
    }
     
     
     
    public Date getRandomDate(Date base) {
        Calendar date = Calendar.getInstance();
        date.setTime(base);
        date.add(Calendar.DATE, ((int) (Math.random()*30)) + 1);    //set random day of month
         
        return date.getTime();
    }
     
    public Date getInitialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.FEBRUARY, calendar.get(Calendar.DATE), 0, 0, 0);
         
        return calendar.getTime();
    }
     
    public ScheduleModel getEventModel() {
        return mttoModel;
    }
      
    private Calendar today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), 0, 0, 0);
 
        return calendar;
    }
     
    
     
    public ScheduleEvent getMtto() {
        return mtto;
    }
 
    public void setMtto (ScheduleEvent mtto) {
        this.mtto = mtto;
    }
     
    public void addEvent(ActionEvent actionEvent) {
        if(mtto.getId() == null)
            mttoModel.addEvent(mtto);
        else
            mttoModel.updateEvent(mtto);
         
        mtto = new DefaultScheduleEvent();
    }
     
    public void onEventSelect(SelectEvent selectEvent) {
       
        ScheduleEvent mtto = (ScheduleEvent) selectEvent.getObject();
               
         for (CatCalendario cm : listaMttos){
             if (cm.getCod_man() == mtto.getData()){
                 catcalendario = cm;                
                 break;
             }         
         }        
    }
     
    public void onDateSelect(SelectEvent selectEvent) {
        mtto = new DefaultScheduleEvent("", (Date) selectEvent.getObject(), (Date) selectEvent.getObject());
    }
    
    public void onEventMove(ScheduleEntryMoveEvent event) {
        addMessage("Calendario", "Se ha movido el mantenimiento de fecha. ", 2);
    }
     
    public void onEventResize(ScheduleEntryResizeEvent event) {
        addMessage("Calendario", "Se ha modificado el mantenimiento. ", 2);
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
}