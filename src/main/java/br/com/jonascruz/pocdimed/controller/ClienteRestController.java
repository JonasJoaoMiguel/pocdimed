package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.dto.ClienteDTO;
import br.com.jonascruz.pocdimed.entity.Cliente;
import br.com.jonascruz.pocdimed.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clientes")
@AllArgsConstructor
public class ClienteRestController {


    private ClienteService clienteService;

    protected ClienteService getService() {
        return clienteService;
    }

    @PostMapping("/cliente")
    public ResponseEntity<?> salvaCliente(@RequestBody ClienteDTO clienteDTO){
        return ResponseEntity.ok(clienteService.salvaClienteComDTO(clienteDTO));
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity<?> update(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @GetMapping("/cliente")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @DeleteMapping("/cliente/{id}")
    public void excluir(@PathVariable("id") Long id) {
        clienteService.delete(id);
    }
}
