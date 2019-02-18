package br.com.jonascruz.pocdimed.dto;


import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDTO {

    private CoordenadaGeograficaService coordenadaGeograficaService;

    private Long idLinha;
    private String nome;
    private String codigo;
    private LinhaOnibus linhaOnibus;
}
