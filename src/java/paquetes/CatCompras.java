package paquetes;

import java.io.Serializable;

public class CatCompras implements Serializable {

    private String cod_mae, cod_det, cor_com, cod_pro, det_com, nompro, can_com;

    public CatCompras() {
    }

    public CatCompras(String cod_mae, String cod_det, String cor_com, String cod_pro, String det_com, String nompro, String can_com) {
        this.cod_mae = cod_mae;
        this.cod_det = cod_det;
        this.cor_com = cor_com;
        this.cod_pro = cod_pro;
        this.det_com = det_com;
        this.nompro = nompro;
        this.can_com = can_com;
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

    public String getCor_com() {
        return cor_com;
    }

    public void setCor_com(String cor_com) {
        this.cor_com = cor_com;
    }

    public String getCod_pro() {
        return cod_pro;
    }

    public void setCod_pro(String cod_pro) {
        this.cod_pro = cod_pro;
    }

    public String getDet_com() {
        return det_com;
    }

    public void setDet_com(String det_com) {
        this.det_com = det_com;
    }

    public String getNompro() {
        return nompro;
    }

    public void setNompro(String nompro) {
        this.nompro = nompro;
    }

    public String getCan_com() {
        return can_com;
    }

    public void setCan_com(String can_com) {
        this.can_com = can_com;
    }

}
