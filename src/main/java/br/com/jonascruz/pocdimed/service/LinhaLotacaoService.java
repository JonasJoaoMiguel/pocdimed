package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.LinhaLotacaoDTO;
import br.com.jonascruz.pocdimed.entity.LinhaLotacao;
import br.com.jonascruz.pocdimed.repository.LinhaLotacaoRepository;
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
public class LinhaLotacaoService extends AbstractCrudService<LinhaLotacao> {

    private LinhaLotacaoRepository linhaLotacaoRepository;
    private RestTemplateConverter converter;

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
        return responseAuxiliar;
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

    public List<LinhaLotacao> findByNome(String nome){
        return linhaLotacaoRepository.findByNomeContainingIgnoreCase(nome);
    }


}
