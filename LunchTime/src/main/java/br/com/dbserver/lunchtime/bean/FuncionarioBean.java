/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.negocio.FuncionarioRN;
import br.com.dbserver.lunchtime.util.ContextoUtil;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luiz Henrique
 */
@ManagedBean(name = "funcionarioBean")
@RequestScoped
public class FuncionarioBean {

    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> lista;
    private String senhaCriptografada;
    private String confirmaSenha;

    public String novo() {
        this.funcionario = new Funcionario();
        this.confirmaSenha = "";
        return "/publico/cadastrarFuncionario";
    }

    public void salvar() {
        String senha = this.funcionario.getSenha();
        if (senha.equals(this.confirmaSenha)) {
            try {
                this.funcionario.setSenha(criptografaSenha(senha));
                this.funcionario.setAtivo(true);

                if (validaNovoFuncionario()) {
                    FuncionarioRN funcionarioRN = new FuncionarioRN();
                    funcionarioRN.salvar(this.funcionario);
                    enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Clique em 'Voltar' e efetue o login.", "Funcionário cadastrado com sucesso!");
                }
            } catch (DAOException ex) {
                enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro: " + ex.getMessage(), "Não foi possível cadastrar o funcionário! Se o problema persistir entre em contato com o administrador.");
            }
        } else {
            enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro", "As senhas digitadas não conferem!");
        }
    }

    public void atualizar() {
        String senha = this.funcionario.getSenha();
        if (senha.equals(this.confirmaSenha)) {
            try {
                this.funcionario.setSenha(criptografaSenha(senha));
                this.funcionario.setAtivo(true);
                FuncionarioRN funcionarioRN = new FuncionarioRN();
                funcionarioRN.salvar(this.funcionario);
                ContextoBean contextoBean = ContextoUtil.getContextoBean();
                contextoBean.setFuncionarioLogado(null);
                enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Sucesso.", "Perfil atualizado com sucesso!");

            } catch (DAOException ex) {
                enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro: " + ex.getMessage(), "Não foi possível atualizar seu perfil! Se o problema persistir entre em contato com o administrador.");
            }
        } else {
            enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro", "As senhas digitadas não conferem!");
        }
    }

    public String editar() {
        this.lista = null;
        return "funcionario";
    }

    public void excluir() {
        try {
            FuncionarioRN funcionarioRN = new FuncionarioRN();
            funcionarioRN.excluir(this.funcionario);
            this.lista = null;
        } catch (DAOException ex) {
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Funcionario> getLista() {
        if (this.lista == null) {
            try {
                FuncionarioRN funcionarioRN = new FuncionarioRN();
                this.lista = funcionarioRN.listar();
            } catch (DAOException ex) {
                Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.lista;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getSenhaCriptografada() {
        return senhaCriptografada;
    }

    public void setSenhaCriptografada(String senhaCriptografada) {
        this.senhaCriptografada = senhaCriptografada;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    private String criptografaSenha(String senha) {
        if (senha != null && senha.trim().length() == 0) {
            return this.senhaCriptografada;
        } else {
            String senhaCripto = org.apache.commons.codec.digest.DigestUtils.sha256Hex(senha);
            return senhaCripto;
        }
    }

    private boolean verificaFuncionarioExistenteEmail(String email) throws DAOException {
        FuncionarioRN funcionarioRN = new FuncionarioRN();
        return funcionarioRN.buscaPorEmail(email) != null;
    }

    private boolean verificaFuncionarioExistenteCodigo(String codigo) throws DAOException {
        FuncionarioRN funcionarioRN = new FuncionarioRN();
        return funcionarioRN.buscaPorCodigoFuncionarioNaEmpresa(codigo) != null;
    }

    private boolean verificaFuncionarioExistenteLogin(String login) throws DAOException {
        FuncionarioRN funcionarioRN = new FuncionarioRN();
        return funcionarioRN.buscaPorLogin(login) != null;
    }

    private boolean validaNovoFuncionario() throws DAOException {
        if (!verificaFuncionarioExistenteEmail(funcionario.getEmail())) {
            if (!verificaFuncionarioExistenteCodigo(funcionario.getCodigoFuncionarioNaEmpresa())) {
                if (!verificaFuncionarioExistenteLogin(funcionario.getLogin())) {
                    return true;
                } else {
                    enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro", "Esse login não está disponível para cadastro!");
                    return false;
                }
            } else {
                enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro", "Já existe um funcionário com este código na empresa!");
                return false;
            }
        } else {
            enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro", "Já existe um funcionário cadastrado com este e-mail!");
            return false;
        }
    }

    private void enviaMensagemFaces(Severity severidade, String titulo, String conteudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(severidade, conteudo, titulo);
        context.addMessage(null, facesMessage);
    }
}
