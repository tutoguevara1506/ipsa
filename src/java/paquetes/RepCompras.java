package paquetes;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRPptxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.primefaces.event.SelectEvent;

@Named
@ConversationScoped

public class RepCompras extends Conexion implements Serializable {

    private static final long serialVersionUID = 8419483266747168L;
    @Inject
    Login cbean;
    private String nombrereporte, nombreexportar;
    private String fecini, fecfin;
    private Date dfechaini, dfechafin;

    private Map<String, Object> parametros;

    public RepCompras() {
    }

    public String getNombrereporte() {
        return nombrereporte;
    }

    public void setNombrereporte(String nombrereporte) {
        this.nombrereporte = nombrereporte;
    }

    public String getNombreexportar() {
        return nombreexportar;
    }

    public void setNombreexportar(String nombreexportar) {
        this.nombreexportar = nombreexportar;
    }

    public String getFecini() {
        return fecini;
    }

    public void setFecini(String fecini) {
        this.fecini = fecini;
    }

    public String getFecfin() {
        return fecfin;
    }

    public void setFecfin(String fecfin) {
        this.fecfin = fecfin;
    }

    public Date getDfechaini() {
        return dfechaini;
    }

    public void setDfechaini(Date dfechaini) {
        this.dfechaini = dfechaini;
    }

    public Date getDfechafin() {
        return dfechafin;
    }

    public void setDfechafin(Date dfechafin) {
        this.dfechafin = dfechafin;
    }

    public Map<String, Object> getParametros() {
        return parametros;
    }

    public void setParametros(Map<String, Object> parametros) {
        this.parametros = parametros;
    }

    public void iniciarventana() {
        parametros = null;
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dfechaini = Date.from(Instant.now());
        dfechafin = Date.from(Instant.now());
        fecini = format.format(dfechaini);
        fecfin = format.format(dfechafin);

    }

    public void ejecutarreporte(int reporte, int tipo) {
        try {
            if (!"".equals(fecini) && !"".equals(fecfin)) {
                paramRepVarios(reporte);

                switch (tipo) {
                    case 0:
                        verPDF();
                        break;
                    case 1:
                        exportarPDF();
                        break;
                    case 2:
                        exportarXLS();
                        break;
                    case 3:
                        exportarDOC();
                        break;
                    case 4:
                        exportarPPT();
                        break;
                }

            } else {
                addMessage("Ejecutar Reporte", "Fechas Vacías.", 2);
            }
        } catch (Exception e) {
            System.out.println("Error en EjecutarReporte Seguimiento Solicitudes" + e.getMessage());
        }

    }

    public void paramRepVarios(int reporte) {
        parametros = new HashMap<>();
        switch (reporte) {
            case 1:
                parametros.put("fecini", fecini);
                parametros.put("fecfin", fecfin);
                nombrereporte = "/reportes/comprasporperiodo.jasper";
                nombreexportar = "Solicitud_por_período";
                break;
            case 2:
                parametros.put("fecini", fecini);
                parametros.put("fecfin", fecfin);
                nombrereporte = "/reportes/comprasgrafdepartamento.jasper";
                nombreexportar = "SolXDep_por_período";
                break;
        }

    }

    public void verPDF() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), parametros, Conectar());
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outStream = response.getOutputStream();
            outStream.write(bytes, 0, bytes.length);
            outStream.flush();
            outStream.close();

            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();
        } catch (JRException | IOException e) {
            System.out.println("Error en verPDF en Seguimiento Solicitudes." + e.getMessage());
        }
    }

    public void exportarPDF() {
        try {
            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, Conectar());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreexportar + ".pdf;");
            ServletOutputStream stream = response.getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
            stream.flush();
            stream.close();

            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();
        } catch (JRException | IOException e) {
            System.out.println("Error en Exportar PDF en VisReportes." + e.getMessage());
        }

    }

    public void exportarXLS() {

        try {

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, Conectar());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreexportar + ".xlsx");
            try (ServletOutputStream outStreamxls = response.getOutputStream()) {
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStreamxls);
                exporter.exportReport();

                outStreamxls.flush();
            }
            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();

        } catch (JRException | IOException e) {
            System.out.println("Error en Exportar XLS en VisReportes." + e.getMessage());
        }

    }

    public void exportarDOC() {

        try {

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, Conectar());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreexportar + ".doc");
            ServletOutputStream outStream = response.getOutputStream();

            JRDocxExporter exporter = new JRDocxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
            exporter.exportReport();

            outStream.flush();
            outStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();
        } catch (JRException | IOException e) {
            System.out.println("Error en Exportar DOC en VisReportes." + e.getMessage());
        }
    }

    public void exportarPPT() {
        try {

            File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(nombrereporte));
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros, Conectar());

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            response.addHeader("Content-disposition", "attachment; filename=" + nombreexportar + ".ppt");
            ServletOutputStream outStream = response.getOutputStream();

            JRPptxExporter exporter = new JRPptxExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outStream);
            exporter.exportReport();

            outStream.flush();
            outStream.close();
            FacesContext.getCurrentInstance().responseComplete();
            Desconectar();
        } catch (JRException | IOException e) {
            System.out.println("Error en Exportar PPT en VisReportes." + e.getMessage());
        }
    }

    public void dateiniSelectedAction(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fecini = format.format(date);
    }

    public void datefinSelectedAction(SelectEvent f) {
        Date date = (Date) f.getObject();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        fecfin = format.format(date);
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
