package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.DTO.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class LinhaOnibusService extends AbstractCrudService<LinhaOnibus>{

    private LinhaOnibusRepositoy linhaOnibusRepositoy;


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

    public LinhaOnibus findByNome(String nome){
        return linhaOnibusRepositoy.findByNome(nome);
    }

}