package com.aniuska.utils.xls;

import com.aniuska.utils.servlet.FileParams;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hventurar@edenorte.com.do
 */
public class JxlsExportUtils {

    private String fileTemplate;
    private String fileNameOutput = "report_out.xls";
    private String nameDownload = "reporte.xls";
    private JxlsEventHandler eventHandler;
    private final Context context;

    public JxlsExportUtils() {
        context = new Context();
    }

    public String getFileTemplate() {
        return fileTemplate;
    }

    public String getNameDownload() {
        return nameDownload;
    }

    public JxlsExportUtils setNameDownload(String nameDownload) {
        this.nameDownload = nameDownload;
        return this;
    }

    public JxlsExportUtils setFileTemplate(String fileTemplate) {
        this.fileTemplate = fileTemplate;
        return this;
    }

    public String getFileNameOutput() {
        return fileNameOutput;
    }

    public JxlsExportUtils setFileNameOutput(String fileNameOutput) {
        this.fileNameOutput = fileNameOutput;
        return this;
    }

    public JxlsEventHandler getEventHandler() {
        return eventHandler;
    }

    public JxlsExportUtils setEventHandler(JxlsEventHandler eventHandler) {
        this.eventHandler = eventHandler;
        return this;
    }

    public JxlsExportUtils putVar(String key, Object value) {
        context.putVar(key, value);
        return this;
    }

    public JxlsExportUtils putVars(Map<String, Object> vars) {
        vars.forEach((k, v) -> {
            context.putVar(k, v);
        });
        return this;
    }

    public JxlsExportUtils process() {

        try (InputStream is = JxlsExportUtils.class.getResourceAsStream(fileTemplate)) {

            try (OutputStream os = new FileOutputStream(fileNameOutput)) {

                JxlsHelper.getInstance().processTemplate(is, os, context);

                if (eventHandler != null) {
                    eventHandler.onComplete();
                }
            } catch (Exception ex) {
                if (eventHandler != null) {
                    eventHandler.onException(ex);
                }
            }
        } catch (Exception ex) {
            if (eventHandler != null) {
                eventHandler.onException(ex);
            }
        }

        return this;
    }

    public void servlet() {

        try {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
            HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

            String param = "report1";
            StringBuffer path;
            path = new StringBuffer()
                    .append(request.getContextPath())
                    .append("/file?")
                    .append(FileParams.FILE_SERVLET_PARAM)
                    .append("=")
                    .append(param);

            FileParams fp = FileParams.createXlsFile()
                    .setFile(fileNameOutput)
                    .setFileName(nameDownload);

            request.getSession()
                    .setAttribute(param, fp);

            response.sendRedirect(path.toString());
            FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException ex) {
            Logger.getLogger(JxlsExportUtils.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
