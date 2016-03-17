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
public class ManAlertasUsuarios implements Serializable {

    private static final long serialVersionUID = 8797678674716638L;
    @Inject
    Login cbean;
    private CatAlertasUsuarios catalertasusu;
    private List<CatAlertasUsuarios> alertasusu;
    private String id_ale_usu, id_ale, cod_usu;

    public ManAlertasUsuarios() {
    }

    public CatAlertasUsuarios getCatalertasusu() {
        return catalertasusu;
    }

    public void setCatalertasusu(CatAlertasUsuarios catalertasusu) {
        this.catalertasusu = catalertasusu;
    }

    public List<CatAlertasUsuarios> getAlertasusu() {
        return alertasusu;
    }

    public void setAlertasusu(List<CatAlertasUsuarios> alertasusu) {
        this.alertasusu = alertasusu;
    }

    public String getId_ale_usu() {
        return id_ale_usu;
    }

    public void setId_ale_usu(String id_ale_usu) {
        this.id_ale_usu = id_ale_usu;
    }

    public String getId_ale() {
        return id_ale;
    }

    public void setId_ale(String id_ale) {
        this.id_ale = id_ale;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public void iniciarventana() {
        id_ale_usu = "";
        id_ale = "";
        cod_usu ="";
        llenarAlertasusu();
    }

    public void cerrarventana() {
        id_ale_usu = "";
        id_ale = "";
        cod_usu ="";
        alertasusu = new ArrayList<>();
    }

    public void llenarAlertasusu() {
        String mQuery = "";
        try {
            catalertasusu = new CatAlertasUsuarios();
            alertasusu = new ArrayList<>();

            mQuery = "select id_ale_usu, id_ale, cod_usu, nom_usu order by id_ale_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                alertasusu.add(new CatAlertasUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de usuarios. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_ale_usu = "";
        id_ale = "";
        cod_usu = "";
        catalertasusu = new CatAlertasUsuarios();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_ale_usu)) {
                    mQuery = "select ifnull(max(id_ale_usu),0)+1 as codigo from cat_ale_usu;";
                    id_ale_usu = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_ale_usu (id_ale_usu, id_ale, cod_usu) "
                            + "values (" + id_ale_usu + "," + id_ale + "," + cod_usu + ");";
                } else {
                    mQuery = "update cat_ale_usu SET "
                            + " id_ale = " + id_ale + ", "
                            + " cod_usu = " + cod_usu + ", "
                            + "WHERE id_ale_usu = " + id_ale_usu + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Usuario", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Usuario", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertasusu();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_ale_usu) == false) {
            try {
                mQuery = "delete from cat_ale_usu where id_ale_usu=" + id_ale_usu + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Udsuario", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Usuario", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Usuario en la Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertasusu();
            nuevo();
        } else {
            addMessage("Eliminar Usuarios", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        
        boolean mValidar = true;
        if ("".equals(cod_usu) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un usuario.", 2);
        }
  
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_ale_usu = ((CatAlertasUsuarios) event.getObject()).getId_ale_usu();
        id_ale = ((CatAlertasUsuarios) event.getObject()).getId_ale();
        cod_usu = ((CatAlertasUsuarios) event.getObject()).getCod_usu();
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
