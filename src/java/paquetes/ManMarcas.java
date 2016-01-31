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
public class ManMarcas implements Serializable {

    private static final long serialVersionUID = 8797678674716638L;
    @Inject
    Login cbean;
    private CatMarcas catmarcas;
    private List<CatMarcas> marcas;
    private String id_mar, nom_mar;

    public ManMarcas() {
    }

    public CatMarcas getCatmarcas() {
        return catmarcas;
    }

    public void setCatmarcas(CatMarcas catmarcas) {
        this.catmarcas = catmarcas;
    }

    public List<CatMarcas> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<CatMarcas> marcas) {
        this.marcas = marcas;
    }

    public String getId_mar() {
        return id_mar;
    }

    public void setId_mar(String id_mar) {
        this.id_mar = id_mar;
    }

    public String getNom_mar() {
        return nom_mar;
    }

    public void setNom_mar(String nom_mar) {
        this.nom_mar = nom_mar;
    }

    public void iniciarventana() {
        id_mar = "";
        nom_mar = "";
        llenarMarcas();
    }

    public void cerrarventana() {
        id_mar = "";
        nom_mar = "";
        marcas = new ArrayList<>();
    }

    public void llenarMarcas() {
        String mQuery = "";
        try {
            catmarcas = new CatMarcas();
            marcas = new ArrayList<>();

            mQuery = "select id_mar, nom_mar from cat_mar order by id_mar;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                marcas.add(new CatMarcas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo Marcas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_mar = "";
        nom_mar = "";
        catmarcas = new CatMarcas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_mar)) {
                    mQuery = "select ifnull(max(id_mar),0)+1 as codigo from cat_mar;";
                    id_mar = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_mar (id_mar,nom_mar) "
                            + "values (" + id_mar + ",'" + nom_mar + "');";
                } else {
                    mQuery = "update cat_mar SET "
                            + " nom_mar = '" + nom_mar + "' "
                            + "WHERE id_mar = " + id_mar + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Marca", "Informaci�n Almacenada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Marca", "Error al momento de guardar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarMarcas();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_mar) == false) {
            try {
                mQuery = "delete from cat_mar where id_mar=" + id_mar + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Marca", "Informaci�n Eliminada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Marca", "Error al momento de Eliminar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarMarcas();
            nuevo();
        } else {
            addMessage("Eliminar Marcas", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_mar) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para la Marca.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_mar) from cat_mar "
                + "where upper(nom_mar)='" + nom_mar.toUpperCase() + "';")) == false && "".equals(id_mar)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de la Marca ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_mar = ((CatMarcas) event.getObject()).getId_mar();
        nom_mar = ((CatMarcas) event.getObject()).getNom_mar();
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
