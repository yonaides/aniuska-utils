package com.aniuska.utils.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hventurar@edenorte.com.do
 */
@WebServlet("/file")
public class FileServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     *
     * @param request
     * @param response
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String queryParams = request.getQueryString();

        if (queryParams == null) {
            return;
        }

        String query[] = queryParams.split("=");
        if (FileParams.FILE_SERVLET_PARAM.equals(query[0])) {

            FileParams file = (FileParams) request.getSession().getAttribute(query[1]);

            if (file == null) {
                throw new ServletException("no 'FileParams.FILE_SERVLET_PARAM' "
                        + "not found on the HTTP session.");
            }

            try {
                response.setContentType(file.getContentType());
                response.setHeader("Content-Disposition",
                        String.format("attachment; filename=%s", file.getFileName()));

                OutputStream out = response.getOutputStream();
                try (FileInputStream in = new FileInputStream(file.getFile())) {
                    byte[] buffer = new byte[4096];
                    int length;
                    while ((length = in.read(buffer)) > 0) {
                        out.write(buffer, 0, length);
                    }
                }
                out.flush();

            } catch (Exception e) {
                throw new ServletException(e);
            }
        }

    }

}
