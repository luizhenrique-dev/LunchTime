/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique 
 * Interface relacionada a entidade Voto a ser implementada pelo componente responsável pela persistência.
 */
public interface VotoDAO {

    /**
     * Responsável por persistir o voto.
     *
     * @param voto é o voto a ser persistido.
     */
    public void salvar(Voto voto);

    /**
     * Responsável por atualizar um voto já existente.
     *
     * @param voto é o voto a ser atualizado.
     */
    public void atualizar(Voto voto);

    /**
     * Responsável por excluir um voto existente.
     *
     * @param voto é o voto a ser excluído.
     */
    public void excluir(Voto voto);

    /**
     * Responsável por recuperar um voto existente a partir de seu
     * identificador.
     *
     * @param codigo identificador único do voto a ser recuperado.
     * @return o Voto com suas informações caso ele exista.
     */
    public Voto carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os votos persistidos no sistema.
     */
    public List<Voto> listar();

    /**
     * Lista contendo todos os votos de um determinado funcionário cadastrado.
     * @param funcionario é o funcionário que se deseja obter os votos realizados.
     * @return lista de votos realizados pelo funcionário.
     */
    public List<Voto> listar(Funcionario funcionario);

    /**
     * Lista contendo todos os votos de um determinado restaurante cadastrado.
     * @param restaurante é o restaurante o qual se deseja obter os votos recebidos.
     * @return lista de votos obtidos pelo restaurante.
     */
    public List<Voto> listar(Restaurante restaurante);

    /**
     * Lista contendo todos os votos de um determinado restaurante cadastrado no dia definido.
     * @param restaurante é o restaurante o qual se deseja obter os votos recebidos.
     * @param diaEscolhido é o dia em que se deseja buscar os votos.
     * @return lista de votos obtidos pelo restaurante no dia escolhido.
     */
    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido);

    /**
     * Verifica se o voto já foi feito pelo funcionário no dia escolhido.
     * @param funcionario é o funcionário que voto deve estar vinculado.
     * @param diaEscolhido é o dia em que se deseja buscar o voto.
     * @return Voto com suas informações caso exista.
     */
    public Voto buscarVoto(Funcionario funcionario, Date diaEscolhido);
}
