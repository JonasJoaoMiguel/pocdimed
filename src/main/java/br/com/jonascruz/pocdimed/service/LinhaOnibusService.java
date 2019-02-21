package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@AllArgsConstructor
@Service
public class LinhaOnibusService extends AbstractCrudService<LinhaOnibus>{

    private LinhaOnibusRepositoy linhaOnibusRepositoy;
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

        for (LinhaOnibus l : listaLinhas) {
            Long id = l.getId();
            ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ItinerarioDTO>() {});
            responseItinerario.getBody().stream().map(itinerarioDTO ->
                    itinerarioToObject(itinerarioDTO)).collect(Collectors.toList());
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

    public Itinerario itinerarioToObject(ItinerarioDTO itinerarioDTO) {
//        Optional<LinhaOnibus> linhaOnibusAux = Optional.ofNullable
//                (getRepository().findById(itinerarioDTO.getIdLinha())).orElse(null);
        Itinerario itinerario = Itinerario.builder()
//                .idlinha(itinerarioDTO.getIdLinha())
                .coordenadaGeograficaList(coordenadaToObject(itinerarioDTO.getCoordenadaGeograficaDTOList()))
                .build();
        itinerarioService.getRepository().save(itinerario);
        return itinerario;
    }

    public List<CoordenadaGeografica> coordenadaToObject(List<CoordenadaGeograficaDTO> coordenadaGeograficaDTOList){
        List<CoordenadaGeografica> listaRetorno = null;
        for (CoordenadaGeograficaDTO c : coordenadaGeograficaDTOList) {
            CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                    .lat(c.getLat())
                    .lng(c.getLng())
                    .build();
            coordenadaGeograficaService.getRepository().save(coordenadaGeografica);
            listaRetorno.add(coordenadaGeografica);
        }
        return listaRetorno;

    }

    public LinhaOnibus findByNome(String nome){
        return linhaOnibusRepositoy.findByNome(nome);
    }

}