package br.com.jonascruz.pocdimed.service;


import br.com.jonascruz.pocdimed.Config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.DTO.ItinerarioDTO;
import br.com.jonascruz.pocdimed.DTO.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class ApiDataPoa{

    @Autowired
    private ItinerarioService itinerarioService;

    @Autowired
    private LinhaOnibusService linhaOnibusService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RestTemplateConverter restTemplateConverter;

    List <LinhaOnibus> listaOnibus = findAll();

    public List<LinhaOnibus> findAll() {
        String url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";
        ResponseEntity<List<LinhaOnibusDTO>> response = restTemplateConverter.messageConverter().exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<LinhaOnibusDTO>>() {
                });

        return response.getBody().stream().map(linhaOnibusDTO ->
                linhaOnibusService.toObject(linhaOnibusDTO)).collect(Collectors.toList());
    }

    public void createItinerario() {
        for(LinhaOnibus l : listaOnibus) {
            String id = l.getId();
            String url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + id;
            ResponseEntity<List<ItinerarioDTO>> response = restTemplateConverter.messageConverter().exchange(
                url, HttpMethod.GET, null, new ParameterizedTypeReference<List<ItinerarioDTO>>() {
                });
            response.getBody().stream().map(itinerarioDTO ->
                itinerarioService.toObject(itinerarioDTO)).collect(Collectors.toList());
        }
    }
}
