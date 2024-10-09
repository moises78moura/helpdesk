package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.repository.TecnicoRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {


    private final TecnicoRepository repository;


    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow();
    }

}
