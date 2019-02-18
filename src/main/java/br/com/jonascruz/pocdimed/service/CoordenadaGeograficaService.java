package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.repository.CoordenadaGeograficaRepository;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CoordenadaGeograficaService extends AbstractCrudService<CoordenadaGeografica> {

    private CoordenadaGeograficaRepository coordenadaGeograficaRepository;
    private ItinerarioRepository itinerarioRepository;
    private LinhaOnibusService linhaOnibusService;
    private ItinerarioService itinerarioService;
    //private CoordenadaGeograficaService coordenadaGeograficaService;


    @Override
    protected JpaRepository<CoordenadaGeografica, Long> getRepository() {
        return coordenadaGeograficaRepository;
    }

//    public List<String> filtraPorRaioEIdLinha(Long id, CoordenadaGeografica coordenadaGeografica){
//        double raio = 0;
//        List<String> latitudes = new ArrayList<>();
//        LinhaOnibus linhaOnibus = linhaOnibusService.findById(id).orElse(null);
//        Itinerario itinerario = itinerarioService.findById(linhaOnibus.getId()).orElse(null);
//        List<CoordenadaGeografica> cooredenadas = (List<CoordenadaGeografica>) coordenadaGeograficaRepository.findByIdItinerario(itinerario.getIdlinha());
//
//
//        return latitudes;
//
//
//    }
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public CoordenadaGeografica toObject(CoordenadaGeograficaDTO dto){
        Optional<Itinerario> itinerarioAux = itinerarioService.findById(dto.getItinerario().getLinhaOnibus().getId());
        CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                .lat(dto.getLat())
                .lng(dto.getLng())
                .itinerario(itinerarioAux.orElse(null))
                .build();
        getRepository().save(coordenadaGeografica);
        return coordenadaGeografica;
    }
}
