package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.dto.ItinerarioDTO;
import br.com.jonascruz.pocdimed.entity.Itinerario;
import br.com.jonascruz.pocdimed.service.ItinerarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/itinerarios")
public class ItinerarioRestController {

    @Autowired
    private ItinerarioService itinerarioService;

    protected ItinerarioService getService() {
        return itinerarioService;
    }

    @PostMapping("/itinerario")
    public ResponseEntity<?> salvaCliente(@RequestBody ItinerarioDTO itinerarioDTO){
        return ResponseEntity.ok(itinerarioService.toObject(itinerarioDTO));
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

}
