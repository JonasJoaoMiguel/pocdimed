package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LinhaOnibusService extends AbstractCrudService<LinhaOnibus>{

    private LinhaOnibusRepositoy linhaOnibusRepositoy;

    private RestTemplate restTemplate;

    private ItinerarioService itinerarioService;

    @Override
    protected JpaRepository getRepository() {
        return linhaOnibusRepositoy;
    }

    public LinhaOnibus toObject(LinhaOnibusDTO linhaOnibusDTO){
        LinhaOnibus linhaOnibus = LinhaOnibus.builder()
                .id(linhaOnibusDTO.getId())
                .codigo(linhaOnibusDTO.getCodigo())
                .nome(linhaOnibusDTO.getNome())
                .build();
        return linhaOnibus;

    }

    public List<LinhaOnibus> findAll() {
        ResponseEntity<List<LinhaOnibusDTO>> response = restTemplate.exchange(
                "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LinhaOnibusDTO>>(){});
        return response.getBody().stream().map(linhaOnibusDTO ->
                toObject(linhaOnibusDTO)).collect(Collectors.toList());
    }

    List <LinhaOnibus> listaLinhaOnibus = getRepository().findAll();

    private void createItinerario(){
        itinerarioService.createItinerario(listaLinhaOnibus);
    }

    public LinhaOnibus findByNome(String nome){
        return linhaOnibusRepositoy.findByNome(nome);
    }

}