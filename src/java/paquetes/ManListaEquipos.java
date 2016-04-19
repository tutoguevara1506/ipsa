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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;

@Named
@ConversationScoped
public class ManListaEquipos implements Serializable {

    private static final long serialVersionUID = 8774836761534938L;
    @Inject
    Login cbean;
    private CatTipos cattipos;
    private List<CatTipos> tipos;
    private CatMantenimientos catmantenimientos;
    private List<CatMantenimientos> mantenimientos;
    private CatProveedores catproveedores;
    private List<CatProveedores> proveedores;
    private CatClientes catclientes;
    private List<CatClientes> clientes;
    private CatClientes catclientesb;
    private List<CatClientes> clientesb;
    private CatPaises catpaises;
    private List<CatPaises> paises;
    private CatUsuarios catusuarios;
    private List<CatUsuarios> usuarios;
    private CatContratos catcontratos;
    private List<CatContratos> contratos;
    private CatGarantias catgarantias;
    private List<CatGarantias> garantias;
    private CatSistemas catsistemas;
    private List<CatSistemas> sistemas;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatListaEquipos catlistaequipos;
    private List<CatListaEquipos> lequipos;

    private String cod_lis_equ, cod_pai, cod_equ, cod_pro, cod_cli, num_mod, num_ser, des_equ,
            des_ubi, fec_fab, fec_com, fec_adq, fec_pue_ser, fec_ult_man, fec_ret, cod_bar;

    private String gar_fec_ini, gar_fec_fin, gar_obs;

    private String cod_equ_b, cod_cli_b, cod_pai_b, num_ser_b;

    private String tabindex;

    private String s_cod_sys, s_det_obs, s_ver_ant, s_ver_act, s_fec_act;

    private String c_cod_con, c_cod_ref, c_des_inf, c_fec_con, c_fec_exp;

    private String m_cod_man, m_cod_tip, m_det_obs, m_fec_ini, m_fec_fin, m_det_sta, m_nomtip, m_estado;

    private String nombre_mantenimiento;

    private Date dfecfab, dfeccom, dfecadq, dfecpueser, dfecultman, dfecret, dfecini, dfecfin, dfecact, dfeccon, dfecexp;

    public ManListaEquipos() {
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

    public CatProveedores getCatproveedores() {
        return catproveedores;
    }

    public void setCatproveedores(CatProveedores catproveedores) {
        this.catproveedores = catproveedores;
    }

    public List<CatProveedores> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<CatProveedores> proveedores) {
        this.proveedores = proveedores;
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

    public CatClientes getCatclientesb() {
        return catclientesb;
    }

    public void setCatclientesb(CatClientes catclientesb) {
        this.catclientesb = catclientesb;
    }

    public List<CatClientes> getClientesb() {
        return clientesb;
    }

    public void setClientesb(List<CatClientes> clientesb) {
        this.clientesb = clientesb;
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

    public CatContratos getCatcontratos() {
        return catcontratos;
    }

    public void setCatcontratos(CatContratos catcontratos) {
        this.catcontratos = catcontratos;
    }

    public List<CatContratos> getContratos() {
        return contratos;
    }

    public void setContratos(List<CatContratos> contratos) {
        this.contratos = contratos;
    }

    public CatGarantias getCatgarantias() {
        return catgarantias;
    }

    public void setCatgarantias(CatGarantias catgarantias) {
        this.catgarantias = catgarantias;
    }

    public List<CatGarantias> getGarantias() {
        return garantias;
    }

    public void setGarantias(List<CatGarantias> garantias) {
        this.garantias = garantias;
    }

    public CatSistemas getCatsistemas() {
        return catsistemas;
    }

    public void setCatsistemas(CatSistemas catsistemas) {
        this.catsistemas = catsistemas;
    }

    public List<CatSistemas> getSistemas() {
        return sistemas;
    }

    public void setSistemas(List<CatSistemas> sistemas) {
        this.sistemas = sistemas;
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

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getCod_equ() {
        return cod_equ;
    }

    public void setCod_equ(String cod_equ) {
        this.cod_equ = cod_equ;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getNum_mod() {
        return num_mod;
    }

    public void setNum_mod(String num_mod) {
        this.num_mod = num_mod;
    }

    public String getNum_ser() {
        return num_ser;
    }

    public void setNum_ser(String num_ser) {
        this.num_ser = num_ser;
    }

    public String getDes_equ() {
        return des_equ;
    }

    public void setDes_equ(String des_equ) {
        this.des_equ = des_equ;
    }

    public String getDes_ubi() {
        return des_ubi;
    }

    public void setDes_ubi(String des_ubi) {
        this.des_ubi = des_ubi;
    }

    public String getFec_fab() {
        return fec_fab;
    }

    public void setFec_fab(String fec_fab) {
        this.fec_fab = fec_fab;
    }

    public String getFec_com() {
        return fec_com;
    }

    public void setFec_com(String fec_com) {
        this.fec_com = fec_com;
    }

    public String getFec_adq() {
        return fec_adq;
    }

    public void setFec_adq(String fec_adq) {
        this.fec_adq = fec_adq;
    }

    public String getFec_pue_ser() {
        return fec_pue_ser;
    }

    public void setFec_pue_ser(String fec_pue_ser) {
        this.fec_pue_ser = fec_pue_ser;
    }

    public String getFec_ult_man() {
        return fec_ult_man;
    }

    public void setFec_ult_man(String fec_ult_man) {
        this.fec_ult_man = fec_ult_man;
    }

    public String getFec_ret() {
        return fec_ret;
    }

    public void setFec_ret(String fec_ret) {
        this.fec_ret = fec_ret;
    }

    public String getCod_bar() {
        return cod_bar;
    }

    public void setCod_bar(String cod_bar) {
        this.cod_bar = cod_bar;
    }

    public String getGar_fec_ini() {
        return gar_fec_ini;
    }

    public void setGar_fec_ini(String gar_fec_ini) {
        this.gar_fec_ini = gar_fec_ini;
    }

    public String getGar_fec_fin() {
        return gar_fec_fin;
    }

    public void setGar_fec_fin(String gar_fec_fin) {
        this.gar_fec_fin = gar_fec_fin;
    }

    public String getGar_obs() {
        return gar_obs;
    }

    public void setGar_obs(String gar_obs) {
        this.gar_obs = gar_obs;
    }

    public String getCod_equ_b() {
        return cod_equ_b;
    }

    public void setCod_equ_b(String cod_equ_b) {
        this.cod_equ_b = cod_equ_b;
    }

    public String getCod_cli_b() {
        return cod_cli_b;
    }

    public void setCod_cli_b(String cod_cli_b) {
        this.cod_cli_b = cod_cli_b;
    }

    public String getCod_pai_b() {
        return cod_pai_b;
    }

    public void setCod_pai_b(String cod_pai_b) {
        this.cod_pai_b = cod_pai_b;
    }

    public String getNum_ser_b() {
        return num_ser_b;
    }

    public void setNum_ser_b(String num_ser_b) {
        this.num_ser_b = num_ser_b;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getS_cod_sys() {
        return s_cod_sys;
    }

    public void setS_cod_sys(String s_cod_sys) {
        this.s_cod_sys = s_cod_sys;
    }

    public String getS_det_obs() {
        return s_det_obs;
    }

    public void setS_det_obs(String s_det_obs) {
        this.s_det_obs = s_det_obs;
    }

    public String getS_ver_ant() {
        return s_ver_ant;
    }

    public void setS_ver_ant(String s_ver_ant) {
        this.s_ver_ant = s_ver_ant;
    }

    public String getS_ver_act() {
        return s_ver_act;
    }

    public void setS_ver_act(String s_ver_act) {
        this.s_ver_act = s_ver_act;
    }

    public String getS_fec_act() {
        return s_fec_act;
    }

    public void setS_fec_act(String s_fec_act) {
        this.s_fec_act = s_fec_act;
    }

    public String getC_cod_con() {
        return c_cod_con;
    }

    public void setC_cod_con(String c_cod_con) {
        this.c_cod_con = c_cod_con;
    }

    public String getC_cod_ref() {
        return c_cod_ref;
    }

    public void setC_cod_ref(String c_cod_ref) {
        this.c_cod_ref = c_cod_ref;
    }

    public String getC_des_inf() {
        return c_des_inf;
    }

    public void setC_des_inf(String c_des_inf) {
        this.c_des_inf = c_des_inf;
    }

    public String getC_fec_con() {
        return c_fec_con;
    }

    public void setC_fec_con(String c_fec_con) {
        this.c_fec_con = c_fec_con;
    }

    public String getC_fec_exp() {
        return c_fec_exp;
    }

    public void setC_fec_exp(String c_fec_exp) {
        this.c_fec_exp = c_fec_exp;
    }

    public String getM_cod_man() {
        return m_cod_man;
    }

    public void setM_cod_man(String m_cod_man) {
        this.m_cod_man = m_cod_man;
    }

    public String getM_cod_tip() {
        return m_cod_tip;
    }

    public void setM_cod_tip(String m_cod_tip) {
        this.m_cod_tip = m_cod_tip;
    }

    public String getM_det_obs() {
        return m_det_obs;
    }

    public void setM_det_obs(String m_det_obs) {
        this.m_det_obs = m_det_obs;
    }

    public String getM_fec_ini() {
        return m_fec_ini;
    }

    public void setM_fec_ini(String m_fec_ini) {
        this.m_fec_ini = m_fec_ini;
    }

    public String getM_fec_fin() {
        return m_fec_fin;
    }

    public void setM_fec_fin(String m_fec_fin) {
        this.m_fec_fin = m_fec_fin;
    }

    public String getM_det_sta() {
        return m_det_sta;
    }

    public void setM_det_sta(String m_det_sta) {
        this.m_det_sta = m_det_sta;
    }

    public String getM_nomtip() {
        return m_nomtip;
    }

    public void setM_nomtip(String m_nomtip) {
        this.m_nomtip = m_nomtip;
    }

    public String getM_estado() {
        return m_estado;
    }

    public void setM_estado(String m_estado) {
        this.m_estado = m_estado;
    }

    public String getNombre_mantenimiento() {
        return nombre_mantenimiento;
    }

    public void setNombre_mantenimiento(String nombre_mantenimiento) {
        this.nombre_mantenimiento = nombre_mantenimiento;
    }

    public Date getDfecfab() {
        return dfecfab;
    }

    public void setDfecfab(Date dfecfab) {
        this.dfecfab = dfecfab;
    }

    public Date getDfeccom() {
        return dfeccom;
    }

    public void setDfeccom(Date dfeccom) {
        this.dfeccom = dfeccom;
    }

    public Date getDfecadq() {
        return dfecadq;
    }

    public void setDfecadq(Date dfecadq) {
        this.dfecadq = dfecadq;
    }

    public Date getDfecpueser() {
        return dfecpueser;
    }

    public void setDfecpueser(Date dfecpueser) {
        this.dfecpueser = dfecpueser;
    }

    public Date getDfecultman() {
        return dfecultman;
    }

    public void setDfecultman(Date dfecultman) {
        this.dfecultman = dfecultman;
    }

    public Date getDfecret() {
        return dfecret;
    }

    public void setDfecret(Date dfecret) {
        this.dfecret = dfecret;
    }

    public Date getDfecini() {
        return dfecini;
    }

    public void setDfecini(Date dfecini) {
        this.dfecini = dfecini;
    }

    public Date getDfecfin() {
        return dfecfin;
    }

    public void setDfecfin(Date dfecfin) {
        this.dfecfin = dfecfin;
    }

    public Date getDfecact() {
        return dfecact;
    }

    public void setDfecact(Date dfecact) {
        this.dfecact = dfecact;
    }

    public Date getDfeccon() {
        return dfeccon;
    }

    public void setDfeccon(Date dfeccon) {
        this.dfeccon = dfeccon;
    }

    public Date getDfecexp() {
        return dfecexp;
    }

    public void setDfecexp(Date dfecexp) {
        this.dfecexp = dfecexp;
    }

    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dfecfab = Date.from(Instant.now());
        dfeccom = dfecfab;
        dfecadq = dfecfab;
        dfecpueser = dfecfab;
        dfecultman = dfecfab;
        dfecini = dfecfab;
        dfecfin = dfecfab;
        dfecret = dfecfab;
        dfecact = dfecfab;
        dfeccon = dfecfab;
        dfecexp = dfecfab;

        tabindex = "0";

        cod_lis_equ = "";
        cod_pai = cbean.getCod_pai();
        cod_equ = "";
        cod_pro = "";
        cod_cli = "";
        num_mod = "";
        num_ser = "";
        des_equ = "";
        des_ubi = "";
        fec_fab = format.format(dfecfab);
        fec_com = format.format(dfeccom);
        fec_adq = format.format(dfecadq);
        fec_pue_ser = format.format(dfecpueser);
        fec_ult_man = format.format(dfecultman);
        fec_ret = format.format(dfecret);
        cod_bar = "";
        gar_fec_ini = format.format(dfecini);
        gar_fec_fin = format.format(dfecfin);
        gar_obs = "";
        s_cod_sys = "";
        s_det_obs = "";
        s_ver_ant = "";
        s_ver_act = "";
        s_fec_act = format.format(dfecact);
        c_cod_con = "";
        c_cod_ref = "";
        c_des_inf = "";
        c_fec_con = format.format(dfeccon);
        c_fec_exp = format.format(dfecexp);
        m_cod_man = "";
        m_cod_tip = "";
        m_det_obs = "";
        m_fec_ini = "";
        m_fec_fin = "";
        m_det_sta = "";
        m_nomtip = "";
        m_estado = "";
        nombre_mantenimiento = "Equipo sin Guardar";
        llenarTipos();
        llenarPaises();
        llenarEquipos();
        llenarUsuarios();
        sistemas = new ArrayList<>();
        contratos = new ArrayList<>();

    }

    public void cerrarventana() {
        tabindex = "0";

        cod_lis_equ = "";
        cod_pai = "";
        cod_equ = "";
        cod_pro = "";
        cod_cli = "";
        num_mod = "";
        num_ser = "";
        des_equ = "";
        des_ubi = "";
        fec_fab = "";
        fec_com = "";
        fec_adq = "";
        fec_pue_ser = "";
        fec_ult_man = "";
        fec_ret = "";
        cod_bar = "";
        gar_fec_ini = "";
        gar_fec_fin = "";
        gar_obs = "";
        s_cod_sys = "";
        s_det_obs = "";
        s_ver_ant = "";
        s_ver_act = "";
        s_fec_act = "";
        c_cod_con = "";
        c_cod_ref = "";
        c_des_inf = "";
        c_fec_con = "";
        c_fec_exp = "";
        m_cod_man = "";
        m_cod_tip = "";
        m_det_obs = "";
        m_fec_ini = "";
        m_fec_fin = "";
        m_det_sta = "";
        m_nomtip = "";
        m_estado = "";
        nombre_mantenimiento = "Equipo sin Guardar";
        catsistemas = null;
        catcontratos = null;
        catgarantias = null;
        catmantenimientos = null;
        mantenimientos = new ArrayList<>();
        sistemas = new ArrayList<>();
        contratos = new ArrayList<>();

    }

    public void nuevo() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        dfecfab = Date.from(Instant.now());
        dfeccom = Date.from(Instant.now());
        dfecadq = Date.from(Instant.now());
        dfecpueser = Date.from(Instant.now());
        dfecultman = Date.from(Instant.now());
        dfecini = Date.from(Instant.now());
        dfecfin = Date.from(Instant.now());
        dfecret = Date.from(Instant.now());
        dfecact = Date.from(Instant.now());
        dfeccon = Date.from(Instant.now());
        dfecexp = Date.from(Instant.now());

        tabindex = "0";

        cod_lis_equ = "";
        cod_pai = cbean.getCod_pai();
        cod_equ = "";
        cod_pro = "";
        cod_cli = "";
        num_mod = "";
        num_ser = "";
        des_equ = "";
        des_ubi = "";
        fec_fab = format.format(dfecfab);
        fec_com = format.format(dfeccom);
        fec_adq = format.format(dfecadq);
        fec_pue_ser = format.format(dfecpueser);
        fec_ult_man = format.format(dfecultman);
        fec_ret = format.format(dfecret);
        cod_bar = "";
        gar_fec_ini = format.format(dfecini);
        gar_fec_fin = format.format(dfecfin);
        gar_obs = "";
        s_cod_sys = "";
        s_det_obs = "";
        s_ver_ant = "";
        s_ver_act = "";
        s_fec_act = format.format(dfecact);
        c_cod_con = "";
        c_cod_ref = "";
        c_des_inf = "";
        c_fec_con = format.format(dfeccon);
        c_fec_exp = format.format(dfecexp);
        m_cod_man = "";
        m_cod_tip = "";
        m_det_obs = "";
        m_fec_ini = "";
        m_fec_fin = "";
        m_det_sta = "";
        m_nomtip = "";
        m_estado = "";
        nombre_mantenimiento = "Equipo sin Guardar";
        llenarTipos();
        llenarPaises();
        llenarEquipos();
        llenarProveedores();
        llenarClientes();
        llenarUsuarios();
        catsistemas = null;
        catcontratos = null;
        catgarantias = null;
        catmantenimientos = null;
        mantenimientos = new ArrayList<>();
        sistemas = new ArrayList<>();
        contratos = new ArrayList<>();
    }

    public void iniciarventanabuscar() {
        cod_equ_b = "0";
        cod_cli_b = "0";
        cod_pai_b = "0";
        num_ser_b = "";
        lequipos = new ArrayList<>();
    }

    public void cerrarventanabuscar() {
        cod_equ_b = "";
        cod_cli_b = "";
        cod_pai_b = "0";
        num_ser_b = "";
        nombre_mantenimiento = "";
    }

    public void llenarTipos() {
        String mQuery = "";
        try {
            tipos = new ArrayList<>();

            mQuery = "select cod_tip, nom_tip,flg_urg from cat_tip order by cod_tip;";
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
            System.out.println("Error en el llenado de Tipos ManListaEquipos" + e.getMessage() + " Query: " + mQuery);
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
            System.out.println("Error en el llenado de Países en Lista Equipos. " + e.getMessage());
        }
    }

    public void llenarEquipos() {
        String mQuery = "";
        try {
            equipos = new ArrayList<>();

            mQuery = "select equ.cod_equ, equ.cod_mar,equ.cod_ref, "
                    + "equ.nom_equ, equ.des_equ,mar.nom_mar,equ.det_ima "
                    + "from cat_equ as equ "
                    + "left join cat_mar as mar on mar.id_mar = equ.cod_mar "
                    + "order by equ.cod_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                equipos.add(new CatEquipos(
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
            System.out.println("Error en el llenado de Equipos en Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarProveedores() {
        String mQuery = "";
        try {
            proveedores = new ArrayList<>();

            mQuery = "select cod_pro,cod_pai,nom_pro,per_con,tel_con,det_mai  "
                    + "from cat_pro order by cod_pro;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                proveedores.add(new CatProveedores(
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
            System.out.println("Error en el llenado de Proveedores en Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarClientes() {
        String mQuery = "";
        try {
            clientes = new ArrayList<>();

            mQuery = "select cod_cli,cod_pai, nom_cli,per_con,tel_con,det_mai  "
                    + "from cat_cli order by cod_cli;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                clientes.add(new CatClientes(
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
            System.out.println("Error en el llenado de Clientes en Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarClientesB() {
        String mQuery = "";
        try {
            clientesb = new ArrayList<>();

            mQuery = "select cod_cli,cod_pai, nom_cli,per_con,tel_con,det_mai  "
                    + "from cat_cli where cod_pai = " + cod_pai_b + " order by cod_cli;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                clientesb.add(new CatClientes(
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
            System.out.println("Error en el llenado de Clientes Busqueda en Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarUsuarios() {
        try {
            usuarios = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, "
                    + "usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, "
                    + "ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep "
                    + "and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai "
                    + "order by cod_usu;";
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
            System.out.println("Error en el llenado de Usuarios en Lista Equipos. " + e.getMessage());
        }
    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(cod_equ) || "0".equals(cod_equ)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Escoger un Equipo.", 2);
        }
        return mValidar;

    }

    public void guardar() {
        String mQuery = "", mvalores = "";
        if (validardatos()) {
            try {
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                fec_fab = format.format(dfecfab);
                fec_com = format.format(dfeccom);
                fec_adq = format.format(dfecadq);
                fec_pue_ser = format.format(dfecpueser);
                fec_ult_man = format.format(dfecultman);
                fec_ret = format.format(dfecret);
                gar_fec_ini = format.format(dfecini);
                gar_fec_fin = format.format(dfecfin);

                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_lis_equ)) {
                    mQuery = "select ifnull(max(cod_lis_equ),0)+1 as codigo from lis_equ;";
                    cod_lis_equ = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into lis_equ ("
                            + "cod_lis_equ,"
                            + "cod_pai,cod_equ,cod_pro,cod_cli,num_mod,num_ser,des_equ,"
                            + "des_ubi,fec_fab,fec_com,fec_adq,fec_pue_ser,fec_ult_man,"
                            + "fec_ret,cod_bar"
                            + ") VALUES ("
                            + cod_lis_equ + ","
                            + cod_pai + ","
                            + cod_equ + ","
                            + cod_pro + ","
                            + cod_cli + ",'"
                            + num_mod + "','"
                            + num_ser + "','"
                            + des_equ + "','"
                            + des_ubi + "',str_to_date('"
                            + fec_fab + "','%d/%m/%Y'),str_to_date('"
                            + fec_com + "','%d/%m/%Y'),str_to_date('"
                            + fec_adq + "','%d/%m/%Y'),str_to_date('"
                            + fec_pue_ser + "','%d/%m/%Y'),str_to_date('"
                            + fec_ult_man + "','%d/%m/%Y'),str_to_date('"
                            + fec_ret + "','%d/%m/%Y'),'"
                            + cod_bar
                            + "')";

                    mAccesos.dmlSQLvariable("insert into tbl_gar (cod_lis_equ,cod_gar,det_obs,fec_ini,fec_exp) values "
                            + "(" + cod_lis_equ + "," + cod_lis_equ + ",'" + gar_obs + "',str_to_date('" + gar_fec_ini + "','%d/%m/%Y'),"
                            + "str_to_date('" + gar_fec_fin + "','%d/%m/%Y'));");

                } else {
                    mQuery = "update lis_equ SET "
                            + "cod_pai=" + cod_pai
                            + ",cod_equ=" + cod_equ
                            + ",cod_pro=" + cod_pro
                            + ",cod_cli=" + cod_cli
                            + ",num_mod='" + num_mod + "' "
                            + ",num_ser='" + num_ser + "' "
                            + ",des_equ='" + des_equ + "' "
                            + ",des_ubi='" + des_ubi + "' "
                            + ",fec_fab=str_to_date('" + fec_fab + "','%d/%m/%Y')"
                            + ",fec_com=str_to_date('" + fec_com + "','%d/%m/%Y')"
                            + ",fec_adq=str_to_date('" + fec_adq + "','%d/%m/%Y')"
                            + ",fec_pue_ser=str_to_date('" + fec_pue_ser + "','%d/%m/%Y')"
                            + ",fec_ult_man=str_to_date('" + fec_ult_man + "','%d/%m/%Y')"
                            + ",fec_ret=str_to_date('" + fec_ret + "','%d/%m/%Y')"
                            + ",cod_bar='" + cod_bar + "' "
                            + " WHERE cod_lis_equ = " + cod_lis_equ + ";";

                    mAccesos.dmlSQLvariable("update tbl_gar set "
                            + "det_obs = '" + gar_obs + "' "
                            + ",fec_ini = str_to_date('" + gar_fec_ini + "','%d/%m/%Y') "
                            + ",fec_exp = str_to_date('" + gar_fec_fin + "','%d/%m/%Y') "
                            + "where cod_lis_equ =" + cod_lis_equ + ";");

                }
                mAccesos.dmlSQLvariable(mQuery);

                // ********************* Sistemas ******************************************
                mQuery = "delete from tbl_sys where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                for (int i = 0; i < sistemas.size(); i++) {
                    mvalores = mvalores + "," + "("
                            + cod_lis_equ + ","
                            + (i + 1) + ",'"
                            + sistemas.get(i).getDet_obs() + "','"
                            + sistemas.get(i).getVer_ant() + "','"
                            + sistemas.get(i).getVer_act() + "', str_to_date('"
                            + sistemas.get(i).getFec_act() + "','%d/%m/%Y')"
                            + ")";
                }
                if (!"".equals(mvalores)) {
                    mvalores = mvalores.substring(1);
                    mQuery = " insert into tbl_sys (cod_lis_equ,cod_sys,det_obs,ver_ant,ver_act,fec_act) values " + mvalores + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                }

                // ********************** Contratos ****************************************
                mQuery = "delete from tbl_con where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);

                mvalores = "";
                for (int i = 0; i < contratos.size(); i++) {
                    mvalores = mvalores + "," + "("
                            + cod_lis_equ + ","
                            + (i + 1) + ",'"
                            + contratos.get(i).getCod_ref() + "','"
                            + contratos.get(i).getDes_inf() + "', str_to_date('"
                            + contratos.get(i).getFec_con() + "','%d/%m/%Y'), str_to_date('"
                            + contratos.get(i).getFec_exp() + "','%d/%m/%Y')"
                            + ")";
                }
                if (!"".equals(mvalores)) {
                    mvalores = mvalores.substring(1);
                    mQuery = " insert into tbl_con (cod_lis_equ,cod_con,cod_ref,des_inf,fec_con,fec_exp) values " + mvalores + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                }

                mAccesos.Desconectar();
                addMessage("Guardar Lista Equipos", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Lista Equipos", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarLequipos();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_lis_equ) == false) {
            try {
                mQuery = "delete from tbl_con where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_sys where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from tbl_gar where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "delete from lis_equ where cod_lis_equ=" + cod_lis_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Equipo", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Equipo", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Equipo. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarLequipos();
            nuevo();
        } else {
            addMessage("Eliminar Equipo", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public void onRowSelectMan(SelectEvent event) {
        m_cod_man = ((CatMantenimientos) event.getObject()).getCod_man();
        m_cod_tip = ((CatMantenimientos) event.getObject()).getCod_tip();
        m_det_obs = ((CatMantenimientos) event.getObject()).getDet_obs();
        m_fec_ini = ((CatMantenimientos) event.getObject()).getFec_ini();
        m_fec_fin = ((CatMantenimientos) event.getObject()).getFec_fin();
        m_det_sta = ((CatMantenimientos) event.getObject()).getDet_sta();
        m_nomtip = ((CatMantenimientos) event.getObject()).getNomtip();
        m_estado = ((CatMantenimientos) event.getObject()).getStatus();
    }

    /**
     * ************************* SISTEMAS *************************************
     */
    public void agregarsistema() {
        if (validarsistema()) {
            int correlativo = 0, insert = 0;
            try {
                for (int i = 0; i < sistemas.size(); i++) {
                    if (Integer.valueOf(sistemas.get(i).getCod_sys()) > correlativo) {
                        correlativo = Integer.valueOf(sistemas.get(i).getCod_sys());
                    }
                }

                if (insert == 0) {
                    sistemas.add(new CatSistemas(
                            cod_lis_equ,
                            String.valueOf(correlativo),
                            s_det_obs,
                            s_ver_ant,
                            s_ver_act,
                            s_fec_act
                    ));

                    s_det_obs = "";
                    s_ver_ant = "";
                    s_ver_act = "";
                    s_fec_act = "";

                }

            } catch (Exception e) {
                System.out.println("Error en Agregar Sistemas ManListaEquipos." + e.getMessage());
            }
        }

    }

    public boolean validarsistema() {
        boolean mvalidar = true;
        if ("".equals(s_det_obs)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre de Sistema.", 2);
        }
        if ("".equals(s_ver_act)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar La Versión Actual del Software.", 2);
        }
        if ("".equals(s_fec_act)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar La Fecha de Actualización.", 2);
        }
        if (s_ver_ant.equals(s_ver_act)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar La Versión Actual del Software Distinta a la Anterior.", 2);
        }

        return mvalidar;

    }

    public void eliminarsistema() {
        s_cod_sys = catsistemas.getCod_sys();
        catsistemas = null;
        if ("".equals(s_cod_sys)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Sistema para Remover.", 2);
        } else {
            for (int i = 0; i < sistemas.size(); i++) {
                if (s_cod_sys.equals(sistemas.get(i).getCod_sys())) {
                    sistemas.remove(i);
                }
            }
            s_cod_sys = "";
            s_det_obs = "";
            s_ver_ant = "";
            s_ver_act = "";
            s_fec_act = "";
            //catlistaequipos = new CatListaEquipos();
        }
    }

    /**
     * ************** CONTRATOS ***********************
     */
    public void agregarcontrato() {
        if (validarcontrato()) {
            int correlativo = 0, insert = 0;
            try {
                for (int i = 0; i < contratos.size(); i++) {
                    if (Integer.valueOf(contratos.get(i).getCod_con()) > correlativo) {
                        correlativo = Integer.valueOf(contratos.get(i).getCod_con());
                    }
                }

                if (insert == 0) {
                    contratos.add(new CatContratos(
                            cod_lis_equ,
                            String.valueOf(correlativo),
                            c_cod_ref,
                            c_des_inf,
                            c_fec_con,
                            c_fec_exp
                    ));

                    c_cod_con = "";
                    c_cod_ref = "";
                    c_des_inf = "";
                    c_fec_con = "";
                    c_fec_exp = "";

                }

            } catch (Exception e) {
                System.out.println("Error en Agregar Contratos ManListaEquipos." + e.getMessage());
            }
        }

    }

    public boolean validarcontrato() {
        boolean mvalidar = true;
        if ("".equals(c_cod_ref)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Número de Contrato.", 2);
        }
        if ("".equals(c_fec_con)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar La Fecha de Inicio del Contrato.", 2);
        }
        if ("".equals(c_fec_exp)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar La Fecha de Expiración del Contrato.", 2);
        }

        return mvalidar;

    }

    public void eliminarcontrato() {
        c_cod_con = catcontratos.getCod_con();
        catcontratos = null;
        if ("".equals(c_cod_con)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un Contrato para Remover.", 2);
        } else {
            for (int i = 0; i < contratos.size(); i++) {
                if (c_cod_con.equals(contratos.get(i).getCod_con())) {
                    contratos.remove(i);
                }
            }
            c_cod_con = "";
            c_cod_ref = "";
            c_des_inf = "";
            c_fec_con = "";
            c_fec_exp = "";
        }
    }

    // ******************************** BUSCAR *******************************************
    public void llenarSistemas() {
        String mQuery = "";
        try {
            catsistemas = new CatSistemas();
            sistemas = new ArrayList<>();

            mQuery = "select cod_lis_equ,cod_sys,det_obs,ver_ant,ver_act,date_format(fec_act,'%d/%m/%Y') "
                    + "from tbl_sys "
                    + "where cod_lis_equ=" + cod_lis_equ + " "
                    + "order by cod_lis_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                sistemas.add(new CatSistemas(
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
            System.out.println("Error en el llenado de Sistemas en Lista Equipos. "
                    + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarContratos() {
        String mQuery = "";
        try {
            catcontratos = new CatContratos();
            contratos = new ArrayList<>();

            mQuery = "select cod_lis_equ, cod_con, cod_ref, des_inf, date_format(fec_con,'%d/%m/%Y'), date_format(fec_exp,'%d/%m/%Y') "
                    + "from tbl_con "
                    + "where cod_lis_equ=" + cod_lis_equ + " "
                    + "order by cod_lis_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                contratos.add(new CatContratos(
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
            System.out.println("Error en el llenado de Contratos en Lista Equipos. "
                    + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarGarantias() {
        String mQuery = "";
        try {
            gar_obs = "";
            gar_fec_ini = "";
            gar_fec_fin = "";

            mQuery = "select cod_lis_equ, cod_gar, det_obs, "
                    + "date_format(fec_ini,'%d/%m/%Y'), "
                    + "date_format(fec_exp,'%d/%m/%Y') "
                    + "from tbl_gar "
                    + "where cod_lis_equ=" + cod_lis_equ + " "
                    + "order by cod_lis_equ;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                gar_obs = resVariable.getString(3);
                gar_fec_ini = resVariable.getString(4);
                gar_fec_fin = resVariable.getString(5);
            }
            mAccesos.Desconectar();

            if ("00/00/0000".equals(gar_fec_ini)) {
                gar_fec_ini = "";
            }
            if ("00/00/0000".equals(gar_fec_fin)) {
                gar_fec_fin = "";
            }
        } catch (Exception e) {
            System.out.println("Error en el llenado de Garantías en Lista Equipos. "
                    + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarMantenimientos() {
        String mQuery = "";
        try {
            catmantenimientos = new CatMantenimientos();
            mantenimientos = new ArrayList<>();

            mQuery = "select mm.cod_lis_equ, mm.cod_man, mm.cod_tip, mm.det_obs, "
                    + "date_format(mm.fec_ini,'%d/%m/%Y %H:%i'), "
                    + "date_format(mm.fec_fin,'%d/%m/%Y %H:%i'), "
                    + "mm.det_sta, mm.cod_usu,tip.nom_tip,"
                    + "case mm.det_sta "
                    + "when 1 then 'PENDIENTE' "
                    + "when 2 then 'CANCELADO' "
                    + "when 3 then 'EN PROCESO' "
                    + "when 4 then 'FINALIZADO' "
                    + "end as status,"
                    + "'' as dr,"
                    + "'' as color,"
                    + "mm.cod_per, per.nom_per,mm.flg_ext "
                    + "from tbl_mae_man as mm "
                    + "left join cat_tip as tip on mm.cod_tip = tip.cod_tip "
                    + "left join lis_equ as lis on mm.cod_lis_equ = lis.cod_lis_equ "
                    + "left join cat_per as per on mm.cod_per = per.cod_per "
                    + "where "
                    + "mm.det_sta IN (2,4) "
                    + "and mm.cod_lis_equ=" + cod_lis_equ + " "
                    + "order by mm.cod_man;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
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
                        resVariable.getString(10), "", "", "", "", "", "", "", "", "", "", "", "",""                        
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Mantenimientos en Lista Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarLequipos() {
        try {
            catlistaequipos = new CatListaEquipos();
            lequipos = new ArrayList<>();
            String mAnexo = "";
            if (!"0".equals(cod_cli_b)) {
                mAnexo = "and lequ.cod_cli = " + cod_cli_b;
            }
            if (!"0".equals(cod_equ_b)) {
                mAnexo = mAnexo + " and lequ.cod_equ =" + cod_equ_b;
            }
            if (!"0".equals(cod_pai_b)) {
                mAnexo = mAnexo + " and lequ.cod_pai =" + cod_pai_b;
            }
            if (!"".equals(num_ser_b)) {
                mAnexo = mAnexo + " and lequ.num_ser ='" + num_ser_b+ "'";
            }

            String mQuery = "select lequ.cod_lis_equ, lequ.cod_pai, lequ.cod_equ, lequ.cod_pro, "
                    + "lequ.cod_cli, lequ.num_mod, lequ.num_ser, lequ.des_equ, lequ.des_ubi, "
                    + "DATE_FORMAT(lequ.fec_fab,'%d/%m/%Y') as ffab, "
                    + "DATE_FORMAT(lequ.fec_com,'%d/%m/%Y') as fcom, "
                    + "DATE_FORMAT(lequ.fec_adq,'%d/%m/%Y') as fadq,"
                    + "DATE_FORMAT(lequ.fec_pue_ser,'%d/%m/%Y') as fps,"
                    + "DATE_FORMAT(lequ.fec_ult_man,'%d/%m/%Y') as fum, "
                    + "DATE_FORMAT(lequ.fec_ret,'%d/%m/%Y') as fret,"
                    + "lequ.cod_bar,"
                    + "cpai.nom_pai as nompai,"
                    + "cequ.nom_equ as nomequ, "
                    + "cpro.nom_pro as nompro, "
                    + "ccli.nom_cli as nomcli "
                    + "from lis_equ as lequ "
                    + "left join cat_pai as cpai on lequ.cod_pai = cpai.cod_pai "
                    + "left join cat_equ as cequ on lequ.cod_equ = cequ.cod_equ "
                    + "left join cat_pro as cpro on lequ.cod_pro = cpro.cod_pro and lequ.cod_pai = cpro.cod_pai "
                    + "left join cat_cli as ccli on lequ.cod_cli = ccli.cod_cli and lequ.cod_pai = ccli.cod_pai "
                    + "where "
                    + "lequ.cod_lis_equ <> 0 "
                    + mAnexo
                    + " order by lequ.cod_pai, lequ.cod_equ, lequ.num_ser ,lequ.cod_lis_equ;";
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
                        resVariable.getString(19),
                        resVariable.getString(20)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Lista Equipos ManListaEquipos. " + e.getMessage());
        }
    }

    public void onRowSelect(SelectEvent event) {
        cod_lis_equ = ((CatListaEquipos) event.getObject()).getCod_lis_equ();
        cod_pai = ((CatListaEquipos) event.getObject()).getCod_pai();
        llenarProveedores();
        llenarClientes();
        cod_equ = ((CatListaEquipos) event.getObject()).getCod_equ();
        cod_pro = ((CatListaEquipos) event.getObject()).getCod_pro();
        cod_cli = ((CatListaEquipos) event.getObject()).getCod_cli();
        num_mod = ((CatListaEquipos) event.getObject()).getNum_mod();
        num_ser = ((CatListaEquipos) event.getObject()).getNum_ser();
        des_equ = ((CatListaEquipos) event.getObject()).getDes_equ();
        des_ubi = ((CatListaEquipos) event.getObject()).getDes_ubi();
        fec_fab = ((CatListaEquipos) event.getObject()).getFec_fab();
        fec_com = ((CatListaEquipos) event.getObject()).getFec_com();
        fec_adq = ((CatListaEquipos) event.getObject()).getFec_adq();
        fec_pue_ser = ((CatListaEquipos) event.getObject()).getFec_pue_ser();
        fec_ult_man = ((CatListaEquipos) event.getObject()).getFec_ult_man();
        fec_ret = ((CatListaEquipos) event.getObject()).getFec_ret();
        cod_bar = ((CatListaEquipos) event.getObject()).getCod_bar();
        if ("00/00/0000".equals(fec_fab)) {
            fec_fab = "";
        }
        if ("00/00/0000".equals(fec_com)) {
            fec_com = "";
        }
        if ("00/00/0000".equals(fec_adq)) {
            fec_adq = "";
        }
        if ("00/00/0000".equals(fec_pue_ser)) {
            fec_pue_ser = "";
        }
        if ("00/00/0000".equals(fec_ult_man)) {
            fec_ult_man = "";
        }
        if ("00/00/0000".equals(fec_ret)) {
            fec_ret = "";
        }
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            dfecfab = format.parse(fec_fab);
            dfeccom = format.parse(fec_com);
            dfecadq = format.parse(fec_adq);
            dfecpueser = format.parse(fec_pue_ser);
            dfecultman = format.parse(fec_ult_man);
        } catch (Exception ex) {

        }
        nombre_mantenimiento = ((CatListaEquipos) event.getObject()).getNomequ() + " " + num_ser;
        llenarSistemas();
        llenarContratos();
        llenarGarantias();
        llenarMantenimientos();
        lequipos = null;
        RequestContext.getCurrentInstance().update("frmListaEquipos");
        RequestContext.getCurrentInstance().execute("PF('wSearchLE').hide()");
    }

    public void onPaisBusChange() {
        llenarClientesB();
    }

    //************************* GENERALES ***********************************
    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tabGeneral":
                tabindex = "0";
                break;
            case "tabHistoria":
                tabindex = "1";
                break;
            case "tabSoftware":
                tabindex = "2";
                break;
            case "tabContratos":
                tabindex = "3";
                break;
        }
        //System.out.println(tabindex);
        //RequestContext.getCurrentInstance().update(":frmListaEquipos:tvLE");
    }

    public void onPaisChange() {
        llenarProveedores();
        llenarClientes();

    }

    public void dateSelectedFecfab(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_fab = format.format(date);
    }

    public void dateSelectedFeccom(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_com = format.format(date);
    }

    public void dateSelectedFecadq(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_adq = format.format(date);
    }

    public void dateSelectedFecpueser(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_pue_ser = format.format(date);
    }

    public void dateSelectedFecultman(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_ult_man = format.format(date);
    }

    public void dateSelectedFecgarini(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        gar_fec_ini = format.format(date);
    }

    public void dateSelectedFecgarfin(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        gar_fec_fin = format.format(date);
    }

    public void dateSelectedFecret(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_ret = format.format(date);
    }

    public void dateSelectedFecact(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        s_fec_act = format.format(date);
    }

    public void dateSelectedFeccon(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        c_fec_con = format.format(date);
    }

    public void dateSelectedFecexp(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        c_fec_exp = format.format(date);
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
