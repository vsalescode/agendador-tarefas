package com.vssfullstack.agendadortarefas.business.mapper;

import com.vssfullstack.agendadortarefas.business.dto.TarefasDTO;
import com.vssfullstack.agendadortarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO tarefasDTO);
    TarefasDTO paraTarefaDTO(TarefasEntity tarefasEntity);


    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTO> tarefasDTOS);
    List<TarefasDTO> paraListaTarefasDTO(List<TarefasEntity> tarefasEntity);


}
