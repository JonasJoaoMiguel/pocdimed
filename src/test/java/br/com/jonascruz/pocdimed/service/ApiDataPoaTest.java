package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.PocDimedApplicationTests;
import br.com.jonascruz.pocdimed.config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiDataPoaTest extends PocDimedApplicationTests {

    @MockBean
    private RestTemplateConverter restTemplateConverter;

    @Autowired
    private ApiDataPoa apiDataPoa;


    @Test
    public void findAllTest(){
        GenericHttpMessageConverter converter = mock(GenericHttpMessageConverter.class);
        ParameterizedTypeReference<List<LinhaOnibusDTO>> param = new ParameterizedTypeReference<List<LinhaOnibusDTO>>(){};
        given(converter.canRead(param.getType(), null, null)).willReturn(true);
        given(converter.getSupportedMediaTypes()).willReturn(Collections.singletonList(MediaType.TEXT_PLAIN));
        RestTemplate template = mock(RestTemplate.class);
        //doReturn(template).when(restTemplateConverter.messageConverter());
        ResponseEntity<List<LinhaOnibusDTO>> resp = template.exchange("http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o",
                HttpMethod.GET,
                null,
                param);
        when(resp).thenReturn(new ResponseEntity<List<LinhaOnibusDTO>>(HttpStatus.OK));
        List<LinhaOnibus> list = apiDataPoa.findAll();
        assert(list.size() > 0);
    }


}