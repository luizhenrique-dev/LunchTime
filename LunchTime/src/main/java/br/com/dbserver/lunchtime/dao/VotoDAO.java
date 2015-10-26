/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface VotoDAO {

    public void salvar(Voto voto) throws DAOException;

    public void atualizar(Voto voto) throws DAOException;

    public void excluir(Voto voto) throws DAOException;

    public Voto carregar(Integer codigo) throws DAOException;

    public List<Voto> listar() throws DAOException;
    
    public List<Voto> listar(Funcionario funcionario) throws DAOException;
    
    public List<Voto> listar(Restaurante restaurante) throws DAOException;
    
     public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido) throws DAOException;
    
    public Voto buscarVoto(Funcionario funcionario, Date diaEscolhido) throws DAOException;
}
