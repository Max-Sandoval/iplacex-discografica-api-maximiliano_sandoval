package org.iplacex.Discografia.discos;

import org.iplacex.Discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository repo;

    @Autowired
    private IArtistaRepository artistaRepo;

    
    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> crear(@RequestBody Disco disco) {

       
        if (!artistaRepo.existsById(disco.idArtista)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("El artista no existe");
        }

        Disco nuevo = repo.save(disco);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    
    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> listar() {

        return ResponseEntity.ok(repo.findAll());
    }

    
    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable String id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el disco");
        }

        return ResponseEntity.ok(repo.findById(id).get());
    }

    
    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Disco>> porArtista(@PathVariable String id) {

        return ResponseEntity.ok(repo.findDiscosByIdArtista(id));
    }
}