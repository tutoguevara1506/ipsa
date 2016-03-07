package paquetes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.extensions.event.timeline.TimelineSelectEvent;
import org.primefaces.extensions.model.timeline.TimelineEvent;
import org.primefaces.extensions.model.timeline.TimelineModel;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.TreeNode;
import org.primefaces.model.UploadedFile;

@Named
@ConversationScoped

public class ManMaestroMan implements Serializable {

    private static final long serialVersionUID = 8774297541534938L;
    @Inject
    Login cbean;
    private List<CatGrupoFallas> grupofallas;
    private List<CatFallas> listafallas;
    private CatOperaciones catoperaciones;
    private List<CatOperaciones> operaciones;
    private CatTipos cattipos;
    private List<CatTipos> tipos;
    private CatPeriodos catperiodos;
    private List<CatPeriodos> periodos;
    private CatMantenimientos catmantenimientos;
    private List<CatMantenimientos> mantenimientos;
    private CatClientes catclientes;
    private List<CatClientes> clientes;
    private CatPaises catpaises;
    private List<CatPaises> paises;
    private CatBodegas catbodegas;
    private List<CatBodegas> bodegas;
    private CatUbicaciones catubicaciones;
    private List<CatUbicaciones> ubicaciones;
    private CatUsuarios catusuarios;
    private List<CatUsuarios> usuarios;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatListaEquipos catlistaequipos;
    private List<CatListaEquipos> lequipos;
    private CatExistenciaReal catexistenciareal;
    private List<CatExistenciaReal> existenciareal;
    private CatPiezasConExistencia catpiezasconexistencia;
    private List<CatPiezasConExistencia> existencias;
    private CatMantenimientosGen catmantenimientosgen;
    private List<CatMantenimientosGen> general;
    private CatMantenimientosPie catmantenimientospie;
    private List<CatMantenimientosPie> piezas;
    private CatMantenimientosAne catmantenimientosane;
    private List<CatMantenimientosAne> anexos;
    private CatMantenimientosFal catmantenimientosfal;
    private List<CatMantenimientosFal> fallas;
    private CatMantenimientosAcc catmantenimientosacc;
    private List<CatMantenimientosAcc> accesorios;
    private List<CatSolicitudesDetalle> solicitudes;
    private List<CatSolicitudesDetalle> requisiciones;
    private CatCalendario catcalendario;
    private List<CatCalendario> listaMttos;
    private CatDepartamentos catdepartamentos;
    private List<CatDepartamentos> departamentos;
    private ScheduleModel mttoModel;
    private ScheduleEvent mtto = new DefaultScheduleEvent();
    
    private String cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, cod_per, flg_ext, cod_sup, turno, cod_pri, cod_dep;
    private String gen_det_man, gen_fec_man, gen_cod_ope, gen_det_obs, gen_cod_usu, gen_det_min;
    private String pie_det_man, pie_fec_man, pie_cod_pai, pie_cod_bod, pie_cod_ubi,
            pie_det_can, pie_cod_pie, pie_num_ser, pie_cod_usu;
    private String ane_det_man, ane_det_obs, ane_tip_ane, ane_rut_ane, ane_cod_usu;
    private String acc_det_man, acc_fec_man, acc_cod_pai, acc_det_can, acc_des_ite, acc_cod_usu;

    private String tabindex, buscar_serie, nompai, nombod, nomubi, cod_gru_fal, cod_fal, mmensaje;
    private Date dfecha1, dfecha2, dfecha3, dfecfinF, dfecini;
    private TreeNode root, selectednode;
    
    // Variables para timeline
    private TimelineModel modelTimeLine;  
  
    private boolean selectable = true;  
    private boolean zoomable = true;  
    private boolean moveable = true;  
    private boolean stackEvents = true;  
    private String eventStyle = "box";  
    private boolean axisOnTop = false;  
    private boolean showCurrentTime = true;  
    private boolean showNavigation = false;  
    
    private UploadedFile file;

    public ManMaestroMan() {
    }

    @PostConstruct
    public void init() {
        catcalendario = new CatCalendario();
        mttoModel = new DefaultScheduleModel();
        modelTimeLine = new TimelineModel(); 
        Date now = new Date();
        
        llenarMttosCalendario();
        
        for (CatCalendario cm : listaMttos) {
            DefaultScheduleEvent cmt = new DefaultScheduleEvent();
            TimelineEvent tle = new TimelineEvent();
            tle.setData(cm.getDes_equ());
            tle.setStartDate(cm.getFec_ini());
            //tle.setEndDate(cm.getFec_fin());
            //tle.setGroup(cm.getDes_equ());
            
            //Verifica condicion del mantenimiento
            now = new Date();
            
            long r = (cm.getFec_ini().getTime())- now.getTime();
            double dias = Math.floor(r / (1000 * 60 * 60 * 24)); 
            String estado = cm.getDet_sta();
            String color;
            
            if ("1".equals(estado) && dias > 1){
                color= "entiempo";
            } else if ("1".equals(estado) && dias < 30){
                color= "atrasoleve";
            } else {
                color= "atrazado";
            }
            tle.setStyleClass(color);
            cmt.setId(cm.getCod_man());
            cmt.setDescription(cm.getDet_obs());
            cmt.setTitle(cm.getDes_equ());
            //cmt.setData(cm.getCod_man());
            cmt.setAllDay(true);
            cmt.setEditable(true);
            cmt.setStartDate(cm.getFec_ini());
            if (cm.getFec_fin() == null) {
                cm.setFec_fin(cm.getFec_ini());
            }

            cmt.setEndDate(cm.getFec_fin());

            if ("1".equals(cm.getDet_sta())) {
                cmt.setStyleClass("mtto1");
            } else if ("2".equals(cm.getDet_sta())) {
                cmt.setStyleClass("mtto2");
            } else if ("3".equals(cm.getDet_sta())) {
                cmt.setStyleClass("mtto3");
            } else {
                cmt.setStyleClass("mtto4");
            }

            mttoModel.addEvent(cmt);
            modelTimeLine.add(tle); 

        }
    }

       
    public List<CatGrupoFallas> getGrupofallas() {
        return grupofallas;
    }

    public void setGrupofallas(List<CatGrupoFallas> grupofallas) {
        this.grupofallas = grupofallas;
    }

    public List<CatFallas> getListafallas() {
        return listafallas;
    }

    public void setListafallas(List<CatFallas> listafallas) {
        this.listafallas = listafallas;
    }

    public CatOperaciones getCatoperaciones() {
        return catoperaciones;
    }

    public void setCatoperaciones(CatOperaciones catoperaciones) {
        this.catoperaciones = catoperaciones;
    }

    public List<CatOperaciones> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<CatOperaciones> operaciones) {
        this.operaciones = operaciones;
    }

    public CatTipos getCattipos() {
        return cattipos;
    }

    public void setCattipos(CatTipos cattipos) {
        this.cattipos = cattipos;
    }

    public List<CatTipos> getTipos() {
        return tipos;
    }

    public void setTipos(List<CatTipos> tipos) {
        this.tipos = tipos;
    }

    public CatPeriodos getCatperiodos() {
        return catperiodos;
    }

    public void setCatperiodos(CatPeriodos catperiodos) {
        this.catperiodos = catperiodos;
    }

    public List<CatPeriodos> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<CatPeriodos> periodos) {
        this.periodos = periodos;
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

    public CatClientes getCatclientes() {
        return catclientes;
    }

    public void setCatclientes(CatClientes catclientes) {
        this.catclientes = catclientes;
    }

    public List<CatClientes> getClientes() {
        return clientes;
    }

    public void setClientes(List<CatClientes> clientes) {
        this.clientes = clientes;
    }

    public CatPaises getCatpaises() {
        return catpaises;
    }

    public void setCatpaises(CatPaises catpaises) {
        this.catpaises = catpaises;
    }

    public List<CatPaises> getPaises() {
        return paises;
    }

    public void setPaises(List<CatPaises> paises) {
        this.paises = paises;
    }

    public CatBodegas getCatbodegas() {
        return catbodegas;
    }

    public void setCatbodegas(CatBodegas catbodegas) {
        this.catbodegas = catbodegas;
    }

    public List<CatBodegas> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<CatBodegas> bodegas) {
        this.bodegas = bodegas;
    }

    public CatUbicaciones getCatubicaciones() {
        return catubicaciones;
    }

    public void setCatubicaciones(CatUbicaciones catubicaciones) {
        this.catubicaciones = catubicaciones;
    }

    public List<CatUbicaciones> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<CatUbicaciones> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

    public CatUsuarios getCatusuarios() {
        return catusuarios;
    }

    public void setCatusuarios(CatUsuarios catusuarios) {
        this.catusuarios = catusuarios;
    }

    public List<CatUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CatUsuarios> usuarios) {
        this.usuarios = usuarios;
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

    public CatExistenciaReal getCatexistenciareal() {
        return catexistenciareal;
    }

    public void setCatexistenciareal(CatExistenciaReal catexistenciareal) {
        this.catexistenciareal = catexistenciareal;
    }

    public List<CatExistenciaReal> getExistenciareal() {
        return existenciareal;
    }

    public void setExistenciareal(List<CatExistenciaReal> existenciareal) {
        this.existenciareal = existenciareal;
    }

    public CatPiezasConExistencia getCatpiezasconexistencia() {
        return catpiezasconexistencia;
    }

    public void setCatpiezasconexistencia(CatPiezasConExistencia catpiezasconexistencia) {
        this.catpiezasconexistencia = catpiezasconexistencia;
    }

    public List<CatPiezasConExistencia> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<CatPiezasConExistencia> existencias) {
        this.existencias = existencias;
    }

    public CatMantenimientosGen getCatmantenimientosgen() {
        return catmantenimientosgen;
    }

    public void setCatmantenimientosgen(CatMantenimientosGen catmantenimientosgen) {
        this.catmantenimientosgen = catmantenimientosgen;
    }

    public List<CatMantenimientosGen> getGeneral() {
        return general;
    }

    public void setGeneral(List<CatMantenimientosGen> general) {
        this.general = general;
    }

    public CatMantenimientosPie getCatmantenimientospie() {
        return catmantenimientospie;
    }

    public void setCatmantenimientospie(CatMantenimientosPie catmantenimientospie) {
        this.catmantenimientospie = catmantenimientospie;
    }

    public List<CatMantenimientosPie> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<CatMantenimientosPie> piezas) {
        this.piezas = piezas;
    }

    public CatMantenimientosAne getCatmantenimientosane() {
        return catmantenimientosane;
    }

    public void setCatmantenimientosane(CatMantenimientosAne catmantenimientosane) {
        this.catmantenimientosane = catmantenimientosane;
    }

    public List<CatMantenimientosAne> getAnexos() {
        return anexos;
    }

    public void setAnexos(List<CatMantenimientosAne> anexos) {
        this.anexos = anexos;
    }

    public CatMantenimientosFal getCatmantenimientosfal() {
        return catmantenimientosfal;
    }

    public void setCatmantenimientosfal(CatMantenimientosFal catmantenimientosfal) {
        this.catmantenimientosfal = catmantenimientosfal;
    }

    public List<CatMantenimientosFal> getFallas() {
        return fallas;
    }

    public void setFallas(List<CatMantenimientosFal> fallas) {
        this.fallas = fallas;
    }

    public CatMantenimientosAcc getCatmantenimientosacc() {
        return catmantenimientosacc;
    }

    public void setCatmantenimientosacc(CatMantenimientosAcc catmantenimientosacc) {
        this.catmantenimientosacc = catmantenimientosacc;
    }

    public List<CatMantenimientosAcc> getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(List<CatMantenimientosAcc> accesorios) {
        this.accesorios = accesorios;
    }

    public List<CatSolicitudesDetalle> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<CatSolicitudesDetalle> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<CatSolicitudesDetalle> getRequisiciones() {
        return requisiciones;
    }

    public void setRequisiciones(List<CatSolicitudesDetalle> requisiciones) {
        this.requisiciones = requisiciones;
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

    public String getCod_per() {
        return cod_per;
    }

    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    public String getFlg_ext() {
        return flg_ext;
    }

    public void setFlg_ext(String flg_ext) {
        this.flg_ext = flg_ext;
    }

    public String getGen_det_man() {
        return gen_det_man;
    }

    public void setGen_det_man(String gen_det_man) {
        this.gen_det_man = gen_det_man;
    }

    public String getGen_fec_man() {
        return gen_fec_man;
    }

    public void setGen_fec_man(String gen_fec_man) {
        this.gen_fec_man = gen_fec_man;
    }

    public String getGen_cod_ope() {
        return gen_cod_ope;
    }

    public void setGen_cod_ope(String gen_cod_ope) {
        this.gen_cod_ope = gen_cod_ope;
    }

    public String getGen_det_obs() {
        return gen_det_obs;
    }

    public void setGen_det_obs(String gen_det_obs) {
        this.gen_det_obs = gen_det_obs;
    }

    public String getGen_cod_usu() {
        return gen_cod_usu;
    }

    public void setGen_cod_usu(String gen_cod_usu) {
        this.gen_cod_usu = gen_cod_usu;
    }

    public String getGen_det_min() {
        return gen_det_min;
    }

    public void setGen_det_min(String gen_det_min) {
        this.gen_det_min = gen_det_min;
    }

    public String getPie_det_man() {
        return pie_det_man;
    }

    public void setPie_det_man(String pie_det_man) {
        this.pie_det_man = pie_det_man;
    }

    public String getPie_fec_man() {
        return pie_fec_man;
    }

    public void setPie_fec_man(String pie_fec_man) {
        this.pie_fec_man = pie_fec_man;
    }

    public String getPie_cod_pai() {
        return pie_cod_pai;
    }

    public void setPie_cod_pai(String pie_cod_pai) {
        this.pie_cod_pai = pie_cod_pai;
    }

    public String getPie_cod_bod() {
        return pie_cod_bod;
    }

    public void setPie_cod_bod(String pie_cod_bod) {
        this.pie_cod_bod = pie_cod_bod;
    }

    public String getPie_cod_ubi() {
        return pie_cod_ubi;
    }

    public void setPie_cod_ubi(String pie_cod_ubi) {
        this.pie_cod_ubi = pie_cod_ubi;
    }

    public String getPie_det_can() {
        return pie_det_can;
    }

    public void setPie_det_can(String pie_det_can) {
        this.pie_det_can = pie_det_can;
    }

    public String getPie_cod_pie() {
        return pie_cod_pie;
    }

    public void setPie_cod_pie(String pie_cod_pie) {
        this.pie_cod_pie = pie_cod_pie;
    }

    public String getPie_num_ser() {
        return pie_num_ser;
    }

    public void setPie_num_ser(String pie_num_ser) {
        this.pie_num_ser = pie_num_ser;
    }

    public String getPie_cod_usu() {
        return pie_cod_usu;
    }

    public void setPie_cod_usu(String pie_cod_usu) {
        this.pie_cod_usu = pie_cod_usu;
    }

    public String getAne_det_man() {
        return ane_det_man;
    }

    public void setAne_det_man(String ane_det_man) {
        this.ane_det_man = ane_det_man;
    }

    public String getAne_det_obs() {
        return ane_det_obs;
    }

    public void setAne_det_obs(String ane_det_obs) {
        this.ane_det_obs = ane_det_obs;
    }

    public String getAne_tip_ane() {
        return ane_tip_ane;
    }

    public void setAne_tip_ane(String ane_tip_ane) {
        this.ane_tip_ane = ane_tip_ane;
    }

    public String getAne_rut_ane() {
        return ane_rut_ane;
    }

    public void setAne_rut_ane(String ane_rut_ane) {
        this.ane_rut_ane = ane_rut_ane;
    }

    public String getAne_cod_usu() {
        return ane_cod_usu;
    }

    public void setAne_cod_usu(String ane_cod_usu) {
        this.ane_cod_usu = ane_cod_usu;
    }

    public String getAcc_det_man() {
        return acc_det_man;
    }

    public void setAcc_det_man(String acc_det_man) {
        this.acc_det_man = acc_det_man;
    }

    public String getAcc_fec_man() {
        return acc_fec_man;
    }

    public void setAcc_fec_man(String acc_fec_man) {
        this.acc_fec_man = acc_fec_man;
    }

    public String getAcc_cod_pai() {
        return acc_cod_pai;
    }

    public void setAcc_cod_pai(String acc_cod_pai) {
        this.acc_cod_pai = acc_cod_pai;
    }

    public String getAcc_det_can() {
        return acc_det_can;
    }

    public void setAcc_det_can(String acc_det_can) {
        this.acc_det_can = acc_det_can;
    }

    public String getAcc_des_ite() {
        return acc_des_ite;
    }

    public void setAcc_des_ite(String acc_des_ite) {
        this.acc_des_ite = acc_des_ite;
    }

    public String getAcc_cod_usu() {
        return acc_cod_usu;
    }

    public void setAcc_cod_usu(String acc_cod_usu) {
        this.acc_cod_usu = acc_cod_usu;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getBuscar_serie() {
        return buscar_serie;
    }

    public void setBuscar_serie(String buscar_serie) {
        this.buscar_serie = buscar_serie;
    }

    public String getNompai() {
        return nompai;
    }

    public void setNompai(String nompai) {
        this.nompai = nompai;
    }

    public String getNombod() {
        return nombod;
    }

    public void setNombod(String nombod) {
        this.nombod = nombod;
    }

    public String getNomubi() {
        return nomubi;
    }

    public void setNomubi(String nomubi) {
        this.nomubi = nomubi;
    }

    public String getCod_gru_fal() {
        return cod_gru_fal;
    }

    public void setCod_gru_fal(String cod_gru_fal) {
        this.cod_gru_fal = cod_gru_fal;
    }

    public String getCod_fal() {
        return cod_fal;
    }

    public void setCod_fal(String cod_fal) {
        this.cod_fal = cod_fal;
    }

    public Date getDfecha1() {
        return dfecha1;
    }

    public void setDfecha1(Date dfecha1) {
        this.dfecha1 = dfecha1;
    }

    public Date getDfecha2() {
        return dfecha2;
    }

    public void setDfecha2(Date dfecha2) {
        this.dfecha2 = dfecha2;
    }

    public Date getDfecha3() {
        return dfecha3;
    }

    public void setDfecha3(Date dfecha3) {
        this.dfecha3 = dfecha3;
    }

    public Date getDfecfinF() {
        return dfecfinF;
    }

    public void setDfecfinF(Date dfecfinF) {
        this.dfecfinF = dfecfinF;
    }

    public Date getDfecini() {
        return dfecini;
    }

    public void setDfecini(Date dfecini) {
        this.dfecini = dfecini;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getSelectednode() {
        return selectednode;
    }

    public void setSelectednode(TreeNode selectednode) {
        this.selectednode = selectednode;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public String getMmensaje() {
        return mmensaje;
    }

    public void setMmensaje(String mmensaje) {
        this.mmensaje = mmensaje;
    }

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

    public TimelineModel getModelTimeLine() {
        return modelTimeLine;
    }

    public void setModelTimeLine(TimelineModel modelTimeLine) {
        this.modelTimeLine = modelTimeLine;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isZoomable() {
        return zoomable;
    }

    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(boolean stackEvents) {
        this.stackEvents = stackEvents;
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
    }

    public boolean isAxisOnTop() {
        return axisOnTop;
    }

    public void setAxisOnTop(boolean axisOnTop) {
        this.axisOnTop = axisOnTop;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }

    public ScheduleEvent getMtto() {
        return mtto;
    }

    public void setMtto(ScheduleEvent mtto) {
        this.mtto = mtto;
    }

    public CatDepartamentos getCatdepartamentos() {
        return catdepartamentos;
    }

    public void setCatdepartamentos(CatDepartamentos catdepartamentos) {
        this.catdepartamentos = catdepartamentos;
    }

    public List<CatDepartamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<CatDepartamentos> departamentos) {
        this.departamentos = departamentos;
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
    
    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        dfecha1 = Date.from(Instant.now());
        dfecha2 = Date.from(Instant.now());
        dfecha3 = Date.from(Instant.now());

        tabindex = "0";

        cod_lis_equ = "";
        cod_man = "";
        cod_tip = "";
        det_obs = "";
        fec_ini = format.format(dfecha1);
        fec_fin = format.format(dfecha1);
        det_sta = "";
        cod_usu = "0";
        cod_per = "0";
        flg_ext = "0";
        cod_sup = cbean.getCod_usu();
        turno = "0";
        cod_pri = "";
        cod_dep = "0";
        gen_det_man = "";
        gen_fec_man = format.format(dfecha1);
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();
        gen_det_min = "";
        pie_det_man = "";
        pie_fec_man = format.format(dfecha1);
        pie_cod_pai = "0";
        pie_cod_bod = "0";
        pie_cod_ubi = "0";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = cbean.getCod_usu();
        ane_det_man = "";
        ane_det_obs = "";
        ane_tip_ane = "";
        ane_rut_ane = "";
        ane_cod_usu = cbean.getCod_usu();
        acc_det_man = "";
        acc_fec_man = format.format(dfecha3);
        acc_cod_pai = "0";
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = cbean.getCod_usu();
        buscar_serie = "";
        mmensaje = "";
        root = null;
        selectednode = null;
        catmantenimientos = new CatMantenimientos();
        mantenimientos = new ArrayList<>();
        general = new ArrayList<>();
        piezas = new ArrayList<>();
        anexos = new ArrayList<>();
        accesorios = new ArrayList<>();

        llenarUsuarios();
        llenarOperaciones();
        llenarPaises();
        llenarCatalogoPiezas();
        llenarTipos();
        llenarPeriodos();
        llenarDepartamentos();
        llenarListaEquipos();

    }

    public void cerrarventana() {
        dfecha1 = Date.from(Instant.now());
        dfecha2 = dfecha1;

        tabindex = "0";

        cod_lis_equ = "";
        cod_man = "";
        cod_tip = "";
        det_obs = "";
        fec_ini = "";
        fec_fin = "";
        det_sta = "";
        cod_usu = "";
        cod_per = "0";
        flg_ext = "0";
        cod_sup = "0";
        turno = "0";
        cod_pri = "";
        cod_dep = "0";
        gen_det_man = "";
        gen_fec_man = "";
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = "";
        gen_det_min = "";
        pie_det_man = "";
        pie_fec_man = "";
        pie_cod_pai = "";
        pie_cod_bod = "";
        pie_cod_ubi = "";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = "";
        ane_det_man = "";
        ane_det_obs = "";
        ane_tip_ane = "";
        ane_rut_ane = "";
        ane_cod_usu = "";
        acc_det_man = "";
        acc_fec_man = "";
        acc_cod_pai = "";
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = "";
        buscar_serie = "";
        mmensaje = "";
        root = null;
        selectednode = null;
        catmantenimientos = new CatMantenimientos();
        mantenimientos = new ArrayList<>();

    }

    public void nuevo() {

        dfecha1 = Date.from(Instant.now());
        dfecha2 = dfecha1;

        tabindex = "0";

        cod_lis_equ = "";
        cod_man = "";
        cod_tip = "";
        det_obs = "";
        fec_ini = "";
        fec_fin = "";
        det_sta = "";
        cod_usu = "0";
        cod_per = "0";
        flg_ext = "0";
        cod_sup = cbean.getCod_usu();
        turno = "0";
        cod_pri = "";
        cod_dep = "0";
        gen_det_man = "";
        gen_fec_man = "";
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();
        gen_det_min = "";
        pie_det_man = "";
        pie_fec_man = "";
        pie_cod_pai = "0";
        pie_cod_bod = "0";
        pie_cod_ubi = "0";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = cbean.getCod_usu();
        ane_det_man = "";
        ane_det_obs = "";
        ane_tip_ane = "";
        ane_rut_ane = "";
        ane_cod_usu = cbean.getCod_usu();
        acc_det_man = "";
        acc_fec_man = "";
        acc_cod_pai = "0";
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = cbean.getCod_usu();
        buscar_serie = "";
        mmensaje = "";
        root = null;
        selectednode = null;
        ubicaciones = new ArrayList<>();
        existenciareal = new ArrayList<>();
    }

    public void iniciarventananew() {
        if ("".equals(cod_lis_equ) || "0".equals(cod_lis_equ)) {
            addMessage("Nuevo Mantenimiento", "Debe Seleccionar un Equipo.", 2);
        } else {
            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfecini = Date.from(Instant.now());
            dfecfinF = Date.from(Instant.now());

            cod_man = "";
            cod_tip = "0";
            det_obs = "";
            fec_ini = format.format(dfecini);
            fec_fin = format.format(dfecfinF);
            det_sta = "0";
            cod_usu = "0";
            cod_per = "0";
            flg_ext = "0";
            cod_sup = cbean.getCod_usu();
            turno = "0";
            cod_pri = "";
            cod_dep = "0";
            cod_gru_fal = "0";
            cod_fal = "0";
            llenarTipos();
            llenarPeriodos();
            llenarGrupoFallas();
            llenarDepartamentos();
            catmantenimientosfal = new CatMantenimientosFal();
            fallas = new ArrayList<>();
            RequestContext.getCurrentInstance().execute("PF('wMaestraNew').show()");
            
        }

    }

    public void cerrarventananew() {
        llenarMantenimientos();
    }

    public void iniciarventanaedit() {

        if ("".equals(cod_man) || "0".equals(cod_man)) {
            addMessage("Modificar Mantenimiento", "Debe Seleccionar un Registro a Modificar.", 2);
        } else {
            fallas = new ArrayList<>();
            llenarTipos();
            llenarPeriodos();
            llenarFallas();
            llenarGrupoFallas();
            RequestContext.getCurrentInstance().execute("PF('wMaestraNew').show()");
        }
    }

    public void iniciarventanaexistencia() {
        catpiezasconexistencia = new CatPiezasConExistencia();
        existencias = new ArrayList<>();
        Accesos acc = new Accesos();
        acc.Conectar();
        nompai = acc.strQuerySQLvariable("select nom_pai from cat_alm where cod_alm=" + pie_cod_pai + ";");
        nombod = acc.strQuerySQLvariable("select nom_bod from cat_bodegas where cod_pai=" + pie_cod_pai + " and id_bod = " + pie_cod_bod + ";");
        nomubi = acc.strQuerySQLvariable("select nom_ubi from cat_ubicaciones where cod_bod = " + pie_cod_bod + " and id_ubi=" + pie_cod_ubi + ";");
        acc.Desconectar();
    }

    public void buscarexistencia() {
        String mQuery = "";

        if (!"0".equals(pie_cod_pie)) {
            mQuery = "and exi.cod_pie=" + pie_cod_pie;
        }

        llenarExistencias(mQuery);
    }

    public void cerrarventanaexistencia() {
        nompai = "";
        nombod = "";
        nomubi = "";
        catpiezasconexistencia = null;
        existencias.clear();
    }

    public void llenarExistencias(String mWhere) {
        try {
            catpiezasconexistencia = null;
            existencias.clear();

            String mQuery = "select "
                    + "exi.cod_pie, exi.cod_pai, exi.cod_bod, exi.cod_ubi, "
                    + "(select ifnull(sum(res.det_can),0) as suma "
                    + "from tbl_res as res "
                    + "where res.cod_pie = exi.cod_pie "
                    + "and res.cod_pai = exi.cod_pai "
                    + "and res.cod_bod = exi.cod_bod "
                    + "and res.cod_ubi = exi.cod_ubi ) as reserva,"
                    + "exi.det_can, pie.nom_pie, pai.nom_alm, bod.nom_bod, ubi.nom_ubi "
                    + "from tbl_existencias as exi "
                    + "left join cat_pie as pie on exi.cod_pie = pie.cod_pie "
                    + "left join cat_alm as pai on exi.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on exi.cod_pai = bod.cod_pai and exi.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on exi.cod_bod = ubi.cod_bod and exi.cod_ubi = ubi.id_ubi "
                    + "where exi.cod_pai=" + pie_cod_pai + " and exi.cod_bod=" + pie_cod_bod + " and exi.cod_ubi=" + pie_cod_ubi + " "
                    + mWhere + " "
                    + "order by exi.cod_pie,exi.cod_pai,exi.cod_bod,exi.cod_ubi;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                existencias.add(new CatPiezasConExistencia(
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
            System.out.println("Error en el llenado de Existencias en ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarNodos() {
        String mQuery = "";
        int mCodPais = 0, mNodoPais = 0, mCodEquipo = 0, mNodoEquipo = 0;
        Accesos macc = new Accesos();
        macc.Conectar();
        try {

            root = new DefaultTreeNode("Root", null);

            //-------- Llenar Nodo Paises
            ResultSet resVariable;

            mQuery = "select lis.cod_pai, pai.nom_pai,lis.cod_equ,equ.nom_equ, lis.des_equ "
                    + "FROM lis_equ as lis "
                    + "left join cat_pai as pai on lis.cod_pai = pai.cod_pai "
                    + "left join cat_equ as equ on lis.cod_equ = equ.cod_equ "
                    + "order by lis.cod_pai,lis.cod_equ,lis.num_ser;";
            resVariable = macc.querySQLvariable(mQuery);

            while (resVariable.next()) {
                if (mCodPais != resVariable.getInt(1)) {
                    mCodPais = resVariable.getInt(1);
                    mNodoPais = mNodoPais + 1;
                    root.getChildren().add(new DefaultTreeNode(resVariable.getString(2)));
                }
                if (mCodEquipo != resVariable.getInt(3)) {
                    mCodEquipo = resVariable.getInt(3);
                    mNodoEquipo = mNodoEquipo + 1;
                    root.getChildren().get(mNodoPais - 1).getChildren().add(new DefaultTreeNode(resVariable.getString(4)));
                }
                root.getChildren().get(mNodoPais - 1).getChildren().get(mNodoEquipo - 1).getChildren().add(new DefaultTreeNode("MNS-" + resVariable.getString(5)));
            }

        } catch (Exception e) {
            System.out.println("Error en el llenado de Nodos ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        } finally {
            macc.Desconectar();
        }
    }

    public void llenarMantenimientos() {
        String mQuery = "";
        try {
            cod_man = "";
            catmantenimientosgen = new CatMantenimientosGen();
            general = new ArrayList<>();
            catmantenimientospie = new CatMantenimientosPie();
            piezas = new ArrayList<>();
            catmantenimientosacc = new CatMantenimientosAcc();
            accesorios = new ArrayList<>();
            catmantenimientosane = new CatMantenimientosAne();
            anexos = new ArrayList<>();
            catmantenimientos = new CatMantenimientos();
            mantenimientos = new ArrayList<>();

            if (!"".equals(buscar_serie)) {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                cod_lis_equ = buscar_serie;
                mQuery = "select "
                        + "mm.cod_lis_equ, "
                        + "mm.cod_man, "
                        + "mm.cod_tip, "
                        + "mm.det_obs, "
                        + "date_format(mm.fec_ini,'%d/%m/%Y %H:%i'), "
                        + "date_format(mm.fec_fin,'%d/%m/%Y %H:%i'), "
                        + "mm.det_sta, "
                        + "mm.cod_usu,"
                        + "tip.nom_tip,"
                        + "case mm.det_sta "
                        + "when 1 then 'PENDIENTE' "
                        + "when 2 then 'CANCELADO' "
                        + "when 3 then 'EN PROCESO' "
                        + "when 4 then 'FINALIZADO' "
                        + "end as status,"
                        + "if((TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))<2,0,(TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))) as dr,"
                        + "if((TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))<=2,'yellow','red')) as color,"
                        + "mm.cod_per, "
                        + "per.nom_per,"
                        + "mm.flg_ext,mm.cod_sup, mm.turno, mm.cod_pri, mm.cod_dep "
                        + "from tbl_mae_man as mm "
                        + "left join cat_tip as tip on mm.cod_tip = tip.cod_tip "
                        + "left join cat_per as per on mm.cod_per = per.cod_per "
                        + "where "
                        + "mm.det_sta IN (1,3) "
                        + "and mm.cod_lis_equ =" + buscar_serie + " "
                        + "order by mm.cod_man;";

                ResultSet resVariable;

                resVariable = mAccesos.querySQLvariable(mQuery);
                while (resVariable.next()) {
                    mantenimientos.add(new CatMantenimientos(
                            resVariable.getString(1),
                            resVariable.getString(2),
                            resVariable.getString(3),
                            resVariable.getString(4),
                            resVariable.getString(5),
                            resVariable.getString(6),
                            resVariable.getString(7),
                            resVariable.getString(8),
                            resVariable.getString(9),
                            resVariable.getString(10),
                            resVariable.getString(11),
                            resVariable.getString(12),
                            resVariable.getString(13),
                            resVariable.getString(14),
                            resVariable.getString(15),
                            resVariable.getString(16),
                            resVariable.getString(17),
                            resVariable.getString(18),
                            resVariable.getString(19)
                    ));

                }
                mAccesos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("Error en el llenado de Mantenimientos en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarGrupoFallas() {
        try {
            grupofallas = new ArrayList<>();

            String mQuery = "select cod_gru_fal,nom_gru_fal "
                    + "from cat_gru_fal order by cod_gru_fal;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                grupofallas.add(new CatGrupoFallas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de GrupoFallas en ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarListaFallas() {
        try {
            listafallas = new ArrayList<>();

            String mQuery = "select fal.cod_fal,fal.cod_gru_fal,fal.nom_fal,gru.nom_gru_fal "
                    + "from cat_fal as fal "
                    + "left join cat_gru_fal as gru on fal.cod_gru_fal = gru.cod_gru_fal "
                    + "where fal.cod_gru_fal = " + cod_gru_fal + " "
                    + "order by fal.cod_gru_fal,fal.cod_fal;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                listafallas.add(new CatFallas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de ListaFallas en ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarPaises() {
        try {
            paises = new ArrayList<>();

            String mQuery = "select cod_alm, nom_alm "
                    + "from cat_alm order by cod_alm;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                paises.add(new CatPaises(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Paises en ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarPiezasExistentes() {
        String mQuery = "";
        try {

            existenciareal = new ArrayList<>();

            mQuery = "select "
                    + "arreglo.cod_pie,arreglo.cod_ref, arreglo.nom_pie, "
                    + "(arreglo.existencia - arreglo.reserva) as exireal "
                    + "from ("
                    + "select "
                    + "exi.cod_pie, pie.cod_ref, pie.nom_pie,"
                    + "exi.det_can as existencia, "
                    + "ifnull(sum(res.det_can),0) as reserva "
                    + "from tbl_existencias as exi "
                    + "left join tbl_res as res on exi.cod_pai = res.cod_pai and exi.cod_bod = res.cod_bod and exi.cod_ubi = res.cod_ubi and exi.cod_pie = res.cod_pie "
                    + "left join cat_pie as pie on exi.cod_pie = pie.cod_pie "
                    + "where "
                    + "exi.cod_pai = " + pie_cod_pai + " "
                    + "and exi.cod_bod = " + pie_cod_bod + " "
                    + "and exi.cod_ubi = " + pie_cod_ubi + " "
                    + "and exi.det_can > 0 "
                    + "group by exi.cod_pie, pie.cod_ref, pie.nom_pie, exi.det_can "
                    + "order by exi.cod_pie "
                    + ") as arreglo "
                    + "where  "
                    + "(arreglo.existencia - arreglo.reserva) > 0";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                existenciareal.add(new CatExistenciaReal(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Piezas en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarCatalogoPiezas() {
        String mQuery = "";
        try {

            existenciareal = new ArrayList<>();

            mQuery = "select cod_pie, cod_ref, nom_pie from cat_pie order by cod_ref asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                existenciareal.add(new CatExistenciaReal(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        "0"
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en llenarCatalogoPiezas en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarUsuarios() {
        try {
            catusuarios = new CatUsuarios();
            usuarios = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai order by cod_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                usuarios.add(new CatUsuarios(
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
            System.out.println("Error en el llenado de Usuarios ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarTipos() {
        String mQuery = "";
        try {
            cattipos = new CatTipos();
            tipos = new ArrayList<>();

            mQuery = "select cod_tip, nom_tip, flg_urg from cat_tip order by cod_tip;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                tipos.add(new CatTipos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarPeriodos() {
        String mQuery = "";
        try {
            catperiodos = new CatPeriodos();
            periodos = new ArrayList<>();

            mQuery = "select cod_per, nom_per, det_dia from cat_per order by cod_per;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                periodos.add(new CatPeriodos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Períodos en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarOperaciones() {
        String mQuery = "";
        try {
            catoperaciones = new CatOperaciones();
            operaciones = new ArrayList<>();

            mQuery = "select cod_ope, nom_ope from cat_ope order by cod_ope;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                operaciones.add(new CatOperaciones(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Operaciones en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarGeneral() {
        String mQuery = "";
        try {
            catmantenimientosgen = new CatMantenimientosGen();
            general = new ArrayList<>();
            mQuery = "select gen.cod_lis_equ,gen.cod_man,gen.det_man,"
                    + "date_format(gen.fec_man,'%d/%m/%Y %H:%i'),"
                    + "gen.cod_ope,gen.det_obs,"
                    + "gen.cod_usu,ope.nom_ope, "
                    + "usu.det_nom,gen.det_min "
                    + "from tbl_det_man_gen as gen "
                    + "left join cat_ope as ope on gen.cod_ope = ope.cod_ope "
                    + "left join cat_usu as usu on gen.cod_usu = usu.cod_usu "
                    + "where gen.cod_lis_equ=" + cod_lis_equ + " "
                    + "and gen.cod_man=" + cod_man + " "
                    + "order by gen.det_man;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                general.add(new CatMantenimientosGen(
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
            System.out.println("Error en el llenado Detalle General en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarPiezas() {
        String mQuery = "";
        try {
            catmantenimientospie = new CatMantenimientosPie();
            piezas = new ArrayList<>();

            mQuery = "select "
                    + "gen.cod_lis_equ,gen.cod_man,gen.det_man,"
                    + "date_format(gen.fec_man,'%d/%m/%Y %H:%i'), "
                    + "gen.cod_pai, gen.cod_bod, gen.cod_ubi, gen.det_can, "
                    + "gen.cod_pie, gen.num_ser, gen.cod_usu,pai.nom_alm as nompai, "
                    + "bod.nom_bod as nombod, ubi.nom_ubi as nomubi,"
                    + "usu.det_nom as nomusu, pie.nom_pie as nompie,"
                    + "gen.flg_sol "
                    + "from tbl_det_man_pie as gen "
                    + "left join cat_alm as pai on gen.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on gen.cod_pai = bod.cod_pai and gen.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on gen.cod_bod = ubi.cod_bod and gen.cod_ubi = ubi.id_ubi "
                    + "left join cat_usu as usu on gen.cod_usu = usu.cod_usu "
                    + "left join cat_pie as pie on gen.cod_pie = pie.cod_pie "
                    + "where gen.cod_lis_equ= " + cod_lis_equ + " "
                    + "and gen.cod_man=" + cod_man + " "
                    + "order by gen.det_man;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezas.add(new CatMantenimientosPie(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
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
            System.out.println("Error en el llenado Detalle Piezas en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarAccesorios() {
        String mQuery = "";
        try {
            catmantenimientosacc = new CatMantenimientosAcc();
            accesorios = new ArrayList<>();

            mQuery = "select "
                    + "acc.cod_lis_equ, acc.cod_man, acc.det_man, "
                    + "date_format(acc.fec_man,'%d/%m/%Y %H:%i'), "
                    + "acc.cod_pai, acc.det_can, acc.des_ite, acc.cod_usu, acc.flg_sol,"
                    + "pai.nom_alm, usu.det_nom "
                    + "FROM tbl_det_man_acc as acc "
                    + "left join cat_alm as pai on acc.cod_pai = pai.cod_alm "
                    + "left join cat_usu as usu on acc.cod_usu = usu.cod_usu "
                    + "where acc.cod_lis_equ= " + cod_lis_equ + " "
                    + "and acc.cod_man=" + cod_man + " "
                    + "order by acc.det_man;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                accesorios.add(new CatMantenimientosAcc(
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
            System.out.println("Error en el llenado Accesorios en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarAnexos() {
        String mQuery = "";
        try {
            catmantenimientosane = new CatMantenimientosAne();
            anexos = new ArrayList<>();

            mQuery = "select "
                    + "man.cod_lis_equ, man.cod_man, man.det_man, "
                    + "man.det_obs, man.tip_ane, man.rut_ane, man.cod_usu, "
                    + "case man.tip_ane "
                    + "when 1 then 'PDF' "
                    + "when 2 then 'IMAGEN' "
                    + "when 3 then 'MP4' "
                    + "when 4 then 'OTRO' "
                    + "end as nomtip, "
                    + "usu.det_nom "
                    + "from tbl_det_man_ane as man "
                    + "left join cat_usu as usu on man.cod_usu = usu.cod_usu "
                    + "where cod_lis_equ=" + cod_lis_equ + " "
                    + "and cod_man=" + cod_man + " "
                    + "order by det_man;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                anexos.add(new CatMantenimientosAne(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Anexos en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarFallas() {
        String mQuery = "";
        try {
            catmantenimientosfal = new CatMantenimientosFal();
            fallas = new ArrayList<>();

            mQuery = "select fal.cod_lis_equ,fal.cod_man,fal.det_man,fal.cod_gru_fal,fal.cod_fal,fal.det_obs, "
                    + "gru.nom_gru_fal,"
                    + "lfal.nom_fal "
                    + "from tbl_det_man_fal as fal "
                    + "left join cat_gru_fal as gru on fal.cod_gru_fal = gru.cod_gru_fal "
                    + "left join cat_fal as lfal on fal.cod_gru_fal = lfal.cod_gru_fal and fal.cod_fal = lfal.cod_fal "
                    + "where fal.cod_lis_equ=" + cod_lis_equ + " "
                    + "and fal.cod_man=" + cod_man + " "
                    + "order by fal.det_man;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                fallas.add(new CatMantenimientosFal(
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
            System.out.println("Error en el llenado Detalle Fallas en ManMaestroMan." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarDepartamentos() {
        try {
            catdepartamentos = new CatDepartamentos();
            departamentos = new ArrayList<>();

            String mQuery = "select cod_dep, cod_pai, nom_dep "
                    + "from cat_dep order by cod_dep;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                departamentos.add(new CatDepartamentos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Departamentos en ManMaestroMan. " + e.getMessage());
        }
    }
    
    
    public void guardarencabezado() {
        String mQuery = "";
        if (validarencabezado()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_man)) {
                    mQuery = "select ifnull(max(cod_man),0)+1 as codigo from tbl_mae_man where cod_lis_equ = " + cod_lis_equ + ";";
                    cod_man = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into tbl_mae_man (cod_lis_equ,cod_man,cod_tip,det_obs,fec_ini,fec_fin,det_sta,cod_usu,cod_per,flg_ext,cod_sup,turno,cod_pri,cod_dep) "
                            + "VALUES (" + cod_lis_equ + "," + cod_man + "," + cod_tip + ",'" + det_obs.replace("'", " ") + "',"
                            + "str_to_date('" + fec_ini + "','%d/%m/%Y %H:%i'),str_to_date('" + fec_fin + "','%d/%m/%Y %H:%i'),1,"
                            + cod_usu + "," + cod_per + "," + flg_ext + "," + cod_sup + "," + turno + "," + cod_pri + "," + cod_dep + ");";
                } else {
                    mQuery = "delete from tbl_det_man_fal where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                    mQuery = "update tbl_mae_man set "
                            + "cod_tip= " + cod_tip + ","
                            + "det_obs= '" + det_obs.replace("'", " ") + "',"
                            + "fec_ini = str_to_date('" + fec_ini + "','%d/%m/%Y %H:%i'),"
                            + "fec_fin = str_to_date('" + fec_fin + "','%d/%m/%Y %H:%i'),"
                            + "cod_usu = " + cod_usu + ","
                            + "cod_per= " + cod_per + ","
                            + "flg_ext= " + flg_ext + ","
                            + "cod_sup = " + cod_sup + ","
                            + "turno= " + turno + ","
                            + "cod_pri= '" + cod_pri + "',"
                            + "cod_dep= " + cod_dep + " "
                            + "where cod_lis_equ = " + cod_lis_equ + " "
                            + "and cod_man = " + cod_man + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                String mValues = "";
                mQuery = "";

                for (int i = 0; i < fallas.size(); i++) {
                    mValues = mValues + ",(" + cod_lis_equ + "," + cod_man + ","
                            + (i + 1) + "," + fallas.get(i).getCod_gru_fal() + "," + fallas.get(i).getCod_fal() + ",'')";
                }

                if (!"".equals(mValues)) {
                    mQuery = "insert into tbl_det_man_fal (cod_lis_equ,cod_man,det_man,cod_gru_fal,cod_fal,det_obs) VALUES" + mValues.substring(1) + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                }

                mAccesos.Desconectar();

                addMessage("Guardar Mantenimiento", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Mantenimiento", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Mantenimiento. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarMantenimientos();
            RequestContext.getCurrentInstance().execute("PF('wMaestraNew').hide()");
        }
    }

    public boolean validarencabezado() {
        boolean mvalidar = true;
        if ("".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Escoger un Equipo.", 2);
        }
        if ("0".equals(cod_tip)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Escoger un Tipo de Mantenimiento.", 2);
        }
        return mvalidar;

    }

    public void eliminarencabezado() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_lis_equ) == false && "".equals(cod_man) == false) {
            try {
                mQuery = "delete from tbl_det_man_ane where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_det_man_acc where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_det_man_pie where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_det_man_gen where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_mae_man where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Mantenimiento", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Mantenimiento", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Equipo. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarMantenimientos();

        } else {
            addMessage("Eliminar Mantenimiento", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();
    }

    public void agregargeneral() {
        if (validargeneral()) {
            int correlativo = 0;
            try {
                for (int i = 0; i < general.size(); i++) {
                    if (Integer.valueOf(general.get(i).getDet_man()) > correlativo) {
                        correlativo = Integer.valueOf(general.get(i).getDet_man());
                    }
                }

                Accesos macc = new Accesos();
                macc.Conectar();
                String nomope = macc.strQuerySQLvariable("select ifnull(nom_ope,'') from cat_ope where cod_ope =" + gen_cod_ope + ";");
                String nomusu = macc.strQuerySQLvariable("select ifnull(det_nom,'') from cat_usu where cod_usu =" + gen_cod_usu + ";");
                macc.Desconectar();

                general.add(new CatMantenimientosGen(
                        cod_lis_equ,
                        cod_man,
                        String.valueOf(correlativo + 1),
                        gen_fec_man,
                        gen_cod_ope,
                        gen_det_obs.replace("'", " "),
                        gen_cod_usu,
                        nomope,
                        nomusu,
                        gen_det_min
                ));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dfecha1 = Date.from(Instant.now());
                gen_fec_man = format.format(dfecha1);
                gen_cod_ope = "0";
                gen_det_obs = "";
                gen_cod_usu = cbean.getCod_usu();
                gen_det_min = "";

            } catch (Exception e) {
                System.out.println("Error en Agregar General ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validargeneral() {
        boolean mvalidar = true;
        String mQuery = "";
        if ("".equals(gen_det_min)) {
            gen_det_min = "0.0";
        }
        if ("0".equals(gen_cod_ope) && "0".equals(gen_cod_ope)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar almenos una Operación o una Observación.", 2);
        }
        if ("0".equals(gen_cod_usu)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar al responsable de la Operación.", 2);
        }

        if ("".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        } else if ("".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }
        try {
            Accesos macc = new Accesos();
            macc.Conectar();
            mQuery = "select DATEDIFF(str_to_date('" + pie_fec_man + "','%d/%m/%Y'), str_to_date('" + fec_ini + "','%d/%m/%Y')) as datedif;";
            if (macc.doubleQuerySQLvariable(mQuery) < 0.0) {
                mvalidar = false;
                addMessage("Validar Datos", "La Fecha del Detalle Técnico no puede ser anterior al Inicio del Mantenimiento.", 2);
            }
            macc.Desconectar();
        } catch (Exception e) {
            System.out.println("Error en validación General ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }

        return mvalidar;

    }

    public void eliminargeneral() {

        if ("".equals(gen_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Detalle Técnico para Remover.", 2);
        } else {
            for (int i = 0; i < general.size(); i++) {
                if (cod_lis_equ.equals(general.get(i).getCod_lis_equ())
                        && cod_man.equals(general.get(i).getCod_man())
                        && gen_det_man.equals(general.get(i).getDet_man())) {
                    general.remove(i);
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfecha1 = Date.from(Instant.now());
            gen_fec_man = format.format(dfecha1);
            gen_cod_ope = "0";
            gen_det_obs = "";
            gen_cod_usu = cbean.getCod_usu();
        }
    }

    public void agregarpiezas() {
        if (validarpiezas()) {
            int correlativo = 0;
            try {
                for (int i = 0; i < piezas.size(); i++) {
                    if (Integer.valueOf(piezas.get(i).getDet_man()) > correlativo) {
                        correlativo = Integer.valueOf(piezas.get(i).getDet_man());
                    }
                }

                Accesos macc = new Accesos();
                macc.Conectar();
                String nompaipie = macc.strQuerySQLvariable("select ifnull(nom_alm,'') from cat_alm where cod_alm =" + pie_cod_pai + ";");
                String nombodpie = macc.strQuerySQLvariable("select ifnull(nom_bod,'') from cat_bodegas where cod_pai=" + pie_cod_pai + " and id_bod =" + pie_cod_bod + ";");
                String nomubipie = macc.strQuerySQLvariable("select ifnull(nom_ubi,'') from cat_ubicaciones where cod_bod=" + pie_cod_bod + " and id_ubi =" + pie_cod_ubi + ";");
                String nomusupie = macc.strQuerySQLvariable("select ifnull(det_nom,'') from cat_usu where cod_usu =" + pie_cod_usu + ";");
                String nompiepie = macc.strQuerySQLvariable("select ifnull(nom_pie,'') from cat_pie where cod_pie =" + pie_cod_pie + ";");

                macc.Desconectar();

                piezas.add(new CatMantenimientosPie(
                        cod_lis_equ,
                        cod_man,
                        String.valueOf(correlativo + 1),
                        pie_fec_man,
                        pie_cod_pai,
                        pie_cod_bod,
                        pie_cod_ubi,
                        pie_det_can.replace(",", " "),
                        pie_cod_pie,
                        ((pie_num_ser.replace("'", " ")).replace("/", " ")).replace("\\", " "),
                        pie_cod_usu,
                        nompaipie,
                        nombodpie,
                        nomubipie,
                        nomusupie,
                        nompiepie,
                        "0"
                ));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dfecha2 = Date.from(Instant.now());
                pie_fec_man = format.format(dfecha1);
                pie_cod_pai = "0";
                pie_cod_bod = "0";
                pie_cod_ubi = "0";
                pie_det_can = "";
                pie_cod_pie = "0";
                pie_num_ser = "";
                pie_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar Pieza ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validarpiezas() {
        boolean mvalidar = true;
        String mQuery = "";
        if ("".equals(pie_det_can)) {
            pie_det_can = "0.0";
        }
        if (Double.valueOf(pie_det_can) == 0.0) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar Una Cantidad Mayor que Cero.", 2);
        }
        if ("0".equals(pie_cod_pie)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar Una Pieza.", 2);
        }
        if ("0".equals(pie_cod_pai)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un País.", 2);
        }

        if ("0".equals(pie_cod_usu)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar al responsable de la Operación.", 2);
        }
        if ("".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        } else if ("".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }
        try {
            Accesos macc = new Accesos();
            macc.Conectar();
            mQuery = "select DATEDIFF(str_to_date('" + pie_fec_man + "','%d/%m/%Y'), str_to_date('" + fec_ini + "','%d/%m/%Y')) as datedif;";
            if (macc.doubleQuerySQLvariable(mQuery) < 0.0) {
                mvalidar = false;
                addMessage("Validar Datos", "La Fecha del Detalle de Piezas Utilizadas no puede ser anterior al Inicio del Mantenimiento.", 2);
            }
            macc.Desconectar();
        } catch (Exception e) {
            System.out.println("Error en validación pieza ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
        return mvalidar;

    }

    public void eliminarpiezas() {
        if ("".equals(pie_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar una Pieza para Remover.", 2);
        } else {
            for (int i = 0; i < piezas.size(); i++) {
                if (cod_lis_equ.equals(piezas.get(i).getCod_lis_equ())
                        && cod_man.equals(piezas.get(i).getCod_man())
                        && pie_det_man.equals(piezas.get(i).getDet_man())) {
                    piezas.remove(i);
                }
            }

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfecha2 = Date.from(Instant.now());
            pie_fec_man = format.format(dfecha2);
            pie_cod_pai = "0";
            pie_cod_bod = "0";
            pie_cod_ubi = "0";
            pie_det_can = "";
            pie_cod_pie = "0";
            pie_num_ser = "";
            pie_cod_usu = cbean.getCod_usu();
        }
    }

    public void agregaraccesorios() {
        if (validaraccesorios()) {
            int correlativo = 0;
            try {
                for (int i = 0; i < accesorios.size(); i++) {
                    if (Integer.valueOf(accesorios.get(i).getDet_man()) > correlativo) {
                        correlativo = Integer.valueOf(accesorios.get(i).getDet_man());
                    }
                }

                Accesos macc = new Accesos();
                macc.Conectar();
                String nompaipie = macc.strQuerySQLvariable("select ifnull(nom_alm,'') from cat_alm where cod_alm =" + acc_cod_pai + ";");

                macc.Desconectar();

                accesorios.add(new CatMantenimientosAcc(
                        cod_lis_equ,
                        cod_man,
                        String.valueOf(correlativo + 1),
                        acc_fec_man,
                        acc_cod_pai,
                        acc_det_can.replace(",", " "),
                        ((acc_des_ite.replace("'", " ")).replace("/", " ")).replace("\\", " "),
                        acc_cod_usu,
                        "0",
                        nompaipie
                ));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dfecha3 = Date.from(Instant.now());
                acc_fec_man = format.format(dfecha3);
                acc_cod_pai = "0";
                acc_det_can = "";
                acc_des_ite = "";
                acc_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar Accesorio ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validaraccesorios() {
        boolean mvalidar = true;
        String mQuery = "";
        if ("".equals(acc_det_can)) {
            acc_det_can = "0.0";
        }
        if ("".equals(acc_des_ite)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar Una Descripción del Accesorio.", 2);
        }
        if (Double.valueOf(acc_det_can) == 0.0) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar Una Cantidad Mayor que Cero.", 2);
        }
        if ("0".equals(acc_cod_usu)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar al responsable de la Operación.", 2);
        }
        if ("0".equals(acc_cod_pai)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Almacén.", 2);
        }
        if ("".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        } else if ("".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }
        try {
            Accesos macc = new Accesos();
            macc.Conectar();
            mQuery = "select DATEDIFF(str_to_date('" + acc_fec_man + "','%d/%m/%Y'), str_to_date('" + fec_ini + "','%d/%m/%Y')) as datedif;";
            if (macc.doubleQuerySQLvariable(mQuery) < 0.0) {
                mvalidar = false;
                addMessage("Validar Datos", "La Fecha del Detalle de Accesorios Utilizados no puede ser anterior al Inicio del Mantenimiento.", 2);
            }
            macc.Desconectar();
        } catch (Exception e) {
            System.out.println("Error en validación Accesorios ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
        return mvalidar;

    }

    public void eliminarAccesorio() {
        if ("".equals(acc_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Accesorio para Remover.", 2);
        } else {
            for (int i = 0; i < accesorios.size(); i++) {
                if (cod_lis_equ.equals(accesorios.get(i).getCod_lis_equ())
                        && cod_man.equals(accesorios.get(i).getCod_man())
                        && acc_det_man.equals(accesorios.get(i).getDet_man())) {
                    accesorios.remove(i);
                }
            }

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            dfecha3 = Date.from(Instant.now());
            acc_fec_man = format.format(dfecha3);
            acc_cod_pai = "0";
            acc_det_can = "";
            acc_des_ite = "";
            acc_cod_usu = cbean.getCod_usu();
        }
    }

    public void agregaranexo() {
        if (validaranexos()) {
            int correlativo = 0;
            try {
                for (int i = 0; i < anexos.size(); i++) {
                    if (Integer.valueOf(anexos.get(i).getDet_man()) > correlativo) {
                        correlativo = Integer.valueOf(anexos.get(i).getDet_man());
                    }
                }

                Accesos macc = new Accesos();
                macc.Conectar();
                String nomusupie = macc.strQuerySQLvariable("select ifnull(det_nom,'') from cat_usu where cod_usu =" + ane_cod_usu + ";");
                String tipoanexo = "";
                switch (ane_tip_ane) {
                    case "1":
                        tipoanexo = "PDF";
                        break;
                    case "2":
                        tipoanexo = "IMAGEN";
                        break;
                    case "3":
                        tipoanexo = "MP4";
                        break;
                    case "4":
                        tipoanexo = "OTRO";
                        break;

                }

                macc.Desconectar();

                anexos.add(new CatMantenimientosAne(
                        cod_lis_equ,
                        cod_man,
                        String.valueOf(correlativo + 1),
                        ((ane_det_obs.replace("'", " ")).replace("/", " ")).replace("\\", " "),
                        ane_tip_ane,
                        ane_rut_ane,
                        ane_cod_usu,
                        tipoanexo,
                        nomusupie
                ));

                ane_det_obs = "";
                ane_tip_ane = "0";
                ane_rut_ane = "";
                ane_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar Anexo ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validaranexos() {
        boolean mvalidar = true;

        if ("".equals(ane_det_obs)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar Una Observación de Referencia para el Archivo.", 2);
        }

        if ("0".equals(ane_tip_ane)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Tipo de Anexo.", 2);
        }
        if ("0".equals(ane_cod_usu)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Indicar el Usuario que comparte el Anexo.", 2);
        }
        if ("".equals(ane_rut_ane)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Subir un Archivo.", 2);
        }
        if ("".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        } else if ("".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }

        return mvalidar;

    }

    public void eliminarAnexos() {
        if ("".equals(ane_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Anexo para Remover.", 2);
        } else {
            for (int i = 0; i < anexos.size(); i++) {
                if (cod_lis_equ.equals(anexos.get(i).getCod_lis_equ())
                        && cod_man.equals(anexos.get(i).getCod_man())
                        && ane_det_man.equals(anexos.get(i).getDet_man())) {

                    try {
                        File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/anexos/"
                                + (anexos.get(i).getRut_ane().replace("/resources/images/anexos/", "")).replace("/resources/images/temp/", "")));
                        if (mIMGFile.exists()) {
                            mIMGFile.delete();
                        }
                    } catch (Exception e) {
                        System.out.println("Error al Eliminar Archivos Anexos 1: "
                                + FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/anexos/"
                                        + (ane_rut_ane.replace("/resources/images/anexos/", "")).replace("/resources/images/temp/", "")));
                    }
                    try {
                        File mIMGFile2 = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/temp/"
                                + (ane_rut_ane.replace("/resources/images/anexos/", "")).replace("/resources/images/temp/", "")));
                        if (mIMGFile2.exists()) {
                            mIMGFile2.delete();
                        }
                    } catch (Exception e) {
                        System.out.println("Error al Eliminar Archivos Anexos 2: "
                                + FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/temp/"
                                        + (anexos.get(i).getRut_ane().replace("/resources/images/anexos/", "")).replace("/resources/images/temp/", "")));
                    }
                    try {

                        Accesos macc = new Accesos();
                        macc.Conectar();
                        macc.dmlSQLvariable("delete from tbl_det_man_ane where cod_lis_equ = " + cod_lis_equ + " and cod_man = " + cod_man
                                + " and tip_ane = " + ane_tip_ane + " and rut_ane = '" + ane_rut_ane.replace("\\", "\\\\") + "';");
                        macc.Desconectar();
                        anexos.remove(i);

                    } catch (Exception e) {
                        System.out.println("Error al Eliminar Archivos Anexos 3: "
                                + "delete from tbl_det_man_ane where cod_lis_equ = "
                                + cod_lis_equ + " and cod_man = " + cod_man
                                + " and tip_ane = " + ane_tip_ane
                                + " and rut_ane = '" + ane_rut_ane + "';" + ". "
                                + e.getMessage());
                    }

                }
            }

            ane_det_obs = "";
            ane_tip_ane = "0";
            ane_rut_ane = "";
            ane_cod_usu = cbean.getCod_usu();
        }
    }

    public void agregarfalla() {
        if (validarfalla()) {
            int correlativo = 0, existe = 0;
            try {
                for (int i = 0; i < fallas.size(); i++) {
                    if (Integer.valueOf(fallas.get(i).getDet_man()) > correlativo) {
                        correlativo = Integer.valueOf(fallas.get(i).getDet_man());
                    }
                    if (cod_gru_fal.equals(fallas.get(i).getCod_gru_fal()) && cod_fal.equals(fallas.get(i).getCod_fal())) {
                        existe = 1;
                    }

                }

                if (existe == 0) {
                    Accesos macc = new Accesos();
                    macc.Conectar();
                    String nomgrup = macc.strQuerySQLvariable("select ifnull(nom_gru_fal,'') from cat_gru_fal where cod_gru_fal =" + cod_gru_fal + ";");
                    String nomfal = macc.strQuerySQLvariable("select ifnull(nom_fal,'') from cat_fal where cod_gru_fal =" + cod_gru_fal + " and cod_fal=" + cod_fal + ";");
                    macc.Desconectar();

                    fallas.add(new CatMantenimientosFal(
                            cod_lis_equ,
                            cod_man,
                            String.valueOf(correlativo + 1),
                            cod_gru_fal,
                            cod_fal,
                            "",
                            nomgrup,
                            nomfal
                    ));
                }

                cod_gru_fal = "0";
                cod_fal = "0";
                catmantenimientosfal = new CatMantenimientosFal();

            } catch (Exception e) {
                System.out.println("Error en Agregar Falla ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validarfalla() {
        boolean mvalidar = true;

        if ("0".equals(cod_gru_fal)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Grupo de Fallas.", 2);
        }
        if ("0".equals(cod_fal)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Falla Específica.", 2);
        }

        return mvalidar;

    }

    public void eliminarfalla() {
        if ("0".equals(cod_fal)) {
            addMessage("Eliminar Fallas", "Debe Seleccionar una Falla para Remover.", 2);
        } else {
            for (int i = 0; i < fallas.size(); i++) {
                if (cod_gru_fal.equals(fallas.get(i).getCod_gru_fal()) && cod_fal.equals(fallas.get(i).getCod_fal())) {
                    fallas.remove(i);
                }
            }

            cod_gru_fal = "0";
            cod_fal = "0";
            catmantenimientosfal = new CatMantenimientosFal();
        }
    }

    public void generarsolicitudes() {
        String mQuery = "", mDetSol = "", mDetReq = "";
        try {
            solicitudes = new ArrayList<>();
            requisiciones = new ArrayList<>();
            String mExistencias;
            int correlasol = 1, correlareq = 1;
            Accesos macc = new Accesos();
            macc.Conectar();
            mQuery = "select ifnull(max(cod_mae),0)+1 as codigo from sol_mae;";
            String cod_mae_sol = macc.strQuerySQLvariable(mQuery);
            mQuery = "select ifnull(max(cod_mae),0)+1 as codigo from req_mae;";
            String cod_mae_req = macc.strQuerySQLvariable(mQuery);

            for (int i = 0; i < piezas.size(); i++) {
                if ("0".equals(piezas.get(i).getFlg_sol())) {

                    mExistencias = macc.strQuerySQLvariable("select exi.det_exi - "
                            + "(select ifnull(sum(res.det_can),0) as suma "
                            + "from tbl_res as res "
                            + "where res.cod_pie = exi.cod_pie "
                            + "and res.cod_pai = exi.cod_pai ) -"
                            + "(select ifnull(sum(salidas.det_exi),0) as suma "
                            + "from bol_exi_pai as salidas "
                            + "where salidas.cod_pie = exi.cod_pie "
                            + "and salidas.cod_pai = exi.cod_pai "
                            + "and salidas.ing_sal=1) as existencias "
                            + "from bol_exi_pai as exi "
                            + "where exi.cod_pai= " + piezas.get(i).getCod_pai() + " "
                            + "and exi.cod_pie= " + piezas.get(i).getCod_pie() + " "
                            + "and exi.ing_sal=0;");

                    if ("".equals(mExistencias) || "0".equals(mExistencias)) {

                        solicitudes.add(new CatSolicitudesDetalle(
                                piezas.get(i).getDet_man(),
                                String.valueOf(correlasol),
                                piezas.get(i).getCod_pai(),
                                "0",
                                "0",
                                piezas.get(i).getCod_pie(),
                                macc.strQuerySQLvariable("select ifnull(concat(cod_ref,' - '),'') as codref from cat_pie where cod_pie = "
                                        + piezas.get(i).getCod_pie() + ";") + piezas.get(i).getNompie(),
                                piezas.get(i).getDet_can(),
                                "0",
                                piezas.get(i).getDet_can(),
                                "1",
                                "0",
                                "null",
                                "0",
                                "",
                                "",
                                ""
                        ));

                        correlasol = correlasol + 1;

                    } else if ((Double.valueOf(mExistencias) - Double.valueOf(piezas.get(i).getDet_can())) < 0) {

                        solicitudes.add(new CatSolicitudesDetalle(
                                piezas.get(i).getDet_man(),
                                String.valueOf(correlasol),
                                piezas.get(i).getCod_pai(),
                                "0",
                                "0",
                                piezas.get(i).getCod_pie(),
                                macc.strQuerySQLvariable("select ifnull(concat(cod_ref,' - '),'') as codref from cat_pie where cod_pie = "
                                        + piezas.get(i).getCod_pie() + ";") + piezas.get(i).getNompie(),
                                String.valueOf(Double.valueOf(piezas.get(i).getDet_can()) - Double.valueOf(mExistencias)),
                                "0",
                                String.valueOf(Double.valueOf(piezas.get(i).getDet_can()) - Double.valueOf(mExistencias)),
                                "1",
                                "0",
                                "null",
                                "0",
                                "",
                                "",
                                ""
                        ));

                        correlasol = correlasol + 1;

                        requisiciones.add(new CatSolicitudesDetalle(
                                piezas.get(i).getDet_man(),
                                String.valueOf(correlasol),
                                piezas.get(i).getCod_pai(),
                                "0",
                                "0",
                                piezas.get(i).getCod_pie(),
                                macc.strQuerySQLvariable("select ifnull(concat(cod_ref,' - '),'') as codref from cat_pie where cod_pie = "
                                        + piezas.get(i).getCod_pie() + ";") + piezas.get(i).getNompie(),
                                mExistencias,
                                "0",
                                mExistencias,
                                "1",
                                "0",
                                "null",
                                "0",
                                "",
                                "",
                                ""
                        ));

                        mQuery = "select ifnull(max(cod_res),0)+1 as codigo from tbl_res;";
                        mQuery = "insert into tbl_res (cod_res,fec_res,cod_pai,cod_pie,det_can,cod_lis_pie,cod_det,"
                                + "cod_lis_equ,cod_man,det_man,cod_usu,cod_req,det_req) VALUES (" + macc.strQuerySQLvariable(mQuery) + ","
                                + "now()," + piezas.get(i).getCod_pai() + "," + piezas.get(i).getCod_pie() + ","
                                + mExistencias + ",0,0,"
                                + cod_lis_equ + "," + cod_man + "," + piezas.get(i).getDet_man() + "," + piezas.get(i).getCod_usu() + ","
                                + cod_mae_req + "," + correlareq + ")";
                        macc.dmlSQLvariable(mQuery);

                        correlareq = correlareq + 1;

                    } else {
                        requisiciones.add(new CatSolicitudesDetalle(
                                piezas.get(i).getDet_man(),
                                String.valueOf(correlasol),
                                piezas.get(i).getCod_pai(),
                                "0",
                                "0",
                                piezas.get(i).getCod_pie(),
                                macc.strQuerySQLvariable("select ifnull(concat(cod_ref,' - '),'') as codref from cat_pie where cod_pie = "
                                        + piezas.get(i).getCod_pie() + ";") + piezas.get(i).getNompie(),
                                piezas.get(i).getDet_can(),
                                "0",
                                piezas.get(i).getDet_can(),
                                "1",
                                "0",
                                "null",
                                "0",
                                "",
                                "",
                                ""
                        ));

                        mQuery = "select ifnull(max(cod_res),0)+1 as codigo from tbl_res;";
                        mQuery = "insert into tbl_res (cod_res,fec_res,cod_pai,cod_pie,det_can,cod_lis_pie,cod_det,"
                                + "cod_lis_equ,cod_man,det_man,cod_usu,cod_req,det_req) VALUES (" + macc.strQuerySQLvariable(mQuery) + ","
                                + "now()," + piezas.get(i).getCod_pai() + "," + piezas.get(i).getCod_pie() + ","
                                + piezas.get(i).getDet_can() + ",0,0,"
                                + cod_lis_equ + "," + cod_man + "," + piezas.get(i).getDet_man() + "," + piezas.get(i).getCod_usu() + ","
                                + cod_mae_req + "," + correlareq + ")";
                        macc.dmlSQLvariable(mQuery);

                        correlareq = correlareq + 1;
                    }

                    macc.dmlSQLvariable("update tbl_det_man_pie "
                            + "set flg_sol=1 "
                            + "where cod_lis_equ=" + cod_lis_equ + " "
                            + "and cod_man=" + cod_man + " "
                            + "and det_man=" + piezas.get(i).getDet_man() + ";");

                }

            }

            for (int i = 0; i < accesorios.size(); i++) {
                if ("0".equals(accesorios.get(i).getFlg_sol())) {
                    solicitudes.add(new CatSolicitudesDetalle(
                            accesorios.get(i).getDet_man(),
                            String.valueOf(correlasol),
                            accesorios.get(i).getCod_pai(),
                            "0",
                            "0",
                            "0",
                            accesorios.get(i).getDes_ite(),
                            accesorios.get(i).getDet_can(),
                            "0",
                            accesorios.get(i).getDet_can(),
                            "1",
                            "0",
                            "null",
                            "0",
                            "",
                            "",
                            ""
                    ));

                    correlasol = correlasol + 1;
                    macc.dmlSQLvariable("update tbl_det_man_acc "
                            + "set flg_sol=1 "
                            + "where cod_lis_equ=" + cod_lis_equ + " "
                            + "and cod_man=" + cod_man + " "
                            + "and det_man=" + accesorios.get(i).getDet_man() + ";");
                }

            }

            //******************** INSERTAR SOLICITUDES ***************************************
            int verificavuelta;
            List<Integer> misitems = new ArrayList<>();

            if (solicitudes.size() > 0) {
                verificavuelta = 0;
            } else {
                verificavuelta = 1;
            }
            String mpais = "0";

            while (verificavuelta == 0) {
                correlasol = 1;
                mDetSol = "";

                for (int i = 0; i < solicitudes.size(); i++) {
                    mpais = solicitudes.get(0).getCod_pai();
                    if (mpais.equals(solicitudes.get(i).getCod_pai())) {
                        mDetSol = mDetSol + detalleregistrosolreq(cod_mae_sol, String.valueOf(correlasol),
                                solicitudes.get(i).getCod_pai(), solicitudes.get(i).getCod_ite(),
                                solicitudes.get(i).getDes_ite(), solicitudes.get(i).getDet_can_sol());
                        correlasol = correlasol + 1;
                        misitems.add(i);
                    }

                }
                Collections.sort(misitems, Collections.reverseOrder());
                Iterator<Integer> iter = misitems.iterator();

                while (iter.hasNext()) {
                    int indice = iter.next();
                    solicitudes.remove(indice);

                }

                String mnombreequipo = macc.strQuerySQLvariable("select concat(equ.nom_equ,'-', lequ.num_ser) from lis_equ as lequ left join cat_equ as equ on lequ.cod_equ = equ.cod_equ where lequ.cod_lis_equ=" + cod_lis_equ + ";");
                String mdepartamento = macc.strQuerySQLvariable("select cod_dep from cat_usu where cod_usu=" + cod_usu + ";");
                String mequipo = macc.strQuerySQLvariable("select cod_equ from lis_equ as lequ where lequ.cod_lis_equ=" + cod_lis_equ + ";");

                mQuery = "insert into sol_mae (cod_mae, cod_alt, fec_sol, cod_usu_sol, cod_usu_apr, "
                        + "cod_usu_rec, cod_dep, det_uso, cod_maq, det_sta, det_obs, fec_cie, flg_loc,cod_pai) "
                        + "values (" + cod_mae_sol + ",'',now(),"
                        + cod_usu + ",0,0," + mdepartamento + ",'Mantenimiento Equipo " + mnombreequipo + "',"
                        + mequipo + ",0,'Solicitud Generada Automáticamente para el Mantenimiento',null,0," + mpais + ");";
                macc.dmlSQLvariable(mQuery);

                mQuery = "insert into sol_det(cod_mae,cod_det,cod_pai,cod_bod,cod_ubi,cod_ite,des_ite,"
                        + "det_can_sol,det_can_ent,det_can_pen,non_sto,det_sta,fec_cie,cos_uni) values " + mDetSol.substring(1) + ";";
                macc.dmlSQLvariable(mQuery);

                mQuery = "select ifnull(max(cod_rel),0)+1 as codigo from tbl_rel_man_sol_req;";
                String cod_rel = macc.strQuerySQLvariable(mQuery);
                mQuery = "insert into tbl_rel_man_sol_req (cod_rel,cod_lis_equ,cod_man,det_man,cod_sol_req,flg_sol_req,det_sta_sol_req) VALUES ("
                        + cod_rel + "," + cod_lis_equ + "," + cod_man + ",0," + cod_mae_sol + ",'SOL',0);";
                macc.dmlSQLvariable(mQuery);
                misitems = new ArrayList<>();
                if (solicitudes.isEmpty()) {
                    verificavuelta = 1;
                    break;
                } else {
                    mQuery = "select ifnull(max(cod_mae),0)+1 as codigo from sol_mae;";
                    cod_mae_sol = macc.strQuerySQLvariable(mQuery);
                }
            }

            //******************** INSERTAR REQUISICIONES ***************************************
            if (requisiciones.size() > 0) {
                verificavuelta = 0;
            } else {
                verificavuelta = 1;
            }
            mpais = "0";

            while (verificavuelta == 0) {
                correlareq = 1;
                mDetReq = "";
                for (int i = 0; i < requisiciones.size(); i++) {
                    mpais = requisiciones.get(0).getCod_pai();
                    if (mpais.equals(requisiciones.get(i).getCod_pai())) {
                        mDetReq = mDetReq + detalleregistrosolreq(cod_mae_sol, String.valueOf(correlareq),
                                requisiciones.get(i).getCod_pai(), requisiciones.get(i).getCod_ite(),
                                requisiciones.get(i).getDes_ite(), requisiciones.get(i).getDet_can_sol());
                        misitems.add(i);
                        correlareq = correlareq + 1;
                    }
                }
                Collections.sort(misitems, Collections.reverseOrder());
                Iterator<Integer> iter = misitems.iterator();

                while (iter.hasNext()) {
                    int indice = iter.next();
                    requisiciones.remove(indice);

                }

                String mnombreequipo = macc.strQuerySQLvariable("select concat(equ.nom_equ,'-', lequ.num_ser) from lis_equ as lequ left join cat_equ as equ on lequ.cod_equ = equ.cod_equ where lequ.cod_lis_equ=" + cod_lis_equ + ";");
                String mdepartamento = macc.strQuerySQLvariable("select cod_dep from cat_usu where cod_usu=" + cod_usu + ";");
                String mequipo = macc.strQuerySQLvariable("select cod_equ from lis_equ as lequ where lequ.cod_lis_equ=" + cod_lis_equ + ";");

                mQuery = "insert into req_mae (cod_mae, cod_alt, fec_sol, cod_usu_sol, cod_usu_apr, "
                        + "cod_usu_rec, cod_dep, det_uso, cod_maq, det_sta, det_obs, fec_cie, flg_loc,cod_pai) "
                        + "values (" + cod_mae_req + ",'',now(),"
                        + cod_usu + ",0,0," + mdepartamento + ",'Mantenimiento Equipo " + mnombreequipo + "',"
                        + mequipo + ",0,'Requisición Generada Automáticamente para el Mantenimiento',null,0," + mpais + ");";
                macc.dmlSQLvariable(mQuery);

                mQuery = "insert into req_det(cod_mae,cod_det,cod_pai,cod_bod,cod_ubi,cod_ite,des_ite,"
                        + "det_can_sol,det_can_ent,det_can_pen,non_sto,det_sta,fec_cie,cos_uni) values " + mDetReq.substring(1) + ";";
                macc.dmlSQLvariable(mQuery);

                mQuery = "select ifnull(max(cod_rel),0)+1 as codigo from tbl_rel_man_sol_req;";
                String cod_rel = macc.strQuerySQLvariable(mQuery);
                mQuery = "insert into tbl_rel_man_sol_req (cod_rel,cod_lis_equ,cod_man,det_man,cod_sol_req,flg_sol_req,det_sta_sol_req) VALUES ("
                        + cod_rel + "," + cod_lis_equ + "," + cod_man + ",0," + cod_mae_sol + ",'REQ',0);";
                macc.dmlSQLvariable(mQuery);

                misitems = new ArrayList<>();
                if (requisiciones.size() <= 0) {
                    verificavuelta = 1;
                    break;
                } else {
                    mQuery = "select ifnull(max(cod_mae),0)+1 as codigo from req_mae;";
                    cod_mae_req = macc.strQuerySQLvariable(mQuery);
                }
            }

            macc.Desconectar();
            limpiarventana();
            addMessage("Generación de Solicitudes", " Las respectivas Solicitudes y Requisiciones han sido Generadas Satisfactoriamente para este Mantenimiento.", 1);

        } catch (Exception e) {
            addMessage("Generación de Solicitudes", "Error al Generar Solicitudes.", 2);
            System.out.println("Error al Generar Solicitudes y Requisiciones en ManMaestroMan." + e.getMessage() + " Query: " + mQuery);
            System.out.println(" mDetSol: " + mDetSol);
            System.out.println(" mDetReq: " + mDetReq);
        }
    }

    public String detalleregistrosolreq(String cod_mae, String det_mae, String pais, String codigo, String descripcion, String cantidad) {
        String mDetalles = ",("
                + cod_mae + ","
                + det_mae + ","
                + pais + ","
                + "0" + ","
                + "0" + ","
                + codigo + ",'"
                + descripcion + "',"
                + cantidad + ","
                + "0" + ","
                + cantidad + ","
                + "1" + ","
                + "0" + ","
                + "null"
                + ",0)";

        return mDetalles;
    }

    public void guardarmantenimiento() {
        String mQuery = "";
        if (validarGuardarTodo()) {
            int correlativo = 0;
            Double mItems = 0.0;
            String mValoresGeneral = "", mValoresPiezas = "", mValoresAccesorios = "", mValoresAnexos = "";
            try {
                for (int i = 0; i < general.size(); i++) {
                    mValoresGeneral = mValoresGeneral + ",(" + cod_lis_equ + "," + cod_man + "," + (i + 1)
                            + ",str_to_date('" + general.get(i).getFec_man() + "', '%d/%m/%Y %H:%i'),"
                            + general.get(i).getCod_ope() + ",'" + general.get(i).getDet_obs() + "',"
                            + general.get(i).getCod_usu() + "," + general.get(i).getDet_min() + ")";
                }

                for (int i = 0; i < piezas.size(); i++) {
                    mValoresPiezas = mValoresPiezas + ",(" + cod_lis_equ + "," + cod_man + "," + (i + 1)
                            + ",str_to_date('" + piezas.get(i).getFec_man() + "', '%d/%m/%Y %H:%i'),"
                            + piezas.get(i).getCod_pai() + ",0,0," + piezas.get(i).getDet_can() + ","
                            + piezas.get(i).getCod_pie() + ",'" + piezas.get(i).getNum_ser() + "',"
                            + piezas.get(i).getCod_usu() + "," + piezas.get(i).getFlg_sol() + ")";

                    if ("0".equals(piezas.get(i).getFlg_sol())) {
                        mItems = mItems + Double.valueOf(piezas.get(i).getDet_can());
                    }

                }

                for (int i = 0; i < accesorios.size(); i++) {
                    mValoresAccesorios = mValoresAccesorios + ",(" + cod_lis_equ + "," + cod_man + "," + (i + 1)
                            + ",str_to_date('" + accesorios.get(i).getFec_man() + "', '%d/%m/%Y %H:%i'),"
                            + accesorios.get(i).getCod_pai() + "," + accesorios.get(i).getDet_can() + ",'"
                            + accesorios.get(i).getDes_ite() + "'," + accesorios.get(i).getCod_usu() + ","
                            + accesorios.get(i).getFlg_sol() + ")";

                    if ("0".equals(accesorios.get(i).getFlg_sol())) {
                        mItems = mItems + Double.valueOf(accesorios.get(i).getDet_can());
                    }

                }

                Accesos macc = new Accesos();
                macc.Conectar();
                for (int i = 0; i < anexos.size(); i++) {
                    if ("0".equals(macc.strQuerySQLvariable("select count(det_man) from tbl_det_man_ane where cod_lis_equ =" + cod_lis_equ
                            + " and cod_man = " + cod_man + " and tip_ane = " + anexos.get(i).getTip_ane()
                            + " and rut_ane = '" + anexos.get(i).getRut_ane() + "';"))) {

                        mValoresAnexos = mValoresAnexos + ",(" + cod_lis_equ + "," + cod_man + "," + (i + 1) + ",'"
                                + anexos.get(i).getDet_obs() + "'," + anexos.get(i).getTip_ane() + ",'"
                                + anexos.get(i).getRut_ane().replace("/resources/images/temp/", "/resources/images/anexos/") + "'," + anexos.get(i).getCod_usu() + ")";

                        String nTemporal = anexos.get(i).getRut_ane().replace("/resources/images/temp/", "");

                        File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/temp/config.xml"));
                        String destinationO = mIMGFile.getPath().replace("config.xml", "");

                        File mIMGFile2 = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/anexos/config.xml"));
                        String destinationD = mIMGFile2.getPath().replace("config.xml", "");

                        //Mueve el nuevo archivo
                        try {
                            Path Origen = Paths.get(destinationO + nTemporal);
                            Path Destino = Paths.get(destinationD + nTemporal);
                            Files.move(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);
                        } catch (Exception e) {
                            System.out.println("Error en mover Archivo: " + nTemporal + ". " + e.getMessage());
                        }
                        correlativo = correlativo + 1;

                    } else {
                        macc.dmlSQLvariable("update tbl_det_man_ane set det_man =" + (i + 1) + " where cod_lis_equ =" + cod_lis_equ
                                + " and cod_man = " + cod_man + " and tip_ane = " + anexos.get(i).getTip_ane()
                                + " and rut_ane = '" + anexos.get(i).getRut_ane() + "';");
                    }

                }

                mQuery = "delete from tbl_det_man_gen where cod_lis_equ =" + cod_lis_equ + " and cod_man= " + cod_man + "; ";
                macc.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_det_man_pie where cod_lis_equ =" + cod_lis_equ + " and cod_man= " + cod_man + "; ";
                macc.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_det_man_acc where cod_lis_equ =" + cod_lis_equ + " and cod_man= " + cod_man + "; ";
                macc.dmlSQLvariable(mQuery);

                if (general.size() > 0) {
                    mValoresGeneral = "insert into tbl_det_man_gen (cod_lis_equ,cod_man,det_man,fec_man,cod_ope,det_obs,cod_usu,det_min) VALUES " + mValoresGeneral.substring(1) + ";";
                    macc.dmlSQLvariable(mValoresGeneral);
                }
                if (piezas.size() > 0) {
                    mValoresPiezas = "insert into tbl_det_man_pie (cod_lis_equ,cod_man,det_man,fec_man,cod_pai,"
                            + "cod_bod,cod_ubi,det_can,cod_pie,num_ser,cod_usu,flg_sol) VALUES " + mValoresPiezas.substring(1) + ";";
                    macc.dmlSQLvariable(mValoresPiezas);
                }
                if (accesorios.size() > 0) {
                    mValoresAccesorios = "insert into tbl_det_man_acc (cod_lis_equ,cod_man,det_man,fec_man,cod_pai,"
                            + "det_can,des_ite,cod_usu,flg_sol) VALUES " + mValoresAccesorios.substring(1) + ";";
                    macc.dmlSQLvariable(mValoresAccesorios);
                }
                if (correlativo > 0) {
                    mValoresAnexos = "insert into tbl_det_man_ane (cod_lis_equ,cod_man,det_man,det_obs,tip_ane,"
                            + "rut_ane,cod_usu) VALUES " + mValoresAnexos.substring(1) + ";";
                    macc.dmlSQLvariable(mValoresAnexos);
                }

                macc.dmlSQLvariable("update tbl_mae_man set det_sta = 3 where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";");

                macc.Desconectar();

                addMessage("Guardar Detalles Mantenimiento", "La Información ha sido almacenada con éxito.", 1);
                if (mItems > 0) {
                    mmensaje = "Existen " + mItems + " Items sin solicitar. ¿Desea que el Sistema Genere automáticamente las respectivas solicitudes?";
                    RequestContext.getCurrentInstance().update("frmAutoSolReq");
                    RequestContext.getCurrentInstance().execute("PF('wAutoSolReq').show()");
                }
            } catch (Exception e) {
                addMessage("Guardar Detalles Mantenimiento", "Error en el almacenamiento de la información.", 2);
                System.out.println("Error en guardar Detalles Mantenimiento en ManMaestroMan." + e.getMessage() + " Query: " + mQuery);
                System.out.println(" General: " + mValoresGeneral);
                System.out.println(" Piezas: " + mValoresPiezas);
                System.out.println(" Accesorios: " + mValoresAccesorios);
                System.out.println(" Anexos: " + mValoresAnexos);

            }
        }

    }

    public boolean validarGuardarTodo() {
        boolean mvalidar = true;

        if ("".equals(cod_lis_equ) || "0".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        }
        if ("".equals(cod_man) || "0".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }

        return mvalidar;

    }

    public void finalizarmantenimiento() {
        String mQuery = "";
        if (validarfinalizar()) {
            try {
                Accesos macc = new Accesos();
                macc.Conectar();
                mQuery = "update tbl_mae_man set "
                        + "fec_fin = str_to_date('" + fec_fin + "','%d/%m/%Y %H:%i'), "
                        + "det_sta = 4 "
                        + "where "
                        + "cod_lis_equ= " + cod_lis_equ + " "
                        + "and cod_man = " + cod_man + ";";
                macc.dmlSQLvariable(mQuery);
                if (!"0".equals(cod_per)) {
                    String newcodman = macc.strQuerySQLvariable("select ifnull(max(cod_man),0)+1 as codigo from tbl_mae_man where cod_lis_equ=" + cod_lis_equ + ";");
                    String diasper = macc.strQuerySQLvariable("Select det_dia from cat_per where cod_per=" + cod_per + ";");
                    String newfechaini = macc.strQuerySQLvariable("select date_format(DATE_ADD(str_to_date('" + fec_fin + "','%d/%m/%Y %H:%i'), INTERVAL " + diasper + " DAY),'%d/%m/%Y %H:%i');");
                    mQuery = "insert into tbl_mae_man (cod_lis_equ,cod_man,cod_tip,det_obs,fec_ini,fec_fin,det_sta,cod_usu,cod_per,flg_ext) "
                            + "VALUES (" + cod_lis_equ + "," + newcodman + "," + cod_tip + ",'Mantenimiento Preventivo',"
                            + "str_to_date('" + newfechaini + "','%d/%m/%Y %H:%i'),str_to_date('" + newfechaini + "','%d/%m/%Y %H:%i'),1,"
                            + cod_usu + "," + cod_per + "," + flg_ext + ");";
                    macc.dmlSQLvariable(mQuery);
                }
                macc.Desconectar();
                limpiarventana();
                addMessage("Finalizar Mantenimiento", "El Mantenimiento ha Finalizado Satisfactoriamente.", 1);
            } catch (Exception e) {
                addMessage("Finalizar Mantenimiento", "El Mantenimiento no ha podido Finalizar.", 1);
                System.out.println("Error en Finalizar Mantenimieinto. " + e.getMessage() + " Query: " + mQuery);
            }
        }
    }

    public boolean validarfinalizar() {
        boolean mvalidar = true;

        if ("".equals(cod_lis_equ) || "0".equals(cod_lis_equ)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Equipo.", 2);
        }
        if ("".equals(cod_usu) || "0".equals(cod_usu)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Responsable de Aceptar Finalizar el Mantenimiento.", 2);
        }
        if ("".equals(cod_man) || "0".equals(cod_man)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Mantenimiento.", 2);
        }

        if (general.isEmpty() && piezas.isEmpty() && accesorios.isEmpty() && anexos.isEmpty()) {
            mvalidar = false;
            addMessage("Validar Datos", "Este Mantenimiento no ha tenido ningun movimiento.", 2);
        }
        if (dfecfinF.before(dfecini)) {
            mvalidar = false;
            addMessage("Validar Datos", "La Fecha de Finalización no puede ser menor que la fecha Inicial.", 2);
        }
        Accesos macc = new Accesos();
        macc.Conectar();
        if (!"0".equals(macc.strQuerySQLvariable("select count(flg_sol_req) "
                + "FROM tbl_rel_man_sol_req "
                + "where cod_lis_equ=" + cod_lis_equ + " "
                + "and cod_man=" + cod_man + " "
                + "and det_sta_sol_req in(0,2,4);"))) {
            mvalidar = false;
            addMessage("Validar Datos", "El Mantenimiento Tiene Solicitudes y/o Requisiciones Pendientes.", 2);
        }
        if (!"0".equals(macc.strQuerySQLvariable("select (select count(flg_sol) "
                + "from tbl_det_man_pie where cod_lis_equ = 1 AND cod_man = 1 and flg_sol=0) + ( "
                + "select count(flg_sol) "
                + "from tbl_det_man_acc where cod_lis_equ = 1 AND cod_man = 1 and flg_sol=0);"))) {
            mvalidar = false;
            addMessage("Validar Datos", "El Mantenimiento Tiene Items que aun no han sido Solicitados.", 2);
        }
        macc.Desconectar();

        return mvalidar;

    }

    public void limpiarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dfecha1 = Date.from(Instant.now());
        gen_fec_man = format.format(dfecha1);

        gen_cod_ope = "0";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();

        dfecha2 = Date.from(Instant.now());
        pie_fec_man = format.format(dfecha1);
        pie_cod_pai = cbean.getCod_pai();
        pie_cod_bod = "0";
        pie_cod_ubi = "0";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = cbean.getCod_usu();

        dfecha3 = Date.from(Instant.now());
        acc_fec_man = format.format(dfecha3);
        acc_cod_pai = cbean.getCod_pai();
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = cbean.getCod_usu();

        ane_det_obs = "";
        ane_tip_ane = "0";
        ane_rut_ane = "";
        ane_cod_usu = cbean.getCod_usu();

        llenarMantenimientos();
        general = new ArrayList<>();
        piezas = new ArrayList<>();
        accesorios = new ArrayList<>();
        anexos = new ArrayList<>();
    }

    public void onNodeSelect(NodeSelectEvent event) {
        catmantenimientos = new CatMantenimientos();
        mantenimientos = new ArrayList<>();
        buscar_serie = "";
        cod_man = "";
        if (event.getTreeNode().toString().length() > 4) {
            if ("MNS-".equals(event.getTreeNode().toString().substring(0, 4))) {
                buscar_serie = event.getTreeNode().toString().substring(4);
                limpiarventana();
            }
        }
    }

    public void onclickbuscar() {

        limpiarventana();

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

    public void onRowSelectEnc(SelectEvent event) {
        cod_lis_equ = ((CatMantenimientos) event.getObject()).getCod_lis_equ();
        cod_man = ((CatMantenimientos) event.getObject()).getCod_man();
        cod_tip = ((CatMantenimientos) event.getObject()).getCod_tip();
        det_obs = ((CatMantenimientos) event.getObject()).getDet_obs();
        fec_ini = ((CatMantenimientos) event.getObject()).getFec_ini();
        fec_fin = ((CatMantenimientos) event.getObject()).getFec_fin();
        det_sta = ((CatMantenimientos) event.getObject()).getDet_sta();
        cod_usu = ((CatMantenimientos) event.getObject()).getCod_usu();
        cod_per = ((CatMantenimientos) event.getObject()).getCod_per();
        flg_ext = ((CatMantenimientos) event.getObject()).getFlg_ext();
        cod_sup = ((CatMantenimientos) event.getObject()).getCod_sup();
        cod_dep = ((CatMantenimientos) event.getObject()).getCod_dep();
        turno = ((CatMantenimientos) event.getObject()).getTurno();
        cod_pri = ((CatMantenimientos) event.getObject()).getCod_pri();
        if ("00/00/0000".equals(fec_ini)) {
            fec_ini = "";
        }
        if ("00/00/0000".equals(fec_fin)) {
            fec_fin = "";
        }

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        dfecha1 = Date.from(Instant.now());
        gen_fec_man = format.format(dfecha1);

        gen_cod_ope = "0";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();

        dfecha2 = Date.from(Instant.now());
        pie_fec_man = format.format(dfecha1);
        pie_cod_pai = cbean.getCod_pai();
        pie_cod_bod = "0";
        pie_cod_ubi = "0";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = cbean.getCod_usu();

        dfecha3 = Date.from(Instant.now());
        acc_fec_man = format.format(dfecha3);
        acc_cod_pai = cbean.getCod_pai();
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = cbean.getCod_usu();

        ane_det_obs = "";
        ane_tip_ane = "0";
        ane_rut_ane = "";
        ane_cod_usu = cbean.getCod_usu();

        try {
            dfecini = format.parse(fec_ini);
            dfecfinF = format.parse(fec_fin);
        } catch (Exception ex) {
            System.out.println("Error en convertir fechas encabezado." + ex.getMessage() + " fec_ini: " + fec_ini + " fec_fin: " + fec_fin);
        }

        llenarGeneral();
        llenarPiezas();
        llenarAccesorios();
        llenarAnexos();

    }

    public void onRowUnSelectEnc(SelectEvent event) {
        cod_lis_equ = "";
        cod_man = "";
        cod_tip = "";
        det_obs = "";
        fec_ini = "";
        fec_fin = "";
        det_sta = "";

        general = new ArrayList<>();
        piezas = new ArrayList<>();
        anexos = new ArrayList<>();
        accesorios = new ArrayList<>();

    }

    public void onRowSelectGen(SelectEvent event) {
        gen_det_man = ((CatMantenimientosGen) event.getObject()).getDet_man();
        gen_fec_man = ((CatMantenimientosGen) event.getObject()).getFec_man();
        gen_cod_ope = ((CatMantenimientosGen) event.getObject()).getCod_ope();
        gen_det_obs = ((CatMantenimientosGen) event.getObject()).getDet_obs();
        gen_cod_usu = ((CatMantenimientosGen) event.getObject()).getCod_usu();
        gen_det_min = ((CatMantenimientosGen) event.getObject()).getDet_min();
        if ("00/00/0000".equals(gen_fec_man)) {
            gen_fec_man = "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            dfecha1 = format.parse(gen_fec_man);
        } catch (Exception ex) {

        }

    }

    public void onRowSelectPie(SelectEvent event) {

        pie_det_man = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_fec_man = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_cod_pai = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_cod_bod = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_cod_ubi = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_det_can = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_cod_pie = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_num_ser = ((CatMantenimientosPie) event.getObject()).getDet_man();
        pie_cod_usu = ((CatMantenimientosPie) event.getObject()).getDet_man();
        if ("00/00/0000".equals(pie_fec_man)) {
            pie_fec_man = "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            dfecha2 = format.parse(pie_fec_man);
        } catch (Exception ex) {

        }
    }

    public void onRowSelectAcc(SelectEvent event) {

        acc_det_man = ((CatMantenimientosAcc) event.getObject()).getDet_man();
        acc_fec_man = ((CatMantenimientosAcc) event.getObject()).getFec_man();
        acc_cod_pai = ((CatMantenimientosAcc) event.getObject()).getCod_pai();
        acc_det_can = ((CatMantenimientosAcc) event.getObject()).getDet_can();
        acc_des_ite = ((CatMantenimientosAcc) event.getObject()).getDes_ite();
        acc_cod_usu = ((CatMantenimientosAcc) event.getObject()).getCod_usu();

        if ("00/00/0000".equals(acc_fec_man)) {
            acc_fec_man = "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        try {
            dfecha3 = format.parse(acc_fec_man);
        } catch (Exception ex) {

        }
    }

    public void onRowSelectAne(SelectEvent event) {

        ane_det_man = ((CatMantenimientosAne) event.getObject()).getDet_man();
        ane_det_obs = ((CatMantenimientosAne) event.getObject()).getDet_obs();
        ane_tip_ane = ((CatMantenimientosAne) event.getObject()).getTip_ane();
        ane_rut_ane = ((CatMantenimientosAne) event.getObject()).getRut_ane();
        ane_cod_usu = ((CatMantenimientosAne) event.getObject()).getCod_usu();

    }

    public void onRowSelectFal(SelectEvent event) {
        cod_gru_fal = ((CatMantenimientosFal) event.getObject()).getCod_gru_fal();
        llenarListaFallas();
        cod_fal = ((CatMantenimientosFal) event.getObject()).getCod_fal();

    }

    public void onChangeGrupoFalla() {
        llenarListaFallas();
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tabGEN":
                tabindex = "0";
                break;
            case "tabPIE":
                tabindex = "1";
                break;
            case "tabACC":
                tabindex = "2";
                break;
            case "tabANE":
                tabindex = "3";
                break;

        }
        //System.out.println(tabindex);
        //RequestContext.getCurrentInstance().update(":frmListaEquipos:tvLE");
    }

    public void dateSelectedFencabezado(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fec_ini = format.format(date);
    }

    public void dateSelectedFencabezadoFin(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        fec_fin = format.format(date);
    }

    public void dateSelectedGeneral(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        gen_fec_man = format.format(date);
    }

    public void dateSelectedPiezas(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        pie_fec_man = format.format(date);
    }

    public void dateSelectedAccesorios(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        acc_fec_man = format.format(date);
    }

    public void upload(FileUploadEvent event) {
        try {
            String destination = "";
            File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/anexos/config.xml"));
            destination = mIMGFile.getPath().replace("config.xml", "");

            //Verifica que no exista otro archivo con el mismo nombre.
            try {
                File mfile = new File(destination + "ane_equ_" + cod_lis_equ + "_man_" + cod_man + "_" + event.getFile().getFileName().toLowerCase());
                if (mfile.exists()) {
                    addMessage("Procesar Archivo", "El Archivo " + event.getFile().getFileName() + " ya Existe en este Mantenimiento. ", 2);
                } else {
                    Random rnd = new Random();
                    String prefijo = String.valueOf(((int) (rnd.nextDouble() * 100)) + ((int) (rnd.nextDouble() * 100)) * ((int) (rnd.nextDouble() * 100)));
                    copyFile("ane_equ_" + cod_lis_equ + "_man_" + cod_man + "_" + event.getFile().getFileName().toLowerCase(), event.getFile().getInputstream());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        } catch (Exception e) {
            addMessage("Procesar Archivo", "El Archivo " + event.getFile().getFileName() + " No se ha podido Cargar. " + e.getMessage(), 2);
            System.out.println("Error en subir archivo Mantenimiento." + e.getMessage());
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            String destination = "";
            File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/temp/config.xml"));
            ane_rut_ane = "/resources/images/temp/" + fileName;

            destination = mIMGFile.getPath().replace("config.xml", "");

            //Verifica que no exista otro archivo con el mismo nombre.
            try {
                File mfile = new File(destination + fileName);
                if (mfile.exists()) {
                    mfile.delete();
                }

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName.toLowerCase()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            addMessage("Copiar Archivo Mantenimiento", "El Archivo en copyFyle" + fileName + " No se ha podido procesar. " + e.getMessage(), 2);
            System.out.println(e.getMessage());

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

    // ********************************* Ver Solicitudes ************************************
    private CatSolicitudes catmaestro;
    private List<CatSolicitudes> maestro;
    private CatSolicitudesDetalle catdetalles;
    private List<CatSolicitudesDetalle> detalles;

    private String mansolreq, flagsolreq;

    public CatSolicitudes getCatmaestro() {
        return catmaestro;
    }

    public void setCatmaestro(CatSolicitudes catmaestro) {
        this.catmaestro = catmaestro;
    }

    public List<CatSolicitudes> getMaestro() {
        return maestro;
    }

    public void setMaestro(List<CatSolicitudes> maestro) {
        this.maestro = maestro;
    }

    public CatSolicitudesDetalle getCatdetalles() {
        return catdetalles;
    }

    public void setCatdetalles(CatSolicitudesDetalle catdetalles) {
        this.catdetalles = catdetalles;
    }

    public List<CatSolicitudesDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CatSolicitudesDetalle> detalles) {
        this.detalles = detalles;
    }

    public String getFlagsolreq() {
        return flagsolreq;
    }

    public void setFlagsolreq(String flagsolreq) {
        this.flagsolreq = flagsolreq;
    }

    public String getMansolreq() {
        return mansolreq;
    }

    public void setMansolreq(String mansolreq) {
        this.mansolreq = mansolreq;
    }

    public void iniciarventanaSolicitudes() {
        if ("".equals(cod_man) || "0".equals(cod_man)) {
            addMessage("Solicitudes", "Debe Seleccionar un Mantenimiento.", 2);
        } else {
            mansolreq = "";
            flagsolreq = "";
            detalles = new ArrayList<>();
            llenarMaestro();
            RequestContext.getCurrentInstance().execute("PF('wSolReqMan').show()");
        }

    }

    public void cerrarventanaSolicitudes() {
        mansolreq = "";
        flagsolreq = "";
        maestro = new ArrayList<>();
        detalles = new ArrayList<>();
    }

    public void llenarMaestro() {
        try {
            catmaestro = new CatSolicitudes();
            maestro = new ArrayList<>();

            String mQuery = "SELECT * FROM ( "
                    + "select  "
                    + "mae.cod_mae, mae.cod_alt,  "
                    + "date_format(mae.fec_sol,'%d/%m/%Y') as fec_sol,  "
                    + "'SOLICITUD' as label,  "
                    + "mae.cod_usu_apr, mae.cod_usu_rec, mae.cod_dep, mae.det_uso, mae.cod_maq,  "
                    + "case mae.det_sta  "
                    + "when 0 then 'ESPERA APROBACIÓN'  "
                    + "when 1 then 'CANCELADA'  "
                    + "when 2 then 'APROBADA'  "
                    + "when 3 then 'DENEGADA'  "
                    + "when 4 then 'PENDIENTE'  "
                    + "when 5 then 'CERRADA' end as sta,  "
                    + "mae.det_obs,  "
                    + "date_format(mae.fec_cie,'%d/%m/%Y') as fec_fin, mae.flg_loc,mae.cod_pai,  "
                    + "dep.nom_dep, concat(maq.nom_equ,'-',lis.num_ser) as nomequ, "
                    + "pai.nom_alm, usu.det_nom  "
                    + "FROM sol_mae as mae  "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep  "
                    + "left join lis_equ as lis on mae.cod_maq = lis.cod_lis_equ  "
                    + "left join cat_equ as maq on lis.cod_equ = maq.cod_equ  "
                    + "left join cat_alm as pai on mae.cod_pai = pai.cod_alm  "
                    + "left join cat_usu as usu on mae.cod_usu_sol = usu.cod_usu  "
                    + "inner join tbl_rel_man_sol_req as rel on mae.cod_mae = rel.cod_sol_req "
                    + "where  "
                    + "rel.flg_sol_req = 'SOL' and rel.cod_lis_equ = " + cod_lis_equ + "  and rel.cod_man= " + cod_man + " "
                    + "UNION ALL "
                    + "select  "
                    + "req.cod_mae, req.cod_alt,  "
                    + "date_format(req.fec_sol,'%d/%m/%Y') as fec_sol,  "
                    + "'REQUISICIÓN' as label,  "
                    + "req.cod_usu_apr, req.cod_usu_rec, req.cod_dep, req.det_uso, req.cod_maq,  "
                    + "case req.det_sta  "
                    + "when 0 then 'ESPERA APROBACIÓN'  "
                    + "when 1 then 'CANCELADA'  "
                    + "when 2 then 'APROBADA'  "
                    + "when 3 then 'DENEGADA'  "
                    + "when 4 then 'PENDIENTE'  "
                    + "when 5 then 'CERRADA' end as sta,  "
                    + "req.det_obs,  "
                    + "date_format(req.fec_cie,'%d/%m/%Y') as fec_fin, req.flg_loc,req.cod_pai,  "
                    + "dep.nom_dep, concat(maq.nom_equ,'-',lis.num_ser) as nomequ, "
                    + "pai.nom_alm, usu.det_nom  "
                    + "FROM req_mae as req  "
                    + "left join cat_dep as dep on req.cod_dep = dep.cod_dep  "
                    + "left join lis_equ as lis on req.cod_maq = lis.cod_lis_equ  "
                    + "left join cat_equ as maq on lis.cod_equ = maq.cod_equ  "
                    + "left join cat_alm as pai on req.cod_pai = pai.cod_alm  "
                    + "left join cat_usu as usu on req.cod_usu_sol = usu.cod_usu  "
                    + "inner join tbl_rel_man_sol_req as rel on req.cod_mae = rel.cod_sol_req "
                    + "where  "
                    + "rel.flg_sol_req = 'REQ' and rel.cod_lis_equ = " + cod_lis_equ + " and rel.cod_man= " + cod_man + " "
                    + ") AS UNIDO "
                    + "order by fec_sol desc, label;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                maestro.add(new CatSolicitudes(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10),
                        resVariable.getString(11),
                        resVariable.getString(12),
                        resVariable.getString(13),
                        resVariable.getString(14),
                        resVariable.getString(15),
                        resVariable.getString(16),
                        resVariable.getString(17),
                        resVariable.getString(18)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Maestro de Solicitudes y Requisiciones en ManMaestroMan. " + e.getMessage());
        }
    }

    public void llenarDetalles() {
        try {
            catdetalles = new CatSolicitudesDetalle();
            detalles = new ArrayList<>();
            String mwhere;

            if ("SOLICITUD".equals(flagsolreq)) {
                mwhere = "sol_det";
            } else {
                mwhere = "req_det";
            }

            String mQuery = "select  "
                    + "det.cod_mae, det.cod_det, det.cod_pai, det.cod_bod, "
                    + "det.cod_ubi, det.cod_ite, det.des_ite, det.det_can_sol, det.det_can_ent, "
                    + "det.det_can_pen, det.non_sto, "
                    + "case det.det_sta "
                    + "when 0 then 'PENDIENTE' "
                    + "when 1 then 'COTIZADO' "
                    + "when 2 then 'COMPRADO' "
                    + "when 3 then 'ENTREGA PARCIAL' "
                    + "when 4 then 'ENTREGADO' "
                    + "when 5 then 'CANCELADO' "
                    + "end as sta , "
                    + "det.fec_cie, "
                    + "det.cos_uni,"
                    + "pai.nom_alm, bod.nom_bod, ubi.nom_ubi "
                    + "from " + mwhere + " as det "
                    + "left join cat_alm as pai on det.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on det.cod_pai = bod.cod_pai and det.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on det.cod_bod = ubi.cod_bod and det.cod_ubi = ubi.id_ubi "
                    + "where det.cod_mae = " + mansolreq + " order by det.cod_det asc;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detalles.add(new CatSolicitudesDetalle(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
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
            System.out.println("Error en el llenado Detalles Solicitudes y Requisiciones en ManMaestroMan. " + e.getMessage());
        }
    }

    public void onRowSelect(SelectEvent event) {
        mansolreq = ((CatSolicitudes) event.getObject()).getCod_mae();
        flagsolreq = ((CatSolicitudes) event.getObject()).getCod_usu_sol(); // Se cambió el código de usuario por un flag de solicitud o requsición en la consulta.
        llenarDetalles();
    }

    public void onRowUnselect(UnselectEvent event) {
        detalles = new ArrayList<>();
    }

    public void onEventSelect(SelectEvent selectEvent) {

        ScheduleEvent smtto = (ScheduleEvent) selectEvent.getObject();
        TimelineEvent tlmtto = (TimelineEvent) selectEvent.getObject();

        for (CatCalendario cm : listaMttos) {
            if (cm.getCod_man() == smtto.getData()) {
                catcalendario = cm;
                buscar_serie = catcalendario.getCod_lis_equ();
                llenarMantenimientos();
                break;
            }
        }
    }
    
    public void onMttoSelect(String cod_lis_equ) {
                buscar_serie = cod_lis_equ;
                llenarMantenimientos();            
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

        for (CatCalendario cm : listaMttos) {
            if (cm.getCod_man() == mttoMove.getScheduleEvent().getData()) {
                catcalendario = cm;
                actualizar();
                break;
            }
        }

    }

    public void onEventResize(ScheduleEntryResizeEvent mttoResize) {
        for (CatCalendario cm : listaMttos) {
            if (cm.getCod_man() == mttoResize.getScheduleEvent().getData()) {
                catcalendario = cm;
                actualizar();
                break;
            }
        }
    }

    public void imprimir_f_man_004() {
        try {
            byte[] content;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            content = imprimirFicha();
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
    
    public void onSelect(TimelineSelectEvent e) {

        TimelineEvent tlmtto = (TimelineEvent) e.getTimelineEvent();

        for (CatCalendario cm : listaMttos) {
            if (cm.getCod_man() == tlmtto.getData()) {
                catcalendario = cm;
                buscar_serie = catcalendario.getCod_lis_equ();
                llenarMantenimientos();
                break;
            }
        }
    }
        
    public byte[] imprimirFicha() throws SQLException, JRException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportPath = ctx.getRealPath(File.separator + "reportes" + File.separator);
        HashMap param = new HashMap();
        param.put("cod_lis_equ", cod_lis_equ);
        param.put("cod_man", cod_man);

        Accesos racc = new Accesos();
        return JasperRunManager.runReportToPdf(reportPath + File.separator + "FMAN004.jasper", param, racc.Conectar());
    }
    
}
