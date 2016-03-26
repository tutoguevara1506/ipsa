
package paquetes;

import java.io.Serializable;

public class LogAlertas implements Serializable{
    
    private String id_log_ale, fec_ale, id_tip_ale, ale_des, nom_tip_ale;

    public LogAlertas() {
    }

    public LogAlertas(String id_log_ale, String fec_ale, String id_tip_ale, String ale_des, String nom_tip_ale) {
        this.id_log_ale = id_log_ale;
        this.fec_ale = fec_ale;
        this.id_tip_ale = id_tip_ale;
        this.ale_des = ale_des;
        this.nom_tip_ale = nom_tip_ale;
    }

    public String getId_log_ale() {
        return id_log_ale;
    }

    public void setId_log_ale(String id_log_ale) {
        this.id_log_ale = id_log_ale;
    }

    public String getFec_ale() {
        return fec_ale;
    }

    public void setFec_ale(String fec_ale) {
        this.fec_ale = fec_ale;
    }

    public String getId_tip_ale() {
        return id_tip_ale;
    }

    public void setId_tip_ale(String id_tip_ale) {
        this.id_tip_ale = id_tip_ale;
    }

    public String getAle_des() {
        return ale_des;
    }

    public void setAle_des(String ale_des) {
        this.ale_des = ale_des;
    }

    public String getNom_tip_ale() {
        return nom_tip_ale;
    }

    public void setNom_tip_ale(String nom_tip_ale) {
        this.nom_tip_ale = nom_tip_ale;
    }    
}
