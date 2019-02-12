package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService //extends AbstractCrudService<Cliente>
{

    private ClienteRepository clienteRepository;

    //@Override
    //protected JpaRepository<Cliente, Long> getRepository() {
    //    return clienteRepository;
    //}

    //public void addLinhaOnibus(LinhaOnibus linha){
    //    this.addLinhaOnibus(linha);
    //}

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

}
