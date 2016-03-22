
package paquetes;

import java.io.Serializable;

public class LogAlertas implements Serializable{
    
    private String id_log_ale, fec_ale, cod_lis_equ, ale_des, tip_ale, ale_destino;

    public LogAlertas() {
    }

    public LogAlertas(String id_log_ale, String fec_ale, String cod_lis_equ, String ale_des, String tip_ale, String ale_destino) {
        this.id_log_ale = id_log_ale;
        this.fec_ale = fec_ale;
        this.cod_lis_equ = cod_lis_equ;
        this.ale_des = ale_des;
        this.tip_ale = tip_ale;
        this.ale_destino = ale_destino;
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

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getAle_des() {
        return ale_des;
    }

    public void setAle_des(String ale_des) {
        this.ale_des = ale_des;
    }

    public String getTip_ale() {
        return tip_ale;
    }

    public void setTip_ale(String tip_ale) {
        this.tip_ale = tip_ale;
    }

    public String getAle_destino() {
        return ale_destino;
    }

    public void setAle_destino(String ale_destino) {
        this.ale_destino = ale_destino;
    }
    
}
