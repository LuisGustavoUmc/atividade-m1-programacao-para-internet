package com.pfc.thindesk.repository;

import com.pfc.thindesk.entity.Vaga;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends MongoRepository<Vaga, String> {
}
