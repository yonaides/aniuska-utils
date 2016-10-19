package com.aniuska.utils.servlet;

import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hventurar@edenorte.com.do
 */
public class FileParams implements Serializable {

    public static final String FILE_SERVLET_PARAM = "fileId";

    private String file;
    private String fileName;
    private String contentType;

    public String getFile() {
        return file;
    }

    public FileParams setFile(String file) {
        this.file = file;
        return this;
    }

    public String getFileName() {
        return fileName;
    }

    public FileParams setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public String getContentType() {
        return contentType;

    }

    public FileParams setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public static FileParams createXlsFile() {
        return new FileParams()
                .setContentType("application/vnd.ms-excel")
                .setFile("reporte.xls")
                .setFileName("reporte.xls");

    }

    @Override
    public String toString() {
        return "FileParams{" + "file=" + file + ", fileName=" + fileName + ", contentType=" + contentType + '}';
    }

}
