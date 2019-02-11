package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.Config.RestTemplateConverter;
import br.com.jonascruz.pocdimed.DTO.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.PocDimedApplicationTests;
import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import org.junit.Assert;
import org.junit.Before;
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
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApiDataPoaTest extends PocDimedApplicationTests {

    @MockBean
    private RestTemplateConverter restTemplateConverter;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ApiDataPoa apiDataPoa;

    @Before
    public void before() {
        clienteRepository.deleteAll();
    }

    @Test
    public void createClienteTest(){
        Cliente cliente = Cliente.builder()
                .id(1l)
                .nome("jonas")
                .cpf(000000000)
                .build();
        Cliente c = clienteRepository.save(cliente);
        Assert.assertEquals(c.getCpf(), 000000000);
        Assert.assertEquals(c.getNome(), "jonas");
    }

    @Test
    public void updateClienteTest(){
        Cliente cliente = Cliente.builder()
                .id(1l)
                .nome("jonas")
                .cpf(000000000)
                .build();
        Cliente c = clienteRepository.save(cliente);
        c.setNome("joao");
        clienteRepository.save(c);
        Assert.assertEquals(c.getNome(), "joao");
    }

    @Test
    public void findAllClienteTest(){
        for(int i = 1; i <= 3; i++){
            Cliente cliente = Cliente.builder()
                    .nome("jonas"+i)
                    .cpf(00000000)
                    .build();
            Cliente c = clienteRepository.save(cliente);
        }
        List<Cliente> list = clienteRepository.findAll();
        Assert.assertEquals(list.size(), 3);
    }

    @Test
    public void findByIdClienteTest(){
        Cliente cliente = Cliente.builder()
                .nome("jonas")
                .cpf(00000000)
                .build();
        Cliente c = clienteRepository.save(cliente);
        Optional<Cliente> d = clienteRepository.findById(c.getId());
        Assert.assertEquals(c, d.get());
        Assert.assertEquals(c.getId(), d.get().getId());
    }

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