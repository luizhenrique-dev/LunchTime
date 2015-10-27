/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.dao.VotoDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.util.DAOFactory;
import br.com.dbserver.lunchtime.util.RNException;
import java.util.Date;
import java.util.List;

/**
 * Classe responsável por conter a regra de negócio da entidade Voto. Sendo uma
 * ponte entre o ManagedBean e a camada de acesso aos dados DAO.
 *
 * @author Luiz Henrique
 */
public class VotoRN {

    private VotoDAO votoDAO;

    public VotoRN() {
        this.votoDAO = DAOFactory.criarVotoDAO();
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a persistência de
     * um voto.
     *
     * @param voto é o voto que se deseja persistir.
     * @return true caso a operação seja realizada com sucesso.
     * @throws RNException exceção lançada caso o voto não possa ser persistido.
     */
    public boolean salvar(Voto voto) throws RNException {
        Integer codigo = voto.getId();
        if ((codigo == null || codigo == 0) && votoDisponivelDia(voto.getFuncionario(), voto.getDataVoto())) {
            this.votoDAO.salvar(voto);
        } else {
            this.votoDAO.atualizar(voto);
        }
        return true;
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a exclusão de um
     * voto.
     *
     * @param voto é o voto a ser excluído.
     * @return true caso a operação seja realizada com sucesso.
     */
    public boolean excluir(Voto voto) {
        this.votoDAO.excluir(voto);
        return true;
    }

    /**
     * Responsável por requisitar à camada de acesso a recuperação de um voto.
     *
     * @param id é o identificador único do voto.
     * @return voto com suas respectivas informações caso ele exista.
     */
    public Voto carregar(Integer id) {
        return this.votoDAO.carregar(id);
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista de votos
     * de um restaurante.
     *
     * @param restaurante é o restaurante que se deseja obter os votos.
     * @return lista contendo todos os votos do restaurante informado no
     * parâmetro.
     */
    public List<Voto> listarVotosRestaurante(Restaurante restaurante) {
        return this.votoDAO.listar(restaurante);
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista de votos
     * realizado por um funcionário.
     *
     * @param funcionario é o funcionário que se dejesa buscar os votos.
     * @return lista contendo todos os votos do funcionário informado no
     * parâmetro.
     */
    public List<Voto> listarVotosFuncionario(Funcionario funcionario) {
        return this.votoDAO.listar(funcionario);
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista de todos
     * os votos persistidos.
     *
     * @return lista contendo os votos encontrados.
     */
    public List<Voto> listar() {
        return this.votoDAO.listar();
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista contendo
     * todos os votos de um determinado restaurante cadastrado no dia definido.
     *
     * @param restaurante é o restaurante o qual se deseja obter os votos
     * recebidos.
     * @param diaEscolhido é o dia em que se deseja buscar os votos.
     * @return lista de votos obtidos pelo restaurante no dia escolhido.
     */
    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) {
        return this.votoDAO.listarVotosDoDia(restaurante, diaEscolhido);
    }

    /**
     * Verifica se o funcionário já votou no dia escolhido. Afim de evitar que a
     * mesma pessoa vote mais de uma vez por dia.
     *
     * @param funcionario é o funcionário que voto deve estar vinculado.
     * @param dataEscolhida é o dia em que se deseja buscar o voto.
     * @return true - caso o voto possa ser realizado.
     * @throws RNException exceção lançada caso o voto nesse dia já tenha sido
     * realizado. Impedindo a realização de outro no mesmo dia.
     */
    public boolean votoDisponivelDia(Funcionario funcionario, Date dataEscolhida) throws RNException {
        if (this.votoDAO.buscarVoto(funcionario, dataEscolhida) != null) {
            throw new RNException("Você já votou neste dia! Seu voto não foi efetivado.");
        }
        return true;
    }
}
