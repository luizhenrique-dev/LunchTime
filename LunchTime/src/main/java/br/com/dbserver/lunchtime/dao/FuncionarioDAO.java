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
 * implementada pelo componente responsável pela persistência.
 */
public interface FuncionarioDAO {

    /**
     * Responsável por persistir o funcionário.
     *
     * @param funcionario é o funcionário a ser persistido.
     */
    public void salvar(Funcionario funcionario);

    /**
     * Responsável por atualizar um funcionário já existente.
     *
     * @param funcionario é o funcionário a ser atualizado.
     */
    public void atualizar(Funcionario funcionario);

    /**
     * Responsável por excluir um funcionário existente.
     *
     * @param funcionario é o funcionário a ser excluído.
     */
    public void excluir(Funcionario funcionario);

    /**
     * Responsável por recuperar um funcionário existente a partir de seu
     * identificador.
     *
     * @param codigo identificador do funcionário a ser recuperado.
     * @return o Funcionário com suas informações caso ele exista.
     */
    public Funcionario carregar(Integer codigo);

    /**
     *
     * @return lista contendo todos os funcionários persistidos no sistema.
     */
    public List<Funcionario> listar();

    /**
     * Verifica se já existe algum funcionário persistido que possui o código
     * informado.
     *
     * @param codigoFuncionario identificador único do funcionário na empresa.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscarPorCodigoFuncionarioNaEmpresa(String codigoFuncionario);

    /**
     * Verifica se já existe algum funcionário persistido que possui o email
     * informado.
     *
     * @param email email do funcionário que deve ser único.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscarPorEmail(String email);

    /**
     * Verifica se já existe algum funcionário persistido que possui o login
     * informado.
     *
     * @param login identificador único do funcionário no sistema, sendo uma das
     * credenciais de acesso do mesmo ao sistema.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscarPorLogin(String login);
}
