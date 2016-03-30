
package paquetes;

import java.io.Serializable;

public class CatDetalleEvaluacionPersonas implements Serializable{
    
    private String id_det_eva_per, id_tbl_eva_per, id_eva, id_fac, id_cri, id_cal, calif;

    public CatDetalleEvaluacionPersonas() {
    }

    public CatDetalleEvaluacionPersonas(String id_det_eva_per, String id_tbl_eva_per, String id_eva, String id_fac, String id_cri, String id_cal, String calif) {
        this.id_det_eva_per = id_det_eva_per;
        this.id_tbl_eva_per = id_tbl_eva_per;
        this.id_eva = id_eva;
        this.id_fac = id_fac;
        this.id_cri = id_cri;
        this.id_cal = id_cal;
        this.calif = calif;
    }

    public String getId_det_eva_per() {
        return id_det_eva_per;
    }

    public void setId_det_eva_per(String id_det_eva_per) {
        this.id_det_eva_per = id_det_eva_per;
    }

    public String getId_tbl_eva_per() {
        return id_tbl_eva_per;
    }

    public void setId_tbl_eva_per(String id_tbl_eva_per) {
        this.id_tbl_eva_per = id_tbl_eva_per;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public String getId_fac() {
        return id_fac;
    }

    public void setId_fac(String id_fac) {
        this.id_fac = id_fac;
    }

    public String getId_cri() {
        return id_cri;
    }

    public void setId_cri(String id_cri) {
        this.id_cri = id_cri;
    }

    public String getId_cal() {
        return id_cal;
    }

    public void setId_cal(String id_cal) {
        this.id_cal = id_cal;
    }

    public String getCalif() {
        return calif;
    }

    public void setCalif(String calif) {
        this.calif = calif;
    }
   
}
