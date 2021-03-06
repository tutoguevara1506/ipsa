package paquetes;

import java.io.Serializable;

public class CatMantenimientos implements Serializable {

    private String cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta,
            cod_usu, nomtip, status, datraso, color, cod_per, periodo, flg_ext, cod_sup, turno, cod_pri, cod_dep, cod_alt, obs_tec, otr_per, nomequ, fec_ent_pro;

    public CatMantenimientos() {
    }

    public CatMantenimientos(String cod_lis_equ, String cod_man, String cod_tip, String det_obs, String fec_ini, String fec_fin, String det_sta, String cod_usu, String nomtip, String status, String datraso, String color, String cod_per, String periodo, String flg_ext, String cod_sup, String turno, String cod_pri, String cod_dep, String cod_alt, String obs_tec, String otr_per, String nomequ, String fec_ent_pro) {
        this.cod_lis_equ = cod_lis_equ;
        this.cod_man = cod_man;
        this.cod_tip = cod_tip;
        this.det_obs = det_obs;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.det_sta = det_sta;
        this.cod_usu = cod_usu;
        this.nomtip = nomtip;
        this.status = status;
        this.datraso = datraso;
        this.color = color;
        this.cod_per = cod_per;
        this.periodo = periodo;
        this.flg_ext = flg_ext;
        this.cod_sup = cod_sup;
        this.turno = turno;
        this.cod_pri = cod_pri;
        this.cod_dep = cod_dep;
        this.cod_alt = cod_alt;
        this.obs_tec = obs_tec;
        this.otr_per = otr_per;
        this.nomequ = nomequ;
        this.fec_ent_pro = fec_ent_pro;
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

    public String getFec_ini() {
        return fec_ini;
    }

    public void setFec_ini(String fec_ini) {
        this.fec_ini = fec_ini;
    }

    public String getFec_fin() {
        return fec_fin;
    }

    public void setFec_fin(String fec_fin) {
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

    public String getNomtip() {
        return nomtip;
    }

    public void setNomtip(String nomtip) {
        this.nomtip = nomtip;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDatraso() {
        return datraso;
    }

    public void setDatraso(String datraso) {
        this.datraso = datraso;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCod_per() {
        return cod_per;
    }

    public void setCod_per(String cod_per) {
        this.cod_per = cod_per;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public String getCod_alt() {
        return cod_alt;
    }

    public void setCod_alt(String cod_alt) {
        this.cod_alt = cod_alt;
    }

    public String getObs_tec() {
        return obs_tec;
    }

    public void setObs_tec(String obs_tec) {
        this.obs_tec = obs_tec;
    }

    public String getOtr_per() {
        return otr_per;
    }

    public void setOtr_per(String otr_per) {
        this.otr_per = otr_per;
    }

    public String getNomequ() {
        return nomequ;
    }

    public void setNomequ(String nomequ) {
        this.nomequ = nomequ;
    }

    public String getFec_ent_pro() {
        return fec_ent_pro;
    }

    public void setFec_ent_pro(String fec_ent_pro) {
        this.fec_ent_pro = fec_ent_pro;
    }

}
