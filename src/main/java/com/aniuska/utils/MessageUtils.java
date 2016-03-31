/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aniuska.utils;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author hectorvent@gmail,com
 */
public class MessageUtils {

    public static void sendErrorMessage(String clientId, String message) {
        sendMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Error:", message));
    }

    public static void sendErrorMessage(String message) {
        sendErrorMessage(null, message);
    }

    public static void sendSuccessfulMessage(String clientId, String message) {
        sendMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Mensaje:", message));
    }

    public static void sendSuccessfulMessage(String message) {
        sendSuccessfulMessage(null, message);
    }

    public static void sendMessage(FacesMessage message) {
        sendMessage(null, message);
    }

    public static void sendMessage(String clientId, FacesMessage message) {
        FacesContext.getCurrentInstance()
                .addMessage(clientId, message);
    }

}
