
package paquetes;

import java.io.Serializable;

public class CatActivoFijo implements Serializable{
    
    private String id_act_fij, id_tip_act, desc_equ, fecha_adquisicion, valor_adqui, id_depto, dist_gast_porc, seccion, id_estado, tiempo_deprecia, cuota_mes_deprecia, porcentaje_deduc, porcentaje_no_deduc, serie_equ, modelo_equ, no_inventario, observacion, codigo_equ;

    public CatActivoFijo() {
    }

    public CatActivoFijo(String id_act_fij, String id_tip_act, String desc_equ, String fecha_adquisicion, String valor_adqui, String id_depto, String dist_gast_porc, String seccion, String id_estado, String tiempo_deprecia, String cuota_mes_deprecia, String porcentaje_deduc, String porcentaje_no_deduc, String serie_equ, String modelo_equ, String no_inventario, String observacion, String codigo_equ ) {
        this.id_act_fij = id_act_fij;
        this.id_tip_act = id_tip_act;
        this.desc_equ = desc_equ;
        this.fecha_adquisicion = fecha_adquisicion;
        this.valor_adqui = valor_adqui;
        this.id_depto = id_depto;
        this.dist_gast_porc = dist_gast_porc;
        this.seccion = seccion;
        this.id_estado = id_estado;
        this.tiempo_deprecia = tiempo_deprecia;
        this.cuota_mes_deprecia = cuota_mes_deprecia;
        this.porcentaje_deduc = porcentaje_deduc;
        this.porcentaje_no_deduc = porcentaje_no_deduc;
        this.serie_equ = serie_equ;
        this.modelo_equ = modelo_equ;
        this.no_inventario = no_inventario;
        this.observacion = observacion;
        this.codigo_equ = codigo_equ;
    }

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

}
