package br.com.jonascruz.pocdimed.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClienteDTO {

    private String nome;
    private Long cpf;
}
