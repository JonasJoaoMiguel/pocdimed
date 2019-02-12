package br.com.jonascruz.pocdimed.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public abstract class AbstractCrudService<entity> {

    protected abstract JpaRepository<entity, Long> getRepository();

    public entity save(entity entity){
        return getRepository().save(entity);
    }

    public void delete(Long id) {
        getRepository().deleteById(id);
    }

    public Optional<entity> findById(Long id){
        return getRepository().findById(id);
    }

    public List<entity> findAll() {
        return getRepository().findAll();
    }
}
