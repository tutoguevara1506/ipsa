package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped

public class ManTblPiezas implements Serializable {

    private static final long serialVersionUID = 7997486784762638L;
    @Inject
    Login cbean;
    private List<CatHistorico> historico;
    private CatPiezasConExistencia catpiezasconexistencia;
    private List<CatPiezasConExistencia> existencias;
    private List<CatPaises> paises;
    private List<CatProveedores> proveedores;
    private List<CatMovimientos> movimientos;
    private List<CatMovimientos> movimientostotal;
    private CatPiezasAbreviado catpiezasabreviado;
    private List<CatPiezasAbreviado> piezas;
    private List<CatBodegas> bodegas;
    private List<CatUbicaciones> ubicaciones;
    private List<CatSolicitudes> solicitudes;
    private List<CatSolicitudesDetalle> solicitudesdetalle;

    private CatTblPiezas cattblpiezas;
    private List<CatTblPiezas> piezasencabezado;
    private CatTblPiezasDetalle cattblpiezasdetalle;
    private List<CatTblPiezasDetalle> piezasdetalle;
    private List<CatTblPiezasDetalle> piezasdetallecopia;
    private String cod_lis_pie, por_qbo, cod_pai, cod_pro, cod_mov, doc_tra,
            cod_sol, fec_tra, det_obs, flg_ing, det_sta;
    private String cod_enc, cod_det, cod_pie, cod_bod, cod_ubi, det_can, det_cos, det_sta2;

    private String id_mae;

    private String boolinout, booleditable, nompai, fecbus, idbus, probus, pobus, movpus, paibus;

    private Date mfecha;

    public ManTblPiezas() {
    }

    public List<CatHistorico> getHistorico() {
        return historico;
    }

    public void setHistorico(List<CatHistorico> historico) {
        this.historico = historico;
    }

    public CatPiezasConExistencia getCatpiezasconexistencia() {
        return catpiezasconexistencia;
    }

    public void setCatpiezasconexistencia(CatPiezasConExistencia catpiezasconexistencia) {
        this.catpiezasconexistencia = catpiezasconexistencia;
    }

    public List<CatPiezasConExistencia> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<CatPiezasConExistencia> existencias) {
        this.existencias = existencias;
    }

    public List<CatPaises> getPaises() {
        return paises;
    }

    public void setPaises(List<CatPaises> paises) {
        this.paises = paises;
    }

    public List<CatProveedores> getProveedores() {
        return proveedores;
    }

    public void setProveedores(List<CatProveedores> proveedores) {
        this.proveedores = proveedores;
    }

    public List<CatMovimientos> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<CatMovimientos> movimientos) {
        this.movimientos = movimientos;
    }

    public List<CatMovimientos> getMovimientostotal() {
        return movimientostotal;
    }

    public void setMovimientostotal(List<CatMovimientos> movimientostotal) {
        this.movimientostotal = movimientostotal;
    }

    public CatPiezasAbreviado getCatpiezasabreviado() {
        return catpiezasabreviado;
    }

    public void setCatpiezasabreviado(CatPiezasAbreviado catpiezasabreviado) {
        this.catpiezasabreviado = catpiezasabreviado;
    }

    public List<CatPiezasAbreviado> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<CatPiezasAbreviado> piezas) {
        this.piezas = piezas;
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

    public List<CatSolicitudes> getSolicitudes() {
        return solicitudes;
    }

    public void setSolicitudes(List<CatSolicitudes> solicitudes) {
        this.solicitudes = solicitudes;
    }

    public List<CatSolicitudesDetalle> getSolicitudesdetalle() {
        return solicitudesdetalle;
    }

    public void setSolicitudesdetalle(List<CatSolicitudesDetalle> solicitudesdetalle) {
        this.solicitudesdetalle = solicitudesdetalle;
    }

    public CatTblPiezas getCattblpiezas() {
        return cattblpiezas;
    }

    public void setCattblpiezas(CatTblPiezas cattblpiezas) {
        this.cattblpiezas = cattblpiezas;
    }

    public List<CatTblPiezas> getPiezasencabezado() {
        return piezasencabezado;
    }

    public void setPiezasencabezado(List<CatTblPiezas> piezasencabezado) {
        this.piezasencabezado = piezasencabezado;
    }

    public CatTblPiezasDetalle getCattblpiezasdetalle() {
        return cattblpiezasdetalle;
    }

    public void setCattblpiezasdetalle(CatTblPiezasDetalle cattblpiezasdetalle) {
        this.cattblpiezasdetalle = cattblpiezasdetalle;
    }

    public List<CatTblPiezasDetalle> getPiezasdetalle() {
        return piezasdetalle;
    }

    public void setPiezasdetalle(List<CatTblPiezasDetalle> piezasdetalle) {
        this.piezasdetalle = piezasdetalle;
    }

    public String getCod_lis_pie() {
        return cod_lis_pie;
    }

    public void setCod_lis_pie(String cod_lis_pie) {
        this.cod_lis_pie = cod_lis_pie;
    }

    public String getPor_qbo() {
        return por_qbo;
    }

    public void setPor_qbo(String por_qbo) {
        this.por_qbo = por_qbo;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getCod_mov() {
        return cod_mov;
    }

    public void setCod_mov(String cod_mov) {
        this.cod_mov = cod_mov;
    }

    public String getDoc_tra() {
        return doc_tra;
    }

    public void setDoc_tra(String doc_tra) {
        this.doc_tra = doc_tra;
    }

    public String getCod_sol() {
        return cod_sol;
    }

    public void setCod_sol(String cod_sol) {
        this.cod_sol = cod_sol;
    }

    public String getFec_tra() {
        return fec_tra;
    }

    public void setFec_tra(String fec_tra) {
        this.fec_tra = fec_tra;
    }

    public String getDet_obs() {
        return det_obs;
    }

    public void setDet_obs(String det_obs) {
        this.det_obs = det_obs;
    }

    public String getFlg_ing() {
        return flg_ing;
    }

    public void setFlg_ing(String flg_ing) {
        this.flg_ing = flg_ing;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getCod_enc() {
        return cod_enc;
    }

    public void setCod_enc(String cod_enc) {
        this.cod_enc = cod_enc;
    }

    public String getCod_det() {
        return cod_det;
    }

    public void setCod_det(String cod_det) {
        this.cod_det = cod_det;
    }

    public String getCod_pie() {
        return cod_pie;
    }

    public void setCod_pie(String cod_pie) {
        this.cod_pie = cod_pie;
    }

    public String getCod_bod() {
        return cod_bod;
    }

    public void setCod_bod(String cod_bod) {
        this.cod_bod = cod_bod;
    }

    public String getCod_ubi() {
        return cod_ubi;
    }

    public void setCod_ubi(String cod_ubi) {
        this.cod_ubi = cod_ubi;
    }

    public String getDet_can() {
        return det_can;
    }

    public void setDet_can(String det_can) {
        this.det_can = det_can;
    }

    public String getDet_cos() {
        return det_cos;
    }

    public void setDet_cos(String det_cos) {
        this.det_cos = det_cos;
    }

    public String getDet_sta2() {
        return det_sta2;
    }

    public void setDet_sta2(String det_sta2) {
        this.det_sta2 = det_sta2;
    }

    public String getId_mae() {
        return id_mae;
    }

    public void setId_mae(String id_mae) {
        this.id_mae = id_mae;
    }

    public String getBoolinout() {
        return boolinout;
    }

    public void setBoolinout(String boolinout) {
        this.boolinout = boolinout;
    }

    public String getBooleditable() {
        return booleditable;
    }

    public void setBooleditable(String booleditable) {
        this.booleditable = booleditable;
    }

    public String getNompai() {
        return nompai;
    }

    public void setNompai(String nompai) {
        this.nompai = nompai;
    }

    public Date getMfecha() {
        return mfecha;
    }

    public void setMfecha(Date mfecha) {
        this.mfecha = mfecha;
    }

    public String getFecbus() {
        return fecbus;
    }

    public void setFecbus(String fecbus) {
        this.fecbus = fecbus;
    }

    public String getIdbus() {
        return idbus;
    }

    public void setIdbus(String idbus) {
        this.idbus = idbus;
    }

    public String getProbus() {
        return probus;
    }

    public void setProbus(String probus) {
        this.probus = probus;
    }

    public String getPobus() {
        return pobus;
    }

    public void setPobus(String pobus) {
        this.pobus = pobus;
    }

    public String getMovpus() {
        return movpus;
    }

    public void setMovpus(String movpus) {
        this.movpus = movpus;
    }

    public String getPaibus() {
        return paibus;
    }

    public void setPaibus(String paibus) {
        this.paibus = paibus;
    }

    //*********************** Configuraciones Principal ********************************
    public void iniciarventana() {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        mfecha = Date.from(Instant.now());
        cod_lis_pie = "";
        por_qbo = "";
        cod_pai = cbean.getCod_pai();
        cod_pro = "";
        cod_mov = "";
        doc_tra = "";
        cod_sol = "0";
        fec_tra = format.format(mfecha);
        det_obs = "";
        flg_ing = "0";
        det_sta = "0";
        cod_enc = "";
        cod_det = "";
        cod_pie = "0";
        cod_bod = "0";
        cod_ubi = "";
        det_can = "";
        det_cos = "";
        det_sta2 = "0";
        id_mae = "";
        boolinout = "false";
        booleditable = "true";
        nompai = "";
        piezasdetalle = new ArrayList<>();
        piezasdetallecopia = new ArrayList<>();
        bodegas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
        proveedores = new ArrayList<>();
        catpiezasabreviado = new CatPiezasAbreviado();
        piezas = new ArrayList<>();
        llenarPaises();
        llenarProveedores();
        llenarMovimientos();
        //llenarPiezas();
        llenarBodegas();
        //llenarEncabezadoSol();
    }

    public void cerrarventana() {
        cod_lis_pie = "";
        por_qbo = "";
        cod_pai = "";
        cod_pro = "0";
        cod_mov = "0";
        doc_tra = "";
        cod_sol = "0";
        fec_tra = "";
        det_obs = "";
        flg_ing = "0";
        det_sta = "0";
        cod_enc = "";
        cod_det = "";
        cod_pie = "0";
        cod_bod = "0";
        cod_ubi = "0";
        det_can = "";
        det_cos = "";
        det_sta2 = "0";
        id_mae = "0";
        boolinout = "false";
        booleditable = "true";
        nompai = "";
        paises = null;
        proveedores = null;
        piezas = null;
        bodegas = null;
        ubicaciones = null;
        solicitudes = null;
        solicitudesdetalle = null;
        piezasencabezado = null;
        piezasdetalle = null;
        piezasdetallecopia = null;
    }

    public void nuevo() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        mfecha = Date.from(Instant.now());

        cod_lis_pie = "";
        por_qbo = "";
        //cod_pai = cbean.getCod_pai();
        cod_pro = "0";
        cod_mov = "0";
        doc_tra = "";
        cod_sol = "0";
        fec_tra = format.format(mfecha);
        det_obs = "";
        flg_ing = "0";
        det_sta = "0";
        cod_enc = "";
        cod_det = "";
        cod_pie = "0";
        cod_bod = "0";
        cod_ubi = "";
        det_can = "";
        det_cos = "";
        det_sta2 = "0";
        id_mae = "0";
        boolinout = "false";
        booleditable = "true";
        nompai = "";

        solicitudesdetalle = null;
        cattblpiezas = null;
        cattblpiezasdetalle = null;
        piezas.clear();
        piezasdetalle.clear();
        piezasdetallecopia.clear();
        llenarMovimientos();
    }

    //*********************** Configuraciones Búsqueda ********************************
    public void iniciarventanabuscarid() {
        cattblpiezas = new CatTblPiezas();
        piezasencabezado = new ArrayList<>();
        idbus = "";
        probus = "0";
        pobus = "";
        movpus = "0";
        paibus = cod_pai;
        fecbus = "";
        llenarMovimientosTotal();
    }

    public void cerrarventanaid() {
        cattblpiezas = null;
        piezasencabezado.clear();
        idbus = "";
        probus = "0";
        pobus = "";
        movpus = "0";
        paibus = "";
        fecbus = "";
    }

    public void buscarid() {
        String mQuery = "";
        if (!"".equals(idbus)) {
            mQuery = mQuery + " and enc.cod_lis_pie= " + idbus + " ";
        }
        if (!"0".equals(probus)) {
            mQuery = mQuery + " and enc.cod_pro= " + probus + " ";
        }
        if (!"".equals(pobus)) {
            mQuery = mQuery + " and enc.por_qbo= " + pobus + " ";
        }
        if (!"0".equals(movpus)) {
            mQuery = mQuery + " and enc.cod_mov= " + movpus + " ";
        }
        if (!"".equals(fecbus)) {
            mQuery = mQuery + " and enc.fec_tra= date_format(" + fecbus + ",'%d/%m/%Y') ";
        }

        //System.out.println(mQuery);
        llenarEncabezado(mQuery);
    }

    //*********************** Configuraciones Existencias ********************************
    public void iniciarventanaexistencia() {
        catpiezasconexistencia = new CatPiezasConExistencia();
        existencias = new ArrayList<>();
        Accesos acc = new Accesos();
        acc.Conectar();
        nompai = acc.strQuerySQLvariable("select nom_alm from cat_alm where cod_alm=" + cod_pai + ";");
        acc.Desconectar();
    }

    public void buscarexistencia() {
        String mQuery = "";

        if (!"0".equals(cod_pie)) {
            if (!"0".equals(cod_bod)) {
                if (!"0".equals(cod_ubi)) {
                    mQuery = "and exis.cod_pie=" + cod_pie + " and exis.cod_bod=" + cod_bod + " and exis.cod_ubi=" + cod_ubi;
                } else {
                    mQuery = "and exis.cod_pie=" + cod_pie + " and exis.cod_bod=" + cod_bod;
                }
            } else if (!"0".equals(cod_ubi)) {
                mQuery = "and exis.cod_pie=" + cod_pie + " and exis.cod_ubi=" + cod_ubi;
            } else {
                mQuery = "and exis.cod_pie=" + cod_pie;
            }
        } else if (!"0".equals(cod_bod)) {
            if (!"0".equals(cod_ubi)) {
                mQuery = "and exis.cod_bod=" + cod_bod + " and exis.cod_ubi=" + cod_ubi;
            } else {
                mQuery = "and exis.cod_bod=" + cod_bod;
            }
        } else if (!"0".equals(cod_ubi)) {
            mQuery = "and exis.cod_ubi=" + cod_ubi;
        } else {
            mQuery = "";
        }

        llenarExistencias(mQuery);
    }

    public void cerrarventanaexistencia() {
        nompai = "";
        catpiezasconexistencia = null;
        existencias.clear();
    }

    public void llenarExistencias(String mWhere) {
        try {
            catpiezasconexistencia = null;
            existencias.clear();

            String mQuery = "select "
                    + "exis.cod_pie,  "
                    + "exis.cod_pai, "
                    + "pie.cod_ref as bodega,  "
                    + "0 as ubicacion,  "
                    + "(select ifnull(sum(res.det_can),0) as suma  "
                    + "from tbl_res as res  "
                    + "where res.cod_pie = exis.cod_pie  "
                    + "and res.cod_pai = exis.cod_pai) as reserva, "
                    + "exis.existencias, "
                    + "pie.nom_pie,  "
                    + "pai.nom_alm,  "
                    + "'' as nombod,  "
                    + "'' as nomubi  "
                    + "from ( "
                    + "select exi.cod_pai, exi.cod_pie, 0 as ingsal,  "
                    + "sum(case exi.ing_sal when 0 then exi.det_exi when 1 then exi.det_exi*-1 end) as existencias  "
                    + "FROM bol_exi_pai as exi group by exi.cod_pai, exi.cod_pie "
                    + ") as exis "
                    + "left join cat_pie as pie on exis.cod_pie = pie.cod_pie  "
                    + "left join cat_alm as pai on exis.cod_pai = pai.cod_alm "
                    + "where exis.cod_pai=" + cod_pai + " "
                    + mWhere + " "
                    + "order by exis.cod_pie,exis.cod_pai;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                existencias.add(new CatPiezasConExistencia(
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
            System.out.println("Error en el llenado de Existencias en ManTblPiezas. " + e.getMessage());
        }
    }

    //*********************** Llenado de Lista de Catálogos ********************************
    public void llenarPaises() {
        try {
            //Bodegas
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
            ubicaciones.clear();
            piezasdetalle.clear();
            //proveedores.clear();
        } catch (Exception e) {
            System.out.println("Error en el llenado de Paises en ManTblPiezas. " + e.getMessage());
        }
    }

    public void llenarBodegas() {
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
            ubicaciones.clear();
            piezas.clear();
        } catch (Exception e) {
            System.out.println("Error en el llenado de Bodegas en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
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
            piezas.clear();
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
            System.out.println("Error en el llenado de Proveedores en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarMovimientos() {
        String mQuery = "", tipmov = "";
        try {
            if ("false".equals(boolinout)) {
                tipmov = "0";
            } else {
                tipmov = "1";
            }

            movimientos = new ArrayList<>();

            mQuery = "select id_mov, nom_mov, "
                    + "case flg_tip when '0' then 'Entrada' when '1' then 'Salida' end as flg_tip "
                    + "from cat_mov "
                    + "where flg_tip = " + tipmov + " "
                    + "order by id_mov;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                movimientos.add(new CatMovimientos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Movimientos en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarMovimientosTotal() {
        String mQuery = "";
        try {

            movimientostotal = new ArrayList<>();

            mQuery = "select id_mov, nom_mov, "
                    + "case flg_tip when '0' then 'Entrada' when '1' then 'Salida' end as flg_tip "
                    + "from cat_mov "
                    + "order by id_mov;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                movimientostotal.add(new CatMovimientos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Movimientos Total en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarPiezas() {
        String mQuery = "";
        try {

            piezas = new ArrayList<>();

            mQuery = "select "
                    + "piezas.cod_pie, "
                    + "piezas.cod_ref, "
                    + "piezas.nom_pie, "
                    + "piezas.existencia, "
                    + "piezas.pendientes "
                    + "from( "
                    + "select "
                    + "pie.cod_pie, "
                    + "pie.cod_ref, "
                    + "pie.nom_pie, "
                    + "ifnull((select exi.can_exi "
                    + "from tbl_pie_his as exi "
                    + "where "
                    + "exi.cod_pie= pie.cod_pie "
                    + "and exi.fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                    + "and exi.det_sta = 0 "
                    + "and exi.cod_pai = " + cod_pai + " "
                    + "order by exi.fec_his desc,"
                    + "exi.ord_dia desc "
                    + "limit 1),0) as existencia,"
                    + "ifnull(("
                    + "select sum(det_can_pen) from sol_det as det "
                    + "left join sol_mae as mae on mae.cod_mae = det.cod_mae "
                    + "where "
                    + "det.cod_ite = pie.cod_pie "
                    + "and det.det_sta <> 2 "
                    + "and mae.det_sta not in (0,1,3) "
                    + "),0) as pendientes "
                    + "from cat_pie as pie ) as piezas "
                    + "order by piezas.cod_pie;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezas.add(new CatPiezasAbreviado(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Piezas en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarPiezasout() {
        String mQuery = "";
        try {

            piezas = new ArrayList<>();

            mQuery = "select "
                    + "piezas.cod_pie, "
                    + "piezas.cod_ref, "
                    + "piezas.nom_pie, "
                    + "piezas.existencia, "
                    + "piezas.pendientes "
                    + "from( "
                    + "select "
                    + "pie.cod_pie, "
                    + "pie.cod_ref, "
                    + "pie.nom_pie, "
                    + "ifnull((select exi.can_exi "
                    + "from tbl_pie_his as exi "
                    + "where "
                    + "exi.cod_pie= pie.cod_pie "
                    + "and exi.fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                    + "and exi.det_sta = 0 "
                    + "and exi.cod_pai = " + cod_pai + " "
                    + "order by exi.fec_his desc,"
                    + "exi.ord_dia desc "
                    + "limit 1),0) as existencia,"
                    + "ifnull(("
                    + "select sum(det_can_pen) from sol_det as det "
                    + "left join sol_mae as mae on mae.cod_mae = det.cod_mae "
                    + "where "
                    + "det.cod_ite = pie.cod_pie "
                    + "and det.det_sta <> 2 "
                    + "and mae.det_sta not in (0,1,3) "
                    + "),0) as pendientes "
                    + "from cat_pie as pie ) as piezas "
                    + "where "
                    + "piezas.existencia > 0 "
                    + "order by piezas.cod_pie;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezas.add(new CatPiezasAbreviado(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Piezas en ManTblPiezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

//**************************** Llenado de Catálogo Principal ***********
    public void llenarEncabezado(String mWhere) {
        try {
            cattblpiezas = null;
            piezasencabezado.clear();

            String mQuery = "select enc.cod_lis_pie, enc.por_qbo, enc.cod_pai, enc.cod_pro, enc.cod_mov, "
                    + "enc.doc_tra, enc.cod_sol, date_format(enc.fec_tra,'%d/%m/%Y') as fec_tra, enc.det_obs, "
                    + "enc.flg_ing, enc.det_sta, "
                    + "pai.nom_alm, ifnull(pro.nom_pro,'Ninguno') as nom_pro, mov.nom_mov, "
                    + "case enc.det_sta when 0 then 'ACTIVO' when 1 then 'ANULADO' end as detsta "
                    + "from tbl_pie as enc "
                    + "left join cat_alm as pai on enc.cod_pai = pai.cod_alm "
                    + "left join cat_pro as pro on enc.cod_pro = pro.cod_pro "
                    + "left join cat_mov as mov on enc.cod_mov = mov.id_mov "
                    + "where enc.cod_pai = " + paibus + " and enc.det_sta = 0 "
                    + "and enc.cod_mov <> 0 "
                    + mWhere + " "
                    + "order by enc.cod_pai, enc.flg_ing, enc.fec_tra, enc.cod_pro, enc.cod_lis_pie;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezasencabezado.add(new CatTblPiezas(
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
                        resVariable.getString(15)
                ));
            }
            mAccesos.Desconectar();
        } catch (Exception e) {
            System.out.println("Error en el llenado de Encabezado en ManTblPiezas. " + e.getMessage());
        }
    }

    public void llenarDetalle() {
        try {
            cattblpiezasdetalle = null;
            piezasdetalle.clear();
            piezasdetallecopia.clear();

            String mQuery = "select det.cod_enc, det.cod_det, det.cod_pie, det.cod_bod, det.cod_ubi, "
                    + "det.det_can, det.det_cos, det.det_sta, pie.nom_pie,bod.nom_bod,ubi.nom_ubi "
                    + "from tbl_pie_det as det "
                    + "left join cat_pie as pie on det.cod_pie = pie.cod_pie "
                    + "left join cat_bodegas as bod on det.cod_bod = bod.id_bod "
                    + "left join cat_ubicaciones as ubi on det.cod_bod = ubi.cod_bod and det.cod_ubi = ubi.id_ubi "
                    + "where det.cod_enc = " + cod_lis_pie + " and det.det_sta = 0 "
                    + "order by det.cod_det;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezasdetalle.add(new CatTblPiezasDetalle(
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
                piezasdetallecopia.add(new CatTblPiezasDetalle(
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
            System.out.println("Error en el llenado de Detalle en ManTblPiezas. " + e.getMessage());
        }
    }

//**************************** Operaciones de Grid Detalle ***********
    public void agregardetalle() {
        try {
            if (validardetalle()) {
                Accesos acc = new Accesos();
                acc.Conectar();
                int correlativo = 0, insert = 0;

                for (int i = 0; i < piezasdetalle.size(); i++) {

                    if (cod_pie.equals(piezasdetalle.get(i).getCod_pie())
                            && cod_bod.equals(piezasdetalle.get(i).getCod_bod())
                            && cod_ubi.equals(piezasdetalle.get(i).getCod_ubi())
                            && det_cos.equals(piezasdetalle.get(i).getDet_cos())) {
                        insert = 1;
                        piezasdetalle.set(i, new CatTblPiezasDetalle(
                                piezasdetalle.get(i).getCod_enc(),
                                piezasdetalle.get(i).getCod_det(),
                                piezasdetalle.get(i).getCod_pie(),
                                piezasdetalle.get(i).getCod_bod(),
                                piezasdetalle.get(i).getCod_ubi(),
                                String.valueOf(Integer.valueOf(piezasdetalle.get(i).getDet_can()) + Integer.valueOf(det_can)),
                                piezasdetalle.get(i).getDet_cos(),
                                piezasdetalle.get(i).getDet_sta(),
                                piezasdetalle.get(i).getNompie(),
                                piezasdetalle.get(i).getNombod(),
                                piezasdetalle.get(i).getNomubi()
                        ));
                    }

                    if (Integer.valueOf(piezasdetalle.get(i).getCod_det()) > correlativo) {
                        correlativo = Integer.valueOf(piezasdetalle.get(i).getCod_det());
                    }
                }

                if (insert == 0) {
                    piezasdetalle.add(new CatTblPiezasDetalle(
                            "",
                            String.valueOf(correlativo + 1),
                            cod_pie,
                            cod_bod,
                            cod_ubi,
                            det_can,
                            det_cos,
                            det_sta,
                            acc.strQuerySQLvariable("select nom_pie from cat_pie where cod_pie =" + cod_pie + ";"),
                            acc.strQuerySQLvariable("select nom_bod from cat_bodegas where cod_pai = " + cod_pai + " and id_bod =" + cod_bod + ";"),
                            acc.strQuerySQLvariable("select nom_ubi from cat_ubicaciones where cod_bod = " + cod_bod + " and id_ubi =" + cod_ubi + ";")
                    ));
                }
                acc.Desconectar();
                cod_pie = "0";
                cod_bod = "0";
                //cod_ubi = "";
                det_can = "";
                det_cos = "";
                det_sta = "0";
                cattblpiezasdetalle = null;

            }
        } catch (Exception e) {
            System.out.println("Error en Agregar Detalle ManTblPiezas." + e.getMessage());
        }
    }

    public boolean validardetalle() {
        boolean mvalidar = true;
        String mQuery = "";

        if ("".equals(det_can)) {
            det_can = "0.0";
        }
        if ("".equals(det_cos)) {
            det_cos = "0.0";
        }

        if ("0".equals(cod_pai)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Bodega.", 2);
        }

        if ("0".equals(cod_bod)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar un Estante.", 2);
        }

        if ("0".equals(cod_pie)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Pieza.", 2);
        }
        if ("0".equals(cod_mov)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe escoger un Movimiento.", 2);
        }

        if ("".equals(det_can)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad mayor que Cero.", 2);
        } else if (Double.valueOf(det_can) <= 0) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Ingresar una Cantidad mayor que Cero.", 2);
        }
        if ("0".equals(cod_ubi) || "".equals(cod_ubi)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Repisa.", 2);
        } else {
            try {
                if ("1".equals(flg_ing)) {
                    Double existencia = 0.0;
                    Accesos acc = new Accesos();
                    mQuery = "select "
                            + "piezas.existencia "
                            + "from("
                            + "select "
                            + "pie.cod_pie, "
                            + "pie.cod_ref,"
                            + "pie.nom_pie,"
                            + "ifnull((select exi.can_exi "
                            + "from tbl_pie_his as exi "
                            + "where "
                            + "exi.cod_pie= pie.cod_pie "
                            + "and exi.fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and exi.flg_ing = 0 "
                            + "and exi.det_sta = 0 "
                            + "and exi.cod_pai = " + cod_pai + " "
                            + "and exi.cod_bod = " + cod_bod + " "
                            + "and exi.des_ubi = " + cod_ubi + " "
                            + "order by exi.fec_his desc, "
                            + "exi.ord_dia desc "
                            + "limit 1),0) as existencia, "
                            + "ifnull((select sum(det_can_pen) from sol_det as det where det.cod_pai = " + cod_pai
                            + " and det.cod_bod = " + cod_bod + " and det.cod_ubi= " + cod_ubi + " and det.cod_ite = pie.cod_pie ),0) as pendientes "
                            + "from cat_pie as pie ) as piezas "
                            + "where "
                            + "piezas.cod_pie = " + cod_pie + ";";

                    if (!piezasdetalle.isEmpty()) {
                        for (int i = 0; i < piezasdetalle.size(); i++) {
                            if (cod_pie.equals(piezasdetalle.get(i).getCod_pie())) {
                                existencia = existencia + Double.valueOf(piezasdetalle.get(i).getDet_can());
                            }
                        }
                    }

                    acc.Conectar();
                    existencia = acc.doubleQuerySQLvariable(mQuery) - existencia;
                    acc.Desconectar();

                    if (existencia <= 0.0) {
                        mvalidar = false;

                        addMessage("Validar Datos", "La existencia de esta Pieza es Cero.", 2);

                    } else if (existencia < Double.valueOf(det_can)) {
                        mvalidar = false;
                        addMessage("Validar Datos", "La Cantidad Solicitada sobrepasa la existencia de esta Pieza.", 2);
                    }
                }
            } catch (Exception e) {
                mvalidar = false;
                System.out.println("Error en Validación Existencia agregardetalle de ManInventarioPiezas. " + e.getMessage() + " Query: " + mQuery);
            }
        }
        return mvalidar;
    }

    public void eliminardetalle() {
        if ("".equals(cod_det)) {
            addMessage("Eliminar Detalles", "Debe Seleccionar un detalle para remover.", 2);
        } else {
            for (int i = 0; i < piezasdetalle.size(); i++) {
                if (cod_det.equals(piezasdetalle.get(i).getCod_det())) {
                    piezasdetalle.remove(i);
                    cod_det = "";
                    cod_pie = "0";
                    cod_bod = "0";
                    cod_ubi = "0";
                    det_can = "";
                    det_cos = "";
                    det_sta = "0";
                    cattblpiezasdetalle = null;
                }
            }

        }
    }

//**************************** Operaciones de Mantenimiento de Tablas ***********
    public void guardar() {
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                String mQuery, mQuery2;
                if ("".equals(cod_lis_pie)) {
                    mQuery = "select ifnull(max(cod_lis_pie),0)+1 as codigo from tbl_pie;";
                    cod_lis_pie = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into tbl_pie (cod_lis_pie,por_qbo,cod_pai,cod_pro,cod_mov,"
                            + "doc_tra,cod_sol,fec_tra,det_obs,flg_ing,det_sta) "
                            + "values (" + cod_lis_pie + ",'" + por_qbo + "'," + cod_pai + ","
                            + cod_pro + "," + cod_mov + ",'" + doc_tra + "'," + cod_sol + ","
                            + "STR_TO_DATE('" + fec_tra + "','%d/%m/%Y')" + ",'"
                            + det_obs + "'," + flg_ing + "," + det_sta + ");";
                    mAccesos.dmlSQLvariable(mQuery);
                } else {
                    mQuery = "update tbl_pie SET "
                            + " por_qbo ='" + por_qbo + "' "
                            + ",cod_pai = " + cod_pai
                            + ",cod_pro = " + cod_pro
                            + ",cod_mov = " + cod_mov
                            + ",doc_tra ='" + doc_tra + "' "
                            + ",cod_sol = " + cod_sol
                            + ",fec_tra = " + "STR_TO_DATE('" + fec_tra + "','%d/%m/%Y')" + " "
                            + ",det_obs ='" + det_obs + "' "
                            + ",flg_ing = " + flg_ing
                            + ",det_sta = " + det_sta + " "
                            + "WHERE cod_lis_pie =" + cod_lis_pie + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                    mQuery = " delete from tbl_pie_det where cod_enc=" + cod_lis_pie + ";";
                    mAccesos.dmlSQLvariable(mQuery);
                    // ********************** Borrado Lógico del Histórico *************************************
                    mQuery = " update tbl_pie_his set det_sta=1, fec_mod=now(), cod_usu=" + cbean.getCod_usu()
                            + " where flg_ing=" + flg_ing + " and cod_enc=" + cod_lis_pie + " and det_sta=0;";
                    mAccesos.dmlSQLvariable(mQuery);

                    for (int l = 0; l < piezasdetallecopia.size(); l++) {

                        mQuery = " update bol_exi_pai set det_exi=(det_exi-" + piezasdetallecopia.get(l).getDet_can() + ") "
                                + " where cod_pai=" + cod_pai + " and cod_pie=" + piezasdetallecopia.get(l).getCod_pie() + " and ing_sal=" + flg_ing + ";";

                        mAccesos.dmlSQLvariable(mQuery);
                        if ("0".equals(flg_ing)) {
                            mQuery = " update tbl_existencias set det_can=(det_can-" + piezasdetallecopia.get(l).getDet_can() + ") "
                                    + "where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetallecopia.get(l).getCod_bod() + " "
                                    + "and cod_ubi=" + piezasdetallecopia.get(l).getCod_ubi() + " "
                                    + "and cod_pie=" + piezasdetallecopia.get(l).getCod_pie() + " ;";
                        } else {
                            mQuery = " update tbl_existencias set det_can=(det_can+" + piezasdetallecopia.get(l).getDet_can() + ") "
                                    + "where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetallecopia.get(l).getCod_bod() + " "
                                    + "and cod_ubi=" + piezasdetallecopia.get(l).getCod_ubi() + " "
                                    + "and cod_pie=" + piezasdetallecopia.get(l).getCod_pie() + " ;";
                        }

                        mAccesos.dmlSQLvariable(mQuery);
                    }

                }
                // ******************* Borra Reservas *****************
                mQuery = " delete from tbl_res where cod_lis_pie = " + cod_lis_pie + ";";
                mAccesos.dmlSQLvariable(mQuery);

                //-----------               mAccesos.dmlSQLvariable(mQuery);
                String mValues = "";

                for (int i = 0; i < piezasdetalle.size(); i++) {
//              ********************************** Existencias ****************************************

                    Double mCantidad;
                    mCantidad = Double.valueOf(piezasdetalle.get(i).getDet_can().replace(",", ""));
                    //código correlativo existencia histórica de artículo
                    String cod_cor_exi_art = mAccesos.strQuerySQLvariable("select ifnull(max(cod_his),0)+1 "
                            + "as codigo from tbl_pie_his;");
                    //Código correlativo diario existencia histórica de artículo
                    String cor_dia = mAccesos.strQuerySQLvariable("select ifnull(max(ord_dia),0)+1 "
                            + "as cordia from tbl_pie_his "
                            + "where "
                            + "cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                            + "and fec_his=STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and cod_pai = " + cod_pai + " "
                            + ";");

                    //Costo promedio
                    Double cPromedio, exisAnt, cunitario, mNuevaExistencia;
                    if ("1".equals(cod_cor_exi_art)) {
                        cPromedio = Double.valueOf(piezasdetalle.get(i).getDet_cos().replace(",", ""));
                        exisAnt = 0.0;
                    } else {
                        if ("0".equals(flg_ing)) {
                            cPromedio = mAccesos.doubleQuerySQLvariable("select (ifnull((can_exi*cos_pro),0)+"
                                    + (Double.valueOf(piezasdetalle.get(i).getDet_can()) * Double.valueOf(piezasdetalle.get(i).getDet_cos())) + ")"
                                    + "/(IFNULL(can_exi,0)+" + mCantidad + ") as Cpromedio "
                                    + "from tbl_pie_his "
                                    + "where "
                                    + "cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and flg_ing = 0 "
                                    + "and det_sta = 0 "
                                    + "and cod_pai = " + cod_pai + " "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;");
                        } else {
                            cPromedio = mAccesos.doubleQuerySQLvariable("select ifnull(cos_pro,0) as cospro "
                                    + "from tbl_pie_his "
                                    + "where "
                                    + "cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and flg_ing = 0 "
                                    + "and det_sta = 0 "
                                    + "and cod_pai = " + cod_pai + " "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;");
                        }
                        //Existencia Anterior
                        exisAnt = mAccesos.doubleQuerySQLvariable("select ifnull(can_exi,0) as exisant "
                                + "from tbl_pie_his "
                                + "where "
                                + "cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                + "and fec_his <= STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                + "and det_sta = 0 "
                                + "and cod_pai = " + cod_pai + " "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;");

                    }
                    //Inserta Registro
                    if ("0".equals(flg_ing)) {
                        mNuevaExistencia = (exisAnt + mCantidad);
                    } else {
                        mNuevaExistencia = (exisAnt - mCantidad);
                    }
                    cunitario = Double.valueOf(piezasdetalle.get(i).getDet_cos().replace(",", ""));
                    mQuery2 = " insert into tbl_pie_his (cod_his,cod_pie,fec_his,ord_dia,flg_ing,"
                            + "cod_enc,cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,cod_usu,cod_pai,cod_bod,des_ubi) "
                            + "VALUES (" + cod_cor_exi_art + "," + piezasdetalle.get(i).getCod_pie() + ","
                            + "STR_TO_DATE('" + fec_tra + "','%d/%m/%Y')" + "," + cor_dia + "," + flg_ing + ","
                            + cod_lis_pie + "," + (i + 1) + "," + mCantidad + "," + cunitario + ","
                            + mNuevaExistencia + ","
                            + cPromedio + "," + "0" + "," + "now()" + "," + cbean.getCod_usu() + ","
                            + cod_pai + "," + piezasdetalle.get(i).getCod_bod() + "," + piezasdetalle.get(i).getCod_ubi() + ");";

                    mAccesos.dmlSQLvariable(mQuery2);

                    // Verifica si hay registros posteriores y si los hay actualiza a partir de la fecha de Transacción
                    String contasiguientes = mAccesos.strQuerySQLvariable("select count(cod_his) "
                            + "from tbl_pie_his where fec_his=STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and ord_dia >" + cor_dia + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                            + "and det_sta = 0 "
                            + "and cod_pai = " + cod_pai + " "
                            + ";");
                    contasiguientes = String.valueOf(
                            Integer.valueOf(contasiguientes)
                            + Integer.valueOf(mAccesos.strQuerySQLvariable("select count(cod_his) "
                                    + "from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta = 0 "
                                    + "and cod_pai = " + cod_pai + " "
                                    + ";")));

                    Double nuevacantidad = mNuevaExistencia;
                    if ("0".equals(contasiguientes) == false) {
                        try {
                            historico = new ArrayList<>();

                            //Double cos_uni_sal = 0.0;
                            ResultSet resvariable;
                            resvariable = mAccesos.querySQLvariable("select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
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
                                mQuery2 = "update tbl_pie_his set "
                                        + "cos_pro= " + cPromedio + " ,"
                                        + "can_exi= " + nuevacantidad + " "
                                        + "where "
                                        + "cod_his = " + seriehistorica1.getCod_his()
                                        + ";";
                                mAccesos.dmlSQLvariable(mQuery2);
                            }

                        } catch (Exception e) {
                            System.out.println("Error en actualización de costos posteriores Agregar. " + e.getMessage());
                        }

                    }

                    // Tratamiento tabla bol_exi_pai
                    String mContador = mAccesos.strQuerySQLvariable("select count(cod_pie) as contador from bol_exi_pai "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and ing_sal=" + flg_ing + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie()
                            + ";");

                    if ("0".equals(mContador)) {

                        mQuery2 = "insert into bol_exi_pai(cod_pai,cod_pie,ing_sal,det_exi) "
                                + "VALUES ("
                                + cod_pai + ","
                                + piezasdetalle.get(i).getCod_pie() + ","
                                + flg_ing + ","
                                + piezasdetalle.get(i).getDet_can()
                                + ");";

                    } else {
                        mQuery2 = "update bol_exi_pai set "
                                + "det_exi= (det_exi + " + piezasdetalle.get(i).getDet_can() + ") "
                                + "where "
                                + "cod_pai=" + cod_pai + " "
                                + "and ing_sal=" + flg_ing + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + ";";

                    }

                    mAccesos.dmlSQLvariable(mQuery2);

                    // Tratamiento tabla tbl_existencias
                    mContador = mAccesos.strQuerySQLvariable("select count(cod_exi) as contador from tbl_existencias "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and cod_bod=" + piezasdetalle.get(i).getCod_bod() + " "
                            + "and cod_ubi=" + piezasdetalle.get(i).getCod_ubi() + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie()
                            + ";");

                    if ("0".equals(mContador)) {

                        mQuery2 = "insert into tbl_existencias(cod_exi,cod_pie,cod_pai,cod_bod,cod_ubi,det_can,cos_pro) "
                                + "VALUES ("
                                + mAccesos.strQuerySQLvariable("select (ifnull(max(cod_exi),0) + 1) as codigo from tbl_existencias;") + ","
                                + piezasdetalle.get(i).getCod_pie() + ","
                                + cod_pai + ","
                                + piezasdetalle.get(i).getCod_bod() + ","
                                + piezasdetalle.get(i).getCod_ubi() + ","
                                + piezasdetalle.get(i).getDet_can()
                                + ",0);";

                    } else if ("0".equals(flg_ing)) {
                        mQuery2 = " update tbl_existencias set det_can=(det_can+" + piezasdetalle.get(i).getDet_can() + ") "
                                + "where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetalle.get(i).getCod_bod() + " "
                                + "and cod_ubi=" + piezasdetalle.get(i).getCod_ubi() + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " ;";
                    } else {
                        mQuery2 = " update tbl_existencias set det_can=(det_can-" + piezasdetalle.get(i).getDet_can() + ") "
                                + "where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetalle.get(i).getCod_bod() + " "
                                + "and cod_ubi=" + piezasdetalle.get(i).getCod_ubi() + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " ;";
                    }

                    mAccesos.dmlSQLvariable(mQuery2);

//              ********************************* Fin Existencias ************************************
                    mValues = mValues + "," + "("
                            + cod_lis_pie + ","
                            + (i + 1) + ","
                            + piezasdetalle.get(i).getCod_pie() + ","
                            + piezasdetalle.get(i).getCod_bod() + ",'"
                            + piezasdetalle.get(i).getCod_ubi() + "',"
                            + piezasdetalle.get(i).getDet_can() + ","
                            + piezasdetalle.get(i).getDet_cos() + ","
                            + piezasdetalle.get(i).getDet_sta()
                            + ")";

                }
                // ******************* Inserta Detalles*****************
                mValues = mValues.substring(1);
                mQuery = " insert into tbl_pie_det(cod_enc,cod_det,"
                        + "cod_pie,cod_bod,cod_ubi,det_can,det_cos,det_sta) "
                        + "values " + mValues + ";";

                mAccesos.dmlSQLvariable(mQuery);
                // ******************* Borra Reservas *****************
                mQuery = " delete from tbl_res where cod_lis_pie = " + cod_lis_pie + ";";
                mAccesos.dmlSQLvariable(mQuery);
                //*************** Devolver a estatus normal el registro editado en histórico ******************
                /*mQuery = " update tbl_pie_his set det_sta = 0 "
                        + " where cod_enc=" + cod_lis_pie + ";";
                mAccesos.dmlSQLvariable(mQuery);*/

                mAccesos.Desconectar();
                addMessage("Guardar Almacén", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Almacén", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Almacén. " + e.getMessage());
            }
            nuevo();
        }
    }

    public boolean validardatos() {
        boolean mvalidar = true;
        if (piezasdetalle.isEmpty()) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe agregar al menos un Item a la Lista.", 2);
        }
        if ("0".equals(cod_mov)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe escoger un Movimiento.", 2);
        }
        if ("0".equals(cod_pai)) {
            mvalidar = false;
            addMessage("Validar Datos", "Debe Seleccionar una Bodega.", 2);
        }
        return mvalidar;
    }

    public void eliminar() {
        String mQuery = "";
        if ("".equals(cod_lis_pie) == false) {
            try {
                Accesos mAccesos = new Accesos();

                mAccesos.Conectar();
                for (int i = 0; i < piezasdetalle.size(); i++) {

                    //Borra el registro físico del Detalle
                    mQuery = " delete from tbl_pie_det where cod_enc=" + cod_lis_pie + ";";
                    mAccesos.dmlSQLvariable(mQuery);

                    // ******************* Borra Reservas *****************
                    mQuery = " delete from tbl_res where cod_lis_pie = " + cod_lis_pie + ";";
                    mAccesos.dmlSQLvariable(mQuery);

                    //Obtener el correlativo diario del registro actual
                    mQuery = "select ifnull(ord_dia,0) cordia "
                            + "from tbl_pie_his "
                            + "where "
                            + "fec_his = STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and cod_pie =" + piezasdetalle.get(i).getCod_pie() + " "
                            + "and cod_enc =" + piezasdetalle.get(i).getCod_enc() + " "
                            + "and cod_det =" + piezasdetalle.get(i).getCod_det() + " "
                            + "and flg_ing =" + flg_ing + " "
                            + "and cod_pai =" + cod_pai + " "
                            + "limit 1;";
                    String cor_dia = mAccesos.strQuerySQLvariable(mQuery);

                    //Borrado Lógico el registro del Histórico
                    mQuery = " update tbl_pie_his set det_sta=1, fec_mod=now(), cod_usu=" + cbean.getCod_usu()
                            + " where flg_ing=" + flg_ing + " and cod_enc=" + cod_lis_pie + " and det_sta=0;";
                    mAccesos.dmlSQLvariable(mQuery);

                    // Verifica si hay registros anteriores y toma sus valores
                    mQuery = "select count(ord_dia) as contador "
                            + "from tbl_pie_his "
                            + "where fec_his = STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and cod_pai =" + cod_pai + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                            + "and det_sta=0;";
                    String contador = mAccesos.strQuerySQLvariable(mQuery);

                    Double cPromedio = 0.0, nuevacantidad = 0.0;

                    if ("0".equals(contador)) {
                        mQuery = "select count(ord_dia) as contador "
                                + "from tbl_pie_his "
                                + "where fec_his < STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                + "and cod_pai =" + cod_pai + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                + "and det_sta=0;";
                        contador = mAccesos.strQuerySQLvariable(mQuery);
                        if ("0".equals(contador) == false) {
                            mQuery = "select ifnull(cos_pro,0) as cpromedio "
                                    + "from tbl_pie_his where fec_his < STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta=0 "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;";
                            cPromedio = mAccesos.doubleQuerySQLvariable(mQuery);
                            mQuery = "select ifnull(can_exi,0) as nuevacantidad "
                                    + "from tbl_pie_his where fec_his < STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta=0 "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;";
                            nuevacantidad = mAccesos.doubleQuerySQLvariable(mQuery);
                            /*mQuery = "select ifnull(det_cos,0) as costounitario "
                                    + "from tbl_pie_his where fec_his < STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_bod =" + piezasdetalle.get(i).getCod_bod() + " "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta=0 "
                                    + "order by fec_his desc,"
                                    + "ord_dia desc "
                                    + "limit 1;";
                            cunitario = mAccesos.doubleQuerySQLvariable(mQuery);*/
                        }
                    } else {
                        mQuery = "select ifnull(cos_pro,0) as cpromedio "
                                + "from tbl_pie_his where fec_his = STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                + "and ord_dia < " + cor_dia + " "
                                + "and cod_pai =" + cod_pai + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                + "and det_sta=0 "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;";
                        cPromedio = mAccesos.doubleQuerySQLvariable(mQuery);
                        mQuery = "select ifnull(can_exi,0) as nuevacantidad "
                                + "from tbl_pie_his where fec_his=STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                + "and ord_dia < " + cor_dia + " "
                                + "and cod_pai =" + cod_pai + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                + "and det_sta=0 "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;";
                        nuevacantidad = mAccesos.doubleQuerySQLvariable(mQuery);
                        /*mQuery = "select ifnull(det_cos,0) as costounitario "
                                + "from tbl_pie_his where fec_his=STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                + "and ord_dia < " + cor_dia + " "
                                + "and cod_pai =" + cod_pai + " "
                                + "and cod_bod =" + piezasdetalle.get(i).getCod_bod() + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                + "and det_sta=0 "
                                + "order by fec_his desc,"
                                + "ord_dia desc "
                                + "limit 1;";
                        cunitario = mAccesos.doubleQuerySQLvariable(mQuery);*/
                    }

                    // Verifica si hay registros posteriores y si los hay actualiza a partir de la fecha de Transacción
                    String contasiguientes = mAccesos.strQuerySQLvariable("select count(cod_his) "
                            + "from tbl_pie_his "
                            + "where fec_his=STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                            + "and ord_dia >" + cor_dia + " "
                            + "and cod_pai =" + cod_pai + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                            + "and det_sta = 0 "
                            + ";");
                    contasiguientes = String.valueOf(
                            Integer.valueOf(contasiguientes)
                            + Integer.valueOf(mAccesos.strQuerySQLvariable("select count(cod_his) "
                                    + "from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta = 0 "
                                    + ";")));

                    //Double nuevacantidad = mNuevaExistencia;
                    if ("0".equals(contasiguientes) == false) {
                        try {
                            historico = new ArrayList<>();

                            //Double cos_uni_sal = 0.0;
                            ResultSet resvariable;
                            resvariable = mAccesos.querySQLvariable("select * from ( "
                                    + "select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his = STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and ord_dia >" + cor_dia + " "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta = 0 "
                                    + "UNION ALL "
                                    + "select cod_his,cod_pie,fec_his,ord_dia,flg_ing,cod_enc,"
                                    + "cod_det,det_can,det_cos,can_exi,cos_pro,det_sta,fec_mod,"
                                    + "cod_usu from tbl_pie_his "
                                    + "where fec_his > STR_TO_DATE('" + fec_tra + "','%d/%m/%Y') "
                                    + "and cod_pai =" + cod_pai + " "
                                    + "and cod_pie =" + piezasdetalle.get(i).getCod_pie() + " "
                                    + "and det_sta = 0 ) as tabla "
                                    + "order by fec_his asc, "
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
                                mAccesos.dmlSQLvariable(mQuery);
                            }

                        } catch (SQLException | NumberFormatException e) {
                            System.out.println("Error en actualización de costos posteriores. " + e.getMessage());
                        }

                    }

                    // Tratamiento tabla bol_exi_pai
                    String mContador = mAccesos.strQuerySQLvariable("select count(cod_pie) as contador from bol_exi_pai "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and ing_sal=" + flg_ing + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie()
                            + ";");

                    if (!"0".equals(mContador)) {

                        mQuery = "update bol_exi_pai set "
                                + "det_exi= (det_exi - " + piezasdetalle.get(i).getDet_can() + ") "
                                + "where "
                                + "cod_pai=" + cod_pai + " "
                                + "and ing_sal=" + flg_ing + " "
                                + "and cod_pie=" + piezasdetalle.get(i).getCod_pie() + ";";

                    }

                    mAccesos.dmlSQLvariable(mQuery);

                    // Tratamiento tabla tbl_existencias
                    mContador = mAccesos.strQuerySQLvariable("select count(cod_exi) as contador from tbl_existencias "
                            + "where "
                            + "cod_pai=" + cod_pai + " "
                            + "and cod_bod=" + piezasdetalle.get(i).getCod_bod() + " "
                            + "and cod_pie=" + piezasdetalle.get(i).getCod_pie()
                            + ";");

                    if (!"0".equals(mContador)) {

                        if ("0".equals(flg_ing)) {
                            mQuery = " update tbl_existencias set det_can=(det_can-" + piezasdetalle.get(i).getDet_can() + ") "
                                    + " where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetalle.get(i).getCod_bod() + " "
                                    + " and cod_ubi = " + piezasdetalle.get(i).getCod_ubi() + " "
                                    + " and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " ;";
                        } else {
                            mQuery = " update tbl_existencias set det_can=(det_can+" + piezasdetalle.get(i).getDet_can() + ") "
                                    + " where cod_pai=" + cod_pai + " and cod_bod = " + piezasdetalle.get(i).getCod_bod() + " "
                                    + " and cod_ubi = " + piezasdetalle.get(i).getCod_ubi() + " "
                                    + " and cod_pie=" + piezasdetalle.get(i).getCod_pie() + " ;";
                        }
                    }
                    mAccesos.dmlSQLvariable(mQuery);
                }

                // ************* Borrado Lógico Encabezado *************************************
                mQuery = "update tbl_pie set det_sta = 1 where cod_lis_pie=" + cod_lis_pie + ";";
                mAccesos.dmlSQLvariable(mQuery);

                mAccesos.Desconectar();
                addMessage("Eliminar de Almacén", "Información Eliminada con éxito.", 1);

            } catch (Exception e) {
                addMessage("Eliminar de Almacén", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar de Almacén. " + e.getMessage() + " Query: " + mQuery);
            }

        } else {
            addMessage("Eliminar de Almacén", "Debe elegir un Registro.", 2);
        }
        nuevo();
    }

//**************************** Funciones Varias ***********
    public void inout() {
        cod_pro = "0";
        por_qbo = "";
        cod_mov = "0";
        doc_tra = "";
        cod_pie = "0";
        cod_bod = "0";
        cod_ubi = "";
        det_can = "";
        det_cos = "";
        id_mae = "0";
        cod_sol = "0";
        piezasdetalle.clear();

        if ("true".equals(boolinout)) {
            //solicitudes.clear();
            llenarPiezasout();
            proveedores.clear();
            booleditable = "false";
            flg_ing = "1";
        } else {
            llenarProveedores();
            llenarPiezas();
            //llenarEncabezadoSol();
            booleditable = "true";
            flg_ing = "0";
        }
        llenarMovimientos();

    }

    public void onRowSelect(SelectEvent event) {
        cod_enc = ((CatTblPiezasDetalle) event.getObject()).getCod_enc();
        cod_det = ((CatTblPiezasDetalle) event.getObject()).getCod_det();
        cod_pie = ((CatTblPiezasDetalle) event.getObject()).getCod_pie();
        cod_bod = ((CatTblPiezasDetalle) event.getObject()).getCod_bod();
        cod_ubi = ((CatTblPiezasDetalle) event.getObject()).getCod_ubi();
        det_can = ((CatTblPiezasDetalle) event.getObject()).getDet_can();
        det_cos = ((CatTblPiezasDetalle) event.getObject()).getDet_cos();
        det_sta = ((CatTblPiezasDetalle) event.getObject()).getDet_sta();
    }

    public void onRowSelectBID(SelectEvent event) {
        flg_ing = ((CatTblPiezas) event.getObject()).getFlg_ing();
        if ("0".equals(flg_ing)) {
            boolinout = "false";
        } else {
            boolinout = "true";
        }
        llenarMovimientos();
        cod_lis_pie = ((CatTblPiezas) event.getObject()).getCod_lis_pie();
        por_qbo = ((CatTblPiezas) event.getObject()).getPor_qbo();
        cod_pai = ((CatTblPiezas) event.getObject()).getCod_pai();
        cod_pro = ((CatTblPiezas) event.getObject()).getCod_pro();
        cod_mov = ((CatTblPiezas) event.getObject()).getCod_mov();
        doc_tra = ((CatTblPiezas) event.getObject()).getDoc_tra();
        cod_sol = ((CatTblPiezas) event.getObject()).getCod_sol();
        fec_tra = ((CatTblPiezas) event.getObject()).getFec_tra();
        det_obs = ((CatTblPiezas) event.getObject()).getDet_obs();

        det_sta = ((CatTblPiezas) event.getObject()).getDet_sta();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            mfecha = format.parse(fec_tra);
        } catch (Exception ex) {

        }
        llenarDetalle();
        cerrarventanaid();
        RequestContext.getCurrentInstance().execute("PF('wBuscarListaPiezas').hide()");
    }

    public void seleccionarpais() {
        //llenarProveedores();
        llenarBodegas();
        cod_bod = "0";
        cod_ubi = "0";
        piezas.clear();
    }

    public void seleccionarbodega() {
        //llenarProveedores();
        llenarUbicaciones();
        cod_ubi = "0";
        piezas.clear();
    }

    public void seleccionarubi() {
        if ("false".equals(boolinout)) {
            llenarPiezas();
        } else {
            llenarPiezasout();
        }
    }

    public void seleccionarpieza() {
        cod_pie = catpiezasabreviado.getCodigo();
    }

    public void dateSelectedAction(SelectEvent f) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        if (piezasdetalle.isEmpty()) {
            Date date = (Date) f.getObject();
            fec_tra = format.format(date);
            cod_bod = "0";
            piezas.clear();
        } else {
            try {
                mfecha = format.parse(fec_tra);
            } catch (Exception e) {

            }
            addMessage("Cambiar Fecha", "No puede Modificarse la Fecha pues Existen Detalles asociados. ", 2);
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

    //************Ya no se utiliza ***************************
    public void llenarEncabezadoSol() {
        try {

            solicitudes = new ArrayList<>();

            String mQuery = "select mae.id_mae, date_format(mae.fec_sol,'%d/%m/%Y') as fec_sol, mae.nom_usu, "
                    + "mae.cod_dep, mae.det_uso, mae.cod_maq, mae.nom_apr, mae.det_sta,"
                    + "dep.nom_dep,maq.nom_equ,mae.det_obs "
                    + "FROM sol_mae as mae "
                    + "left join cat_dep as dep on mae.cod_dep = dep.cod_dep "
                    + "left join cat_equ as maq on mae.cod_maq = maq.cod_equ "
                    + "left join cat_usu as usu on mae.nom_usu = usu.cod_usu "
                    + "where "
                    + "mae.det_sta in('CERRADA','BACKORDER') "
                    + "order by mae.fec_sol desc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                solicitudes.add(new CatSolicitudes(
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
                        resVariable.getString(10),
                        resVariable.getString(11),
                        resVariable.getString(11),
                        resVariable.getString(11),
                        resVariable.getString(11),
                        resVariable.getString(11),
                        resVariable.getString(11)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Solicitudes en ManTblPiezas. " + e.getMessage());
        }
    }

    public void llenarDetallesSol() {
        try {

            solicitudesdetalle = new ArrayList<>();

            String mQuery = "select id_mae, id_det, cod_ite,des_ite, det_can, det_sta FROM sol_det "
                    + "where id_mae = " + id_mae + " order by id_det asc;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                solicitudesdetalle.add(new CatSolicitudesDetalle(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(6),
                        resVariable.getString(6),
                        resVariable.getString(6),
                        resVariable.getString(6),
                        resVariable.getString(6),
                        resVariable.getString(6)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado Detalles de Solicitudes en ManTblPiezas. " + e.getMessage());
        }

    }

    public void selectsol() {
        try {
            Accesos acc = new Accesos();
            id_mae = cod_sol;
            cod_bod = "1";
            cod_ubi = "1";
            llenarDetallesSol();
            acc.Conectar();
            piezasdetalle = new ArrayList<>();
            if ("0".equals(acc.strQuerySQLvariable("select count(id_bod) from cat_bodegas where id_bod=1 and cod_pai =" + cod_pai + ";"))) {
                addMessage("Seleccionar Solicitud", "No Existe Bodega Definida Para Este País.", 1);
            } else if ("0".equals(acc.strQuerySQLvariable("select count(id_ubi) from cat_ubicaciones where cod_bod=1 and id_ubi =1;"))) {
                addMessage("Seleccionar Solicitud", "No Existe Ubicación Definida para Esta Bodega.", 1);
            } else {
                for (int i = 0; i < solicitudesdetalle.size(); i++) {
                    piezasdetalle.add(new CatTblPiezasDetalle(
                            "",
                            solicitudesdetalle.get(i).getCod_det(),
                            solicitudesdetalle.get(i).getCod_ite(),
                            "1",
                            "1",
                            solicitudesdetalle.get(i).getDet_can_ent(),
                            "0.0",
                            "0",
                            solicitudesdetalle.get(i).getDes_ite(),
                            acc.strQuerySQLvariable("select nom_bod from cat_bodegas where id_bod=1 and cod_pai =" + cod_pai + ";"),
                            acc.strQuerySQLvariable("select nom_ubi from cat_ubicaciones where cod_bod=1 and id_ubi =1;")
                    ));
                }
            }
            acc.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en Selección de Solicitud de ManTblPiezas. " + e.getMessage());
        }

    }

}
