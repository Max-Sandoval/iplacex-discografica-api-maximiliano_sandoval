package org.iplacex.Discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository repo;

    
    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Artista> crear(@RequestBody Artista artista) {

        Artista nuevo = repo.save(artista);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

   
    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Artista>> listar() {

        return ResponseEntity.ok(repo.findAll());
    }

    
    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> obtener(@PathVariable String id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el artista");
        }

        return ResponseEntity.ok(repo.findById(id).get());
    }

    
    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> actualizar(@PathVariable String id, @RequestBody Artista artista) {

        
        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el artista");
        }

        artista._id = id;
        return ResponseEntity.ok(repo.save(artista));
    }

    
    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> eliminar(@PathVariable String id) {

        if (!repo.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe el artista");
        }

        repo.deleteById(id);
        return ResponseEntity.ok("Artista eliminado");
    }
}