package br.com.jonascruz.pocdimed.dto;


import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ItinerarioDTO {

    @JsonIgnore
    private Map<String, CoordenadaGeograficaDTO> mapItinerario = new HashMap<>();
    @JsonProperty("idlinha")
    private Long idlinha;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("codigo")
    private String codigo;

    @JsonAnyGetter
    public Map<String, CoordenadaGeograficaDTO> getItinerario() {
        return this.mapItinerario;
    }

    @JsonAnySetter
    public void setItinerario(String name, CoordenadaGeograficaDTO value) {
        mapItinerario.put(name, value);
    }
}
