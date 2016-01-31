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
public class ManControles implements Serializable {

    private static final long serialVersionUID = 87976786747638L;
    @Inject
    Login cbean;
    private CatControles catcontroles;
    private List<CatControles> controles;
    private String cod_ctrl, sid_ctrl, ctrl_desc, id_mod;
    private CatModulos catmodulos;
    private List<CatModulos> modulos;

    public ManControles() {
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

    public String getCod_ctrl() {
        return cod_ctrl;
    }

    public void setCod_ctrl(String cod_ctrl) {
        this.cod_ctrl = cod_ctrl;
    }

    public String getCtrl_desc() {
        return ctrl_desc;
    }

    public void setCtrl_desc(String ctrl_desc) {
        this.ctrl_desc = ctrl_desc;
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

    public String getId_mod() {
        return id_mod;
    }

    public void setId_mod(String id_mod) {
        this.id_mod = id_mod;
    }

    public String getSid_ctrl() {
        return sid_ctrl;
    }

    public void setSid_ctrl(String sid_ctrl) {
        this.sid_ctrl = sid_ctrl;
    }
    
    public void iniciarventana() {
        cod_ctrl = "";
        sid_ctrl = "";
        ctrl_desc = "";
        id_mod = "";
        llenarControles();
        llenarModulos();
    }

    public void cerrarventana() {
        id_mod = "";
        cod_ctrl = "";
        sid_ctrl = "";
        ctrl_desc = "";
        controles = new ArrayList<>();
        modulos = new ArrayList<>();
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

    
    
    public void nuevo() {
        id_mod ="";
        cod_ctrl = "";
        sid_ctrl = "";
        ctrl_desc = "";
        catcontroles = new CatControles();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_ctrl)) {
                    mQuery = "select ifnull(max(cod_ctrl),0)+1 as codigo from cat_ctrl;";
                    cod_ctrl = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_ctrl (cod_ctrl,sid_ctrl, ctrl_desc, id_mod) "
                            + "values (" + cod_ctrl + ",'" + sid_ctrl + "','" + ctrl_desc + "','"+ id_mod + "');";
                } else {
                    mQuery = "update cat_ctrl SET "
                            + " sid_ctrl = '" + sid_ctrl + "', "
                            + " ctrl_desc = '" + ctrl_desc + "', "
                            + " id_mod = '" + id_mod + "' "
                            + " WHERE cod_ctrl = " + cod_ctrl + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Control", "Informaci�n Almacenada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Control", "Error al momento de guardar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Control. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarControles();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_ctrl) == false) {
            try {
                mQuery = "delete from cat_ctrl where cod_ctrl=" + cod_ctrl + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Control", "Informaci�n Eliminada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Control", "Error al momento de Eliminar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Control. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarControles();
            nuevo();
        } else {
            addMessage("Eliminar Control", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("0".equals(id_mod) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Modulo Valido.", 2);
        }
        
        if ("".equals(ctrl_desc) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el control.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_ctrl) from cat_ctrl "
                + "where upper(ctrl_desc)='" + ctrl_desc.toUpperCase() + "';")) == false && "".equals(cod_ctrl)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre del Control ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_mod = ((CatControles) event.getObject()).getId_mod();
        cod_ctrl = ((CatControles) event.getObject()).getCod_ctrl();
        sid_ctrl = ((CatControles) event.getObject()).getSid_ctrl();
        ctrl_desc = ((CatControles) event.getObject()).getCtrl_desc();
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
