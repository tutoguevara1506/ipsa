
package paquetes;

import java.io.Serializable;

public class CatTipoActivo implements Serializable{
    
    private String id_tip_act, des_tip_act;

    public CatTipoActivo() {
    }

    public CatTipoActivo(String id_tip_act, String des_tip_act) {
        this.id_tip_act = id_tip_act;
        this.des_tip_act = des_tip_act;
    }

    public String getId_tip_act() {
        return id_tip_act;
    }

    public void setId_tip_act(String id_tip_act) {
        this.id_tip_act = id_tip_act;
    }

    public String getDes_tip_act() {
        return des_tip_act;
    }

    public void setDes_tip_act(String des_tip_act) {
        this.des_tip_act = des_tip_act;
    }

    
}
