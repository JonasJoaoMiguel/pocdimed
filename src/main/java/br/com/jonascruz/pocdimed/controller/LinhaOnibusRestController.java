package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.dto.LinhaOnibusDTO;
import br.com.jonascruz.pocdimed.service.LinhaOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/linhasonibus")
public class LinhaOnibusRestController {

    @Autowired
    private LinhaOnibusService linhaOnibusService;

    protected LinhaOnibusService getService() {
        return linhaOnibusService;
    }

    @PostMapping("/linhaonibus")
    public ResponseEntity<?> salvaOnibus(){
        return ResponseEntity.ok(linhaOnibusService.buscaLinhas());
    }

    @PutMapping("/linhaonibus/{id}")
    public ResponseEntity<?> update(@RequestBody LinhaOnibusDTO linhaOnibusDTO){
        return ResponseEntity.ok(linhaOnibusService.linhaToObject(linhaOnibusDTO));
    }

    @GetMapping("/linhaonibus")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(linhaOnibusService.findAll());
    }

    @GetMapping("/linhaonibus/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(linhaOnibusService.findById(id));
    }

    @DeleteMapping("/linhaonibus/{id}")
    public void excluir(@PathVariable("id") Long id) {
        linhaOnibusService.delete(id);
    }

    @GetMapping("/linhaonibus/filter")
    public ResponseEntity<?> findByNome(@PathParam("nome") String nome) {
        return ResponseEntity.ok(linhaOnibusService.findByNome(nome));
    }


}
