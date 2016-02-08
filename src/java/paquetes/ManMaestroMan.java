package paquetes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
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

    private String cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, cod_per;
    private String gen_det_man, gen_fec_man, gen_cod_ope, gen_det_obs, gen_cod_usu;
    private String pie_det_man, pie_fec_man, pie_cod_pai, pie_cod_bod, pie_cod_ubi,
            pie_det_can, pie_cod_pie, pie_num_ser, pie_cod_usu;
    private String ane_det_man, ane_det_obs, ane_tip_ane, ane_rut_ane, ane_cod_usu;
    private String acc_det_man, acc_fec_man, acc_cod_pai, acc_det_can, acc_des_ite, acc_cod_usu;

    private String tabindex, buscar_serie, nompai, nombod, nomubi, cod_gru_fal, cod_fal;
    private Date dfecha1, dfecha2, dfecha3, dfecfinF;
    private TreeNode root, selectednode;

    private UploadedFile file;

    public ManMaestroMan() {
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

    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dfecha1 = Date.from(Instant.now());
        dfecha2 = dfecha1;
        dfecha3 = dfecha1;

        tabindex = "0";

        cod_lis_equ = "";
        cod_man = "";
        cod_tip = "";
        det_obs = "";
        fec_ini = format.format(dfecha1);
        fec_fin = format.format(dfecha1);
        det_sta = "";
        cod_usu = cbean.getCod_usu();
        cod_per = "0";
        gen_det_man = "";
        gen_fec_man = format.format(dfecha1);
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();
        pie_det_man = "";
        pie_fec_man = format.format(dfecha1);
        pie_cod_pai = cbean.getCod_pai();
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
        acc_cod_pai = cbean.getCod_pai();
        acc_det_can = "";
        acc_des_ite = "";
        acc_cod_usu = cbean.getCod_usu();
        buscar_serie = "";
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
        gen_det_man = "";
        gen_fec_man = "";
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = "";
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
        cod_usu = cbean.getCod_usu();
        cod_per = "0";
        gen_det_man = "";
        gen_fec_man = "";
        gen_cod_ope = "";
        gen_det_obs = "";
        gen_cod_usu = cbean.getCod_usu();
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
        root = null;
        selectednode = null;
        ubicaciones = new ArrayList<>();
        existenciareal = new ArrayList<>();
    }

    public void iniciarventananew() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dfecfinF = Date.from(Instant.now());

        cod_man = "";
        cod_tip = "0";
        det_obs = "";
        fec_ini = format.format(dfecfinF);
        fec_fin = "";
        det_sta = "0";
        cod_usu = cbean.getCod_usu();
        cod_per = "0";
        cod_gru_fal = "0";
        cod_fal = "0";
        llenarTipos();
        llenarPeriodos();
        llenarGrupoFallas();
        catmantenimientosfal = new CatMantenimientosFal();
        fallas = new ArrayList<>();

    }

    public void iniciarventanaedit() {
        fallas = new ArrayList<>();
        llenarTipos();
        llenarPeriodos();
        llenarFallas();
        llenarGrupoFallas();

        if ("".equals(cod_man) || "0".equals(cod_man)) {
            addMessage("Modificar Mantenimiento", "Debe Seleccionar un Registro a Modificar.", 2);
        } else {
            RequestContext.getCurrentInstance().execute("PF('wMaestraNew').show()");
        }
    }

    public void iniciarventanaexistencia() {
        catpiezasconexistencia = new CatPiezasConExistencia();
        existencias = new ArrayList<>();
        Accesos acc = new Accesos();
        acc.Conectar();
        nompai = acc.strQuerySQLvariable("select nom_alm from cat_alm where cod_pai=" + pie_cod_pai + ";");
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

            mQuery = "select lis.cod_pai, pai.nom_pai,lis.cod_equ,equ.nom_equ, lis.num_ser "
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
            catmantenimientosane = new CatMantenimientosAne();
            anexos = new ArrayList<>();
            catmantenimientos = new CatMantenimientos();
            mantenimientos = new ArrayList<>();

            if (!"".equals(buscar_serie)) {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                mQuery = "select cod_lis_equ "
                        + "from lis_equ "
                        + "where "
                        + "num_ser = " + buscar_serie + ";";
                cod_lis_equ = mAccesos.strQuerySQLvariable(mQuery);
                mQuery = "select mm.cod_lis_equ, mm.cod_man, mm.cod_tip, mm.det_obs, "
                        + "date_format(mm.fec_ini,'%d/%m/%Y'), "
                        + "date_format(mm.fec_fin,'%d/%m/%Y'), "
                        + "mm.det_sta, mm.cod_usu,tip.nom_tip,"
                        + "case mm.det_sta "
                        + "when 1 then 'PENDIENTE' "
                        + "when 2 then 'CANCELADO' "
                        + "when 3 then 'EN PROCESO' "
                        + "when 4 then 'FINALIZADO' "
                        + "end as status,"
                        + "(TIMESTAMPDIFF(MONTH,mm.fec_ini,now())) as dr,"
                        + "if((TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))<=1,'lime',if((TIMESTAMPDIFF(MONTH,mm.fec_ini,now()))<=2,'yellow','red')) as color,"
                        + "mm.cod_per, per.nom_per "
                        + "from tbl_mae_man as mm "
                        + "left join cat_tip as tip on mm.cod_tip = tip.cod_tip "
                        + "left join lis_equ as lis on mm.cod_lis_equ = lis.cod_lis_equ "
                        + "left join cat_per as per on mm.cod_per = per.cod_per "
                        + "where "
                        + "mm.det_sta IN (1,3) "
                        + "and lis.num_ser =" + buscar_serie + " "
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
                            resVariable.getString(14)
                    ));

                }
                mAccesos.Desconectar();
            }
        } catch (Exception e) {
            System.out.println("Error en el llenado de Mantenimientos Pendientes en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
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
                    + "usu.det_nom "
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
                        resVariable.getString(9)
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
                    + "acc.cod_lis_equ, acc.cod_man, acc.det_man, acc.fec_man, "
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
                    + "when 3 then 'OTRO' "
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

    public void guardarencabezado() {
        String mQuery = "";
        if (validarencabezado()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_man)) {
                    mQuery = "select ifnull(max(cod_man),0)+1 as codigo from tbl_mae_man where cod_lis_equ = " + cod_lis_equ + ";";
                    cod_man = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into tbl_mae_man (cod_lis_equ,cod_man,cod_tip,det_obs,fec_ini,fec_fin,det_sta,cod_usu,cod_per) "
                            + "VALUES (" + cod_lis_equ + "," + cod_man + "," + cod_tip + ",'" + det_obs.replace("'", " ") + "',"
                            + "str_to_date('" + fec_ini + "','%d/%m/%Y'),null,1," + cod_usu + "," + cod_per + ");";
                } else {
                    mQuery = "delete from tbl_det_man_fal where cod_lis_equ=" + cod_lis_equ + " and cod_man=" + cod_man + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                    mQuery = "update tbl_mae_man set "
                            + "cod_tip= " + cod_tip + ","
                            + "det_obs= '" + det_obs.replace("'", " ") + "',"
                            + "cod_usu = " + cod_usu + ","
                            + "cod_per= " + cod_per + " "
                            + "where cod_lis_equ = " + cod_lis_equ + " "
                            + "and cod_man = " + cod_man + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                String mValues = "";
                for (int i = 0; i < fallas.size(); i++) {
                    mValues = mValues + ",(" + cod_lis_equ + "," + cod_man + ","
                            + (i + 1) + "," + fallas.get(i).getCod_gru_fal() + "," + fallas.get(i).getCod_fal() + ",'')";
                }
                mQuery = "insert into tbl_det_man_fal (cod_lis_equ,cod_man,det_man,cod_gru_fal,cod_fal,det_obs) VALUES" + mValues.substring(1) + ";";
                mAccesos.dmlSQLvariable(mQuery);
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
                        nomusu
                ));

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dfecha1 = Date.from(Instant.now());
                gen_fec_man = format.format(dfecha1);
                gen_cod_ope = "0";
                gen_det_obs = "";
                gen_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar General ManMaestroMan." + e.getMessage());
            }
        }

    }

    public boolean validargeneral() {
        boolean mvalidar = true;
        String mQuery = "";
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
                String nombodpie = macc.strQuerySQLvariable("select ifnull(det_nom,'') from cat_bodegas where cod_pai=" + pie_cod_pai + " and id_bod =" + pie_cod_bod + ";");
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
            addMessage("Validar Datos", "Debe Seleccionar un País.", 2);
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
                    anexos.remove(i);
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
            addMessage("Validar Datos", "Debe Seleccionar uns Falla Específica.", 2);
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

    public void onNodeSelect(NodeSelectEvent event) {
        catmantenimientos = new CatMantenimientos();
        mantenimientos = new ArrayList<>();
        buscar_serie = "";
        cod_man = "";
        if (event.getTreeNode().toString().length() > 4) {
            if ("MNS-".equals(event.getTreeNode().toString().substring(0, 4))) {
                buscar_serie = event.getTreeNode().toString().substring(4);
                llenarMantenimientos();
            }
        }
    }

    public void onclickbuscar() {
        selectednode = null;
        if ("".equals(buscar_serie)) {
            llenarNodos();
        } else {
            llenarMantenimientos();
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

        try {
            dfecfinF = format.parse(fec_ini);
        } catch (Exception ex) {
            System.out.println("Error en convertir fecha encabezado." + ex.getMessage());
        }

        llenarGeneral();
        llenarPiezas();
        llenarAccesorios();
        llenarAnexos();

    }

    public void onRowSelectGen(SelectEvent event) {
        gen_det_man = ((CatMantenimientosGen) event.getObject()).getDet_man();
        gen_fec_man = ((CatMantenimientosGen) event.getObject()).getFec_man();
        gen_cod_ope = ((CatMantenimientosGen) event.getObject()).getCod_ope();
        gen_det_obs = ((CatMantenimientosGen) event.getObject()).getDet_obs();
        gen_cod_usu = ((CatMantenimientosGen) event.getObject()).getCod_usu();
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
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_ini = format.format(date);
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
            //dbImage = new DefaultStreamedContent(event.getFile().getInputstream(), "image/jpeg");
            Random rnd = new Random();
            String prefijo = String.valueOf(((int) (rnd.nextDouble() * 100)) + ((int) (rnd.nextDouble() * 100)) * ((int) (rnd.nextDouble() * 100)));
            copyFile("ane_temp_" + prefijo + "_" + event.getFile().getFileName().toLowerCase(), event.getFile().getInputstream());

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
            addMessage("Copiar Archivo Mantenimeiinto", "El Archivo en copyFyle" + fileName + " No se ha podido procesar. " + e.getMessage(), 2);
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

}
