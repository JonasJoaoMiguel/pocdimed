package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ItinerarioService extends AbstractCrudService<Itinerario>{

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Override
    protected JpaRepository getRepository() {
        return itinerarioRepository;
    }

    public Itinerario toObject(ItinerarioDTO itinerarioDTO) {
        Itinerario itinerario = Itinerario.builder()
                .idlinha(new Long(itinerarioDTO.getIdlinha()))
                .codigo(itinerarioDTO.getCodigo())
                .nome(itinerarioDTO.getNome())
                .coordenadaGeografica(itinerarioDTO.getCoordenadaGeografica())
                .build();
        return itinerario;
        }
    }
