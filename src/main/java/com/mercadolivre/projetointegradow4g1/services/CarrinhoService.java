package com.mercadolivre.projetointegradow4g1.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.entities.CarrinhoAnuncio;
import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoAnuncioRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;

@Service
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoAnuncioRepository carrinhoAnuncioRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository, CarrinhoAnuncioRepository carrinhoAnuncioRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.carrinhoAnuncioRepository = carrinhoAnuncioRepository;
    }

    public Carrinho salvar(Carrinho carrinho) {

        //validações
        validaComprador(carrinho);
        BigDecimal precoTotal = new BigDecimal(0);
        for (CarrinhoAnuncio carrinhoAnuncio : carrinho.getCarrinhoAnuncios()){
            validaQtdEstoque(carrinhoAnuncio);
            confereValidade(carrinhoAnuncio);
            precoTotal = precoTotal.add(AnuncioService.buscarAnuncio(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())));
            LoteService.atualizaQuantidade(carrinhoAnuncio.getAnuncio().getLote(), -carrinhoAnuncio.getQuantidade());
            atualizaVolumeSetor(carrinhoAnuncio, carrinhoAnuncio.getQuantidade());
        }

        carrinho.setPrecoTotal(precoTotal);

        Carrinho carrinhoRet = this.carrinhoRepository.save(carrinho);
        List<CarrinhoAnuncio> carrinhoAnuncios = carrinho.getCarrinhoAnuncios();
        for (CarrinhoAnuncio c: carrinhoAnuncios ) {
            c.setCarrinho(carrinhoRet);
            carrinhoAnuncioRepository.save(c);
        }
        return carrinhoRet;
    }

    public List<Carrinho> listar(){ return this.carrinhoRepository.findAll();}

    public Carrinho buscar(Long id) {
        return this.carrinhoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não registrado."));
    }

    public Carrinho alterar(Long id, Carrinho carrinho) {
        Carrinho carrinhoRet = carrinhoRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Carrinho não registrado."));

        BigDecimal precoTotal = new BigDecimal(0);
        List<CarrinhoAnuncio> carrinhoAnuncios = carrinho.getCarrinhoAnuncios();
        for (CarrinhoAnuncio carrinhoAnuncioRet : carrinhoRet.getCarrinhoAnuncios()) {
            if (carrinhoAnuncios.contains(carrinhoAnuncioRet)) { //Ja existia
                CarrinhoAnuncio carrinhoAnuncio = carrinhoAnuncios.stream().filter(c -> c.getId() == carrinhoAnuncioRet.getId()).findFirst().get();
                if (carrinhoAnuncioRet.getQuantidade() != carrinhoAnuncio.getQuantidade()) { //Mudou quantidade
                    int quantidade = carrinhoAnuncioRet.getQuantidade() - carrinhoAnuncio.getQuantidade();
                    LoteService.atualizaQuantidade(carrinhoAnuncio.getAnuncio().getLote(), quantidade);
                    atualizaVolumeSetor(carrinhoAnuncio, -quantidade);

                    CarrinhoAnuncio carrinhoAnuncioAtualiza = carrinhoAnuncioRepository.getById(carrinhoAnuncio.getId());
                    carrinhoAnuncioAtualiza.setQuantidade(carrinhoAnuncio.getQuantidade());
                }
                precoTotal = precoTotal.add(AnuncioService.buscarAnuncio(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())));
            } else { //Remove anuncio excluído
                int quantidade = carrinhoAnuncioRet.getQuantidade();
                LoteService.atualizaQuantidade(carrinhoAnuncioRet.getAnuncio().getLote(), quantidade);
                atualizaVolumeSetor(carrinhoAnuncioRet, -quantidade);
                carrinhoAnuncioRepository.deleteById(carrinhoAnuncioRet.getId());
            }
        }
        for (CarrinhoAnuncio carrinhoAnuncio : carrinho.getCarrinhoAnuncios()) {
            if (!carrinhoRet.getCarrinhoAnuncios().contains(carrinhoAnuncio)) { //Adiciona novo anuncio
                int quantidade = carrinhoAnuncio.getQuantidade();
                LoteService.atualizaQuantidade(carrinhoAnuncio.getAnuncio().getLote(), -quantidade);
                atualizaVolumeSetor(carrinhoAnuncio, quantidade);
                precoTotal = precoTotal.add(AnuncioService.buscarAnuncio(carrinhoAnuncio.getAnuncio().getId()).getPreco().multiply(new BigDecimal(carrinhoAnuncio.getQuantidade())));
                carrinhoAnuncio.setCarrinho(carrinhoRet);
                carrinhoAnuncioRepository.save(carrinhoAnuncio);
            }
        }



        carrinhoRet.setCarrinhoAnuncios(carrinho.getCarrinhoAnuncios());
        carrinhoRet.setComprador(carrinho.getComprador());
        carrinhoRet.setPrecoTotal(precoTotal);
        return carrinhoRepository.save(carrinhoRet);
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
