/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.util.RNException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Luiz Henrique
 */
public class FuncionarioRNTest {

    private static FuncionarioRN mockedFuncionarioRN;
    private static Funcionario funcionarioA;
    private static Funcionario funcionarioB;

    public FuncionarioRNTest() {
    }

    @BeforeClass
    public static void setUpClass() throws RNException {
        //Criação do Mock da classe FuncionarioRN
        mockedFuncionarioRN = mock(FuncionarioRN.class);

        //Criação de algumas instâncias de Funcionario
        funcionarioA = new Funcionario();
        funcionarioA.setId(1);
        funcionarioA.setNome("João da Silva");
        funcionarioA.setAtivo(true);
        funcionarioA.setEmail("testea@email.com");
        funcionarioA.setLogin("joaoteste");
        funcionarioA.setCodigoFuncionarioNaEmpresa("123456");
        funcionarioA.setDataCadastro(new Date(System.currentTimeMillis()));

        funcionarioB = new Funcionario();
        funcionarioB.setId(2);
        funcionarioB.setNome("Ciclano de Souza");
        funcionarioB.setAtivo(true);
        funcionarioB.setEmail("testeb@email.com");
        funcionarioB.setLogin("ciclanoteste");
        funcionarioB.setCodigoFuncionarioNaEmpresa("654321");
        funcionarioB.setDataCadastro(new Date(System.currentTimeMillis()));

        //Fazendo Stubbing dos métodos do FuncionarioRN mockado, com os dados mockados.
        when(mockedFuncionarioRN.listar()).thenReturn(Arrays.asList(funcionarioA, funcionarioB));
        when(mockedFuncionarioRN.buscaPorCodigoFuncionarioNaEmpresa("123456")).thenReturn(funcionarioA);
        when(mockedFuncionarioRN.buscaPorEmail("testeb@email.com")).thenReturn(funcionarioB);
        when(mockedFuncionarioRN.buscaPorLogin("joaoteste")).thenReturn(funcionarioA);
        when(mockedFuncionarioRN.carregar(2)).thenReturn(funcionarioB);
        when(mockedFuncionarioRN.excluir(funcionarioA)).thenReturn(Boolean.TRUE);
        when(mockedFuncionarioRN.getEmailFuncionario(funcionarioB)).thenReturn("testeb@email.com");
        try {
            when(mockedFuncionarioRN.salvar(funcionarioA)).thenReturn(Boolean.TRUE);
        } catch (RNException ex) {
            Mockito.doThrow(new RNException(ex.getMessage())).when(mockedFuncionarioRN.salvar(funcionarioA));
        }
        System.out.println("TESTES CLASSE FuncionarioRN:");
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of carregar method, of class FuncionarioRN.
     */
    @Test
    public void testCarregar() {
        System.out.println("carregar");
        Funcionario funcionario = mockedFuncionarioRN.carregar(2);
        assertEquals(funcionarioB.getId(), funcionario.getId());
    }

    /**
     * Test of buscaPorCodigoFuncionarioNaEmpresa method, of class
     * FuncionarioRN.
     */
    @Test
    public void testBuscaPorCodigoFuncionarioNaEmpresa() {
        System.out.println("buscaPorCodigoFuncionarioNaEmpresa");
        Funcionario funcionario = mockedFuncionarioRN.buscaPorCodigoFuncionarioNaEmpresa("123456");
        assertNotNull(funcionario.getId());
        assertEquals(funcionarioA.getId(), funcionario.getId());
    }

    /**
     * Test of buscaPorLogin method, of class FuncionarioRN.
     */
    @Test
    public void testBuscaPorLogin() {
        System.out.println("buscaPorLogin");
        Funcionario funcionario = mockedFuncionarioRN.buscaPorLogin("joaoteste");
        assertNotNull(funcionario.getId());
        assertEquals(funcionarioA.getId(), funcionario.getId());
    }

    /**
     * Test of buscaPorEmail method, of class FuncionarioRN.
     */
    @Test
    public void testBuscaPorEmail() {
        System.out.println("buscaPorEmail");
        Funcionario funcionario = mockedFuncionarioRN.buscaPorEmail("testeb@email.com");
        assertNotNull(funcionario.getId());
        assertEquals(funcionarioB.getId(), funcionario.getId());
    }

    /**
     * Test of getEmailFuncionario method, of class FuncionarioRN.
     */
    @Test
    public void testGetEmailFuncionario() {
        System.out.println("getEmailFuncionario");
        String email = mockedFuncionarioRN.getEmailFuncionario(funcionarioB);
        assertEquals(funcionarioB.getEmail(), email);
    }

    /**
     * Test of salvar method, of class FuncionarioRN.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        boolean salvo = mockedFuncionarioRN.salvar(funcionarioA);
        assertEquals(true, salvo);
    }

    /**
     * Test of excluir method, of class FuncionarioRN.
     */
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        boolean excluido = mockedFuncionarioRN.excluir(funcionarioA);
        assertEquals(true, excluido);
    }

    /**
     * Test of listar method, of class FuncionarioRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        List<Funcionario> funcionarios = mockedFuncionarioRN.listar();
        assertEquals(2, funcionarios.size());
    }

}
