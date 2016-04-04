
package paquetes;

import java.io.Serializable;

public class CatEvaluacionPersonas implements Serializable{
    
    private String id_eva_per, id_per, id_eva, f_eva, id_per_eva, obs_eva, nom_per, nom_eva;

    public CatEvaluacionPersonas() {
    }

    public CatEvaluacionPersonas(String id_eva_per, String id_per, String id_eva, String f_eva, String id_per_eva, String obs_eva, String nom_per, String nom_eva) {
        this.id_eva_per = id_eva_per;
        this.id_per = id_per;
        this.id_eva = id_eva;
        this.f_eva = f_eva;
        this.id_per_eva = id_per_eva;
        this.obs_eva = obs_eva;
        this.nom_per = nom_per;
        this.nom_eva = nom_eva;       
    }

    public String getId_eva_per() {
        return id_eva_per;
    }

    public void setId_eva_per(String id_eva_per) {
        this.id_eva_per = id_eva_per;
    }

    public String getId_per() {
        return id_per;
    }

    public void setId_per(String id_per) {
        this.id_per = id_per;
    }

    public String getId_eva() {
        return id_eva;
    }

    public void setId_eva(String id_eva) {
        this.id_eva = id_eva;
    }

    public String getF_eva() {
        return f_eva;
    }

    public void setF_eva(String f_eva) {
        this.f_eva = f_eva;
    }

    public String getId_per_eva() {
        return id_per_eva;
    }

    public void setId_per_eva(String id_per_eva) {
        this.id_per_eva = id_per_eva;
    }

    public String getObs_eva() {
        return obs_eva;
    }

    public void setObs_eva(String obs_eva) {
        this.obs_eva = obs_eva;
    }

    public String getNom_per() {
        return nom_per;
    }

    public void setNom_per(String nom_per) {
        this.nom_per = nom_per;
    }

    public String getNom_eva() {
        return nom_eva;
    }

    public void setNom_eva(String nom_eva) {
        this.nom_eva = nom_eva;
    }   
    
}
