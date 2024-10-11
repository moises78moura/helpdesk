package br.com.mmt.helpdesk.resources;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mmt.helpdesk.domain.enuns.Perfil;
import br.com.mmt.helpdesk.service.TecnicoService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.config.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    private final TecnicoService service;

    private final ModelMapper modelMapper;

    public TecnicoResource(TecnicoService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findBy(@PathVariable Integer id){

        Tecnico tecnico = service.findById(id);
        TecnicoDTO tecnicoDTO = modelMapper.map(tecnico, TecnicoDTO.class);
        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){

        List<Tecnico> tecnicos = service.findAll();
        List<TecnicoDTO> tecnicoDTOList = tecnicos.stream().map(tecnico -> modelMapper.map(tecnico, TecnicoDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(tecnicoDTOList);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnico){

        TecnicoDTO tecnicoResponse = service.save(tecnico);
        return ResponseEntity.ok().body(tecnicoResponse);
    }

}
