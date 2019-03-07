package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.dto.LinhaLotacaoDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaLotacao;
import br.com.jonascruz.pocdimed.repository.LinhaLotacaoRepository;
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
public class LinhaLotacaoService extends AbstractCrudService<LinhaLotacao> {

    private LinhaLotacaoRepository linhaLotacaoRepository;
    private RestTemplateConverter converter;
    private ItinerarioService itinerarioService;
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected JpaRepository<LinhaLotacao, Long> getRepository() {
        return linhaLotacaoRepository;
    }

    @Transactional
    public List<LinhaLotacao> buscaLinhas() {
        ResponseEntity<List<LinhaLotacaoDTO>> response = null;
        try{
            response = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=l",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LinhaLotacaoDTO>>(){});
        }catch (Exception e){
            e.printStackTrace();
        }
        List<LinhaLotacao> responseAuxiliar = response.getBody().stream().map(linhaLotacaoDTO ->
                linhaToObject(linhaLotacaoDTO)).collect(Collectors.toList());
        criaItinerarios(responseAuxiliar);
        return responseAuxiliar;
    }

    public void criaItinerarios(List<LinhaLotacao> listaLinhas){
        for (LinhaLotacao l : listaLinhas) {
            Long id = l.getId();
            ResponseEntity<ItinerarioDTO> responseItinerario = converter.messageConverter().exchange(
                    "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="+id,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ItinerarioDTO>() {});
            itinerarioToObject(responseItinerario);
        }
    }

    public LinhaLotacao linhaToObject(LinhaLotacaoDTO linhaLotacaoDTO){
        LinhaLotacao linhaLotacao = LinhaLotacao.builder()
                .id(linhaLotacaoDTO.getId())
                .codigo(linhaLotacaoDTO.getCodigo())
                .nome(linhaLotacaoDTO.getNome())
                .build();
        Optional<LinhaLotacao> linhaLotacaoAux = Optional.ofNullable
                (this.findById(linhaLotacaoDTO.getId())).orElse(null);
        if(linhaLotacao.equals(linhaLotacaoAux))
            System.out.print("Linha já existente e sem alteração");
        else
            return (LinhaLotacao) getRepository().save(linhaLotacao);
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
            c.setIdItinerario(auxiliar.getId());
        }
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

            listaRetorno.add(coordenadaGeograficaService.getRepository().save(coordenadaGeografica));
        }
        return listaRetorno;
    }

    public List<LinhaLotacao> findByNome(String nome){
        return linhaLotacaoRepository.findByNomeContainingIgnoreCase(nome);
    }


}
