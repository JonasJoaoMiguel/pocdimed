package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import lombok.AllArgsConstructor;
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

    private LinhaOnibusRepositoy linhaOnibusRepository;
    private RestTemplateConverter converter;

    @Override
    protected JpaRepository getRepository() {
        return linhaOnibusRepository;
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
        return response.getBody().stream().map(linhaOnibusDTO ->
                linhaToObject(linhaOnibusDTO)).collect(Collectors.toList());

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

    public List<LinhaOnibus> findByNome(String nome){
        return linhaOnibusRepository.findByNomeContainingIgnoreCase(nome);
    }

}