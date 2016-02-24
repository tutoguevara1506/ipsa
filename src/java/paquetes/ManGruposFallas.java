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
public class ManGruposFallas implements Serializable {

    private static final long serialVersionUID = 8797678674716638L;
    @Inject
    Login cbean;
    private CatGrupoFallas catgrupofallas;
    private List<CatGrupoFallas> grupofallas;
    private String cod_gru_fal, nom_gru_fal;

    public ManGruposFallas() {
    }

    public CatGrupoFallas getCatgrupofallas() {
        return catgrupofallas;
    }

    public void setCatgrupofallas(CatGrupoFallas catgrupofallas) {
        this.catgrupofallas = catgrupofallas;
    }

    public List<CatGrupoFallas> getGrupofallas() {
        return grupofallas;
    }

    public void setGrupofallas(List<CatGrupoFallas> grupofallas) {
        this.grupofallas = grupofallas;
    }

    public String getCod_gru_fal() {
        return cod_gru_fal;
    }

    public void setCod_gru_fal(String cod_gru_fal) {
        this.cod_gru_fal = cod_gru_fal;
    }

    public String getNom_gru_fal() {
        return nom_gru_fal;
    }

    public void setNom_gru_fal(String nom_gru_fal) {
        this.nom_gru_fal = nom_gru_fal;
    }

   
    public void iniciarventana() {
        cod_gru_fal = "";
        nom_gru_fal = "";
        llenarGrupoFallas();
    }

    public void cerrarventana() {
        cod_gru_fal = "";
        nom_gru_fal = "";
        grupofallas = new ArrayList<>();
    }

    public void llenarGrupoFallas() {
        String mQuery = "";
        try {
            catgrupofallas = new CatGrupoFallas();
            grupofallas = new ArrayList<>();

            mQuery = "select cod_gru_fal, nom_gru_fal from cat_gru_fal order by cod_gru_fal;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                grupofallas.add(new CatGrupoFallas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Grupos de Falla. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_gru_fal = "";
        nom_gru_fal = "";
        catgrupofallas = new CatGrupoFallas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_gru_fal)) {
                    mQuery = "select ifnull(max(cod_gru_fal),0)+1 as codigo from cat_gru_fal;";
                    cod_gru_fal = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_gru_fal (cod_gru_fal,nom_gru_fal) "
                            + "values (" + cod_gru_fal + ",'" + nom_gru_fal + "');";
                } else {
                    mQuery = "update cat_gru_fal SET "
                            + " nom_gru_fal = '" + nom_gru_fal + "' "
                            + "WHERE cod_gru_fal = " + cod_gru_fal + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Grupo de Falla", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Grupo de Falla", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Grupo de Falla. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarGrupoFallas();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_gru_fal) == false) {
            try {
                mQuery = "delete from cat_gru_fal where cod_gru_fal=" + cod_gru_fal + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Grupo de Fallas", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Grupo de Fallas", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Grupo de Fallas. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarGrupoFallas();
            nuevo();
        } else {
            addMessage("Eliminar Grupo de Fallas", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_gru_fal) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Grupo.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_gru_fal) from cat_gru_fal "
                + "where upper(nom_gru_fal)='" + nom_gru_fal.toUpperCase() + "';")) == false && "".equals(cod_gru_fal)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de la Marca ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_gru_fal = ((CatGrupoFallas) event.getObject()).getCod_gru_fal();
        nom_gru_fal = ((CatGrupoFallas) event.getObject()).getNom_gru_fal();
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
