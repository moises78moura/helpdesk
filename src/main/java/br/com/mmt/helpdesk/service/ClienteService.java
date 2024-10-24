package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Cliente;
import br.com.mmt.helpdesk.domain.Pessoa;
import br.com.mmt.helpdesk.domain.dtos.ClienteDTO;
import br.com.mmt.helpdesk.domain.repository.ClienteRepository;
import br.com.mmt.helpdesk.domain.repository.PessoaRepository;
import br.com.mmt.helpdesk.resources.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {


    private final ClienteRepository repository;
    private final PessoaRepository pessoaRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder encoder;

    public ClienteService(ClienteRepository repository, PessoaRepository pessoaRepository, ModelMapper modelMapper, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    public ClienteDTO findById(Integer id){
        String mensagem = String.format("Cliente com o ID: %s não encontrado! ",id);
        Cliente cliente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
        ClienteDTO ClienteDTO = modelMapper.map(cliente, ClienteDTO.class);
        return ClienteDTO;
    }

    public Cliente findClienteById(Integer id){
        String mensagem = String.format("Cliente com o ID: %s não encontrado! ",id);
        Cliente Cliente = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(mensagem));
        return Cliente;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public ClienteDTO save(ClienteDTO clienteDTO) {

        clienteDTO.setSenha(encoder.encode(clienteDTO.getSenha()));

        Cliente clienteToSave = modelMapper.map(clienteDTO, Cliente.class);
        checarSeExisteCpfCadastrado(clienteToSave);
        checarSeExisteEmailCadastrado(clienteToSave);

        clienteToSave = repository.save(clienteToSave);

        return modelMapper.map(clienteToSave, ClienteDTO.class);
    }

    private void checarSeExisteEmailCadastrado(Cliente Cliente) {
        Optional<Pessoa> ClienteSalvo = pessoaRepository.findByEmail(Cliente.getEmail());
        if(ClienteSalvo.isPresent() && Cliente.getId() != ClienteSalvo.get().getId()){
            String msg = String.format("Email: %s já está cadastrado no Sistema: ", Cliente.getEmail());
            throw new DataIntegrityViolationException(msg);
        }
    }

    private void checarSeExisteCpfCadastrado(Cliente Cliente) {

        Optional<Pessoa> ClienteSalvo = pessoaRepository.findByCpf(Cliente.getCpf());

        if(ClienteSalvo.isPresent() && Cliente.getId() != ClienteSalvo.get().getId()){
            String msg = String.format("CPF: %s já está cadastrado no Sistema: ", Cliente.getCpf());
            throw new DataIntegrityViolationException(msg);
        }
    }

    public ClienteDTO update(Integer id, ClienteDTO Cliente) {
        Cliente.setId(id);
        Pessoa pessoa = findClienteById(id);
        checarSeExisteCpfCadastrado((Cliente) pessoa);
        checarSeExisteEmailCadastrado((Cliente) pessoa);
        Cliente clienteToUpdate = modelMapper.map(Cliente, Cliente.class);

        return modelMapper.map(repository.save(clienteToUpdate), ClienteDTO.class);
    }

    public Cliente findByCpf(String cpf){
        String mensagem = String.format("Cliente com o cpf: %s não encontrado! ",cpf);
        return (Cliente) pessoaRepository.findByCpf(cpf).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

    public Cliente findByEmail(String email){
        String mensagem = String.format("Cliente com o email: %s não encontrado! ",email);
        return (Cliente) pessoaRepository.findByEmail(email).orElseThrow(() -> new ObjectNotFoundException(mensagem));
    }

    public void delete(Integer id) {

        Cliente Cliente = findClienteById(id);

        if (Cliente.getChamados().size() > 0){
            String mensagem = String.format("Cliente com o ID: %s possui Ordens de Serviço Aberta! Não pode ser excluido nesse momento ",id);
            throw new DataIntegrityViolationException(mensagem);
        }
        repository.deleteById(id);
    }
}
