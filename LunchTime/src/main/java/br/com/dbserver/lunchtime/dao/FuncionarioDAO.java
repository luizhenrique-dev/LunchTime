/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.util.DAOException;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface FuncionarioDAO {

    public void salvar(Funcionario funcionario) throws DAOException;

    public void atualizar(Funcionario funcionario)throws DAOException;

    public void excluir(Funcionario funcionario)throws DAOException;

    public Funcionario carregar(Integer codigo) throws DAOException;

    public List<Funcionario> listar() throws DAOException;

    public Funcionario buscarPorCodigoFuncionarioNaEmpresa(String codigoFuncionario) throws DAOException;

    public Funcionario buscarPorEmail(String email)throws DAOException;
    
    public Funcionario buscarPorLogin(String login)throws DAOException;
}
