package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long> {

    //Itinerario findByItinerario(Itinerario itinerario);
}
