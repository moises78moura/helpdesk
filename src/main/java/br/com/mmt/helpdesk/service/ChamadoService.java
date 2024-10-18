package br.com.mmt.helpdesk.service;

import br.com.mmt.helpdesk.domain.Chamado;
import br.com.mmt.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mmt.helpdesk.domain.enuns.Prioridade;
import br.com.mmt.helpdesk.domain.enuns.Status;
import br.com.mmt.helpdesk.domain.repository.ChamadoRepository;
import br.com.mmt.helpdesk.resources.exceptions.ObjectNotFoundException;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChamadoService {

    private final ChamadoRepository repository;
    private final ModelMapper chamadoModelMapper;

    public ChamadoService(ChamadoRepository repository, ModelMapper chamadoModelMapper) {
        this.repository = repository;
        this.chamadoModelMapper = chamadoModelMapper;
    }

    public ChamadoDTO findById(Integer id){

        Optional<Chamado> chamado = repository.findById(id);


        String mensagem = String.format("Chamado com o ID: %s não encontrado! ",id);
        Chamado chamadoResponse = chamado.orElseThrow(() -> new ObjectNotFoundException(mensagem));

        ChamadoDTO chamadoDTO = this.chamadoModelMapper.map(chamadoResponse, ChamadoDTO.class);
        return chamadoDTO;

    }

    public List<ChamadoDTO> findAll(){
        List<Chamado> chamados = repository.findAll();
        List<ChamadoDTO> chamadosDTO = chamados.stream().map(chamado -> this.chamadoModelMapper.map(chamado, ChamadoDTO.class)).collect(Collectors.toList());
        return chamadosDTO;
    }

    public ChamadoDTO save(ChamadoDTO chamadoDTO){
        map();
        Chamado chamadoToSave = this.chamadoModelMapper.map(chamadoDTO, Chamado.class);
        Chamado chamadoSaved = repository.save(chamadoToSave);
        return this.chamadoModelMapper.map(chamadoSaved, ChamadoDTO.class);
    }

    private void map() {
        TypeMap<ChamadoDTO, Chamado> typeMap = this.chamadoModelMapper.typeMap(ChamadoDTO.class, Chamado.class);
        Converter<Integer, Prioridade> toPrioridade = c -> Prioridade.toEnum(c.getSource());
        Converter<Integer, Status> toStatus = c -> Status.toEnum(c.getSource());
        typeMap.addMappings(mapper ->{
                    mapper.using(toPrioridade).map(ChamadoDTO::getCodigoPrioridade, Chamado::setPrioridade);
                    mapper.using(toStatus).map(ChamadoDTO::getCodigoStatus, Chamado::setStatus);
            }
        );
    }

    public ChamadoDTO update(Integer id, ChamadoDTO chamado){

//        Chamado chamadoToUpdate = findById(id);
//
//        chamadoToUpdate.setCliente(chamado.getCliente());
//        chamadoToUpdate.setDataAbertura(chamado.getDataAbertura());
//        chamadoToUpdate.setObservacao(chamado.getObservacao());
//        chamadoToUpdate.setPrioridade(chamado.getPrioridade());
//        chamadoToUpdate.setStatus(chamado.getStatus());
//        chamadoToUpdate.setTecnico(chamado.getTecnico());
//        chamadoToUpdate.setDataFechamento(chamado.getDataFechamento());
//
//        return repository.save(chamadoToUpdate);
        return null;
    }

}
