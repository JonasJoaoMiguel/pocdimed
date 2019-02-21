package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.dto.CoordenadaGeograficaDTO;
import br.com.jonascruz.pocdimed.entity.CoordenadaGeografica;
import br.com.jonascruz.pocdimed.service.CoordenadaGeograficaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coordenadasgeograficas")
public class CoordenadaGeograficaRestController {

    @Autowired
    private CoordenadaGeograficaService coordenadaGeograficaService;

    protected CoordenadaGeograficaService getService() {
        return coordenadaGeograficaService;
    }

    @PostMapping("/coordenadageografica")
    public ResponseEntity<?> salvaItinerario(@RequestBody CoordenadaGeograficaDTO coordenadaGeograficaDTO){
        return ResponseEntity.ok(coordenadaGeograficaService.toObject(coordenadaGeograficaDTO));
    }

    @PutMapping("/coordenadageografica/{id}")
    public ResponseEntity<?> update(@RequestBody CoordenadaGeografica coordenadaGeografica){
        return ResponseEntity.ok(coordenadaGeograficaService.save(coordenadaGeografica));
    }

    @GetMapping("/coordenadageografica")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(coordenadaGeograficaService.findAll());
    }

    @GetMapping("/coordenadageografica/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(coordenadaGeograficaService.findById(id));
    }

    @DeleteMapping("/coordenadageografica/{id}")
    public void excluir(@PathVariable("id") Long id) {
        coordenadaGeograficaService.delete(id);
    }
}
