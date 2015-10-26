/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface RestauranteDAO {

    public void salvar(Restaurante restaurante) throws DAOException;

    public void atualizar(Restaurante restaurante) throws DAOException;

    public void excluir(Restaurante restaurante) throws DAOException;

    public Restaurante carregar(Integer codigo) throws DAOException;

    public List<Restaurante> listar() throws DAOException;
}
