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
 * @author Luiz Henrique Interface relacionada a entidade Funcionario a ser
 * implementada pelo componente respons�vel pela persist�ncia.
 */
public interface FuncionarioDAO {

    /**
     * Respons�vel por persistir o funcion�rio.
     *
     * @param funcionario � o funcion�rio a ser persistido.
     */
    public void salvar(Funcionario funcionario);

    /**
     * Respons�vel por atualizar um funcion�rio j� existente.
     *
     * @param funcionario � o funcion�rio a ser atualizado.
     */
    public void atualizar(Funcionario funcionario);

    /**
     * Respons�vel por excluir um funcion�rio existente.
     *
     * @param funcionario � o funcion�rio a ser exclu�do.
     */
    public void excluir(Funcionario funcionario);

    /**
     * Respons�vel por recuperar um funcion�rio existente a partir de seu
     * identificador.
     *
     * @param codigo identificador do funcion�rio a ser recuperado.
     * @return o Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os funcion�rios persistidos no sistema.
     */
    public List<Funcionario> listar();

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o c�digo
     * informado.
     *
     * @param codigoFuncionario identificador �nico do funcion�rio na empresa.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscarPorCodigoFuncionarioNaEmpresa(String codigoFuncionario);

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o email
     * informado.
     *
     * @param email email do funcion�rio que deve ser �nico.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscarPorEmail(String email);

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o login
     * informado.
     *
     * @param login identificador �nico do funcion�rio no sistema, sendo uma das
     * credenciais de acesso do mesmo ao sistema.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscarPorLogin(String login);
}
