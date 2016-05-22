
package paquetes;

import java.io.Serializable;


public class CatCotizaciones implements Serializable{
    
    private String cod_mae,cod_det,cor_det,cod_pro,det_cot,nompro;

    public CatCotizaciones() {
    }

    public CatCotizaciones(String cod_mae, String cod_det, String cor_det, String cod_pro, String det_cot, String nompro) {
        this.cod_mae = cod_mae;
        this.cod_det = cod_det;
        this.cor_det = cor_det;
        this.cod_pro = cod_pro;
        this.det_cot = det_cot;
        this.nompro = nompro;
    }

    public String getCod_mae() {
        return cod_mae;
    }

    public void setCod_mae(String cod_mae) {
        this.cod_mae = cod_mae;
    }

    public String getCod_det() {
        return cod_det;
    }

    public void setCod_det(String cod_det) {
        this.cod_det = cod_det;
    }

    public String getCor_det() {
        return cor_det;
    }

    public void setCor_det(String cor_det) {
        this.cor_det = cor_det;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getDet_cot() {
        return det_cot;
    }

    public void setDet_cot(String det_cot) {
        this.det_cot = det_cot;
    }

    public String getNompro() {
        return nompro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }
    
    
    
    
}
