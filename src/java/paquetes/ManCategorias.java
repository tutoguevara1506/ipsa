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
public class ManCategorias implements Serializable {

    private static final long serialVersionUID = 8799543897676638L;
    @Inject
    Login cbean;
    private CatCategorias catcategorias;
    private List<CatCategorias> categorias;
    private String cod_cat, nom_cat;

    public ManCategorias() {
    }

    public CatCategorias getCatcategorias() {
        return catcategorias;
    }

    public void setCatcategorias(CatCategorias catcategorias) {
        this.catcategorias = catcategorias;
    }

    public List<CatCategorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CatCategorias> categorias) {
        this.categorias = categorias;
    }

    public String getCod_cat() {
        return cod_cat;
    }

    public void setCod_cat(String cod_cat) {
        this.cod_cat = cod_cat;
    }

    public String getNom_cat() {
        return nom_cat;
    }

    public void setNom_cat(String nom_cat) {
        this.nom_cat = nom_cat;
    }

    public void iniciarventana() {
        cod_cat = "";
        nom_cat = "";
        llenarCategorias();
    }

    public void cerrarventana() {
        cod_cat = "";
        nom_cat = "";
        categorias = new ArrayList<>();
    }

    public void llenarCategorias() {
        String mQuery = "";
        try {
            catcategorias = new CatCategorias();
            categorias = new ArrayList<>();

            mQuery = "select cod_cat, nom_cat from cat_cat order by cod_cat;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                categorias.add(new CatCategorias(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo Categorías. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_cat = "";
        nom_cat = "";
        catcategorias = new CatCategorias();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_cat)) {
                    mQuery = "select ifnull(max(cod_cat),0)+1 as codigo from cat_cat;";
                    cod_cat = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_cat (cod_cat,nom_cat) "
                            + "values (" + cod_cat + ",'" + nom_cat + "');";
                } else {
                    mQuery = "update cat_cat SET "
                            + "nom_cat= '" + nom_cat + "' "
                            + "WHERE cod_cat= " + cod_cat + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Categorías", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Categorías", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Categorías. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCategorias();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_cat) == false) {
            try {
                mQuery = "delete from cat_cat where cod_cat=" + cod_cat + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Categorías", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Categorías", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Categorías. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCategorias();
            nuevo();
        } else {
            addMessage("Eliminar Categorías", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_cat) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para la Categoría.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_cat) from cat_cat "
                + "where upper(nom_cat)='" + nom_cat.toUpperCase() + "';")) == false && "".equals(cod_cat)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de la Categoría ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_cat = ((CatCategorias) event.getObject()).getCod_cat();
        nom_cat = ((CatCategorias) event.getObject()).getNom_cat();
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
