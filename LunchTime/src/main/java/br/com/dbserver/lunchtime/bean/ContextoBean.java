/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.negocio.FuncionarioRN;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 * Classe responsável por obter o usuário logado no sistema no momento da
 * requisição.
 *
 * @author Luiz Henrique
 */
@ManagedBean(name = "contextoBean")
@SessionScoped
public class ContextoBean {

    private Funcionario funcionarioLogado = null;

    public Funcionario getFuncionarioLogado() {
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        String login = external.getRemoteUser();
        if (this.funcionarioLogado == null || !login.equals(funcionarioLogado.getLogin())) {
            if (login != null) {
                FuncionarioRN funcionarioRN = new FuncionarioRN();
                this.funcionarioLogado = funcionarioRN.buscaPorLogin(login);
            }
        }
        return funcionarioLogado;
    }

    public void setFuncionarioLogado(Funcionario funcionario) {
        this.funcionarioLogado = funcionario;
    }
}
