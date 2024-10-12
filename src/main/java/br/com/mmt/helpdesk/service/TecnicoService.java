package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Pessoa;
import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mmt.helpdesk.domain.repository.PessoaRepository;
import br.com.mmt.helpdesk.domain.repository.TecnicoRepository;
import br.com.mmt.helpdesk.resources.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {


    private final TecnicoRepository repository;
    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;

    public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
    }

    public TecnicoDTO findById(Integer id){
        String mensagem = String.format("Técnico com o ID: %s não encontrado! ",id);
        Tecnico tecnico = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
        TecnicoDTO tecnicoDTO = modelMapper.map(tecnico, TecnicoDTO.class);
        return tecnicoDTO;
    }

    public Tecnico findTecnicoById(Integer id){
        String mensagem = String.format("Técnico com o ID: %s não encontrado! ",id);
        Tecnico tecnico = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
        return tecnico;
    }

    public List<Tecnico> findAll() {
        return repository.findAll();
    }

    public TecnicoDTO save(TecnicoDTO tecnicoDTO) {


        Tecnico tecnicoToSave = modelMapper.map(tecnicoDTO, Tecnico.class);
        checarSeExisteCpfCadastrado(tecnicoToSave);
        checarSeExisteEmailCadastrado(tecnicoToSave);

        tecnicoToSave = repository.save(tecnicoToSave);

        return modelMapper.map(tecnicoToSave, TecnicoDTO.class);
    }

    private void checarSeExisteEmailCadastrado(Tecnico tecnico) {
        Optional<Pessoa> tecnicoSalvo = pessoaRepository.findByEmail(tecnico.getEmail());
        if(tecnicoSalvo.isPresent() && tecnico.getId() != tecnicoSalvo.get().getId()){
            String msg = String.format("Email: %s já está cadastrado no Sistema: ", tecnico.getEmail());
            throw new DataIntegrityViolationException(msg);
        }
    }

    private void checarSeExisteCpfCadastrado(Tecnico tecnico) {

        Optional<Pessoa> tecnicoSalvo = pessoaRepository.findByCpf(tecnico.getCpf());

        if(tecnicoSalvo.isPresent() && tecnico.getId() != tecnicoSalvo.get().getId()){
            String msg = String.format("CPF: %s já está cadastrado no Sistema: ", tecnico.getCpf());
            throw new DataIntegrityViolationException(msg);
        }
    }

    public TecnicoDTO update(Integer id, TecnicoDTO tecnico) {
        tecnico.setId(id);
        Pessoa pessoa = findTecnicoById(id);
        checarSeExisteCpfCadastrado((Tecnico) pessoa);
        checarSeExisteEmailCadastrado((Tecnico) pessoa);
        Tecnico tecnicoToUpdate = modelMapper.map(tecnico, Tecnico.class);

        return modelMapper.map(repository.save(tecnicoToUpdate), TecnicoDTO.class);
    }

    public Tecnico findByCpf(String cpf){
        String mensagem = String.format("Técnico com o cpf: %s não encontrado! ",cpf);
        return (Tecnico) pessoaRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

    public Tecnico findByEmail(String email){
        String mensagem = String.format("Técnico com o email: %s não encontrado! ",email);
        return (Tecnico) pessoaRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

    public void delete(Integer id) {

        Tecnico tecnico = findTecnicoById(id);

        if (tecnico.getChamados().size() > 0){
            String mensagem = String.format("Técnico com o ID: %s possui Ordens de Serviço Aberta! Não pode ser excluido nesse momento ",id);
            throw new DataIntegrityViolationException(mensagem);
        }
        repository.deleteById(id);
    }
}
