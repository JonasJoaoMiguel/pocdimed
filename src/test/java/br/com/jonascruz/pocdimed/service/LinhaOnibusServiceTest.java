package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.PocDimedApplicationTests;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.repository.LinhaOnibusRepositoy;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LinhaOnibusServiceTest extends PocDimedApplicationTests {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LinhaOnibusService linhaOnibusService;

    @Mock
    private LinhaOnibusRepositoy linhaOnibusRepositoy;


    @Test
    public void findAllTest(){
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
        Assertions.assertTrue(linhaOnibusService.findAll().size() > 0);
        verify(linhaOnibusRepositoy).findAll();
    }
}
