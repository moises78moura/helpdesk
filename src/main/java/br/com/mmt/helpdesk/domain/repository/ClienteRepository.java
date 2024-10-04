package br.com.mmt.helpdesk.domain.repository;

import br.com.mmt.helpdesk.domain.Cliente;
import br.com.mmt.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}