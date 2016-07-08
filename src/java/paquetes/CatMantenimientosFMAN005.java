
package paquetes;

import java.io.Serializable;

public class CatMantenimientosFMAN005 implements Serializable{
    
    private String cod_lis_equ, cod_man, det_man, obs_001;

    public CatMantenimientosFMAN005() {
    }

    public CatMantenimientosFMAN005(String cod_lis_equ, String cod_man, String det_man, String obs_001) {
        this.cod_lis_equ = cod_lis_equ;
        this.cod_man = cod_man;
        this.det_man = det_man;
        this.obs_001 = obs_001;
    }

    public String getCod_lis_equ() {
        return cod_lis_equ;
    }

    public void setCod_lis_equ(String cod_lis_equ) {
        this.cod_lis_equ = cod_lis_equ;
    }

    public String getCod_man() {
        return cod_man;
    }

    public void setCod_man(String cod_man) {
        this.cod_man = cod_man;
    }

    public String getDet_man() {
        return det_man;
    }

    public void setDet_man(String det_man) {
        this.det_man = det_man;
    }

    public String getObs_001() {
        return obs_001;
    }

    public void setObs_001(String obs_001) {
        this.obs_001 = obs_001;
    }
    
    
    
}
