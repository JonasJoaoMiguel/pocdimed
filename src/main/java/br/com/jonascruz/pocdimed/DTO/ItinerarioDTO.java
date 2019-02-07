package br.com.jonascruz.pocdimed.DTO;


import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Data
public class ItinerarioDTO {

    @Autowired
    public CoordenadaGeograficaService coordenadaGeograficaService;

    private String idlinha;
    private String nome;
    private String codigo;
    private List<CoordenadaGeograficaDTO> coordenadaGeograficaListDTO;

    public List<CoordenadaGeografica> findAll(){
        return coordenadaGeograficaService.findAll();
    }

}
