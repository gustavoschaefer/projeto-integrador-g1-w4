package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface RegistroDeEstoqueRepository extends JpaRepository<RegistroDeEstoque, Long>{

    @Query(value = "SELECT TB_LOTE.ID as id, TB_LOTE.QUANTIDADE_ATUAL as quantidadeAtual, TB_LOTE.DATA_VALIDADE as dataValidade " +
            " FROM TB_ESTOQUE " +
            " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
            " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
            " WHERE LOTES_ID = :idLote ", nativeQuery = true)
    public LotesTmp buscaLote(@Param("idLote") Long id);
    public interface LotesTmp{
        String getId();
        Integer getQuantidadeAtual();
        Instant getDataValidade();
    }


//    SELECT TB_LOTE.ID, TB_LOTE.QUANTIDADE_ATUAL, *
//    FROM TB_ESTOQUE
//    JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID)
//    JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID)
//    WHERE LOTES_ID = 1

}
