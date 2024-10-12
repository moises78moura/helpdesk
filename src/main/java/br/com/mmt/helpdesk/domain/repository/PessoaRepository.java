package br.com.mmt.helpdesk.domain.repository;

import br.com.mmt.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

    Optional<Pessoa> findByEmail(String email);

    Optional<Pessoa>  findByCpf(String cpf);
}
