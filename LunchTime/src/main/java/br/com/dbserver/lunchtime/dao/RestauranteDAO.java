/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Voto;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface RestauranteDAO {

    public void salvar(Voto voto);

    public void atualizar(Voto voto);

    public void excluir(Voto voto);

    public Voto carregar(Integer codigo);

    public List<Voto> listar();
}
