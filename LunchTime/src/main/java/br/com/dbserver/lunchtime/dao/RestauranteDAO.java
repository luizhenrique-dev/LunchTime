/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import java.util.List;

/**
 *
 * @author Luiz Henrique Interface relacionada a entidade Restaurante a ser
 * implementada pelo componente respons�vel pela persist�ncia.
 */
public interface RestauranteDAO {

    /**
     * Respons�vel por persistir o restaurante.
     *
     * @param restaurante � o restaurante a ser persistido.
     */
    public void salvar(Restaurante restaurante);

    /**
     * Respons�vel por atualizar um restaurante j� existente.
     *
     * @param restaurante � o restaurante a ser atualizado.
     */
    public void atualizar(Restaurante restaurante);

    /**
     * Respons�vel por excluir um restaurante existente.
     *
     * @param restaurante � o restaurante a ser exclu�do.
     */
    public void excluir(Restaurante restaurante);

    /**
     * Respons�vel por recuperar um restaurante existente a partir de seu
     * identificador.
     *
     * @param codigo identificador �nico do restaurante a ser recuperado.
     * @return o Restaurante com suas informa��es caso ele exista.
     */
    public Restaurante carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os restaurantes persistidos no sistema.
     */
    public List<Restaurante> listar();
}
