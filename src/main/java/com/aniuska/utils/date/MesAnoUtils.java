/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author hectorvent@gmail,com
 */
@ManagedBean
@ApplicationScoped
public class MesAnoUtils {

    private List meses;
    private List<Integer> years;
    private List<Integer> dias;

    @PostConstruct
    public void init() {

        // Años
        years = new ArrayList<>();
        Integer firstYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 0; i < 10; i++) {
            years.add(firstYear + i);
        }

        // Meses
        meses = new ArrayList();
        meses.add(new Mes(1, "Enero"));
        meses.add(new Mes(2, "Febrero"));
        meses.add(new Mes(3, "Marzo"));
        meses.add(new Mes(4, "Abril"));
        meses.add(new Mes(5, "Mayo"));
        meses.add(new Mes(6, "Junio"));
        meses.add(new Mes(7, "Julio"));
        meses.add(new Mes(8, "Agosto"));
        meses.add(new Mes(9, "Septiembre"));
        meses.add(new Mes(10, "Octubre"));
        meses.add(new Mes(11, "Noviembre"));
        meses.add(new Mes(12, "Diciembre"));

        //Dias
        dias = new ArrayList();

        for (int i = 1; i <= 30; i++) {
            dias.add(i);
        }

    }

    public List<Mes> getMeses() {

        return meses;
    }

    public List<Integer> getYears() {
        return years;
    }

    public List<Integer> getDias() {
        return dias;
    }

    public class Mes {

        private int mesId;
        private String mesDescripcion;

        public Mes(int mesId, String mesDescripcion) {
            this.mesId = mesId;
            this.mesDescripcion = mesDescripcion;
        }

        public int getMesId() {
            return mesId;
        }

        public void setMesId(int mesId) {
            this.mesId = mesId;
        }

        public String getMesDescripcion() {
            return mesDescripcion;
        }

        public void setMesDescripcion(String mesDescripcion) {
            this.mesDescripcion = mesDescripcion;
        }

    }
}
