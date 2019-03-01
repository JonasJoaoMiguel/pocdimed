package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class LinhaOnibusService extends AbstractCrudService<LinhaOnibus>{

    private LinhaOnibusRepositoy linhaOnibusRepositoy;
    private ItinerarioRepository itinerarioRepository;
    private RestTemplateConverter converter;
    private ItinerarioService itinerarioService;
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected JpaRepository getRepository() {
        return linhaOnibusRepositoy;
    }

    @Transactional
    public List<LinhaOnibus> buscaLinhas() {
        ResponseEntity<List<LinhaOnibusDTO>> response = null;
        try{
            response = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LinhaOnibusDTO>>(){});
        }catch (Exception e){
            e.printStackTrace();
        }
        List<LinhaOnibus> responseAuxiliar = response.getBody().stream().map(linhaOnibusDTO ->
                linhaToObject(linhaOnibusDTO)).collect(Collectors.toList());
        criaItinerarios(responseAuxiliar);
        return responseAuxiliar;
    }

    public void criaItinerarios(List<LinhaOnibus> listaLinhas){
        int i = listaLinhas.size();
        for (LinhaOnibus l : listaLinhas) {
            i--;
            Long id = l.getId();
            ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ItinerarioDTO>() {});
            itinerarioToObject(responseItinerario);
        }
    }

    public LinhaOnibus linhaToObject(LinhaOnibusDTO linhaOnibusDTO){
        LinhaOnibus linhaOnibus = LinhaOnibus.builder()
                .id(linhaOnibusDTO.getId())
                .codigo(linhaOnibusDTO.getCodigo())
                .nome(linhaOnibusDTO.getNome())
                .build();
        Optional<LinhaOnibus> linhaOnibusAux = Optional.ofNullable
                (this.findById(linhaOnibusDTO.getId())).orElse(null);
        if(linhaOnibus.equals(linhaOnibusAux))
            System.out.print("Linha já existente e sem alteração");
        else
            return (LinhaOnibus) getRepository().save(linhaOnibus);
        return null;
    }

    public void itinerarioToObject(ResponseEntity<ItinerarioDTO> itinerarioDTO) {
        Itinerario itinerario = Itinerario.builder()
                .idlinha(itinerarioDTO.getBody().getIdlinha())
                .codigo(itinerarioDTO.getBody().getCodigo())
                .nome(itinerarioDTO.getBody().getNome())
                .coordenadaGeograficaList(coordenadaToObject(itinerarioDTO.getBody().getItinerario()))
                .build();
        Itinerario auxiliar = (Itinerario) itinerarioService.getRepository().save(itinerario);
        for(CoordenadaGeografica c : auxiliar.getCoordenadaGeograficaList()){
            c.setIdItinerario(auxiliar);
        }
    }

    public List<CoordenadaGeografica> coordenadaToObject(Map <String, CoordenadaGeograficaDTO> map){
        List<CoordenadaGeografica> listaRetorno = new ArrayList<>();
        Set<String> keys = map.keySet();
        for(String s : keys){
            Long idAux = Long.valueOf(s);
            CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                    .id(idAux)
                    .lat(map.get(s).getLat())
                    .lng(map.get(s).getLng())
                    .build();

            listaRetorno.add(coordenadaGeograficaService.getRepository().save(coordenadaGeografica));
        }
        return listaRetorno;
    }

    public List<LinhaOnibus> findByNome(String nome){
        return linhaOnibusRepositoy.findByNomeContainingIgnoreCase(nome);
    }

}