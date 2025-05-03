package yatai.back.end.yatai_back_end.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import yatai.back.end.yatai_back_end.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  Page<Produto> findAll(Pageable pageable);
}