package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
