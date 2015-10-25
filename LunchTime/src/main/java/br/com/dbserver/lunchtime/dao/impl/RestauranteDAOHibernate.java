/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao.impl;

import br.com.dbserver.lunchtime.dao.RestauranteDAO;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Luiz Henrique
 */
public class RestauranteDAOHibernate implements RestauranteDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Restaurante restaurante) {
        this.session.save(restaurante);
    }

    @Override
    public void excluir(Restaurante restaurante) {
        this.session.delete(restaurante);
    }

    @Override
    public Restaurante carregar(Integer codigo) {
        return (Restaurante) this.session.get(Restaurante.class, codigo);
    }

    @Override
    public void atualizar(Restaurante restaurante) {
        this.session.update(restaurante);
    }

    @Override
    public List<Restaurante> listar() {
        return this.session.createCriteria(Restaurante.class).addOrder(Order.asc("nome")).list();
    }
}
