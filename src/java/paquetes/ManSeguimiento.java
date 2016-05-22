package paquetes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.UnselectEvent;

@Named
@ConversationScoped

public class ManSeguimiento extends Conexion implements Serializable {

    private static final long serialVersionUID = 8791111238764288L;
    @Inject
    Login cbean;
    private List<CatCotizaciones> cotizaciones;
    private CatCotizaciones catcotizaciones;
    private List<CatProveedores> proveedores;
    private CatCompras catcompras;
    private List<CatCompras> compras;
    private List<CatHistorico> historico;
    private List<CatUsuarios> recibidores;
    private List<CatUsuarios> aprobadores;
    private List<CatPaises> paises;
    private List<CatBodegas> bodegas;
    private List<CatUbicaciones> ubicaciones;
    private CatSolicitudes catmaestro;
    private List<CatSolicitudes> maestro;
    private CatSolicitudesDetalle catdetalles;
    private List<CatSolicitudesDetalle> detalles;
    private CatSolicitudes catmaestroaprobar;
    private List<CatSolicitudes> maestroaprobar;
    private CatSolicitudesDetalle catdetallesaprobar;
    private List<CatSolicitudesDetalle> detallesaprobar;
    private CatSolicitudes catmaestrohistoria;
    private List<CatSolicitudes> maestrohistoria;
    private CatSolicitudesDetalleHistoria catdetalleshistoria;
    private List<CatSolicitudesDetalleHistoria> detalleshistoria;

    private String cot_cod_cor, cot_cod_pro, cot_det_cot;
    private String com_cod_cor, com_cod_pro, com_det_com, com_can_com;
    private String cod_mae, det_sta, fec_cie;
    private String det_can_sol, det_can_ent, det_can_pen, mdt_cod_det, mdt_cod_ite, mdt_des_ite, mdt_non_sto, mdt_det_sta, mdt_cos_uni, det_fec_cie;
    private String apr_cod_mae, his_cod_mae, his_cod_det;
    private String tabindex, tabindex2, rbhistoria;
    private Date mfecha, mfecha2;
    private String aprobador, recibidapor, cod_pai, cod_alt, idbuscar, destino, cod_bod, des_ubi, booledit;

    private String nombrereporte, nombreexportar;
    private Map<String, Object> parametros;

    public ManSeguimiento() {
    }

    public List<CatProveedores> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<CatProveedores> proveedores) {
        this.proveedores = proveedores;
    }

    public CatCotizaciones getCatcotizaciones() {
        return catcotizaciones;
    }

    public void setCatcotizaciones(CatCotizaciones catcotizaciones) {
        this.catcotizaciones = catcotizaciones;
    }

    public List<CatCotizaciones> getCotizaciones() {
        return cotizaciones;
    }

    public void setCotizaciones(List<CatCotizaciones> cotizaciones) {
        this.cotizaciones = cotizaciones;
    }

    public CatCompras getCatcompras() {
        return catcompras;
    }

    public void setCatcompras(CatCompras catcompras) {
        this.catcompras = catcompras;
    }

    public List<CatCompras> getCompras() {
        return compras;
    }

    public void setCompras(List<CatCompras> compras) {
        this.compras = compras;
    }

    public List<CatHistorico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<CatHistorico> historico) {
        this.historico = historico;
    }

    public List<CatUsuarios> getRecibidores() {
        return recibidores;
    }

    public void setRecibidores(List<CatUsuarios> recibidores) {
        this.recibidores = recibidores;
    }

    public List<CatUsuarios> getAprobadores() {
        return aprobadores;
    }

    public void setAprobadores(List<CatUsuarios> aprobadores) {
        this.aprobadores = aprobadores;
    }

    public List<CatPaises> getPaises() {
        return paises;
    }

    public void setPaises(List<CatPaises> paises) {
        this.paises = paises;
    }

    public List<CatBodegas> getBodegas() {
        return bodegas;
    }

    public void setBodegas(List<CatBodegas> bodegas) {
        this.bodegas = bodegas;
    }

    public List<CatUbicaciones> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<CatUbicaciones> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }

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

    public CatSolicitudes getCatmaestroaprobar() {
        return catmaestroaprobar;
    }

    public void setCatmaestroaprobar(CatSolicitudes catmaestroaprobar) {
        this.catmaestroaprobar = catmaestroaprobar;
    }

    public List<CatSolicitudes> getMaestroaprobar() {
        return maestroaprobar;
    }

    public void setMaestroaprobar(List<CatSolicitudes> maestroaprobar) {
        this.maestroaprobar = maestroaprobar;
    }

    public CatSolicitudesDetalle getCatdetallesaprobar() {
        return catdetallesaprobar;
    }

    public void setCatdetallesaprobar(CatSolicitudesDetalle catdetallesaprobar) {
        this.catdetallesaprobar = catdetallesaprobar;
    }

    public List<CatSolicitudesDetalle> getDetallesaprobar() {
        return detallesaprobar;
    }

    public void setDetallesaprobar(List<CatSolicitudesDetalle> detallesaprobar) {
        this.detallesaprobar = detallesaprobar;
    }

    public CatSolicitudes getCatmaestrohistoria() {
        return catmaestrohistoria;
    }

    public void setCatmaestrohistoria(CatSolicitudes catmaestrohistoria) {
        this.catmaestrohistoria = catmaestrohistoria;
    }

    public List<CatSolicitudes> getMaestrohistoria() {
        return maestrohistoria;
    }

    public void setMaestrohistoria(List<CatSolicitudes> maestrohistoria) {
        this.maestrohistoria = maestrohistoria;
    }

    public CatSolicitudesDetalleHistoria getCatdetalleshistoria() {
        return catdetalleshistoria;
    }

    public void setCatdetalleshistoria(CatSolicitudesDetalleHistoria catdetalleshistoria) {
        this.catdetalleshistoria = catdetalleshistoria;
    }

    public List<CatSolicitudesDetalleHistoria> getDetalleshistoria() {
        return detalleshistoria;
    }

    public void setDetalleshistoria(List<CatSolicitudesDetalleHistoria> detalleshistoria) {
        this.detalleshistoria = detalleshistoria;
    }

    public String getCot_cod_cor() {
        return cot_cod_cor;
    }

    public void setCot_cod_cor(String cot_cod_cor) {
        this.cot_cod_cor = cot_cod_cor;
    }

    public String getCot_cod_pro() {
        return cot_cod_pro;
    }

    public void setCot_cod_pro(String cot_cod_pro) {
        this.cot_cod_pro = cot_cod_pro;
    }

    public String getCot_det_cot() {
        return cot_det_cot;
    }

    public void setCot_det_cot(String cot_det_cot) {
        this.cot_det_cot = cot_det_cot;
    }

    public String getCom_cod_cor() {
        return com_cod_cor;
    }

    public void setCom_cod_cor(String com_cod_cor) {
        this.com_cod_cor = com_cod_cor;
    }

    public String getCom_cod_pro() {
        return com_cod_pro;
    }

    public void setCom_cod_pro(String com_cod_pro) {
        this.com_cod_pro = com_cod_pro;
    }

    public String getCom_det_com() {
        return com_det_com;
    }

    public void setCom_det_com(String com_det_com) {
        this.com_det_com = com_det_com;
    }

    public String getCod_mae() {
        return cod_mae;
    }

    public String getCom_can_com() {
        return com_can_com;
    }

    public void setCom_can_com(String com_can_com) {
        this.com_can_com = com_can_com;
    }

    public void setCod_mae(String cod_mae) {
        this.cod_mae = cod_mae;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getFec_cie() {
        return fec_cie;
    }

    public void setFec_cie(String fec_cie) {
        this.fec_cie = fec_cie;
    }

    public String getDet_can_sol() {
        return det_can_sol;
    }

    public void setDet_can_sol(String det_can_sol) {
        this.det_can_sol = det_can_sol;
    }

    public String getDet_can_ent() {
        return det_can_ent;
    }

    public void setDet_can_ent(String det_can_ent) {
        this.det_can_ent = det_can_ent;
    }

    public String getDet_can_pen() {
        return det_can_pen;
    }

    public void setDet_can_pen(String det_can_pen) {
        this.det_can_pen = det_can_pen;
    }

    public String getMdt_cod_det() {
        return mdt_cod_det;
    }

    public void setMdt_cod_det(String mdt_cod_det) {
        this.mdt_cod_det = mdt_cod_det;
    }

    public String getMdt_cod_ite() {
        return mdt_cod_ite;
    }

    public void setMdt_cod_ite(String mdt_cod_ite) {
        this.mdt_cod_ite = mdt_cod_ite;
    }

    public String getMdt_des_ite() {
        return mdt_des_ite;
    }

    public void setMdt_des_ite(String mdt_des_ite) {
        this.mdt_des_ite = mdt_des_ite;
    }

    public String getMdt_non_sto() {
        return mdt_non_sto;
    }

    public void setMdt_non_sto(String mdt_non_sto) {
        this.mdt_non_sto = mdt_non_sto;
    }

    public String getMdt_det_sta() {
        return mdt_det_sta;
    }

    public void setMdt_det_sta(String mdt_det_sta) {
        this.mdt_det_sta = mdt_det_sta;
    }

    public String getMdt_cos_uni() {
        return mdt_cos_uni;
    }

    public void setMdt_cos_uni(String mdt_cos_uni) {
        this.mdt_cos_uni = mdt_cos_uni;
    }

    public String getDet_fec_cie() {
        return det_fec_cie;
    }

    public void setDet_fec_cie(String det_fec_cie) {
        this.det_fec_cie = det_fec_cie;
    }

    public String getApr_cod_mae() {
        return apr_cod_mae;
    }

    public void setApr_cod_mae(String apr_cod_mae) {
        this.apr_cod_mae = apr_cod_mae;
    }

    public String getHis_cod_mae() {
        return his_cod_mae;
    }

    public void setHis_cod_mae(String his_cod_mae) {
        this.his_cod_mae = his_cod_mae;
    }

    public String getHis_cod_det() {
        return his_cod_det;
    }

    public void setHis_cod_det(String his_cod_det) {
        this.his_cod_det = his_cod_det;
    }

    public String getTabindex() {
        return tabindex;
    }

    public void setTabindex(String tabindex) {
        this.tabindex = tabindex;
    }

    public String getTabindex2() {
        return tabindex2;
    }

    public void setTabindex2(String tabindex2) {
        this.tabindex2 = tabindex2;
    }

    public String getRbhistoria() {
        return rbhistoria;
    }

    public void setRbhistoria(String rbhistoria) {
        this.rbhistoria = rbhistoria;
    }

    public Date getMfecha() {
        return mfecha;
    }

    public void setMfecha(Date mfecha) {
        this.mfecha = mfecha;
    }

    public Date getMfecha2() {
        return mfecha2;
    }

    public void setMfecha2(Date mfecha2) {
        this.mfecha2 = mfecha2;
    }

    public String getAprobador() {
        return aprobador;
    }

    public void setAprobador(String aprobador) {
        this.aprobador = aprobador;
    }

    public String getRecibidapor() {
        return recibidapor;
    }

    public void setRecibidapor(String recibidapor) {
        this.recibidapor = recibidapor;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getCod_alt() {
        return cod_alt;
    }

    public void setCod_alt(String cod_alt) {
        this.cod_alt = cod_alt;
    }

    public String getIdbuscar() {
        return idbuscar;
    }

    public void setIdbuscar(String idbuscar) {
        this.idbuscar = idbuscar;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }

    public String getDes_ubi() {
        return des_ubi;
    }

    public void setDes_ubi(String des_ubi) {
        this.des_ubi = des_ubi;
    }

    public String getBooledit() {
        return booledit;
    }

    public void setBooledit(String booledit) {
        this.booledit = booledit;
    }

    public String getNombrereporte() {
        return nombrereporte;
    }

    public void setNombrereporte(String nombrereporte) {
        this.nombrereporte = nombrereporte;
    }

    public String getNombreexportar() {
        return nombreexportar;
    }

    public void setNombreexportar(String nombreexportar) {
        this.nombreexportar = nombreexportar;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    //*************** Configuración Inicial *****************************************
    public void iniciarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        tabindex = "0";
        tabindex2 = "0";
        rbhistoria = "0";
        mfecha = Date.from(Instant.now());
        mfecha2 = Date.from(Instant.now());

        cod_mae = "";
        apr_cod_mae = "";
        his_cod_mae = "";
        his_cod_det = "";
        det_sta = "";
        det_can_sol = "";
        det_can_ent = "";
        det_can_pen = "";
        mdt_cod_det = "";
        mdt_cod_ite = "";
        mdt_des_ite = "";
        mdt_non_sto = "1";
        mdt_det_sta = "";
        mdt_cos_uni = "0.0";
        det_fec_cie = format.format(mfecha);
        fec_cie = format.format(mfecha2);
        aprobador = "0";
        cot_cod_cor = "";
        cot_cod_pro = "0";
        cot_det_cot = "";
        com_cod_cor = "";
        com_cod_pro = "0";
        com_det_com = "";
        com_can_com = "";
        recibidapor = "0";
        cod_pai = "";
        cod_bod = "0";
        des_ubi = "";
        cod_alt = "";
        idbuscar = "";
        destino = "0";
        booledit = "false";
        bodegas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        maestrohistoria = new ArrayList<>();
        detalles = new ArrayList<>();
        detallesaprobar = new ArrayList<>();
        detalleshistoria = new ArrayList<>();
        catdetalles = new CatSolicitudesDetalle();
        llenarProveedores();
        llenarBodegas();
        llenarAprobadores();
        llenarRecibidores();
        llenarMaestro();
        llenarMaestroAprobar();

    }

    public void cerrarventana() {
        cod_mae = "";
        apr_cod_mae = "";
        his_cod_mae = "";
        his_cod_det = "";
        det_sta = "";
        det_can_sol = "";
        det_can_ent = "";
        det_can_pen = "";
        det_fec_cie = "";
        fec_cie = "";
        destino = "";
        maestro = new ArrayList<>();
        detalles = new ArrayList<>();
        maestroaprobar = new ArrayList<>();
        detallesaprobar = new ArrayList<>();
        maestrohistoria = new ArrayList<>();
        detalleshistoria = new ArrayList<>();
        catmaestrohistoria = new CatSolicitudes();
    }

    public void refrescarventana() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        rbhistoria = "0";

        mfecha = Date.from(Instant.now());
        mfecha2 = Date.from(Instant.now());

        cod_mae = "";

        det_sta = "";
        det_can_sol = "";
        det_can_ent = "";
        det_can_pen = "";
        mdt_cod_det = "";
        mdt_cod_ite = "";
        mdt_des_ite = "";
        mdt_non_sto = "1";
        mdt_det_sta = "";
        mdt_cos_uni = "0.0";
        det_fec_cie = format.format(mfecha);
        fec_cie = format.format(mfecha2);
        aprobador = "0";
        cot_cod_cor = "";
        cot_cod_pro = "0";
        cot_det_cot = "";
        com_cod_cor = "";
        com_cod_pro = "0";
        com_det_com = "";
        com_can_com = "";
        recibidapor = "0";
        cod_pai = "";
        cod_bod = "0";
        des_ubi = "";
        cod_alt = "";
        idbuscar = "";
        destino = "0";
        booledit = "false";
        bodegas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        maestrohistoria = new ArrayList<>();
        detalles = new ArrayList<>();
        detallesaprobar = new ArrayList<>();
        detalleshistoria = new ArrayList<>();
        catdetalles = new CatSolicitudesDetalle();
        llenarProveedores();
        llenarBodegas();
        llenarAprobadores();
        llenarRecibidores();
        llenarMaestro();
        llenarMaestroAprobar();

    }

    //******************* Llenar Catálogos ******************************************
    public void llenarAprobadores() {
        try {
            aprobadores = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai "
                    + "left join lis_esp as lis on usu.cod_usu = lis.cod_usu "
                    + "where lis.cod_lis = 3 "
                    + "order by cod_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                aprobadores.add(new CatUsuarios(
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

    public void llenarRecibidores() {
        try {
            recibidores = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai "
                    + "left join lis_esp as lis on usu.cod_usu = lis.cod_usu "
                    + "order by cod_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                recibidores.add(new CatUsuarios(
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

    public void llenarBodegas() {
        try {

            paises = new ArrayList<>();

            String mQuery = "select cod_alm, nom_alm "
                    + "from cat_alm order by cod_alm;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                paises.add(new CatPaises(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();
            bodegas.clear();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Paises en ManSeguimiento. " + e.getMessage());
        }
    }

    public void llenarEstantes() {
        String mQuery = "";
        try {

            bodegas.clear();

            mQuery = "select id_bod, nom_bod,cod_pai "
                    + "from cat_bodegas "
                    + "where cod_pai=" + cod_pai + " "
                    + "order by id_bod;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                bodegas.add(new CatBodegas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3), ""
                ));
            }
            mAccesos.Desconectar();
            des_ubi = "";

        } catch (Exception e) {
            System.out.println("Error en el llenado de Bodegas en ManSeguimiento. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarUbicaciones() {
        String mQuery = "";
        try {

            ubicaciones = new ArrayList<>();

            mQuery = "select ubi.id_ubi,ubi.cod_bod,ubi.nom_ubi,bod.nom_bod, bod.cod_pai, alm.nom_alm "
                    + "from cat_ubicaciones as ubi "
                    + "left join cat_bodegas as bod on bod.id_bod = ubi.cod_bod "
                    + "left join cat_alm as alm on bod.cod_pai = alm.cod_alm "
                    + "where alm.cod_alm=" + cod_pai + " "
                    + "and ubi.cod_bod=" + cod_bod + " "
                    + "order by ubi.cod_bod,ubi.id_ubi;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                ubicaciones.add(new CatUbicaciones(
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
            System.out.println("Error en el llenado de Catálogo Repisas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarProveedores() {
        String mQuery = "";
        try {

            proveedores = new ArrayList<>();

            mQuery = "select cod_pro,cod_pai,nom_pro,per_con,tel_con,det_mai  "
                    + "from cat_pro order by cod_pro;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                proveedores.add(new CatProveedores(
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
            System.out.println("Error en el llenado de Proveedores en ManSeguimiento. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    //****************** Llenar Listas *******************************
    public void llenarMaestroAprobar() {
        try {
            catmaestroaprobar = new CatSolicitudes();
            maestroaprobar = new ArrayList<>();

            String mQuery = "select "
                    + "mae.cod_mae, mae.cod_alt, "
                    + "date_format(mae.fec_sol,'%d/%m/%Y'), "
                    + "mae.cod_usu_sol, "
                    + "mae.cod_usu_apr, mae.cod_usu_rec, mae.cod_dep, mae.det_uso, mae.cod_maq, "
                    + "case mae.det_sta "
                    + "when 0 then 'ESPERA APROBACIÓN' "
                    + "when 1 then 'CANCELADA' "
                    + "when 2 then 'APROBADA' "
                    + "when 3 then 'DENEGADA' "
                    + "when 4 then 'PENDIENTE' "
                    + "when 5 then 'CERRADA' end as sta, "
                    + "mae.det_obs, "
                    + "date_format(mae.fec_cie,'%d/%m/%Y'), mae.flg_loc,mae.cod_pai, "
                    + "dep.nom_dep, lis.des_equ as nomequ,"
                    + "pai.nom_alm, usu.det_nom "
                    + "FROM sol_mae as mae "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep "
                    + "left join lis_equ as lis on mae.cod_maq = lis.cod_lis_equ "
                    + "left join cat_equ as maq on lis.cod_equ = maq.cod_equ "
                    + "left join cat_alm as pai on mae.cod_pai = pai.cod_alm "
                    + "left join cat_usu as usu on mae.cod_usu_sol = usu.cod_usu "
                    + "where "
                    + "mae.det_sta = 0 "
                    + "order by mae.fec_sol asc, mae.cod_mae asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                maestroaprobar.add(new CatSolicitudes(
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
            System.out.println("Error en el llenado Maestro en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarDetallesAprobar() {
        try {
            catdetallesaprobar = new CatSolicitudesDetalle();
            detallesaprobar = new ArrayList<>();

            String mQuery = "select  "
                    + "det.cod_mae, det.cod_det, det.cod_pai, det.cod_bod, "
                    + "det.cod_ubi, det.cod_ite, det.des_ite, det.det_can_sol, det.det_can_ent, "
                    + "det.det_can_pen, det.non_sto, "
                    + "case det.det_sta "
                    + "when 0 then 'PENDIENTE' "
                    + "when 1 then 'COTIZADO' "
                    + "when 2 then 'COMPRADO' "
                    + "when 3 then 'ENTREGA PARCIAL' "
                    + "when 4 then 'ENTREGADO' "
                    + "when 5 then 'CANCELADO' "
                    + "end as sta , "
                    + "det.fec_cie, "
                    + "det.cos_uni, "
                    + "pai.nom_alm, bod.nom_bod, ubi.nom_ubi "
                    + "from sol_det as det "
                    + "left join cat_alm as pai on det.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on det.cod_pai = bod.cod_pai and det.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on det.cod_bod = ubi.cod_bod and det.cod_ubi = ubi.id_ubi "
                    + "where det.cod_mae = " + apr_cod_mae + " order by det.cod_det asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detallesaprobar.add(new CatSolicitudesDetalle(
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
                        resVariable.getString(17)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Detalles en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarMaestro() {
        try {
            RequestContext.getCurrentInstance().execute("PF('wvSegui').clearFilters()");
            catmaestro = new CatSolicitudes();
            maestro = new ArrayList<>();

            String mQuery = "select "
                    + "mae.cod_mae, mae.cod_alt, "
                    + "date_format(mae.fec_sol,'%d/%m/%Y'), "
                    + "mae.cod_usu_sol, "
                    + "mae.cod_usu_apr, mae.cod_usu_rec, mae.cod_dep, mae.det_uso, mae.cod_maq, "
                    + "case mae.det_sta "
                    + "when 0 then 'ESPERA APROBACIÓN' "
                    + "when 1 then 'CANCELADA' "
                    + "when 2 then 'APROBADA' "
                    + "when 3 then 'DENEGADA' "
                    + "when 4 then 'PENDIENTE' "
                    + "when 5 then 'CERRADA' end as sta, "
                    + "mae.det_obs, "
                    + "date_format(mae.fec_cie,'%d/%m/%Y'), mae.flg_loc,mae.cod_pai, "
                    + "dep.nom_dep, lis.des_equ as nomequ,"
                    + "pai.nom_alm, usu.det_nom "
                    + "FROM sol_mae as mae "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep "
                    + "left join lis_equ as lis on mae.cod_maq = lis.cod_lis_equ "
                    + "left join cat_equ as maq on lis.cod_equ = maq.cod_equ "
                    + "left join cat_alm as pai on mae.cod_pai = pai.cod_alm "
                    + "left join cat_usu as usu on mae.cod_usu_sol = usu.cod_usu "
                    + "where "
                    + "mae.det_sta in (2,4) "
                    + "order by mae.fec_sol asc, mae.cod_alt asc;";
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
            System.out.println("Error en el llenado Maestro en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarDetalles() {
        try {

            detalles = new ArrayList<>();

            String mQuery = "select  "
                    + "det.cod_mae, det.cod_det, det.cod_pai, det.cod_bod, "
                    + "det.cod_ubi, det.cod_ite, det.des_ite, det.det_can_sol, 0, "
                    + "det.det_can_pen, det.non_sto, "
                    + "case det.det_sta "
                    + "when 0 then 'PENDIENTE' "
                    + "when 1 then 'COTIZADO' "
                    + "when 2 then 'COMPRADO' "
                    + "when 3 then 'ENTREGA PARCIAL' "
                    + "when 4 then 'ENTREGADO' "
                    + "when 5 then 'CANCELADO' "
                    + "end as sta , "
                    + "det.fec_cie, "
                    + "det.cos_uni, "
                    + "pai.nom_alm, bod.nom_bod, ubi.nom_ubi "
                    + "from sol_det as det "
                    + "left join cat_alm as pai on det.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on det.cod_pai = bod.cod_pai and det.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on det.cod_bod = ubi.cod_bod and det.cod_ubi = ubi.id_ubi "
                    + "where det.cod_mae = " + cod_mae + " "
                    + "and det.det_sta in (0,1,2,3) "
                    + "order by det.cod_det asc;";
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
                        resVariable.getString(17)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Detalles en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarMaestroHistoria() {
        try {
            catmaestrohistoria = new CatSolicitudes();
            maestrohistoria = new ArrayList<>();
            String mwhere = "";
            if ("".equals(idbuscar)) {
                mwhere = " ";
            } else {
                switch (rbhistoria) {
                    case "0":
                        mwhere = "where "
                                + "mae.cod_alt in ('" + idbuscar.replace(",", "','") + "') ";
                        break;
                    case "1":
                        mwhere = "where "
                                + "mae.fec_sol = str_to_date('" + idbuscar + "','%d/%m/%Y') ";
                        break;
                    case "2":
                        mwhere = "where usu.det_nom like '%" + idbuscar + "%' ";
                        break;
                }

            }

            String mQuery = "select "
                    + "mae.cod_mae, mae.cod_alt, "
                    + "date_format(mae.fec_sol,'%d/%m/%Y'), "
                    + "mae.cod_usu_sol, "
                    + "mae.cod_usu_apr, mae.cod_usu_rec, mae.cod_dep, mae.det_uso, mae.cod_maq, "
                    + "case mae.det_sta "
                    + "when 0 then 'ESPERA APROBACIÓN' "
                    + "when 1 then 'CANCELADA' "
                    + "when 2 then 'APROBADA' "
                    + "when 3 then 'DENEGADA' "
                    + "when 4 then 'PENDIENTE' "
                    + "when 5 then 'CERRADA' end as sta, "
                    + "mae.det_obs, "
                    + "date_format(mae.fec_cie,'%d/%m/%Y'), mae.flg_loc,mae.cod_pai, "
                    + "dep.nom_dep, lis.des_equ as nomequ,"
                    + "pai.nom_alm, usu.det_nom "
                    + "FROM sol_mae as mae "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep "
                    + "left join lis_equ as lis on mae.cod_maq = lis.cod_lis_equ "
                    + "left join cat_equ as maq on lis.cod_equ = maq.cod_equ "
                    + "left join cat_alm as pai on mae.cod_pai = pai.cod_alm "
                    + "left join cat_usu as usu on mae.cod_usu_sol = usu.cod_usu "
                    + mwhere
                    + "order by mae.fec_sol asc, mae.cod_alt asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                maestrohistoria.add(new CatSolicitudes(
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
            idbuscar = "";
        } catch (Exception e) {
            System.out.println("Error en el llenado Maestro Historia en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarDetallesHistoria() {
        try {
            catdetalleshistoria = new CatSolicitudesDetalleHistoria();
            detalleshistoria = new ArrayList<>();

            String mQuery = "select "
                    + "detrec.cod_mae, "
                    + "detrec.det_mae, "
                    + "his.cod_pai,"
                    + "his.cod_bod,"
                    + "detrec.cod_rec,"
                    + "his.cod_pie,"
                    + "det.des_ite,"
                    + "det.det_can_sol,"
                    + "detrec.det_can,"
                    + "0 as det_can_pen,"
                    + "det.non_sto, "
                    + "case detrec.det_sta "
                    + "when 0 then 'PENDIENTE' "
                    + "when 1 then 'COTIZADO' "
                    + "when 2 then 'COMPRADO' "
                    + "when 3 then 'ENTREGA PARCIAL' "
                    + "when 4 then 'ENTREGADO' "
                    + "when 5 then 'CANCELADO' "
                    + "end as estado,"
                    + "date_format(detrec.fec_rec,'%d/%m/%Y') as fecrec,"
                    + "his.cos_pro,"
                    + "pai.nom_alm,"
                    + "bod.nom_bod,"
                    + "detrec.cod_his, "
                    + "ubi.id_ubi, "
                    + "ubi.nom_ubi "
                    + "from sol_det_rec as detrec "
                    + "left join sol_det as det on detrec.cod_mae = det.cod_mae and detrec.det_mae = det.cod_det "
                    + "left join tbl_pie_his as his on detrec.cod_his = his.cod_his "
                    + "left join cat_alm as pai on his.cod_pai = pai.cod_alm "
                    + "left join cat_bodegas as bod on his.cod_pai = bod.cod_pai and his.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on his.cod_bod = ubi.cod_bod and his.des_ubi = ubi.id_ubi "
                    + "where detrec.cod_mae = " + his_cod_mae + " "
                    + "order by detrec.det_mae, his.cod_pie, detrec.fec_rec;";

            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                detalleshistoria.add(new CatSolicitudesDetalleHistoria(
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
                        resVariable.getString(18),
                        resVariable.getString(19)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Detalles en Seguimiento Solicitud Historia. " + e.getMessage());
        }
    }

    public void llenarCotizaciones() {
        try {
            catcotizaciones = new CatCotizaciones();
            cotizaciones = new ArrayList<>();

            String mQuery = "select cot.cod_mae, cot.cod_det, cot.cor_det, cot.cod_pro, cot.det_cot, pro.nom_pro "
                    + "from sol_cot as cot "
                    + "left join cat_pro as pro on cot.cod_pro = pro.cod_pro "
                    + "where cot.cod_mae=" + cod_mae + " and cot.cod_det=" + mdt_cod_det + " "
                    + "order by cot.cor_det;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                cotizaciones.add(new CatCotizaciones(
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
            System.out.println("Error en el llenado Cotizaciones en Seguimiento Solicitud. " + e.getMessage());
        }
    }

    public void llenarCompras() {
        try {
            catcompras = new CatCompras();
            compras = new ArrayList<>();

            String mQuery = "select com.cod_mae, com.cod_det, com.cor_com, com.cod_pro, com.det_com, pro.nom_pro,can_com "
                    + "from sol_com as com "
                    + "left join cat_pro as pro on com.cod_pro = pro.cod_pro "
                    + "where com.cod_mae=" + cod_mae + " and com.cod_det=" + mdt_cod_det + " "
                    + "order by com.cod_mae;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                compras.add(new CatCompras(
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
            System.out.println("Error en el llenado Compras en Seguimiento Solicitud. " + e.getMessage());
        }

    }

    //************************* Acciones **********************
    public void aprobar() {
        String mQuery;
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if (!"".equals(apr_cod_mae) && !"0".equals(aprobador)) {

            try {
                mQuery = "update tbl_rel_man_sol_req set det_sta_sol_req=2 "
                        + "where cod_sol_req= " + apr_cod_mae + " and flg_sol_req='SOL'";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "update sol_mae set det_sta=2, cod_usu_apr=" + aprobador
                        + " where cod_mae=" + apr_cod_mae + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Aprobar Solicitud", "La Solicitud ha sido Aprobada.", 1);
            } catch (Exception e) {
                addMessage("Aprobar Solicitud", "Error al Aprobar Solicitud. " + e.getMessage(), 2);
                System.out.println("Error al Aprobar Solicitud. " + e.getMessage());
            }
            maestrohistoria = new ArrayList<>();
            detalles = new ArrayList<>();
            detallesaprobar = new ArrayList<>();
            detalleshistoria = new ArrayList<>();
            llenarMaestro();
            llenarMaestroAprobar();

        } else {
            addMessage("Aprobar Solicitud", "Debe elegir un Registro y un Aprobador.", 2);
        }

        mAccesos.Desconectar();

    }

    public void rechazar() {
        String mQuery;
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if ("".equals(cod_mae) == false) {

            try {
                mQuery = "update tbl_rel_man_sol_req set det_sta_sol_req=3 "
                        + "where cod_sol_req= " + apr_cod_mae + " and flg_sol_req='SOL';";
                mAccesos.dmlSQLvariable(mQuery);
                mQuery = "update sol_mae set det_sta=3 where cod_mae=" + apr_cod_mae + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Cancelar Solicitud", "La Solicitud ha sido denegada.", 1);
            } catch (Exception e) {
                addMessage("Cancelar Solicitud", "Error al Denegar Solicitud. " + e.getMessage(), 2);
                System.out.println("Error al Cancelar Solicitud. " + e.getMessage());
            }
            maestrohistoria = new ArrayList<>();
            detalles = new ArrayList<>();
            detallesaprobar = new ArrayList<>();
            detalleshistoria = new ArrayList<>();
            llenarMaestro();
            llenarMaestroAprobar();

        } else {
            addMessage("Cancelar Solicitud", "Debe elegir un Registro.", 2);
        }

        mAccesos.Desconectar();

    }

    public void agregarcotizacion() {
        String mQuery = "";
        try {
            if (validarcotizacion()) {
                Accesos acc = new Accesos();
                acc.Conectar();

                if ("".equals(cot_cod_cor) || "0".equals(cot_cod_cor)) {
                    mQuery = "select ifnull(max(cor_det),0)+1 as codigo from sol_cot where cod_mae= " + cod_mae + " and cod_det = " + mdt_cod_det + ";";
                    cot_cod_cor = acc.strQuerySQLvariable(mQuery);
                    mQuery = "insert into sol_cot (cod_mae,cod_det,cor_det,cod_pro,det_cot) VALUES ("
                            + cod_mae + "," + mdt_cod_det + "," + cot_cod_cor + "," + cot_cod_pro + "," + cot_det_cot
                            + ");";
                } else {
                    mQuery = "update sol_cot set "
                            + "cod_pro=" + cot_cod_pro + ","
                            + "det_cot=" + cot_det_cot + " "
                            + "where cod_mae=" + cod_mae + " "
                            + "and cod_det=" + mdt_cod_det + " "
                            + "and cor_det=" + cot_cod_cor + ";";
                }
                acc.dmlSQLvariable(mQuery);

                mQuery = "update sol_det set det_sta=1 where cod_mae=" + cod_mae + " and cod_det=" + mdt_cod_det + ";";
                acc.dmlSQLvariable(mQuery);

                acc.Desconectar();

                llenarDetalles();
                //cotizaciones = new ArrayList<>();
                llenarCotizaciones();
                catcotizaciones = new CatCotizaciones();
                cot_cod_cor = "0";
                cot_cod_pro = "0";
                cot_det_cot = "";

                addMessage("Guardar Cotización", "Información almacenada con Éxito.", 1);
            }
        } catch (Exception e) {
            System.out.println("Error en guardar Cotización." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public boolean validarcotizacion() {
        boolean mvalidar = true;

        if ("".equals(cot_det_cot)) {
            cot_det_cot = "0";
        }

        if ("".equals(cod_mae) || "0".equals(cod_mae)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Solicitud.", 2);
        }

        if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Item de la Solicitud.", 2);
        } else {
            if (!"COTIZADO".equals(catdetalles.getDet_sta()) && !"PENDIENTE".equals(catdetalles.getDet_sta())) {
                mvalidar = false;
                addMessage("Validar Datos", "El Detalle Seleccionado ya no puede ser cotizado.", 2);
            }
            if ("".equals(cot_cod_pro) || "0".equals(cot_cod_pro)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar un Proveedor.", 2);
            }
        }

        return mvalidar;
    }

    public void eliminarcotizacion() {
        String mQuery = "";
        try {
            if ("".equals(cod_mae) || "0".equals(cod_mae)) {
                if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
                    if (!cotizaciones.isEmpty()) {
                        if ("".equals(cot_cod_cor) || "0".equals(cot_cod_cor)) {
                            Accesos acc = new Accesos();
                            acc.Conectar();
                            mQuery = "delete from sol_cot where cod_mae=" + cod_mae + " and cod_det=" + mdt_cod_det + " and cor_det=" + cot_cod_cor + ";";
                            acc.dmlSQLvariable(mQuery);
                            acc.Desconectar();
                            llenarCotizaciones();
                            catcotizaciones = new CatCotizaciones();
                        } else {
                            addMessage("Validar Datos", "Seleccione un Item de la Lista de Cotización.", 2);
                        }

                    } else {
                        addMessage("Validar Datos", "No Existe ningún Registro de Cotización para este Item.", 2);
                    }
                } else {
                    addMessage("Validar Datos", "Seleccione un Detalle de la Solicitud.", 2);
                }
            } else {
                addMessage("Validar Datos", "Seleccione una Solicitud.", 2);
            }
        } catch (Exception e) {
            System.out.println("Error en eliminarcotizacion de manseguimiento. " + e.getMessage() + " Query: " + mQuery);
        }

    }

    public void agregarcompra() {
        String mQuery = "";
        try {
            if (validarcompra()) {
                Accesos acc = new Accesos();
                acc.Conectar();

                if ("".equals(com_cod_cor) || "0".equals(com_cod_cor)) {
                    mQuery = "select ifnull(max(cor_com),0)+1 as codigo from sol_com where cod_mae= " + cod_mae + " and cod_det = " + mdt_cod_det + ";";
                    com_cod_cor = acc.strQuerySQLvariable(mQuery);
                    mQuery = "insert into sol_com (cod_mae,cod_det,cor_com,cod_pro,det_com,can_com) VALUES ("
                            + cod_mae + "," + mdt_cod_det + "," + com_cod_cor + "," + com_cod_pro + "," + com_det_com + "," + com_can_com
                            + ");";
                } else {
                    mQuery = "update sol_cot set "
                            + "cod_pro=" + com_cod_pro + ","
                            + "det_com=" + com_det_com + ","
                            + "can_com=" + com_can_com + " "
                            + "where cod_mae=" + cod_mae + " "
                            + "and cod_det=" + mdt_cod_det + " "
                            + "and cor_com=" + com_cod_cor + ";";
                }
                acc.dmlSQLvariable(mQuery);

                mQuery = "update sol_det set det_sta=2 where cod_mae=" + cod_mae + " and cod_det=" + mdt_cod_det + ";";
                acc.dmlSQLvariable(mQuery);

                acc.Desconectar();

                llenarDetalles();
                //compras = new ArrayList<>();
                catcompras = new CatCompras();
                llenarCompras();
                com_cod_cor = "0";
                com_cod_pro = "0";
                com_det_com = "";
                com_can_com = "";

                addMessage("Guardar Compra", "Información almacenada con Éxito.", 1);
            }
        } catch (Exception e) {
            System.out.println("Error en guardar Compra." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public boolean validarcompra() {
        boolean mvalidar = true;

        if ("".equals(com_det_com)) {
            cot_det_cot = "0";
        }

        if ("".equals(com_can_com)) {
            com_can_com = "0";
        }

        if ("".equals(cod_mae) || "0".equals(cod_mae)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Solicitud.", 2);
        }

        if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Item de la Solicitud.", 2);
        } else {
            if (!"COTIZADO".equals(catdetalles.getDet_sta()) && !"PENDIENTE".equals(catdetalles.getDet_sta()) && !"COMPRADO".equals(catdetalles.getDet_sta())) {
                mvalidar = false;
                addMessage("Validar Datos", "El Item Seleccionado ya ha sido Comprado.", 2);
            }
            if ("".equals(com_cod_pro) || "0".equals(com_cod_pro)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar un Proveedor.", 2);
            }
        }

        Accesos acc = new Accesos();
        acc.Conectar();
        Double micantidad = acc.doubleQuerySQLvariable("select ifnull(sum(can_com),0) from sol_com where cod_mae=" + cod_mae + " and cod_det=" + mdt_cod_det + ";") + Double.valueOf(com_can_com);
        acc.Desconectar();

        if (micantidad > Double.valueOf(catdetalles.getDet_can_sol())) {
            mvalidar = false;
            addMessage("Validar Datos", "La Cantidad Comprada no puede ser superior a la Cantidad Solicitada.", 2);
        }

        return mvalidar;
    }

    public void eliminarcompra() {
        String mQuery = "";
        try {
            if ("".equals(cod_mae) || "0".equals(cod_mae)) {
                if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
                    if (!compras.isEmpty()) {
                        if ("".equals(com_cod_cor) || "0".equals(com_cod_cor)) {
                            Accesos acc = new Accesos();
                            acc.Conectar();
                            mQuery = "delete from sol_com where cod_mae=" + cod_mae + " and cod_det=" + mdt_cod_det + " and cor_com=" + com_cod_cor + ";";
                            acc.dmlSQLvariable(mQuery);
                            acc.Desconectar();
                            llenarCompras();
                            catcompras = new CatCompras();

                        } else {
                            addMessage("Validar Datos", "Seleccione un Item de la Lista de Compra.", 2);
                        }

                    } else {
                        addMessage("Validar Datos", "No Existe ningún Registro de Compra para este Item.", 2);
                    }
                } else {
                    addMessage("Validar Datos", "Seleccione un Detalle de la Solicitud.", 2);
                }
            } else {
                addMessage("Validar Datos", "Seleccione una Solicitud.", 2);
            }
        } catch (Exception e) {
            System.out.println("Error en eliminarcompra de manseguimiento. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void guardarItem() {
        String mQuery = "", mdetsta;
        try {
            if (validarseguimiento()) {
                Accesos acc = new Accesos();

                acc.Conectar();
                mQuery = "update sol_mae set "
                        + "det_sta = 4 "
                        + "where cod_mae = " + cod_mae + ";";
                acc.dmlSQLvariable(mQuery);

                String strvar = acc.strQuerySQLvariable("select (det_can_pen - " + det_can_ent + " ) as resta from sol_det where cod_mae= " + cod_mae
                        + " and cod_det=" + mdt_cod_det + ";");

                mQuery = "update sol_det set "
                        + "det_can_ent = (det_can_ent + " + det_can_ent + "), "
                        + "det_can_pen = (det_can_pen-" + det_can_ent + "), "
                        + "cos_uni = " + mdt_cos_uni + " "
                        + "where cod_mae= " + cod_mae
                        + " and cod_det=" + mdt_cod_det + ";";
                acc.dmlSQLvariable(mQuery);

                if ("0".equals(strvar)) {
                    mQuery = "update sol_det set "
                            + "det_sta=4, "
                            + "fec_cie = str_to_date('" + det_fec_cie + "','%d/%m/%Y') "
                            + "where cod_mae= " + cod_mae
                            + " and cod_det=" + mdt_cod_det + ";";
                    acc.dmlSQLvariable(mQuery);
                    mdetsta = "4";
                } else {
                    mQuery = "update sol_det set "
                            + "det_sta=3, "
                            + "fec_cie = str_to_date('" + det_fec_cie + "','%d/%m/%Y') "
                            + "where cod_mae= " + cod_mae
                            + " and cod_det=" + mdt_cod_det + ";";
                    acc.dmlSQLvariable(mQuery);
                    mdetsta = "3";
                }

                strvar = acc.strQuerySQLvariable("select count(cod_mae) from sol_det where cod_mae= " + cod_mae
                        + " and det_sta not in (4,5);");

                if ("0".equals(strvar)) {
                    mQuery = "update sol_mae set "
                            + "det_sta = 5, "
                            + "fec_cie = str_to_date('" + fec_cie + "','%d/%m/%Y') "
                            + "where cod_mae = " + cod_mae + ";";
                    acc.dmlSQLvariable(mQuery);
                    catdetalles = new CatSolicitudesDetalle();
                    detalles = new ArrayList<>();
                    llenarMaestro();
                } else {
                    catdetalles = new CatSolicitudesDetalle();
                    llenarDetalles();

                }

                catcotizaciones = new CatCotizaciones();
                cotizaciones = new ArrayList<>();
                catcompras = new CatCompras();
                compras = new ArrayList<>();

                String cod_rec = acc.strQuerySQLvariable("select ifnull(max(cod_rec),0)+1 as cod from sol_det_rec;");
                if ("0".equals(destino)) {
                    mQuery = "insert into sol_det_rec (cod_rec,cod_mae,det_mae,fec_rec,det_can,flg_usu_alm,cod_usu_rec,cod_his,det_sta) "
                            + "VALUES (" + cod_rec + "," + cod_mae + "," + mdt_cod_det
                            + ",str_to_date('" + det_fec_cie + "','%d/%m/%YYYY')," + det_can_ent + ",0," + recibidapor + ",0," + mdetsta + ");";
                    acc.dmlSQLvariable(mQuery);
                } else {
                    //código correlativo existencia histórica de artículo
                    String cod_cor_exi_art = acc.strQuerySQLvariable("select ifnull(max(cod_his),0)+1 "
                            + "as codigo from tbl_pie_his;");
                    //Código correlativo diario existencia histórica de artículo
                    String cor_dia = acc.strQuerySQLvariable("select ifnull(max(ord_dia),0)+1 "
                            + "as cordia from tbl_pie_his "
                            + "where "
                            + "cod_pie=" + mdt_cod_ite + " "
                            + "and fec_his=STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                            + "and cod_pai = " + cod_pai + " "
                            + ";");
                    //Costo promedio
                    Double cPromedio, exisAnt, mNuevaExistencia;
                    if ("1".equals(cod_cor_exi_art)) {
                        cPromedio = 0.0;
                        exisAnt = 0.0;
                    } else {
                        cPromedio = acc.doubleQuerySQLvariable("select ifnull(cos_pro,0) as cospro "
                                + "from tbl_pie_his "
                                + "where "
                                + "cod_pie=" + mdt_cod_ite + " "
                                + "and fec_his <= STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                                + "and flg_ing = 0 "
                                + "and det_sta = 0 "
                                + "and cod_pai = " + cod_pai + " "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;");

                        //Existencia Anterior
                        exisAnt = acc.doubleQuerySQLvariable("select ifnull(can_exi,0) as exisant "
                                + "from tbl_pie_his "
                                + "where "
                                + "cod_pie=" + mdt_cod_ite + " "
                                + "and fec_his <= STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                                + "and det_sta = 0 "
                                + "and cod_pai = " + cod_pai + " "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;");

                    }
                    //Inserta Registros

                    mNuevaExistencia = (exisAnt + Double.valueOf(det_can_ent));

                    mQuery = " insert into tbl_pie_his (cod_his,cod_pie,fec_his,ord_dia,flg_ing,"
                            + "cod_enc,cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,cod_usu,cod_pai,cod_bod,des_ubi) "
                            + "VALUES (" + cod_cor_exi_art + "," + mdt_cod_ite + ","
                            + "STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y')" + "," + cor_dia + ",0,0,0," + det_can_ent + "," + cPromedio + ","
                            + mNuevaExistencia + ","
                            + cPromedio + "," + "0" + "," + "now()" + "," + cbean.getCod_usu() + ","
                            + cod_pai + "," + cod_bod + "," + des_ubi + ");";
                    acc.dmlSQLvariable(mQuery);
                    mQuery = "insert into sol_det_rec (cod_rec,cod_mae,det_mae,fec_rec,det_can,flg_usu_alm,cod_usu_rec,cod_his,det_sta) "
                            + "VALUES (" + cod_rec + "," + cod_mae + "," + mdt_cod_det
                            + ",str_to_date('" + det_fec_cie + "','%d/%m/%YYYY')," + det_can_ent + ",1,0," + cod_cor_exi_art + "," + mdetsta + ");";
                    acc.dmlSQLvariable(mQuery);

                    // Verifica si hay registros posteriores y si los hay actualiza a partir de la fecha de Transacción
                    String contasiguientes = acc.strQuerySQLvariable("select count(cod_his) "
                            + "from tbl_pie_his where fec_his=STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                            + "and ord_dia >" + cor_dia + " "
                            + "and cod_pie=" + mdt_cod_ite + " "
                            + "and det_sta = 0 "
                            + "and cod_pai = " + cod_pai + " "
                            + ";");
                    contasiguientes = String.valueOf(
                            Integer.valueOf(contasiguientes)
                            + Integer.valueOf(acc.strQuerySQLvariable("select count(cod_his) "
                                    + "from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                                    + "and cod_pie=" + mdt_cod_ite + " "
                                    + "and det_sta = 0 "
                                    + "and cod_pai = " + cod_pai + " "
                                    + ";")));

                    Double nuevacantidad = mNuevaExistencia;
                    if ("0".equals(contasiguientes) == false) {
                        try {
                            historico = new ArrayList<>();

                            //Double cos_uni_sal = 0.0;
                            ResultSet resvariable;
                            resvariable = acc.querySQLvariable("select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + det_fec_cie + "','%d/%m/%Y') "
                                    + "and cod_pie=" + mdt_cod_ite + " "
                                    + "and det_sta = 0 "
                                    + "and cod_pai = " + cod_pai + " "
                                    + "order by fec_his asc,"
                                    + "ord_dia asc"
                                    + ";");
                            while (resvariable.next()) {
                                historico.add(new CatHistorico(
                                        resvariable.getString(1),
                                        resvariable.getString(2),
                                        resvariable.getString(3),
                                        resvariable.getString(4),
                                        resvariable.getString(5),
                                        resvariable.getString(6),
                                        resvariable.getString(7),
                                        resvariable.getString(8),
                                        resvariable.getString(9),
                                        resvariable.getString(10),
                                        resvariable.getString(11),
                                        resvariable.getString(12),
                                        resvariable.getString(13),
                                        resvariable.getString(14)
                                ));
                            }

                            for (CatHistorico seriehistorica1 : historico) {
                                if ("0".equals(seriehistorica1.getFlg_ing())) {
                                    cPromedio = (cPromedio * nuevacantidad + Double.valueOf(seriehistorica1.getDet_can()) * Double.valueOf(seriehistorica1.getDet_cos())) / (nuevacantidad + Double.valueOf(seriehistorica1.getDet_can()));
                                    nuevacantidad = nuevacantidad + Double.valueOf(seriehistorica1.getDet_can());
                                    //cos_uni_sal = 0.0;
                                    //cunitario = Double.valueOf(seriehistorica1.getDet_cos());
                                } else {
                                    nuevacantidad = nuevacantidad - Double.valueOf(seriehistorica1.getDet_can());
                                    //cos_uni_sal = cPromedio;
                                }
                                mQuery = "update tbl_pie_his set "
                                        + "cos_pro= " + cPromedio + " ,"
                                        + "can_exi= " + nuevacantidad + " "
                                        + "where "
                                        + "cod_his = " + seriehistorica1.getCod_his()
                                        + ";";
                                acc.dmlSQLvariable(mQuery);
                            }

                        } catch (Exception e) {
                            System.out.println("Error en actualización de costos posteriores Agregar. " + e.getMessage());
                        }

                    }

                    // Tratamiento tabla bol_exi_pai
                    String mContador = acc.strQuerySQLvariable("select count(cod_pie) as contador from bol_exi_pai "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and ing_sal=0 "
                            + "and cod_pie=" + mdt_cod_ite
                            + ";");

                    if ("0".equals(mContador)) {

                        mQuery = "insert into bol_exi_pai(cod_pai,cod_pie,ing_sal,det_exi) "
                                + "VALUES ("
                                + cod_pai + ","
                                + mdt_cod_ite + ",0,"
                                + det_can_ent
                                + ");";

                    } else {
                        mQuery = "update bol_exi_pai set "
                                + "det_exi= (det_exi + " + det_can_ent + ") "
                                + "where "
                                + "cod_pai=" + cod_pai + " "
                                + "and ing_sal=0 "
                                + "and cod_pie=" + mdt_cod_ite + ";";

                    }

                    acc.dmlSQLvariable(mQuery);

                    // Tratamiento tabla tbl_existencias
                    mContador = acc.strQuerySQLvariable("select count(cod_exi) as contador from tbl_existencias "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and cod_bod=" + cod_bod + " "
                            + "and cod_ubi=" + des_ubi + " "
                            + "and cod_pie=" + mdt_cod_ite
                            + ";");

                    if ("0".equals(mContador)) {

                        mQuery = "insert into tbl_existencias(cod_exi,cod_pie,cod_pai,cod_bod,cod_ubi,det_can,cos_pro) "
                                + "VALUES ("
                                + acc.strQuerySQLvariable("select (ifnull(max(cod_exi),0) + 1) as codigo from tbl_existencias;") + ","
                                + mdt_cod_ite + ","
                                + cod_pai + ","
                                + cod_bod + "," + des_ubi + ","
                                + det_can_ent
                                + ",0);";
                    } else {
                        mQuery = " update tbl_existencias set det_can=(det_can+" + det_can_ent + ") "
                                + " where cod_pai=" + cod_pai + " and cod_bod = " + cod_bod + " and cod_ubi=" + des_ubi + " "
                                + " and cod_pie=" + mdt_cod_ite + " ;";
                    }
                    acc.dmlSQLvariable(mQuery);

                }

                acc.Desconectar();
                det_can_ent = "";
                addMessage("Guardar Solicitud", "Cambios Almacenados con Éxito.", 1);
            }

        } catch (Exception e) {
            System.out.println("Error al Guardar Cambios en Seguimiento." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public boolean validarseguimiento() {
        boolean mvalidar = true;

        if ("".equals(det_can_ent)) {
            det_can_ent = "0";
        }

        if ("".equals(cod_mae) || "0".equals(cod_mae)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Solicitud.", 2);
        }

        if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Item de la Solicitud.", 2);
        }

        try {
            if (Integer.valueOf(det_can_pen) < Integer.valueOf(det_can_ent)) {
                mvalidar = false;
                addMessage("Validar Datos", "La Cantidad Entregada no puede ser Superior a la Pendiente.", 2);
            }
        } catch (Exception e) {
            mvalidar = false;
            addMessage("Validar Datos", "Verifique la Cantidad Entregada y la Cantidad Pendiente.", 2);
        }

        if ("0".equals(destino)) {
            if ("0".equals(recibidapor)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar un Responsable de Recibir.", 2);
            }
        } else {
            if ("0".equals(mdt_non_sto)) {
                mvalidar = false;
                addMessage("Validar Datos", "El Item Seleccionado no es Artìculo codificado de Almacèn.", 2);
            }
            if ("0".equals(cod_pai)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar una Bodega.", 2);
            }
            if ("0".equals(cod_bod)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar un Estante.", 2);
            }
            if ("0".equals(des_ubi)) {
                mvalidar = false;
                addMessage("Validar Datos", "Debe Seleccionar una Repisa.", 2);
            }
            if ("0".equals(mdt_cod_ite) || "".equals(mdt_cod_ite)) {
                mvalidar = false;
                addMessage("Validar Datos", "Este Item no es Pieza Codificada.", 2);
            }
        }
        if ("0".equals(det_can_ent)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad Entregada superior a Cero.", 2);
        }

        Accesos macc = new Accesos();
        macc.Conectar();
        String mQuery = "select str_to_date(fec_sol,'%d/%m/%Y') from req_mae where cod_mae=" + cod_mae + ";";
        String mQuery2 = "select DATEDIFF(str_to_date('" + det_fec_cie + "','%d/%m/%Y'), str_to_date('" + macc.strQuerySQLvariable(mQuery) + "','%d/%m/%Y')) as datedif;";
        if (macc.doubleQuerySQLvariable(mQuery2) < 0.0) {
            mvalidar = false;
            addMessage("Validar Datos", "La Fecha de Recepción no puede ser anterior a la Fecha de Creación de Solicitud.", 2);
        }
        macc.Desconectar();

        return mvalidar;
    }

    public void cancelarItem() {
        String mQuery = "";
        try {
            if (validarCancelado()) {
                Accesos acc = new Accesos();

                acc.Conectar();
                mQuery = "update sol_mae set "
                        + "det_sta = 4 "
                        + "where cod_mae = " + cod_mae + ";";
                acc.dmlSQLvariable(mQuery);

                mQuery = "update sol_det set "
                        + "det_sta=5, "
                        + "fec_cie = str_to_date('" + det_fec_cie + "','%d/%m/%Y') "
                        + "where cod_mae= " + cod_mae
                        + " and cod_det=" + mdt_cod_det + ";";
                acc.dmlSQLvariable(mQuery);
                String cod_rec = acc.strQuerySQLvariable("select ifnull(max(cod_rec),0)+1 as cod from sol_det_rec;");
                mQuery = "insert into sol_det_rec (cod_rec,cod_mae,det_mae,fec_rec,det_can,flg_usu_alm,cod_usu_rec,cod_his,det_sta) "
                        + "VALUES (" + cod_rec + "," + cod_mae + "," + mdt_cod_det
                        + ",str_to_date('" + det_fec_cie + "','%d/%m/%YYYY'),0,1,0,0,2);";
                acc.dmlSQLvariable(mQuery);

                String strvar = acc.strQuerySQLvariable("select count(cod_mae) from sol_det where cod_mae= " + cod_mae + ";");

                if (strvar.equals(acc.strQuerySQLvariable("select count(cod_mae) from sol_det where cod_mae= " + cod_mae + " and det_sta =5;"))) {
                    mQuery = "update sol_mae set "
                            + "det_sta = 1, "
                            + "fec_cie = str_to_date('" + fec_cie + "','%d/%m/%Y') "
                            + "where cod_mae = " + cod_mae + ";";
                    acc.dmlSQLvariable(mQuery);
                    catdetalles = new CatSolicitudesDetalle();
                    detalles = new ArrayList<>();
                    llenarMaestro();
                } else {
                    mQuery = "select (" + strvar + "-"
                            + acc.strQuerySQLvariable("select count(cod_mae) from sol_det where cod_mae= " + cod_mae + " and det_sta =5;") + "-"
                            + " count(cod_mae)) as total from sol_det where cod_mae=" + cod_mae + " and det_sta = 4;";
                    if ("0".equals(acc.strQuerySQLvariable(mQuery))) {
                        mQuery = "update sol_mae set "
                                + "det_sta = 5, "
                                + "fec_cie = str_to_date('" + fec_cie + "','%d/%m/%Y') "
                                + "where cod_mae = " + cod_mae + ";";
                        acc.dmlSQLvariable(mQuery);
                        catdetalles = new CatSolicitudesDetalle();
                        detalles = new ArrayList<>();
                        llenarMaestro();
                    } else {
                        catdetalles = new CatSolicitudesDetalle();
                        llenarDetalles();
                    }
                }

                acc.Desconectar();
                det_can_ent = "";
                addMessage("Guardar Solicitud", "Cambios Almacenados con Éxito.", 1);
            }

        } catch (Exception e) {
            System.out.println("Error al Guardar Cambios en Seguimiento." + e.getMessage() + " Query: " + mQuery);
        }
    }

    public boolean validarCancelado() {
        boolean mvalidar = true;

        if ("".equals(det_can_ent)) {
            det_can_ent = "0";
        }
        if ("".equals(cod_mae) || "0".equals(cod_mae)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Solicitud.", 2);
        }

        if ("".equals(mdt_cod_det) || "0".equals(mdt_cod_det)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Item de la Solicitud.", 2);
        }

        return mvalidar;
    }

    public void deshacer() {
        String mQuery = "", mdetsta;
        try {
            if (validardeshacer()) {
                Accesos acc = new Accesos();

                acc.Conectar();
                mQuery = "update sol_mae set "
                        + "det_sta = 4 "
                        + "where cod_mae = " + catdetalleshistoria.getCod_mae() + ";";
                acc.dmlSQLvariable(mQuery);

                mQuery = "update sol_det set "
                        + "det_can_ent = (det_can_ent - " + catdetalleshistoria.getDet_can_ent() + "), "
                        + "det_can_pen = (det_can_pen + " + catdetalleshistoria.getDet_can_ent() + ") "
                        + "where cod_mae= " + catdetalleshistoria.getCod_mae()
                        + " and cod_det=" + catdetalleshistoria.getCod_det() + ";";
                acc.dmlSQLvariable(mQuery);

                mdetsta = acc.strQuerySQLvariable("select (det_can_sol - det_can_pen) as resta from sol_det where cod_mae= "
                        + catdetalleshistoria.getCod_mae()
                        + " and cod_det=" + catdetalleshistoria.getCod_det() + ";");

                if ("0".equals(mdetsta)) {
                    String countcompra = acc.strQuerySQLvariable("select count(cod_mae) as resta from sol_com where cod_mae= "
                            + catdetalleshistoria.getCod_mae()
                            + " and cod_det=" + catdetalleshistoria.getCod_det() + ";");

                    String countcotiza = acc.strQuerySQLvariable("select count(cod_mae) as resta from sol_cot where cod_mae= "
                            + catdetalleshistoria.getCod_mae()
                            + " and cod_det=" + catdetalleshistoria.getCod_det() + ";");

                    if ("0".equals(countcompra) && "0".equals(countcompra)) {
                        mQuery = "update sol_det set "
                                + "det_sta = 0 "
                                + "where cod_mae= " + catdetalleshistoria.getCod_mae() + " "
                                + "and cod_det=" + catdetalleshistoria.getCod_det() + ";";
                        acc.dmlSQLvariable(mQuery);
                    }
                    if (!"0".equals(countcompra)) {
                        mQuery = "update sol_det set "
                                + "det_sta = 2 "
                                + "where cod_mae= " + catdetalleshistoria.getCod_mae() + " "
                                + "and cod_det=" + catdetalleshistoria.getCod_det() + ";";
                        acc.dmlSQLvariable(mQuery);
                    } else {
                        mQuery = "update sol_det set "
                                + "det_sta = 1 "
                                + "where cod_mae= " + catdetalleshistoria.getCod_mae() + " "
                                + "and cod_det=" + catdetalleshistoria.getCod_det() + ";";
                        acc.dmlSQLvariable(mQuery);
                    }

                } else {
                    mQuery = "update sol_det set "
                            + "det_sta = 3 "
                            + "where cod_mae= " + catdetalleshistoria.getCod_mae() + " "
                            + "and cod_det=" + catdetalleshistoria.getCod_det() + ";";
                    acc.dmlSQLvariable(mQuery);
                }

                if ("1".equals(acc.strQuerySQLvariable("select flg_usu_alm from sol_det_rec where cod_rec= " + catdetalleshistoria.getCod_ubi() + ";"))) {
                    //Obtener el correlativo diario del registro actual
                    mQuery = "select ifnull(ord_dia,0) cordia "
                            + "from tbl_pie_his "
                            + "where "
                            + "cod_his = " + catdetalleshistoria.getNomubi() + ";";
                    String cor_dia = acc.strQuerySQLvariable(mQuery);

                    //Borrado Lógico el registro del Histórico
                    mQuery = " update tbl_pie_his set det_sta=1, fec_mod=now(), cod_usu=" + cbean.getCod_usu()
                            + " where cod_his=" + catdetalleshistoria.getNomubi() + ";";
                    acc.dmlSQLvariable(mQuery);

                    // Verifica si hay registros anteriores y toma sus valores
                    mQuery = "select count(ord_dia) as contador "
                            + "from tbl_pie_his "
                            + "where fec_his = STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                            + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                            + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                            + "and det_sta=0;";
                    String contador = acc.strQuerySQLvariable(mQuery);

                    Double cPromedio = 0.0, nuevacantidad = 0.0;

                    if ("0".equals(contador)) {
                        mQuery = "select count(ord_dia) as contador "
                                + "from tbl_pie_his "
                                + "where fec_his < STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                + "and det_sta=0;";
                        contador = acc.strQuerySQLvariable(mQuery);
                        if ("0".equals(contador) == false) {
                            mQuery = "select ifnull(cos_pro,0) as cpromedio "
                                    + "from tbl_pie_his where fec_his < STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                    + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                    + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                    + "and det_sta=0 "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;";
                            cPromedio = acc.doubleQuerySQLvariable(mQuery);
                            mQuery = "select ifnull(can_exi,0) as nuevacantidad "
                                    + "from tbl_pie_his where fec_his < STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                    + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                    + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                    + "and det_sta=0 "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;";
                            nuevacantidad = acc.doubleQuerySQLvariable(mQuery);

                        }
                    } else {
                        mQuery = "select ifnull(cos_pro,0) as cpromedio "
                                + "from tbl_pie_his where fec_his = STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                + "and ord_dia < " + cor_dia + " "
                                + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                + "and det_sta=0 "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;";
                        cPromedio = acc.doubleQuerySQLvariable(mQuery);
                        mQuery = "select ifnull(can_exi,0) as nuevacantidad "
                                + "from tbl_pie_his where fec_his=STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                + "and ord_dia < " + cor_dia + " "
                                + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                + "and det_sta=0 "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;";
                        nuevacantidad = acc.doubleQuerySQLvariable(mQuery);

                    }

                    // Verifica si hay registros posteriores y si los hay actualiza a partir de la fecha de Transacción
                    String contasiguientes = acc.strQuerySQLvariable("select count(cod_his) "
                            + "from tbl_pie_his "
                            + "where fec_his=STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                            + "and ord_dia >" + cor_dia + " "
                            + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                            + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                            + "and det_sta = 0 "
                            + ";");
                    contasiguientes = String.valueOf(
                            Integer.valueOf(contasiguientes)
                            + Integer.valueOf(acc.strQuerySQLvariable("select count(cod_his) "
                                    + "from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                    + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                    + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                    + "and det_sta = 0 "
                                    + ";")));

                    //Double nuevacantidad = mNuevaExistencia;
                    if ("0".equals(contasiguientes) == false) {
                        try {
                            historico = new ArrayList<>();

                            //Double cos_uni_sal = 0.0;
                            ResultSet resvariable;
                            resvariable = acc.querySQLvariable(" select * from ("
                                    + "select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his = STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                    + "and ord_dia >" + cor_dia + " "
                                    + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                    + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                    + "and det_sta = 0 "
                                    + "UNION ALL "
                                    + "select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + catdetalleshistoria.getFec_cie() + "','%d/%m/%Y') "
                                    + "and cod_pai =" + catdetalleshistoria.getCod_pai() + " "
                                    + "and cod_pie=" + catdetalleshistoria.getCod_ite() + " "
                                    + "and det_sta = 0 ) as tabla "
                                    + "order by fec_his asc,"
                                    + "ord_dia asc"
                                    + ";");
                            while (resvariable.next()) {
                                historico.add(new CatHistorico(
                                        resvariable.getString(1),
                                        resvariable.getString(2),
                                        resvariable.getString(3),
                                        resvariable.getString(4),
                                        resvariable.getString(5),
                                        resvariable.getString(6),
                                        resvariable.getString(7),
                                        resvariable.getString(8),
                                        resvariable.getString(9),
                                        resvariable.getString(10),
                                        resvariable.getString(11),
                                        resvariable.getString(12),
                                        resvariable.getString(13),
                                        resvariable.getString(14)
                                ));
                            }

                            for (CatHistorico seriehistorica1 : historico) {
                                if ("0".equals(seriehistorica1.getFlg_ing())) {
                                    cPromedio = (cPromedio * nuevacantidad + Double.valueOf(seriehistorica1.getDet_can()) * Double.valueOf(seriehistorica1.getDet_cos())) / (nuevacantidad + Double.valueOf(seriehistorica1.getDet_can()));
                                    nuevacantidad = nuevacantidad + Double.valueOf(seriehistorica1.getDet_can());
                                    //cos_uni_sal = 0.0;
                                    //cunitario = Double.valueOf(seriehistorica1.getDet_cos());
                                } else {
                                    nuevacantidad = nuevacantidad - Double.valueOf(seriehistorica1.getDet_can());
                                    //cos_uni_sal = cPromedio;
                                }
                                mQuery = "update tbl_pie_his set "
                                        + "cos_pro= " + cPromedio + " ,"
                                        + "can_exi= " + nuevacantidad + " "
                                        + "where "
                                        + "cod_his = " + seriehistorica1.getCod_his()
                                        + ";";
                                acc.dmlSQLvariable(mQuery);
                            }

                        } catch (Exception e) {
                            System.out.println("Error en actualización de costos posteriores. " + e.getMessage());
                        }

                    }

                    // Tratamiento tabla bol_exi_pai
                    String mContador = acc.strQuerySQLvariable("select count(cod_pie) as contador from bol_exi_pai "
                            + "where "
                            + "cod_pai=" + catdetalleshistoria.getCod_pai() + " "
                            + "and ing_sal=0 "
                            + "and cod_pie=" + catdetalleshistoria.getCod_ite()
                            + ";");

                    if (!"0".equals(mContador)) {

                        mQuery = "update bol_exi_pai set "
                                + "det_exi= (det_exi - " + catdetalleshistoria.getDet_can_ent() + ") "
                                + "where "
                                + "cod_pai=" + catdetalleshistoria.getCod_pai() + " "
                                + "and ing_sal=0 "
                                + "and cod_pie=" + catdetalleshistoria.getCod_ite() + ";";

                    }

                    acc.dmlSQLvariable(mQuery);

                    // Tratamiento tabla tbl_existencias
                    mContador = acc.strQuerySQLvariable("select count(cod_exi) as contador from tbl_existencias "
                            + "where "
                            + "cod_pai=" + catdetalleshistoria.getCod_pai() + " "
                            + "and cod_bod=" + catdetalleshistoria.getCod_bod() + " "
                            + "and cod_ubi=" + catdetalleshistoria.getExtra1() + " "
                            + "and cod_pie=" + catdetalleshistoria.getCod_ite()
                            + ";");

                    if (!"0".equals(mContador)) {

                        mQuery = " update tbl_existencias set det_can=(det_can-" + catdetalleshistoria.getDet_can_ent() + ") "
                                + " where cod_pai=" + catdetalleshistoria.getCod_pai() + " and cod_bod = " + catdetalleshistoria.getCod_bod()
                                + " and cod_ubi = " + catdetalleshistoria.getExtra1()
                                + " and cod_pie=" + catdetalleshistoria.getCod_ite() + " ;";

                    }
                    acc.dmlSQLvariable(mQuery);
                    mQuery = "delete from tbl_pie_his where cod_his= " + catdetalleshistoria.getNomubi() + ";";
                    acc.dmlSQLvariable(mQuery);
                }

                mQuery = "delete from sol_det_rec where cod_rec= " + catdetalleshistoria.getCod_ubi() + ";";
                acc.dmlSQLvariable(mQuery);

                cod_mae = "";
                apr_cod_mae = "";
                his_cod_mae = "";
                his_cod_det = "";
                det_sta = "";
                det_can_sol = "";
                det_can_ent = "";
                det_can_pen = "";
                mdt_cod_det = "";
                mdt_cod_ite = "";
                mdt_des_ite = "";
                mdt_non_sto = "1";
                mdt_det_sta = "";
                mdt_cos_uni = "0.0";
                cod_pai = "";
                cod_bod = "0";
                des_ubi = "";
                cod_alt = "";
                idbuscar = "";

                detalles = new ArrayList<>();
                detallesaprobar = new ArrayList<>();

                llenarMaestro();
                llenarMaestroAprobar();
                llenarDetallesHistoria();

                addMessage("Deshacer Cambios Solicitud", "Cambios Almacenados con Éxito.", 1);

            }
        } catch (Exception e) {
            System.out.println("Error en Deshacer Cambios Solicitud. " + e.getMessage());
            addMessage("Guardar Cambios Solicitud", "Error al Almacenar la Información.", 2);
        }
    }

    public boolean validardeshacer() {
        boolean mvalidar = true;

        if ("".equals(his_cod_mae) || "0".equals(his_cod_mae)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Solicitud.", 2);
        }

        if ("".equals(his_cod_det) || "0".equals(his_cod_det)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Item de la Solicitud.", 2);
        }

        return mvalidar;
    }

    //********************* Controles *****************************
    public void onRowSelect(SelectEvent event) {
        cod_mae = ((CatSolicitudes) event.getObject()).getCod_mae();
        det_sta = ((CatSolicitudes) event.getObject()).getDet_sta();
        cod_pai = ((CatSolicitudes) event.getObject()).getCod_pai();
        cod_alt = ((CatSolicitudes) event.getObject()).getCod_alt();

        mfecha = Date.from(Instant.now());
        mfecha2 = Date.from(Instant.now());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        det_fec_cie = format.format(mfecha);
        fec_cie = format.format(mfecha2);
        recibidapor = "0";

        llenarDetalles();
        llenarBodegas();
        ubicaciones = new ArrayList<>();
        catdetalles = new CatSolicitudesDetalle();
        catcotizaciones = new CatCotizaciones();
        catcompras = new CatCompras();
        cotizaciones = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public void onRowSelectDetalle(SelectEvent event) {
        det_can_sol = ((CatSolicitudesDetalle) event.getObject()).getDet_can_sol();
        //det_can_ent = ((CatSolicitudesDetalle) event.getObject()).getDet_can_ent();
        det_can_pen = ((CatSolicitudesDetalle) event.getObject()).getDet_can_pen();
        mdt_cod_det = ((CatSolicitudesDetalle) event.getObject()).getCod_det();
        mdt_cod_ite = ((CatSolicitudesDetalle) event.getObject()).getCod_ite();
        mdt_des_ite = ((CatSolicitudesDetalle) event.getObject()).getDes_ite();
        mdt_non_sto = ((CatSolicitudesDetalle) event.getObject()).getNon_sto();
        mdt_det_sta = ((CatSolicitudesDetalle) event.getObject()).getDet_sta();
        mdt_cos_uni = ((CatSolicitudesDetalle) event.getObject()).getCos_uni();

        det_can_ent = det_can_pen;

        llenarCompras();
        llenarCotizaciones();

        cot_cod_cor = "0";
        com_cod_cor = "0";
        cot_cod_pro = "0";
        com_cod_pro = "0";
        cot_det_cot = "";
        com_det_com = "";
        com_can_com = "";

    }

    public void onRowUnselect(UnselectEvent event) {
        detalles = new ArrayList<>();
        compras = new ArrayList<>();
        cotizaciones = new ArrayList<>();
        catdetalles = new CatSolicitudesDetalle();
    }

    public void onRowUnselectDetalle(UnselectEvent event) {
        compras = new ArrayList<>();
        cotizaciones = new ArrayList<>();
    }

    public void onRowAprobarSelect(SelectEvent event) {
        apr_cod_mae = ((CatSolicitudes) event.getObject()).getCod_mae();
        cod_alt = ((CatSolicitudes) event.getObject()).getCod_alt();
        llenarDetallesAprobar();
    }

    public void onRowAprobarUnselect(UnselectEvent event) {
        detallesaprobar = new ArrayList<>();
    }

    public void onRowHistoriaSelect(SelectEvent event) {
        his_cod_mae = ((CatSolicitudes) event.getObject()).getCod_mae();
        llenarDetallesHistoria();
    }

    public void onRowHistoriaUnselect(UnselectEvent event) {
        detalleshistoria = new ArrayList<>();
    }

    public void onRowHistoriaDetalleSelect(SelectEvent event) {
        his_cod_det = ((CatSolicitudesDetalleHistoria) event.getObject()).getCod_det();
    }

    public void onRowHistoriaDetalleUnselect(UnselectEvent event) {
        his_cod_det = "";
    }

    public void onRowSelectCotizacion(SelectEvent event) {
        cot_cod_cor = ((CatCotizaciones) event.getObject()).getCor_det();
        cot_cod_pro = ((CatCotizaciones) event.getObject()).getCod_pro();
        cot_det_cot = ((CatCotizaciones) event.getObject()).getDet_cot();
    }

    public void onRowUnselectCotizacion(UnselectEvent event) {
        cot_cod_cor = "";
        cot_cod_pro = "0";
        cot_det_cot = "";
    }

    public void onRowSelectCompras(SelectEvent event) {
        com_cod_cor = ((CatCompras) event.getObject()).getCor_com();
        com_cod_pro = ((CatCompras) event.getObject()).getCod_pro();
        com_det_com = ((CatCompras) event.getObject()).getDet_com();
        com_can_com = ((CatCompras) event.getObject()).getCan_com();
    }

    public void onRowUnselectCompras(UnselectEvent event) {
        com_cod_cor = "";
        com_cod_pro = "0";
        com_det_com = "";
        com_can_com = "";
    }

    public void dateCieSelected(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fec_cie = format.format(date);
        det_fec_cie = format.format(date);
    }

    public void onTabChange(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tabSegSol":
                tabindex = "0";
                break;
            case "tabAproSol":
                tabindex = "1";
                RequestContext.getCurrentInstance().execute("PF('wvSegui').clearFilters()");
                break;
            case "tabHistoSol":
                tabindex = "2";
                RequestContext.getCurrentInstance().execute("PF('wvSegui').clearFilters()");
                break;
        }
        //System.out.println(tabindex);
        //RequestContext.getCurrentInstance().update(":frmListaEquipos:tvLE");
    }

    public void onTabChange2(TabChangeEvent event) {
        switch (event.getTab().getId()) {
            case "tabSegCot":
                tabindex2 = "0";
                break;
            case "tabSegCom":
                tabindex2 = "1";
                break;
            case "panentregas":
                tabindex2 = "2";
                break;
        }
        //System.out.println(tabindex);
        //RequestContext.getCurrentInstance().update(":frmListaEquipos:tvLE");
    }

    public void onSelectBodega() {
        des_ubi = "";
    }

    public void onSelectDestino() {
        if ("false".equals(booledit)) {
            booledit = "true";
        } else {
            booledit = "false";
        }
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

    //******************* Reporte ***************************************
    public void limpiar() {
        if ("".equals(apr_cod_mae) || "0".equals(apr_cod_mae)) {
            addMessage("Imprimir Solicitud", "Debe elegir un Registro.", 2);
            RequestContext.getCurrentInstance().update("frmSeguimiento");
            RequestContext.getCurrentInstance().execute("PF('wCodImp').hide()");
        } else {
            RequestContext.getCurrentInstance().execute("PF('wCodImp').show()");
        }
    }

    public void cerrarcodimp() {
        apr_cod_mae = "";
        cod_alt = "";
        llenarMaestroAprobar();
        catmaestroaprobar = new CatSolicitudes();
        detallesaprobar = new ArrayList<>();
    }

    public void validarcodimp() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        try {
            mQuery = "select count(cod_alt) from sol_mae where UPPER(cod_alt)='" + cod_alt.replace("'", "").toUpperCase() + "' and cod_mae <> " + apr_cod_mae + ";";
            if ("0".equals(mAccesos.strQuerySQLvariable(mQuery))) {
                if ("".equals(cod_alt)) {
                    addMessage("Modificar Solicitud", "Código de Impresión Vacío.", 2);
                } else {
                    addMessage("Modificar Solicitud", "Código de Impresión Válido.", 1);
                }
            } else {
                addMessage("Modificar Solicitud", "Código de Impresión ya Existe.", 2);
            }
        } catch (Exception e) {
            addMessage("Modificar Solicitud", "Error al Validar Código de Impresión. " + e.getMessage(), 2);
            System.out.println("Error al Validar Código de Impresión en Seguimiento Solicitudes. " + e.getMessage() + " Query: " + mQuery);
        }

        mAccesos.Desconectar();

    }

    public void editarcodimp() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        try {
            mQuery = "select count(cod_alt) from sol_mae where UPPER(cod_alt)='" + cod_alt.trim().replace("'", "").toUpperCase() + "' and cod_mae <> " + apr_cod_mae + ";";
            if ("0".equals(mAccesos.strQuerySQLvariable(mQuery))) {
                mQuery = "update sol_mae set cod_alt='" + cod_alt + "' where cod_mae=" + apr_cod_mae + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Modificar Solicitud", "Código de Impresión Actualizado.", 1);
            } else if ("".equals(cod_alt.trim())) {
                mQuery = "update sol_mae set cod_alt='' where cod_mae=" + apr_cod_mae + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Modificar Solicitud", "Código de Impresión Actualizado.", 1);
            } else {
                addMessage("Modificar Solicitud", "Código de Impresión ya Existe.", 2);
            }
        } catch (Exception e) {
            addMessage("Modificar Solicitud", "Error al Actualizar Código de Impresión. " + e.getMessage(), 2);
            System.out.println("Error al Actualizar Código de Impresión en Seguimiento Solicitudes. " + e.getMessage() + " Query: " + mQuery);
        }

        mAccesos.Desconectar();

    }

    public void ejecutarreporte() {
        try {
            if (!"".equals(apr_cod_mae) && !"0".equals(apr_cod_mae)) {
                editarcodimp();
                paramRepVarios();
                verPDF();
            } else {
                addMessage("Imprimir Solicitud", "Debe elegir un Registro.", 2);
            }
        } catch (Exception e) {
            System.out.println("Error en EjecutarReporte Seguimiento Solicitudes" + e.getMessage());
        }

    }

    public void paramRepVarios() {
        parametros = new HashMap<>();
        parametros.put("solicitud", apr_cod_mae);
        nombrereporte = "/reportes/solicitud.jasper";
        nombreexportar = "Solicitud_" + apr_cod_mae;

    }

    public void verPDF() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, Conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();
        } catch (JRException | IOException e) {
            System.out.println("Error en verPDF en Seguimiento Solicitudes." + e.getMessage());
        }
    }

}
