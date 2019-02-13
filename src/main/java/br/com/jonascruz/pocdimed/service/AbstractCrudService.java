package br.com.jonascruz.pocdimed.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public abstract class AbstractCrudService<entity> {

    protected abstract JpaRepository<entity, Long> getRepository();

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public entity save(entity entity){
        return getRepository().save(entity);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
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
