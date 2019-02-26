package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    List<Itinerario> findByIdlinha(Long id);
}
