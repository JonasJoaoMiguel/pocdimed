package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.LinhaOnibus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinhaOnibusRepositoy extends JpaRepository<LinhaOnibus, Long> {

    public LinhaOnibus findByNome(String nome);

}
