package com.mercadolivre.projetointegradow4g1.services;

import com.mercadolivre.projetointegradow4g1.entities.Carrinho;
import com.mercadolivre.projetointegradow4g1.entities.CarrinhoAnuncio;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoAnuncioRepository;
import com.mercadolivre.projetointegradow4g1.repositories.CarrinhoRepository;
import org.springframework.stereotype.Service;

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
        Carrinho carrinhoRet = this.carrinhoRepository.save(carrinho);
        Set<CarrinhoAnuncio> carrinhoAnuncios = carrinho.getCarrinhoAnuncios();
        for (CarrinhoAnuncio c: carrinhoAnuncios ) {
            c.setCarrinho(carrinhoRet);
            carrinhoAnuncioRepository.save(c);
        }
        return carrinhoRet;
    }

    public List<Carrinho> listar(){ return this.carrinhoRepository.findAll();}

    public Carrinho obter(Long id){
        Optional<Carrinho> optional = this.carrinhoRepository.findById(id);
        return  optional.orElse(new Carrinho());
    }
}
