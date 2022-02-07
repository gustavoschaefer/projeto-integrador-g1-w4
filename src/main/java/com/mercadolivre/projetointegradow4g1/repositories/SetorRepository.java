package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.entities.enums.CondicaoConservacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mercadolivre.projetointegradow4g1.entities.Setor;


import java.time.Instant;
import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long> {
        @Query(value = " SELECT TB_SETOR.TIPO AS setor, TB_PRODUTO.ID AS produto, TB_LOTE.ID AS lote, TB_LOTE.QUANTIDADE_ATUAL AS quantidade, TB_LOTE.DATA_VALIDADE AS validade,\n" +
                " FROM TB_ESTOQUE " +
                " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
                " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
                " JOIN (TB_PRODUTO) ON (TB_PRODUTO.ID = TB_LOTE.PRODUTO_ID) " +
                " JOIN (TB_SETOR) ON (TB_SETOR.ID = TB_ESTOQUE.SETOR_ID) " +
                " WHERE TB_SETOR.ID = :idSetor and TB_LOTE.DATA_VALIDADE <= :data " +
                " ORDER BY validade ", nativeQuery = true)

        public List<SetorTmp> buscaLostesPorSetor(@Param("idSetor") Long id, @Param("data") Instant data);

        public interface SetorTmp{
            CondicaoConservacao getSetor();
            Integer getProduto();
            Integer getLote();
            Integer getQuantidade();
            Instant getValidade();
        }
}
