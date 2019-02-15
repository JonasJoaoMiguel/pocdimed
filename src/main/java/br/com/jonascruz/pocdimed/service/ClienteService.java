package br.com.jonascruz.pocdimed.service;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import br.com.jonascruz.pocdimed.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Data
@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService extends AbstractCrudService<Cliente> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    protected JpaRepository<Cliente, Long> getRepository() {
        return clienteRepository;
    }

    @Transactional
    public Cliente salvaClienteComDTO(Cliente cliente) {
//        Cliente cliente = Cliente.builder()
//                .nome(clienteDTO.getNome())
//                .cpf(clienteDTO.getCpf())
//                .build();
        return clienteRepository.save(cliente);
    }

    public void addLinhaOnibus(LinhaOnibus linha) {
        this.addLinhaOnibus(linha);
    }

}
