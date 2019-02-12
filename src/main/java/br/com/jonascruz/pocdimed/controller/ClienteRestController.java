package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Controller
@RequestMapping("/cliente")
public class ClienteRestController  {

    @Autowired
    private ClienteService clienteService;

    //@Override
    @GetMapping("/buscar")
    public List<Cliente> findAll() {
        return clienteService.findAll();
    }
}
