/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.dbserver.lunchtime.negocio;

import br.com.dbserver.lunchtime.entidade.Restaurante;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Luiz Henrique
 */
public class RestauranteRNTest {

    private static RestauranteRN mockedRestauranteRN;
    private static Restaurante restauranteA;
    private static Restaurante restauranteB;
    private static final Date dataParaTeste = new Date(System.currentTimeMillis());

    public RestauranteRNTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        //Criação do Mock da classe RestauranteRN
        mockedRestauranteRN = mock(RestauranteRN.class);

        //Criação de algumas instâncias de Restaurante
        restauranteA = new Restaurante();
        restauranteA.setId(1);
        restauranteA.setNome("Restaurante Kanpai Express");
        restauranteA.setPreco(32);
        restauranteA.setTipoAlmoco("Por kilo");
        restauranteA.setTipoComida("Japonesa");

        restauranteB = new Restaurante();
        restauranteB.setId(2);
        restauranteB.setNome("Churrascaria Gramado");
        restauranteB.setPreco(38);
        restauranteB.setTipoAlmoco("Por pessoa");
        restauranteB.setTipoComida("Churrasco");
        
        //Fazendo Stubbing dos métodos do RestauranteRN mockado, com os dados mockados.
        when(mockedRestauranteRN.listar(dataParaTeste)).thenReturn(Arrays.asList(restauranteA, restauranteB));
        when(mockedRestauranteRN.carregar(2)).thenReturn(restauranteB);
        when(mockedRestauranteRN.excluir(restauranteA)).thenReturn(Boolean.TRUE);
        when(mockedRestauranteRN.salvar(restauranteA, dataParaTeste, dataParaTeste, dataParaTeste)).thenReturn(Boolean.TRUE);
        
        System.out.println("TESTES CLASSE RestauranteRN:");
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
     * Test of carregar method, of class RestauranteRN.
     */
    @Test
    public void testCarregar() {
        System.out.println("carregar");
        Restaurante restaurante = mockedRestauranteRN.carregar(2);
        assertEquals(restauranteB.getId(), restaurante.getId());
    }

    /**
     * Test of salvar method, of class RestauranteRN.
     */
    @Test
    public void testSalvar() {
        System.out.println("salvar");
        boolean salvo = mockedRestauranteRN.salvar(restauranteA, dataParaTeste, dataParaTeste, dataParaTeste);
        assertEquals(true, salvo);
    }

    /**
     * Test of excluir method, of class RestauranteRN.
     */
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        boolean excluido = mockedRestauranteRN.excluir(restauranteA);
        assertEquals(true, excluido);
    }

    /**
     * Test of listar method, of class RestauranteRN.
     */
    @Test
    public void testListar() {
        System.out.println("listar");
        List<Restaurante> restaurantes = mockedRestauranteRN.listar(dataParaTeste);
        assertEquals(2, restaurantes.size());
    }

}
