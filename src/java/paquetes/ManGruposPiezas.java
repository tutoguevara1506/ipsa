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

public class ManGruposPiezas implements Serializable {

    private static final long serialVersionUID = 74696476741638L;
    @Inject
    Login mbMain;
    private CatGruposPiezas catgrupospiezas;
    private List<CatGruposPiezas> grupospiezas;

    private String cod_gru, nom_gru;

    public ManGruposPiezas() {
    }

    public CatGruposPiezas getCatgrupospiezas() {
        return catgrupospiezas;
    }

    public void setCatgrupospiezas(CatGruposPiezas catgrupospiezas) {
        this.catgrupospiezas = catgrupospiezas;
    }

    public List<CatGruposPiezas> getGrupospiezas() {
        return grupospiezas;
    }

    public void setGrupospiezas(List<CatGruposPiezas> grupospiezas) {
        this.grupospiezas = grupospiezas;
    }

    public String getCod_gru() {
        return cod_gru;
    }

    public void setCod_gru(String cod_gru) {
        this.cod_gru = cod_gru;
    }

    public String getNom_gru() {
        return nom_gru;
    }

    public void setNom_gru(String nom_gru) {
        this.nom_gru = nom_gru;
    }

   
    public void iniciarventana() {
        cod_gru = "";
        nom_gru = "";
        llenarGrupospiezas();
        if (Integer.valueOf(mbMain.getPerfil()) < 5) {
            RequestContext.getCurrentInstance().execute("PF('wGrupospiezas').hide()");
            addMessage("Error de Validación", "Usuario sin Privilegios de Administración.", 2);
        }
    }

    public void cerrarventana() {
        cod_gru = "";
        nom_gru = "";
        grupospiezas = new ArrayList<>();
    }

    public void llenarGrupospiezas() {
        try {
            grupospiezas = new ArrayList<>();

            String mQuery = "select cod_gru, nom_gru "
                    + "from cat_gru order by cod_gru;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                grupospiezas.add(new CatGruposPiezas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado del Catálogo de Grupos de piezas. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_gru = "";
        nom_gru = "";
    }

    public void guardar() {
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                String mQuery;
                if ("".equals(cod_gru)) {
                    mQuery = "select ifnull(max(cod_gru),0)+1 as codigo from cat_gru;";
                    cod_gru = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_gru (cod_gru, nom_gru) "
                            + "values (" + cod_gru + ",'" + nom_gru + "');";
                } else {
                    mQuery = "update cat_gru SET "
                            + " nom_gru = '" + nom_gru + "' "
                            + "WHERE cod_gru = " + cod_gru + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Grupo", "Información Almacenada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Grupo", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Grupo de piezas. " + e.getMessage());
            }
            llenarGrupospiezas();
            nuevo();
        }

    }

    public void eliminar() {

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if ("".equals(cod_gru) == false) {
            try {
                String mQuery = "delete from cat_gru where cod_gru=" + cod_gru + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Grupo", "Información Eliminada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Grupo", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Bodega. " + e.getMessage());
            }
            llenarGrupospiezas();
        } else {
            addMessage("Eliminar Grupo", "Debe elegir un Registro.", 2);
        }

        mAccesos.Desconectar();
        nuevo();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_gru)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre de Grupo.", 2);
        }

        return mValidar;
    }

    public void onRowSelect(SelectEvent event) {
        cod_gru = ((CatGruposPiezas) event.getObject()).getCod_gru();
        nom_gru = ((CatGruposPiezas) event.getObject()).getNom_gru();

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
