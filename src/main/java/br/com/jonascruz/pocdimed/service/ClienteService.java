package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
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
