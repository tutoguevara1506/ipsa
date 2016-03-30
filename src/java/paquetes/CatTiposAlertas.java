
package paquetes;

import java.io.Serializable;

public class CatTiposAlertas implements Serializable{
    
    private String id_tip_ale, nom_tip_ale;

    public CatTiposAlertas() {
    }

    public CatTiposAlertas(String id_tip_ale, String nom_tip_ale) {
        this.id_tip_ale = id_tip_ale;
        this.nom_tip_ale = nom_tip_ale;
    }

    public String getId_tip_ale() {
        return id_tip_ale;
    }

    public void setId_tip_ale(String id_tip_ale) {
        this.id_tip_ale = id_tip_ale;
    }

    public String getNom_tip_ale() {
        return nom_tip_ale;
    }

    public void setNom_tip_ale(String nom_tip_ale) {
        this.nom_tip_ale = nom_tip_ale;
    }   
}
