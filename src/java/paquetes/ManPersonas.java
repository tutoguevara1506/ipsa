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
public class ManPersonas implements Serializable {

    private static final long serialVersionUID = 8797678674711638L;
    @Inject
    Login cbean;
    private CatPersonas catpersonas;
    private List<CatPersonas> personas;
    private String id_per, nombres, apellidos, direccion, telefono, celular, email, dui, nit, isss, id_cargo, usuario;

    public ManPersonas() {
    }

    public CatPersonas getCatpersonas() {
        return catpersonas;
    }

    public void setCatpersonas(CatPersonas catpersonas) {
        this.catpersonas = catpersonas;
    }

    public List<CatPersonas> getPersonas() {
        return personas;
    }

    public void setPersonas(List<CatPersonas> personas) {
        this.personas = personas;
    }

    public String getId_per() {
        return id_per;
    }

    public void setId_per(String id_per) {
        this.id_per = id_per;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getIsss() {
        return isss;
    }

    public void setIsss(String isss) {
        this.isss = isss;
    }

    public String getId_cargo() {
        return id_cargo;
    }

    public void setId_cargo(String id_cargo) {
        this.id_cargo = id_cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    
    public void iniciarventana() {
        id_per = "";
        nombres = "";
        apellidos = "";
        direccion = "";
        telefono = "";
        celular = "";
        email = "";
        dui = "";
        nit = "";
        isss = "";
        id_cargo = "";
        usuario = "";
        llenarPersonas();
    }

    public void cerrarventana() {
        id_per = "";
        nombres = "";
        apellidos = "";
        direccion = "";
        telefono = "";
        celular = "";
        email = "";
        dui = "";
        nit = "";
        isss = "";
        id_cargo = "";
        usuario = "";
        personas = new ArrayList<>();
    }

    public void llenarPersonas() {
        String mQuery = "";
        try {
            catpersonas = new CatPersonas();
            personas = new ArrayList<>();

            mQuery = "select id_per, nombres, apellidos, direccion, telefono, celular, email, dui, nit, isss, id_cargo, cod_usu from cat_per order by id_per;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                personas.add(new CatPersonas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9),
                        resVariable.getString(10),
                        resVariable.getString(11),
                        resVariable.getString(11)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Registro de Personas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        id_per = "";
        nombres = "";
        apellidos = "";
        direccion = "";
        telefono = "";
        celular = "";
        email = "";
        dui = "";
        nit = "";
        isss = "";
        id_cargo = "";
        usuario = "";
        catpersonas = new CatPersonas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(id_per)) {
                    mQuery = "select ifnull(max(id_per),0)+1 as codigo from cat_per;";
                    id_per = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_per (id_per,nombres, apellidos, direccion, telefono, celular, email, dui, nit, isss, id_cargo, cod_usu) "
                            + "values (" + id_per + ",'" + nombres + "','" + apellidos + "','" + direccion + "','" + telefono 
                            + "','" + celular + "','" + email + "','" + dui + "','" + nit + "','" + isss + "'," + id_cargo + ",'" + usuario + "');";
                } else {
                    mQuery = "update cat_per SET "
                            + " nombres = '" + nombres + "',"
                            + " apellidos = '" + apellidos + "',"
                            + " direccion = '" + direccion + "',"
                            + " telefono = '" + telefono + "',"
                            + " celular = '" + celular + "',"
                            + " email = '" + email + "',"
                            + " dui = '" + dui + "',"
                            + " nit = '" + nit + "',"
                            + " isss = '" + isss + "',"
                            + " id_cargo = '" + id_cargo + "',"
                            + " cod_usu = '" + usuario + "'"
                            + "WHERE id_per = " + id_per + ";";

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Persona", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Persona", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Persona. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPersonas();
            nuevo();
        }
 
    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(id_per) == false) {
            try {
                mQuery = "delete from cat_per where id_per=" + id_per + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Persona", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Persona", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Persona. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPersonas();
            nuevo();
        } else {
            addMessage("Eliminar Persona", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nombres) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Nombre de la persona.", 2);
        }
        
        if ("".equals(apellidos) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar Apellidos de la persona.", 2);
        }
        
        if ("".equals(dui) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar DUI de la persona.", 2);
        }
        
        if ("".equals(email) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar E-Mail de la persona.", 2);
        }
        if ("0".equals(id_cargo) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar El cargo de la persona.", 2);
        }
        
        Accesos maccesos = new Accesos();
        maccesos.Conectar();
        if ("0".equals(maccesos.strQuerySQLvariable("select count(id_per) from cat_per "
                + "where dui='" + dui + "';")) == false && "".equals(id_per)) {
            mValidar = false;
            addMessage("Validar Datos", "El DUI de la Persona ya existe.", 2);
        }
        maccesos.Desconectar();
        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        id_per = ((CatPersonas) event.getObject()).getId_per();
        nombres = ((CatPersonas) event.getObject()).getNombres();
        apellidos = ((CatPersonas) event.getObject()).getApellidos();
        direccion = ((CatPersonas) event.getObject()).getDireccion();
        telefono = ((CatPersonas) event.getObject()).getTelefono();
        celular = ((CatPersonas) event.getObject()).getCelular();
        dui = ((CatPersonas) event.getObject()).getDui();
        nit = ((CatPersonas) event.getObject()).getNit();
        isss = ((CatPersonas) event.getObject()).getIsss();
        id_cargo = ((CatPersonas) event.getObject()).getId_cargo();
        usuario = ((CatPersonas) event.getObject()).getUsuario();
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
