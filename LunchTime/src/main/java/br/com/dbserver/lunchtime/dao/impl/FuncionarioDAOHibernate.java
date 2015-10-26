/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao.impl;

import br.com.dbserver.lunchtime.dao.FuncionarioDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Luiz Henrique
 */
public class FuncionarioDAOHibernate implements FuncionarioDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Funcionario funcionario) throws DAOException {
        try {
            this.session.save(funcionario);
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a operação no banco de dados. " + e.getMessage());
        }
    }

    @Override
    public void excluir(Funcionario funcionario) throws DAOException{
        try {
            this.session.delete(funcionario);
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a operação no banco de dados. " + e.getMessage());
        }
    }

    @Override
    public Funcionario carregar(Integer codigo) {
        return (Funcionario) this.session.get(Funcionario.class, codigo);
    }

    @Override
    public void atualizar(Funcionario funcionario) throws DAOException {
        try {
            if (funcionario.getPermissao() == null || funcionario.getPermissao().size() == 0) {
                Funcionario funcionarioPermissao = this.carregar(funcionario.getId());
                funcionario.setPermissao(funcionarioPermissao.getPermissao());
                this.session.evict(funcionarioPermissao);
            }
            this.session.update(funcionario);
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a operação no banco de dados. " + e.getMessage());
        }
    }

    @Override
    public Funcionario buscarPorCodigoFuncionarioNaEmpresa(String codigo) {
        String hql = "from Funcionario u where u.codigoFuncionarioNaEmpresa = :codigo";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("codigo", codigo);
        return (Funcionario) consulta.uniqueResult();
    }

    @Override
    public Funcionario buscarPorEmail(String email) {
        String hql = "from Funcionario u where u.email = :email";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("email", email);
        return (Funcionario) consulta.uniqueResult();
    }

    @Override
    public Funcionario buscarPorLogin(String login) {
        String hql = "from Funcionario u where u.login = :login";
        Query consulta = this.session.createQuery(hql);
        consulta.setString("login", login);
        return (Funcionario) consulta.uniqueResult();
    }

    @Override
    public List<Funcionario> listar() {
        Criteria criteria = this.session.createCriteria(Funcionario.class);
        criteria.addOrder(Order.asc("nome"));
        return criteria.list();
    }
}
