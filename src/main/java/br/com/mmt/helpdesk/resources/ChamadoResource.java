package br.com.mmt.helpdesk.resources;

import br.com.mmt.helpdesk.domain.dtos.ChamadoDTO;
import br.com.mmt.helpdesk.service.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {


    private final ChamadoService service;




    public ChamadoResource(ChamadoService service) {
        this.service = service;
    }

    @RequestMapping(value = "{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        ChamadoDTO chamado = service.findById(id);

        return ResponseEntity.ok().body(chamado);
    }

    @RequestMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<ChamadoDTO> chamados = service.findAll();
        return ResponseEntity.ok().body(chamados);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Validated @RequestBody ChamadoDTO chamadoDTO){

        ChamadoDTO chamadoSalvo = service.save(chamadoDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(chamadoSalvo.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Validated @RequestBody ChamadoDTO chamadoDTO){

        ChamadoDTO chamadoSalvo = service.update(id, chamadoDTO);
        return ResponseEntity.ok().body(chamadoSalvo);
    }

}
