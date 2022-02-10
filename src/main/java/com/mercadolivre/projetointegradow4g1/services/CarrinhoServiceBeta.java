package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.*;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoAnuncioRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
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
        Vendedor vendedor = new Vendedor();
        for (CarrinhoAnuncio carrinhoAnuncio : carrinho.getCarrinhoAnuncios()) {
            vendedor = carrinhoAnuncio.getAnuncio().getVendedor();
            validaQtdEstoque(carrinhoAnuncio);
            confereValidade(carrinhoAnuncio);
            precoTotal = precoTotal.add(AnuncioService.buscar(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())));
            qtde += carrinhoAnuncio.getQuantidade();
            LoteService.atualizaQuantidade(carrinhoAnuncio.getAnuncio().getLote(), -carrinhoAnuncio.getQuantidade());
            atualizaVolumeSetor(carrinhoAnuncio, carrinhoAnuncio.getQuantidade());
        }

        BigDecimal valorDesconto = new BigDecimal(0);
        Map<Integer,Double> descontos = DescontoService.buscaDescontosVendedor(vendedor);
        for (Map.Entry<Integer,Double> entry : descontos.entrySet()) {
            if (qtde >= entry.getKey()) {
                valorDesconto = precoTotal.multiply(BigDecimal.valueOf(entry.getValue()));
            }
        }

        carrinho.setValorDesconto(valorDesconto);
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

    public List<Carrinho> listar(){
        return this.carrinhoRepository.findAll();
    }

    public Carrinho buscar(Long id) {
        return this.carrinhoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não registrado."));
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
        Lote lote = LoteService.buscar(carrinhoAnuncio.getAnuncio().getLote().getId());
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
