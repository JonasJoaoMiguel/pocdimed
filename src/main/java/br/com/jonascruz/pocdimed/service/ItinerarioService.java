package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ItinerarioService extends AbstractCrudService<Itinerario>{

    private ItinerarioRepository itinerarioRepository;
    private LinhaOnibusService linhaOnibusService;
    private RestTemplate restTemplate;

    @Override
    protected JpaRepository getRepository() {
        return itinerarioRepository;
    }

    public void createItinerario(List<LinhaOnibus> listaLinhaOnibus) {
        for (LinhaOnibus l : listaLinhaOnibus) {
            Long id = l.getId();
            ResponseEntity<List<ItinerarioDTO>> response = restTemplate.exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=".concat(id.toString()),
                    HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<ItinerarioDTO>>() {});
            response.getBody().stream().map(itinerarioDTO ->
                    toObject(itinerarioDTO)).collect(Collectors.toList());
        }

    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Itinerario toObject(ItinerarioDTO itinerarioDTO) {
        Optional<LinhaOnibus> linhaOnibusAux = linhaOnibusService.findById(itinerarioDTO.getIdLinha());
        Itinerario itinerario = Itinerario.builder()
                .idlinha(itinerarioDTO.getIdLinha())
                .codigo(itinerarioDTO.getCodigo())
                .nome(itinerarioDTO.getNome())
                .linhaOnibus(linhaOnibusAux.orElse(null))
                .build();
        getRepository().save(itinerario);
        return itinerario;
        }
    }
