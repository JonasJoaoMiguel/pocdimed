package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.dto.LinhaLotacaoDTO;
import br.com.jonascruz.pocdimed.service.LinhaLotacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/linhaslotacao")
public class LinhaLotacaoRestController {

    @Autowired
    private LinhaLotacaoService linhaLotacaoService;

    protected LinhaLotacaoService getService() {
        return linhaLotacaoService;
    }

    @PostMapping("/linhalotacao")
    public ResponseEntity<?> salvaLotacao(){
        return ResponseEntity.ok(linhaLotacaoService.buscaLinhas());
    }

    @PutMapping("/linhalotacao/{id}")
    public ResponseEntity<?> update(@RequestBody LinhaLotacaoDTO linhaLotacaoDTO){
        return ResponseEntity.ok(linhaLotacaoService.linhaToObject(linhaLotacaoDTO));
    }

    @GetMapping("/linhalotacao")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(linhaLotacaoService.findAll());
    }

    @GetMapping("/linhalotacao/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(linhaLotacaoService.findById(id));
    }

    @DeleteMapping("/linhalotacao/{id}")
    public void excluir(@PathVariable("id") Long id) {
        linhaLotacaoService.delete(id);
    }

    @GetMapping("/linhalotacao/filter")
    public ResponseEntity<?> findByNome(@PathParam("nome") String nome) {
        return ResponseEntity.ok(linhaLotacaoService.findByNome(nome));
    }



}
