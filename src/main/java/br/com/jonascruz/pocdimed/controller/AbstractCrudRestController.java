package br.com.jonascruz.pocdimed.controller;

import br.com.jonascruz.pocdimed.service.AbstractCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractCrudRestController <entity, service extends AbstractCrudService<entity>> {

    protected abstract service getService();

    @GetMapping()
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        return getService().findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable Long id, @RequestBody entity input) {
        return ResponseEntity.ok(getService().save(input));
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody entity input) {
        return ResponseEntity.ok(getService().save(input));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
