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

public class ManBodegas implements Serializable {

    private static final long serialVersionUID = 8799345674716638L;
    @Inject
    Login cbean;
    private CatPaises catpaises;
    private List<CatPaises> paises;
    private CatBodegas catbodegas;
    private List<CatBodegas> bodegas;
    private String id_bod, nom_bod, cod_pai;

    public ManBodegas() {
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

    public String getId_bod() {
        return id_bod;
    }

    public void setId_bod(String id_bod) {
        this.id_bod = id_bod;
    }

    public String getNom_bod() {
        return nom_bod;
    }

    public void setNom_bod(String nom_bod) {
        this.nom_bod = nom_bod;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public void iniciarventana() {
        id_bod = "";
        nom_bod = "";
        cod_pai = cbean.getCod_pai();
        llenarPaises();
        llenarBodegas();
    }

    public void cerrarventana() {
        id_bod = "";
        nom_bod = "";
        cod_pai = "";
        bodegas = new ArrayList<>();
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
            System.out.println("Error en el llenado de Bodegas en Estantes. " + e.getMessage());
        }
    }
     
    public void llenarBodegas() {
        String mQuery = "";
        try {
            catbodegas = new CatBodegas();
            bodegas = new ArrayList<>();

            mQuery = "select bod.id_bod, bod.nom_bod,bod.cod_pai,pai.nom_pai "
                    + "from cat_bodegas as bod "
                    + "inner join cat_pai as pai on bod.cod_pai = pai.cod_pai "
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
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo de Estantes. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_bod = "";
        nom_bod = "";
        cod_pai = cbean.getCod_pai();
        catbodegas = new CatBodegas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_bod)) {
                    mQuery = "select ifnull(max(id_bod),0)+1 as codigo from cat_bodegas;";
                    id_bod = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_bodegas (id_bod,nom_bod,cod_pai) "
                            + "values (" + id_bod + ",'" + nom_bod + "'," + cod_pai + ");";
                } else {
                    mQuery = "update cat_bodegas SET "
                            + " nom_bod = '" + nom_bod + "', "
                            + " cod_pai = " + cod_pai + " "
                            + "WHERE id_bod = " + id_bod + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Estantes", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Estantes", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Estantes. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarBodegas();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_bod) == false) {
            try {
                mQuery = "delete from cat_bodegas where id_bod=" + id_bod + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Estantes", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Estantes", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Estantes. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarBodegas();
            nuevo();
        } else {
            addMessage("Eliminar Estantes", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_bod) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Estante.", 2);
        }
        if ("0".equals(cod_pai) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Bodega.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_bod) from cat_bodegas "
                + "where upper(nom_bod)='" + nom_bod.toUpperCase() + "' and cod_pai ="
                + cbean.getCod_pai() + ";")) == false
                && "".equals(id_bod)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de Estante ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_bod = ((CatBodegas) event.getObject()).getId_bod();
        nom_bod = ((CatBodegas) event.getObject()).getNom_bod();
        cod_pai = ((CatBodegas) event.getObject()).getCod_pai();
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
