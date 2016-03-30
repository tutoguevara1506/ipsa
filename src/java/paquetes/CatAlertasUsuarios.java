
package paquetes;

import java.io.Serializable;

public class CatAlertasUsuarios implements Serializable{
    
    private String id_ale_usu, id_ale, cod_usu, nom_usu, mailusu;

    public CatAlertasUsuarios() {
    }

    public CatAlertasUsuarios(String id_ale_usu, String id_ale, String cod_usu, String nom_usu, String mailusu) {
        this.id_ale_usu = id_ale_usu;
        this.id_ale = id_ale;
        this.cod_usu = cod_usu;
        this.nom_usu = nom_usu;
        this.mailusu = mailusu;
    }

    public String getId_ale() {
        return id_ale;
    }

    public void setId_ale(String id_ale) {
        this.id_ale = id_ale;
    }

    public String getId_ale_usu() {
        return id_ale_usu;
    }

    public void setId_ale_usu(String id_ale_usu) {
        this.id_ale_usu = id_ale_usu;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }   

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getMailusu() {
        return mailusu;
    }

    public void setMailusu(String mailusu) {
        this.mailusu = mailusu;
    }    
}
