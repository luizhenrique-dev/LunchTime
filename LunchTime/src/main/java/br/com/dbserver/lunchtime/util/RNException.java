/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.util;

/**
 * Exce��o a ser lan�ada quando ocorrer uma condi��o que altera o fluxo normal da execu��o do programa no contexto de regra de neg�cio.
 * @author Luiz Henrique
 */
public class RNException extends Exception {

    private static final long serialVersionUID = -936364196317968175L;

    public RNException() {
        // TODO Auto-generated constructor stub
    }

    public RNException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public RNException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public RNException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public RNException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }
}
