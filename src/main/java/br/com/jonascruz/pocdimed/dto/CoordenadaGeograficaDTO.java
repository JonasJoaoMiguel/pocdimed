package br.com.jonascruz.pocdimed.dto;


import br.com.jonascruz.pocdimed.entity.Itinerario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordenadaGeograficaDTO {

    //private Long idItinerario;
    private double lat;
    private double lng;
    private Itinerario itinerario;

}
