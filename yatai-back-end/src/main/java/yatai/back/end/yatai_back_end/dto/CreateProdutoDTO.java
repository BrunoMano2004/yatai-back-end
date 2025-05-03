package yatai.back.end.yatai_back_end.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProdutoDTO {
  private String nome;
  private String descricao;
  private Double preco;
  private String urlDeImagem;
}