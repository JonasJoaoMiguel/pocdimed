package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import br.com.jonascruz.pocdimed.stub.ClienteStub;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente;

    @BeforeEach
    void before() {
        cliente = ClienteStub.getCliente();
    }

    @Test
    public void createClienteTest(){
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        Assertions.assertEquals(clienteService.save(cliente), cliente);
        verify(clienteRepository).save(cliente);
    }

    @Test
    public void updateClienteTest(){
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        cliente.setNome("joao");
        Assertions.assertEquals(clienteService.save(cliente).getNome(), "joao");
    }

    @Test
    public void findAllClienteTest(){
        when(clienteRepository.save(Mockito.any())).thenReturn(cliente);
        List<Cliente> clientes = Lists.newArrayList(Cliente.builder().nome("Miguel").build(),
                Cliente.builder().nome("Joao").build());
        for(Cliente cliente1 : clientes){
            when(clienteRepository.save(Mockito.any())).thenReturn(cliente1);
        }
        when(clienteRepository.findAll()).thenReturn(clientes);
        Assertions.assertEquals(clienteService.findAll(), clientes);
    }

    @Test
    public void findByIdClienteTest(){
        when(clienteRepository.findById(Mockito.any())).thenReturn(Optional.of(cliente));
        Assertions.assertEquals(clienteService.findById(cliente.getId()), Optional.of(cliente));
        verify(clienteRepository).findById(cliente.getId());
    }


}