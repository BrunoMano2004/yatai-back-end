package yatai.back.end.yatai_back_end.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import yatai.back.end.yatai_back_end.dto.CreateProdutoDTO;
import yatai.back.end.yatai_back_end.model.Produto;
import yatai.back.end.yatai_back_end.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @PostMapping
  public ResponseEntity<Produto> criarProduto(@RequestBody CreateProdutoDTO produtoDTO) {
    Produto produto = new Produto();
    produto.setNome(produtoDTO.getNome());
    produto.setDescricao(produtoDTO.getDescricao());
    produto.setPreco(produtoDTO.getPreco());
    produto.setUrlDeImagem(produtoDTO.getUrlDeImagem());

    Produto produtoSalvo = produtoService.salvar(produto);
    return ResponseEntity.ok(produtoSalvo);
  }

  @GetMapping
  public ResponseEntity<Page<Produto>> listarProdutos(Pageable pageable) {
    Page<Produto> produtos = produtoService.listarTodos(pageable);
    return ResponseEntity.ok(produtos);
  }
}