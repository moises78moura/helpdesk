package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mmt.helpdesk.domain.repository.TecnicoRepository;
import br.com.mmt.helpdesk.resources.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {


    private final TecnicoRepository repository;

    private final ModelMapper modelMapper;

    public TecnicoService(TecnicoRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public Tecnico findById(Integer id){
        String mensagem = String.format("Técnico com o ID: %s não encontrado! ",id);
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public TecnicoDTO save(TecnicoDTO tecnicoDTO) {

        Tecnico tecnicoToSave = modelMapper.map(tecnicoDTO, Tecnico.class);

        tecnicoToSave = repository.save(tecnicoToSave);

        return modelMapper.map(tecnicoToSave, TecnicoDTO.class);
    }

}
