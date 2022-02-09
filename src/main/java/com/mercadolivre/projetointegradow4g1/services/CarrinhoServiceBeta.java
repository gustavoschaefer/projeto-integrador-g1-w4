package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.*;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoAnuncioRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

public class CarrinhoServiceBeta {
    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoAnuncioRepository carrinhoAnuncioRepository;


    public CarrinhoServiceBeta(CarrinhoRepository carrinhoRepository, CarrinhoAnuncioRepository carrinhoAnuncioRepository, CarrinhoService carrinhoService) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoAnuncioRepository = carrinhoAnuncioRepository;

    }

    public Carrinho salvar(Carrinho carrinho) {

        //validações
        validaComprador(carrinho);


        BigDecimal precoTotal = new BigDecimal(0);
        Integer qtde = 0;
        for (CarrinhoAnuncio carrinhoAnuncio : carrinho.getCarrinhoAnuncios()){
            validaQtdEstoque(carrinhoAnuncio);
            confereValidade(carrinhoAnuncio);
            precoTotal = precoTotal.add(AnuncioService.buscarAnuncio(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())));
            qtde += carrinhoAnuncio.getQuantidade();
            LoteService.atualizaQuantidade(carrinhoAnuncio.getAnuncio().getLote(), -carrinhoAnuncio.getQuantidade());
            atualizaVolumeSetor(carrinhoAnuncio, carrinhoAnuncio.getQuantidade());
        }

        carrinho.setPrecoTotal(precoTotal);
        carrinho.setValorFrete(EstadoDestinoService.calculaFrete(carrinho.getComprador().getEstadoDestino().getSigla(), qtde));
        Carrinho carrinhoRet = this.carrinhoRepository.save(carrinho);
        List<CarrinhoAnuncio> carrinhoAnuncios = carrinho.getCarrinhoAnuncios();
        for (CarrinhoAnuncio c: carrinhoAnuncios ) {
            c.setCarrinho(carrinhoRet);
            carrinhoAnuncioRepository.save(c);
        }
        return carrinhoRet;
    }

    private void validaComprador(Carrinho carrinho) {
        if (!CompradorService.existe(carrinho.getComprador())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comprador invalido.");
        }
    }

    private void validaQtdEstoque(CarrinhoAnuncio carrinhoAnuncio) {
        if (!RegistroDeEstoqueService.temEstoque(carrinhoAnuncio.getAnuncio().getLote(), carrinhoAnuncio.getQuantidade())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade selecionada maior do que o disponivel para o produto: " + carrinhoAnuncio.getAnuncio().getLote().getProduto().getNome());
        }
    }

    private void confereValidade(CarrinhoAnuncio carrinhoAnuncio) {
        if (!RegistroDeEstoqueService.estaValido(carrinhoAnuncio.getAnuncio().getLote(), 21)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto: " + carrinhoAnuncio.getAnuncio().getLote().getProduto().getNome() + " está com data de validade menor do que três semanas.");
        }
    }

    private void atualizaVolumeSetor(CarrinhoAnuncio carrinhoAnuncio, int quantidade) {
        Setor setor = new Setor();
        Lote lote = LoteService.obter(carrinhoAnuncio.getAnuncio().getLote().getId());
        for (RegistroDeEstoque registroDeEstoque : lote.getRegistroDeEstoques()) {
            for (Lote l : registroDeEstoque.getLotes()) {
                if (l.equals(carrinhoAnuncio.getAnuncio().getLote())) {
                    setor = registroDeEstoque.getSetor();
                }
            }
        }
        SetorService.atualizaCapacidade(setor, -(quantidade * lote.getProduto().getVolumeUni()));
    }
}
