package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.entities.CarrinhoAnuncio;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoAnuncioRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;
import com.mercadolivre.projetointegradow4g1.repositories.RegistroDeEstoqueRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoAnuncioRepository carrinhoAnuncioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, CarrinhoAnuncioRepository carrinhoAnuncioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoAnuncioRepository = carrinhoAnuncioRepository;
    }

    public Carrinho salvar(Carrinho carrinho){

        if (!CompradorService.existe(carrinho.getComprador())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comprador invalido.");
        }

        BigDecimal precoTotal = new BigDecimal(0);
        for (CarrinhoAnuncio carrinhoAnuncio : carrinho.getCarrinhoAnuncios()){
            if (!RegistroDeEstoqueService.temEstoque(carrinhoAnuncio.getAnuncio().getLote(), carrinhoAnuncio.getQuantidade())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade selecionada maior do que o disponivel para o produto: " + carrinhoAnuncio.getAnuncio().getLote().getProduto().getNome());
            }

            if (!RegistroDeEstoqueService.estaValido(carrinhoAnuncio.getAnuncio().getLote(), 21)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O produto: " + carrinhoAnuncio.getAnuncio().getLote().getProduto().getNome() + " está com data de validade menor do que três semanas.");
            }

            precoTotal = AnuncioService.buscarAnuncio(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())).add(precoTotal);
        }

        carrinho.setPrecoTotal(precoTotal);

        Carrinho carrinhoRet = this.carrinhoRepository.save(carrinho);
        Set<CarrinhoAnuncio> carrinhoAnuncios = carrinho.getCarrinhoAnuncios();
        for (CarrinhoAnuncio c: carrinhoAnuncios ) {
            c.setCarrinho(carrinhoRet);
            carrinhoAnuncioRepository.save(c);
        }
        return carrinhoRet;
    }

    public List<Carrinho> listar(){ return this.carrinhoRepository.findAll();}

    public Carrinho obter(Long id) {
        Optional<Carrinho> optional = this.carrinhoRepository.findById(id);
        return  optional.orElse(new Carrinho());
    }
}
