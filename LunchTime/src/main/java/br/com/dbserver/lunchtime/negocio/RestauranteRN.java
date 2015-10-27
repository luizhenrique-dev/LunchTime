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
import br.com.dbserver.lunchtime.util.DAOFactory;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por conter a regra de negócio da entidade Restaurante.
 * Sendo uma ponte entre o ManagedBean e a camada de acesso aos dados DAO.
 *
 * @author Luiz Henrique
 */
public class RestauranteRN {

    private RestauranteDAO restauranteDAO;

    public RestauranteRN() {
        this.restauranteDAO = DAOFactory.criarRestauranteDAO();
    }

    /**
     * Responsável por requisitar à camada de acesso a recuperação de um
     * restaurante.
     *
     * @param id é o identificador único do restaurante.
     * @return restaurante com suas respectivas informações caso ele exista.
     */
    public Restaurante carregar(Integer id) {
        return this.restauranteDAO.carregar(id);
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a persistência de
     * um restaurante.
     *
     * @param restaurante é o restaurante que se deseja persistir.
     * @param horaAbertura é a hora em que o restaurante abre.
     * @param horaEncerramento é a hora em que o restaurante fecha.
     * @param horarioDePico é a hora de maior movimento no restaurante.
     */
    public void salvar(Restaurante restaurante, Date horaAbertura, Date horaEncerramento, Date horarioDePico) {
        Integer codigo = restaurante.getId();
        if (codigo == null || codigo == 0) {
            preencheHorarios(restaurante, horaAbertura, horaEncerramento, horarioDePico);
            this.restauranteDAO.salvar(restaurante);
        } else {
            preencheHorarios(restaurante, horaAbertura, horaEncerramento, horarioDePico);
            this.restauranteDAO.atualizar(restaurante);
        }
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a exclusão de um
     * restaurante.
     *
     * @param restaurante
     */
    public void excluir(Restaurante restaurante) {
        this.restauranteDAO.excluir(restaurante);
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista de todos
     * os restaurantes persistidos com a seus respectivos votos recebidos no dia
     * informado no parâmetro.
     *
     * @param diaEscolhido é o dia em que se deseja obter o total de votos
     * recebidos por cada restaurante.
     * @return lista de todos os restaurantes e seus respectivos votos.
     */
    public List<Restaurante> listar(Date diaEscolhido) {
        List<Restaurante> lista = this.restauranteDAO.listar();
        preencheVotosDoDiaRestaurante(lista, diaEscolhido);
        return lista;
    }

    /**
     * Método responsável por preencher o atributo 'quantidadeVotosDia' de cada
     * restaurante da lista no dia determinado no parâmetro.
     *
     * @param lista é a lista de todos os restaurantes persistidos.
     * @param diaEscolhido é o dia em que se deseja obter os votos de cada
     * restaurante.
     */
    private void preencheVotosDoDiaRestaurante(List<Restaurante> lista, Date diaEscolhido) {
        VotoDAO votoDAO = DAOFactory.criarVotoDAO();
        for (Restaurante restaurante : lista) {
            List<Voto> listaVotosRestauranteHoje = votoDAO.listarVotosDoDia(restaurante, diaEscolhido);
            restaurante.setQuantidadeVotosDia(listaVotosRestauranteHoje.size());
        }
    }

    /**
     * Método responsável por preencher os atributos 'horaAbertura'
     * 'horaEncerramento' e 'horarioDePico' do restaurante informado no
     * parâmetro caso os mesmos não sejem nulos.
     *
     * @param restaurante é o restaurante que se deseja atribuir as informações.
     * @param horaAbertura é a hora em que o restaurante abre.
     * @param horaEncerramento é a hora em que o restaurante fecha.
     * @param horarioDePico é a hora de maior movimento no restaurante.
     */
    private void preencheHorarios(Restaurante restaurante, Date horaAbertura, Date horaEncerramento, Date horarioDePico) {
        if (horaEncerramento != null) {
            restaurante.setHoraEncerramento(new Time(horaEncerramento.getTime()));
        }
        if (horaAbertura != null) {
            restaurante.setHoraAbertura(new Time(horaAbertura.getTime()));
        }
        if (horarioDePico != null) {
            restaurante.setHorarioDePico(new Time(horarioDePico.getTime()));
        }
    }
}
