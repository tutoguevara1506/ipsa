package paquetes;

import java.io.Serializable;

public class CatSolicitudesDetalle implements Serializable {

    private String id_mae, id_det, cod_ite, des_ite, det_can, det_sta;

    public CatSolicitudesDetalle() {
    }

    public CatSolicitudesDetalle(String id_mae, String id_det, String cod_ite, String des_ite, String det_can, String det_sta) {
        this.id_mae = id_mae;
        this.id_det = id_det;
        this.cod_ite = cod_ite;
        this.des_ite = des_ite;
        this.det_can = det_can;
        this.det_sta = det_sta;
    }

    public String getId_mae() {
        return id_mae;
    }

    public void setId_mae(String id_mae) {
        this.id_mae = id_mae;
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

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

}
