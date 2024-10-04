package br.com.mmt.helpdesk.service;

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
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instaciaDB(){
        Tecnico tecnico = new Tecnico(null, "Moises Moura", "33985840040", "moises@ig.com", "132456");
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Veronica", "59510644064", "veronica@ig.com", "132456");

        Chamado chamado = new Chamado(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Teste primeiro chamado", tecnico, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(chamado));
    }

}
