package yatai.back.end.yatai_back_end.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import yatai.back.end.yatai_back_end.model.Produto;
import yatai.back.end.yatai_back_end.repository.ProdutoRepository;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  @Transactional
  public Produto salvar(Produto produto) {
    if (produto.getId() != null) {
      // Se for uma atualização, carrega o produto existente primeiro
      Produto produtoExistente = produtoRepository.findById(produto.getId())
          .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

      // Atualiza apenas os campos necessários
      produtoExistente.setNome(produto.getNome());
      produtoExistente.setDescricao(produto.getDescricao());
      produtoExistente.setPreco(produto.getPreco());
      produtoExistente.setUrlDeImagem(produto.getUrlDeImagem());

      return produtoRepository.save(produtoExistente);
    }
    return produtoRepository.save(produto);
  }

  public Page<Produto> listarTodos(Pageable pageable) {
    return produtoRepository.findAll(pageable);
  }
}