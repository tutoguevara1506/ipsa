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
    private CatModulos catmodulos;
    private List<CatModulos> modulos;
    private CatControles catcontroles;
    private List<CatControles> controles;
    private CatGrupos catgrupos;
    private List<CatGrupos> grupos;
    private CatUsuarios catusuarios;
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

    public CatModulos getCatmodulos() {
        return catmodulos;
    }

    public void setCatmodulos(CatModulos catmodulos) {
        this.catmodulos = catmodulos;
    }

    public List<CatModulos> getModulos() {
        return modulos;
    }

    public void setModulos(List<CatModulos> modulos) {
        this.modulos = modulos;
    }

    public CatControles getCatcontroles() {
        return catcontroles;
    }

    public void setCatcontroles(CatControles catcontroles) {
        this.catcontroles = catcontroles;
    }

    public List<CatControles> getControles() {
        return controles;
    }

    public void setControles(List<CatControles> controles) {
        this.controles = controles;
    }

    public CatGrupos getCatgrupos() {
        return catgrupos;
    }

    public void setCatgrupos(CatGrupos catgrupos) {
        this.catgrupos = catgrupos;
    }

    public List<CatGrupos> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<CatGrupos> grupos) {
        this.grupos = grupos;
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
        id_mod = "";
        cod_ctrl = "";
        id_grp = "";
        cod_usu = "";
        activo = "";
        llenarModulos();
        llenarControles();
        llenarGrupos();
        llenarUsuarios();
        llenarSeguridad();
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

            mQuery = " SELECT cod_sec, ipsa.cat_mod.des_mod, ipsa.sec_ctrl.cod_ctrl, ipsa.cat_ctrl.ctrl_desc," +
                     " ipsa.sec_ctrl.id_grp, ipsa.cat_grp.des_grp, ipsa.sec_ctrl.cod_usu, ipsa.cat_usu.nom_usu, activo"+
                     " FROM ipsa.sec_ctrl INNER JOIN" +
                     " ipsa.cat_ctrl ON ipsa.cat_ctrl.cod_ctrl = ipsa.sec_ctrl.cod_ctrl LEFT OUTER JOIN"+
                     " ipsa.cat_grp ON ipsa.cat_grp.id_grp = ipsa.sec_ctrl.id_grp LEFT OUTER JOIN" +
                     " ipsa.cat_mod ON ipsa.cat_mod.id_mod = ipsa.cat_ctrl.id_mod LEFT OUTER JOIN" +
                     " ipsa.cat_usu ON ipsa.cat_usu.cod_usu = ipsa.sec_ctrl.cod_usu;";
                          
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
            System.out.println("Error en el Registro de la Seguridad. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarModulos() {
        String mQuery = "";
        try {
            catmodulos = new CatModulos();
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
            catcontroles = new CatControles();
            controles = new ArrayList<>();

            //mQuery = "select cod_ctrl, sid_ctrl, ctrl_desc, id_mod from cat_ctrl order by cod_ctrl;";
            
            mQuery = " SELECT cod_ctrl, sid_ctrl, ctrl_desc, ipsa.cat_ctrl.id_mod, ipsa.cat_mod.des_mod " +
                     " FROM ipsa.cat_ctrl inner join ipsa.cat_mod ON "+
                     " ipsa.cat_mod.id_mod =ipsa.cat_ctrl.id_mod;";
            
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
            catgrupos = new CatGrupos();
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
            System.out.println("Error en el llenado de Cat�logo de Usuarios. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_sec = "";
        id_mod = "";
        cod_ctrl = "";
        id_grp = "";
        cod_usu = "";
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
                            + "values (" + cod_sec + ",'" + cod_ctrl + "','" + id_grp + "','" + cod_usu + "','" +  activo + "');";
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
        if ("".equals(cod_ctrl) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el control.", 2);
        }
        
        if ("".equals(activo) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe indicar Activo o Inactivo.", 2);
        }
        
        if (("".equals(id_grp) == true) && ("".equals(cod_usu) == true)) {
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
    
    public boolean disableOpt(String idControl){
        grupo = "";
        codCtrl = "";
        usuario = cbean.getCod_usu();
        
        if (!"1".equals(usuario)){
           
            Accesos maccesos = new Accesos();
            maccesos.Conectar();
                grupo = maccesos.strQuerySQLvariable("select id_grp from cat_usr_grp where cod_usu = '"+ usuario +"' limit 1;");
                codCtrl = maccesos.strQuerySQLvariable("select cod_ctrl from cat_ctrl where sid_ctrl = '"+ idControl +"' limit 1;");
                
                ctrlActivo = maccesos.strQuerySQLvariable("select activo from sec_ctrl where cod_ctrl = '" + codCtrl + "' AND ( id_grp = '"+ grupo +"' OR cod_usu = '" + usuario + "') limit 1;");
                
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
        activo =  ((CatSeguridad) event.getObject()).getActivo();
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
