/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.report;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.BaseHttpServlet;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;
import java.sql.Connection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.sql.DataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author hectorvent@gmail,com
 */
public class JasperReportUtils {

    private static final Logger LOGGER = LogManager.getLogger(JasperReportUtils.class);
    private static final Map<String, Object> parameters = new HashMap();
    private String jaspersDir = "/";
    private String jasperFile;
    private String reportName;

    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public JasperReportUtils setReportName(String reporteName) {
        this.reportName = reporteName;
        return this;
    }

    public JasperReportUtils setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public static JasperReportUtils newReport() {
        return new JasperReportUtils();
    }

    public String getJasperFile() {
        return jasperFile;
    }

    public JasperReportUtils setJasperFile(String jasperFile) {
        this.jasperFile = jasperFile;
        return this;
    }

    public String getJaspersDir() {
        return jaspersDir;
    }

    public JasperReportUtils setJaspersDir(String jaspersDir) {
        this.jaspersDir = jaspersDir;
        return this;
    }

    public JasperReportUtils put(String key, Object value) {
        parameters.put(key, value);
        return this;
    }

    public JasperReportUtils putAll(Map<String, Object> values) {
        parameters.putAll(values);
        return this;
    }

    public JasperReportUtils clearAll() {
        parameters.clear();

        return this;
    }

    public void executeReport() throws JasperReportExcepcion {
        executeReport(false);
    }

    public void executeReportAjax() throws JasperReportExcepcion {
        executeReport(true);
    }

    public void executeReport(boolean ajaxExecute) throws JasperReportExcepcion {

        if (dataSource == null) {
            throw new JasperReportExcepcion("Datasource no puede ser nulo");
        }

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        ServletContext context = (ServletContext) externalContext.getContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

        JasperPrint jasperPrint = fillReport(context);

        StringBuilder path = new StringBuilder();

        path.append(request.getContextPath())
                .append("/app/reportes/pdf");

        if (reportName != null) {
            path.append("?jrprint=")
                    .append(reportName);
        } else {
            reportName = BaseHttpServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE;
        }

        request.getSession().setAttribute(reportName, jasperPrint);

        reportName = null;

        if (!ajaxExecute) {
            try {

                response.sendRedirect(path.toString());
                FacesContext.getCurrentInstance().responseComplete();
            } catch (IOException ex) {
                throw new JasperReportExcepcion("Error redireccionando reporte", ex);
            }
        }

    }

    public JasperPrint fillReport(ServletContext context) throws JasperReportExcepcion {

        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception ex) {
            throw new JasperReportExcepcion("Error en la conexión a base de datos ", ex);
        }

        String pathJasper = jaspersDir + jasperFile + ".jasper";
        LOGGER.info("Ejecutando archivo JasperReport = {}", pathJasper);

        InputStream is = JasperPrint.class.getResourceAsStream(pathJasper);

        parameters.put("SUBREPORT_DIR", getSubDir(pathJasper));
        parameters.put("logo", "logo.png");

        try {
            return JasperFillManager.fillReport(is, parameters, conn);
        } catch (JRException ex) {
            throw new JasperReportExcepcion("Error al ejecutar reporte ", ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                }
            }
        }

    }

    private static String getSubDir(String file) throws JasperReportExcepcion {

        String subDir;

        URL url = JasperPrint.class.getResource(file);
        if (url == null) {
            throw new JasperReportExcepcion("Archivo no encontrado " + file);
        }

        try {
            LOGGER.info("URL *.jasper = {}", url);
            subDir = url.toURI().toString();
        } catch (URISyntaxException ex) {
            throw new JasperReportExcepcion("Archivo no encontrado " + file);
        }

        return subDir.substring(0, subDir.lastIndexOf("/") + 1);
    }

}

//    Esta configuración debe estar especificada en el web.xml
//    <servlet>
//        <servlet-name>PdfServlet</servlet-name>
//        <servlet-class>net.sf.jasperreports.j2ee.servlets.PdfServlet</servlet-class>
//    </servlet>
//    <servlet-mapping>
//        <servlet-name>PdfServlet</servlet-name>
//        <url-pattern>/app/reportes/pdf</url-pattern>
//    </servlet-mapping>
