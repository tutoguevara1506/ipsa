
package paquetes;

import java.io.Serializable;

public class CatPronosticoMtto implements Serializable{
    
    private String id_pro_mtto, nom_pro_mtto, fecha_pro_mtto, anho_pro_mtto;

    public CatPronosticoMtto() {
    }

    public CatPronosticoMtto(String id_pro_mtto, String nom_pro_mtto, String fecha_pro_mtto, String anho_pro_mtto) {
        this.id_pro_mtto = id_pro_mtto;
        this.nom_pro_mtto = nom_pro_mtto;
        this.fecha_pro_mtto = fecha_pro_mtto;
        this.anho_pro_mtto = anho_pro_mtto;        
    }

    public String getId_pro_mtto() {
        return id_pro_mtto;
    }

    public void setId_pro_mtto(String id_pro_mtto) {
        this.id_pro_mtto = id_pro_mtto;
    }

    public String getNom_pro_mtto() {
        return nom_pro_mtto;
    }

    public void setNom_pro_mtto(String nom_pro_mtto) {
        this.nom_pro_mtto = nom_pro_mtto;
    }

    public String getFecha_pro_mtto() {
        return fecha_pro_mtto;
    }

    public void setFecha_pro_mtto(String fecha_pro_mtto) {
        this.fecha_pro_mtto = fecha_pro_mtto;
    }

    public String getAnho_pro_mtto() {
        return anho_pro_mtto;
    }

    public void setAnho_pro_mtto(String anho_pro_mtto) {
        this.anho_pro_mtto = anho_pro_mtto;
    }

    
    
}
