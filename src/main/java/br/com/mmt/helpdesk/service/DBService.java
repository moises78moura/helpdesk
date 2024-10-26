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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder encoder;


    public void instaciaDB(){
        Tecnico tecnico = new Tecnico(null, "Moises Moura", "33985840040", "moises@ig.com", encoder.encode("123456"));
        tecnico.addPerfil(Perfil.ADMIN);
//        Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("123"));
//        tec1.addPerfil(Perfil.ADMIN);
//        Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
//        Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
//        Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
//        Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));


        Cliente cliente = new Cliente(null, "Veronica", "59510644064", "veronica@ig.com", encoder.encode("123456"));

//        Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
//        Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
//        Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
//        Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
//        Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
//
//        Chamado c1 = new Chamado(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
//        Chamado c2 = new Chamado(null, null,Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
//        Chamado c3 = new Chamado(null, null,Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
//        Chamado c4 = new Chamado(null, null,Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
//        Chamado c5 = new Chamado(null, null,Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
//        Chamado c6 = new Chamado(null, null,Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

        Chamado chamado = new Chamado(null, null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Teste primeiro chamado", tecnico, cliente);

        tecnicoRepository.saveAll(Arrays.asList(tecnico));
        clienteRepository.saveAll(Arrays.asList(cliente));
        chamadoRepository.saveAll(Arrays.asList(chamado));

//        pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
//        chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));

    }

}
