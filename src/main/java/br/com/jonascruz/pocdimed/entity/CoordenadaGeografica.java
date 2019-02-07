package br.com.jonascruz.pocdimed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CoordenadaGeografica {

    private String lat;
    private String lng;
}
