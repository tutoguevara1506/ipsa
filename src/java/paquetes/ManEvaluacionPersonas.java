package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManEvaluacionPersonas implements Serializable {

    private static final long serialVersionUID = 8797678674711638L;
    @Inject
    Login cbean;
    private CatPersonas catpersonas;
    private List<CatPersonas> personas;
    private CatCargos catcargos;
    private List<CatCargos> cargos;
    private CatUsuarios catusuarios;
    private List<CatUsuarios> usuarios;
    private CatDepartamentos catdepartamentos;
    private List<CatDepartamentos> departamentos;
    private CatEvaluaciones catevaluaciones;
    private List<CatEvaluaciones> evaluaciones;
    private CatEvaluacionPersonas catevaluacionpersonas;
    private List<CatEvaluacionPersonas> evaluacionpersonas;
    
    private String id_eva_per, id_per, id_eva, f_eva, per_eva, obs_eva, nom_per, nom_per_eva;
    private Date dfevaluacion;
    
    public ManEvaluacionPersonas() {
    }

    public CatPersonas getCatpersonas() {
        return catpersonas;
    }

    public void setCatpersonas(CatPersonas catpersonas) {
        this.catpersonas = catpersonas;
    }

    public List<CatPersonas> getPersonas() {
        return personas;
    }

    public void setPersonas(List<CatPersonas> personas) {
        this.personas = personas;
    }

    public CatCargos getCatcargos() {
        return catcargos;
    }

    public void setCatcargos(CatCargos catcargos) {
        this.catcargos = catcargos;
    }

    public List<CatCargos> getCargos() {
        return cargos;
    }

    public void setCargos(List<CatCargos> cargos) {
        this.cargos = cargos;
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
    
    public String getId_per() {
        return id_per;
    }

    public void setId_per(String id_persona) {
        this.id_per = id_persona;
    }

    public Date getDfevaluacion() {
        return dfevaluacion;
    }

    public void setDfevaluacion(Date dfevaluacion) {
        this.dfevaluacion = dfevaluacion;
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

    public CatEvaluaciones getCatevaluaciones() {
        return catevaluaciones;
    }

    public void setCatevaluaciones(CatEvaluaciones catevaluaciones) {
        this.catevaluaciones = catevaluaciones;
    }

    public List<CatEvaluaciones> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<CatEvaluaciones> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    
    public CatEvaluacionPersonas getCatevaluacionpersonas() {
        return catevaluacionpersonas;
    }

    public void setCatevaluacionpersonas(CatEvaluacionPersonas catevaluacionpersonas) {
        this.catevaluacionpersonas = catevaluacionpersonas;
    }

    public List<CatEvaluacionPersonas> getEvaluacionpersonas() {
        return evaluacionpersonas;
    }

    public void setEvaluacionpersonas(List<CatEvaluacionPersonas> evaluacionpersonas) {
        this.evaluacionpersonas = evaluacionpersonas;
    }

    public String getId_eva_per() {
        return id_eva_per;
    }

    public void setId_eva_per(String id_eva_per) {
        this.id_eva_per = id_eva_per;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public String getF_eva() {
        return f_eva;
    }

    public void setF_eva(String f_eva) {
        this.f_eva = f_eva;
    }

    public String getPer_eva() {
        return per_eva;
    }

    public void setPer_eva(String per_eva) {
        this.per_eva = per_eva;
    }

    public String getObs_eva() {
        return obs_eva;
    }

    public void setObs_eva(String obs_eva) {
        this.obs_eva = obs_eva;
    }

    public String getNom_per() {
        return nom_per;
    }

    public void setNom_per(String nom_per) {
        this.nom_per = nom_per;
    }

    public String getNom_per_eva() {
        return nom_per_eva;
    }

    public void setNom_per_eva(String nom_per_eva) {
        this.nom_per_eva = nom_per_eva;
    }

    public void iniciarventana() {        
        id_per = "";
        id_eva_per = "";
        id_per = "";
        id_eva = "";
        f_eva = "";
        per_eva = "";
        obs_eva = "";
        nom_per = "";
        nom_per_eva = "";
        llenarCargos();
        llenarUsuarios();
        llenarPersonas();
        llenarEvaluaciones();
        llenarDepartamentos();
        llenarEvaluacionPersonas();
    }
    
    public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
         id_eva_per = "";
        id_per = "";
        id_eva = "";
        f_eva = "";
        per_eva = "";
        obs_eva = "";
        nom_per = "";
        nom_per_eva = "";
        catevaluacionpersonas = new CatEvaluacionPersonas();
    }

    public void cerrarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        id_eva_per = "";
        id_per = "";
        id_eva = "";
        f_eva = "";
        per_eva = "";
        obs_eva = "";
        nom_per = "";
        nom_per_eva = "";
        personas = new ArrayList<>();
    }

    public void llenarCargos() {
        String mQuery = "";
        try {
            catcargos = new CatCargos();
            cargos = new ArrayList<>();

            mQuery = "select id_car, des_car from cat_car order by id_car;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                cargos.add(new CatCargos(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cargos de Personal. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    public void llenarUsuarios() {
        try {
            catusuarios = new CatUsuarios();
            usuarios = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai order by cod_usu;";
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
            System.out.println("Error en el llenado de Catálogo de Usuarios. " + e.getMessage());
        }
    }
    
    public void llenarPersonas() {
        String mQuery = "";
        try {
            catpersonas = new CatPersonas();
            personas = new ArrayList<>();

            mQuery = "select id_per, nombres, apellidos, direccion, "
                    + "telefono, celular, email, dui, nit, isss, fingreso, codigo, "
                    + "cod_dep, id_jef, id_car, cod_usu "
                    + "from cat_persona order by id_per;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                personas.add(new CatPersonas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10),
                        resVariable.getString(11),
                        resVariable.getString(12),
                        resVariable.getString(13),
                        resVariable.getString(14),
                        resVariable.getString(15),
                        resVariable.getString(16)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Registro de Personas. " + e.getMessage() + " Query: " + mQuery);
        }
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
            System.out.println("Error en el llenado de Departamentos en ManMaestroMan. " + e.getMessage());
        }
    }
    
     public void llenarEvaluaciones() {
        String mQuery = "";
        try {
            catevaluaciones = new CatEvaluaciones();
            evaluaciones = new ArrayList<>();

            mQuery = "select id_eva, nom_eva from cat_eva order by id_eva;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                evaluaciones.add(new CatEvaluaciones(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo de Evaluaciones. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarEvaluacionPersonas() {
        try {
            catevaluacionpersonas = new CatEvaluacionPersonas();
            evaluacionpersonas = new ArrayList<>();

            String mQuery = "SELECT id_eva_per, id_per, id_eva, f_eva, per_eva, obs_eva" +
                            " FROM ipsa.tbl_eva_per order by id_eva_per;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                evaluacionpersonas.add(new CatEvaluacionPersonas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Evaluaciones en ManEvaluacionPersonas. " + e.getMessage());
        }
    }

    public void guardar() {
        String mQuery = "";
        
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                
                if ("".equals(id_eva_per)) {
                    mQuery = "select ifnull(max(id_eva_per),0)+1 as codigo from tbl_eva_per;";
                    id_eva_per = mAccesos.strQuerySQLvariable(mQuery);
                           
                    
                    mQuery = "insert into cat_persona (id_eva_per, id_per, id_eva, f_eva, per_eva, obs_eva) "
                            + "values (" + id_eva_per + "," + id_per + ", str_to_date('" + f_eva + "','%d/%m/%Y')" + ", " + per_eva + ",'" + obs_eva + "');";
                } else {
                    mQuery = "update tbl_eva_per SET "                           
                            + " id_per = '" + id_per + "',"
                            + " id_eva = '" + id_eva + "',"
                            + " f_eva = '" + f_eva + "',"
                            + " per_eva = '" + per_eva + "',"
                            + " obs_eva = '" + obs_eva + "'"
                            + " WHERE id_eva_per = " + id_eva_per + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Evaluacion Persona", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Evaluacion Persona", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Evaluacion Persona. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPersonas();
            nuevo();
        }
 
    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_per) == false) {
            try {
                mQuery = "delete from cat_persona where id_per=" + id_per + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Persona", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Persona", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Persona. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPersonas();
            nuevo();
        } else {
            addMessage("Eliminar Persona", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }
    
    public void dateSelected (SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        f_eva = format.format(date);
    }

    public boolean validardatos() {
        boolean mValidar = true;
        
        if ("".equals(id_per) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Nombre de la persona a evaluar.", 2);
        }
        
        if ("".equals(id_eva) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Evaluacion.", 2);
        }
        
        if ("".equals(f_eva) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Fecha de evaluacion.", 2);
        }
        
        if ("".equals(per_eva) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Persona que evalua.", 2);
        }
        
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_eva_per = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        id_per = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        id_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        f_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        per_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        obs_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        nom_per = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        nom_per_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
    
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