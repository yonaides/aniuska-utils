package com.aniuska.utils.xls;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hventurar@edenorte.com.do
 */
public interface JxlsEventHandler {

    void onComplete();

    void onException(Exception ex);

}
