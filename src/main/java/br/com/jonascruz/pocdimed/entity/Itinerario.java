package br.com.jonascruz.pocdimed.entity;


import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Builder
@AllArgsConstructor
public class Itinerario {

    @Autowired
    private CoordenadaGeograficaService coordenadaGeograficaService;


    private String idlinha;
    private String codigo;
    private String nome;
    private List<CoordenadaGeografica> coordenadaGeograficaList;

}
