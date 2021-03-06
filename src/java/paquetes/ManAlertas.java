package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.DragDropEvent;
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
    private List<CatTiposAlertas> tiposalertas;
    private CatUsuarios catusuarios;
    private List<CatUsuarios> usuarios;
    private List<CatUsuarios> usuariosel;
    private LogAlertas logalertas;
    private List<LogAlertas> logale;
    
    private String id_ale, cod_dep, id_tip_ale, aviso, recordatorio, id_estado;
    private String id_ale_usu, cod_usu;

    public ManAlertas() {
    }

    @PostConstruct
    public void init() {
        Date time= new Date();
        System.out.println("hora de inicio "+ time.toString());
        
        usuariosel = new ArrayList<CatUsuarios>();
        llenarLogAlertas();        
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

    public List<CatTiposAlertas> getTiposalertas() {
        return tiposalertas;
    }

    public void setTiposalertas(List<CatTiposAlertas> tiposalertas) {
        this.tiposalertas = tiposalertas;
    }
    

    public CatUsuarios getCatusuarios() {
        return catusuarios;
    }

    public void setCatusuarios(CatUsuarios catusuarios) {
        this.catusuarios = catusuarios;
    }

    public List<CatUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CatUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public void setAlertas(List<CatAlertas> alertas) {
        this.alertas = alertas;
    }

   

    public List<CatUsuarios> getUsuariosel() {
        return usuariosel;
    }

    public void setUsuariosel(List<CatUsuarios> usuariosel) {
        this.usuariosel = usuariosel;
    }

    public String getId_ale() {
        return id_ale;
    }

    public void setId_ale(String id_ale) {
        this.id_ale = id_ale;
    }

    public String getId_tip_ale() {
        return id_tip_ale;
    }

    public void setId_tip_ale(String id_tip_ale) {
        this.id_tip_ale = id_tip_ale;
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

    public String getId_ale_usu() {
        return id_ale_usu;
    }

    public void setId_ale_usu(String id_ale_usu) {
        this.id_ale_usu = id_ale_usu;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public List<LogAlertas> getLogale() {
        return logale;
    }

    public void setLogale(List<LogAlertas> logale) {
        this.logale = logale;
    }

    public LogAlertas getLogalertas() {
        return logalertas;
    }

    public void setLogalertas(LogAlertas logalertas) {
        this.logalertas = logalertas;
    }
        
    public void iniciarventana() {
        id_ale = "";
        cod_dep = ""; 
        id_tip_ale = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        id_ale_usu = "";
        cod_usu = "";
        llenarAlertas();
        llenarDepartamentos();
        llenarTiposAlertas();
        llenarUsuarios();
    }

    public void cerrarventana() {
        id_ale = "";
        cod_dep = ""; 
        id_tip_ale = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        id_ale_usu= "";
        cod_usu = "";
        alertas = new ArrayList<>();
        usuariosel = new ArrayList<>();
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
            System.out.println("Error en el llenado de Cat�logo de Departamentos. " + e.getMessage());
        }
    }
    
    public void llenarTiposAlertas() {
        try {
            
            tiposalertas = new ArrayList<>();

            String mQuery = "select id_tip_ale, nom_tip_ale "
                    + "from cat_tip_ale order by id_tip_ale;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                tiposalertas.add(new CatTiposAlertas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo de Departamentos. " + e.getMessage());
        }
    }
    
    public void llenarUsuarios() {
        try {
            catusuarios = new CatUsuarios();
            usuarios = new ArrayList<>();
            String mQuery = "";
            
            if("".equals(id_ale)){
                mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai, ifnull(usu.cod_pai,'') as cod_pai, ifnull(dep.nom_dep,'') as nomdep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep order by cod_usu;";
            }
            else {
                mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai, ifnull(usu.cod_pai,'') as cod_pai, ifnull(dep.nom_dep,'') as nomdep "
                    + "from cat_usu as usu left join cat_dep as dep on usu.cod_dep = dep.cod_dep "
                    + "where usu.cod_usu NOT IN (select alusu.cod_usu from cat_ale_usu alusu where alusu.id_ale="+id_ale+") order by cod_usu;";
            }
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                usuarios.add(new CatUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10)                         
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo de Usuarios. " + e.getMessage());
        }
    }

    public void llenarUsuSeleccionados() {
        try {
            usuariosel = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai, ifnull(usu.cod_pai,'') as cod_pai, ifnull(dep.nom_dep,'') as nomdep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep "
                    + "inner join cat_ale_usu aleu on usu.cod_usu = aleu.cod_usu "
                    + " where aleu.id_ale = " + id_ale + " order by cod_usu;";
                    
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                usuariosel.add(new CatUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10)                         
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cat�logo de Usuarios Seleccionados. " + e.getMessage());
        }
    }
    
    public void llenarAlertas() {
        String mQuery = "";
        try {
            catalertas = new CatAlertas();
            alertas = new ArrayList<>();

            mQuery = "select ale.id_ale, ale.cod_dep, ale.id_tip_ale, ale.aviso, ale.recordatorio, ale.id_estado, dep.nom_dep, tip.nom_tip_ale "
                    +"from cat_ale ale inner join cat_dep dep on ale.cod_dep = dep.cod_dep "
                    + "inner join cat_tip_ale tip on ale.id_tip_ale = tip.id_tip_ale order by ale.id_ale;";
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
    
    public void llenarLogAlertas() {
        String mQuery = "";
        try {
            
            logalertas = new LogAlertas();
            logale = new ArrayList<>();

            mQuery = "SELECT DISTINCT lale.id_log_ale, lale.fec_ale, lale.id_tip_ale, lale.ale_des, tip.nom_tip_ale "
                    + "FROM log_ale lale INNER JOIN cat_tip_ale tip  ON lale.id_tip_ale = tip.id_tip_ale " 
                    + "INNER JOIN cat_ale_usu usu ON usu.id_ale = lale.id_ale "
                    + "WHERE usu.cod_usu ="+ cbean.getCod_usu() + " order by lale.fec_ale desc;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                logale.add(new LogAlertas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el registro de log de alertas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_ale = "";
        cod_dep = ""; 
        id_tip_ale = "";
        aviso = "";
        recordatorio = "";
        id_estado = "";
        id_ale_usu="";
        cod_usu="";
        catalertas = new CatAlertas();
        llenarUsuarios();
        usuariosel = new ArrayList<>();
    }

    public void guardar() {
        String mQuery = "";
        this.id_estado= "0";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_ale)) {
                    mQuery = "select ifnull(max(id_ale),0)+1 as codigo from cat_ale;";
                    id_ale = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_ale (id_ale, cod_dep, id_tip_ale, aviso, recordatorio, id_estado) "
                            + "values (" + id_ale + ",'" + cod_dep + "'," + id_tip_ale + ", " + aviso + ", " + recordatorio + "," + id_estado + ");";
                } else {
                    mQuery = "update cat_ale SET "
                            + " cod_dep = '" + cod_dep + "', "
                            + " id_tip_ale = " + id_tip_ale + ", "
                            + " aviso = " + aviso + ", "
                            + " recordatorio = " + recordatorio + ", "
                            + " id_estado = " + id_estado + " "
                            + "WHERE id_ale = " + id_ale + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                    
                mQuery = "delete from cat_ale_usu where id_ale = "+ id_ale + ";";
                mAccesos.dmlSQLvariable(mQuery);
                
                usuariosel.stream().forEach((usadd) -> {
                    String mQuery2="";
                                        
                    mQuery2 = "select ifnull(max(id_ale_usu),0)+1 as codigo from cat_ale_usu;";
                    id_ale_usu = mAccesos.strQuerySQLvariable(mQuery2);
                     cod_usu = usadd.getCod_usu();
                    
                    mQuery2 = "insert into cat_ale_usu (id_ale_usu, id_ale, cod_usu) "
                    + "values (" + id_ale_usu + "," + id_ale + ","+ cod_usu + ");";
                    
                    mAccesos.dmlSQLvariable(mQuery2);
                   // System.out.println(usadd.getCod_usu());
                });            
                
                mAccesos.Desconectar();
                addMessage("Guardar Alerta", "Informaci�n Almacenada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Alerta", "Error al momento de guardar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertas();
            nuevo();
        }
    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_ale) == false) {
            try {
                mQuery = "delete from cat_ale where id_ale=" + id_ale + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Alerta", "Informaci�n Eliminada con �xito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Alerta", "Error al momento de Eliminar la informaci�n. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Alerta. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarAlertas();
            nuevo();
        } else {
            addMessage("Eliminar Alertas", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }
    
    public void remove(){
                        
        String idRemove= logalertas.getId_log_ale();
        System.out.println("entro a remove "+ idRemove);
    }

    public boolean validardatos() {
        
        boolean mValidar = true;
        if ("".equals(cod_dep) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Departamento.", 2);
        }
        
        if ("".equals(id_tip_ale) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un tipo de alerta.", 2);
        }
        
        if ("".equals(aviso)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad de dias en la alerta mayor que Cero.", 2);
        } else if (!Utilitarios.isNumeric(aviso)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una numero valido de dias para el aviso.", 2);
        }

        if ("".equals(recordatorio)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad de dias en el periodo para recordatorio mayor que Cero.", 2);
        } else if (!Utilitarios.isNumeric(recordatorio)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar una numero valido de dias para el recordatorio.", 2);
        }
        
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_ale = ((CatAlertas) event.getObject()).getId_ale();
        cod_dep = ((CatAlertas) event.getObject()).getCod_dep();
        id_tip_ale = ((CatAlertas) event.getObject()).getId_tip_ale();
        aviso = ((CatAlertas) event.getObject()).getAviso();
        recordatorio = ((CatAlertas) event.getObject()).getRecordatorio();
        id_estado = ((CatAlertas) event.getObject()).getId_estado();
        llenarUsuarios();
        llenarUsuSeleccionados();
    }
    
    public void onUsuDrop(DragDropEvent ddEvent) {
               
        catusuarios = ((CatUsuarios) ddEvent.getData());
        usuariosel.add(catusuarios);
        usuarios.remove(catusuarios);
    }
    
    public void onUsuDropRet(DragDropEvent ddEvent) {
               
        catusuarios = ((CatUsuarios) ddEvent.getData());
        usuarios.add(catusuarios);
        usuariosel.remove(catusuarios);
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
