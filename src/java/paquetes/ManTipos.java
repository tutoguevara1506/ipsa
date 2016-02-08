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
public class ManTipos implements Serializable {

    private static final long serialVersionUID = 8799478674646638L;
    @Inject
    Login cbean;
    private CatTipos cattipos;
    private List<CatTipos> tipos;
    private String cod_tip, nom_tip, flg_urg;

    public ManTipos() {
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

    public String getCod_tip() {
        return cod_tip;
    }

    public void setCod_tip(String cod_tip) {
        this.cod_tip = cod_tip;
    }

    public String getNom_tip() {
        return nom_tip;
    }

    public void setNom_tip(String nom_tip) {
        this.nom_tip = nom_tip;
    }

    public String getFlg_urg() {
        return flg_urg;
    }

    public void setFlg_urg(String flg_urg) {
        this.flg_urg = flg_urg;
    }

    public void iniciarventana() {
        cod_tip = "";
        nom_tip = "";
        flg_urg = "false";
        llenarTipos();
    }

    public void cerrarventana() {
        cod_tip = "";
        nom_tip = "";
        flg_urg = "false";
        tipos = new ArrayList<>();
    }

    public void llenarTipos() {
        String mQuery = "";
        try {
            cattipos = new CatTipos();
            tipos = new ArrayList<>();

            mQuery = "select cod_tip, nom_tip,"
                    + "case flg_urg "
                    + "when 0 then 'false' "
                    + "when 1 then 'true' end as urg from cat_tip order by cod_tip;";
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
            System.out.println("Error en el llenado de Catálogo Tipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_tip = "";
        nom_tip = "";
        flg_urg = "false";
        cattipos = new CatTipos();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            if ("false".equals(flg_urg)) {
                flg_urg = "0";
            } else {
                flg_urg = "1";
            }

            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_tip)) {
                    mQuery = "select ifnull(max(cod_tip),0)+1 as codigo from cat_tip;";
                    cod_tip = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_tip (cod_tip,nom_tip,flg_urg) "
                            + "values (" + cod_tip + ",'" + nom_tip + "'," + flg_urg + ");";
                } else {
                    mQuery = "update cat_tip SET "
                            + " nom_tip= '" + nom_tip + "', "
                            + " flg_urg= " + flg_urg + " "
                            + "WHERE cod_tip= " + cod_tip + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Tipos", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Tipos", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Tipos. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarTipos();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_tip) == false) {
            try {
                mQuery = "delete from cat_tip where cod_tip=" + cod_tip + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Tipos", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Tipos", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Tipos. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarTipos();
            nuevo();
        } else {
            addMessage("Eliminar Tipos", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_tip) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Tipo de Mantenimiento.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_tip) from cat_tip "
                + "where upper(nom_tip)='" + nom_tip.toUpperCase() + "';")) == false && "".equals(cod_tip)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre del Tipo de Mantenimiento ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_tip = ((CatTipos) event.getObject()).getCod_tip();
        nom_tip = ((CatTipos) event.getObject()).getNom_tip();
        flg_urg = ((CatTipos) event.getObject()).getFlg_urg();
        
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
