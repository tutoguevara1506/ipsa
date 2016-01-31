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

public class ManPaises implements Serializable {

    private static final long serialVersionUID = 74696476741638L;
    @Inject
    Login mbMain;
    private CatPaises catpaises;
    private List<CatPaises> paises;

    private String cod_pai, nom_pai;

    public ManPaises() {
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

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getNom_pai() {
        return nom_pai;
    }

    public void setNom_pai(String nom_pai) {
        this.nom_pai = nom_pai;
    }

    public void iniciarventana() {
        cod_pai = "";
        nom_pai = "";
        llenarPaises();
        if (Integer.valueOf(mbMain.getPerfil()) < 5) {
            RequestContext.getCurrentInstance().execute("PF('wPaises').hide()");
            addMessage("Error de Validación", "Usuario sin Privilegios de Administración.", 2);
        }
    }

    public void cerrarventana() {
        cod_pai = "";
        nom_pai = "";
        paises = new ArrayList<>();
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
            System.out.println("Error en el llenado del Catálogo de Paises. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_pai = "";
        nom_pai = "";
    }

    public void guardar() {
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                String mQuery;
                if ("".equals(cod_pai)) {
                    mQuery = "select ifnull(max(cod_pai),0)+1 as codigo from cat_pai;";
                    cod_pai = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_pai (cod_pai, nom_pai) "
                            + "values (" + cod_pai + ",'" + nom_pai + "');";
                } else {
                    mQuery = "update cat_pai SET "
                            + " nom_pai = '" + nom_pai + "' "
                            + "WHERE cod_pai = " + cod_pai + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar País", "Información Almacenada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar País", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar País. " + e.getMessage());
            }
            llenarPaises();
            nuevo();
        }

    }

    public void eliminar() {

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if ("".equals(cod_pai) == false) {
            try {
                String mQuery = "delete from cat_pai where cod_pai=" + cod_pai + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar País", "Información Eliminada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar País", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar País. " + e.getMessage());
            }
            llenarPaises();
        } else {
            addMessage("Eliminar País", "Debe elegir un Registro.", 2);
        }

        mAccesos.Desconectar();
        nuevo();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_pai)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre de País.", 2);
        }

        return mValidar;
    }

    public void onRowSelect(SelectEvent event) {
        cod_pai = ((CatPaises) event.getObject()).getCod_pai();
        nom_pai = ((CatPaises) event.getObject()).getNom_pai();

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
