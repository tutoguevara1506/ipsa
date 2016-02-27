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

public class ManEvaluacionDetalle implements Serializable {

    private static final long serialVersionUID = 8797678674761L;
    @Inject
    Login cbean;
    private CatEvaluaciones catevaluaciones;
    private List<CatEvaluaciones> evaluaciones;
    private CatCriterios catcriterios;
    private List<CatCriterios> criterios;
    private CatFactores catfactores;
    private List<CatFactores> factores;
    private CatEvaluacionDetalle catevaluaciondetalle;
    private List<CatEvaluacionDetalle> evaluaciondetalle;
    
    private String id_eva_det, id_eva, num_preg, id_cri, nomeva, nomcri, id_fac, factor;
        
         
    public ManEvaluacionDetalle() {
       
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

    public CatEvaluacionDetalle getCatevaluaciondetalle() {
        return catevaluaciondetalle;
    }

    public void setCatevaluaciondetalle(CatEvaluacionDetalle catevaluaciondetalle) {
        this.catevaluaciondetalle = catevaluaciondetalle;
    }

    public List<CatEvaluacionDetalle> getEvaluaciondetalle() {
        return evaluaciondetalle;
    }

    public void setEvaluaciondetalle(List<CatEvaluacionDetalle> evaluaciondetalle) {
        this.evaluaciondetalle = evaluaciondetalle;
    }

       
    public String getId_eva_det() {
        return id_eva_det;
    }

    public void setId_eva_det(String id_eva_det) {
        this.id_eva_det = id_eva_det;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public String getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(String num_preg) {
        this.num_preg = num_preg;
    }

    public String getId_cri() {
        return id_cri;
    }

    public void setId_cri(String id_cri) {
        this.id_cri = id_cri;
    }

   
    public String getNomeva() {
        return nomeva;
    }

    public void setNomeva(String nomeva) {
        this.nomeva = nomeva;
    }

    public String getNomcri() {
        return nomcri;
    }

    public void setNomcri(String nomcri) {
        this.nomcri = nomcri;
    }

    public String getId_fac() {
        return id_fac;
    }

    public void setId_fac(String id_fac) {
        this.id_fac = id_fac;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }
       
    public void iniciarventana() {
        id_eva_det ="";
        id_fac ="";
        id_eva = "";
        num_preg = "";
        id_cri = "";
        llenarEvaluaciones();
        llenarFactores();
        llenarCriterios();
        
    }

    public void cerrarventana() {
        id_eva_det ="";
        id_fac= "";
        id_eva = "";
        num_preg = "";
        id_cri = "";
    }
    
    
    public void llenarEvaluaciones() {
        String mQuery = "";
        try {
            catevaluaciones = new CatEvaluaciones();
            evaluaciones = new ArrayList<>();

            mQuery = " SELECT id_eva, nom_eva FROM cat_eva";
                          
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
            System.out.println("Error en recuperacion de evaluaciones. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarFactores() {
        String mQuery = "";
        try {
            catfactores = new CatFactores();
            factores = new ArrayList<>();

            mQuery = "select id_fac, nom_fac from cat_fac order by id_fac;";
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
            System.out.println("Error en el llenado de Factores. " + e.getMessage() + " Query: " + mQuery);
        }
    }


    public void llenarCriterios() {
        String mQuery = "";
        try {
            catcriterios = new CatCriterios();
            criterios = new ArrayList<>();

            mQuery = "select id_cri, nom_cri, id_fac from cat_cri order by id_cri;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                criterios.add(new CatCriterios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3), ""
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Criterios. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarEvaluacionDetalle() {
        String mQuery = "";
        try {
            catevaluaciondetalle = new CatEvaluacionDetalle();
            evaluaciondetalle = new ArrayList<>();

            mQuery = "select det.id_eva_det, det.id_eva, det.num_preg, det.id_fac, det.id_cri, eva.nom_eva, fac.nomfac, cri.nomcri from "
                    + " cat_eva_det det inner join cat_fac fac on fac.id_fac = det.id_fac inner join "
                    + " cat_eva  eva on det.id_eva = eva.id_eva inner join "
                    + " cat_cri cri on det.id_cri = cri.id_cri order by cat_eva_det;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                evaluaciondetalle.add(new CatEvaluacionDetalle(
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
            System.out.println("Error en el llenado de Detalle evaluaciones. " + e.getMessage() + " Query: " + mQuery);
        }
    }
    
    
    public void nuevo() {
        id_eva_det ="";
        id_eva = "";
        id_fac = "";
        num_preg = "";
        id_cri = "";
        catevaluaciondetalle = new CatEvaluacionDetalle();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_eva_det)) {
                    mQuery = "select ifnull(max(id_eva_det),0)+1 as codigo from cat_eva_det;";
                    id_eva_det = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_eva_det (id_eva_det, id_eva, num_preg, id_cri) "
                            + "values (" + id_eva_det + "," + id_eva + "," + num_preg + "," + id_cri + ");";
                } else {
                    mQuery = "update cat_eva_det SET "
                            + " id_eva = " + id_eva + ", "
                            + " num_preg = " + num_preg + ", "
                            + " id_cri = " + id_cri + " "
                            + "WHERE id_eva_det = " + id_eva_det + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Detalle Evaluación", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Detalle Evaluación", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Detalle Evaluación " + e.getMessage() + " Query: " + mQuery);
            }
            llenarEvaluacionDetalle();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_eva_det) == false) {
            try {
                mQuery = "delete from cat_eva_det where id_eva_det=" + id_eva_det + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Registro", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Registro", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Registro. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarEvaluacionDetalle();
            nuevo();
        } else {
            addMessage("Eliminar Detalle Evaluación", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(id_cri) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Criterio.", 2);
        }
        
        if ("".equals(num_preg) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe indicar Numero de pregunta.", 2);
        }
              
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_eva_det) from cat_eva_det "
                + "where id_cri =" + id_cri + " AND id_eva =" + id_eva + ";")) == false && "".equals(id_eva_det)) {
            mValidar = false;
            addMessage("Validar Datos", "El Registro ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }
    
    public void onRowSelect(SelectEvent event) {
        id_eva_det =((CatEvaluacionDetalle) event.getObject()).getId_eva_det();
        id_eva = ((CatEvaluacionDetalle) event.getObject()).getId_eva();
        num_preg = ((CatEvaluacionDetalle) event.getObject()).getNum_preg();
        id_cri = ((CatEvaluacionDetalle) event.getObject()).getId_cri();
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
