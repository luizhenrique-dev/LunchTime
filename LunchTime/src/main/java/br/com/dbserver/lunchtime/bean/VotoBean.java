/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.bean;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.negocio.VotoRN;
import br.com.dbserver.lunchtime.util.ContextoUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Luiz Henrique
 */
@ManagedBean(name = "votoBean")
@ViewScoped
public class VotoBean {

    private Voto voto = new Voto();
    private List<Voto> lista;
    private List<Voto> listaVotosRestaurante;
    private List<Voto> listaVotosRestauranteDia;
    private List<Voto> listaVotosFuncionario;
    private Restaurante restauranteSelecionado;

    public String novo() {
        this.voto = new Voto();
        return "/publico/cadastrarVoto";
    }

    public String salvar() {
        VotoRN votoRN = new VotoRN();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        Funcionario funcionario = contextoBean.getFuncionarioLogado();
        if (restauranteSelecionado != null) {
            if (!votoRN.buscaVoto(funcionario, voto.getDataVoto())) {
                voto.setFuncionario(funcionario);
                votoRN.salvar(this.voto);
                enviaMensagemFaces(FacesMessage.SEVERITY_INFO, "Clique em 'Acompanhar Votação' no menu 'Eleições' para mais informações.", "Voto realizado com sucesso!");
                novo();
                return "votos";
            } else {
                enviaMensagemFaces(FacesMessage.SEVERITY_ERROR, "Você já votou neste dia!", "Erro:");
                return null;
            }
        } else {
            enviaMensagemFaces(FacesMessage.SEVERITY_WARN, "Erro de validação", "Escolha o restaurante em que você vai votar!");
            return null;
        }
    }

    public String listarVotos() {
        return "/restrito/votos";
    }

    public void excluir() {
        VotoRN votoRN = new VotoRN();
        votoRN.excluir(this.voto);
        this.lista = null;
    }

    public List<Voto> getLista() {
        if (this.lista == null) {
            VotoRN votoRN = new VotoRN();
            this.lista = votoRN.listar();
        }
        return this.lista;
    }

    public List<Voto> getListaVotosRestauranteDia() {
        VotoRN votoRN = new VotoRN();
        this.listaVotosRestauranteDia = votoRN.listarVotosDoDia(restauranteSelecionado, new Date(System.currentTimeMillis()));
        return listaVotosRestauranteDia;
    }

    public void setListaVotosRestauranteDia(List<Voto> listaVotosRestauranteDia) {
        this.listaVotosRestauranteDia = listaVotosRestauranteDia;
    }

    public Voto getVoto() {
        return voto;
    }

    public void setVoto(Voto voto) {
        this.voto = voto;
    }

    public List<Voto> getListaVotosRestaurante() {
        VotoRN votoRN = new VotoRN();
        this.listaVotosRestaurante = votoRN.listarVotosRestaurante(restauranteSelecionado);
        return listaVotosRestaurante;
    }

    public void setListaVotosRestaurante(List<Voto> listaVotosRestaurante) {
        this.listaVotosRestaurante = listaVotosRestaurante;
    }

    public List<Voto> getListaVotosFuncionario() {
        VotoRN votoRN = new VotoRN();
        ContextoBean contextoBean = ContextoUtil.getContextoBean();
        this.listaVotosFuncionario = votoRN.listarVotosFuncionario(contextoBean.getFuncionarioLogado());
        return listaVotosFuncionario;
    }

    public void setListaVotosFuncionario(List<Voto> listaVotosFuncionario) {
        this.listaVotosFuncionario = listaVotosFuncionario;
    }

    public Restaurante getRestauranteSelecionado() {
        return restauranteSelecionado;
    }

    public void setRestauranteSelecionado(Restaurante restauranteSelecionado) {
        this.restauranteSelecionado = restauranteSelecionado;
    }

    private void enviaMensagemFaces(Severity severidade, String titulo, String conteudo) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(severidade, conteudo, titulo);
        context.addMessage(null, facesMessage);
    }

    public void onRowSelectRestaurante(SelectEvent event) {
        this.restauranteSelecionado = (Restaurante) event.getObject();
        voto.setRestaurante(this.restauranteSelecionado);
        enviaMensagemFaces(FacesMessage.SEVERITY_INFO, ((Restaurante) event.getObject()).getNome(), "Restaurante selecionado: ");
    }
}
