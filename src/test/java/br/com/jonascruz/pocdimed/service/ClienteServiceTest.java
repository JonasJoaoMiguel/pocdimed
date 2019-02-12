package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import br.com.jonascruz.pocdimed.stub.ClienteStub;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeAll
    public void before() {
        cliente = ClienteStub.getCliente();
    }

    @Test
    public void createClienteTest(){
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        Assert.assertEquals(clienteService.save(cliente), cliente);
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


}