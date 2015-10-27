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
 * @author Luiz Henrique
 */
public interface RestauranteDAO {

    public void salvar(Restaurante restaurante);

    public void atualizar(Restaurante restaurante);

    public void excluir(Restaurante restaurante);

    public Restaurante carregar(Integer codigo);

    public List<Restaurante> listar();
}
