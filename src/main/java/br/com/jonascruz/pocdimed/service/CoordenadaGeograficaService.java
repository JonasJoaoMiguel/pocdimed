package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.repository.CoordenadaGeograficaRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CoordenadaGeograficaService extends AbstractCrudService<CoordenadaGeografica> {

    @Autowired
    private CoordenadaGeograficaRepository coordenadaGeograficaRepository;

    public CoordenadaGeograficaService coordenadaGeograficaService;

    public CoordenadaGeograficaService getInstance(){
        return coordenadaGeograficaService;
    }

    @Override
    protected JpaRepository<CoordenadaGeografica, Long> getRepository() {
        return coordenadaGeograficaRepository;
    }

    public CoordenadaGeografica toObject(CoordenadaGeograficaDTO dto){
        CoordenadaGeografica coordenadaGeografica = CoordenadaGeografica.builder()
                .lat(dto.getLat())
                .lng(dto.getLng())
                .build();
        return coordenadaGeografica;
    }
}
