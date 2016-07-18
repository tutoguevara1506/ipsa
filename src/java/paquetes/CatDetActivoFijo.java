
package paquetes;

import java.io.Serializable;

public class CatDetActivoFijo implements Serializable{
    
    private String id_det_act_fij, id_act_fij, fec_det_act_fij, depr_calculada, depr_acumulada, rem_suj_dep, valor_actual;

    public CatDetActivoFijo() {
    }

    public CatDetActivoFijo(String id_det_act_fij, String id_act_fij, String fec_det_act_fij, String depr_calculada, String depr_acumulada, String rem_suj_dep, String valor_actual) {
        this.id_det_act_fij = id_det_act_fij;
        this.id_act_fij = id_act_fij;
        this.fec_det_act_fij = fec_det_act_fij;
        this.depr_calculada = depr_calculada;
        this.depr_acumulada = depr_acumulada;
        this.rem_suj_dep = rem_suj_dep;
        this.valor_actual = valor_actual;
    }

    public String getId_det_act_fij() {
        return id_det_act_fij;
    }

    public void setId_det_act_fij(String id_det_act_fij) {
        this.id_det_act_fij = id_det_act_fij;
    }

    public String getId_act_fij() {
        return id_act_fij;
    }

    public void setId_act_fij(String id_act_fij) {
        this.id_act_fij = id_act_fij;
    }

    public String getFec_det_act_fij() {
        return fec_det_act_fij;
    }

    public void setFec_det_act_fij(String fec_det_act_fij) {
        this.fec_det_act_fij = fec_det_act_fij;
    }

    public String getDepr_calculada() {
        return depr_calculada;
    }

    public void setDepr_calculada(String depr_calculada) {
        this.depr_calculada = depr_calculada;
    }

    public String getDepr_acumulada() {
        return depr_acumulada;
    }

    public void setDepr_acumulada(String depr_acumulada) {
        this.depr_acumulada = depr_acumulada;
    }

    public String getRem_suj_dep() {
        return rem_suj_dep;
    }

    public void setRem_suj_dep(String rem_suj_dep) {
        this.rem_suj_dep = rem_suj_dep;
    }

    public String getValor_actual() {
        return valor_actual;
    }

    public void setValor_actual(String valor_actual) {
        this.valor_actual = valor_actual;
    }

}
