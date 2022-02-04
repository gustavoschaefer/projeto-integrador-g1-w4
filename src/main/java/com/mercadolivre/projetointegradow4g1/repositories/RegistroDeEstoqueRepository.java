package com.mercadolivre.projetointegradow4g1.repositories;

import com.mercadolivre.projetointegradow4g1.entities.Lote;
import com.mercadolivre.projetointegradow4g1.entities.Produto;
import com.mercadolivre.projetointegradow4g1.entities.RegistroDeEstoque;
import com.mercadolivre.projetointegradow4g1.entities.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Repository
public interface RegistroDeEstoqueRepository extends JpaRepository<RegistroDeEstoque, Long>{

    @Query(value = " SELECT TB_SETOR.ARMAZEM_ID AS armazem, TB_ESTOQUE.SETOR_ID AS setor, TB_PRODUTO.ID AS produto, TB_LOTE.ID AS lote, TB_LOTE.QUANTIDADE_ATUAL AS quantidade, TB_LOTE.DATA_VALIDADE AS validade " +
            " FROM TB_ESTOQUE " +
            " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
            " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
            " JOIN (TB_PRODUTO) ON (TB_PRODUTO.ID = TB_LOTE.PRODUTO_ID) " +
            "JOIN (TB_SETOR) ON (TB_SETOR.ID = TB_ESTOQUE.SETOR_ID) " +
            " WHERE PRODUTO_ID = :idProduto " +
            " ORDER BY validade", nativeQuery = true)
    public List<ProdutoTmp> buscaProdutoVal(@Param("idProduto") Long id);

    @Query(value = " SELECT TB_SETOR.ARMAZEM_ID AS armazem, TB_ESTOQUE.SETOR_ID AS setor, TB_PRODUTO.ID AS produto, TB_LOTE.ID AS lote, TB_LOTE.QUANTIDADE_ATUAL AS quantidade, TB_LOTE.DATA_VALIDADE AS validade " +
            " FROM TB_ESTOQUE " +
            " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
            " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
            " JOIN (TB_PRODUTO) ON (TB_PRODUTO.ID = TB_LOTE.PRODUTO_ID) " +
            "JOIN (TB_SETOR) ON (TB_SETOR.ID = TB_ESTOQUE.SETOR_ID) " +
            " WHERE PRODUTO_ID = :idProduto " +
            " ORDER BY quantidade", nativeQuery = true)
    public List<ProdutoTmp> buscaProdutoQuantidade(@Param("idProduto") Long id);

    @Query(value = " SELECT TB_SETOR.ARMAZEM_ID AS armazem, TB_ESTOQUE.SETOR_ID AS setor, TB_PRODUTO.ID AS produto, TB_LOTE.ID AS lote, TB_LOTE.QUANTIDADE_ATUAL AS quantidade, TB_LOTE.DATA_VALIDADE AS validade " +
            " FROM TB_ESTOQUE " +
            " JOIN (TB_ESTOQUE_LOTES) ON (TB_ESTOQUE.ID = TB_ESTOQUE_LOTES.REGISTRO_DE_ESTOQUES_ID) " +
            " JOIN (TB_LOTE) ON (TB_LOTE.ID = TB_ESTOQUE_LOTES.LOTES_ID) " +
            " JOIN (TB_PRODUTO) ON (TB_PRODUTO.ID = TB_LOTE.PRODUTO_ID) " +
            "JOIN (TB_SETOR) ON (TB_SETOR.ID = TB_ESTOQUE.SETOR_ID) " +
            " WHERE PRODUTO_ID = :idProduto " +
            " ORDER BY lote", nativeQuery = true)
    public List<ProdutoTmp> buscaProdutoLote(@Param("idProduto") Long id);

    public interface ProdutoTmp{
        Integer getArmazem();
        Integer getSetor();
        Integer getProduto();
        Integer getLote();
        Integer getQuantidade();
        Instant getValidade();
    }



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
