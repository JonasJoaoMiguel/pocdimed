package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinhaOnibusRepositoy extends JpaRepository<LinhaOnibus, Long> {

    List<LinhaOnibus> findByNomeContainingIgnoreCase(String nome);

}
