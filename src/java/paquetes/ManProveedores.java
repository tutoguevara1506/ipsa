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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManProveedores implements Serializable {

    private static final long serialVersionUID = 8719719679471638L;
    @Inject
    Login cbean;
    private CatProveedores catproveedores;
    private List<CatProveedores> proveedores;
    private String cod_pro, cod_pai, nom_pro, per_con, tel_con, det_mai;

    public ManProveedores() {
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

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getNom_pro() {
        return nom_pro;
    }

    public void setNom_pro(String nom_pro) {
        this.nom_pro = nom_pro;
    }

    public String getPer_con() {
        return per_con;
    }

    public void setPer_con(String per_con) {
        this.per_con = per_con;
    }

    public String getTel_con() {
        return tel_con;
    }

    public void setTel_con(String tel_con) {
        this.tel_con = tel_con;
    }

    public String getDet_mai() {
        return det_mai;
    }

    public void setDet_mai(String det_mai) {
        this.det_mai = det_mai;
    }

    public void iniciarventana() {
        cod_pro = "";
        cod_pai = "0";
        nom_pro = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        llenarProveedores();
    }

    public void cerrarventana() {
        cod_pro = "";
        cod_pai = "";
        nom_pro = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        proveedores = new ArrayList<>();
        RequestContext.getCurrentInstance().execute("PF('wvPro').clearFilters()");
    }

    public void llenarProveedores() {
        String mQuery = "";
        try {
            catproveedores = new CatProveedores();
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
            System.out.println("Error en el llenado de Catálogo Proveedores. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_pro = "";
        cod_pai = "0";
        nom_pro = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        catproveedores = new CatProveedores();
        RequestContext.getCurrentInstance().execute("PF('wvPro').clearFilters()");
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_pro)) {
                    mQuery = "select ifnull(max(cod_pro),0)+1 as codigo from cat_pro;";
                    cod_pro = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_pro (cod_pro,cod_pai,nom_pro,per_con,tel_con,det_mai) "
                            + "values (" + cod_pro + "," + cod_pai + ",'" + nom_pro + "', '"
                            + per_con + "','" + tel_con + "','" + det_mai + "');";
                } else {
                    mQuery = "update cat_pro SET "
                            + " nom_pro = '" + nom_pro + "' "
                            + " ,per_con = '" + per_con + "' "
                            + " ,tel_con = '" + tel_con + "' "
                            + " ,det_mai = '" + det_mai + "' "
                            + "WHERE cod_pro = " + cod_pro + " "
                            + "and cod_pai = " + cod_pai + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Proveedores", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Proveedores", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Proveedores. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarProveedores();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_pro) == false) {
            try {
                mQuery = "delete from cat_pro where cod_pro=" + cod_pro + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Proveedores", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Proveedores", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Proveedores. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarProveedores();
            nuevo();
        } else {
            addMessage("Eliminar Proveedores", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_pro) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Proveedor.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_pro) from cat_pro "
                + "where upper(nom_pro)='" + nom_pro.toUpperCase()
                + "' and cod_pai =" + cod_pai + ";")) == false
                && "".equals(cod_pro)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre del Proveedor ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_pro = ((CatProveedores) event.getObject()).getCod_pro();
        cod_pai = ((CatProveedores) event.getObject()).getCod_pai();
        nom_pro = ((CatProveedores) event.getObject()).getNom_pro();
        per_con = ((CatProveedores) event.getObject()).getPer_con();
        tel_con = ((CatProveedores) event.getObject()).getTel_con();
        det_mai = ((CatProveedores) event.getObject()).getDet_mai();
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
