package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LinhaOnibusServiceTest  {

    private LinhaOnibusDTO linhaOnibusDTO;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LinhaOnibusService linhaOnibusService;

    @Mock
    private LinhaOnibusRepositoy linhaOnibusRepository;

    @Mock
    private ItinerarioService itinerarioService;


    @Test
    public void buscaLinhasTest(){
        List<LinhaOnibusDTO> body = Lists.newArrayList(
                LinhaOnibusDTO.builder()
                        .id(1L)
                        .codigo("123")
                        .nome("B56").build(),
                LinhaOnibusDTO.builder()
                        .id(2L)
                        .codigo("321")
                        .nome("B55").build());
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<LinhaOnibusDTO>>(){})
        )).thenReturn(new ResponseEntity(body, HttpStatus.OK));

        LinhaOnibus linhaOnibus = LinhaOnibus.builder()
                .id(1L).codigo("123").nome("B56").build();
        when(linhaOnibusRepository.save(Mockito.any())).thenReturn(linhaOnibus);
        Assertions.assertTrue(linhaOnibusService.buscaLinhas().size() > 0);
        Mockito.verify(linhaOnibusRepository).save(linhaOnibus);
    }


}

