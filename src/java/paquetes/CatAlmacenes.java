
package paquetes;

import java.io.Serializable;


public class CatAlmacenes implements Serializable{
    
    private String cod_alm, nom_alm;

    public CatAlmacenes() {
    }

    public CatAlmacenes(String cod_alm, String nom_alm) {
        this.cod_alm = cod_alm;
        this.nom_alm = nom_alm;
    }

    public String getCod_alm() {
        return cod_alm;
    }

    public void setCod_alm(String cod_alm) {
        this.cod_alm = cod_alm;
    }

    public String getNom_alm() {
        return nom_alm;
    }

    public void setNom_alm(String nom_alm) {
        this.nom_alm = nom_alm;
    }

    
}
