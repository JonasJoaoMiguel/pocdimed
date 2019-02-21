package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.repository.CoordenadaGeograficaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CoordenadaGeograficaService extends AbstractCrudService<CoordenadaGeografica> {

    private CoordenadaGeograficaRepository coordenadaGeograficaRepository;

    @Override
    protected JpaRepository<CoordenadaGeografica, Long> getRepository() {
        return coordenadaGeograficaRepository;
    }


    @Transactional
    public CoordenadaGeografica toObject(CoordenadaGeograficaDTO coordenadaGeograficaDTO){
        CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                .lat(coordenadaGeograficaDTO.getLat())
                .lng(coordenadaGeograficaDTO.getLng())
                .build();
        getRepository().save(coordenadaGeografica);
        return coordenadaGeografica;
    }
}
