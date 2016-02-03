package paquetes;

import java.io.Serializable;

public class CatCalendario implements Serializable {

    private String cod_lis_equ, cod_man, cod_tip, det_obs, fec_ini, fec_fin, det_sta, cod_usu;

    public CatCalendario() {
    }

    public CatCalendario(String cod_lis_equ, String cod_man, String cod_tip, String det_obs, String fec_ini, String fec_fin, String det_sta, String cod_usu) {
        this.cod_lis_equ = cod_lis_equ;
        this.cod_man = cod_man;
        this.cod_tip = cod_tip;
        this.det_obs = det_obs;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.det_sta = det_sta;
        this.cod_usu = cod_usu;
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
}
