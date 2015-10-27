/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.dao.FuncionarioDAO;
import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.util.DAOFactory;
import br.com.dbserver.lunchtime.util.RNException;
import java.util.Date;
import java.util.List;

/**
 * Classe respons�vel por conter a regra de neg�cio da entidade Funcionario.
 * Sendo uma ponte entre o ManagedBean e a camada de acesso aos dados DAO.
 *
 * @author Luiz Henrique
 */
public class FuncionarioRN {

    private FuncionarioDAO funcionarioDAO;

    public FuncionarioRN() {
        this.funcionarioDAO = DAOFactory.criarFuncionarioDAO();
    }

    /**
     * Respons�vel por requisitar � camada de acesso a recupera��o de um
     * funcionario.
     *
     * @param id � o identificador �nico do funcion�rio.
     * @return funcion�rio com suas respectivas informa��es caso ele exista.
     */
    public Funcionario carregar(Integer id) {
        return this.funcionarioDAO.carregar(id);
    }

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o c�digo
     * informado.
     *
     * @param codigo identificador �nico do funcion�rio na empresa.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscaPorCodigoFuncionarioNaEmpresa(String codigo) {
        return this.funcionarioDAO.buscarPorCodigoFuncionarioNaEmpresa(codigo);
    }

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o login
     * informado.
     *
     * @param login identificador �nico do funcion�rio no sistema, sendo uma das
     * credenciais de acesso do mesmo ao sistema.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscaPorLogin(String login) {
        return this.funcionarioDAO.buscarPorLogin(login);
    }

    /**
     * Verifica se j� existe algum funcion�rio persistido que possui o email
     * informado.
     *
     * @param email email do funcion�rio que deve ser �nico.
     * @return Funcion�rio com suas informa��es caso ele exista.
     */
    public Funcionario buscaPorEmail(String email) {
        return this.funcionarioDAO.buscarPorEmail(email);
    }

    public String getEmailFuncionario(Funcionario funcionario) {
        return this.funcionarioDAO.carregar(funcionario.getId()).getEmail();
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a persist�ncia de
     * um funcion�rio. O m�todo preenche algumas informa��es como a permiss�o do
     * mesmo e sua respectiva data de cadastro que n�o s�o escolhidas no
     * formul�rio de cadastro.
     *
     * @param funcionario � o funcionario que se deseja persistir.
     * @return true caso a opera��o seja realizada com sucesso.
     * @throws RNException exce��o lan�ada caso o funcionario n�o possa ser
     * persistido.
     */
    public boolean salvar(Funcionario funcionario) throws RNException {
        Integer codigo = funcionario.getId();
        if ((codigo == null || codigo == 0) && validaNovoFuncionario(funcionario)) {
            funcionario.setNome(funcionario.getNome().toUpperCase());
            funcionario.getPermissao().add("ROLE_USUARIO");
            funcionario.setDataCadastro(new Date(System.currentTimeMillis()));
            this.funcionarioDAO.salvar(funcionario);
            return true;
        } else {
            funcionario.setLogin(buscaPorEmail(funcionario.getEmail()).getLogin());
            funcionario.setCodigoFuncionarioNaEmpresa(buscaPorEmail(funcionario.getEmail()).getCodigoFuncionarioNaEmpresa());
            funcionario.setDataCadastro(buscaPorEmail(funcionario.getEmail()).getDataCadastro());
            this.funcionarioDAO.atualizar(funcionario);
            return true;
        }
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a exclus�o de um
     * funcion�rio.
     *
     * @param funcionario � o funcion�rio a ser exclu�do.
     * @return true caso a opera��o seja realizada com sucesso.
     */
    public boolean excluir(Funcionario funcionario) {
        this.funcionarioDAO.excluir(funcionario);
        return true;
    }

    /**
     * Respons�vel por requisitar � camada de acesso aos dados a lista de todos
     * os funcion�rios persistidos.
     *
     * @return lista contendo os funcion�rios encontrados.
     */
    public List<Funcionario> listar() {
        List<Funcionario> lista = this.funcionarioDAO.listar();
        return lista;
    }

    /**
     * M�todo respons�vel por determinar se um funcion�rio pode ser persistido
     * ou n�o a partir dos identificadores �nicos que cada funcion�rio possui.
     * Caso j� exista um funcion�rio com alguma dessas informa��es, � lan�ada a
     * exce��o apropriada ao caso garantindo a unicidade das mesmas.
     *
     * @param funcionario � o funcion�rio que se deseja validar as informa��es.
     * @return true caso as informa��es do funcion�rio informado sejam �nicas 
     * @throws RNException exce��o lan�ada caso j� exista um funcion�rio com algum dos dados informados.
     */
    private boolean validaNovoFuncionario(Funcionario funcionario) throws RNException {
        if (buscaPorEmail(funcionario.getEmail()) != null) {
            throw new RNException("J� existe um funcion�rio cadastrado com este e-mail!");
        } else if (buscaPorCodigoFuncionarioNaEmpresa(funcionario.getCodigoFuncionarioNaEmpresa()) != null) {
            throw new RNException("J� existe um funcion�rio com este c�digo na empresa!");
        } else if (buscaPorLogin(funcionario.getLogin()) != null) {
            throw new RNException("Esse login n�o est� dispon�vel para cadastro!");
        }
        return true;
    }
}
