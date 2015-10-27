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
 * Classe responsável por conter a regra de negócio da entidade Funcionario.
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
     * Responsável por requisitar à camada de acesso a recuperação de um
     * funcionario.
     *
     * @param id é o identificador único do funcionário.
     * @return funcionário com suas respectivas informações caso ele exista.
     */
    public Funcionario carregar(Integer id) {
        return this.funcionarioDAO.carregar(id);
    }

    /**
     * Verifica se já existe algum funcionário persistido que possui o código
     * informado.
     *
     * @param codigo identificador único do funcionário na empresa.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscaPorCodigoFuncionarioNaEmpresa(String codigo) {
        return this.funcionarioDAO.buscarPorCodigoFuncionarioNaEmpresa(codigo);
    }

    /**
     * Verifica se já existe algum funcionário persistido que possui o login
     * informado.
     *
     * @param login identificador único do funcionário no sistema, sendo uma das
     * credenciais de acesso do mesmo ao sistema.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscaPorLogin(String login) {
        return this.funcionarioDAO.buscarPorLogin(login);
    }

    /**
     * Verifica se já existe algum funcionário persistido que possui o email
     * informado.
     *
     * @param email email do funcionário que deve ser único.
     * @return Funcionário com suas informações caso ele exista.
     */
    public Funcionario buscaPorEmail(String email) {
        return this.funcionarioDAO.buscarPorEmail(email);
    }

    public String getEmailFuncionario(Funcionario funcionario) {
        return this.funcionarioDAO.carregar(funcionario.getId()).getEmail();
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a persistência de
     * um funcionário. O método preenche algumas informações como a permissão do
     * mesmo e sua respectiva data de cadastro que não são escolhidas no
     * formulário de cadastro.
     *
     * @param funcionario é o funcionario que se deseja persistir.
     * @return true caso a operação seja realizada com sucesso.
     * @throws RNException exceção lançada caso o funcionario não possa ser
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
     * Responsável por requisitar à camada de acesso aos dados a exclusão de um
     * funcionário.
     *
     * @param funcionario é o funcionário a ser excluído.
     * @return true caso a operação seja realizada com sucesso.
     */
    public boolean excluir(Funcionario funcionario) {
        this.funcionarioDAO.excluir(funcionario);
        return true;
    }

    /**
     * Responsável por requisitar à camada de acesso aos dados a lista de todos
     * os funcionários persistidos.
     *
     * @return lista contendo os funcionários encontrados.
     */
    public List<Funcionario> listar() {
        List<Funcionario> lista = this.funcionarioDAO.listar();
        return lista;
    }

    /**
     * Método responsável por determinar se um funcionário pode ser persistido
     * ou não a partir dos identificadores únicos que cada funcionário possui.
     * Caso já exista um funcionário com alguma dessas informações, é lançada a
     * exceção apropriada ao caso garantindo a unicidade das mesmas.
     *
     * @param funcionario é o funcionário que se deseja validar as informações.
     * @return true caso as informações do funcionário informado sejam únicas 
     * @throws RNException exceção lançada caso já exista um funcionário com algum dos dados informados.
     */
    private boolean validaNovoFuncionario(Funcionario funcionario) throws RNException {
        if (buscaPorEmail(funcionario.getEmail()) != null) {
            throw new RNException("Já existe um funcionário cadastrado com este e-mail!");
        } else if (buscaPorCodigoFuncionarioNaEmpresa(funcionario.getCodigoFuncionarioNaEmpresa()) != null) {
            throw new RNException("Já existe um funcionário com este código na empresa!");
        } else if (buscaPorLogin(funcionario.getLogin()) != null) {
            throw new RNException("Esse login não está disponível para cadastro!");
        }
        return true;
    }
}
