package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.Config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.DTO.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class ApiDataPoaTest {

    @Mock
    private RestTemplateConverter restTemplateConverter;

    @Mock
    private ItinerarioService itinerarioService;

    @Mock
    private LinhaOnibusService linhaOnibusService;

    @InjectMocks
    private ApiDataPoa apiDataPoa;

    @Test
    public void findAllTest(){
//        when(restTemplateConverter.messageConverter().exchange("",
//                HttpMethod.GET,
//                null,
//                new ParameterizedTypeReference<List<LinhaOnibusDTO>>() {
//                })).thenReturn(new ResponseEntity<List< LinhaOnibusDTO >>);
        ResponseEntity<?> r = new ResponseEntity <List<LinhaOnibus>>;
        Mockito.doReturn(r).when(restTemplateConverter.messageConverter().exchange("",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<LinhaOnibusDTO>>() {
                }));
        List<LinhaOnibus> list = apiDataPoa.findAll();
        assert(list.size() > 0);
    }

}