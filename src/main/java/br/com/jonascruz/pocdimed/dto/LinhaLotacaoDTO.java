package br.com.jonascruz.pocdimed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LinhaLotacaoDTO {

    private Long id;
    private String codigo;
    private String nome;

}
