package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ItinerarioService extends AbstractCrudService<Itinerario>{

    private ItinerarioRepository itinerarioRepository;
    private CoordenadaGeograficaService coordenadaGeograficaService;

    @Override
    protected JpaRepository getRepository() {
        return itinerarioRepository;
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
    }
