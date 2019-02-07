package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.DTO.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LinhaOnibusService extends AbstractCrudService<LinhaOnibus>{

    @Autowired
    private LinhaOnibusRepositoy linhaOnibusRepositoy;

    public LinhaOnibusService linhaOnibusService;

    public LinhaOnibusService getInstance(){
        return linhaOnibusService;
    }

    @Override
    protected JpaRepository getRepository() {
        return linhaOnibusRepositoy;
    }

    public LinhaOnibus toObject(LinhaOnibusDTO linhaOnibusDTO){
        LinhaOnibus linhaOnibus = LinhaOnibus.builder()
                .id(linhaOnibusDTO.getId())
                .codigo(linhaOnibusDTO.getCodigo())
                .nome(linhaOnibusDTO.getNome())
                .build();
        return linhaOnibus;

    }

}