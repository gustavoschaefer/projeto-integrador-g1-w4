package com.mercadolivre.projetointegradow4g1.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mercadolivre.projetointegradow4g1.entities.EstadoDestino;
import com.mercadolivre.projetointegradow4g1.repositories.EstadoDestinoRepository;

@Service
public class EstadoDestinoService {

    private static EstadoDestinoRepository estadoDestinoRepository;

    public EstadoDestinoService(EstadoDestinoRepository estadoDestinoRepository){
        EstadoDestinoService.estadoDestinoRepository = estadoDestinoRepository;
    }

    public EstadoDestino salvar(EstadoDestino estadoDestino){
        return estadoDestinoRepository.save(estadoDestino);
    }

    public List<EstadoDestino> listar() {
        return estadoDestinoRepository.findAll();
    }

    public static BigDecimal calculaFrete(String sigla, Integer quantidade){
        BigDecimal valorFrete = BigDecimal.valueOf(0);
        int qtde = quantidade;

        if (qtde > 15){qtde *= 2;}

        switch (sigla) {
            case "SP":
                valorFrete = BigDecimal.valueOf(qtde * 15.00);
                break;
            case "RJ":
                valorFrete = BigDecimal.valueOf(qtde * 18.00);
                break;
            case "MG":
                valorFrete = BigDecimal.valueOf(qtde * 19.00);
                break;
            case "ES":
                valorFrete = BigDecimal.valueOf(qtde * 19.00);
                break;
            case "MT":
                valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "MS":
                valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "RS":
                valorFrete = BigDecimal.valueOf(qtde * 25.00);
                break;
            case "PR":
                valorFrete = BigDecimal.valueOf(qtde * 18.00);
                break;
            case "SC":
                 valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "PB":
                 valorFrete = BigDecimal.valueOf(qtde * 22.00);
                break;
            case "SE":
                 valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "AL":
                valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "CE":
                valorFrete = BigDecimal.valueOf(qtde * 21.00);
                break;
            case "PE":
                 valorFrete = BigDecimal.valueOf(qtde * 25.00);
                break;
            case "PI":
                 valorFrete = BigDecimal.valueOf(qtde * 25.00);
                break;
            case "PA":
                 valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "AM":
                 valorFrete = BigDecimal.valueOf(qtde * 30.00);
                break;
            case "AP":
                 valorFrete = BigDecimal.valueOf(qtde * 30.00);
                break;
            case "RO":
                 valorFrete = BigDecimal.valueOf(qtde * 32.00);
                break;
            case "RM":
                 valorFrete = BigDecimal.valueOf(qtde * 30.00);
                break;
            case "GO":
                 valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "DF":
                valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "RN":
                 valorFrete = BigDecimal.valueOf(qtde * 26.00);
                break;
            case "BA":
                 valorFrete = BigDecimal.valueOf(qtde * 20.00);
                break;
            case "MA":
                 valorFrete = BigDecimal.valueOf(qtde * 22.00);
                break;
            case "AC":
                valorFrete = BigDecimal.valueOf(qtde * 30.00);
                break;
            case "TO":
                 valorFrete = BigDecimal.valueOf(qtde * 22.00);
                break;
            default:
                valorFrete = BigDecimal.valueOf(0);

        }

        return valorFrete;
    }
}
