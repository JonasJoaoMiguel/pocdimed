package br.com.jonascruz.pocdimed.service;


import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ApiDataPoa{

    @Autowired
    private ItinerarioService itinerarioService;

    @Lazy
    @Autowired
    private LinhaOnibusService linhaOnibusService;

    @Autowired
    private RestTemplateConverter restTemplateConverter;



    public List<LinhaOnibus> findAll() {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
        ResponseEntity<List<LinhaOnibusDTO>> response = restTemplateConverter.messageConverter().exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusDTO>>() {
                });

        return response.getBody().stream().map(linhaOnibusDTO ->
                linhaOnibusService.toObject(linhaOnibusDTO)).collect(Collectors.toList());
    }

    List <LinhaOnibus> listaLinhaOnibus = null;
//    @PostConstruct
//    public void postConstruct(){
//        listaLinhaOnibus = findAll();
//    }

    public void createItinerario() {
        for(LinhaOnibus l : listaLinhaOnibus) {
            Long id = l.getId();
            String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + id;
            ResponseEntity<List<ItinerarioDTO>> response = restTemplateConverter.messageConverter().exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ItinerarioDTO>>() {
                });
            response.getBody().stream().map(itinerarioDTO ->
                itinerarioService.toObject(itinerarioDTO)).collect(Collectors.toList());
        }
    }
}
