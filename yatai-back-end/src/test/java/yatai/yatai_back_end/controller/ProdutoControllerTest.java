package yatai.yatai_back_end.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import yatai.back.end.yatai_back_end.controller.ProdutoController;
import yatai.back.end.yatai_back_end.dto.CreateProdutoDTO;
import yatai.back.end.yatai_back_end.model.Produto;
import yatai.back.end.yatai_back_end.service.ProdutoService;

public class ProdutoControllerTest {

  @Mock
  private ProdutoService produtoService;

  @InjectMocks
  private ProdutoController produtoController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCriarProduto() {
    CreateProdutoDTO dto = new CreateProdutoDTO("Produto Teste", "Descrição", 10.0, "url");
    Produto produtoSalvo = new Produto(null, null, "Produto Teste", "Descrição", 10.0, "url");
    when(produtoService.salvar(any(Produto.class))).thenReturn(produtoSalvo);

    ResponseEntity<Produto> response = produtoController.criarProduto(dto);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getNome()).isEqualTo("Produto Teste");
    assertThat(response.getBody().getDescricao()).isEqualTo("Descrição");
    assertThat(response.getBody().getPreco()).isEqualTo(10.0);
    assertThat(response.getBody().getUrlDeImagem()).isEqualTo("url");
  }

  @Test
  void testListarProdutos() {
    Produto produto1 = new Produto(1L, 0L, "Produto 1", "Desc 1", 10.0, "url1");
    Produto produto2 = new Produto(2L, 0L, "Produto 2", "Desc 2", 20.0, "url2");
    Pageable pageable = PageRequest.of(0, 2);
    Page<Produto> page = new PageImpl<>(Arrays.asList(produto1, produto2), pageable, 2);
    when(produtoService.listarTodos(pageable)).thenReturn(page);

    ResponseEntity<Page<Produto>> response = produtoController.listarProdutos(pageable);

    assertThat(response.getStatusCodeValue()).isEqualTo(200);
    assertThat(response.getBody().getContent().size()).isEqualTo(2);
    assertThat(response.getBody().getContent().get(0).getNome()).isEqualTo("Produto 1");
    assertThat(response.getBody().getContent().get(1).getNome()).isEqualTo("Produto 2");
  }
}