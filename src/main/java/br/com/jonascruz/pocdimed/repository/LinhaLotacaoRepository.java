package br.com.jonascruz.pocdimed.repository;

import br.com.jonascruz.pocdimed.entity.LinhaLotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinhaLotacaoRepository extends JpaRepository<LinhaLotacao, Long> {

    List<LinhaLotacao> findByNomeContainingIgnoreCase(String nome);
}
