/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao.impl;

import br.com.dbserver.lunchtime.dao.VotoDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

/**
 *
 * @author Luiz Henrique
 */
public class VotoDAOHibernate implements VotoDAO {

    private Session session;

    public void setSession(Session session) {
        this.session = session;
    }

    public void salvar(Voto voto) throws DAOException {
        try {
            this.session.save(voto);
        } catch (Exception e) {
            throw new DAOException("Não foi possível realizar a operação no banco de dados. " + e.getMessage());
        }
    }

    public void atualizar(Voto voto) {
        this.session.update(voto);
    }

    public void excluir(Voto voto) {
        this.session.delete(voto);
    }

    public Voto carregar(Integer codigo) {
        return (Voto) this.session.get(Voto.class, codigo);
    }

    public List<Voto> listar() {
        return this.session.createCriteria(Voto.class).addOrder(Order.desc("dataVoto")).list();
    }

    public List<Voto> listar(Funcionario funcionario) {
        String hql = "from Voto voto where voto.funcionario = :funcionario";
        Query consulta = this.session.createQuery(hql);
        consulta.setParameter("funcionario", funcionario);
        return consulta.list();
    }

    public List<Voto> listar(Restaurante restaurante) {
        String hql = "from Voto voto where voto.restaurante = :restaurante";
        Query consulta = this.session.createQuery(hql);
        consulta.setParameter("restaurante", restaurante);
        return consulta.list();

    }

    public Voto buscarVoto(Funcionario funcionario, Date diaEscolhido) throws DAOException {
        String hql = "from Voto voto where voto.funcionario = :funcionario and voto.dataVoto = :diaEscolhido";
        Query consulta = this.session.createQuery(hql);
        consulta.setParameter("funcionario", funcionario);
        consulta.setParameter("diaEscolhido", diaEscolhido);
        return (Voto) consulta.uniqueResult();
    }

    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) throws DAOException {
        String hql = "from Voto voto where voto.restaurante = :restaurante and voto.dataVoto = :diaEscolhido";
        Query consulta = this.session.createQuery(hql);
        consulta.setParameter("restaurante", restaurante);
        consulta.setParameter("diaEscolhido", diaEscolhido);
        return consulta.list();
    }
    
    

}
