package br.com.mmt.helpdesk;

import br.com.mmt.helpdesk.domain.Chamado;
import br.com.mmt.helpdesk.domain.Cliente;
import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.enuns.Perfil;
import br.com.mmt.helpdesk.domain.enuns.Prioridade;
import br.com.mmt.helpdesk.domain.enuns.Status;
import br.com.mmt.helpdesk.domain.repository.ChamadoRepository;
import br.com.mmt.helpdesk.domain.repository.ClienteRepository;
import br.com.mmt.helpdesk.domain.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tecnico tecnico = new Tecnico(null, "Moises Moura", "33985840040", "moises@ig.com", "132456");
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Veronica", "59510644064", "veronica@ig.com", "132456");

        Chamado chamado = new Chamado(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Teste primeiro chamado", tecnico, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(chamado));

    }
}
