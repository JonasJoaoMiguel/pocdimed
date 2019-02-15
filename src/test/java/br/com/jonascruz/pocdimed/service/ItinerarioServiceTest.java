package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ItinerarioRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ItinerarioServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private LinhaOnibusRepositoy linhaOnibusRepositoy;

    @Mock
    private ItinerarioRepository itinerarioRepository;

    @Mock
    private LinhaOnibusService linhaOnibusService;

    @InjectMocks
    private ItinerarioService itinerarioService;

    @Test
    public void createItinerarioTest(){
        List<ItinerarioDTO> body = Lists.newArrayList(
                ItinerarioDTO.builder()
                        .codigo("passo")
                        .nome("B56").build(),
                ItinerarioDTO.builder()
                        .codigo("iguatemi")
                        .nome("B25").build());
        Itinerario itinerario = Itinerario.builder()
                .codigo("AA").nome("BB").build();
        when(restTemplate.exchange(anyString(),
                eq(HttpMethod.GET),
                eq(null),
                eq(new ParameterizedTypeReference<List<ItinerarioDTO>>(){})
        )).thenReturn(new ResponseEntity(body, HttpStatus.OK));
        List<LinhaOnibus> lista = new ArrayList<>();
        lista.add(LinhaOnibus
                        .builder()
                        .id(1L)
                        .nome("jjjj").build());
        when(itinerarioRepository.save(Mockito.any())).thenReturn(itinerario);
        itinerarioService.createItinerario(lista);
        Assertions.assertEquals(lista.get(0).getNome(), "jjjj");



    }

}