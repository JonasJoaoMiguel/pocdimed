package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class ItinerarioService extends AbstractCrudService<Itinerario>{

    private LinhaOnibusService linhaOnibusService;
    private ItinerarioRepository itinerarioRepository;
    private RestTemplateConverter converter;
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected JpaRepository getRepository() {
        return itinerarioRepository;
    }

    @Transactional
    public List<Itinerario> criaItinerarios(){
        List<Itinerario> listaRetorno = new ArrayList<>();
        List<LinhaOnibus> listaLinhas = linhaOnibusService.findAll();
        int i = listaLinhas.size();
        for (LinhaOnibus l : listaLinhas) {
            i--;
            Long id = l.getId();
            ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ItinerarioDTO>() {});
            Itinerario auxiliar = itinerarioToObject(responseItinerario);
            listaRetorno.add(auxiliar);
        }
        return listaRetorno;
    }

    public Itinerario itinerarioToObject(ResponseEntity<ItinerarioDTO> itinerarioDTO) {
        Itinerario itinerario = Itinerario.builder()
                .idlinha(itinerarioDTO.getBody().getIdlinha())
                .codigo(itinerarioDTO.getBody().getCodigo())
                .nome(itinerarioDTO.getBody().getNome())
                .coordenadaGeograficaList(coordenadaToObject(itinerarioDTO.getBody().getItinerario()))
                .build();
        Itinerario auxiliar = (Itinerario) getRepository().save(itinerario);
        for(CoordenadaGeografica c : auxiliar.getCoordenadaGeograficaList()){
            c.setIdItinerario(auxiliar.getId());
        }
        return auxiliar;
    }

    public List<CoordenadaGeografica> coordenadaToObject(Map<String, CoordenadaGeograficaDTO> map){
        List<CoordenadaGeografica> listaRetorno = new ArrayList<>();
        Set<String> keys = map.keySet();
        for(String s : keys){
            Long idAux = Long.valueOf(s);
            CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                    .id(idAux)
                    .lat(map.get(s).getLat())
                    .lng(map.get(s).getLng())
                    .build();
            listaRetorno.add(coordenadaGeograficaService.save(coordenadaGeografica));
        }
        return listaRetorno;
    }

    @Transactional
    public Itinerario toObject(ItinerarioDTO itinerarioDTO) {
        Itinerario itinerario = Itinerario.builder()
                .idlinha(itinerarioDTO.getIdlinha())
                .codigo(itinerarioDTO.getCodigo())
                .idlinha(itinerarioDTO.getIdlinha())
                .build();
        getRepository().save(itinerario);
        return itinerario;
    }

    public Itinerario buscaItinerarioPorIdLinha(Long id){
        return itinerarioRepository.findByIdlinha(id);
    }
}
