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
public class ManAlertas implements Serializable {

    private static final long serialVersionUID = 8797678674716638L;
    @Inject
    Login cbean;
    private CatAlertas catalertas;
    private List<CatAlertas> alertas;
    private CatDepartamentos catdepartamentos;
    private List<CatDepartamentos> departamentos;
    
    private String id_ale, proceso, tabla_ctrl, campo_ctrl, alerta, aviso, recordatorio, id_estado, cod_dep;

    public ManAlertas() {
    }

    public CatAlertas getCatalertas() {
        return catalertas;
    }

    public void setCatalertas(CatAlertas catalertas) {
        this.catalertas = catalertas;
    }

    public List<CatAlertas> getAlertas() {
        return alertas;
    }

    public CatDepartamentos getCatdepartamentos() {
        return catdepartamentos;
    }

    public void setCatdepartamentos(CatDepartamentos catdepartamentos) {
        this.catdepartamentos = catdepartamentos;
    }

    public List<CatDepartamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<CatDepartamentos> departamentos) {
        this.departamentos = departamentos;
    }

    public void setAlertas(List<CatAlertas> alertas) {
        this.alertas = alertas;
    }

    public String getId_ale() {
        return id_ale;
    }

    public void setId_ale(String id_ale) {
        this.id_ale = id_ale;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getTabla_ctrl() {
        return tabla_ctrl;
    }

    public void setTabla_ctrl(String tabla_ctrl) {
        this.tabla_ctrl = tabla_ctrl;
    }

    public String getCampo_ctrl() {
        return campo_ctrl;
    }

    public void setCampo_ctrl(String campo_ctrl) {
        this.campo_ctrl = campo_ctrl;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }
    

    public void iniciarventana() {
        id_ale = "";
        proceso = ""; 
        tabla_ctrl = "";
        campo_ctrl = "";
        alerta = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        llenarAlertas();
        llenarDepartamentos();
    }

    public void cerrarventana() {
        id_ale = "";
        proceso = ""; 
        tabla_ctrl = "";
        campo_ctrl = "";
        alerta = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        alertas = new ArrayList<>();
    }
    
    public void llenarDepartamentos() {
        try {
            catdepartamentos = new CatDepartamentos();
            departamentos = new ArrayList<>();

            String mQuery = "select cod_dep, cod_pai, nom_dep "
                    + "from cat_dep order by cod_dep;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                departamentos.add(new CatDepartamentos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo de Departamentos. " + e.getMessage());
        }
    }

    public void llenarAlertas() {
        String mQuery = "";
        try {
            catalertas = new CatAlertas();
            alertas = new ArrayList<>();

            mQuery = "select id_ale, proceso, tabla_ctrl, camp_ctrl, alerta, aviso, recordatorio, id_estado from cat_ale order by id_ale;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                alertas.add(new CatAlertas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de alertas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_ale = "";
        proceso = ""; 
        tabla_ctrl = "";
        campo_ctrl = "";
        alerta = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        catalertas = new CatAlertas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_ale)) {
                    mQuery = "select ifnull(max(id_ale),0)+1 as codigo from cat_ale;";
                    id_ale = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_ale (id_ale, proceso, tabla_ctrl, campo_ctrl, alerta, aviso, recordatorio, id_estado) "
                            + "values (" + id_ale + ",'" + proceso + "','"+ tabla_ctrl + "','" + campo_ctrl + "','" + alerta + "','" + aviso + "','" + recordatorio + "','" + id_estado + "');";
                } else {
                    mQuery = "update cat_ale SET "
                            + " proceso = '" + proceso + "', "
                            + " tabla_ctrl = '" + tabla_ctrl + "', "
                            + " campo_ctrl = '" + campo_ctrl + "', "
                            + " alerta = '" + alerta + "', "
                            + " aviso = '" + aviso + "', "
                            + " recordatorio = '" + recordatorio + "', "
                            + " id_estado = '" + id_estado + "' "
                            + "WHERE id_ale = " + id_ale + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Alerta", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Alerta", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertas();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_ale) == false) {
            try {
                mQuery = "delete from cat_ale where id_ale=" + id_ale + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Alerta", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Alerta", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertas();
            nuevo();
        } else {
            addMessage("Eliminar Alertas", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        
        boolean mValidar = true;
        if ("".equals(proceso) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Proceso.", 2);
        }
        
        if ("".equals(tabla_ctrl) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una tabla a controlar.", 2);
        }

        if ("".equals(campo_ctrl) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una campo fecha a controlar.", 2);
        }
        
        if ("".equals(alerta) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un texto a enviar como alerta.", 2);
        }
        
        if ("".equals(aviso) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un periodo de tiempo para avisar la alerta.", 2);
        }
        
        if ("".equals(recordatorio) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un periodo de tiempo para recordar la alerta.", 2);
        }
  
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_ale = ((CatAlertas) event.getObject()).getId_ale();
        proceso = ((CatAlertas) event.getObject()).getProceso();
        tabla_ctrl = ((CatAlertas) event.getObject()).getTabla_ctrl();
        campo_ctrl = ((CatAlertas) event.getObject()).getCampo_ctrl();
        alerta = ((CatAlertas) event.getObject()).getAlerta();
        aviso = ((CatAlertas) event.getObject()).getAviso();
        recordatorio = ((CatAlertas) event.getObject()).getRecordatorio();
        id_estado = ((CatAlertas) event.getObject()).getId_estado();
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
