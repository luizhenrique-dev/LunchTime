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
import br.com.dbserver.lunchtime.util.DAOFactory;
import br.com.dbserver.lunchtime.util.RNException;
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

    public void salvar(Voto voto) throws RNException {
        Integer codigo = voto.getId();
        if ((codigo == null || codigo == 0) && votoDisponivelDia(voto.getFuncionario(), voto.getDataVoto())) {
            this.votoDAO.salvar(voto);
        } else {
            this.votoDAO.atualizar(voto);
        }
    }

    public void excluir(Voto voto) {
        this.votoDAO.excluir(voto);
    }

    public Voto carregar(Integer id) {
        return this.votoDAO.carregar(id);
    }

    public List<Voto> listarVotosRestaurante(Restaurante restaurante) {
        return this.votoDAO.listar(restaurante);
    }

    public List<Voto> listarVotosFuncionario(Funcionario funcionario) {
        return this.votoDAO.listar(funcionario);
    }

    public List<Voto> listar() {
        return this.votoDAO.listar();
    }

    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) {
        return this.votoDAO.listarVotosDoDia(restaurante, diaEscolhido);
    }

    public boolean votoDisponivelDia(Funcionario funcionario, Date dataEscolhida) throws RNException{
        if (this.votoDAO.buscarVoto(funcionario, dataEscolhida) != null) {
            throw new RNException("Você já votou neste dia! Seu voto não foi efetivado.");
        }
        return true;
    }
}
