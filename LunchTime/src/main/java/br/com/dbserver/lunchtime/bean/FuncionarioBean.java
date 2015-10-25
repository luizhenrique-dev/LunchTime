/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.negocio.FuncionarioRN;
import java.util.List;
import javax.faces.application.FacesMessage;
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
        FacesContext context = FacesContext.getCurrentInstance();
        String senha = this.funcionario.getSenha();
        if (senha.equals(this.confirmaSenha)) {
            this.funcionario.setSenha(criptografaSenha(senha));
            this.funcionario.setAtivo(true);
            FuncionarioRN funcionarioRN = new FuncionarioRN();

            if (!verificaFuncionarioExistenteEmail(funcionario.getEmail())) {
                if (!verificaFuncionarioExistenteCodigo(funcionario.getCodigoFuncionarioNaEmpresa())) {
                    funcionarioRN.salvar(this.funcionario);
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Successo no cadastro.", "Funcionário cadastrado com sucesso!");
                    context.addMessage(null, facesMessage);
                } else {
                    FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um usuário funcionário com este código na empresa!", "Erro");
                    context.addMessage(null, facesMessage);
                }
            } else {
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Já existe um funcionário cadastrado com este e-mail!", "Erro");
                context.addMessage(null, facesMessage);
            }
        } else {
            FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "As senhas digitadas não conferem!", "Erro");
            context.addMessage(null, facesMessage);
        }
    }

    public String editar() {
        this.lista = null;
        return "cadastrarFuncionario";
    }

    public void excluir() {
        FuncionarioRN funcionarioRN = new FuncionarioRN();
        funcionarioRN.excluir(this.funcionario);
        this.lista = null;
    }

    public List<Funcionario> getLista() {
        if (this.lista == null) {
            FuncionarioRN funcionarioRN = new FuncionarioRN();
            this.lista = funcionarioRN.listar();
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

    private boolean verificaFuncionarioExistenteEmail(String email) {
         FuncionarioRN funcionarioRN = new FuncionarioRN();
         if (funcionarioRN.buscaPorEmail(email) != null) {
             return true;
         }
         return false;
    }

    private boolean verificaFuncionarioExistenteCodigo(String codigo) {
        FuncionarioRN funcionarioRN = new FuncionarioRN();
        if (funcionarioRN.buscaPorCodigoFuncionarioNaEmpresa(codigo) != null){
            return true;
        }
        return false;
    }
}
