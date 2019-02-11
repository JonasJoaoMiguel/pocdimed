package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService extends AbstractCrudService<Cliente> {

    private ClienteRepository clienteRepository;

    @Override
    protected JpaRepository<Cliente, Long> getRepository() {
        return clienteRepository;
    }

    public void addLinhaOnibus(LinhaOnibus linha){
        this.addLinhaOnibus(linha);
    }
}
