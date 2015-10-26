/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.dao.VotoDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.util.DAOException;
import br.com.dbserver.lunchtime.util.DAOFactory;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public class VotoRN {

    private VotoDAO votoDAO;

    public VotoRN() {
        this.votoDAO = DAOFactory.criarVotoDAO();
    }

    public void salvar(Voto voto) throws DAOException {
        Integer codigo = voto.getId();
        if (codigo == null || codigo == 0) {
            this.votoDAO.salvar(voto);
        } else {
            this.votoDAO.atualizar(voto);
        }
    }

    public void excluir(Voto voto) throws DAOException {
        this.votoDAO.excluir(voto);
    }

    public Voto carregar(Integer id) throws DAOException {
        return this.votoDAO.carregar(id);
    }

    public List<Voto> listarVotosRestaurante(Restaurante restaurante) throws DAOException {
        return this.votoDAO.listar(restaurante);
    }

    public List<Voto> listarVotosFuncionario(Funcionario funcionario) throws DAOException {
        return this.votoDAO.listar(funcionario);
    }

    public List<Voto> listar() throws DAOException {
        return this.votoDAO.listar();
    }
    
    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) throws DAOException {
        return this.votoDAO.listarVotosDoDia(restaurante, diaEscolhido);
    }
    
    public boolean buscaVoto(Funcionario funcionario, Date dataEscolhida) throws DAOException {
        if (this.votoDAO.buscarVoto(funcionario, dataEscolhida) == null){
            return false;
        }
        return true;
    }
}
