package paquetes;

import java.io.Serializable;
import java.util.Date;

public class CatCalendario implements Serializable {

    private String cod_lis_equ, cod_man, cod_tip, det_obs, det_sta, cod_usu, des_equ, num_ser;
    private Date fec_ini, fec_fin;

    public CatCalendario() {
    }

    public CatCalendario(String cod_lis_equ, String cod_man, String cod_tip, String det_obs, Date fec_ini, Date fec_fin, String det_sta, String cod_usu, String des_equ, String num_ser) {
        this.cod_lis_equ = cod_lis_equ;
        this.cod_man = cod_man;
        this.cod_tip = cod_tip;
        this.det_obs = det_obs;
        this.fec_ini = fec_ini;
        this.fec_fin = fec_fin;
        this.det_sta = det_sta;
        this.cod_usu = cod_usu;
        this.des_equ = des_equ;
        this.num_ser = num_ser;
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

    public String getDes_equ() {
        return des_equ;
    }

    public void setDes_equ(String des_equ) {
        this.des_equ = des_equ;
    }    

    public String getNum_ser() {
        return num_ser;
    }

    public void setNum_ser(String num_ser) {
        this.num_ser = num_ser;
    }
}
