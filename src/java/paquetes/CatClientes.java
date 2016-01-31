package paquetes;

import java.io.Serializable;

public class CatClientes implements Serializable {

    private String cod_cli,cod_pai,nom_cli,per_con,tel_con,det_mai;

    public CatClientes() {
    }

    public CatClientes(String cod_cli, String cod_pai, String nom_cli, String per_con, String tel_con, String det_mai) {
        this.cod_cli = cod_cli;
        this.cod_pai = cod_pai;
        this.nom_cli = nom_cli;
        this.per_con = per_con;
        this.tel_con = tel_con;
        this.det_mai = det_mai;
    }

    public String getCod_cli() {
        return cod_cli;
    }

    public void setCod_cli(String cod_cli) {
        this.cod_cli = cod_cli;
    }

    public String getCod_pai() {
        return cod_pai;
    }

    public void setCod_pai(String cod_pai) {
        this.cod_pai = cod_pai;
    }

    public String getNom_cli() {
        return nom_cli;
    }

    public void setNom_cli(String nom_cli) {
        this.nom_cli = nom_cli;
    }

    public String getPer_con() {
        return per_con;
    }

    public void setPer_con(String per_con) {
        this.per_con = per_con;
    }

    public String getTel_con() {
        return tel_con;
    }

    public void setTel_con(String tel_con) {
        this.tel_con = tel_con;
    }

    public String getDet_mai() {
        return det_mai;
    }

    public void setDet_mai(String det_mai) {
        this.det_mai = det_mai;
    }

   
    
}
