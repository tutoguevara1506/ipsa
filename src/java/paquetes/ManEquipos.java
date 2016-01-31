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
public class ManEquipos implements Serializable {

    private static final long serialVersionUID = 8797671468316638L;
    @Inject
    Login cbean;
    private CatEquipos catequipos;
    private List<CatEquipos> equipos;
    private CatMarcas catmarcas;
    private List<CatMarcas> marcas;
    private String cod_equ, cod_mar, cod_ref, nom_equ, des_equ, det_ima;

    public ManEquipos() {
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

    public CatMarcas getCatmarcas() {
        return catmarcas;
    }

    public void setCatmarcas(CatMarcas catmarcas) {
        this.catmarcas = catmarcas;
    }

    public List<CatMarcas> getMarcas() {
        return marcas;
    }

    public void setMarcas(List<CatMarcas> marcas) {
        this.marcas = marcas;
    }

    public String getCod_equ() {
        return cod_equ;
    }

    public void setCod_equ(String cod_equ) {
        this.cod_equ = cod_equ;
    }

    public String getCod_mar() {
        return cod_mar;
    }

    public void setCod_mar(String cod_mar) {
        this.cod_mar = cod_mar;
    }

    public String getNom_equ() {
        return nom_equ;
    }

    public void setNom_equ(String nom_equ) {
        this.nom_equ = nom_equ;
    }

    public String getDes_equ() {
        return des_equ;
    }

    public void setDes_equ(String des_equ) {
        this.des_equ = des_equ;
    }

    public String getCod_ref() {
        return cod_ref;
    }

    public void setCod_ref(String cod_ref) {
        this.cod_ref = cod_ref;
    }

    public String getDet_ima() {
        return det_ima;
    }

    public void setDet_ima(String det_ima) {
        this.det_ima = det_ima;
    }

    public void iniciarventana() {
        cod_equ = "";
        nom_equ = "";
        cod_mar = "";
        cod_ref = "";
        des_equ = "";
        det_ima = "";
        llenarMarcas();
        llenarEquipos();
    }

    public void cerrarventana() {
        cod_equ = "";
        nom_equ = "";
        cod_mar = "";
        cod_ref = "";
        des_equ = "";
        det_ima = "";
        equipos = new ArrayList<>();
        marcas = new ArrayList<>();
    }

    public void llenarMarcas() {
        String mQuery = "";
        try {
            catmarcas = new CatMarcas();
            marcas = new ArrayList<>();

            mQuery = "select id_mar, nom_mar from cat_mar order by id_mar;";
            ResultSet resVariable;
            Accesos mAccesos = new Accesos();
            mAccesos.Conectar();
            resVariable = mAccesos.querySQLvariable(mQuery);
            while (resVariable.next()) {
                marcas.add(new CatMarcas(
                        resVariable.getString(1),
                        resVariable.getString(2)
                ));
            }
            mAccesos.Desconectar();

        } catch (Exception e) {
            System.out.println("Error en el llenado de Marcas en Catálogo Equipos. " + e.getMessage() + " Query: " + mQuery);
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
            System.out.println("Error en el llenado de Catálogo Equipos. " + e.getMessage() + " Query: " + mQuery);
        }
    }

    public void nuevo() {
        cod_equ = "";
        nom_equ = "";
        cod_mar = "";
        cod_ref = "";
        des_equ = "";
        det_ima = "";
        catequipos = new CatEquipos();
    }

    public void guardar() {
        String mQuery = "";
        if (validardatos()) {
            try {
                Accesos mAccesos = new Accesos();
                mAccesos.Conectar();
                if ("".equals(cod_equ)) {
                    mQuery = "select ifnull(max(cod_equ),0)+1 as codigo from cat_equ;";
                    cod_equ = mAccesos.strQuerySQLvariable(mQuery);
                    mQuery = "insert into cat_equ (cod_equ, cod_mar,cod_ref, nom_equ, des_equ,det_ima) "
                            + "values (" + cod_equ + "," + cod_mar + ",'" + cod_ref + "','"
                            + nom_equ + "','" + des_equ + "','" + det_ima + "');";
                } else {
                    mQuery = "update cat_equ SET "
                            + "cod_mar = " + cod_mar + " "
                            + ",cod_ref = '" + cod_ref + "' "
                            + ",nom_equ = '" + nom_equ + "' "
                            + ",des_equ = '" + des_equ + "' "
                            + ",det_ima = '" + det_ima + "' "
                            + "WHERE cod_equ = " + cod_equ;

                }
                mAccesos.dmlSQLvariable(mQuery);
                mAccesos.Desconectar();
                addMessage("Guardar Equipo", "Información Almacenada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Guardar Equipo", "Error al momento de guardar la información. " + e.getMessage(), 2);
                System.out.println("Error al Guardar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarEquipos();
        }
        nuevo();

    }

    public void eliminar() {
        String mQuery = "";
        Accesos mAccesos = new Accesos();
        mAccesos.Conectar();
        if ("".equals(cod_equ) == false) {
            try {
                File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(det_ima));
                mIMGFile.delete();
                mQuery = "delete from cat_equ where cod_equ=" + cod_equ + ";";
                mAccesos.dmlSQLvariable(mQuery);
                addMessage("Eliminar Equipo", "Información Eliminada con éxito.", 1);
            } catch (Exception e) {
                addMessage("Eliminar Equipo", "Error al momento de Eliminar la información. " + e.getMessage(), 2);
                System.out.println("Error al Eliminar Marca. " + e.getMessage() + " Query: " + mQuery);
            }
            llenarEquipos();
            nuevo();
        } else {
            addMessage("Eliminar Equipo", "Debe elegir un Registro.", 2);
        }
        mAccesos.Desconectar();

    }

    public boolean validardatos() {
        boolean mValidar = true;
        if ("".equals(nom_equ) == true) {
            mValidar = false;
            addMessage("Validar Datos", "Debe Ingresar un Nombre para el Equipo.", 2);
        }

        return mValidar;

    }

    public void onRowSelect(SelectEvent event) {
        cod_equ = ((CatEquipos) event.getObject()).getCod_equ();
        cod_mar = ((CatEquipos) event.getObject()).getCod_mar();
        cod_ref = ((CatEquipos) event.getObject()).getCod_ref();
        nom_equ = ((CatEquipos) event.getObject()).getNom_equ();
        des_equ = ((CatEquipos) event.getObject()).getDes_equ();
        det_ima = ((CatEquipos) event.getObject()).getDet_ima();
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
            File mIMGFile = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images/equipos/config.xml"));
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

            File file = new File(destination + "equ_" + fileName);
            file.delete();
            copiarImagen(destination + fileName, destination + "equ_" + fileName, 320);
            File file2 = new File(destination + fileName);
            file2.delete();
            //copiarImagen(destination + nFile, destination + nFile, 150);
            det_ima = "/resources/images/equipos/equ_" + fileName;

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
