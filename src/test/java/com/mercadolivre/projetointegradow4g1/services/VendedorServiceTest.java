package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Vendedor;
import com.mercadolivre.projetointegradow4g1.repositories.VendedorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class VendedorServiceTest {
    @Test
    void deveCadastrarVendedor() {
        VendedorRepository mock = Mockito.mock(VendedorRepository.class);

        Vendedor vendedor = Vendedor.builder().id(1L).nome("Vendedor 1").build();

        Mockito.when(mock.save(Mockito.any(Vendedor.class))).thenReturn(vendedor);

        VendedorService vendedorService = new VendedorService(mock);
        vendedorService.salvar(vendedor);
        assertNotNull(vendedor.getId());
    }

    @Test
    void deveBuscarVendedor() {
        VendedorRepository mock = Mockito.mock(VendedorRepository.class);

        Optional<Vendedor> optionalVendedor = Optional.of(Vendedor.builder().id(1L).nome("Vendedor 1").build());

        Mockito.when(mock.findById(Mockito.anyLong())).thenReturn(optionalVendedor);

        VendedorService vendedorService = new VendedorService(mock);

        assertEquals(1L, vendedorService.buscar(1L).getId());
    }

    @Test
    void naoDeveBuscarVendedor() {
        VendedorRepository mock = Mockito.mock(VendedorRepository.class);

        Optional<Vendedor> optionalVendedor = Optional.empty();

        Mockito.when(mock.findById(Mockito.anyLong())).thenReturn(optionalVendedor);

        VendedorService vendedorService = new VendedorService(mock);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () ->
                        vendedorService.buscar(1L));
        assertTrue(exception.getMessage().contains("Vendedor não cadastrado."));
    }

    @Test
    void deveListarVendedor() {
        VendedorRepository mock = Mockito.mock(VendedorRepository.class);

        List<Vendedor> vendedorList = Arrays.asList(
                Vendedor.builder().id(1L).nome("Vendedor 1").build(),
                Vendedor.builder().id(2L).nome("Vendedor 2").build(),
                Vendedor.builder().id(3L).nome("Vendedor 3").build()
        );

        Mockito.when(mock.findAll()).thenReturn(vendedorList);

        VendedorService vendedorService = new VendedorService(mock);
        vendedorService.listar();
        assertEquals(2L, vendedorService.listar().get(1).getId());

    }
}
