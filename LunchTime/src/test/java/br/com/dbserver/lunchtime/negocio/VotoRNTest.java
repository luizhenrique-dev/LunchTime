/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.entidade.Funcionario;
import br.com.dbserver.lunchtime.entidade.Restaurante;
import br.com.dbserver.lunchtime.entidade.Voto;
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
public class VotoRNTest {

    private static VotoRN mockedVotoRN;
    private static Voto voto1;
    private static Voto voto2;
    private static Funcionario funcionarioA;
    private static Funcionario funcionarioB;
    private static Restaurante restauranteA;
    private static final Date dataParaTeste = new Date(System.currentTimeMillis());

    public VotoRNTest() {
    }

    @BeforeClass
    public static void setUpClass() throws RNException {
        //Criação do Mock da classe VotoRN
        mockedVotoRN = mock(VotoRN.class);

        //Criação de dois funcionário
        funcionarioA = new Funcionario();
        funcionarioA.setId(1);
        funcionarioA.setNome("João da Silva");
        funcionarioB = new Funcionario();
        funcionarioB.setId(2);
        funcionarioB.setNome("Ciclano de Souza");

        //Criação de um restaurante
        restauranteA = new Restaurante();
        restauranteA.setId(1);
        restauranteA.setNome("Restaurante Kanpai Express");

        //Criação de algumas instâncias de Voto
        voto1 = new Voto();
        voto1.setId(1);
        voto1.setDataVoto(dataParaTeste);
        voto1.setFuncionario(funcionarioA);
        voto1.setRestaurante(restauranteA);

        voto2 = new Voto();
        voto2.setId(2);
        voto2.setDataVoto(dataParaTeste);
        voto2.setFuncionario(funcionarioB);
        voto2.setRestaurante(restauranteA);

        //Fazendo Stubbing dos métodos do VotoRN mockado, com os dados mockados.
        when(mockedVotoRN.listar()).thenReturn(Arrays.asList(voto1, voto2));
        when(mockedVotoRN.listarVotosDoDia(restauranteA, dataParaTeste)).thenReturn(Arrays.asList(voto1, voto2));
        when(mockedVotoRN.listarVotosFuncionario(funcionarioA)).thenReturn(Arrays.asList(voto1));
        when(mockedVotoRN.listarVotosRestaurante(restauranteA)).thenReturn(Arrays.asList(voto1, voto2));
        when(mockedVotoRN.carregar(2)).thenReturn(voto2);
        when(mockedVotoRN.votoDisponivelDia(funcionarioA, dataParaTeste)).thenReturn(Boolean.FALSE);
        when(mockedVotoRN.excluir(voto1)).thenReturn(Boolean.TRUE);
        try {
            when(mockedVotoRN.salvar(voto1)).thenReturn(Boolean.TRUE);
        } catch (RNException ex) {
            Mockito.doThrow(new RNException(ex.getMessage())).when(mockedVotoRN.salvar(voto1));
        }
        System.out.println("TESTES CLASSE VotoRN:");
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
     * Test of salvar method, of class VotoRN.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        boolean salvo = mockedVotoRN.salvar(voto1);
        assertEquals(true, salvo);
    }

    /**
     * Test of excluir method, of class VotoRN.
     */
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        boolean excluido = mockedVotoRN.excluir(voto1);
        assertEquals(true, excluido);
    }

    /**
     * Test of carregar method, of class VotoRN.
     */
    @Test
    public void testCarregar() {
        System.out.println("carregar");
        Voto voto = mockedVotoRN.carregar(2);
        assertEquals(voto2.getId(), voto.getId());
    }

    /**
     * Test of listarVotosRestaurante method, of class VotoRN.
     */
    @Test
    public void testListarVotosRestaurante() {
        System.out.println("listarVotosRestaurante");
        List<Voto> votosRestaurante = mockedVotoRN.listarVotosRestaurante(restauranteA);
        assertEquals(2, votosRestaurante.size());
    }

    /**
     * Test of listarVotosFuncionario method, of class VotoRN.
     */
    @Test
    public void testListarVotosFuncionario() {
        System.out.println("listarVotosFuncionario");
        List<Voto> votosFuncionario = mockedVotoRN.listarVotosFuncionario(funcionarioA);
        assertEquals(1, votosFuncionario.size());
    }

    /**
     * Test of listar method, of class VotoRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        List<Voto> votos = mockedVotoRN.listar();
        assertEquals(2, votos.size());
    }

    /**
     * Test of listarVotosDoDia method, of class VotoRN.
     */
    @Test
    public void testListarVotosDoDia() {
        System.out.println("listarVotosDoDia");
        List<Voto> votosRestauranteNoDia = mockedVotoRN.listarVotosDoDia(restauranteA, dataParaTeste);
        assertEquals(2, votosRestauranteNoDia.size());
    }

    /**
     * Test of votoDisponivelDia method, of class VotoRN.
     */
    @Test
    public void testVotoDisponivelDia() throws Exception {
        System.out.println("votoDisponivelDia");
        boolean podeVotar = mockedVotoRN.votoDisponivelDia(funcionarioA, dataParaTeste);
        assertEquals(false, podeVotar);
    }

}
