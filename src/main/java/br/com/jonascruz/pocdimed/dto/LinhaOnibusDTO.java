package br.com.jonascruz.pocdimed.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LinhaOnibusDTO {

    private Long id;
    private String codigo;
    private String nome;
}
