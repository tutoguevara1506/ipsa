package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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

public class ManSolicitudes implements Serializable {

    private static final long serialVersionUID = 8791114786471668L;
    @Inject
    Login cbean;
    private CatSolicitudes catmaestro;
    private List<CatSolicitudes> maestro;
    private CatSolicitudesDetalle catdetalles;
    private List<CatSolicitudesDetalle> detalles;
    private CatUsuarios catusuarios;
    private List<CatUsuarios> usuarios;
    private CatPiezas catpiezas;
    private List<CatPiezas> piezas;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatDepartamentos catdepartamentos;
    private List<CatDepartamentos> departamentos;

    private String id_mae, fec_sol, nom_usu, cod_dep, det_uso, cod_maq, nom_apr, det_sta, det_obs;
    private String id_det, cod_ite, des_ite, det_can, det_sta_det;
    private String cod_pai;

    public CatSolicitudes getCatmaestro() {
        return catmaestro;
    }

    public void setCatmaestro(CatSolicitudes catmaestro) {
        this.catmaestro = catmaestro;
    }

    public List<CatSolicitudes> getMaestro() {
        return maestro;
    }

    public void setMaestro(List<CatSolicitudes> maestro) {
        this.maestro = maestro;
    }

    public CatSolicitudesDetalle getCatdetalles() {
        return catdetalles;
    }

    public void setCatdetalles(CatSolicitudesDetalle catdetalles) {
        this.catdetalles = catdetalles;
    }

    public List<CatSolicitudesDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<CatSolicitudesDetalle> detalles) {
        this.detalles = detalles;
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

    public CatPiezas getCatpiezas() {
        return catpiezas;
    }

    public void setCatpiezas(CatPiezas catpiezas) {
        this.catpiezas = catpiezas;
    }

    public List<CatPiezas> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<CatPiezas> piezas) {
        this.piezas = piezas;
    }

    public CatEquipos getCatequipos() {
        return catequipos;
    }

    public void setCatequipos(CatEquipos catequipos) {
        this.catequipos = catequipos;
    }

    public List<CatEquipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<CatEquipos> equipos) {
        this.equipos = equipos;
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

    public String getId_mae() {
        return id_mae;
    }

    public void setId_mae(String id_mae) {
        this.id_mae = id_mae;
    }

    public String getFec_sol() {
        return fec_sol;
    }

    public void setFec_sol(String fec_sol) {
        this.fec_sol = fec_sol;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }

    public String getDet_uso() {
        return det_uso;
    }

    public void setDet_uso(String det_uso) {
        this.det_uso = det_uso;
    }

    public String getCod_maq() {
        return cod_maq;
    }

    public void setCod_maq(String cod_maq) {
        this.cod_maq = cod_maq;
    }

    public String getNom_apr() {
        return nom_apr;
    }

    public void setNom_apr(String nom_apr) {
        this.nom_apr = nom_apr;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getId_det() {
        return id_det;
    }

    public void setId_det(String id_det) {
        this.id_det = id_det;
    }

    public String getCod_ite() {
        return cod_ite;
    }

    public void setCod_ite(String cod_ite) {
        this.cod_ite = cod_ite;
    }

    public String getDes_ite() {
        return des_ite;
    }

    public void setDes_ite(String des_ite) {
        this.des_ite = des_ite;
    }

    public String getDet_can() {
        return det_can;
    }

    public void setDet_can(String det_can) {
        this.det_can = det_can;
    }

    public String getDet_sta_det() {
        return det_sta_det;
    }

    public void setDet_sta_det(String det_sta_det) {
        this.det_sta_det = det_sta_det;
    }

    public String getDet_obs() {
        return det_obs;
    }

    public void setDet_obs(String det_obs) {
        this.det_obs = det_obs;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public void iniciarventana() {
        Accesos acc = new Accesos();
        /*Encabezado*/
        id_mae = "";
        acc.Conectar();
        fec_sol = acc.strQuerySQLvariable("select date_format(now(),'%d/%m/%Y');");
        acc.Desconectar();
        nom_usu = cbean.getCod_usu();
        cod_dep = cbean.getCod_dep();
        det_uso = "";
        cod_maq = "";
        nom_apr = "0";
        det_sta = "ESPERA APROBACIÓN";
        det_obs = "";
        maestro = new ArrayList<>();
        /*Detalles*/
        id_det = "";
        cod_ite = "0";
        des_ite = "";
        det_can = "";
        det_sta_det = "PENDIENTE";
        cod_pai = cbean.getCod_pai();
        detalles = new ArrayList<>();
        llenarPiezas();
        llenarUsuarios();
        llenarEquipos();
        llenarDepartamentos();
    }

    public void cerrarventana() {
        id_mae = "";
        fec_sol = "";
        nom_usu = "";
        cod_dep = "";
        det_uso = "";
        cod_maq = "";
        nom_apr = "";
        det_sta = "";
        det_obs = "";
        /*Detalles*/
        id_det = "";
        cod_ite = "";
        des_ite = "";
        det_can = "";
        det_sta_det = "";
        cod_pai = "";
        usuarios = new ArrayList<>();
        equipos = new ArrayList<>();
        departamentos = new ArrayList<>();
        maestro = new ArrayList<>();
        detalles = new ArrayList<>();
    }

    public void iniciarventanasearch() {
        llenarMaestro();
    }

    public void cerrarventanasearch() {
        catmaestro = new CatSolicitudes();
        maestro = new ArrayList<>();
    }

    public void llenarUsuarios() {
        try {
            catusuarios = new CatUsuarios();
            usuarios = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, "
                    + "usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, "
                    + "ifnull(dep.nom_dep,'') as nom_dep "
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
            System.out.println("Error en el llenado de Usuarios en Solicitudes. " + e.getMessage());
        }
    }

    public void llenarEquipos() {
        String mQuery = "";
        try {
            catequipos = new CatEquipos();
            equipos = new ArrayList<>();

            mQuery = "select equ.cod_equ, equ.cod_mar,equ.cod_ref, "
                    + "equ.nom_equ, equ.des_equ,mar.nom_mar,equ.det_ima "
                    + "from cat_equ as equ "
                    + "left join cat_mar as mar on mar.id_mar = equ.cod_mar "
                    + "order by equ.cod_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                equipos.add(new CatEquipos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Equipos en Solicitudes. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarDepartamentos() {
        try {
            catdepartamentos = new CatDepartamentos();
            departamentos = new ArrayList<>();

            String mQuery = "select cod_dep, cod_pai, nom_dep "
                    + "from cat_dep where cod_pai = " + cod_pai + " order by cod_dep;";
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
            System.out.println("Error en el llenado de Departamentos en Solicitudes. " + e.getMessage());
        }
    }
    
    public void llenarPiezas() {
        String mQuery = "";
        try {
            catpiezas = new CatPiezas();
            piezas = new ArrayList<>();

            mQuery = "select pie.cod_pie,pie.cod_ref,pie.cod_equ,"
                    + "pie.nom_pie,pie.des_pie,equ.nom_equ,pie.cod_cat,pie.det_ima,pie.vid_uti "
                    + "from cat_pie as pie "
                    + "left join cat_equ as equ on equ.cod_equ=pie.cod_equ "
                    + "order by pie.cod_pie;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezas.add(new CatPiezas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Piezas en Solicitudes. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarMaestro() {
        try {
            catmaestro = new CatSolicitudes();
            maestro = new ArrayList<>();

            String mQuery = "select mae.id_mae, date_format(mae.fec_sol,'%d/%m/%Y') as fec_sol, "
                    + "mae.nom_usu, "
                    + "mae.cod_dep, mae.det_uso, mae.cod_maq, mae.nom_apr, mae.det_sta,"
                    + "dep.nom_dep,maq.nom_equ,mae.det_obs "
                    + "FROM mae_sol as mae "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep "
                    + "left join cat_equ as maq on mae.cod_maq = maq.cod_equ "
                    + "left join cat_usu as usu on mae.nom_usu = usu.cod_usu "
                    + "where "
                    + "mae.nom_usu = " + cbean.getCod_usu() + " "
                    + "and mae.det_sta = 'ESPERA APROBACIÓN' "
                    + "order by mae.fec_sol desc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                maestro.add(new CatSolicitudes(
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
            System.out.println("Error en el llenado Maestro en Crear Solicitud. " + e.getMessage());
        }
    }

    public void llenarDetalles() {
        try {
            catdetalles = new CatSolicitudesDetalle();
            detalles = new ArrayList<>();

            String mQuery = "select id_mae, id_det, cod_ite,des_ite, det_can, det_sta FROM det_sol "
                    + "where id_mae = " + id_mae + " order by id_det asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detalles.add(new CatSolicitudesDetalle(
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
            System.out.println("Error en el llenado Detalles en Crear Solicitudes. " + e.getMessage());
        }
    }

    public void nuevo() {
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        id_mae = "";
        fec_sol = dia + "/" + (mes + 1) + "/" + año;
        nom_usu = cbean.getCod_usu();
        cod_dep = cbean.getCod_dep();
        det_uso = "";
        cod_maq = "";
        nom_apr = "0";
        det_sta = "ESPERA APROBACIÓN";
        det_obs = "";
        /*Detalles*/
        id_det = "";
        cod_ite = "0";
        des_ite = "";
        det_can = "";
        det_sta_det = "PENDIENTE";
        cod_pai = cbean.getCod_pai();
        catdetalles = new CatSolicitudesDetalle();
        detalles = new ArrayList<>();
    }

    public void agregardetalle() {
        if (validardetalle()) {
            int correlativo = 0, insert = 0;
            try {
                for (int i = 0; i < detalles.size(); i++) {
                    if (Integer.valueOf(detalles.get(i).getId_det()) > correlativo) {
                        correlativo = Integer.valueOf(detalles.get(i).getId_det());
                    }
                }

                if (insert == 0 && detalles.size() < 17) {
                    detalles.add(new CatSolicitudesDetalle(
                            "",
                            String.valueOf(correlativo + 1),
                            cod_ite,
                            des_ite,
                            det_can,
                            det_sta_det
                    ));

                    id_det = "";
                    cod_ite = "";
                    des_ite = "";
                    det_can = "";
                    det_sta_det = "PENDIENTE";

                } else {
                    addMessage("Validar Datos", "El número máximo de Items permitidos por formulario ha sido alcanzado.", 2);
                }

            } catch (Exception e) {
                System.out.println("Error en agregardetalle de ManSolicitudes." + e.getMessage());
            }
        }

    }

    public boolean validardetalle() {
        boolean mvalidar = true;
        if ("".equals(des_ite)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Descripción.", 2);
        }
        if ("".equals(det_can)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad mayor que Cero.", 2);
        } else {
            if (Double.valueOf(det_can) <= 0) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Ingresar una Cantidad mayor que Cero.", 2);
            }
        }
        return mvalidar;

    }

    public void guardar() {
        String mQuery = "";
        try {
            if (validarg()) {
                Accesos acc = new Accesos();
                acc.Conectar();
                if ("".equals(id_mae)) {
                    mQuery = "select ifnull(max(id_mae),0)+1 as codigo from mae_sol;";
                    id_mae = acc.strQuerySQLvariable(mQuery);
                    mQuery = "insert into mae_sol (id_mae, fec_sol, nom_usu, cod_dep, det_uso, "
                            + "cod_maq, nom_apr, det_sta,det_obs) "
                            + "values (" + id_mae + ",str_to_date('" + fec_sol + "','%d/%m/%Y')," + nom_usu
                            + "," + cod_dep + ",'" + det_uso + "'," + cod_maq + "," + nom_apr + ",'" + det_sta
                            + "','" + det_obs + "');";
                } else {
                    mQuery = "update mae_sol set "
                            + "fec_sol=str_to_date('" + fec_sol + "','%d/%m/%Y %h:%i'),"
                            + "nom_usu=" + nom_usu + ","
                            + "cod_dep=" + cod_dep + ","
                            + "det_uso='" + det_uso + "',"
                            + "cod_maq= " + cod_maq + " "
                            + "nom_apr= '" + nom_apr + "',"
                            + "det_sta='" + det_sta + "', "
                            + "det_obs='" + det_obs + "' "
                            + "where id_mae=" + id_mae + ";";
                }
                acc.dmlSQLvariable(mQuery);
                mQuery = "delete from det_sol where id_mae=" + id_mae + ";";
                acc.dmlSQLvariable(mQuery);
                String mValores = "";
                int mCorrela = 1;
                for (int i = 0; i < detalles.size(); i++) {
                    mValores = mValores + ",(" + id_mae + "," + mCorrela + "," 
                            + detalles.get(i).getCod_ite() + ",'" 
                            + detalles.get(i).getDes_ite() + "'," 
                            + detalles.get(i).getDet_can() + ",'" 
                            + detalles.get(i).getDet_sta() + "')";
                    mCorrela = mCorrela + 1;
                }
                mValores = mValores.substring(1);
                mQuery = "insert into det_sol(id_mae,id_det,cod_ite,des_ite,det_can,det_sta) values " + mValores + ";";
                acc.dmlSQLvariable(mQuery);
                acc.Desconectar();
                nuevo();
                addMessage("Guardar Solicitud", "Información almacenada con Éxito.", 1);
            }
        } catch (Exception e) {
            System.out.println("Error en guardar Solicitud." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public boolean validarg() {
        boolean valida = true;
        /*try {
         SimpleDateFormat ft = new SimpleDateFormat("dd/MM/yyyy hh:mm");
         ft.format(fec_sol);
         } catch (Exception e) {
         valida = false;
         addMessage("Validar Datos", "Debe Proporcionar una Fecha de Solicitud en formato dd/mm/yyyy", 2);
         }*/
        if (detalles.isEmpty()) {
            valida = false;
            addMessage("Validar Datos", "Debe Ingresar por lo menos un Item al Detalle.", 2);
        }

        return valida;
    }

    public void eliminardetalle() {
        if ("".equals(id_det)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un detalle para remover.", 2);
        } else {
            for (int i = 0; i < detalles.size(); i++) {
                if (id_det.equals(detalles.get(i).getId_det())) {
                    detalles.remove(i);
                }
            }
            id_det = "";
            cod_ite = "";
            des_ite = "";
            det_can = "";
            det_sta_det = "PENDIENTE";
        }
    }

    public void onRowSelect(SelectEvent event) {
        id_mae = ((CatSolicitudesDetalle) event.getObject()).getId_mae();
        id_det = ((CatSolicitudesDetalle) event.getObject()).getId_det();
        cod_ite = ((CatSolicitudesDetalle) event.getObject()).getCod_ite();
        des_ite = ((CatSolicitudesDetalle) event.getObject()).getDes_ite();
        det_can = ((CatSolicitudesDetalle) event.getObject()).getDet_can();
        det_sta_det = ((CatSolicitudesDetalle) event.getObject()).getDet_sta();
    }

    public void onRowSelectsearch(SelectEvent event) {
        id_mae = ((CatSolicitudes) event.getObject()).getId_mae();
        fec_sol = ((CatSolicitudes) event.getObject()).getFec_sol();
        nom_usu = ((CatSolicitudes) event.getObject()).getNom_usu();
        cod_dep = ((CatSolicitudes) event.getObject()).getCod_dep();
        det_uso = ((CatSolicitudes) event.getObject()).getDet_uso();
        cod_maq = ((CatSolicitudes) event.getObject()).getCod_maq();
        nom_apr = ((CatSolicitudes) event.getObject()).getNom_apr();
        det_sta = ((CatSolicitudes) event.getObject()).getDet_sta();
        det_obs = ((CatSolicitudes) event.getObject()).getDet_obs();
        llenarDetalles();
        catmaestro = new CatSolicitudes();
        maestro = new ArrayList<>();
        RequestContext.getCurrentInstance().update("frmSearch");
        RequestContext.getCurrentInstance().update("frmSol");
        RequestContext.getCurrentInstance().execute("PF('wSearch').hide()");

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
