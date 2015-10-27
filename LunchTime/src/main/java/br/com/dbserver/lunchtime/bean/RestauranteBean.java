/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.negocio.RestauranteRN;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * Classe respons�vel por ser uma ponte entre a regra de neg�cio da entidade
 * Restaurante com as Views relacionadas a mesma. "Delegando" fun��es
 * espec�ficas para cada View.
 *
 * @author Luiz Henrique
 */
/**
 * Anota��o que reflete o nome a ser utilizado para chamar essa classe atrav�s
 * da View.
 */
@ManagedBean(name = "restauranteBean")
@RequestScoped
public class RestauranteBean {

    /**
     * Este � o objeto utilizado para manipular inser��es, edi��es e dele��es.
     */
    private Restaurante restaurante = new Restaurante();
    private List<Restaurante> lista;
    private Date horaAbertura, horaEncerramento, horarioDePico;
    /**
     * � o dia escolhido na view para se obter o resultado da vota��o de cada restaurante.
     */
    private Date dataFiltroVotos = new Date(System.currentTimeMillis());

    public String novo() {
        this.restaurante = new Restaurante();
        return "/publico/cadastrarRestaurante";
    }

    public String salvar() {
        RestauranteRN restauranteRN = new RestauranteRN();
        restauranteRN.salvar(this.restaurante, horaAbertura, horaEncerramento, horarioDePico);
        enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Sucesso", "Restaurante cadastrado com sucesso!");
        return "restaurantes";
    }

    public String editar() {
        this.lista = null;
        return "cadastrarRestaurante";
    }

    public String verDetalhes() {
        return "/restrito/restaurante";

    }

    public String listarRestaurantes() {
        return "/restrito/restaurantes";
    }

    public void excluir() {
        RestauranteRN restauranteRN = new RestauranteRN();
        restauranteRN.excluir(this.restaurante);
        this.lista = null;

    }

    public List<Restaurante> getLista() {
        if (this.lista == null) {

            RestauranteRN restauranteRN = new RestauranteRN();
            this.lista = restauranteRN.listar(dataFiltroVotos);

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

    public Date getDataFiltroVotos() {
        return dataFiltroVotos;
    }

    public void setDataFiltroVotos(Date dataFiltroVotos) {
        this.dataFiltroVotos = dataFiltroVotos;
    }

    /**
     * M�todo respons�vel por enviar mensagens para as views de acordo com as
     * opera��es realizadas.
     *
     * @param severidade � o grau da mensagem: erro, aviso, informativo.
     * @param titulo � o t�tulo da mensagem.
     * @param conteudo � o conte�do da mensagem.
     */
    private void enviaMensagemFaces(Severity severidade, String titulo, String conteudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(severidade, conteudo, titulo);
        context.addMessage(null, facesMessage);
    }

    /**
     * M�todo respons�vel por atualizar a lista a cada nova requisi��o de pesquisa (filtragem).
     */
    public void filtrarPorDia() {
        if (this.lista != null) {
            lista.clear();
        }
        getLista();
    }
}
