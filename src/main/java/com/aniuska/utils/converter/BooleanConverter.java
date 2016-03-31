/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author hectorvent@gmail,com
 */
@FacesConverter("booleanConverter")
public class BooleanConverter implements Converter {

    /**
     *
     * @param context
     * @param component
     * @param value this can be 'S' or 'N'
     * @return Boolean value
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null) {
            return "S".equals(value);
        }

        return null;
    }

    /**
     *
     * @param context
     * @param component
     * @param value
     * @return String.class 'S' or 'N'
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Boolean) value) ? "S" : "N";
    }

}
