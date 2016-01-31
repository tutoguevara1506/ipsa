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
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManCargos implements Serializable {

    private static final long serialVersionUID = 3797678674716638L;
    @Inject
    Login cbean;
    private CatCargos catcargos;
    private List<CatCargos> cargos;
    private String id_car, des_car;

    public ManCargos() {
    }

    public CatCargos getCatcargos() {
        return catcargos;
    }

    public void setCatcargos(CatCargos catcargos) {
        this.catcargos = catcargos;
    }

    public List<CatCargos> getCargos() {
        return cargos;
    }

    public void setCargor(List<CatCargos> cargos) {
        this.cargos = cargos;
    }

    public String getId_car() {
        return id_car;
    }

    public void setId_car(String id_car) {
        this.id_car = id_car;
    }

    public String getDes_car() {
        return des_car;
    }

    public void setDes_car(String des_car) {
        this.des_car = des_car;
    }

    public void iniciarventana() {
        id_car = "";
        des_car = "";
        llenarCargos();
    }

    public void cerrarventana() {
        id_car = "";
        des_car = "";
        cargos = new ArrayList<>();
    }

    public void llenarCargos() {
        String mQuery = "";
        try {
            catcargos = new CatCargos();
            cargos = new ArrayList<>();

            mQuery = "select id_car, des_car from cat_car order by id_car;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                cargos.add(new CatCargos(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Cargos de Personal. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_car = "";
        des_car = "";
        catcargos = new CatCargos();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_car)) {
                    mQuery = "select ifnull(max(id_car),0)+1 as codigo from cat_car;";
                    id_car = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_car (id_car,des_car) "
                            + "values (" + id_car + ",'" + des_car + "');";
                } else {
                    mQuery = "update cat_car SET "
                            + " des_car = '" + des_car + "' "
                            + "WHERE id_car = " + id_car + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Cargo", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Cargo", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCargos();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_car) == false) {
            try {
                mQuery = "delete from cat_car where id_car=" + id_car + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Cargo", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Cargo", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Cargo. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarCargos();
            nuevo();
        } else {
            addMessage("Eliminar Cargos", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(des_car) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre del Cargo.", 2);
        }
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_car) from cat_car "
                + "where upper(des_car)='" + des_car.toUpperCase() + "';")) == false && "".equals(id_car)) {
            mValidar = false;
            addMessage("Validar Datos", "El Nombre del Cargo ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_car = ((CatCargos) event.getObject()).getId_car();
        des_car = ((CatCargos) event.getObject()).getDes_car();
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
