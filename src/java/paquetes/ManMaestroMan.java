package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Named
@ConversationScoped

public class ManMaestroMan implements Serializable {

    private static final long serialVersionUID = 8774297541534938L;
    @Inject
    Login cbean;
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

    private String cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu, cod_per;
    private String gen_det_man, gen_fec_man, gen_cod_ope, gen_det_obs, gen_cod_usu;
    private String pie_det_man, pie_fec_man, pie_cod_pai, pie_cod_bod, pie_cod_ubi,
            pie_det_can, pie_cod_pie, pie_num_ser, pie_cod_usu;
    private String ane_det_man, ane_det_obs, ane_tip_ane, ane_rut_ane, ane_cod_usu;

    private String tabindex, buscar_serie, nompai, nombod, nomubi;
    private Date dfecha1, dfecha2, dfecfinF;
    private TreeNode root, selectednode;

    public ManMaestroMan() {
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

    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dfecha1 = Date.from(Instant.now());
        dfecha2 = dfecha1;

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
        buscar_serie = "";
        root = null;
        selectednode = null;
        catmantenimientos = new CatMantenimientos();
        mantenimientos = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        existenciareal = new ArrayList<>();
        llenarNodos();
        llenarUsuarios();
        llenarOperaciones();
        llenarPaises();
        llenarBodegas();

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
        llenarTipos();
        llenarPeriodos();

    }

    public void iniciarventanaexistencia() {
        catpiezasconexistencia = new CatPiezasConExistencia();
        existencias = new ArrayList<>();
        Accesos acc = new Accesos();
        acc.Conectar();
        nompai = acc.strQuerySQLvariable("select nom_pai from cat_pai where cod_pai=" + pie_cod_pai + ";");
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
                    + "exi.det_can, pie.nom_pie, pai.nom_pai, bod.nom_bod, ubi.nom_ubi "
                    + "from tbl_existencias as exi "
                    + "left join cat_pie as pie on exi.cod_pie = pie.cod_pie "
                    + "left join cat_pai as pai on exi.cod_pai = pai.cod_pai "
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

    public void llenarPaises() {
        try {
            paises = new ArrayList<>();

            String mQuery = "select cod_pai, nom_pai "
                    + "from cat_pai order by cod_pai;";
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

    public void llenarBodegas() {
        String mQuery = "";
        try {

            bodegas = new ArrayList<>();

            mQuery = "select id_bod, nom_bod,cod_pai "
                    + "from cat_bodegas "
                    + "where cod_pai=" + pie_cod_pai + " "
                    + "order by id_bod;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                bodegas.add(new CatBodegas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        ""
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Bodegas en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarUbicaciones() {
        String mQuery = "";
        try {

            ubicaciones = new ArrayList<>();

            mQuery = "select ubi.id_ubi,ubi.cod_bod,ubi.nom_ubi,bod.nom_bod "
                    + "from cat_ubicaciones as ubi "
                    + "left join cat_bodegas as bod on bod.id_bod = ubi.cod_bod "
                    + "where bod.cod_pai = " + pie_cod_pai + " "
                    + "and ubi.cod_bod=" + pie_cod_bod + " "
                    + "order by ubi.cod_bod,ubi.id_ubi;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                ubicaciones.add(new CatUbicaciones(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        "",
                        ""
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Ubicaciones en ManMaestroMan. " + e.getMessage() + " Query: " + mQuery);
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
                    + "sum(res.det_can) as reserva "
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

            mQuery = "select cod_tip, nom_tip from cat_tip order by cod_tip;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                tipos.add(new CatTipos(
                        resVariable.getString(1),
                        resVariable.getString(2)
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
                    + "gen.cod_pie, gen.num_ser, gen.cod_usu,pai.nom_pai as nompai, "
                    + "bod.nom_bod as nombod, ubi.nom_ubi as nomubi,"
                    + "usu.det_nom as nomusu, pie.nom_pie as nompie "
                    + "from tbl_det_man_pie as gen "
                    + "left join cat_pai as pai on gen.cod_pai = pai.cod_pai "
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
                        resVariable.getString(16)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Detalle Piezas en ManMaestroMan" + e.getMessage() + " Query: " + mQuery);
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

    public void guardarencabezado() {
        String mQuery = "";
        if (validarencabezado()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();

                mQuery = "select ifnull(max(cod_man),0)+1 as codigo from tbl_mae_man where cod_lis_equ = " + cod_lis_equ + ";";
                cod_man = mAccesos.strQuerySQLvariable(mQuery);
                mQuery = "insert into tbl_mae_man (cod_lis_equ,cod_man,cod_tip,det_obs,fec_ini,fec_fin,det_sta,cod_usu,cod_per) "
                        + "VALUES (" + cod_lis_equ + "," + cod_man + "," + cod_tip + ",'" + det_obs + "',"
                        + "str_to_date('" + fec_ini + "','%d/%m/%Y'),null,1," + cod_usu + "," + cod_per + ");";
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

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dfecha1 = Date.from(Instant.now());
                gen_fec_man = format.format(dfecha1);
                gen_cod_ope = "0";
                gen_det_obs = "";
                gen_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar General ManListaEquipos." + e.getMessage());
            }
        }

    }

    public boolean validargeneral() {
        boolean mvalidar = true;
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

        return mvalidar;

    }

    public void eliminargeneral() {

        if ("".equals(gen_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Mantenimiento para Remover.", 2);
        } else {
            for (int i = 0; i < general.size(); i++) {
                if (cod_lis_equ.equals(general.get(i).getCod_lis_equ())
                        && cod_man.equals(general.get(i).getCod_man())
                        && gen_det_man.equals(general.get(i).getDet_man())) {
                    general.remove(i);
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
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

                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                dfecha1 = Date.from(Instant.now());
                gen_fec_man = format.format(dfecha1);
                gen_cod_ope = "0";
                gen_det_obs = "";
                gen_cod_usu = cbean.getCod_usu();

            } catch (Exception e) {
                System.out.println("Error en Agregar General ManListaEquipos." + e.getMessage());
            }
        }

    }

    public boolean validarpiezas() {
        boolean mvalidar = true;
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

        return mvalidar;

    }

    public void eliminarpiezas() {

        if ("".equals(gen_det_man)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Mantenimiento para Remover.", 2);
        } else {
            for (int i = 0; i < general.size(); i++) {
                if (cod_lis_equ.equals(general.get(i).getCod_lis_equ())
                        && cod_man.equals(general.get(i).getCod_man())
                        && gen_det_man.equals(general.get(i).getDet_man())) {
                    general.remove(i);
                }
            }
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            dfecha1 = Date.from(Instant.now());
            gen_fec_man = format.format(dfecha1);
            gen_cod_ope = "0";
            gen_det_obs = "";
            gen_cod_usu = cbean.getCod_usu();
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
        llenarMantenimientos();

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

        pie_fec_man = format.format(dfecha1);
        pie_cod_pai = cbean.getCod_pai();
        pie_cod_bod = "0";
        pie_cod_ubi = "0";
        pie_det_can = "";
        pie_cod_pie = "";
        pie_num_ser = "";
        pie_cod_usu = cbean.getCod_usu();

        llenarGeneral();
        llenarPiezas();
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

    public void onChangePais() {
        llenarBodegas();
        ubicaciones = new ArrayList<>();
        existenciareal = new ArrayList<>();
    }

    public void onChangeBodegas() {
        llenarUbicaciones();
        existenciareal = new ArrayList<>();
    }

    public void onChangeubicaciones() {
        llenarPiezasExistentes();
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tabGEN":
                tabindex = "0";
                break;
            case "tabPIE":
                tabindex = "1";
                break;
            case "tabANE":
                tabindex = "2";
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
