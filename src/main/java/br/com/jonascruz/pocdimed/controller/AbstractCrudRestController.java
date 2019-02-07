package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.service.AbstractCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCrudRestController <ENTITY, SERVICE extends AbstractCrudService<ENTITY>> {

    protected abstract SERVICE getService();

    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return getService().findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody ENTITY input) {
        return ResponseEntity.ok(getService().save(input));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody ENTITY input) {
        return ResponseEntity.ok(getService().save(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
