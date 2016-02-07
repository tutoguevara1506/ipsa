package paquetes;

import java.io.Serializable;

public class CatBodegas implements Serializable {

    private String id_bod, nom_bod, cod_alm, nomalm;

    public CatBodegas() {
    }

    public CatBodegas(String id_bod, String nom_bod, String cod_alm, String nomalm) {
        this.id_bod = id_bod;
        this.nom_bod = nom_bod;
        this.cod_alm = cod_alm;
        this.nomalm = nomalm;
    }

    public String getId_bod() {
        return id_bod;
    }

    public void setId_bod(String id_bod) {
        this.id_bod = id_bod;
    }

    public String getNom_bod() {
        return nom_bod;
    }

    public void setNom_bod(String nom_bod) {
        this.nom_bod = nom_bod;
    }

    public String getCod_alm() {
        return cod_alm;
    }

    public void setCod_alm(String cod_alm) {
        this.cod_alm = cod_alm;
    }

    public String getNomalm() {
        return nomalm;
    }

    public void setNomalm(String nomalm) {
        this.nomalm = nomalm;
    }

}
