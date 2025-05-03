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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import yatai.back.end.yatai_back_end.dto.CreateProdutoDTO;
import yatai.back.end.yatai_back_end.model.Produto;
import yatai.back.end.yatai_back_end.service.ProdutoService;

@Tag(name = "Produtos", description = "Operações relacionadas a produtos")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @Operation(summary = "Criar um novo produto", description = "Adiciona um novo produto ao sistema.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Produto criado com sucesso"),
      @ApiResponse(responseCode = "400", description = "Dados inválidos")
  })
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

  @Operation(summary = "Listar produtos", description = "Retorna uma lista paginada de produtos.")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Lista de produtos retornada com sucesso")
  })
  @GetMapping
  public ResponseEntity<Page<Produto>> listarProdutos(Pageable pageable) {
    Page<Produto> produtos = produtoService.listarTodos(pageable);
    return ResponseEntity.ok(produtos);
  }
}