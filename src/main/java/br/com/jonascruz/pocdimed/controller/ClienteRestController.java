package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;

public class ClienteRestController extends AbstractCrudRestController<Cliente, ClienteService> {

    @Autowired
    private ClienteService clienteService;

    @Override
    protected ClienteService getService() {
        return clienteService;
    }
}
