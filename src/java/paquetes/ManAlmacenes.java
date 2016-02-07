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

public class ManAlmacenes implements Serializable {

    private static final long serialVersionUID = 74696476741638L;
    @Inject
    Login mbMain;
    private CatAlmacenes catalmacenes;
    private List<CatAlmacenes> almacenes;

    private String cod_alm, nom_alm;

    public ManAlmacenes() {
    }

    public CatAlmacenes getCatalmacenes() {
        return catalmacenes;
    }

    public void setCatalmacenes(CatAlmacenes catalmacenes) {
        this.catalmacenes = catalmacenes;
    }

    public List<CatAlmacenes> getAlmacenes() {
        return almacenes;
    }

    public void setAlmacenes(List<CatAlmacenes> almacenes) {
        this.almacenes = almacenes;
    }

    public String getCod_alm() {
        return cod_alm;
    }

    public void setCod_alm(String cod_alm) {
        this.cod_alm = cod_alm;
    }

    public String getNom_alm() {
        return nom_alm;
    }

    public void setNom_alm(String nom_alm) {
        this.nom_alm = nom_alm;
    }

   
    public void iniciarventana() {
        cod_alm = "";
        nom_alm = "";
        llenarAlmacenes();
        if (Integer.valueOf(mbMain.getPerfil()) < 5) {
            RequestContext.getCurrentInstance().execute("PF('wAlmacenes').hide()");
            addMessage("Error de Validación", "Usuario sin Privilegios de Administración.", 2);
        }
    }

    public void cerrarventana() {
        cod_alm = "";
        nom_alm = "";
        almacenes = new ArrayList<>();
    }

    public void llenarAlmacenes() {
        try {
            almacenes = new ArrayList<>();

            String mQuery = "select cod_alm, nom_alm "
                    + "from cat_alm order by cod_alm;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                almacenes.add(new CatAlmacenes(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado del Catálogo de Bodegas. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_alm = "";
        nom_alm = "";
    }

    public void guardar() {
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                String mQuery;
                if ("".equals(cod_alm)) {
                    mQuery = "select ifnull(max(cod_alm),0)+1 as codigo from cat_alm;";
                    cod_alm = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_alm (cod_alm, nom_alm) "
                            + "values (" + cod_alm + ",'" + nom_alm + "');";
                } else {
                    mQuery = "update cat_alm SET "
                            + " nom_alm = '" + nom_alm + "' "
                            + "WHERE cod_alm = " + cod_alm + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Bodega", "Información Almacenada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Bodega", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Bodega. " + e.getMessage());
            }
            llenarAlmacenes();
            nuevo();
        }

    }

    public void eliminar() {

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if ("".equals(cod_alm) == false) {
            try {
                String mQuery = "delete from cat_alm where cod_alm=" + cod_alm + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Bodega", "Información Eliminada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Bodega", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Bodega. " + e.getMessage());
            }
            llenarAlmacenes();
        } else {
            addMessage("Eliminar Bodega", "Debe elegir un Registro.", 2);
        }

        mAccesos.Desconectar();
        nuevo();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_alm)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre de Bodega  .", 2);
        }

        return mValidar;
    }

    public void onRowSelect(SelectEvent event) {
        cod_alm = ((CatAlmacenes) event.getObject()).getCod_alm();
        nom_alm = ((CatAlmacenes) event.getObject()).getNom_alm();

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
