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
public class ManFallas implements Serializable {

    private static final long serialVersionUID = 8797671468416638L;
    @Inject
    Login cbean;
    private CatFallas catfallas;
    private List<CatFallas> fallas;
    private CatGrupoFallas catgrupofallas;
    private List<CatGrupoFallas> grupofallas;
    private String cod_fal, cod_gru_fal, nom_fal, nomgrupo;

    public ManFallas() {
    }

    public CatFallas getCatfallas() {
        return catfallas;
    }

    public void setCatfallas(CatFallas catfallas) {
        this.catfallas = catfallas;
    }

    public List<CatFallas> getFallas() {
        return fallas;
    }

    public void setFallas(List<CatFallas> fallas) {
        this.fallas = fallas;
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

    public String getCod_fal() {
        return cod_fal;
    }

    public void setCod_fal(String cod_fal) {
        this.cod_fal = cod_fal;
    }

    public String getCod_gru_fal() {
        return cod_gru_fal;
    }

    public void setCod_gru_fal(String cod_gru_fal) {
        this.cod_gru_fal = cod_gru_fal;
    }

    public String getNom_fal() {
        return nom_fal;
    }

    public void setNom_fal(String nom_fal) {
        this.nom_fal = nom_fal;
    }

    public String getNomgrupo() {
        return nomgrupo;
    }

    public void setNomgrupo(String nomgrupo) {
        this.nomgrupo = nomgrupo;
    }

    public void iniciarventana() {
        cod_fal = "";
        nom_fal = "";
        cod_gru_fal = "";
        llenarGrupoFallas();
        llenarFallas();
    }

    public void cerrarventana() {
        cod_fal = "";
        nom_fal = "";
        cod_gru_fal = "";
        grupofallas = new ArrayList<>();
        fallas = new ArrayList<>();
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
            System.out.println("Error en el llenado de Grupos fallas en Catálogo Fallas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarFallas() {
        String mQuery = "";
        try {
            catfallas = new CatFallas();
            fallas = new ArrayList<>();

            mQuery = "select fal.cod_fal, fal.cod_gru_fal, fal.nom_fal, grf.nom_gru_fal "
                    + "from cat_fal as fal "
                    + "left join cat_gru_fal as grf on fal.cod_gru_fal = grf.cod_gru_fal "
                    + "order by fal.cod_fal;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                fallas.add(new CatFallas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_fal = "";
        nom_fal = "";
        cod_gru_fal = "";
        catfallas = new CatFallas();
    }

     public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_fal)) {
                    mQuery = "select ifnull(max(cod_fal),0)+1 as codigo from cat_fal;";
                    cod_fal = mAccesos.strQuerySQLvariable(mQuery);
                    
                    mQuery = "insert into cat_fal (cod_fal, cod_gru_fal, nom_fal) "
                            + "values (" + cod_fal + ",'" + cod_gru_fal + "','"+ nom_fal +"');";
                } else {
                    mQuery = "update cat_fal SET "
                            + " cod_gru_fal = '" + cod_gru_fal + "', "
                            + " nom_fal = '" + nom_fal + "' "
                            + "WHERE cod_fal = " + cod_fal + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Falla", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Falla", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Falla " + e.getMessage() + " Query: " + mQuery);
            }
            llenarFallas();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_fal) == false) {
            try {
                mQuery = "delete from cat_fal where cod_fal=" + cod_fal + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Falla", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Falla", "Error al momento de Eliminar Falla. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Falla. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarFallas();
            nuevo();
        } else {
            addMessage("Eliminar Fallas", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("0".equals(cod_gru_fal) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Grupo de Fallas.", 2);
        }
        
        if ("".equals(nom_fal) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un nombre de falla.", 2);
        }
        
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_fal) from cat_fal "
                + "where cod_gru_fal ='" + cod_gru_fal + "' AND upper(nom_fal)='" + nom_fal.toUpperCase() + "';")) == false && "".equals(cod_fal)) {
            mValidar = false;
            addMessage("Validar Datos", "La asignacion ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_fal = ((CatFallas) event.getObject()).getCod_fal();
        cod_gru_fal = ((CatFallas) event.getObject()).getCod_gru_fal();
        nom_fal = ((CatFallas) event.getObject()).getNom_fal();
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
