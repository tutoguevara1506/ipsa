package paquetes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManEvaluacionPersonas implements Serializable {

    private static final long serialVersionUID = 8797678674711638L;
    @Inject
    Login cbean;
    private CatPersonas catpersonas;
    private List<CatPersonas> personas;
    private CatEvaluaciones catevaluaciones;
    private List<CatEvaluaciones> evaluaciones;
    private CatEvaluacionPersonas catevaluacionpersonas;
    private List<CatEvaluacionPersonas> evaluacionpersonas;
    private CatEvaluacionDetalle catevaluaciondetalle;
    private List<CatEvaluacionDetalle> evaluaciondetalle;
    private CatDetalleEvaluacionPersonas catdetalleevaluacionpersonas;
    private List<CatDetalleEvaluacionPersonas> detalleevaluacionpersonas;
       
    private String id_eva_per, id_per, id_eva, f_eva, per_eva, obs_eva, nom_per, nom_per_eva, calif, id_det_eva_per;
    private String id_fac, id_cri, id_cal, num_preg;
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
    
    public String getId_per() {
        return id_per;
    }

    public void setId_per(String id_persona) {
        this.id_per = id_persona;
    }

    public String getId_det_eva_per() {
        return id_det_eva_per;
    }

    public void setId_det_eva_per(String id_det_eva_per) {
        this.id_det_eva_per = id_det_eva_per;
    }

    public String getId_eva_per() {
        return id_eva_per;
    }

    public void setId_eva_per(String id_eva_per) {
        this.id_eva_per = id_eva_per;
    }

    public String getId_fac() {
        return id_fac;
    }

    public void setId_fac(String id_fac) {
        this.id_fac = id_fac;
    }

    public String getId_cri() {
        return id_cri;
    }

    public void setId_cri(String id_cri) {
        this.id_cri = id_cri;
    }

    public String getId_cal() {
        return id_cal;
    }

    public void setId_cal(String id_cal) {
        this.id_cal = id_cal;
    }

    public Date getDfevaluacion() {
        return dfevaluacion;
    }

    public void setDfevaluacion(Date dfevaluacion) {
        this.dfevaluacion = dfevaluacion;
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
    
    public void setEvaluacionpersonas(List<CatEvaluacionPersonas> evaluacionpersonas) {
        this.evaluacionpersonas = evaluacionpersonas;
    }

    public CatDetalleEvaluacionPersonas getCatdetalleevaluacionpersonas() {
        return catdetalleevaluacionpersonas;
    }

    public void setCatdetalleevaluacionpersonas(CatDetalleEvaluacionPersonas catdetalleevaluacionpersonas) {
        this.catdetalleevaluacionpersonas = catdetalleevaluacionpersonas;
    }

    public List<CatDetalleEvaluacionPersonas> getDetalleevaluacionpersonas() {
        return detalleevaluacionpersonas;
    }

    public void setDetalleevaluacionpersonas(List<CatDetalleEvaluacionPersonas> detalleevaluacionpersonas) {
        this.detalleevaluacionpersonas = detalleevaluacionpersonas;
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

    public String getCalif() {
        return calif;
    }

    public void setCalif(String calif) {
        this.calif = calif;
    }

    public String getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(String num_preg) {
        this.num_preg = num_preg;
    }
    
    public void iniciarventana() {        
        id_eva_per = "";
        id_per = "";
        id_eva = "";
        f_eva = "";
        per_eva = "";
        obs_eva = "";
        nom_per = "";
        nom_per_eva = "";
        llenarPersonas();
        llenarEvaluaciones();
        llenarEvaluacionPersonas();
    }
    
    public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        id_eva_per = "";
        id_per = "";
        id_eva = "";
        f_eva = "";
        dfevaluacion = new Date();
        per_eva = "";
        obs_eva = "";
        nom_per = "";
        nom_per_eva = "";
        llenarPersonas();
        catevaluacionpersonas = new CatEvaluacionPersonas();
    }

    public void cerrarventana() {
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
    
    public void cerrardetalle() {
        calif = "";
        id_det_eva_per = "";
        guardaObservacion();
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

            String mQuery = "SELECT tbl.id_eva_per, tbl.id_per, tbl.id_eva, DATE_FORMAT(tbl.f_eva, '%d/%m/%Y'), tbl.per_eva, tbl.obs_eva, concat(per.nombres,' ',per.apellidos), eva.nom_eva" +
                            " FROM ipsa.tbl_eva_per tbl inner join ipsa.cat_persona per on tbl.id_per = per.id_per "
                          + " inner join cat_eva eva on tbl.id_eva = eva.id_eva order by id_eva_per;";
            
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
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Evaluaciones en ManEvaluacionPersonas. " + e.getMessage());
        }
    }
    
    public void llenarEvaluacionDetalle() {
        String mQuery = "";
        try {
            catevaluaciondetalle = new CatEvaluacionDetalle();
            evaluaciondetalle = new ArrayList<>();

            mQuery = "select det.id_eva_det, det.id_eva, det.num_preg, det.id_fac, det.id_cri, eva.nom_eva, fac.nom_fac, cri.nom_cri from "
                    + " cat_eva_det det inner join cat_fac fac on fac.id_fac = det.id_fac inner join "
                    + " cat_eva  eva on det.id_eva = eva.id_eva inner join "
                    + " cat_cri cri on det.id_cri = cri.id_cri where det.id_eva = "+ id_eva +" order by id_eva_det;";
            
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
    
    public void llenarDetalleEvaluacionPersonas() {
        String mQuery = "";
        try {
            catdetalleevaluacionpersonas = new CatDetalleEvaluacionPersonas();
            detalleevaluacionpersonas = new ArrayList<>();

            mQuery = "SELECT det.id_det_eva_per, det.id_eva_per, det.id_eva, det.id_fac, det.num_preg, det.id_cri, det.id_cal, det.calif, eva.nom_eva, fac.nom_fac, cri.nom_cri " +
                     "FROM ipsa.det_eva_per det inner join cat_eva eva on det.id_eva = eva.id_eva " +
                     "inner join cat_fac fac on det.id_fac = fac.id_fac " +
                     "inner join cat_cri cri on det.id_cri = cri.id_cri " +
                     "where det.id_eva_per ="+catevaluacionpersonas.getId_eva_per()+" order by det.num_preg asc;";
            
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detalleevaluacionpersonas.add(new CatDetalleEvaluacionPersonas(
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
                        resVariable.getString(11)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Registro de Personas. " + e.getMessage() + " Query: " + mQuery);
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
                           
                    
                    mQuery = "insert into tbl_eva_per (id_eva_per, id_per, id_eva, f_eva, per_eva, obs_eva) "
                            + "values (" + id_eva_per + "," + id_per +  "," + id_eva + ", str_to_date('" + f_eva + "','%d/%m/%Y')" + ", " + per_eva + ",'" + obs_eva + "');";
                    
                   // insert de detalle de  evaluacion de una sola vez
                    
                    llenarEvaluacionDetalle();
                    
                    evaluaciondetalle.stream().forEach((evdet) -> {
                        String mQuery2="";

                        mQuery2 = "SELECT ifnull(max(id_det_eva_per),0)+1 as codigo FROM ipsa.det_eva_per;";
                        id_det_eva_per = mAccesos.strQuerySQLvariable(mQuery2);
                        id_eva = evdet.getId_eva();
                        id_fac = evdet.getId_fac();
                        num_preg= evdet.getNum_preg();
                        id_cri = evdet.getId_cri();
                                                
                        mQuery2 = "INSERT INTO ipsa.det_eva_per " +
                                  "(id_det_eva_per, id_eva_per, id_eva, id_fac, num_preg, id_cri, id_cal) " +
                                  "VALUES ("+id_det_eva_per+","+id_eva_per+","+id_eva+","+ id_fac+","+ num_preg+","+ id_cri+","+ id_cal+");";

                        mAccesos.dmlSQLvariable(mQuery2);
                       // System.out.println(usadd.getCod_usu());
                    });            
                    
                    //mQuery2 =              
                    
                    
                } else {
                    mQuery = "update tbl_eva_per SET "                           
                            + " id_per = '" + id_per + "',"
                            + " id_eva = '" + id_eva + "',"
                            + " f_eva = str_to_date('" + f_eva + "','%d/%m/%Y'),"
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
            llenarEvaluacionPersonas();
            nuevo();
        }
 
    }

   public void guardarDetalle() {
        
        try {
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
                        
            /*detalleevaluacionpersonas.stream().forEach((detevaper) -> {
                                        
                    String mQuery = "update ipsa.det_eva_per SET "                           
                    + " calif = " + detevaper.getCalif() + ""
                    + " WHERE id_det_eva_per = " + detevaper.getId_det_eva_per() + ";"; 
                    
                    mAccesos.dmlSQLvariable(mQuery);
                   // System.out.println(usadd.getCod_usu());
                });  */
            
            String mQuery = "update ipsa.det_eva_per SET "                           
                    + " calif = " + calif + ""
                    + " WHERE id_det_eva_per = " + id_det_eva_per + ";"; 
            
            mAccesos.dmlSQLvariable(mQuery);
            
         } catch (Exception e) {
            addMessage("Guardar Evaluacion Persona", "Error al momento de guardar la información. " + e.getMessage(), 2);
            System.out.println("Error al Guardar Evaluacion Persona. " + e.getMessage() + " Query: ");
        }
            //llenarEvaluacionPersonas();
            //nuevo(); 
    }
    
   public void guardaObservacion() {
       try {
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            String mQuery = "update ipsa.tbl_eva_per SET "                           
                    + " obs_eva = '" + obs_eva + "'"
                    + " WHERE id_eva_per = " + id_eva_per + ";"; 
                             
            mAccesos.dmlSQLvariable(mQuery);
            mAccesos.Desconectar();            
            
       } catch (Exception e) {
            addMessage("Guardar Observacion de evaluacion", "Error al momento de guardar la observacion. " + e.getMessage(), 2);
            System.out.println("Error al Guardar Observacion de Evaluacion. " + e.getMessage());
        }
  
   }
   
   public void eliminar() {
        String mQuery = "";
        String mQuery2 = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_per) == false) {
            try {
                mQuery2 = "delete from det_eva_per where id_eva_per = " + id_eva_per + ";";
                mQuery = "delete from tbl_eva_per where id_eva_per=" + id_eva_per + ";";
                mAccesos.dmlSQLvariable(mQuery2);
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Evaluacion", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Evaluacion", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Evaluación " + e.getMessage() + " Query: " + mQuery);
            }
            llenarEvaluacionPersonas();
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
    
    public void setCalificacion (String c) {
        calif = c;
    }
    public boolean validardatos() {
        boolean mValidar = true;
        
        if ("0".equals(id_per) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Nombre de la persona a evaluar.", 2);
        }
        
        if ("0".equals(id_eva) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Evaluacion.", 2);
        }
        
        if ("".equals(dfevaluacion) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Fecha de evaluacion.", 2);
        }
        
        if ("0".equals(per_eva) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Persona que evalua.", 2);
        }
        
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        id_eva_per = ((CatEvaluacionPersonas) event.getObject()).getId_eva_per();
        id_per = ((CatEvaluacionPersonas) event.getObject()).getId_per();
        id_eva = ((CatEvaluacionPersonas) event.getObject()).getId_eva();
        f_eva = ((CatEvaluacionPersonas) event.getObject()).getF_eva();
        per_eva = ((CatEvaluacionPersonas) event.getObject()).getId_per_eva();
        obs_eva = ((CatEvaluacionPersonas) event.getObject()).getObs_eva();        
        dfevaluacion = format.parse(f_eva);
    }
    
    public void onRowDetalle(SelectEvent event) {      
        id_det_eva_per = ((CatDetalleEvaluacionPersonas) event.getObject()).getId_det_eva_per();
        id_eva_per = ((CatDetalleEvaluacionPersonas) event.getObject()).getId_eva_per();
        id_eva = ((CatDetalleEvaluacionPersonas) event.getObject()).getId_eva();
        id_fac = ((CatDetalleEvaluacionPersonas) event.getObject()).getId_fac();
        id_cri = ((CatDetalleEvaluacionPersonas) event.getObject()).getId_cri(); 
        id_cal =  ((CatDetalleEvaluacionPersonas) event.getObject()).getId_cal(); 
        calif =  ((CatDetalleEvaluacionPersonas) event.getObject()).getCalif();
        guardarDetalle();
    }
    
    public void imprimir() {
        guardaObservacion();
        try {
            byte[] content;
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            content = imprimirEvaluacion();
            response.setContentType("application/pdf");
            response.setContentLength(content == null ? 0 : content.length);
            response.getOutputStream().write(content);
            response.getOutputStream().flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (SQLException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JRException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ManMaestroMan.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public byte[] imprimirEvaluacion() throws SQLException, JRException {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String reportPath = ctx.getRealPath(File.separator + "reportes" + File.separator);
        HashMap param = new HashMap();
        param.put("id_eva_per", id_eva_per);
        //param.put("cod_man", cod_man);

        Accesos racc = new Accesos();
        return JasperRunManager.runReportToPdf(reportPath + File.separator + "evaluacionDesempeno.jasper", param, racc.Conectar());
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