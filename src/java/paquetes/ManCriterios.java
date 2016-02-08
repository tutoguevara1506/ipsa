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

public class ManCriterios implements Serializable {

    private static final long serialVersionUID = 8799345674716638L;
    @Inject
    Login cbean;
    private CatFactores catfactores;
    private List<CatFactores> factores;
    private CatCriterios catcriterios;
    private List<CatCriterios> criterios;
    private String id_cri, nom_cri, id_fac;

    public ManCriterios() {
    }

    public CatFactores getCatfactores() {
        return catfactores;
    }

    public void setCatfactores(CatFactores catfactores) {
        this.catfactores = catfactores;
    }

    public List<CatFactores> getFactores() {
        return factores;
    }

    public void setFactores(List<CatFactores> factores) {
        this.factores = factores;
    }

    public CatCriterios getCatcriterios() {
        return catcriterios;
    }

    public void setCatcriterios(CatCriterios catcriterios) {
        this.catcriterios = catcriterios;
    }

    public List<CatCriterios> getCriterios() {
        return criterios;
    }

    public void setCriterios(List<CatCriterios> criterios) {
        this.criterios = criterios;
    }

    public String getId_cri() {
        return id_cri;
    }

    public void setId_cri(String id_cri) {
        this.id_cri = id_cri;
    }

    public String getNom_cri() {
        return nom_cri;
    }

    public void setNom_cri(String nom_cri) {
        this.nom_cri = nom_cri;
    }

    public String getId_fac() {
        return id_fac;
    }

    public void setId_fac(String id_fac) {
        this.id_fac = id_fac;
    }

    public void iniciarventana() {
        id_cri = "";
        nom_cri = "";
        id_fac = getId_fac();
        llenarFactores();
        llenarCriterios();
    }

    public void cerrarventana() {
        id_cri = "";
        nom_cri = "";
        id_fac = "";
        criterios = new ArrayList<>();
    }

     public void llenarFactores() {
        try {
            factores = new ArrayList<>();

            String mQuery = "select id_fac, nom_alm "
                    + "from cat_fac order by id_fac;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                factores.add(new CatFactores(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de factores en criterios. " + e.getMessage());
        }
    }
     
    public void llenarCriterios() {
        String mQuery = "";
        try {
            catcriterios = new CatCriterios();
            criterios = new ArrayList<>();

            mQuery = "select cri.id_cri, cri.nom_cri, cri.id_fac, fac.nom_fac "
                    + "from cat_criterios as cri "
                    + "inner join cat_fac as fac on cri.id_fac = fac.id_fac "
                    + "order by id_cri;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                criterios.add(new CatCriterios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Criterios de factores. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_cri = "";
        nom_cri = "";
        id_fac = getId_fac();
        catcriterios = new CatCriterios();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_cri)) {
                    mQuery = "select ifnull(max(id_cri),0)+1 as codigo from cat_criterios;";
                    id_cri = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_criterios (id_cri, nom_cri, id_fac) "
                            + "values (" + id_cri + ",'" + nom_cri + "'," + id_fac + ");";
                } else {
                    mQuery = "update cat_criterios SET "
                            + " nom_cri = '" + nom_cri + "', "
                            + " id_fac = " + id_fac + " "
                            + "WHERE id_cri = " + id_cri + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Criterios", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Criterios", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Criterios. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCriterios();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_cri) == false) {
            try {
                mQuery = "delete from cat_criterios where id_cri=" + id_cri + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Criterios", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Criterios", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Criterios. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCriterios();
            nuevo();
        } else {
            addMessage("Eliminar Criterios", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_cri) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Criterio.", 2);
        }
        if ("0".equals(id_fac) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Factor.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_cri) from cat_criterios "
                + "where upper(nom_cri)='" + nom_cri.toUpperCase() + "' and id_fac ="
                + getId_fac() + ";")) == false
                && "".equals(id_cri)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de Criterio ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_cri = ((CatCriterios) event.getObject()).getId_cri();
        nom_cri = ((CatCriterios) event.getObject()).getNom_cri();
        id_fac = ((CatCriterios) event.getObject()).getId_fac();
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
