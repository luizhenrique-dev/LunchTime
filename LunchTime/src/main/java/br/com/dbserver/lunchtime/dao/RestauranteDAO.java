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
 * implementada pelo componente responsável pela persistência.
 */
public interface RestauranteDAO {

    /**
     * Responsável por persistir o restaurante.
     *
     * @param restaurante é o restaurante a ser persistido.
     */
    public void salvar(Restaurante restaurante);

    /**
     * Responsável por atualizar um restaurante já existente.
     *
     * @param restaurante é o restaurante a ser atualizado.
     */
    public void atualizar(Restaurante restaurante);

    /**
     * Responsável por excluir um restaurante existente.
     *
     * @param restaurante é o restaurante a ser excluído.
     */
    public void excluir(Restaurante restaurante);

    /**
     * Responsável por recuperar um restaurante existente a partir de seu
     * identificador.
     *
     * @param codigo identificador único do restaurante a ser recuperado.
     * @return o Restaurante com suas informações caso ele exista.
     */
    public Restaurante carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os restaurantes persistidos no sistema.
     */
    public List<Restaurante> listar();
}
