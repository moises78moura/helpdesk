package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.repository.TecnicoRepository;
import br.com.mmt.helpdesk.resources.exceptions.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {


    private final TecnicoRepository repository;


    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        String mensagem = String.format("Técnico com o ID: %s não encontrado! ",id);
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

}
