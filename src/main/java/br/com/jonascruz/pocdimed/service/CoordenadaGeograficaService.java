package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.CoordenadaGeograficaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class CoordenadaGeograficaService extends AbstractCrudService<CoordenadaGeografica> {

    private CoordenadaGeograficaRepository coordenadaGeograficaRepository;
    private LinhaOnibusService linhaOnibusService;
    private ItinerarioService itinerarioService;
    //private CoordenadaGeograficaService coordenadaGeograficaService;


    @Override
    protected JpaRepository<CoordenadaGeografica, Long> getRepository() {
        return coordenadaGeograficaRepository;
    }

    public List<String> filtraPorRaioEIdLinha(Long id, CoordenadaGeografica coordenadaGeografica){
        double raio = 0;
        List<String> latitudes = new ArrayList<>();
        LinhaOnibus linhaOnibus = linhaOnibusService.findById(id).orElse(null);
        Itinerario itinerario = itinerarioService.findById(linhaOnibus.getId()).orElse(null);
        List<CoordenadaGeografica> cooredenadas = (List<CoordenadaGeografica>) coordenadaGeograficaRepository.findByIdItinerario(itinerario.getIdlinha());


        return latitudes;


    }
//    public CoordenadaGeografica toObject(CoordenadaGeograficaDTO dto){
//        CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
//                .lat(dto.getLat())
//                .lng(dto.getLng())
//                .build();
//        return coordenadaGeografica;
//    }
}
