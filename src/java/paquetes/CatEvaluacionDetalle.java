
package paquetes;

import java.io.Serializable;

public class CatEvaluacionDetalle implements Serializable{
    
    private String id_eva_det, id_eva, num_preg, id_cri, id_fac, nomeva, nomcri, nomfac;

    public CatEvaluacionDetalle() {
    }

    public CatEvaluacionDetalle(String id_eva_det, String id_eva, String num_preg, String id_fac, String id_cri, String nomeva, String nomfac, String nomcri) {
        this.id_eva_det = id_eva_det;
        this.id_eva = id_eva;
        this.num_preg = num_preg;
        this.id_fac = id_fac;
        this.id_cri = id_cri;
        this.nomeva = nomeva;
        this.nomfac = nomfac;
        this.nomcri = nomcri;        
    }

    public String getId_eva_det() {
        return id_eva_det;
    }

    public void setId_cat_eva_det(String id_cat_eva_det) {
        this.id_eva_det = id_cat_eva_det;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public String getNum_preg() {
        return num_preg;
    }

    public void setNum_preg(String num_preg) {
        this.num_preg = num_preg;
    }

    public String getId_cri() {
        return id_cri;
    }

    public void setId_cri(String id_cri) {
        this.id_cri = id_cri;
    }

    public String getNomeva() {
        return nomeva;
    }

    public void setNomeva(String nomeva) {
        this.nomeva = nomeva;
    }

    public String getNomcri() {
        return nomcri;
    }

    public void setNomcri(String nomcri) {
        this.nomcri = nomcri;
    }

    public String getId_fac() {
        return id_fac;
    }

    public void setId_fac(String id_fac) {
        this.id_fac = id_fac;
    }

    public String getNomfac() {
        return nomfac;
    }

    public void setNomfac(String nomfac) {
        this.nomfac = nomfac;
    }

   
    
}
