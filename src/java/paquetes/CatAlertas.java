
package paquetes;

import java.io.Serializable;

public class CatAlertas implements Serializable{
    
    private String id_ale, cod_dep, id_tip_ale, aviso, recordatorio, id_estado, nom_dep, nom_tip_ale;

    public CatAlertas() {
    }

    public CatAlertas(String id_ale, String cod_dep, String id_tip_ale, String aviso, String recordatorio, String id_estado, String nom_dep, String nom_tip_ale) {
        this.id_ale = id_ale;
        this.cod_dep = cod_dep;
        this.id_tip_ale = id_tip_ale;
        this.aviso = aviso;
        this.recordatorio = recordatorio;
        this.id_estado = id_estado;
        this.nom_dep = nom_dep;
        this.nom_tip_ale = nom_tip_ale;
    }

    public String getId_ale() {
        return id_ale;
    }

    public void setId_ale(String id_ale) {
        this.id_ale = id_ale;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }

    public String getId_tip_ale() {
        return id_tip_ale;
    }

    public void setId_tip_ale(String id_tip_ale) {
        this.id_tip_ale = id_tip_ale;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getRecordatorio() {
        return recordatorio;
    }

    public void setRecordatorio(String recordatorio) {
        this.recordatorio = recordatorio;
    }

    public String getId_estado() {
        return id_estado;
    }

    public void setId_estado(String id_estado) {
        this.id_estado = id_estado;
    }   

    public String getNom_dep() {
        return nom_dep;
    }

    public void setNom_dep(String nom_dep) {
        this.nom_dep = nom_dep;
    }

    public String getNom_tip_ale() {
        return nom_tip_ale;
    }

    public void setNom_tip_ale(String nom_tip_ale) {
        this.nom_tip_ale = nom_tip_ale;
    }
    
    
}
