package br.com.jonascruz.pocdimed.dto;


import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ItinerarioDTO {

    private CoordenadaGeograficaService coordenadaGeograficaService;

    private Long idlinha;
    private String nome;
    private String codigo;
    private CoordenadaGeografica coordenadaGeografica;

    public List<CoordenadaGeografica> findAll(){
        return coordenadaGeograficaService.findAll();
    }

}
