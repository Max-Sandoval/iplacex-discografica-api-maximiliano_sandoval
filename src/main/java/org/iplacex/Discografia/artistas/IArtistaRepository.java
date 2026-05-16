package org.iplacex.Discografia.artistas;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface IArtistaRepository extends MongoRepository<Artista, String> {
}