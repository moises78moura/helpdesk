package br.com.mmt.helpdesk.resources;

import br.com.mmt.helpdesk.domain.Cliente;
import br.com.mmt.helpdesk.domain.dtos.ClienteDTO;
import br.com.mmt.helpdesk.service.ClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/clientes")
public class ClienteResource {

    private final ClienteService service;

    private final ModelMapper modelMapper;

    public ClienteResource(ClienteService service, ModelMapper modelMapper) {
        this.service = service;
        this.modelMapper = modelMapper;
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findBy(@PathVariable Integer id){

        ClienteDTO clienteDTO = service.findById(id);

        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){

        List<Cliente> clientes = service.findAll();
        List<ClienteDTO> clienteDTOList = clientes.stream().map(cliente -> modelMapper.map(cliente, ClienteDTO.class)).collect(Collectors.toList());

        return ResponseEntity.ok().body(clienteDTOList);
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody @Validated ClienteDTO cliente){

        ClienteDTO clienteResponse = service.save(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(clienteResponse.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @RequestBody @Validated ClienteDTO cliente){

        ClienteDTO clienteDTO = service.update(id, cliente);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
