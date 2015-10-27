/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.dao;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Luiz Henrique
 */
public interface VotoDAO {

    public void salvar(Voto voto);

    public void atualizar(Voto voto);

    public void excluir(Voto voto);

    public Voto carregar(Integer codigo);

    public List<Voto> listar();

    public List<Voto> listar(Funcionario funcionario);

    public List<Voto> listar(Restaurante restaurante);

    public List<Voto> listarVotosDoDia(Restaurante restaurante, Date diaEscolhido);

    public Voto buscarVoto(Funcionario funcionario, Date diaEscolhido);
}
