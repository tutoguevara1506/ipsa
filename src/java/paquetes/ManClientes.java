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

public class ManClientes implements Serializable {

    private static final long serialVersionUID = 8719478679471638L;
    @Inject
    Login cbean;
    private CatClientes catclientes;
    private List<CatClientes> clientes;
    private String cod_cli, cod_pai, nom_cli, per_con, tel_con, det_mai;

    public ManClientes() {
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

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
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
        cod_cli = "";
        cod_pai = cbean.getCod_pai();
        nom_cli = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        llenarClientes();
    }

    public void cerrarventana() {
        cod_cli = "";
        cod_pai = "";
        nom_cli = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        clientes = new ArrayList<>();
    }

    public void llenarClientes() {
        String mQuery = "";
        try {
            catclientes = new CatClientes();
            clientes = new ArrayList<>();

            mQuery = "select cod_cli,cod_pai, nom_cli,per_con,tel_con,det_mai  "
                    + "from cat_cli where cod_pai = " + cod_pai + " order by cod_cli;";
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
            System.out.println("Error en el llenado de Catálogo Clientes. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_cli = "";
        cod_pai = cbean.getCod_pai();
        nom_cli = "";
        per_con = "";
        tel_con = "";
        det_mai = "";
        catclientes = new CatClientes();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_cli)) {
                    mQuery = "select ifnull(max(cod_cli),0)+1 as codigo from cat_cli;";
                    cod_cli = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_cli (cod_cli,cod_pai,nom_cli,per_con,tel_con,det_mai) "
                            + "values (" + cod_cli + "," + cod_pai + ",'" + nom_cli + "', '"
                            + per_con + "','" + tel_con + "','" + det_mai + "');";
                } else {
                    mQuery = "update cat_cli SET "
                            + "nom_cli = '" + nom_cli + "' "
                            + ",per_con = '" + per_con + "' "
                            + ",tel_con = '" + tel_con + "' "
                            + ",det_mai = '" + det_mai + "' "
                            + "WHERE cod_cli = " + cod_cli + " "
                            + "and cod_pai = " + cod_pai + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Clientes", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Clientes", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Clientes. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarClientes();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_cli) == false) {
            try {
                mQuery = "delete from cat_cli where cod_cli=" + cod_cli + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Clientes", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Clientes", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Movimiento. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarClientes();
            nuevo();
        } else {
            addMessage("Eliminar Clientes", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_cli) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Cliente.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_cli) from cat_cli "
                + "where upper(nom_cli)='" + nom_cli.toUpperCase() + "' and cod_pai=" + cod_pai + ";")) == false
                && "".equals(cod_cli)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre del Cliente ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_cli = ((CatClientes) event.getObject()).getCod_cli();
        cod_pai = ((CatClientes) event.getObject()).getCod_pai();
        nom_cli = ((CatClientes) event.getObject()).getNom_cli();
        per_con = ((CatClientes) event.getObject()).getPer_con();
        tel_con = ((CatClientes) event.getObject()).getTel_con();
        det_mai = ((CatClientes) event.getObject()).getDet_mai();
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
