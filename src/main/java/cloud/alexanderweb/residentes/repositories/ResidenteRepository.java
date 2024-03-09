package cloud.alexanderweb.residentes.repositories;


import org.springframework.data.mongodb.repository.MongoRepository;

import cloud.alexanderweb.residentes.persistence.entities.Residente;

public interface ResidenteRepository extends MongoRepository<Residente, String> {
    
    
}
