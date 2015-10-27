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
 * Interface relacionada a entidade Voto a ser implementada pelo componente respons�vel pela persist�ncia.
 */
public interface VotoDAO {

    /**
     * Respons�vel por persistir o voto.
     *
     * @param voto � o voto a ser persistido.
     */
    public void salvar(Voto voto);

    /**
     * Respons�vel por atualizar um voto j� existente.
     *
     * @param voto � o voto a ser atualizado.
     */
    public void atualizar(Voto voto);

    /**
     * Respons�vel por excluir um voto existente.
     *
     * @param voto � o voto a ser exclu�do.
     */
    public void excluir(Voto voto);

    /**
     * Respons�vel por recuperar um voto existente a partir de seu
     * identificador.
     *
     * @param codigo identificador �nico do voto a ser recuperado.
     * @return o Voto com suas informa��es caso ele exista.
     */
    public Voto carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os votos persistidos no sistema.
     */
    public List<Voto> listar();

    /**
     * Lista contendo todos os votos de um determinado funcion�rio cadastrado.
     * @param funcionario � o funcion�rio que se deseja obter os votos realizados.
     * @return lista de votos realizados pelo funcion�rio.
     */
    public List<Voto> listar(Funcionario funcionario);

    /**
     * Lista contendo todos os votos de um determinado restaurante cadastrado.
     * @param restaurante � o restaurante o qual se deseja obter os votos recebidos.
     * @return lista de votos obtidos pelo restaurante.
     */
    public List<Voto> listar(Restaurante restaurante);

    /**
     * Lista contendo todos os votos de um determinado restaurante cadastrado no dia definido.
     * @param restaurante � o restaurante o qual se deseja obter os votos recebidos.
     * @param diaEscolhido � o dia em que se deseja buscar os votos.
     * @return lista de votos obtidos pelo restaurante no dia escolhido.
     */
    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido);

    /**
     * Verifica se o voto j� foi feito pelo funcion�rio no dia escolhido.
     * @param funcionario � o funcion�rio que voto deve estar vinculado.
     * @param diaEscolhido � o dia em que se deseja buscar o voto.
     * @return Voto com suas informa��es caso exista.
     */
    public Voto buscarVoto(Funcionario funcionario, Date diaEscolhido);
}
