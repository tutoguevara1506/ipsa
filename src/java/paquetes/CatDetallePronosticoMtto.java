package paquetes;

import java.io.Serializable;
import java.util.Date;

public class CatDetallePronosticoMtto implements Serializable {

    private String id_det_pro_mtto, id_pro_mtto, cod_lis_equ, cod_man, cod_tip, det_obs, det_sta, cod_usu, cod_per, flg_ext, cod_pri, cod_sup, cod_dep, turno, des_equ;
    private Date fec_ini, fec_fin;
    
    public CatDetallePronosticoMtto() {
    }

    public CatDetallePronosticoMtto(String id_det_pro_mtto, String id_pro_mtto, String cod_lis_equ, String cod_man, String cod_tip, String det_obs, Date fec_ini, Date fec_fin, String det_sta, String cod_usu, String cod_per, String flg_ext, String cod_pri, String cod_sup, String cod_dep, String turno, String des_equ) {
        this.id_det_pro_mtto = id_det_pro_mtto;
        this.id_pro_mtto = id_pro_mtto;
        this.cod_lis_equ = cod_lis_equ;
        this.cod_man = cod_man;
        this.cod_tip = cod_tip;
        this.det_obs = det_obs;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.det_sta = det_sta;
        this.cod_usu = cod_usu;
        this.cod_per = cod_per;
        this.flg_ext = flg_ext;
        this.cod_pri = cod_pri;
        this.cod_sup = cod_sup;
        this.cod_dep = cod_dep;
        this.turno = turno;
        this.des_equ = des_equ;
    }

    public String getId_det_pro_mtto() {
        return id_det_pro_mtto;
    }

    public void setId_det_pro_mtto(String id_det_pro_mtto) {
        this.id_det_pro_mtto = id_det_pro_mtto;
    }

    public String getId_pro_mtto() {
        return id_pro_mtto;
    }

    public void setId_pro_mtto(String id_pro_mtto) {
        this.id_pro_mtto = id_pro_mtto;
    }

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getCod_man() {
        return cod_man;
    }

    public void setCod_man(String cod_man) {
        this.cod_man = cod_man;
    }

    public String getCod_tip() {
        return cod_tip;
    }

    public void setCod_tip(String cod_tip) {
        this.cod_tip = cod_tip;
    }

    public String getDet_obs() {
        return det_obs;
    }

    public void setDet_obs(String det_obs) {
        this.det_obs = det_obs;
    }

    public Date getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(Date fec_ini) {
        this.fec_ini = fec_ini;
    }

    public Date getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(Date fec_fin) {
        this.fec_fin = fec_fin;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public String getCod_per() {
        return cod_per;
    }

    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    public String getFlg_ext() {
        return flg_ext;
    }

    public void setFlg_ext(String flg_ext) {
        this.flg_ext = flg_ext;
    }

    public String getCod_sup() {
        return cod_sup;
    }

    public void setCod_sup(String cod_sup) {
        this.cod_sup = cod_sup;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getCod_pri() {
        return cod_pri;
    }

    public void setCod_pri(String cod_pri) {
        this.cod_pri = cod_pri;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }
    
    public String getDes_equ() {
        return des_equ;
    }

    public void setDes_equ(String des_equ) {
        this.des_equ = des_equ;
    }
}
