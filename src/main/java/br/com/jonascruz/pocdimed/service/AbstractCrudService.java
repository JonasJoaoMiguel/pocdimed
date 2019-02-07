package br.com.jonascruz.pocdimed.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public abstract class AbstractCrudService<ENTITY> {

    protected abstract JpaRepository<ENTITY, Long> getRepository();

    public ENTITY save(ENTITY entity){
        return getRepository().save(entity);
    }

    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    public Optional<ENTITY> findById(Long id){
        return getRepository().findById(id);
    }

    public Page<ENTITY> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }
}
