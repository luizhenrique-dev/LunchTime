/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface FuncionarioDAO {

    public void salvar(Funcionario funcionario);

    public void atualizar(Funcionario funcionario);

    public void excluir(Funcionario funcionario);

    public Funcionario carregar(Integer codigo);

    public List<Funcionario> listar();

    public Funcionario buscarPorCodigoFuncionarioNaEmpresa(String codigoFuncionario);

    public Funcionario buscarPorEmail(String email);

    public Funcionario buscarPorLogin(String login);
}
