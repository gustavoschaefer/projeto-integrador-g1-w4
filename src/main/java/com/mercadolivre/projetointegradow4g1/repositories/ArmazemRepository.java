package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.dto.ArmazemProdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercadolivre.projetointegradow4g1.entities.Armazem;

import java.time.Instant;
import java.util.List;

@Repository
public interface ArmazemRepository extends JpaRepository<Armazem, Long> {

    @Query(value = " SELECT SUM (TB_LOTE.QUANTIDADE_ATUAL) AS quantidade, TB_SETOR.ARMAZEM_ID AS armazem, TB_PRODUTO.NOME AS nome,\n" +
            " FROM TB_ESTOQUE  " +
            " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
            " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
            " JOIN (TB_PRODUTO) ON (TB_PRODUTO.ID = TB_LOTE.PRODUTO_ID) " +
            " JOIN (TB_SETOR) ON (TB_SETOR.ID = TB_ESTOQUE.SETOR_ID) " +
            " WHERE PRODUTO_ID = :idProduto " +
            " GROUP BY nome ", nativeQuery = true)
    public List<ArmazemTmp> buscaProdutoArmazem(@Param("idProduto") Long id);

    public interface ArmazemTmp{
        Integer getArmazem();
        Integer getQuantidade();
        String getNome();

    }

}
