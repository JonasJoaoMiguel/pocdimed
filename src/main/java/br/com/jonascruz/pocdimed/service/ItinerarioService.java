package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaLotacao;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@AllArgsConstructor
@Service
public class ItinerarioService extends AbstractCrudService<Itinerario>{

    private LinhaOnibusService linhaOnibusService;
    private LinhaLotacaoService linhaLotacaoService;
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
        List<LinhaOnibus> listaLinhasOnibus = linhaOnibusService.findAll();
        List<LinhaLotacao> listaLinhasLotacao = linhaLotacaoService.findAll();
        try {
            for (LinhaOnibus l : listaLinhasOnibus) {
                Long id = l.getId();
                Optional<Itinerario> comp = getRepository().findById(id);
                ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                        "http://www.poatransporte.com.br/php/facades/process.php?a=il&p=" + id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ItinerarioDTO>() {
                        });
                Itinerario auxiliar = itinerarioToObject(responseItinerario,comp);
                listaRetorno.add(auxiliar);
            }
            for (LinhaLotacao l : listaLinhasLotacao) {
                Long id = l.getId();
                Optional<Itinerario> comp = getRepository().findById(id);
                ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                        "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+id,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ItinerarioDTO>() {});
                Itinerario auxiliar = itinerarioToObject(responseItinerario, comp);
                listaRetorno.add(auxiliar);
            }

        }catch (Exception e){
        e.printStackTrace();
    }
        return listaRetorno;
    }

    public Itinerario itinerarioToObject(
            ResponseEntity<ItinerarioDTO> itinerarioDTO, Optional<Itinerario> comp) {
        Itinerario itinerario = Itinerario.builder()
                .idlinha(itinerarioDTO.getBody().getIdlinha())
                .codigo(itinerarioDTO.getBody().getCodigo())
                .nome(itinerarioDTO.getBody().getNome())
                .coordenadaGeograficaList(coordenadaToObject(itinerarioDTO.getBody().getItinerario()))
                .build();
        if(itinerario.equals(comp)) {
            System.out.print("Itinerario já cadastrado");
            return null;
        }
        Itinerario auxiliar = (Itinerario) getRepository().save(itinerario);
        for(CoordenadaGeografica c : auxiliar.getCoordenadaGeograficaList())
            c.setIditinerario(auxiliar.getIdlinha());
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
