package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.service.ItinerarioService;
import br.com.jonascruz.pocdimed.service.LinhaOnibusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itinerarios")
public class ItinerarioRestController {

    @Autowired
    private ItinerarioService itinerarioService;

    @Autowired
    private LinhaOnibusService linhaOnibusService;

    protected ItinerarioService getService() {
        return itinerarioService;
    }

    @PostMapping("/itinerario")
    public ResponseEntity<?> salvaItinerario(){
        return ResponseEntity.ok(itinerarioService.criaItinerarios());
    }

    @PutMapping("/itinerario/{id}")
    public ResponseEntity<?> update(@RequestBody Itinerario itinerario){
        return ResponseEntity.ok(itinerarioService.save(itinerario));
    }

    @GetMapping("/itinerario")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(itinerarioService.findAll());
    }

    @GetMapping("/itinerario/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itinerarioService.findById(id));
    }

    @DeleteMapping("/itinerario/{id}")
    public void excluir(@PathVariable("id") Long id) {
        itinerarioService.delete(id);
    }

    @GetMapping("/itinerario/idlinha/{id}")
    public ResponseEntity<?> buscaItinerarioPorLinha(@PathVariable("id") Long id) {
        return ResponseEntity.ok(itinerarioService.buscaItinerarioPorIdLinha(id));
    }

}
