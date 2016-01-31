package paquetes;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped
public class ManPiezas implements Serializable {

    private static final long serialVersionUID = 8797678674685238L;
    @Inject
    Login cbean;
    private CatCategorias catcategorias;
    private List<CatCategorias> categorias;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatPiezas catpiezas;
    private List<CatPiezas> piezas;
    private String cod_pie, cod_ref, cod_equ, nom_pie, des_pie, cod_cat, det_ima, vid_uti;

    public ManPiezas() {
    }

    public CatCategorias getCatcategorias() {
        return catcategorias;
    }

    public void setCatcategorias(CatCategorias catcategorias) {
        this.catcategorias = catcategorias;
    }

    public List<CatCategorias> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CatCategorias> categorias) {
        this.categorias = categorias;
    }

    public CatEquipos getCatequipos() {
        return catequipos;
    }

    public void setCatequipos(CatEquipos catequipos) {
        this.catequipos = catequipos;
    }

    public List<CatEquipos> getEquipos() {
        return equipos;
    }

    public void setEquipos(List<CatEquipos> equipos) {
        this.equipos = equipos;
    }

    public CatPiezas getCatpiezas() {
        return catpiezas;
    }

    public void setCatpiezas(CatPiezas catpiezas) {
        this.catpiezas = catpiezas;
    }

    public List<CatPiezas> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<CatPiezas> piezas) {
        this.piezas = piezas;
    }

    public String getCod_pie() {
        return cod_pie;
    }

    public void setCod_pie(String cod_pie) {
        this.cod_pie = cod_pie;
    }

    public String getCod_ref() {
        return cod_ref;
    }

    public void setCod_ref(String cod_ref) {
        this.cod_ref = cod_ref;
    }

    public String getCod_equ() {
        return cod_equ;
    }

    public void setCod_equ(String cod_equ) {
        this.cod_equ = cod_equ;
    }

    public String getNom_pie() {
        return nom_pie;
    }

    public void setNom_pie(String nom_pie) {
        this.nom_pie = nom_pie;
    }

    public String getDes_pie() {
        return des_pie;
    }

    public void setDes_pie(String des_pie) {
        this.des_pie = des_pie;
    }

    public String getCod_cat() {
        return cod_cat;
    }

    public void setCod_cat(String cod_cat) {
        this.cod_cat = cod_cat;
    }

    public String getDet_ima() {
        return det_ima;
    }

    public void setDet_ima(String det_ima) {
        this.det_ima = det_ima;
    }

    public String getVid_uti() {
        return vid_uti;
    }

    public void setVid_uti(String vid_uti) {
        this.vid_uti = vid_uti;
    }

    public void iniciarventana() {
        cod_pie = "";
        cod_ref = "";
        cod_equ = "0";
        nom_pie = "";
        des_pie = "";
        cod_cat = "";
        det_ima = "";
        vid_uti = "";
        llenarCategorias();
        llenarEquipos();
        llenarPiezas();

    }

    public void cerrarventana() {
        cod_pie = "";
        cod_ref = "";
        cod_equ = "0";
        nom_pie = "";
        des_pie = "";
        cod_cat = "";
        det_ima = "";
        vid_uti = "";
        equipos = new ArrayList<>();
        piezas = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public void llenarCategorias() {
        String mQuery = "";
        try {
            catcategorias = new CatCategorias();
            categorias = new ArrayList<>();

            mQuery = "select cod_cat, nom_cat "
                    + "from cat_cat  "
                    + "order by cod_cat;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                categorias.add(new CatCategorias(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Categorías en Catálogo Piezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarEquipos() {
        String mQuery = "";
        try {
            catequipos = new CatEquipos();
            equipos = new ArrayList<>();

            mQuery = "select equ.cod_equ, equ.cod_mar,equ.cod_ref, "
                    + "equ.nom_equ, equ.des_equ,mar.nom_mar,equ.det_ima "
                    + "from cat_equ as equ "
                    + "left join cat_mar as mar on mar.id_mar = equ.cod_mar "
                    + "order by equ.cod_equ;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                equipos.add(new CatEquipos(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Equipos en Catálogo Piezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void llenarPiezas() {
        String mQuery = "";
        try {
            catpiezas = new CatPiezas();
            piezas = new ArrayList<>();

            mQuery = "select pie.cod_pie,pie.cod_ref,pie.cod_equ,"
                    + "pie.nom_pie,pie.des_pie,equ.nom_equ,pie.cod_cat,"
                    + "pie.det_ima,pie.vid_uti "
                    + "from cat_pie as pie "
                    + "left join cat_equ as equ on equ.cod_equ=pie.cod_equ "
                    + "order by pie.cod_pie;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                piezas.add(new CatPiezas(
                        resVariable.getString(1),
                        resVariable.getString(2),
                        resVariable.getString(3),
                        resVariable.getString(4),
                        resVariable.getString(5),
                        resVariable.getString(6),
                        resVariable.getString(7),
                        resVariable.getString(8),
                        resVariable.getString(9)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Catálogo de Piezas. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_pie = "";
        cod_ref = "";
        cod_equ = "0";
        nom_pie = "";
        des_pie = "";
        cod_cat = "";
        det_ima = "";
        vid_uti = "";
        catpiezas = new CatPiezas();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_pie)) {
                    mQuery = "select ifnull(max(cod_pie),0)+1 as codigo from cat_pie;";
                    cod_pie = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_pie (cod_pie,cod_ref,cod_equ,nom_pie,des_pie,cod_cat,det_ima,vid_uti) "
                            + "values (" + cod_pie + ",'" + cod_ref + "'," + cod_equ + ",'" + nom_pie
                            + "','" + des_pie + "'," + cod_cat + ",'" + det_ima + "'," + vid_uti + ");";
                } else {
                    mQuery = "update cat_pie SET "
                            + " cod_ref = " + cod_ref + " "
                            + ",cod_equ = '" + cod_equ + "' "
                            + ",nom_pie = '" + nom_pie + "' "
                            + ",des_pie = '" + des_pie + "' "
                            + ",cod_cat = " + cod_cat + " "
                            + ",det_ima = '" + det_ima + "' "
                            + ",vid_uti = " + vid_uti + " "
                            + "WHERE cod_pie = " + cod_pie;

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Pieza", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Pieza", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Pieza. " + e.getMessage() + " Query: " + mQuery);
            }
        }
        llenarPiezas();
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_pie) == false) {
            try {
                mQuery = "delete from cat_pie where cod_pie=" + cod_pie + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Pieza", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Pieza", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Pieza. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarPiezas();
            nuevo();
        } else {
            addMessage("Eliminar Pieza", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("0".equals(cod_equ)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Escoger un Equipo para la Pieza.", 2);
        }

        if ("".equals(nom_pie)) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para la Pieza.", 2);
        }

        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_pie = ((CatPiezas) event.getObject()).getCod_pie();
        cod_ref = ((CatPiezas) event.getObject()).getCod_ref();
        cod_equ = ((CatPiezas) event.getObject()).getCod_equ();
        nom_pie = ((CatPiezas) event.getObject()).getNom_pie();
        des_pie = ((CatPiezas) event.getObject()).getDes_pie();
        cod_cat = ((CatPiezas) event.getObject()).getCod_cat();
        det_ima = ((CatPiezas) event.getObject()).getDet_ima();
        vid_uti = ((CatPiezas) event.getObject()).getVid_uti();
    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Éxito! ", event.getFile().getFileName() + " ha sido cargado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            //dbImage = new DefaultStreamedContent(event.getFile().getInputstream(), "image/jpeg");
            copyFile("img_" + cod_ref + ".jpg", event.getFile().getInputstream());

        } catch (Exception e) {
            System.out.println("Error en subir archivo Lineas" + e.getMessage());
        }

    }

    public void copyFile(String fileName, InputStream in) {
        try {
            File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/piezas/config.xml"));
            String destination = mIMGFile.getPath().replace("config.xml", "");
            // write the inputStream to a FileOutputStream
            OutputStream out = new FileOutputStream(new File(destination + fileName.toLowerCase()));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

            File file = new File(destination + "pie_" + fileName);
            file.delete();
            copiarImagen(destination + fileName, destination + "pie_" + fileName, 320);
            File file2 = new File(destination + fileName);
            file2.delete();
            //copiarImagen(destination + nFile, destination + nFile, 150);
            det_ima = "/resources/images/piezas/pie_" + fileName;

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void copiarImagen(String filePath, String copyPath, int wh) {
        BufferedImage nuevaImagen = cargarImagen(filePath);
        if (nuevaImagen.getHeight() > nuevaImagen.getWidth()) {
            int heigt = (nuevaImagen.getHeight() * wh) / nuevaImagen.getWidth();
            nuevaImagen = resize(nuevaImagen, wh, heigt);
            int width = (nuevaImagen.getWidth() * wh) / nuevaImagen.getHeight();
            nuevaImagen = resize(nuevaImagen, width, wh);
        } else {
            int width = (nuevaImagen.getWidth() * wh) / nuevaImagen.getHeight();
            nuevaImagen = resize(nuevaImagen, width, wh);
            int heigt = (nuevaImagen.getHeight() * wh) / nuevaImagen.getWidth();
            nuevaImagen = resize(nuevaImagen, wh, heigt);
        }
        GuardarImagen(nuevaImagen, copyPath);
    }

    /*
     Con este método, cargamos la imagen inicial, indicándole el path
     */
    public static BufferedImage cargarImagen(String pathName) {
        BufferedImage nuevaImagen = null;
        try {
            nuevaImagen = ImageIO.read(new File(pathName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nuevaImagen;
    }

    /*
     Este método se utiliza para redimensionar la imagen
     */
    public static BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
        int w = bufferedImage.getWidth();
        int h = bufferedImage.getHeight();
        BufferedImage imagenRedimensionada = new BufferedImage(newW, newH, bufferedImage.getType());
        Graphics2D g = imagenRedimensionada.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
        g.dispose();
        return imagenRedimensionada;
    }

    /*
     Con este método almacenamos la imagen en el disco
     */
    public static void GuardarImagen(BufferedImage bufferedImage, String pathName) {
        try {
            String format = (pathName.endsWith(".png")) ? "png" : "jpg";
            File file = new File(pathName);
            file.getParentFile().mkdirs();
            ImageIO.write(bufferedImage, format, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
