/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.dao.RestauranteDAO;
import br.com.dbserver.lunchtime.dao.VotoDAO;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.util.DAOException;
import br.com.dbserver.lunchtime.util.DAOFactory;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public class RestauranteRN {

    private RestauranteDAO restauranteDAO;

    public RestauranteRN() {
        this.restauranteDAO = DAOFactory.criarRestauranteDAO();
    }

    public Restaurante carregar(Integer id) throws DAOException {
        return this.restauranteDAO.carregar(id);
    }

    public void salvar(Restaurante restaurante, Date horaAbertura, Date horaEncerramento, Date horarioDePico) throws DAOException {
        Integer codigo = restaurante.getId();
        if (codigo == null || codigo == 0) {
            restaurante.setHoraAbertura(new Time(horaAbertura.getTime()));
            restaurante.setHoraEncerramento(new Time(horaEncerramento.getTime()));
            restaurante.setHorarioDePico(new Time(horarioDePico.getTime()));
            this.restauranteDAO.salvar(restaurante);
        } else {
            this.restauranteDAO.atualizar(restaurante);
        }
    }

    public void excluir(Restaurante restaurante) throws DAOException {
        this.restauranteDAO.excluir(restaurante);
    }

    public List<Restaurante> listar() throws DAOException {
        List<Restaurante> lista = this.restauranteDAO.listar();
        preencheVotosDoDiaRestaurante(lista);
        return lista;
    }

    private void preencheVotosDoDiaRestaurante(List<Restaurante> lista) throws DAOException{
        VotoDAO votoDAO = DAOFactory.criarVotoDAO();
        for (Restaurante restaurante : lista) {
            List<Voto> listaVotosRestauranteHoje = votoDAO.listarVotosDoDia(restaurante, new Date(System.currentTimeMillis()));
            restaurante.setQuantidadeVotosDia(listaVotosRestauranteHoje.size());
        }
    }
}
