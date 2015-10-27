/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.util;

import br.com.dbserver.lunchtime.bean.ContextoBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Classe responsável por obter as informações do contexto do sistema no momento da requisição.
 * @author Luiz Henrique
 */
public class ContextoUtil {

    public static ContextoBean getContextoBean() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);
        ContextoBean contextoBean = (ContextoBean) session.getAttribute("contextoBean");
        return contextoBean;
    }
}
