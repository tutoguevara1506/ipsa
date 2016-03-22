
package paquetes;

import java.io.Serializable;

public class CatAlertas implements Serializable{
    
    private String id_ale, cod_dep, tabla_ctrl, camp_ctrl, camp_ref, alerta, aviso, recordatorio, id_estado, nom_dep;

    public CatAlertas() {
    }

    public CatAlertas(String id_ale, String cod_dep, String tabla_ctrl, String camp_ctrl, String camp_ref, String alerta, String aviso, String recordatorio, String id_estado, String nom_dep) {
        this.id_ale = id_ale;
        this.cod_dep = cod_dep;
        this.tabla_ctrl = tabla_ctrl;
        this.camp_ctrl = camp_ctrl;
        this.camp_ref = camp_ref;
        this.alerta = alerta;
        this.aviso = aviso;
        this.recordatorio = recordatorio;
        this.id_estado = id_estado;
        this.nom_dep = nom_dep;
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

    public String getTabla_ctrl() {
        return tabla_ctrl;
    }

    public void setTabla_ctrl(String tabla_ctrl) {
        this.tabla_ctrl = tabla_ctrl;
    }

    public String getCamp_ctrl() {
        return camp_ctrl;
    }

    public void setCamp_ctrl(String camp_ctrl) {
        this.camp_ctrl = camp_ctrl;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
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

    public String getCamp_ref() {
        return camp_ref;
    }

    public void setCamp_ref(String camp_ref) {
        this.camp_ref = camp_ref;
    }   
}
