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

import java.time.LocalDateTime;
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
        return this.chamadoModelMapper.map(chamadoResponse, ChamadoDTO.class);

    }

    public List<ChamadoDTO> findAll(){
        List<Chamado> chamados = repository.findAll();
        return chamados.stream().map(chamado -> this.chamadoModelMapper.map(chamado, ChamadoDTO.class)).collect(Collectors.toList());
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

        if(chamado.getCodigoStatus().equals(Status.ENCERRADO.getCodigo())){
            chamado.setDataFechamento(LocalDateTime.now());
        }
        ChamadoDTO chamadoToUpdate = findById(id);
        if (chamadoToUpdate != null && chamadoToUpdate.getId().equals(chamado.getId()) && chamado.getId().equals(id)){
            return save(chamado);
        }

        return null;
    }

}
