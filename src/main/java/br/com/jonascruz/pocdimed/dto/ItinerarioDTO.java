package br.com.jonascruz.pocdimed.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDTO {

    private List<CoordenadaGeograficaDTO> coordenadaGeograficaDTOList;
    private Long idLinha;
    private String nome;
    private String codigo;
}
