package br.com.mmt.helpdesk.resources;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mmt.helpdesk.service.TecnicoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

        TecnicoDTO tecnicoDTO = service.findById(id);

        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){

        List<Tecnico> tecnicos = service.findAll();
        List<TecnicoDTO> tecnicoDTOList = tecnicos.stream().map(tecnico -> modelMapper.map(tecnico, TecnicoDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(tecnicoDTOList);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody @Validated TecnicoDTO tecnico){

        TecnicoDTO tecnicoResponse = service.save(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(tecnicoResponse.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @RequestBody @Validated TecnicoDTO tecnico){

        TecnicoDTO tecnicoDTO = service.update(id, tecnico);
        return ResponseEntity.ok().body(tecnicoDTO);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
