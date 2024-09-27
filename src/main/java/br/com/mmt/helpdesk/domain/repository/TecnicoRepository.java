package br.com.mmt.helpdesk.domain.repository;

import br.com.mmt.helpdesk.domain.Pessoa;
import br.com.mmt.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
}
