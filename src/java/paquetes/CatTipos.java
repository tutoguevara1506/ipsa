package paquetes;

import java.io.Serializable;

public class CatTipos implements Serializable{

    private String cod_tip,nom_tip;
    
    public CatTipos() {
    }

    public CatTipos(String cod_tip, String nom_tip) {
        this.cod_tip = cod_tip;
        this.nom_tip = nom_tip;
    }

    public String getCod_tip() {
        return cod_tip;
    }

    public void setCod_tip(String cod_tip) {
        this.cod_tip = cod_tip;
    }

    public String getNom_tip() {
        return nom_tip;
    }

    public void setNom_tip(String nom_tip) {
        this.nom_tip = nom_tip;
    }
    
}
