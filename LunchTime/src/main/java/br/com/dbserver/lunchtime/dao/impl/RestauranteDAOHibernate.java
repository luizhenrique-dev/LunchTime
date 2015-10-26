/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao.impl;

import br.com.dbserver.lunchtime.dao.RestauranteDAO;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.util.DAOException;
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
    public void salvar(Restaurante restaurante) throws DAOException {
        try {
            this.session.save(restaurante);
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a operação no banco de dados. " + e.getMessage());
        }
    }

    @Override
    public void excluir(Restaurante restaurante) throws DAOException{
        this.session.delete(restaurante);
    }

    @Override
    public Restaurante carregar(Integer codigo) throws DAOException{
        return (Restaurante) this.session.get(Restaurante.class, codigo);
    }

    @Override
    public void atualizar(Restaurante restaurante) throws DAOException{
        this.session.update(restaurante);
    }

    @Override
    public List<Restaurante> listar() throws DAOException{
        return this.session.createCriteria(Restaurante.class).addOrder(Order.asc("nome")).list();
    }
}
