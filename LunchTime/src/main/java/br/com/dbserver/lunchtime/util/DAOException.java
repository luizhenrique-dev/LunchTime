/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.util;

/**
 *
 * @author Luiz Henrique
 */
public class DAOException extends Exception {

    private static final long serialVersionUID = -6141835898742492895L;

    public DAOException() {
        // TODO Auto-generated constructor stub
    }

    public DAOException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public DAOException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public DAOException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}