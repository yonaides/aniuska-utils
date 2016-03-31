/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.converter;

import com.aniuska.utils.date.DateRange;
import com.aniuska.utils.date.DateUtils;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hectorvent@gmail,com
 */
@FacesConverter("dateRangeConverter")
public class DateRangeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (value != null && value.contains("-")) {

            String[] fechas = value.split("-");
            DateRange dr = new DateRange();
            dr.setFirstDate(DateUtils.string2Date(fechas[0].trim()));
            dr.setEndDate(DateUtils.string2Date(fechas[1].trim()));
            return dr;
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || !(value instanceof DateRange)) {
            return "";
        }
        DateRange dr = (DateRange) value;
        return DateUtils.date2String(dr.getFirstDate()) + " - " + DateUtils.date2String(dr.getEndDate());
    }

}
