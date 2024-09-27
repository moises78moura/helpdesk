package br.com.mmt.helpdesk.domain.repository;

import br.com.mmt.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
}
