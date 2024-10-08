package br.com.mmt.helpdesk.resources;

import br.com.mmt.helpdesk.domain.Tecnico;
import br.com.mmt.helpdesk.domain.dtos.TecnicoDTO;
import br.com.mmt.helpdesk.service.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    private final TecnicoService service;

    public TecnicoResource(TecnicoService service) {
        this.service = service;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findBy(@PathVariable Integer id){

        Tecnico tecnico = service.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){

        List<Tecnico> tecnicos = service.findAll();
        List<TecnicoDTO> tecnicoDTOList = tecnicos.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());

        return ResponseEntity.ok().body(tecnicoDTOList);
    }

}
