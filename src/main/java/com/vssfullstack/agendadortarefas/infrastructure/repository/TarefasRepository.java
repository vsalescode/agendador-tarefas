package com.vssfullstack.agendadortarefas.infrastructure.repository;

import com.vssfullstack.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {




}
