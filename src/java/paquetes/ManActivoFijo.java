package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
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
public class ManActivoFijo implements Serializable {

    private static final long serialVersionUID = 3797678674716638L;
    @Inject
    Login cbean;
    private CatActivoFijo catactivofijo;
    private List<CatActivoFijo> activofijo;
    private CatTipoActivo cattipoactivo;
    private List<CatTipoActivo> tipoactivo;
    private CatDepartamentos catdepartamentos;
    private List<CatDepartamentos> departamentos;
    private String id_act_fij, id_tip_act, desc_equ, fecha_adquisicion, valor_adqui, id_depto, dist_gast_porc, seccion, id_estado, tiempo_deprecia, cuota_mes_deprecia, porcentaje_deduc, porcentaje_no_deduc, serie_equ, modelo_equ, no_inventario, observacion, codigo_equ;
    private Date dfadqu;
    
    public ManActivoFijo() {
    }

      
    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dfadqu = Date.from(Instant.now());
        id_act_fij = "";
        id_tip_act = "";
        desc_equ = "";
        fecha_adquisicion = format.format(dfadqu);
        valor_adqui = "";
        id_depto = "";
        dist_gast_porc = "";
        seccion = "";
        id_estado = "";
        tiempo_deprecia = "";
        cuota_mes_deprecia = "";
        porcentaje_deduc = "";
        porcentaje_no_deduc = "";
        serie_equ = "";
        modelo_equ = "";
        no_inventario = "";
        observacion = "";
        codigo_equ = "";
        llenarActivoFijo();
        llenarTipoActivo();
        llenarDepartamentos();
    }

    public void cerrarventana() {
        id_act_fij = "";
        id_tip_act = "";
        desc_equ = "";
        fecha_adquisicion = "";
        valor_adqui = "";
        id_depto = "";
        dist_gast_porc = "";
        seccion = "";
        id_estado = "";
        tiempo_deprecia = "";
        cuota_mes_deprecia = "";
        porcentaje_deduc = "";
        porcentaje_no_deduc = "";
        serie_equ = "";
        modelo_equ = "";
        no_inventario = "";
        observacion = "";
        codigo_equ = "";
        activofijo = new ArrayList<>();
        tipoactivo = new ArrayList<>();
    }

    public void llenarActivoFijo() {
        String mQuery = "";
        try {
            catactivofijo = new CatActivoFijo();
            activofijo = new ArrayList<>();

            mQuery = "SELECT id_act_fij, id_tip_act, desc_equ, DATE_FORMAT(fecha_adquisicion, '%d/%m/%Y'), valor_adqui, id_depto, dist_gast_porc, seccion, id_estado, tiempo_deprecia, cuota_mes_deprecia, porcentaje_deduc, porcentaje_no_deduc, serie_equ, modelo_equ, no_inventario, observacion, codigo_equ " +
                     "FROM ipsa.cat_act_fij ORDER BY id_act_fij;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                activofijo.add(new CatActivoFijo(
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
                        resVariable.getString(16),
                        resVariable.getString(17),
                        resVariable.getString(18)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Lista de Activos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    
    public void llenarTipoActivo() {
        String mQuery = "";
        try {
            cattipoactivo = new CatTipoActivo();
            tipoactivo = new ArrayList<>();

            mQuery = "SELECT id_tip_act, des_tip_act FROM ipsa.cat_tip_act ORDER BY id_tip_act;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                tipoactivo.add(new CatTipoActivo(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Tipos de Activo. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dfadqu = Date.from(Instant.now());
        id_act_fij = "";
        id_tip_act = "";
        desc_equ = "";
        fecha_adquisicion = format.format(dfadqu);
        valor_adqui = "";
        id_depto = "";
        dist_gast_porc = "";
        seccion = "";
        id_estado = "";
        tiempo_deprecia = "";
        cuota_mes_deprecia = "";
        porcentaje_deduc = "";
        porcentaje_no_deduc = "";
        serie_equ = "";
        modelo_equ = "";
        no_inventario = "";
        observacion = "";
        codigo_equ = "";
        catactivofijo = new CatActivoFijo();
        llenarTipoActivo();
        llenarDepartamentos();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_act_fij)) {
                    mQuery = "select ifnull(max(id_act_fij),0)+1 as codigo from cat_act_fij;";
                    id_act_fij = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "INSERT INTO ipsa.cat_act_fij (id_act_fij, id_tip_act, desc_equ, fecha_adquisicion, valor_adqui, id_depto, dist_gast_porc, "+
                             "seccion, id_estado, tiempo_deprecia, cuota_mes_deprecia, porcentaje_deduc, porcentaje_no_deduc, serie_equ, modelo_equ, no_inventario, observacion, codigo_equ) " +
                             "VALUES ("+id_act_fij+","+id_tip_act+",'"+desc_equ+"',str_to_date('" + fecha_adquisicion + "','%d/%m/%Y'),"+ valor_adqui+ ","+ id_depto+",'"+ dist_gast_porc+"','"+
                                        seccion +"',"+ id_estado+","+ tiempo_deprecia+ ","+ cuota_mes_deprecia+ ","+ porcentaje_deduc+ ","+ porcentaje_no_deduc+",'"+
                                        serie_equ+ "','"+ modelo_equ+ "','"+ no_inventario + "','" + observacion + "','"+codigo_equ+"'); ";
                } else {
                    mQuery =  "UPDATE ipsa.cat_act_fij SET "
                            + "id_tip_act = " + id_tip_act + "," 
                            + "desc_equ = '" + desc_equ + "',"
                            + "fecha_adquisicion = str_to_date('" + fecha_adquisicion + "','%d/%m/%Y'),"
                            + "valor_adqui = "+ valor_adqui + ","
                            + "id_depto = " + id_depto + "," 
                            + "dist_gast_porc = '" + dist_gast_porc + "',"
                            + "seccion = '" + seccion + "',"
                            + "id_estado = " + id_estado + ","
                            + "tiempo_deprecia = " + tiempo_deprecia + ","
                            + "cuota_mes_deprecia = " + cuota_mes_deprecia + ","
                            + "porcentaje_deduc = " + porcentaje_deduc + ","
                            + "porcentaje_no_deduc = "+ porcentaje_no_deduc + ","
                            + "serie_equ = '" + serie_equ + "',"
                            + "modelo_equ = '" + modelo_equ + "',"
                            + "no_inventario = '" + no_inventario + "',"
                            + "observacion = '" + observacion +"',"
                            + "codigo_equ = '" + codigo_equ + "' " 
                            + "WHERE id_act_fij = " + id_act_fij + ";";
                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Cargo", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Cargo", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarActivoFijo();
            nuevo();
        }
    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_act_fij) == false) {
            try {
                mQuery = "delete from ipsa.cat_act_fij where id_act_fij=" + id_act_fij + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Registro de activo fijo", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar REgistro de activo fijo", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Activo fijo. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarActivoFijo();
            nuevo();
        } else {
            addMessage("Eliminar Cargos", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        String mensaje = "Debe Ingresar ";
                
        if ("".equals(id_tip_act) == true) {
            mValidar = false;
            mensaje= mensaje + "- Tipo de Activo. \n";
        }
        
        if ("".equals(desc_equ) == true) {
            mValidar = false;
            mensaje= mensaje + "- Descripcion de Activo. \n";
        }
        
        if ("".equals(dfadqu) == true) {
            mValidar = false;
            mensaje= mensaje + "- Fecha de adquisicion. \n";
        }
        
        if ("".equals(valor_adqui) == true) {
            mValidar = false;
            mensaje= mensaje + "- Valor de adquisicion. \n";
        }
        
        if ("".equals(id_depto) == true) {
            mValidar = false;
            mensaje= mensaje +  "- Departamento. \n";
        }
        
        if ("".equals(tiempo_deprecia) == true) {
            mValidar = false;
            mensaje= mensaje + "- Tiempo de depreciacion. \n";
        }
        
        if ("".equals(porcentaje_deduc) == true) {
            mValidar = false;
            mensaje= mensaje + "- Porcentaje deducible. \n";
        }
        
        if ("".equals(porcentaje_no_deduc) == true) {
            mValidar = false;
            mensaje= mensaje + "- Porcentaje no deducible. \n";
        }
        
        if ("".equals(serie_equ) == true) {
            mValidar = false;
            mensaje= mensaje + "- Numero de serie. \n";
        }
        
        if ("".equals(modelo_equ) == true) {
            mValidar = false;
            mensaje= mensaje + "- Modelo. \n";
        }
                
        if ("".equals(no_inventario) == true) {
            mValidar = false;
            mensaje= mensaje + "- Numero de inventario. \n";
        }
        
        if ("".equals(codigo_equ) == true) {
            mValidar = false;
            mensaje= mensaje + "- Codigo de equipo. \n";
        }
        
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(serie_equ) from cat_act_fij "
                + "where upper(serie_equ)='" + serie_equ.toUpperCase() + "';")) == false && "".equals(serie_equ)) {
            mValidar = false;
            addMessage("Validar Datos", "El Numero de serie ya existe.", 2);
        }
        maccesos.Desconectar();
        
        if (mValidar){            
            return mValidar;
        }
        else
        {
            System.out.println(mensaje);
            addMessage("Validar Datos", mensaje, 2);
            return mValidar;
        }

    }

    public void onRowSelect(SelectEvent event) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        id_act_fij = ((CatActivoFijo) event.getObject()).getId_act_fij();
        id_tip_act = ((CatActivoFijo) event.getObject()).getId_tip_act();
        desc_equ =   ((CatActivoFijo) event.getObject()).getDesc_equ();
        fecha_adquisicion = ((CatActivoFijo) event.getObject()).getFecha_adquisicion();
        valor_adqui = ((CatActivoFijo) event.getObject()).getValor_adqui();
        id_depto = ((CatActivoFijo) event.getObject()).getId_depto();
        dist_gast_porc =((CatActivoFijo) event.getObject()).getDist_gast_porc();
        seccion = ((CatActivoFijo) event.getObject()).getSeccion();
        id_estado = ((CatActivoFijo) event.getObject()).getId_estado();
        tiempo_deprecia = ((CatActivoFijo) event.getObject()).getTiempo_deprecia();
        cuota_mes_deprecia = ((CatActivoFijo) event.getObject()).getCuota_mes_deprecia();
        porcentaje_deduc = ((CatActivoFijo) event.getObject()).getPorcentaje_deduc();
        porcentaje_no_deduc = ((CatActivoFijo) event.getObject()).getPorcentaje_no_deduc();
        serie_equ = ((CatActivoFijo) event.getObject()).getSerie_equ();
        modelo_equ = ((CatActivoFijo) event.getObject()).getModelo_equ();
        no_inventario = ((CatActivoFijo) event.getObject()).getNo_inventario();
        observacion = ((CatActivoFijo) event.getObject()).getObservacion();
        codigo_equ = ((CatActivoFijo) event.getObject()).getCodigo_equ();
        dfadqu = format.parse(fecha_adquisicion);
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
    
    public void dateSelected (SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fecha_adquisicion = format.format(date);
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
    
    public void evaluaCuota () {
        if ("true".equals(dist_gast_porc)){
            try{
                Double cuota;
                Double porcentaje = Double.parseDouble(porcentaje_deduc)/100;
                Integer porcentaje_no = 100 - Integer.parseInt(porcentaje_deduc);
                porcentaje_no_deduc = String.valueOf(porcentaje_no); 
                cuota = (Double.parseDouble(valor_adqui) * porcentaje)/Integer.parseInt(tiempo_deprecia);
                cuota_mes_deprecia = String.format("%1$,.2f", cuota);
                System.out.println(cuota);
            }
            catch (Exception e){
                System.out.println("Error el calculo-- Division entre cero. " + e.getMessage());
            }
            
        } 
    }
    // SETTERS y GETTERS

    public String getId_act_fij() {
        return id_act_fij;
    }

    public void setId_act_fij(String id_act_fij) {
        this.id_act_fij = id_act_fij;
    }

    public String getId_tip_act() {
        return id_tip_act;
    }

    public void setId_tip_act(String id_tip_act) {
        this.id_tip_act = id_tip_act;
    }

    public String getDesc_equ() {
        return desc_equ;
    }

    public void setDesc_equ(String desc_equ) {
        this.desc_equ = desc_equ;
    }

    public String getFecha_adquisicion() {
        return fecha_adquisicion;
    }

    public void setFecha_adquisicion(String fecha_adquisicion) {
        this.fecha_adquisicion = fecha_adquisicion;
    }

    public String getValor_adqui() {
        return valor_adqui;
    }

    public void setValor_adqui(String valor_adqui) {
        this.valor_adqui = valor_adqui;
    }

    public String getId_depto() {
        return id_depto;
    }

    public void setId_depto(String id_depto) {
        this.id_depto = id_depto;
    }

    public String getDist_gast_porc() {
        return dist_gast_porc;
    }

    public void setDist_gast_porc(String dist_gast_porc) {
        this.dist_gast_porc = dist_gast_porc;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }

    public String getTiempo_deprecia() {
        return tiempo_deprecia;
    }

    public void setTiempo_deprecia(String tiempo_deprecia) {
        this.tiempo_deprecia = tiempo_deprecia;
    }

    public String getCuota_mes_deprecia() {
        return cuota_mes_deprecia;
    }

    public void setCuota_mes_deprecia(String cuota_mes_deprecia) {
        this.cuota_mes_deprecia = cuota_mes_deprecia;
    }

    public String getPorcentaje_deduc() {
        return porcentaje_deduc;
    }

    public void setPorcentaje_deduc(String porcentaje_deduc) {
        this.porcentaje_deduc = porcentaje_deduc;
    }

    public String getPorcentaje_no_deduc() {
        return porcentaje_no_deduc;
    }

    public void setPorcentaje_no_deduc(String porcentaje_no_deduc) {
        this.porcentaje_no_deduc = porcentaje_no_deduc;
    }

    public String getSerie_equ() {
        return serie_equ;
    }

    public void setSerie_equ(String serie_equ) {
        this.serie_equ = serie_equ;
    }

    public String getModelo_equ() {
        return modelo_equ;
    }

    public void setModelo_equ(String modelo_equ) {
        this.modelo_equ = modelo_equ;
    }

    public String getNo_inventario() {
        return no_inventario;
    }

    public void setNo_inventario(String no_inventario) {
        this.no_inventario = no_inventario;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getCodigo_equ() {
        return codigo_equ;
    }

    public void setCodigo_equ(String codigo_equ) {
        this.codigo_equ = codigo_equ;
    }

    public CatActivoFijo getCatactivofijo() {
        return catactivofijo;
    }

    public void setCatactivofijo(CatActivoFijo catactivofijo) {
        this.catactivofijo = catactivofijo;
    }

    public List<CatActivoFijo> getActivofijo() {
        return activofijo;
    }

    public void setActivofijo(List<CatActivoFijo> activofijo) {
        this.activofijo = activofijo;
    }

    public CatTipoActivo getCattipoactivo() {
        return cattipoactivo;
    }

    public void setCattipoactivo(CatTipoActivo cattipoactivo) {
        this.cattipoactivo = cattipoactivo;
    }

    public List<CatTipoActivo> getTipoactivo() {
        return tipoactivo;
    }

    public void setTipoactivo(List<CatTipoActivo> tipoactivo) {
        this.tipoactivo = tipoactivo;
    }

    public Date getDfadqu() {
        return dfadqu;
    }

    public void setDfadqu(Date dfadqu) {
        this.dfadqu = dfadqu;
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
   
}
