/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.negocio.RestauranteRN;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Luiz Henrique
 */
@ManagedBean(name = "restauranteBean")
@RequestScoped
public class RestauranteBean {

    private Restaurante restaurante = new Restaurante();
    private List<Restaurante> lista;
    private Date horaAbertura, horaEncerramento, horarioDePico;

    public String novo() {
        this.restaurante = new Restaurante();
        return "/publico/cadastrarRestaurante";
    }

    public String salvar() {
        try {
            RestauranteRN restauranteRN = new RestauranteRN();
            restauranteRN.salvar(this.restaurante, horaAbertura, horaEncerramento, horarioDePico);
            enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Sucesso", "Restaurante cadastrado com sucesso!");
            return "restaurantes";
        } catch (DAOException e) {
            enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), "Não foi possível cadastrar o restaurante! Se o problema persistir entre em contato com o administrador.");
            return null;
        }
    }

    public String editar() {
        this.lista = null;
        return "cadastrarRestaurante";
    }

    public String verDetalhes() {
        return "/restrito/restaurante";

    }

    public void excluir() {
        RestauranteRN restauranteRN = new RestauranteRN();
        restauranteRN.excluir(this.restaurante);
        this.lista = null;
    }

    public List<Restaurante> getLista() {
        if (this.lista == null) {
            RestauranteRN restauranteRN = new RestauranteRN();
            this.lista = restauranteRN.listar();
        }
        return this.lista;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Date getHoraAbertura() {
        return horaAbertura;
    }

    public void setHoraAbertura(Date horaAbertura) {
        this.horaAbertura = horaAbertura;
    }

    public Date getHoraEncerramento() {
        return horaEncerramento;
    }

    public void setHoraEncerramento(Date horaEncerramento) {
        this.horaEncerramento = horaEncerramento;
    }

    public Date getHorarioDePico() {
        return horarioDePico;
    }

    public void setHorarioDePico(Date horarioDePico) {
        this.horarioDePico = horarioDePico;
    }

    private void enviaMensagemFaces(Severity severidade, String titulo, String conteudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(severidade, conteudo, titulo);
        context.addMessage(null, facesMessage);
    }
}
