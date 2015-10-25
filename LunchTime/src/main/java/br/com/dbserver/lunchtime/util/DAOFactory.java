/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.util;

import br.com.dbserver.lunchtime.dao.FuncionarioDAO;
import br.com.dbserver.lunchtime.dao.RestauranteDAO;
import br.com.dbserver.lunchtime.dao.VotoDAO;
import br.com.dbserver.lunchtime.dao.impl.FuncionarioDAOHibernate;
import br.com.dbserver.lunchtime.dao.impl.RestauranteDAOHibernate;
import br.com.dbserver.lunchtime.dao.impl.VotoDAOHibernate;

/**
 *
 * @author Luiz Henrique
 */

public class DAOFactory {

    public static FuncionarioDAO criarFuncionarioDAO() {
        FuncionarioDAOHibernate funcionarioDAO = new FuncionarioDAOHibernate();
        funcionarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return funcionarioDAO;
    }

    public static VotoDAO criarVotoDAO() {
        VotoDAOHibernate votoDAO = new VotoDAOHibernate();
        votoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return votoDAO;
    }

    public static RestauranteDAO criarRestauranteDAO() {
        RestauranteDAOHibernate restauranteDAO = new RestauranteDAOHibernate();
        restauranteDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return restauranteDAO;
    }
}
