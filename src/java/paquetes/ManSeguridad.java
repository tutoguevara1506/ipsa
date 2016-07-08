package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped

public class ManSeguridad implements Serializable {

    private static final long serialVersionUID = 8797678674761L;
    @Inject
    Login cbean;
    private CatSeguridad catseguridad;
    private List<CatSeguridad> seguridad;

    private List<CatModulos> modulos;

    private List<CatControles> controles;

    private List<CatGrupos> grupos;

    private List<CatUsuarios> usuarios;
    private String cod_sec, cod_ctrl, id_grp, cod_usu, activo, id_mod, grupo, usuario, codCtrl, ctrlActivo;
    private Boolean disable = false;

    public ManSeguridad() {

    }

    public CatSeguridad getCatseguridad() {
        return catseguridad;
    }

    public void setCatseguridad(CatSeguridad catseguridad) {
        this.catseguridad = catseguridad;
    }

    public List<CatSeguridad> getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(List<CatSeguridad> seguridad) {
        this.seguridad = seguridad;
    }

    public List<CatModulos> getModulos() {
        return modulos;
    }

    public void setModulos(List<CatModulos> modulos) {
        this.modulos = modulos;
    }

    public List<CatControles> getControles() {
        return controles;
    }

    public void setControles(List<CatControles> controles) {
        this.controles = controles;
    }

    public List<CatGrupos> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<CatGrupos> grupos) {
        this.grupos = grupos;
    }

    public List<CatUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CatUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public String getCod_sec() {
        return cod_sec;
    }

    public void setCod_sec(String cod_sec) {
        this.cod_sec = cod_sec;
    }

    public String getCod_ctrl() {
        return cod_ctrl;
    }

    public void setCod_ctrl(String cod_ctrl) {
        this.cod_ctrl = cod_ctrl;
    }

    public String getId_grp() {
        return id_grp;
    }

    public String getId_mod() {
        return id_mod;
    }

    public void setId_mod(String id_mod) {
        this.id_mod = id_mod;
    }

    public String getCodCtrl() {
        return codCtrl;
    }

    public void setCodCtrl(String codCtrl) {
        this.codCtrl = codCtrl;
    }

    public void setId_grp(String id_grp) {
        this.id_grp = id_grp;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getCtrlActivo() {
        return ctrlActivo;
    }

    public void setCtrlActivo(String ctrlActivo) {
        this.ctrlActivo = ctrlActivo;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public void iniciarventana() {
        cod_sec = "";
        id_mod = "0";
        cod_ctrl = "0";
        id_grp = "0";
        cod_usu = "0";
        activo = "";
        llenarModulos();
        //llenarControles();
        llenarGrupos();
        llenarUsuarios();
        //llenarSeguridad();
    }

    public void cerrarventana() {
        cod_sec = "";
        id_mod = "";
        cod_ctrl = "";
        id_grp = "";
        cod_usu = "";
        activo = "";
    }

    public void llenarSeguridad() {
        String mQuery = "";
        try {
            catseguridad = new CatSeguridad();
            seguridad = new ArrayList<>();

            if ("0".equals(id_grp)) {
                mQuery = " SELECT cod_sec, cat_mod.id_mod, sec_ctrl.cod_ctrl, cat_ctrl.ctrl_desc,"
                        + " sec_ctrl.id_grp, cat_grp.des_grp, sec_ctrl.cod_usu, cat_usu.nom_usu, activo"
                        + " FROM sec_ctrl "
                        + "INNER JOIN cat_ctrl ON cat_ctrl.cod_ctrl = sec_ctrl.cod_ctrl "
                        + "LEFT OUTER JOIN cat_grp ON cat_grp.id_grp = sec_ctrl.id_grp "
                        + "LEFT OUTER JOIN cat_mod ON cat_mod.id_mod = cat_ctrl.id_mod "
                        + "LEFT OUTER JOIN cat_usu ON cat_usu.cod_usu = sec_ctrl.cod_usu "
                        + "where cat_mod.id_mod =" + id_mod + " "
                        + "order by sec_ctrl.id_grp,cod_sec;";
            } else {
                mQuery = " SELECT cod_sec, cat_mod.id_mod, sec_ctrl.cod_ctrl, cat_ctrl.ctrl_desc,"
                        + " sec_ctrl.id_grp, cat_grp.des_grp, sec_ctrl.cod_usu, cat_usu.nom_usu, activo"
                        + " FROM sec_ctrl "
                        + "INNER JOIN cat_ctrl ON cat_ctrl.cod_ctrl = sec_ctrl.cod_ctrl "
                        + "LEFT OUTER JOIN cat_grp ON cat_grp.id_grp = sec_ctrl.id_grp "
                        + "LEFT OUTER JOIN cat_mod ON cat_mod.id_mod = cat_ctrl.id_mod "
                        + "LEFT OUTER JOIN cat_usu ON cat_usu.cod_usu = sec_ctrl.cod_usu "
                        + "where cat_mod.id_mod =" + id_mod + " "
                        + "and sec_ctrl.id_grp=" + id_grp + " "
                        + "order by sec_ctrl.id_grp,cod_sec;";
            }

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                seguridad.add(new CatSeguridad(
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
            System.out.println("Error en el Llenado de la Seguridad. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarModulos() {
        String mQuery = "";
        try {

            modulos = new ArrayList<>();

            mQuery = "select id_mod, des_mod from cat_mod order by id_mod;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                modulos.add(new CatModulos(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo Modulos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarControles() {
        String mQuery = "";
        try {

            controles = new ArrayList<>();

            //mQuery = "select cod_ctrl, sid_ctrl, ctrl_desc, id_mod from cat_ctrl order by cod_ctrl;";
            mQuery = "SELECT mcc.cod_ctrl, mcc.sid_ctrl, ctrl_desc, mcc.id_mod, cmod.des_mod "
                    + "FROM cat_ctrl as mcc "
                    + "inner join cat_mod as cmod ON cmod.id_mod =mcc.id_mod "
                    + "where "
                    + "mcc.id_mod=" + id_mod + " "
                    + "and mcc.cod_ctrl not in (select secctrl.cod_ctrl "
                    + "FROM sec_ctrl as secctrl where secctrl.id_grp=" + id_grp + ");";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                controles.add(new CatControles(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo de controles. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarGrupos() {
        String mQuery = "";
        try {

            grupos = new ArrayList<>();

            mQuery = "select id_grp, des_grp from cat_grp order by des_grp;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                grupos.add(new CatGrupos(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Grupos de Seguridad. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarUsuarios() {
        try {

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
            System.out.println("Error en el llenado de Cat�logo de Usuarios. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_sec = "";
        id_mod = "0";
        cod_ctrl = "0";
        id_grp = "0";
        cod_usu = "0";
        activo = "";
        catseguridad = new CatSeguridad();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_sec)) {
                    mQuery = "select ifnull(max(cod_sec),0)+1 as codigo from sec_ctrl;";
                    cod_sec = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into sec_ctrl (cod_sec, cod_ctrl, id_grp, cod_usu, activo) "
                            + "values (" + cod_sec + ",'" + cod_ctrl + "','" + id_grp + "','" + cod_usu + "','" + activo + "');";
                } else {
                    mQuery = "update sec_ctrl SET "
                            + " cod_ctrl = '" + cod_ctrl + "', "
                            + " id_grp = '" + id_grp + "', "
                            + " cod_usu = '" + cod_usu + "', "
                            + " activo = '" + activo + "' "
                            + "WHERE cod_sec = " + cod_sec + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Control", "Informaci�n Almacenada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Control", "Error al momento de guardar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Seguridad. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarSeguridad();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_sec) == false) {
            try {
                mQuery = "delete from sec_ctrl where cod_sec=" + cod_sec + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Registro", "Informaci�n Eliminada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Registro", "Error al momento de Eliminar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Registro. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarSeguridad();
            nuevo();
        } else {
            addMessage("Eliminar Control", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(cod_ctrl) || "0".equals(cod_ctrl)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el control.", 2);
        }

        if ("".equals(activo)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe indicar Activo o Inactivo.", 2);
        }

        if ("0".equals(id_grp)  && "0".equals(cod_usu) ) {
            mValidar = false;
            addMessage("Validar Datos", "Debe indicar un Grupo o Usuario.", 2);
        }

        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_ctrl) from sec_ctrl "
                + "where cod_ctrl ='" + cod_ctrl + "' AND id_grp ='" + id_grp + "' AND cod_usu = '" + cod_usu + "';")) == false && "".equals(cod_sec)) {
            mValidar = false;
            addMessage("Validar Datos", "El Registro ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public boolean disableOpt(String idControl) {
        grupo = "";
        codCtrl = "";
        usuario = cbean.getCod_usu();

        if (!"1".equals(usuario)) {

            Accesos maccesos = new Accesos();
            maccesos.Conectar();
            grupo = maccesos.strQuerySQLvariable("select id_grp from cat_usr_grp where cod_usu = '" + usuario + "' limit 1;");
            codCtrl = maccesos.strQuerySQLvariable("select cod_ctrl from cat_ctrl where sid_ctrl = '" + idControl + "' limit 1;");

            ctrlActivo = maccesos.strQuerySQLvariable("select activo from sec_ctrl where cod_ctrl = '" + codCtrl + "' AND ( id_grp = '" + grupo + "' OR cod_usu = '" + usuario + "') limit 1;");

            maccesos.Desconectar();

            disable = "".equals(ctrlActivo);
        }

        return disable;
    }

    public void onRowSelect(SelectEvent event) {
        cod_sec = ((CatSeguridad) event.getObject()).getCod_sec();
        id_mod = ((CatSeguridad) event.getObject()).getModulo();
        cod_ctrl = ((CatSeguridad) event.getObject()).getCod_ctrl();
        id_grp = ((CatSeguridad) event.getObject()).getId_grp();
        cod_usu = ((CatSeguridad) event.getObject()).getCod_usu();
        activo = ((CatSeguridad) event.getObject()).getActivo();
    }
    
    public void onSelectGrupo() {
        llenarControles();
        llenarSeguridad();
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
