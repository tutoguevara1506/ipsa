package paquetes;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManListaEspecifica implements Serializable {

    private static final long serialVersionUID = 74697846841638L;
    @Inject
    Login mbMain;
    private CatListaEspecifica catlistaespecifica;
    private List<CatListaEspecifica> lista;
    private List<CatUsuarios> usuarios;

    private String cod_lis, cod_usu;

    public CatListaEspecifica getCatlistaespecifica() {
        return catlistaespecifica;
    }

    public void setCatlistaespecifica(CatListaEspecifica catlistaespecifica) {
        this.catlistaespecifica = catlistaespecifica;
    }

    public List<CatListaEspecifica> getLista() {
        return lista;
    }

    public void setLista(List<CatListaEspecifica> lista) {
        this.lista = lista;
    }

    public List<CatUsuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<CatUsuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public String getCod_lis() {
        return cod_lis;
    }

    public void setCod_lis(String cod_lis) {
        this.cod_lis = cod_lis;
    }

    public String getCod_usu() {
        return cod_usu;
    }

    public void setCod_usu(String cod_usu) {
        this.cod_usu = cod_usu;
    }

    public void iniciarventana() {
        cod_lis = "0";
        cod_usu = "0";
        llenarUsuarios();
    }

    public void cerrarventana() {
        cod_lis = "";
        cod_usu = "";
        lista = new ArrayList<>();
        usuarios = new ArrayList<>();
    }

    public void llenarUsuarios() {
        try {
            usuarios = new ArrayList<>();

            String mQuery = "select usu.cod_usu, usu.nom_usu, usu.des_pas, usu.tip_usu, usu.cod_pai, "
                    + "usu.cod_dep, usu.det_nom, usu.det_mai,ifnull(pai.nom_pai,'') as nom_pai, ifnull(dep.nom_dep,'') as nom_dep "
                    + "from cat_usu as usu "
                    + "left join cat_dep as dep on usu.cod_dep = dep.cod_dep and usu.cod_pai = dep.cod_pai "
                    + "left join cat_pai as pai on usu.cod_pai = pai.cod_pai order by cod_usu;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                usuarios.add(new CatUsuarios(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Usuarios ManLIstaEspecifica. " + e.getMessage());
        }
    }

    public void llenarLista() {
        try {
            catlistaespecifica = new CatListaEspecifica();
            lista = new ArrayList<>();

            String mQuery = "select lis.cod_lis, lis.cod_usu, usu.det_nom "
                    + "from lis_esp as lis "
                    + "left join cat_usu as usu on lis.cod_usu = usu.cod_usu "
                    + "where cod_lis = " + cod_lis + ";";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                lista.add(new CatListaEspecifica(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Lista Específica. " + e.getMessage());
        }
    }

    public void nuevo() {
        cod_lis = "0";
        cod_usu = "0";
        lista = new ArrayList<>();
    }

    public void guardar() {
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                String mQuery;

                mQuery = "insert into lis_esp (cod_lis, cod_usu) "
                        + "values (" + cod_lis + "," + cod_usu + ");";

                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Lista", "Información Almacenada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Lista", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Lista. " + e.getMessage());
            }
            nuevo();
        }

    }

    public void eliminar() {

        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();

        if (!"0".equals(cod_lis) && !"0".equals(cod_usu)) {
            try {
                String mQuery = "delete from lis_esp where cod_lis=" + cod_lis + " and cod_usu=" + cod_usu + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Lista", "Información Eliminada con Éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Lista", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Lista. " + e.getMessage());
            }
        } else {
            addMessage("Eliminar Lista", "Debe elegir un Registro.", 2);
        }

        mAccesos.Desconectar();
        nuevo();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("0".equals(cod_lis)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Escoger una Lista.", 2);
        }
        if ("0".equals(cod_usu)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Escoger un Usuario.", 2);
        }
        Accesos acc = new Accesos();
        acc.Conectar();
        if (!"0".equals(acc.strQuerySQLvariable("select count(cod_lis) from lis_esp where cod_lis=" + cod_lis + " and cod_usu=" + cod_usu + ";"))) {
            mValidar = false;
            addMessage("Validar Datos", "Este Usuario ya existe en la Lista Seleccionada.", 2);
        }
        acc.Desconectar();
        return mValidar;
    }

    public void onRowSelect(SelectEvent event) {
        cod_lis = ((CatListaEspecifica) event.getObject()).getCod_lis();
        cod_usu = ((CatListaEspecifica) event.getObject()).getCod_usu();
    }

    public void addMessage(String summary, String detail, int tipo) {
        FacesMessage message = new FacesMessage();
        switch (tipo) {
            case 1:
                message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
                break;
            case 2:
                message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);
                break;
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
