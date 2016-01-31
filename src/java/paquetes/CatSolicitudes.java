package paquetes;

import java.io.Serializable;

public class CatSolicitudes implements Serializable {

    private String id_mae, fec_sol, nom_usu, cod_dep, det_uso, cod_maq, nom_apr, det_sta, nomdep, nommaq,det_obs;

    public CatSolicitudes() {
    }

    public CatSolicitudes(String id_mae, String fec_sol, String nom_usu, String cod_dep, String det_uso, String cod_maq, String nom_apr, String det_sta, String nomdep, String nommaq, String det_obs) {
        this.id_mae = id_mae;
        this.fec_sol = fec_sol;
        this.nom_usu = nom_usu;
        this.cod_dep = cod_dep;
        this.det_uso = det_uso;
        this.cod_maq = cod_maq;
        this.nom_apr = nom_apr;
        this.det_sta = det_sta;
        this.nomdep = nomdep;
        this.nommaq = nommaq;
        this.det_obs = det_obs;
    }

    public String getId_mae() {
        return id_mae;
    }

    public void setId_mae(String id_mae) {
        this.id_mae = id_mae;
    }

    public String getFec_sol() {
        return fec_sol;
    }

    public void setFec_sol(String fec_sol) {
        this.fec_sol = fec_sol;
    }

    public String getNom_usu() {
        return nom_usu;
    }

    public void setNom_usu(String nom_usu) {
        this.nom_usu = nom_usu;
    }

    public String getCod_dep() {
        return cod_dep;
    }

    public void setCod_dep(String cod_dep) {
        this.cod_dep = cod_dep;
    }

    public String getDet_uso() {
        return det_uso;
    }

    public void setDet_uso(String det_uso) {
        this.det_uso = det_uso;
    }

    public String getCod_maq() {
        return cod_maq;
    }

    public void setCod_maq(String cod_maq) {
        this.cod_maq = cod_maq;
    }

    public String getNom_apr() {
        return nom_apr;
    }

    public void setNom_apr(String nom_apr) {
        this.nom_apr = nom_apr;
    }

    public String getDet_sta() {
        return det_sta;
    }

    public void setDet_sta(String det_sta) {
        this.det_sta = det_sta;
    }

    public String getNomdep() {
        return nomdep;
    }

    public void setNomdep(String nomdep) {
        this.nomdep = nomdep;
    }

    public String getNommaq() {
        return nommaq;
    }

    public void setNommaq(String nommaq) {
        this.nommaq = nommaq;
    }

    public String getDet_obs() {
        return det_obs;
    }

    public void setDet_obs(String det_obs) {
        this.det_obs = det_obs;
    }

}
