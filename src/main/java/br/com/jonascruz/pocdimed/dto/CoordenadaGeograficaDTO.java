package br.com.jonascruz.pocdimed.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordenadaGeograficaDTO {

    private double lat;
    private double lng;

}
