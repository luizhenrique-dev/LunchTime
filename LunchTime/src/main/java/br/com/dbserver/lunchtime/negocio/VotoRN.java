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
 * Classe respons�vel por conter a regra de neg�cio da entidade Voto. Sendo uma
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
     * Respons�vel por requisitar � camada de acesso aos dados a persist�ncia de
     * um voto.
     *
     * @param voto � o voto que se deseja persistir.
     * @return true caso a opera��o seja realizada com sucesso.
     * @throws RNException exce��o lan�ada caso o voto n�o possa ser persistido.
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
     * Respons�vel por requisitar � camada de acesso aos dados a exclus�o de um
     * voto.
     *
     * @param voto � o voto a ser exclu�do.
     * @return true caso a opera��o seja realizada com sucesso.
     */
    public boolean excluir(Voto voto) {
        this.votoDAO.excluir(voto);
        return true;
    }

    /**
     * Respons�vel por requisitar � camada de acesso a recupera��o de um voto.
     *
     * @param id � o identificador �nico do voto.
     * @return voto com suas respectivas informa��es caso ele exista.
     */
    public Voto carregar(Integer id) {
        return this.votoDAO.carregar(id);
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a lista de votos
     * de um restaurante.
     *
     * @param restaurante � o restaurante que se deseja obter os votos.
     * @return lista contendo todos os votos do restaurante informado no
     * par�metro.
     */
    public List<Voto> listarVotosRestaurante(Restaurante restaurante) {
        return this.votoDAO.listar(restaurante);
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a lista de votos
     * realizado por um funcion�rio.
     *
     * @param funcionario � o funcion�rio que se dejesa buscar os votos.
     * @return lista contendo todos os votos do funcion�rio informado no
     * par�metro.
     */
    public List<Voto> listarVotosFuncionario(Funcionario funcionario) {
        return this.votoDAO.listar(funcionario);
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a lista de todos
     * os votos persistidos.
     *
     * @return lista contendo os votos encontrados.
     */
    public List<Voto> listar() {
        return this.votoDAO.listar();
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a lista contendo
     * todos os votos de um determinado restaurante cadastrado no dia definido.
     *
     * @param restaurante � o restaurante o qual se deseja obter os votos
     * recebidos.
     * @param diaEscolhido � o dia em que se deseja buscar os votos.
     * @return lista de votos obtidos pelo restaurante no dia escolhido.
     */
    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) {
        return this.votoDAO.listarVotosDoDia(restaurante, diaEscolhido);
    }

    /**
     * Verifica se o funcion�rio j� votou no dia escolhido. Afim de evitar que a
     * mesma pessoa vote mais de uma vez por dia.
     *
     * @param funcionario � o funcion�rio que voto deve estar vinculado.
     * @param dataEscolhida � o dia em que se deseja buscar o voto.
     * @return true - caso o voto possa ser realizado.
     * @throws RNException exce��o lan�ada caso o voto nesse dia j� tenha sido
     * realizado. Impedindo a realiza��o de outro no mesmo dia.
     */
    public boolean votoDisponivelDia(Funcionario funcionario, Date dataEscolhida) throws RNException {
        if (this.votoDAO.buscarVoto(funcionario, dataEscolhida) != null) {
            throw new RNException("Voc� j� votou neste dia! Seu voto n�o foi efetivado.");
        }
        return true;
    }
}
