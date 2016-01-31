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
public class ManOperaciones implements Serializable{
    private static final long serialVersionUID = 8799330296716638L;
    @Inject
    Login cbean;
    private CatOperaciones catoperaciones;
    private List<CatOperaciones> operaciones;
    private String cod_ope, nom_ope;

    public ManOperaciones() {
    }

    public CatOperaciones getCatoperaciones() {
        return catoperaciones;
    }

    public void setCatoperaciones(CatOperaciones catoperaciones) {
        this.catoperaciones = catoperaciones;
    }

    public List<CatOperaciones> getOperaciones() {
        return operaciones;
    }

    public void setOperaciones(List<CatOperaciones> operaciones) {
        this.operaciones = operaciones;
    }

    public String getCod_ope() {
        return cod_ope;
    }

    public void setCod_ope(String cod_ope) {
        this.cod_ope = cod_ope;
    }

    public String getNom_ope() {
        return nom_ope;
    }

    public void setNom_ope(String nom_ope) {
        this.nom_ope = nom_ope;
    }
    
    public void iniciarventana() {
        cod_ope = "";
        nom_ope = "";
        llenarOperaciones();
    }

    public void cerrarventana() {
        cod_ope = "";
        nom_ope = "";
        operaciones = null;
    }

    public void llenarOperaciones() {
        String mQuery = "";
        try {
            catoperaciones = new CatOperaciones();
            operaciones = new ArrayList<>();

            mQuery = "select cod_ope, nom_ope "
                    + "from cat_ope "
                    + "order by cod_ope;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                operaciones.add(new CatOperaciones(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo Operaciones. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_ope = "";
        nom_ope = "";
        catoperaciones = null;
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_ope)) {
                    mQuery = "select ifnull(max(cod_ope),0)+1 as codigo from cat_ope;";
                    cod_ope = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_ope (cod_ope,nom_ope) "
                            + "values (" + cod_ope + ",'" + nom_ope + "');";
                } else {
                    mQuery = "update cat_ope SET "
                            + " nom_ope = '" + nom_ope + "' "
                            + "WHERE cod_ope = " + cod_ope + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Operación", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Operación", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Operación. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarOperaciones();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_ope) == false) {
            try {
                mQuery = "delete from cat_ope where cod_ope=" + cod_ope + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Operación", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Operación", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Operación. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarOperaciones();
            nuevo();
        } else {
            addMessage("Eliminar Operación", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(cod_ope) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para la Operación.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(cod_ope) from cat_ope "
                + "where upper(nom_ope)='" + nom_ope.toUpperCase() + "';")) == false
                && "".equals(cod_ope)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre de la Operación ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_ope = ((CatOperaciones) event.getObject()).getCod_ope();
        nom_ope = ((CatOperaciones) event.getObject()).getNom_ope();
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
