package com.mercadolivre.projetointegradow4g1.services.unit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.mercadolivre.projetointegradow4g1.entities.Comprador;
import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import com.mercadolivre.projetointegradow4g1.repositories.EstadoDestinoRepository;
import com.mercadolivre.projetointegradow4g1.services.EstadoDestinoService;


public class EstadoDeDestinoServiceTest {

    @Test
    public void deveSalvarEstadoDeDestino(){
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);

        EstadoDestino estadoDestino = EstadoDestino.builder()
                .id(1L)
                .nome("São Paulo")
                .sigla("SP")
                .comprador(Comprador.builder().build())
                .build();

        Mockito.when(mock.save(Mockito.any(EstadoDestino.class))).thenReturn(estadoDestino);

        EstadoDestinoService estadoDestinoService = new EstadoDestinoService(mock);
        estadoDestinoService.salvar(estadoDestino);
        assertNotNull(estadoDestino.getId());
    }

    @Test
    public void deveSListarEstadoDeDestino(){
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        ArrayList<EstadoDestino> lista = new ArrayList<>();

        EstadoDestino estadoDestino = EstadoDestino.builder()
                .id(1L)
                .nome("São Paulo")
                .sigla("SP")
                .comprador(Comprador.builder().build())
                .build();

        EstadoDestino estadoDestino1 = EstadoDestino.builder()
                .id(2L)
                .nome("Rio de Janeiro")
                .sigla("RJ")
                .comprador(Comprador.builder().build())
                .build();

        lista.add(estadoDestino);
        lista.add(estadoDestino1);

        Mockito.when(mock.findAll()).thenReturn(lista);

        EstadoDestinoService estadoDestinoService = new EstadoDestinoService(mock);
        estadoDestinoService.listar();

        assertEquals(2, lista.size());
        assertEquals("São Paulo", lista.get(0).getNome());
        assertEquals("Rio de Janeiro", lista.get(1).getNome());
    }

    @Test
    public void deveCalcularFreteSP() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
            new EstadoDestinoService(mock);
            assertEquals(EstadoDestinoService.calculaFrete("SP",16), BigDecimal.valueOf(480.0));
    }

    @Test
    public void deveCalcularFreteRJ() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("RJ",10), BigDecimal.valueOf(180.0));
    }

    @Test
    public void deveCalcularFreteMG() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("MG",10), BigDecimal.valueOf(190.0));
    }

    @Test
    public void deveCalcularFreteES() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("ES",10), BigDecimal.valueOf(190.0));
    }

    @Test
    public void deveCalcularFreteMT() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("MT",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteMS() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("MS",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteRS() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("RS",10), BigDecimal.valueOf(250.0));
    }

    @Test
    public void deveCalcularFretePR() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("PR",10), BigDecimal.valueOf(180.0));
    }

    @Test
    public void deveCalcularFreteSC() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("SC",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFretePB() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("PB",10), BigDecimal.valueOf(220.0));
    }

    @Test
    public void deveCalcularFreteSE() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("SE",10), BigDecimal.valueOf(200.0));
    }
    @Test
    public void deveCalcularFreteAL() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("AL",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteCE() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("CE",10), BigDecimal.valueOf(210.0));
    }

    @Test
    public void deveCalcularFretePE() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("PE",10), BigDecimal.valueOf(250.0));
    }

    @Test
    public void deveCalcularFretePI() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("PI",10), BigDecimal.valueOf(250.0));
    }

    @Test
    public void deveCalcularFretePA() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("PA",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteAM() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("AM",10), BigDecimal.valueOf(300.0));
    }

    @Test
    public void deveCalcularFreteAP() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("AP",10), BigDecimal.valueOf(300.0));
    }

    @Test
    public void deveCalcularFreteRO() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("RO",10), BigDecimal.valueOf(320.0));
    }

    @Test
    public void deveCalcularFreteRM() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("RM",10), BigDecimal.valueOf(300.0));
    }

    @Test
    public void deveCalcularFreteGO() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("GO",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteDF() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("DF",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteRN() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("RN",10), BigDecimal.valueOf(260.0));
    }

    @Test
    public void deveCalcularFreteBA() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("BA",10), BigDecimal.valueOf(200.0));
    }

    @Test
    public void deveCalcularFreteMA() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("MA",10), BigDecimal.valueOf(220.0));
    }

    @Test
    public void deveCalcularFreteAC() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("AC",10), BigDecimal.valueOf(300.0));
    }

    @Test
    public void deveCalcularFreteTO() {
        EstadoDestinoRepository mock = Mockito.mock(EstadoDestinoRepository.class);
        new EstadoDestinoService(mock);
        assertEquals(EstadoDestinoService.calculaFrete("TO",10), BigDecimal.valueOf(220.0));
    }
}
